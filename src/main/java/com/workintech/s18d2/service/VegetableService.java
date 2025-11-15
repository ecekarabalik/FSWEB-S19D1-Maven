package com.workintech.s18d2.service;

import com.workintech.s18d2.entity.Vegetable;
import java.util.List;

public interface VegetableService {

    Vegetable getById(Long id);

    Vegetable save(Vegetable vegetable);

    List<Vegetable> getByPriceAsc();

    List<Vegetable> getByPriceDesc();

    List<Vegetable> searchByName(String name);

    Vegetable delete(Long id);
}

