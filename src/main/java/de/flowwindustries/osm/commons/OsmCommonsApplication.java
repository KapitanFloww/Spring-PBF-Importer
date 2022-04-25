package de.flowwindustries.osm.commons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * Main class.
 */
@SpringBootApplication
public class OsmCommonsApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(OsmCommonsApplication.class, args);
    }

}
