package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Когда GET /api/hello вызван, сервис B возвращает сообщение с приветствием"
    request {
        method 'GET'
        url '/api/hello'
        // Можно указать необходимые заголовки запроса, например Accept или Authorization.
        // В нашем случае оставим пусто для простоты.
    }
    response {
        status 200
        headers {
            contentType(applicationJson())
        }
        body(
                message: "Hello from B"
        )
    }
}