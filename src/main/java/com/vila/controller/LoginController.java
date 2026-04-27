package com.vila.controller;

import com.vila.SceneFactory;
import com.vila.dao.UserDao;
import com.vila.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    private SceneFactory sceneFactory;

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @FXML
    private void onLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            UserDao dao = UserDao.create();
            User user = dao.findByUsername(username);

            if (user == null || !user.getUserPassword().equals(password)) {
                errorLabel.setText("Invalid username or password.");
                return;
            }

            errorLabel.setText("");
            if (sceneFactory != null) sceneFactory.showHome();

        } catch (Exception e) {
            errorLabel.setText("Something went wrong. Try again.");
        }
    }

    @FXML
    private void onRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }

        try {
            UserDao dao = UserDao.create();

            if (dao.findByUsername(username) != null) {
                errorLabel.setText("Username already taken.");
                return;
            }

            dao.insert(new User(username, password, "user"));
            errorLabel.setText("");
            if (sceneFactory != null) sceneFactory.showHome();

        } catch (Exception e) {
            errorLabel.setText("Something went wrong. Try again.");
        }
    }
}
