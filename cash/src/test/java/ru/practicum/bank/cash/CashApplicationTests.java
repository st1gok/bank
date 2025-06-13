package ru.practicum.bank.cash;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
@EnableAutoConfiguration(exclude = {
		OAuth2ClientAutoConfiguration.class,
})
class CashApplicationTests {
	@MockitoBean
	OAuth2AuthorizedClientManager authorizedClientManager;
	@Test
	void contextLoads() {
	}

}
