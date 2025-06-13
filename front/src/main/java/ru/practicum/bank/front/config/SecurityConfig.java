package ru.practicum.bank.front.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${gateway.host}")
    private String gatewayHost;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/login").permitAll()
//                        .anyRequest().authenticated()
//                ).formLogin(customizer -> customizer
//                        .loginPage(gatewayHost+"/login")
//                        .failureForwardUrl(gatewayHost+"/login?error")
//                        .failureUrl(gatewayHost+"/login?error")
//                        .defaultSuccessUrl(gatewayHost+"/", true))
//                .logout(customizer -> customizer.logoutSuccessUrl(gatewayHost+"/login"))
//                .csrf(csrf -> csrf.disable());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
//                ).formLogin(Customizer.withDefaults())
                ).formLogin(customizer ->

                        customizer
//                                successForwardUrl("http://localhost:8080")
//                                .failureForwardUrl("http://localhost:8080/login?error")
                                .defaultSuccessUrl("http://localhost:8080")
//                                .failureUrl("http://localhost:8080")
                                .failureUrl("http://localhost:8080/login?error"))
                .logout(customizer -> customizer.logoutSuccessUrl("http://localhost:8080/login?logout"))
                .securityContext(context -> context
                        .requireExplicitSave(false)
                )
                .headers(headers -> headers
                        .httpStrictTransportSecurity(hsts -> hsts.disable())
                )
                .csrf(csrf -> csrf.disable());
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/login").permitAll()
//                        .anyRequest().authenticated()
//                ).formLogin(customizer -> customizer
//                        // Путь к представлению с формой логина
//                        .loginPage(gatewayHost+"/login")
//                        // Адрес, на который перенаправляет сервер в случае успешной аутентификации.
////                        .successForwardUrl(gatewayHost+"/")
//                        .failureForwardUrl(gatewayHost+"/login?error")
//                        // Адрес, на который вас перенаправляет сервер в случае неуспешной аутентификации.
//                        // Значение по умолчанию: /login?error
//                        .failureUrl(gatewayHost+"/login?error")
////                        formLogin -> formLogin.loginPage(gatewayHost+"/login")
////                        .loginProcessingUrl(gatewayHost+"/login"))
//                        .defaultSuccessUrl(gatewayHost+"/", true))
//                .logout(customizer -> customizer.logoutSuccessUrl(gatewayHost+"/login"));
////                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
////
//        return http.build();
//    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    UserDetailsService userDetailsService(AccountClient accountClient) {
//        return new RemoteUserDetailsManager(accountClient);
//    }
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
////        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
}
