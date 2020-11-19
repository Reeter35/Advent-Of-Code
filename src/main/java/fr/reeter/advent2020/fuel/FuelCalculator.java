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
        /*
        Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2.
        However, that fuel also requires fuel, and that fuel requires fuel, and so on. Any mass that would require
        negative fuel should instead be treated as if it requires zero fuel; the remaining mass, if any, is instead
         handled by wishing really hard, which has no mass and is outside the scope of this calculation.
         */

        int fuel = (int)Math.floor(((double)moduleMass)/3.0) - 2;
        if(fuel > 0) {
            return fuel+fuelForModule(fuel);
        }
        return 0;
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