package fr.reeter.advent2020.password;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class PasswordHackerTest {
    @Autowired
    private PasswordHacker hacker;

    @Test
    void testHacker() {
        assertThat(hacker.findAllPasswords(111110, 111112).size()).isEqualTo(2);
    }

    @Test
    void testIsValid() {
        int pwd = 111111;
        assertThat(hacker.isValid(pwd)).isFalse();

        pwd = 223450;
        assertThat(hacker.isValid(pwd)).isFalse();

        pwd = 123789;
        assertThat(hacker.isValid(pwd)).isFalse();

        pwd = 766661;
        assertThat(hacker.isValid(pwd)).isFalse();

        pwd = 112233;
        assertThat(hacker.isValid(pwd)).isTrue();

        pwd = 123444;
        assertThat(hacker.isValid(pwd)).isFalse();

        pwd = 111122;
        assertThat(hacker.isValid(pwd)).isTrue();
    }
}
