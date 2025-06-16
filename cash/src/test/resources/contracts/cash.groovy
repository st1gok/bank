package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "Когда Post /api/v1/cash вызван, сервис возвращает сообщение со статусом 200"
    request {
        method 'POST'
        url '/api/v1/cash'
        body '''\
            {
                "account": 1,
                "amount": 100,
                "user": {
                    "name":"name",
                    "surname":"surname",
                    "email":"my@email.com"
                },
                "action": "GET"
            }
        '''
        headers {
            contentType('application/json')
        }
    }
    response {
        status 200
        headers {
            contentType('application/json')
        }
        body '''\
            {"status": 200}
        '''
    }
}