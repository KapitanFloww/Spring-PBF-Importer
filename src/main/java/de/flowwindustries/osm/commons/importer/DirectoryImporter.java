package de.flowwindustries.osm.commons.importer;

import de.flowwindustries.osm.commons.service.ImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.import.enabled", havingValue = "true")
public class DirectoryImporter {

    @Value("${application.import.directory}")
    private String importDir;

    private final ImportService importService;

    @PostConstruct
    void init() {
        log.info("Loading import files");
        File dir = new File(importDir);
        File[] files = dir.listFiles((pathname, name) -> name.toLowerCase(Locale.ROOT).endsWith(".pbf"));
        if(files == null) {
            log.warn("No files found to import");
            return;
        }
        Arrays.stream(files).forEach(file -> {
            try {
                if(file.isDirectory()) {
                    return;
                }
                log.info("Importing PBF: {}", file.getAbsolutePath());
                importService.importPbf(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                log.warn(e.getMessage());
            }
        });
    }
}
