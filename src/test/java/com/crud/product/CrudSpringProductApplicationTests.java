package com.crud.product;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Assertions.assertTrue(mysql.isRunning());
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("e2e")
public class CrudSpringProductApplicationTests {

	@Container
	static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
			.withDatabaseName("testdb")
			.withUsername("testuser")
			.withPassword("testpass")
			.withStartupTimeout(Duration.ofSeconds(180))
            .withInitScript("init.sql")
            .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(180)));


	@DynamicPropertySource
	static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);

    }

	@Test
	void contextLoads() {
		// Verifica se o contexto carrega com sucesso
	}

	@Test
	void mysqlContainerShouldBeRunning() {
        assertTrue(mysql.isRunning());

    }

}

