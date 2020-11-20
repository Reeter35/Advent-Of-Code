package fr.reeter.advent2020.wires;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads a wire file descriptor
 */
@Service
public class WireReader {
    @Autowired
    private Logger logger;

    private List<List<Point>> wires = new ArrayList<>();

    private List<String> wireDesc;

    @Autowired
    public WireReader(@Value("classpath:wires.input") final Resource wiresInput) throws IOException {
        wireDesc = Files.readAllLines(wiresInput.getFile().toPath());

    }

    @PostConstruct
    public void init() {
        for(String wire: wireDesc) {
            logger.info("Reading wire description: " + wire);
            wires.add(read(wire));
        }
    }
    /**
     * Reads a wire description and gets all points
     * @param description
     * @return
     */
    public List<Point> read(final String description) {
        logger.info("Read wires description: " + description);
        List<Point> wire = new ArrayList<>();
        Point point = new Point(0,0);
        wire.add(point);
        for(String vertex: description.split(",")) {
            char direction = vertex.charAt(0);
            int length = Integer.parseInt(vertex.substring(1));
            int offsetX=0;
            int offsetY=0;
            if(direction == 'R') {
                offsetX=1;
            } else if (direction == 'L') {
                offsetX=-1;
            } else if (direction == 'U') {
                offsetY=1;
            } else if (direction == 'D') {
                offsetY=-1;
            }

            point = new Point(point.x+offsetX*length, point.y+offsetY*length);
            wire.add(point);
            logger.info("Add vertex: " + point);

        }

        return wire;
    }

    public List<List<Point>> getWires() { return wires;}
}
