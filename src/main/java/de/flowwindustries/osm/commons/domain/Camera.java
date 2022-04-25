package de.flowwindustries.osm.commons.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Camera entity to be extracted and persisted from the .pbf-files.
 */
@Data
@Entity
public class Camera {

    @Id
    private String identifier;

    private Long osmId;

    private String cameraMount;
    private String cameraType;
    private String cameraDirection;

    private String surveillanceZone;
    private String surveillanceType;
    private String surveillance;
}
