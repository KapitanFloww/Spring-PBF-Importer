package de.flowwindustries.osm.commons.service.impl;

import de.flowwindustries.osm.commons.domain.Camera;
import de.flowwindustries.osm.commons.service.CameraService;
import de.flowwindustries.osm.commons.service.ImportService;
import de.topobyte.osm4j.core.access.OsmIterator;
import de.topobyte.osm4j.core.model.iface.EntityType;
import de.topobyte.osm4j.core.model.iface.OsmNode;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import de.topobyte.osm4j.pbf.seq.PbfIterator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Map;

/**
 * Service implementation of {@link PbfImportService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PbfImportService implements ImportService {

    private final CameraService cameraService;

    @Async
    @Override
    public void importPbf(FileInputStream in) {
        log.debug("Request to extract camera data");
        OsmIterator iterator = new PbfIterator(in, true);
        iterator.forEach(container -> {
            if(container.getType() == EntityType.Node) {

                OsmNode node = (OsmNode) container.getEntity();
                Map<String, String> tags = OsmModelUtil.getTagsAsMap(node);

                //check if node is camera
                String surveillance = tags.get("surveillance");
                boolean isCamera = surveillance != null;

                if(isCamera) {
                    Camera camera = new Camera();
                    camera.setSurveillance(tags.get("surveillance"));
                    camera.setSurveillanceZone(tags.get("surveillance:zone"));
                    camera.setSurveillanceType(tags.get("surveillance:type"));

                    camera.setCameraMount(tags.get("camera:mount"));
                    camera.setCameraType(tags.get("camera:type"));
                    camera.setCameraDirection(tags.get("camera:direction"));

                    camera.setOsmId(node.getId());

                    cameraService.create(camera);
                }
            }
        });
    }
}
