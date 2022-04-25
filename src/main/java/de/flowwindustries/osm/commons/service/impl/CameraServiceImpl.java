package de.flowwindustries.osm.commons.service.impl;

import de.flowwindustries.osm.commons.domain.Camera;
import de.flowwindustries.osm.commons.repository.CameraRepository;
import de.flowwindustries.osm.commons.service.CameraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Service implementation of {@link CameraService} interface.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CameraServiceImpl implements CameraService {

    private final static String CAMERA_NOT_FOUND = "Camera with identifier %s not found";

    private final CameraRepository cameraRepository;

    @Override
    public void create(Camera dto) {
        dto.setIdentifier(UUID.randomUUID().toString());
        log.debug("Request to store camera: {}", dto);
        cameraRepository.save(dto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Camera> get(String identifier) {
        log.debug("Request to get camera with identifier: {}", identifier);
        return cameraRepository.findById(identifier);
    }

    @Override
    @Transactional(readOnly = true)
    public Camera getSafe(String identifier) {
        return get(identifier).orElseThrow(
                () -> new IllegalArgumentException(String.format(CAMERA_NOT_FOUND, identifier)));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Camera> getAll(Pageable pageable) {
        log.debug("Request to get all cameras");
        return cameraRepository.findAll(pageable);
    }

    @Override
    public void update(String identifier, Camera dto) {
        log.debug("Request to update camera with identifier: {}", identifier);

        Camera camera = getSafe(identifier);
        camera.setCameraType(dto.getCameraType());
        camera.setCameraMount(dto.getCameraMount());
        camera.setCameraDirection(dto.getCameraDirection());
        camera.setSurveillance(dto.getSurveillance());
        camera.setSurveillanceZone(dto.getSurveillanceZone());
        camera.setSurveillanceType(dto.getSurveillanceType());
        camera.setOsmId(dto.getOsmId());

        cameraRepository.save(camera);
    }

    @Override
    public void delete(String identifier) {
        log.debug("Request to delete camera with identifier: {}", identifier);
        cameraRepository.deleteById(identifier);
    }
}
