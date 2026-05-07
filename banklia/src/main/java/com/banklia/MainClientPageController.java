package com.banklia;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.HashMap;

import com.google.gson.Gson;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
public class MainClientPageController {
    Client activeUser;
    private HashMap<String,ArrayList<Client>> clients;
    private HashMap<String,ArrayList<Account>> accounts;
    private ArrayList<Transaction> transactions;
    private Stage stage;
    private ArrayList<Account> userAccounts;
    private Account selectedAcount=null;
    @FXML
    private Label welcomeHeader;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView accountsTable;
    @FXML
    private TableView transactionTable;
    @FXML
    private Button despositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button transferButton;
    @FXML
    private TextField amountInput;
    @FXML
    private TextField transferInput;
    @FXML
    private TableColumn numberColumn;
    @FXML
    private TableColumn balanceColumn;
    @FXML
    private TableColumn typeColumn;
    @FXML
    private TableColumn openedColumn;
    @FXML
    private TableColumn transactionColumn;
    @FXML
    private TableColumn amountColumn;
    @FXML
    private TableColumn transactionNumberColumn;
    @FXML
    private TableColumn dateColumn;
    public void setData(Client activeUser,HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,Stage stage){
        this.activeUser=activeUser;
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.stage=stage;
        userAccounts=new ArrayList<>();
        for(String accNum:activeUser.getAccounts()){
            for(String accountType:accounts.keySet()){
                for(Account account:accounts.get(accountType)){
                    if(account.getAccountNum().equals(accNum)){
                        userAccounts.add(account);
                        break;
                    }
                }
            }
        }
    }
    @FXML
    public void deposit(){
        Random r=new Random();
        errorLabel.setText("");
        if(selectedAcount!=null){
            try{
                double deposit=Double.parseDouble(amountInput.getText());
                double prevBalance=selectedAcount.getBalance();
                selectedAcount.deposit(deposit);
                String depositID=selectedAcount.getAccountNum()+r.nextInt(999999);
                transactions.add(new Transaction(depositID,"deposit", activeUser.getClientNum(),selectedAcount.getAccountNum(),null,prevBalance,selectedAcount.getBalance()));
                accountsTable.refresh();
                displayTransactions();
                transactionTable.refresh();
                System.out.println(selectedAcount.getBalance());
            }
            catch(NumberFormatException e){
                errorLabel.setText("please enter a decimal number in the box");
            }
        }
        else errorLabel.setText("must select an account before depositing");
    }
    @FXML
    public void withdraw(){
        Random r=new Random();
        errorLabel.setText("");
        if(selectedAcount!=null){
            try{
                double withdrawl=Double.parseDouble(amountInput.getText());
                try{
                    double prevBalance=selectedAcount.getBalance();
                    selectedAcount.withdraw(withdrawl);
                    String transactionID=selectedAcount.getAccountNum()+r.nextInt(999999);
                    transactions.add(new Transaction(transactionID,"withdrawl", activeUser.getClientNum(),selectedAcount.getAccountNum(),null,prevBalance,selectedAcount.getBalance()));
                    accountsTable.refresh();
                    displayTransactions();
                    transactionTable.refresh();
                    System.out.println(selectedAcount.getBalance());
                }catch(InsufficientFundsException e){
                    errorLabel.setText("Insufficient Funds");
                }catch(InvestmentLockException e){
                    errorLabel.setText("can't withdraw from an investment account");
                }
            }
            catch(NumberFormatException e){
                errorLabel.setText("please enter a decimal number in the box");
            }
        }
        else errorLabel.setText("must select an account before depositing");
    }
    @FXML
    public void transfer(){
        Random r=new Random();
        errorLabel.setText("");
        if(!transferInput.isVisible())transferInput.setVisible(true);
        else{
            if(selectedAcount!=null){
                boolean accountExists=false;
                Account transferAccount=null;
                for(String accountType:accounts.keySet()){
                    for(Account account:accounts.get(accountType)){
                        if(account.getAccountNum().equals(transferInput.getText())){
                            accountExists=true;
                            transferAccount=account;
                        }
                    }
                }
                if(accountExists){
                    try{
                        double transferSum=Double.parseDouble(amountInput.getText());
                        double prevBalance=selectedAcount.getBalance();
                        selectedAcount.transfer(transferSum,transferAccount);
                        String transactionID=selectedAcount.getAccountNum()+r.nextInt(999999);
                        transactions.add(new Transaction(transactionID,"transfer", activeUser.getClientNum(),selectedAcount.getAccountNum(),transferInput.getText(),prevBalance,selectedAcount.getBalance()));
                        accountsTable.refresh();
                        displayTransactions();
                        transactionTable.refresh();
                    }catch(InvestmentLockException e){
                        errorLabel.setText(e.toString());
                    }catch(InsufficientFundsException e){
                        errorLabel.setText(e.toString());
                    }catch(NumberFormatException e){
                        errorLabel.setText("please enter a decimal number in the box");
                    }
                }
                else errorLabel.setText("must select an account before depositing");
            }
        }
    }
    private void displayTransactions(){
        ArrayList<Transaction> accountTransactions=new ArrayList<>();
        for(Transaction transaction:transactions){
            if(transaction.getMainAccountNum().equals(selectedAcount.getAccountNum())){
                accountTransactions.add(transaction);
            }
            List<Transaction> accountTransactionsList=accountTransactions;
            ObservableList<Transaction> observableList = FXCollections.observableList(accountTransactionsList);
            transactionTable.setItems(observableList);
        }
    }
    @FXML
    public void signout(){
        saveData();
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("welcome.fxml"));
            stage.setScene(new Scene(loader.load()));
            ((WelcomePageController)loader.getController()).setData(clients,accounts,transactions,stage);
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    @FXML
    private void quit(){
        saveData();
        Platform.exit();
    }
    @FXML
    public void openChequingAccount(){
        System.out.println("should be adding chequing");
        Random r=new Random();
        ChequingAccount chequing=new ChequingAccount(String.valueOf(r.nextInt(999999)));
        activeUser.addAccount(chequing);
        userAccounts.add(chequing);
        accounts.get("chequings").add(chequing);
        accountsTable.refresh();
    }
    @FXML
    public void openSavingsAccount(){
        boolean hasChequing=false;
        for(Account userAccount:userAccounts){
            if(userAccount instanceof ChequingAccount){
                hasChequing=true;
            }
        }
        try{
            if(hasChequing){
                Random r=new Random();
                SavingsAccount savings=new SavingsAccount(String.valueOf(r.nextInt(999999)));
                activeUser.addAccount(savings);
                userAccounts.add(savings);
                accounts.get("savings").add(savings);
                accountsTable.refresh();
            }
            else throw new MissingChequingAccountException("must have a chequing account before opening an account of another type");
        }catch(MissingChequingAccountException e){
            errorLabel.setText(e.toString());
        }
        accountsTable.refresh();
    }
    @FXML
    public void openInvestmentAccount(){
        boolean hasChequing=false;
        for(Account userAccount:userAccounts){
            if(userAccount instanceof ChequingAccount){
                hasChequing=true;
            }
        }
        try{
            if(hasChequing){
                Random r=new Random();
                InvestmentAccount investment=new InvestmentAccount(String.valueOf(r.nextInt(999999)));
                activeUser.addAccount(investment);
                userAccounts.add(investment);
                accounts.get("investments").add(investment);
                accountsTable.refresh();
            }
            else throw new MissingChequingAccountException("must have a chequing account before opening an account of another type");
        }catch(MissingChequingAccountException e){
            errorLabel.setText(e.toString());
        }
    }
    private void saveData(){
        Gson gson=new Gson();
        HashMap<Object,String> objectFiles=new HashMap<>();
        objectFiles.put(clients.get("students"),"studentClients.json");
        objectFiles.put(clients.get("individuals"),"individualClients.json");
        objectFiles.put(clients.get("corporates"),"corporateClients.json");
        objectFiles.put(clients.get("vips"),"vipClients.json");
        objectFiles.put(accounts.get("chequings"),"chequingAccounts.json");
        objectFiles.put(accounts.get("savings"),"savingsAccounts.json");
        objectFiles.put(accounts.get("investments"),"investmentAccounts.json");
        objectFiles.put(transactions,"transactionHistory.json");
        for(Object thing:objectFiles.keySet()){
            try{
            File file=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\"+objectFiles.get(thing));
            FileWriter writer=new FileWriter(file);
            gson.toJson(thing,writer);
            writer.close();
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            welcomeHeader.setText("Welcome "+activeUser.getName()+"!");
            numberColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("accountNum"));
            balanceColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("balance"));
            openedColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("dateOpened"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<Account,String>("accountType"));
            transactionColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("type"));
            transactionNumberColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("transactionID"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("transactionDate"));
            amountColumn.setCellValueFactory(new PropertyValueFactory<Transaction,String>("difference"));
            List<Account> userAccountList=userAccounts;
            ObservableList<Account> observableList = FXCollections.observableList(userAccountList);
            accountsTable.setItems(observableList);
        });  
        accountsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    selectedAcount=(Account)accountsTable.getSelectionModel().getSelectedItem();
                    displayTransactions();
                }
            });
    }
}
