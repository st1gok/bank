package ru.practicum.bank.account;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
		OAuth2ClientAutoConfiguration.class,
})
@Testcontainers
@ContextConfiguration(initializers = {AccountApplicationTests.Initializer.class})
class AccountApplicationTests {
	@MockitoBean
	OAuth2AuthorizedClientManager authorizedClientManager;
	@Test
	void contextLoads() {
	}

	private static final String DATABASE_NAME = "account";

	@Container
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:17-bullseye")
			.withReuse(true)
			.withDatabaseName(DATABASE_NAME);

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.datasource.username=" + postgreSQLContainer.getUsername(),
					"spring.datasource.password=" + postgreSQLContainer.getPassword(),
					"spring.datasource.url=" + postgreSQLContainer.getJdbcUrl()
			).applyTo(configurableApplicationContext.getEnvironment());
		}
	}

}
