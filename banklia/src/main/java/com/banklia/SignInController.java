package com.banklia;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInController {
    @FXML
    private TextField clientNumField;
    @FXML
    private TextField passwordField;
    private HashMap<String,ArrayList<Client>> clients;
    private HashMap<String,ArrayList<Account>> accounts;
    private ArrayList<Transaction> transactions;
    private Stage stage;
    @FXML
    private void signIn() throws IOException {
        String clientNum=clientNumField.getText();
        String password=passwordField.getText();
        boolean signedIn=false;
        for(String clientType:clients.keySet()){
            for(Client client:clients.get(clientType)){
                if(client.getClientNum().equals(clientNum)&&client.getPassword().equals(password)){
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("mainClientPage.fxml"));
                    stage.setScene(new Scene(loader.load()));
                    ((MainClientPageController)loader.getController()).setData(client,accounts,transactions,stage);
                    stage.show();
                    System.out.println("signed in as "+client.getName());
                    signedIn=true;
                }
            }
        }
        if(!signedIn){
            System.out.println("username or password incorrect");
        }
    }
    public void setData(HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
    }
}