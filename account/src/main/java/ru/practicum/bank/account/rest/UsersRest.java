package ru.practicum.bank.account.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.practicum.bank.account.rest.dto.AccountDto;
import ru.practicum.bank.account.rest.dto.BankAccountDto;
import ru.practicum.bank.account.rest.dto.RegistrationRequest;
import ru.practicum.bank.account.rest.dto.UserDto;
import ru.practicum.bank.account.service.AccountService;
import ru.practicum.bank.account.service.UserService;

import java.util.List;

@RestController()
public class UsersRest {

    private final UserService userService;
    private final AccountService accountService;

    public UsersRest(UserService userService , AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

//    @PostMapping("/api/v1/authenticate")
//    public ResponseEntity<UserDto> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
//        if (userService.authenticate(authenticationRequest)) {
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.status(401).build();
//    }

    @GetMapping("/api/v1/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/api/v1/users/find/{login}")
    public ResponseEntity<UserDto> findUser(@PathVariable String login) {
       return ResponseEntity.ok(userService.getUser(login).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    @PutMapping("/api/v1/users/{login}")
    public ResponseEntity<Void> updateUser(@RequestBody UserDto userDto, @PathVariable String login) {
        userService.changePassword(userDto.setLogin(login));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/v1/users/registration")
    public ResponseEntity<Void> registration(@RequestBody RegistrationRequest registrationRequest) {
        userService.register(registrationRequest);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/api/v1/users/{login}/data")
    public ResponseEntity<AccountDto> getUserData(@PathVariable String login) {
        var account = userService.getUserData(login);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/api/v1/users/{login}/data")
    public ResponseEntity<Void> editAccount(@RequestBody AccountDto account, @PathVariable String login) {
        userService.updateAccount(account, login);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/v1/users/{login}/accounts")
    public ResponseEntity<List<BankAccountDto>> getAccount(@PathVariable String login) {
        var account = accountService.getAccounts(login);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/api/v1/users/{login}/accounts")
    public ResponseEntity<BankAccountDto> createAccount(@PathVariable String login, @RequestBody BankAccountDto account) {
        accountService.createAccount(login, account);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/api/v1/users/{login}/accounts/{accId}")
    public ResponseEntity<Void> createAccount(@PathVariable String login, @PathVariable Long accId) {
        accountService.delete(accId);
        return ResponseEntity.noContent().build();
    }
}
