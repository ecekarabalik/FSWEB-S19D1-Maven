package com.workintech.s18d2.controller;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.service.FruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fruits")
public class FruitController {

    private final FruitService fruitService;

    // TESTİN BEKLEDİĞİ: /fruits/{id}
    @GetMapping("/{id}")
    public Fruit getById(@PathVariable Long id) {
        return fruitService.getById(id);
    }

    // TESTİN BEKLEDİĞİ: /fruits/asc
    @GetMapping("/asc")
    public List<Fruit> getByPriceAsc() {
        return fruitService.getByPriceAsc();
    }

    // TESTİN BEKLEDİĞİ: /fruits/desc
    @GetMapping("/desc")
    public List<Fruit> getByPriceDesc() {
        return fruitService.getByPriceDesc();
    }

    // TESTİN BEKLEDİĞİ: /fruits/search/{name}
    @GetMapping("/search/{name}")
    public List<Fruit> search(@PathVariable String name) {
        return fruitService.searchByName(name);
    }

    // TESTİN BEKLEDİĞİ: /fruits (POST)
    @PostMapping
    public Fruit save(@RequestBody Fruit fruit) {
        return fruitService.save(fruit);
    }

    // TESTİN BEKLEDİĞİ: /fruits/{id} (DELETE)
    @DeleteMapping("/{id}")
    public Fruit delete(@PathVariable Long id) {
        return fruitService.delete(id);
    }
}
