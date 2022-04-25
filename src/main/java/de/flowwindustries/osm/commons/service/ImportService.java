package de.flowwindustries.osm.commons.service;

import java.io.FileInputStream;

/**
 * Service interface for OSM data importer implementations.
 */
public interface ImportService {

    /**
     * Take the given .pbf inside the {@link FileInputStream} and import the data.
     * @param in pbf. file to import
     */
    void importPbf(FileInputStream in);
}
