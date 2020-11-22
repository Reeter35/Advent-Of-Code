package fr.reeter.advent2020.intcode;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;


@ActiveProfiles("test")
@SpringBootTest
public class IntCodeRunnerTest {
    @Autowired
    private IntCodeRunner runner;


    @Test
    public void testRun() {
        int[] testCodes = {1002,4,3,4,33};
        runner.init(testCodes);
        assertThat(runner.run()).isEqualTo(1002);
    }
}
