package it.sevenbits.project.application.model;

/**
 * Provides methods for managing createdAt and updatedAt fields as Unix timestamp
 */
public interface ITimestampable {

    /**
     * Returns createdAt as Unix timestamp
     * @return createdAt as Unix timestamp
     */
    Long getCreatedAt();

    /**
     * Sets createdAt as Unix timestamp
     * @param createdAt Unix timestamp value to set
     */
    void setCreatedAt(Long createdAt);

    /**
     * Returns updatedAt as Unix timestamp
     * @return updatedAt as Unix timestamp
     */
    Long getUpdatedAt();

    /**
     * Sets updatedAt as Unix timestamp
     * @param updatedAt Unix timestamp value to set
     */
    void setUpdatedAt(Long updatedAt);

}
