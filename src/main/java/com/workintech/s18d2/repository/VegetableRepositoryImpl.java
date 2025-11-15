package com.workintech.s18d2.repository;

import com.workintech.s18d2.entity.Vegetable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VegetableRepositoryImpl implements VegetableRepository {

    private final EntityManager entityManager;

    @Override
    public Vegetable save(Vegetable vegetable) {
        return entityManager.merge(vegetable);
    }

    @Override
    public Optional<Vegetable> findById(Long id) {
        Vegetable vegetable = entityManager.find(Vegetable.class, id);
        return Optional.ofNullable(vegetable);
    }

    @Override
    public List<Vegetable> getByPriceAsc() {
        TypedQuery<Vegetable> query = entityManager.createQuery(
                "SELECT v FROM Vegetable v ORDER BY v.price ASC", Vegetable.class);
        return query.getResultList();
    }

    @Override
    public List<Vegetable> getByPriceDesc() {
        TypedQuery<Vegetable> query = entityManager.createQuery(
                "SELECT v FROM Vegetable v ORDER BY v.price DESC", Vegetable.class);
        return query.getResultList();
    }

    @Override
    public List<Vegetable> searchByName(String name) {
        TypedQuery<Vegetable> query = entityManager.createQuery(
                "SELECT v FROM Vegetable v WHERE LOWER(v.name) LIKE LOWER(:name)", Vegetable.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public void delete(Vegetable vegetable) {
        Vegetable managed = vegetable;
        if (!entityManager.contains(vegetable)) {
            managed = entityManager.merge(vegetable);
        }
        entityManager.remove(managed);
    }
}
