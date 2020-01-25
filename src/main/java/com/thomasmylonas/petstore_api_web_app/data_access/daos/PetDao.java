package com.thomasmylonas.petstore_api_web_app.data_access.daos;

import com.thomasmylonas.petstore_api_web_app.data_access.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
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
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component  // (value = "petDao") // With this, 2 beans are created
public class PetDao implements JpaRepository<Pet, Integer> {

    private static final Logger LOGGER = LogManager.getLogger(PetDao.class.getName());

    // @PersistenceContext
    private EntityManager em;
    private Transaction tx;

    public PetDao(LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        EntityManagerFactory emf = entityManagerFactoryBean.getObject();
        this.em = emf.createEntityManager();
        this.tx = (Transaction) em.getTransaction();
    }

    @Override
    public Pet getOne(Integer id) {
        return em.find(Pet.class, id);
    }

    @Override
    public List<Pet> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Pet> cq = cb.createQuery(Pet.class);
        Root<Pet> pet = cq.from(Pet.class);
        CriteriaQuery<Pet> all = cq.select(pet);
        TypedQuery<Pet> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
//    @Transactional
    public <S extends Pet> S save(S s) {
        S sPersisted = null;
        // "Evaluate" window: s.photoUrls
        try {
            tx.begin();
//            sPersisted = em.merge(s);
            em.persist(s);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
        return s;
    }

    @Override
    public void deleteById(Integer id) {

        try {
            tx.begin();

            Pet petToDelete = em.find(Pet.class, id);

            if (petToDelete == null) {
                throw new ResourceNotFoundException(id, "The pet with id = %d, is not found");
            }

            em.remove(petToDelete);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
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
    public void delete(Pet pet) {

    }

    @Override
    public void deleteAll(Iterable<? extends Pet> iterable) {

    }

    @Override
    public void deleteAll() {

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
