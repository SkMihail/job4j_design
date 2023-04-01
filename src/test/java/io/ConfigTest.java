package io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenWithComment() {
        String path = "data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect")).isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("#")).isNull();
    }

    @Test
    void testWithSeveralSignOfEquals() {
        String path = "data/withSeveralSignOfEquals.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate")).isEqualTo("connection.password=password=");
    }
    @Test
    void testLoadWithInvalidTemplate() {
        String path = "data/with_illegal_Template.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(IllegalArgumentException.class, config::load);
        String expectedMessage = "Line contains an invalid template";
        String actualMessage = exception.getMessage();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void testLoadWhenOneSignOfEqualsEnd() {
        String path = "data/with_illegal_Template2.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(IllegalArgumentException.class, config::load);
        String expectedMessage = "Line contains an invalid template";
        String actualMessage = exception.getMessage();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void testLoadWhenNoOneSign() {
        String path = "data/with_illegal_Template3.properties";
        Config config = new Config(path);
        Exception exception = assertThrows(IllegalArgumentException.class, config::load);
        String expectedMessage = "Line contains an invalid template";
        String actualMessage = exception.getMessage();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }
}