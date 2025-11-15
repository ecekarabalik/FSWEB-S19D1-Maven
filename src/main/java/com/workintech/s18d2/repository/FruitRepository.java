package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;

import java.util.List;
import java.util.Optional;

public interface FruitRepository {

    Fruit save(Fruit fruit);

    Optional<Fruit> findById(Long id);

    List<Fruit> getByPriceAsc();

    List<Fruit> getByPriceDesc();

    List<Fruit> searchByName(String name);

    void delete(Fruit fruit);
}
