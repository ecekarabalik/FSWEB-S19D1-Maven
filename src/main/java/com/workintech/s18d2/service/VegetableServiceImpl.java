package com.workintech.s18d2.service;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.ApiException;
import com.workintech.s18d2.repository.VegetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VegetableServiceImpl implements VegetableService {

    private final VegetableRepository vegetableRepository;

    private void validateId(Long id) {
        if (id == null || id < 0) {
            throw new ApiException("Id cannot be negative!", HttpStatus.BAD_REQUEST);
        }
    }

    private void validateVegetable(Vegetable vegetable) {
        if (vegetable == null) {
            throw new ApiException("Vegetable cannot be null!", HttpStatus.BAD_REQUEST);
        }
        if (vegetable.getName() == null || vegetable.getName().isEmpty()) {
            throw new ApiException("Vegetable name cannot be empty!", HttpStatus.BAD_REQUEST);
        }
        if (vegetable.getPrice() == null || vegetable.getPrice() <= 0) {
            throw new ApiException("Vegetable price must be larger than zero!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Vegetable getById(Long id) {
        validateId(id);

        Optional<Vegetable> opt = vegetableRepository.findById(id);
        return opt.orElseThrow(() ->
                new ApiException("Vegetable not found!", HttpStatus.NOT_FOUND));
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        validateVegetable(vegetable);
        return vegetableRepository.save(vegetable);
    }

    @Override
    public List<Vegetable> getByPriceAsc() {
        return vegetableRepository.findAllByPriceAsc();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        return vegetableRepository.findAllByPriceDesc();
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        return vegetableRepository.findByNameContains(name);
    }

    @Override
    public Vegetable delete(Long id) {
        validateId(id);

        Optional<Vegetable> opt = vegetableRepository.findById(id);

        Vegetable vegetable = opt.orElseThrow(() ->
                new ApiException("Vegetable not found!", HttpStatus.NOT_FOUND));

        vegetableRepository.delete(vegetable);
        return vegetable;
    }
}
