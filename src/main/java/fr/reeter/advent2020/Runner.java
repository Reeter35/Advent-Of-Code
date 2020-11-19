package fr.reeter.advent2020;

import fr.reeter.advent2020.fuel.FuelCalculator;
import fr.reeter.advent2020.modules.ModulesParser;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!test")
@Component
public class Runner implements ApplicationRunner {

    @Autowired
    private Logger logger;

    @Autowired
    private FuelCalculator fuelCalculator;

    @Autowired
    private ModulesParser modules;


    @Override
    public void run(final ApplicationArguments args) throws Exception {
        logger.info("Runnning the application...");


        logger.info("Fuel mass to take: " + fuelCalculator.fuelForAllModules(modules.getModulesAsArray()));

    }
}