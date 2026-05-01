package com.banklia;

import java.io.IOException;
import javafx.fxml.FXML;

public class WelcomePageController {

    @FXML
    private void switchToSignIn() throws IOException {
        App.setRoot("signIn");
    }
    @FXML
    private void switchToCreateAccount() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void quit() throws IOException {
        App.setRoot("secondary");
    }
}
