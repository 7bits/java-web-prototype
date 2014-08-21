package it.sevenbits.project.application.model;

import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * Base class for persistent objects with ID and Unix timestamp fields
 * for managing created and updated dates of the object
 * @param <Entity> child class
 */
public abstract class AbstractEntity<Entity extends Serializable> implements ITimestampable, Serializable {

    private static final long serialVersionUID = -3920707135660443143L;

    private Long id = null;
    private Long createdAt = null;
    private Long updatedAt = null;

    /**
     * Default constructor - creates a new instance with no values set
     */
    public AbstractEntity() {
        this(0L, null, null);
    }

    /**
     * Constructor to set name property
     * @param id ID property
     * @param createdAt date of creation
     * @param updatedAt date of updating
     */
    public AbstractEntity(
            final Long id,
            final Long createdAt,
            final Long updatedAt
    ) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Clones this object with all nested objects fully copied
     * @return copy of this
     */
    public Entity deepClone() {
        try {
            return SerializationUtils.clone((Entity) this);
        } catch (SerializationException e) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    @Override
    public Long getCreatedAt() {
        return createdAt;
    }

    @Override
    public Long getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public void setCreatedAt(final Long createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public void setUpdatedAt(final Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
