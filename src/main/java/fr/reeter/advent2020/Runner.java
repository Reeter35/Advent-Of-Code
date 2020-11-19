package fr.reeter.advent2020;

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


    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Runnning the application...");

        // TODO launch the code

    }
}