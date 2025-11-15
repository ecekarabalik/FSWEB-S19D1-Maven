package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;

import java.util.List;
import java.util.Optional;

public interface VegetableRepository {

    Vegetable save(Vegetable vegetable);

    Optional<Vegetable> findById(Long id);

    // TESTİN ARADIĞI METOT İSİMLERİ
    List<Vegetable> getByPriceAsc();
    List<Vegetable> getByPriceDesc();
    List<Vegetable> searchByName(String name);

    void delete(Vegetable vegetable);
}
