package ru.practicum.bank.front.service;

import ru.practicum.bank.front.client.dto.UserData;
import ru.practicum.bank.front.domain.UserDataModel;
import ru.practicum.bank.front.domain.SignupModel;

public interface UserService {
    void changePassword(String password);

    void registration(SignupModel signupModel) throws Exception;

    UserDataModel getUserDataModel();
    UserData getCurrentUserData();
    UserData getUserData(String login);

    void updateUserInfo(UserDataModel userDataModel);

    Object getUsers();
}

