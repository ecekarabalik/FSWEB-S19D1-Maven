package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;

import java.util.List;
import java.util.Optional;

public interface FruitRepository {

    Fruit save(Fruit fruit);

    Optional<Fruit> findById(Long id);

    // TESTLERİN ARADIĞI METOT İSİMLERİ
    List<Fruit> findAllByPriceAsc();
    List<Fruit> findAllByPriceDesc();
    List<Fruit> findByNameContains(String name);

    void delete(Fruit fruit);
}
