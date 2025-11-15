package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Fruit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FruitRepositoryImpl implements FruitRepository {

    private final EntityManager entityManager;

    @Override
    public Fruit save(Fruit fruit) {
        return entityManager.merge(fruit);
    }

    @Override
    public Optional<Fruit> findById(Long id) {
        Fruit fruit = entityManager.find(Fruit.class, id);
        return Optional.ofNullable(fruit);
    }

    @Override
    public List<Fruit> findAllByPriceAsc() {
        TypedQuery<Fruit> query =
                entityManager.createQuery("SELECT f FROM Fruit f ORDER BY f.price ASC", Fruit.class);
        return query.getResultList();
    }

    @Override
    public List<Fruit> findAllByPriceDesc() {
        TypedQuery<Fruit> query =
                entityManager.createQuery("SELECT f FROM Fruit f ORDER BY f.price DESC", Fruit.class);
        return query.getResultList();
    }

    @Override
    public List<Fruit> findByNameContains(String name) {
        TypedQuery<Fruit> query =
                entityManager.createQuery("SELECT f FROM Fruit f WHERE LOWER(f.name) LIKE LOWER(:name)", Fruit.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public void delete(Fruit fruit) {
        entityManager.remove(fruit);
    }
}
