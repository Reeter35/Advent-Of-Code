package fr.reeter.advent2020.intcode;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

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
            logger.info("Reading command at index: " + codeIndex);
            int command = intCodes[codeIndex];

            // OpCode is contained in the two last digits
            int opCode = command%100;

            // parmaters are the first digits
            int paramMode1 = (command/100)%10;
            int paramMode2 = (command/1000)%10;
            int paramMode3 = (command/10000)%10;
            logger.info("Parameters mode + " + paramMode1 + "," + paramMode2 + "," + paramMode3);

            switch(opCode) {
                case 1: // Read the 3 other indexes and call the function
                    logger.info("ADD command received");
                    add(getParam(intCodes[codeIndex+1], paramMode1), getParam(intCodes[codeIndex+2], paramMode2), intCodes[codeIndex+3]);
                    codeIndex+=4;
                    break;
                case 2: // Read the 3 other indexes and call the function
                    logger.info("MULTIPLY command received");
                    mult(getParam(intCodes[codeIndex+1], paramMode1), getParam(intCodes[codeIndex+2], paramMode2), intCodes[codeIndex+3]);
                    codeIndex+=4;
                    break;
                case 3:
                    logger.info("INPUT/STORE command received");
                    input(intCodes[codeIndex+1]);
                    codeIndex+=2;
                    break;
                case 4:
                    logger.info("OUTPUT comamnd received");
                    output(intCodes[codeIndex+1]);
                    codeIndex+=2;
                    break;
                case 5:
                    logger.info("JUMP IF TRUE comamnd received");
                    if(getParam(intCodes[codeIndex+1], paramMode1) != 0) {
                        codeIndex = getParam(intCodes[codeIndex+2], paramMode2);
                    }
                    else{
                        codeIndex += 3;
                    }
                    break;
                case 6:
                    logger.info("JUMP IF FALSE comamnd received");
                    if(getParam(intCodes[codeIndex+1], paramMode1) == 0) {
                        codeIndex = getParam(intCodes[codeIndex+2], paramMode2);
                    }
                    else{
                        codeIndex += 3;
                    }
                    break;
                case 7:
                    logger.info("LESS THAN comamnd received");

                    if(getParam(intCodes[codeIndex+1], paramMode1) < getParam(intCodes[codeIndex+2], paramMode2)) {
                        intCodes[intCodes[codeIndex+3]] = 1;
                    }
                    else {
                        intCodes[intCodes[codeIndex+3]] = 0;
                    }
                    codeIndex += 4;
                    break;
                case 8:
                    logger.info("EQUALS command received");

                    if(getParam(intCodes[codeIndex+1], paramMode1) == getParam(intCodes[codeIndex+2], paramMode2)) {
                        intCodes[intCodes[codeIndex+3]] = 1;
                    }
                    else {
                        intCodes[intCodes[codeIndex+3]] = 0;
                    }
                    codeIndex += 4;
                    break;
                case 99:
                    logger.info("EXIT command received");
                    exitCommand=true;
                    break;
                default:
                    logger.info("Unkwnown command: " + opCode);
                    exitCommand=true;
            }
        }

        return intCodes[0];
    }

    /**
     * Gets the parameter from the index and the parameter mode
     * @param value
     * @param mode
     * @return
     */
    private int getParam(final int value, final int mode) {
        if(mode == 0) {
            // mode 0: position mode
            return intCodes[value];
        }

        // mode 1: immediate mode
        return value;
    }

    /**
     * Adds the values op1 and op2, and write the resuls at resultIdx
     * @param op1
     * @param op2
     * @param resultIdx
     */
    private void add(final int op1, final int op2, final int resultIdx) {
        logger.debug("   add: " + op1 + " + " + op2 + " ==> " + resultIdx);
        intCodes[resultIdx] = op1 + op2;
        logger.debug("Result written at index: " + resultIdx + " : " + intCodes[resultIdx]);
    }

    /**
     * Multiplies the values op1 and op2, and write the resuls at resultIdx
     * @param op1
     * @param op2
     * @param resultIdx
     */
    private void mult(final int op1, final int op2, final int resultIdx) {
        logger.debug("   mult: " + op1 + " x " + op2 + " ==> " + resultIdx);
        intCodes[resultIdx] = op1 * op2;
        logger.debug("Result written at index: " + resultIdx + " : " + intCodes[resultIdx]);
    }

    /**
     * Reads an input and store it at given position
     * @param index
     */
    private void input(final int index) {
        Scanner inputScanner = new Scanner(System.in);
        int input = inputScanner.nextInt();
        logger.info("   store: " + input + " to position " + index);
        intCodes[index] = input;
    }

    private void output(final int index) {
        System.out.println("  ==> TEST OUTPUT: " + intCodes[index]);
    }
}
