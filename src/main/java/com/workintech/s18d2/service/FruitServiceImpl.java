package com.workintech.s18d2.service;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.exceptions.ApiException;
import com.workintech.s18d2.repository.FruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    private void validateId(Long id) {
        if (id == null || id < 0) {
            throw new ApiException("Id cannot be negative!", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateFruit(Fruit fruit) {
        if (fruit == null) {
            throw new ApiException("Fruit cannot be null!", HttpStatus.BAD_REQUEST);
        }
        if (fruit.getName() == null || fruit.getName().isEmpty()) {
            throw new ApiException("Fruit name cannot be empty!", HttpStatus.BAD_REQUEST);
        }
        if (fruit.getPrice() == null || fruit.getPrice() <= 0) {
            throw new ApiException("Fruit price must be larger than zero!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Fruit getById(Long id) {
        validateId(id);
        Optional<Fruit> fruitOpt = fruitRepository.findById(id);

        return fruitOpt.orElseThrow(() ->
                new ApiException("Fruit not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public Fruit save(Fruit fruit) {
        validateFruit(fruit);
        return fruitRepository.save(fruit);
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.findAllByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.findAllByPriceDesc();
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return fruitRepository.findByNameContains(name);
    }

    @Override
    public Fruit delete(Long id) {
        validateId(id);

        Optional<Fruit> fruitOpt = fruitRepository.findById(id);

        Fruit fruit = fruitOpt.orElseThrow(() ->
                new ApiException("Fruit not found!", HttpStatus.NOT_FOUND));

        fruitRepository.delete(fruit);
        return fruit;
    }
}
