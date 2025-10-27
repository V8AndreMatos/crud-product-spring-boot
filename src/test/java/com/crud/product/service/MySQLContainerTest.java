package com.crud.product.service;

import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MySQLContainer;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
public class MySQLContainerTest {

    @Container
    private static final MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("pass");

    @Test
    void testContainerIsRunning() {
        assertTrue(mysql.isRunning());
    }
}
