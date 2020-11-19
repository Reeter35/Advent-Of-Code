package fr.reeter.advent2020.fuel;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FuelCalculatorTest {
    
    @Autowired
    private FuelCalculator calculator;

    @Test
    public void testFuelForModule() {
        /*
        For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
        For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
        For a mass of 1969, the fuel required is 654.
        For a mass of 100756, the fuel required is 33583.
        */
        assertThat(calculator.fuelForModule(12)).isEqualTo(2);
        assertThat(calculator.fuelForModule(14)).isEqualTo(2);
        assertThat(calculator.fuelForModule(1969)).isEqualTo(654);
        assertThat(calculator.fuelForModule(100756)).isEqualTo(33583);

    }

    @Test
    public void testFuelForAllModule() {
        /*
        For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
        For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
        For a mass of 1969, the fuel required is 654.
        For a mass of 100756, the fuel required is 33583.
        */
        int[] testModules = new int[4];
        testModules[0]=12;
        testModules[1]=14;
        testModules[2]=1969;
        testModules[3]=100756;
        assertThat(calculator.fuelForAllModules(testModules)).isEqualTo(2+2+654+33583);

    }
}