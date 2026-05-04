package com.vila.controller;

import com.vila.SceneFactory;
import com.vila.dao.UserDao;
import com.vila.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    private SceneFactory sceneFactory;

    public void setSceneFactory(SceneFactory sceneFactory) {
        this.sceneFactory = sceneFactory;
    }

    @FXML
    private void onRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirm  = confirmPasswordField.getText();

        // Validation
        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            errorLabel.setText("Please fill in all fields.");
            return;
        }
        if (username.length() < 3) {
            errorLabel.setText("Username must be at least 3 characters.");
            return;
        }
        if (password.length() < 6) {
            errorLabel.setText("Password must be at least 6 characters.");
            return;
        }
        if (!password.equals(confirm)) {
            errorLabel.setText("Passwords do not match.");
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

    @FXML
    private void onBackToLogin() throws Exception {
        if (sceneFactory != null) sceneFactory.showLogin();
    }
}