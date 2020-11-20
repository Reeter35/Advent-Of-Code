package fr.reeter.advent2020.wires;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.awt.Point;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest
public class WiresIntersectionFinderTest {
    @Autowired
    private Logger logger;

    @Autowired
    private WireReader reader;

    @Autowired
    private WiresIntersectionFinder intersectionFinder;

    @Test
    public void testClosestFinder() {
        List<List<Point>> wires = reader.getWires();
        logger.info("Loaded wires: " + wires.size());

        List<Point> intersections = intersectionFinder.findAllIntersections(wires.get(0), wires.get(1));
        logger.info("Intersections: " + intersections.size());

        Point closest = intersectionFinder.findClosestToCentral(intersections);
        logger.info("Closest: " + closest);

        int distance = intersectionFinder.manhattanDistanceToCentral(closest);
        assertThat(distance).isEqualTo(159);
    }
}
