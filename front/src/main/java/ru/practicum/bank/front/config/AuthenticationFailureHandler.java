package ru.practicum.bank.front.config;

/**
 * Обработчик неуспешных аутентификаций пользователя
 */
public interface AuthenticationFailureHandler {

    void onAuthenticationFailure(String username);

}
