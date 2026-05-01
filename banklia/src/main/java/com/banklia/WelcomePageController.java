package com.banklia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WelcomePageController {
    private Stage stage;
    private HashMap<String,ArrayList<Client>> clients;
    private HashMap<String,ArrayList<Account>> accounts;
    private ArrayList<Transaction> transactions;
    @FXML
    private void switchToSignIn() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("signIn.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((SignInController)loader.getController()).setData(clients,accounts,transactions,stage);
        stage.show();
    }
    @FXML
    private void switchToCreateAccount() throws IOException {
        App.setRoot("secondary");
    }
    @FXML
    private void quit() throws IOException {
        App.setRoot("secondary");
    }
    public void setData(HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
    }
}
