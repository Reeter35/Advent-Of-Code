package fr.reeter.advent2020;

import fr.reeter.advent2020.fuel.FuelCalculator;
import fr.reeter.advent2020.intcode.IntCodeComputer;
import fr.reeter.advent2020.modules.ModulesParser;
import fr.reeter.advent2020.wires.WireReader;
import fr.reeter.advent2020.wires.WiresIntersectionFinder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.awt.Point;
import java.util.List;

@Profile("!test")
@Component
public class Runner implements ApplicationRunner {

    @Autowired
    private Logger logger;

    @Autowired
    private FuelCalculator fuelCalculator;

    //@Autowired
    //private IntCodeRunner intCodeRunner;

    @Autowired
    private IntCodeComputer computer;

    @Autowired
    private ModulesParser modules;

    @Autowired
    private WireReader reader;

    @Autowired
    private WiresIntersectionFinder intersectionFinder;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        logger.info("Runnning the application...");


        logger.info("Fuel mass to take: " + fuelCalculator.fuelForAllModules(modules.getModulesAsArray()));


        // Day 2;
        // Fix gravity values for int code computer
        //intCodeRunner.fixValue(1, 12);
        //intCodeRunner.fixValue(2, 2);
        //logger.info("IntCode computer result: " + intCodeRunner.run());
        // logger.info("Computer result: " + computer.findNounAndVerb(19690720));

        // Day 3
        List<List<Point>> wires = reader.getWires();
        List<Point> intersections = intersectionFinder.findAllIntersections(wires.get(0), wires.get(1));
        Point closest = intersectionFinder.findClosestToCentral(intersections);
        logger.info("Closest point: " + closest);
        logger.info("Distance to central: " + intersectionFinder.manhattanDistanceToCentral(closest));

    }
}