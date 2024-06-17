package _dev;

import app.Main;
import dev.jeka.core.tool.JkInjectClasspath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@JkInjectClasspath("org.junit.jupiter:junit-jupiter:5.10.2")
class MainTest {

    private static final PrintStream OUT = System.out;;

    ByteArrayOutputStream baos;

    @BeforeEach
    void beforeEach() {
        baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
    }

    @AfterEach
    void afterEach() {
        System.setOut(OUT);
    }

    @Test
    void help_handled() {
        main("--help");
        Assertions.assertTrue(baos.toString().contains("Usage:"));
    }

    @Test
    void distrib_handled() {
        main("--distrib");
        Assertions.assertTrue(baos.toString().contains("temurin"));
        Assertions.assertTrue(baos.toString().contains("corretto"));
    }

    @Test
    void temurin_handled() {
        main("temurin");
        Assertions.assertTrue(baos.toString().contains("17"));
    }

    @Test
    void invalidInput_handled() {
        Assertions.assertThrows(Exception.class, () -> {
            main("invalid input");
        });
    }

    private void main(String... args) {
        Main.main(args);
    }

}