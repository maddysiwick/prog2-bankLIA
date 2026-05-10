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
    private LoadInfo sessionData;
    @FXML
    private void switchToSignIn() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("signIn.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((SignInController)loader.getController()).setData(clients,accounts,transactions,sessionData,stage);
        stage.show();
    }
    @FXML
    private void switchToCreateAccount() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("createAccount.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((CreateAccountController)loader.getController()).setData(clients,accounts,transactions,sessionData,stage);
        stage.show();
    }
    @FXML
    private void quit() throws IOException {
        App.setRoot("secondary");
    }
    public void setData(HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,LoadInfo sessionData,Stage stage){
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.sessionData=sessionData;
        this.stage=stage;
    }
}
