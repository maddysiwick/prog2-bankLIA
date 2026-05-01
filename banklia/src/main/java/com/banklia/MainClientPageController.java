package com.banklia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class MainClientPageController {
    Client activeUser;
    private HashMap<String,ArrayList<Account>> accounts;
    private ArrayList<Transaction> transactions;
    private Stage stage;
    @FXML
    private Label welcomeHeader;
    public void setData(Client activeUser,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.activeUser=activeUser;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
    }
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
        welcomeHeader.setText("Welcome "+activeUser.getName()+"!");
        });  
    }
}
