package com.banklia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAccountController {
    private Stage stage;
    private HashMap<String,ArrayList<Client>> clients;
    private HashMap<String,ArrayList<Account>> accounts;
    private ArrayList<Transaction> transactions;
    private String name;
    private String password;
    private String clientNum;
    @FXML
    private TextField nameField;
    @FXML
    private TextField corporationField;
    @FXML
    private TextField passwordField;
    @FXML
    private ComboBox<String> clientType;
    @FXML
    private ComboBox<Integer> yearBox;
    @FXML
    private ComboBox<Integer> monthBox;
    @FXML
    public void determineNext(){
        Random r=new Random();
        clientNum=String.valueOf(r.nextInt(999999));
        name=nameField.getText();
        password=passwordField.getText();
        try{
            switch(clientType.getValue()){
            case "Individual":
                IndividualClient individual=new IndividualClient(clientNum, name, password,new ArrayList<>());
                clients.get("individuals").add(individual);
                signIn(individual);
                break;
            case "Student":
                choseStudentClient();
                break;
            case "Corporate":
                choseCorporateClient();
                break;
            case "VIP":
                VIPCLient vip=new VIPCLient(clientNum, name, password,new ArrayList<>());
                clients.get("vips").add(vip);
                signIn(vip);
                break;
            default:
                System.out.println("must enter a client type");
        }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    private void signIn(Client client) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("mainClientPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((MainClientPageController)loader.getController()).setData(client,clients,accounts,transactions,stage);
        stage.show();
    }
    private void choseStudentClient() throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("createStudentAccount.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((CreateAccountController)loader.getController()).setData(name,password,clients,accounts,transactions,stage);
        stage.show();
    }
    private void choseCorporateClient()throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("createCorporateAccount.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((CreateAccountController)loader.getController()).setData(name,password,clients,accounts,transactions,stage);
        stage.show();
    }
    @FXML
    public void finishStudentAccount()throws IOException{
        Random r=new Random();
        clientNum=String.valueOf(r.nextInt(999999));
        int year=yearBox.getValue();
        int month=monthBox.getValue();
        StudentClient student=new StudentClient(clientNum,name,password,new ArrayList<>(),new GregorianCalendar(year,month-1,29).getTime());
        clients.get("students").add(student);
        signIn(student);
    }
    @FXML
    public void finishCorporateAccount() throws IOException{
        Random r=new Random();
        clientNum=String.valueOf(r.nextInt(999999));
        String corpName=corporationField.getText();
        CorporateClient corporate=new CorporateClient(clientNum,name,password, new ArrayList<>(),corpName);
        clients.get("corporates").add(corporate);
        signIn(corporate);
    }
    public void setData(HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
    }
    public void setData(String name,String password,HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.name=name;
        this.password=password;
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
    }
}
