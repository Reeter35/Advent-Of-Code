package fr.reeter.advent2020.intcode;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that defines an Int Code computer, able to run programs and adjust to get a specific output
 */
@Component
public class IntCodeComputer {

    private List<Integer> initalIntCodes;

    @Autowired
    private Logger logger;

    @Autowired
    private IntCodeRunner runner;

    @Autowired
    public IntCodeComputer(@Value("classpath:intcode.input") final Resource modulesInput) throws IOException {
        final String[] codes = (Files.readString(modulesInput.getFile().toPath())).split(",");
        initalIntCodes = Arrays.stream(codes).map(Integer::parseInt).collect(Collectors.toList());
    }

    /**
     * Find the noun and verb to get given output
     * @param lookupOutput the output to reach
     * @return String a couple Noun,Verb
     */
    public String findNounAndVerb(final int lookupOutput) {
        boolean found = false;
        for(int noun=0;noun<initalIntCodes.size();noun++){
            for(int verb=0;verb<initalIntCodes.size();verb++){
                logger.info("Trying pair: " + noun + "," + verb);
                runner.init(initalIntCodes);
                runner.fixValue(1, noun);
                runner.fixValue(2, verb);
                if(runner.run()==lookupOutput) {
                    logger.info("Nound and verbs found: " + noun + " , " + verb);
                    return ""+noun+","+verb;
                }
            }
        }

        return "";
    }
}
