package com.banklia;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * controls the sign in page of the app
 */
public class SignInController {
    @FXML
    private TextField clientNumField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label errorLabel;
    /**
     * all clients sorted by client type
     */
    private HashMap<String,ArrayList<Client>> clients;
    /**
     * all accounts sorted by account type
     */
    private HashMap<String,ArrayList<Account>> accounts;
    /**
     * all transactions
     */
    private ArrayList<Transaction> transactions;
    private Stage stage;
    /**
     * important information saved from other sessions
     */
    private LoadInfo sessionData;
    /**
     * handles user clicking the sign in button. if username and password are correct, it then directs to the
     * main page
     * @throws IOException
     */
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
                    ((MainClientPageController)loader.getController()).setData(client,clients,accounts,transactions,sessionData,stage);
                    stage.show();
                    signedIn=true;
                }
            }
        }
        if(!signedIn){
            errorLabel.setText("Username or password incorrect");
        }
    }
    /**
     * recieves important data from the welcome page
     * @param clients
     * @param accounts
     * @param transactions
     * @param sessionData
     * @param stage
     */
    public void setData(HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,LoadInfo sessionData,Stage stage){
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.sessionData=sessionData;
        this.stage=stage;
    }
}