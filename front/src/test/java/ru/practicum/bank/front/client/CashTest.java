package ru.practicum.bank.front.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.client.RestTemplate;
import ru.practicum.bank.front.client.dto.CashDto;
import ru.practicum.bank.front.client.dto.OperationResult;
import ru.practicum.bank.front.client.dto.UserData;
import ru.practicum.bank.front.domain.CashAction;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
        ids = "ru.practicum.bank:cash:+:stubs:8880",    // координаты артефакта стаба B
        stubsMode = StubRunnerProperties.StubsMode.LOCAL
)
@EnableAutoConfiguration(exclude = {
        OAuth2ClientAutoConfiguration.class,
        OAuth2ResourceServerAutoConfiguration.class,
})
public class CashTest {
    @MockitoBean
    OAuth2AuthorizedClientManager authorizedClientManager;


    @Test
    void getData_shouldReturnMessageFromB() {

        CashDto cashDto = new CashDto().setAction(CashAction.GET)
                .setAccount(1l)
                .setAmount(100d)
                .setUser(new UserData().setName("name").setSurname("surname").setEmail("my@email.com"));
        OperationResult response =  new RestTemplate().postForObject("http://localhost:8880"+"/api/v1/cash", cashDto, OperationResult.class);

        Assertions.assertTrue(response.getStatus() == 200);
    }
}
