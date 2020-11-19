package fr.reeter.advent2020.intcode;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that runs a computer base on Int Code
 *
 */
@Service
public class IntCodeRunner {
    @Autowired
    private Logger logger;

    private List<Integer> intCodes;

    @Autowired
    public IntCodeRunner(@Value("classpath:intcode.input") final Resource modulesInput) throws IOException {
        final String[] codes = (Files.readString(modulesInput.getFile().toPath())).split(",");
        intCodes = Arrays.stream(codes).map(Integer::parseInt).collect(Collectors.toList());
    }

    public void fixValue(final int index, final int value) {
        intCodes.set(index, value);
    }

    /**
     * Runs the computer on the given input
     * @return the first integer in the array
     */
    public int run() {
        int codeIndex=0;
        boolean exitCommand=false;
        while(!exitCommand && codeIndex<intCodes.size()) {
            logger.info("Reading comamnd at index: " + codeIndex);
            int command = intCodes.get(codeIndex);

            switch(command) {
                case 1: // Read the 3 other indexes and call the function
                    logger.info("ADD command received");
                    add(intCodes.get(codeIndex+1), intCodes.get(codeIndex+2), intCodes.get(codeIndex+3));
                    codeIndex+=4;
                    break;
                case 2: // Read the 3 other indexes and call the function
                    logger.info("MULTIPLY command received");
                    mult(intCodes.get(codeIndex+1), intCodes.get(codeIndex+2), intCodes.get(codeIndex+3));
                    codeIndex+=4;
                    break;
                case 99:
                    logger.info("EXIT command received");
                    exitCommand=true;
                    break;
            }
        }

        return intCodes.get(0);
    }

    /**
     * Adds the values located at opIdx1 and OpIdx2, and write the resuls at resultIdx
     * @param opIdx1
     * @param opdIdx2
     * @param resultIdx
     */
    private void add(final int opIdx1, final int opIdx2, final int resultIdx) {
        intCodes.set(resultIdx, intCodes.get(opIdx1) + intCodes.get(opIdx2));
        logger.info("Result written at index: " + resultIdx + " : " + intCodes.get(resultIdx));
    }

    /**
     * Multiplies the values located at opIdx1 and OpIdx2, and write the resuls at resultIdx
     * @param opIdx1
     * @param opdIdx2
     * @param resultIdx
     */
    private void mult(final int opIdx1, final int opIdx2, final int resultIdx) {
        intCodes.set(resultIdx, intCodes.get(opIdx1) * intCodes.get(opIdx2));
        logger.info("Result written at index: " + resultIdx + " : " + intCodes.get(resultIdx));
    }
}
