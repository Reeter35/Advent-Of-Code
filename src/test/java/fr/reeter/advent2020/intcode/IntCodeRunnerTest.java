package fr.reeter.advent2020.intcode;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.stream.Collectors;

@ActiveProfiles("test")
@SpringBootTest
public class IntCodeRunnerTest {
    @Autowired
    private IntCodeRunner runner;


    @Test
    public void testRun() {
        int[] testCodes = {1,9,10,3,2,3,11,0,99,30,40,50};
        runner.init(testCodes);
        assertThat(runner.run()).isEqualTo(3500);
    }
}
