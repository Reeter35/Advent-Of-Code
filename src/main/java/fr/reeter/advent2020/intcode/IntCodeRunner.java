package fr.reeter.advent2020.intcode;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
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

    private int[] intCodes;

    public void init(final List<Integer> initIntCodes) {
        intCodes = initIntCodes.stream().mapToInt(i->i).toArray();
    }

    public void init(final int[] initIntCodes) {
        intCodes = initIntCodes;
    }

    public void fixValue(final int index, final int value) {
        intCodes[index] = value;
    }

    /**
     * Runs the computer on the given input
     * @return the first integer in the array
     */
    public int run() {
        int codeIndex=0;
        boolean exitCommand=false;
        while(!exitCommand && codeIndex<intCodes.length) {
            logger.debug("Reading command at index: " + codeIndex);
            int command = intCodes[codeIndex];

            switch(command) {
                case 1: // Read the 3 other indexes and call the function
                    logger.debug("ADD command received");
                    add(intCodes[codeIndex+1], intCodes[codeIndex+2], intCodes[codeIndex+3]);
                    codeIndex+=4;
                    break;
                case 2: // Read the 3 other indexes and call the function
                    logger.debug("MULTIPLY command received");
                    mult(intCodes[codeIndex+1], intCodes[codeIndex+2], intCodes[codeIndex+3]);
                    codeIndex+=4;
                    break;
                case 99:
                    logger.debug("EXIT command received");
                    exitCommand=true;
                    break;
            }
        }

        return intCodes[0];
    }

    /**
     * Adds the values located at opIdx1 and OpIdx2, and write the resuls at resultIdx
     * @param opIdx1
     * @param opIdx2
     * @param resultIdx
     */
    private void add(final int opIdx1, final int opIdx2, final int resultIdx) {
        intCodes[resultIdx] = intCodes[opIdx1] + intCodes[opIdx2];
        logger.debug("Result written at index: " + resultIdx + " : " + intCodes[resultIdx]);
    }

    /**
     * Multiplies the values located at opIdx1 and OpIdx2, and write the resuls at resultIdx
     * @param opIdx1
     * @param opIdx2
     * @param resultIdx
     */
    private void mult(final int opIdx1, final int opIdx2, final int resultIdx) {
        intCodes[resultIdx] = intCodes[opIdx1] * intCodes[opIdx2];
        logger.debug("Result written at index: " + resultIdx + " : " + intCodes[resultIdx]);
    }
}
