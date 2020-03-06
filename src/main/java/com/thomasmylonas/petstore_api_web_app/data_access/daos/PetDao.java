package com.thomasmylonas.petstore_api_web_app.data_access.daos;

import com.thomasmylonas.petstore_api_web_app.data_access.entities.Pet;
import com.thomasmylonas.petstore_api_web_app.exception_handlers.exceptions.ResourceNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component  // (value = "petDao") // With this, 2 beans are created
public class PetDao implements GenericJpaDao<Pet, Integer> {

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
    public Pet findById(Integer id) {

        Pet petResult = null;
        try {
            petResult = em.find(Pet.class, id);
        } catch (IllegalArgumentException e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
        return petResult;
    }

    @Override
    public List<Pet> findAllById(Iterable<Integer> iterable) {
        return null;
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
    public <S extends Pet> S save(S pet) {

        S petPersisted = null;
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
//            sPersisted = em.merge(s);
            em.persist(pet);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
        return pet;
    }

    @Override
    public <S extends Pet> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public <S extends Pet> S update(S pet) {

        Pet updatedPet = null;
        Integer id = pet.getId();
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            Pet petToUpdate = em.find(Pet.class, id);
            if (petToUpdate == null) {
                throw new IllegalArgumentException();
            }
            updatedPet = em.merge(pet);
            em.flush();
            tx.commit();
        } catch (IllegalArgumentException e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
        return (S) updatedPet;
    }

    @Override
    public <S extends Pet> void update(S pet, Integer id) {

        /*
        This method is another way to update
         */
        Integer id1 = pet.getId(); // id;
        Pet petToBeUpdated = em.find(Pet.class, id1);
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
//            pet.setEverything(petToBeUpdated);
            em.flush();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void deleteById(Integer id) {

        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            Pet petToDelete = em.find(Pet.class, id);
            if (petToDelete == null) {
                throw new ResourceNotFoundException(id, "The pet with id: %d, is not found");
            }
            em.remove(petToDelete);
            tx.commit();
        } catch (IllegalArgumentException e) {
            tx.rollback();
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(Pet pet) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public void deleteAllGiven(Iterable<? extends Pet> iterable) {
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {
    }
}
