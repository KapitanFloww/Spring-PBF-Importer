package de.flowwindustries.osm.commons.web;

import de.flowwindustries.osm.commons.domain.Camera;
import de.flowwindustries.osm.commons.service.CameraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource for {@link Camera} entities.
 */
@RestController
@RequestMapping(value = "/api/v1/camera")
@RequiredArgsConstructor
public class CameraResource {

    private final CameraService cameraService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Camera>> getAll(@RequestParam int pageSize, @RequestParam int pageNumber) {
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageNumber);
        return ResponseEntity.ok(cameraService.getAll(pageable));
    }

    @GetMapping(value = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Camera> get(@PathVariable("identifier") String identifier) {
        return ResponseEntity.ok(cameraService.getSafe(identifier));
    }
}
