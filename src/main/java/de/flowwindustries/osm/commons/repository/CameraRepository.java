package de.flowwindustries.osm.commons.repository;

import de.flowwindustries.osm.commons.domain.Camera;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for {@link Camera} entities.
 */
@Repository
public interface CameraRepository extends PagingAndSortingRepository<Camera, String> {
}
