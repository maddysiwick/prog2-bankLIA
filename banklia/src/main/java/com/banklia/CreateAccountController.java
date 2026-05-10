package com.banklia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * controller for the account creation pages, including the more specific student and corporate pages
 */
public class CreateAccountController {
    /**
     * 
     */
    private Stage stage;
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
    /**
     * the name entered on the create account page, saved if it is now on the corporate or student account creation page
     */
    private String name;
    /**
     * the password entered on the create account page, saved if it is now on the corporate or student account creation page
     */
    private String password;
    /**
     * important data saved from previous sessions
     */
    private LoadInfo sessionData;
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
    /**
     * directs the user to the next step of the sign in process or creates and signs in 
     * the client object if it has enough information
     */
    @FXML
    public void determineNext(){
        name=nameField.getText();
        password=passwordField.getText();
        try{
            switch(clientType.getValue()){
            case "Individual":
                IndividualClient individual=new IndividualClient(sessionData.nextClientNum(), name, password,new ArrayList<>());
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
                VIPCLient vip=new VIPCLient(sessionData.nextClientNum(), name, password,new ArrayList<>());
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
    /**
     * signs in a client and directs to the main page of the app
     * @param client
     * @throws IOException
     */
    private void signIn(Client client) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("mainClientPage.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((MainClientPageController)loader.getController()).setData(client,clients,accounts,transactions,sessionData,stage);
        stage.show();
    }
    /**
     * changes the page to the last step of the student sign in process
     * @throws IOException
     */
    private void choseStudentClient() throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("createStudentAccount.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((CreateAccountController)loader.getController()).setData(name,password,clients,accounts,transactions,sessionData,stage);
        stage.show();
    }
    /**
     * changes the page to the last step of the corporate sign in process
     * @throws IOException
     */
    private void choseCorporateClient()throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("createCorporateAccount.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((CreateAccountController)loader.getController()).setData(name,password,clients,accounts,transactions,sessionData,stage);
        stage.show();
    }
    /**
     * creates the student client object and signs it in
     * @throws IOException
     */
    @FXML
    public void finishStudentAccount()throws IOException{
        int year=yearBox.getValue();
        int month=monthBox.getValue();
        StudentClient student=new StudentClient(sessionData.nextClientNum(),name,password,new ArrayList<>(),new GregorianCalendar(year,month-1,29).getTime());
        clients.get("students").add(student);
        signIn(student);
    }
    /**
     * creates the corporate client object and signs it in
     * @throws IOException
     */
    @FXML
    public void finishCorporateAccount() throws IOException{
        String corpName=corporationField.getText();
        CorporateClient corporate=new CorporateClient(sessionData.nextClientNum(),name,password, new ArrayList<>(),corpName);
        clients.get("corporates").add(corporate);
        signIn(corporate);
    }
    /**
     * takes data transmitted from the welcome page
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
    /**
     * takes data transmitted from the first create account page, if this instance of CreateAcount controller is the
     * second step for the student or corporate client accounts
     * @param name
     * @param password
     * @param clients
     * @param accounts
     * @param transactions
     * @param sessionData
     * @param stage
     */
    public void setData(String name,String password,HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,LoadInfo sessionData,Stage stage){
        this.name=name;
        this.password=password;
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.sessionData=sessionData;
        this.stage=stage;
    }
}
