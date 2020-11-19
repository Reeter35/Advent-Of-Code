package fr.reeter.advent2020.fuel;

import org.springframework.stereotype.Service;

@Service
public class FuelCalculator {

    /**
     * Compute the amount of fuel to take for the given module mass
     * @param moduleMass The mass of the module
     * @return the amount of fuel to take
     */
    public int fuelForModule(final int moduleMass) {
        
        /*
        Fuel required to launch a given module is based on its mass.
        Specifically, to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
        */

        return Math.max((int)Math.floor(((double)moduleMass)/3.0) - 2, 0);
    }

    /**
     * Compute the amount of fuel for a given list of modules
     * @param moduleMasses the list of modules
     * @return the total amount of fuel
     */
    public int fuelForAllModules(final int[] moduleMasses) {
        int totalMass = 0;

        for(int i=0;i<moduleMasses.length;i++) {
            totalMass += fuelForModule(moduleMasses[i]);
        }

        return totalMass;

    }

}