package com.thomasmylonas.petstore_api_web_app.daos;

import com.thomasmylonas.petstore_api_web_app.models.Pet;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component  // (value = "petDao") // With this, 2 beans are created
public class PetDao implements JpaRepository<Pet, Integer> {

    // @PersistenceContext
    private EntityManager em;

    public PetDao(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        EntityManagerFactory emf = entityManagerFactoryBean.getObject();
        em = emf.createEntityManager();
    }

    @Override
    public List<Pet> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
        Root<Pet> rootEntry = cq.from(Pet.class);
        CriteriaQuery<Pet> all = cq.select(rootEntry);
        TypedQuery<Pet> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public List<Pet> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Pet> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Pet> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Pet pet) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pet> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Pet> S save(S s) {
        return null;
    }

    @Override
    public <S extends Pet> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Pet> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Pet> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Pet> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Pet getOne(Integer id) {
        return em.find(Pet.class, id);
    }

    @Override
    public <S extends Pet> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Pet> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Pet> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Pet> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Pet> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Pet> boolean exists(Example<S> example) {
        return false;
    }
}
