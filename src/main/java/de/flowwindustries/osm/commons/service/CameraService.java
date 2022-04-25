package de.flowwindustries.osm.commons.service;

import de.flowwindustries.osm.commons.domain.Camera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Crud-Service interface for {@link Camera}-entities.
 */
public interface CameraService {

    /**
     * Create a camera.
     * @param dto
     */
    void create(Camera dto);

    /**
     * Get a specific camera.
     * @param identifier
     * @return
     */
    Optional<Camera> get(String identifier);

    /**
     * Get a specific camera (null-safe).
     * @param identifier
     * @return
     */
    Camera getSafe(String identifier);

    /**
     * Get all cameras on page.
     * @param pageable
     * @return
     */
    Page<Camera> getAll(Pageable pageable);

    /**
     * Update a camera.
     * @param identifier
     * @param dto
     */
    void update(String identifier, Camera dto);

    /**
     * Delete a camera.
     * @param identifier
     */
    void delete(String identifier);
}
