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
        assertThat(calculator.fuelForModule(1969)).isEqualTo(966);
        assertThat(calculator.fuelForModule(100756)).isEqualTo(50346);

    }

    @Test
    public void testFuelForAllModule() {
        /*
        For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
        A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0,
        which would call for a negative fuel), so the total fuel required is still just 2.
        At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216
        then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So,
         the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
        The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12
         + 2 = 50346..
        */
        int[] testModules = new int[4];
        testModules[0]=12;
        testModules[1]=14;
        testModules[2]=1969;
        testModules[3]=100756;
        assertThat(calculator.fuelForAllModules(testModules)).isEqualTo(2+2+966+50346);

    }
}