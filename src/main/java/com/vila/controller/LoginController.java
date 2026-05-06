package com.vila.controller;

import com.vila.SceneFactory;
import com.vila.dao.UserDao;
import com.vila.entity.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Navigable {

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
        String password = passwordField.getText().trim();

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

            if (sceneFactory != null) {
                if (user.getUserRole().equalsIgnoreCase("admin")) { //detects admin account
                    sceneFactory.showAdmin();
                } else {
                    sceneFactory.showHome();
                }
            } else {
                errorLabel.setText("Scene has not been initialized");
            }

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Something went wrong. Try again.");
        }
    }

    // Now just navigates to the register scene instead of handling it inline
    @FXML
    private void onRegister() throws Exception {
        if (sceneFactory != null) sceneFactory.showRegister();
    }
}