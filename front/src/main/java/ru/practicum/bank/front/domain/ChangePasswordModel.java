package ru.practicum.bank.front.domain;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;

public class ChangePasswordModel {

    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public ChangePasswordModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public ChangePasswordModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    @AssertTrue(message = "Values are invalid")
    private boolean isValid() {
        return password != null && password.equals(confirmPassword);
    }
}
