package ru.practicum.bank.cash.contract;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.practicum.bank.cash.models.CashDto;
import ru.practicum.bank.cash.models.OperationResult;
import ru.practicum.bank.cash.service.CashProccesingService;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(excludeAutoConfiguration = {OAuth2ClientAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class})
public abstract class BaseContractTest {
    @MockitoBean
    OAuth2AuthorizedClientManager authorizedClientManager;
    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    CashProccesingService cashProccesingService;

    @BeforeEach
    public void setup() throws Exception {
        // Настраиваем RestAssuredMockMvc с нужным контекстом
        Mockito.when(cashProccesingService.cashProcessing(any(CashDto.class))).thenReturn(new OperationResult().setStatus(200));

        io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc(
                MockMvcBuilders.webAppContextSetup(context).build()
        );


    }
}