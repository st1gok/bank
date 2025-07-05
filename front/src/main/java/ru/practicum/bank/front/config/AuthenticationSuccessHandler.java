package ru.practicum.bank.front.config;

/**
 * Обработчик успешных аутентификаций пользователя
 */
public interface AuthenticationSuccessHandler {

    void onAuthenticationSuccess(String username);

}
