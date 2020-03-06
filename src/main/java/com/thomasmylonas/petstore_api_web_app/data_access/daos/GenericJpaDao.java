package com.thomasmylonas.petstore_api_web_app.data_access.daos;

import org.springframework.data.repository.Repository;

import javax.persistence.EntityManager;

/**
 * Interface for generic CRUD operations on a repository for a specific type
 */
public interface GenericJpaDao<T, ID> extends Repository<T, ID> {

    /**
     * Returns a reference to the entity with the given identifier. Depending on how the JPA persistence provider is
     * implemented this is very likely to always return an instance and throw an
     * {@link javax.persistence.EntityNotFoundException} on first access. Some of them will reject invalid identifiers
     * immediately.
     *
     * @param id must not be {@literal null}.
     * @return a reference to the entity with the given identifier.
     * @see EntityManager#getReference(Class, Object) for details on when an exception is thrown.
     */
    T findById(ID id);

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param idsList
     * @return
     */
    Iterable<T> findAllById(Iterable<ID> idsList);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<T> findAll();

    /**
     * Saves a given entity. Use the returned instance for further operations as the
     * save operation might have changed the entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    <S extends T> S save(S entity);

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    /**
     * Update the entity with the given entity, with the same id.
     *
     * @param entity must not be {@literal null}.
     */
    <S extends T> S update(S entity);

    /**
     * Update the entity with the given entity, with the given id.
     *
     * @param entity must not be {@literal null}.
     */
    <S extends T> void update(S entity, ID id);

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    void delete(T entity);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    void deleteAllGiven(Iterable<? extends T> entities);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    long count();

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    boolean existsById(ID id);

    /**
     * Flushes all pending changes to the database.
     */
    void flush();
}
