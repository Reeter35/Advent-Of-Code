package fr.reeter.advent2020.intcode;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class IntCodeRunnerTest {
    @Autowired
    private IntCodeRunner runner;

    @Test
    public void testRun() {
        assertThat(runner.run()).isEqualTo(3500);
    }
}
