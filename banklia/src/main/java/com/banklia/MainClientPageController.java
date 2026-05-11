package com.banklia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
public class MainClientPageController {
    /**
     * client currently signed in
     */
    Client activeUser;
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
     * accounts related to the currently signed in user
     */
    private ArrayList<Account> userAccounts;
    /**
     * the account currently selected by the user from their account list
     */
    private Account selectedAcount=null;
    /**
     * important information saved from other sessions
     */
    private LoadInfo sessionData;
    /**
     * the ratio of the chosen currency to CAD
     */
    private String currency="CAD";
    @FXML
    private Label welcomeHeader;
    @FXML
    private Label errorLabel;
    @FXML
    private TableView<Account> accountsTable;
    @FXML
    private TableView<Transaction> transactionTable;
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
    private TableColumn<Account,String> numberColumn;
    @FXML
    private TableColumn<Account,String> balanceColumn;
    @FXML
    private TableColumn<Account,String> typeColumn;
    @FXML
    private TableColumn<Account,String> openedColumn;
    @FXML
    private TableColumn<Transaction,String> transactionColumn;
    @FXML
    private TableColumn<Transaction,String> amountColumn;
    @FXML
    private TableColumn<Transaction,String> transactionNumberColumn;
    @FXML
    private TableColumn<Transaction,String> dateColumn;
    @FXML
    private ComboBox<String> currencyChoice;
    @FXML
    private Label clientTypeLabel;
    /**
     * data recieved from the previous page
     * @param activeUser
     * @param clients
     * @param accounts
     * @param transactions
     * @param sessionData
     * @param stage
     */
    public void setData(Client activeUser,HashMap<String,ArrayList<Client>> clients,HashMap<String,ArrayList<Account>> accounts,ArrayList<Transaction> transactions,LoadInfo sessionData,Stage stage){
        this.activeUser=activeUser;
        this.clients=clients;
        this.accounts=accounts;
        this.transactions=transactions;
        this.sessionData=sessionData;
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
    /**
     * handles the user clicking the deposit button
     */
    @FXML
    public void deposit(){
        transferInput.setVisible(false);
        errorLabel.setText("");
        if(selectedAcount!=null){
            try{
                double deposit=Double.parseDouble(amountInput.getText());
                double prevBalance=selectedAcount.getBalance();
                if(!currency.equals("CAD")&&currency!=null) selectedAcount.deposit(deposit,currency);
                else selectedAcount.deposit(deposit);
                transactions.add(new Transaction(sessionData.nextTransationNum(),"deposit", activeUser.getClientNum(),selectedAcount.getAccountNum(),null,prevBalance,selectedAcount.getBalance()));
                accountsTable.refresh();
                displayTransactions();
                transactionTable.refresh();
            }
            catch(NumberFormatException e){
                errorLabel.setText("please enter a decimal number in the box");
            }catch(NegativeMoneyException e){
                errorLabel.setText("amount entered must be positive");
            }
        }
        else errorLabel.setText("must select an account before depositing");
    }
    /**
     * handles the user clicking the withdraw button
     */
    @FXML
    public void withdraw(){
        transferInput.setVisible(false);
        errorLabel.setText("");
        if(selectedAcount!=null){
            try{
                double withdrawl=Double.parseDouble(amountInput.getText());
                try{
                    double prevBalance=selectedAcount.getBalance();
                    selectedAcount.withdraw(withdrawl);
                    transactions.add(new Transaction(sessionData.nextTransationNum(),"withdrawl", activeUser.getClientNum(),selectedAcount.getAccountNum(),null,prevBalance,selectedAcount.getBalance()));
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
            }catch(NegativeMoneyException e){
                errorLabel.setText("amount entered must be positive");
            }
        }
        else errorLabel.setText("must select an account before depositing");
    }
    /**
     * handles the user hitting the transfer button
     */
    @FXML
    public void transfer(){
        errorLabel.setText("");
        if(!transferInput.isVisible())transferInput.setVisible(true);
        else{
            if(selectedAcount!=null){
                boolean accountExists=false;
                Account transferAccount=null;
                double formerOtherbalance=0.0;
                for(String accountType:accounts.keySet()){
                    for(Account account:accounts.get(accountType)){
                        if(account.getAccountNum().equals(transferInput.getText())){
                            accountExists=true;
                            transferAccount=account;
                            formerOtherbalance=account.getBalance();
                            break;
                        }
                    }
                }
                if(accountExists){
                    try{
                        double transferSum=Double.parseDouble(amountInput.getText());
                        double prevBalance=selectedAcount.getBalance();
                        selectedAcount.transfer(transferSum,transferAccount);
                        transactions.add(new Transaction(sessionData.nextTransationNum(),"transfer", activeUser.getClientNum(),selectedAcount.getAccountNum(),transferInput.getText(),prevBalance,selectedAcount.getBalance()));
                        transactions.add(new Transaction(sessionData.nextTransationNum(),"transfer", activeUser.getClientNum(),transferInput.getText(),selectedAcount.getAccountNum(),formerOtherbalance,transferAccount.getBalance()));
                        accountsTable.refresh();
                        displayTransactions();
                        transactionTable.refresh();
                        transferInput.setVisible(false);
                        errorLabel.setText("");
                    }catch(InvestmentLockException e){
                        errorLabel.setText(e.toString());
                    }catch(InsufficientFundsException e){
                        errorLabel.setText(e.toString());
                    }catch(NumberFormatException e){
                        errorLabel.setText("please enter a decimal number in the box");
                    }catch(NegativeMoneyException e){
                        errorLabel.setText("amount entered must be positive");
                    }
                }
                else errorLabel.setText("must select an account before depositing");
            }
        }
    }
    /**
     * finds and displays the necessary transactions based on the selected account
     */
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
    /**
     * handles the user hitting the signout button
     */
    @FXML
    public void signout(){
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("welcome.fxml"));
            stage.setScene(new Scene(loader.load()));
            ((WelcomePageController)loader.getController()).setData(clients,accounts,transactions,sessionData,stage);
            stage.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    /**
     * handles the user clicking the open chequing account option in the account menu
     */
    @FXML
    public void openChequingAccount(){
        try{
            ChequingAccount chequing=new ChequingAccount(sessionData.nextAccountNum());
            activeUser.addAccount(chequing);
            userAccounts.add(chequing);
            accounts.get("chequings").add(chequing);
            accountsTable.refresh();
        }catch(MissingChequingAccountException e){
            System.out.println("something is wrong should never be thrown in this case");
        }
    }
    /**
     * handles the user clicking the open savings account option in the account menu
     */
    @FXML
    public void openSavingsAccount(){
        try{
            SavingsAccount savings=new SavingsAccount(sessionData.nextAccountNum());
            activeUser.addAccount(savings);
            userAccounts.add(savings);
            accounts.get("savings").add(savings);
            accountsTable.refresh();
        }catch(MissingChequingAccountException e){
            errorLabel.setText(e.toString());
        }
        accountsTable.refresh();
    }
    /**
     * handles the user clicking the open savings account option in the account menu
     */
    @FXML
    public void openInvestmentAccount(){
        try{
            InvestmentAccount investment=new InvestmentAccount(sessionData.nextAccountNum());
            activeUser.addAccount(investment);
            userAccounts.add(investment);
            accounts.get("investments").add(investment);
            accountsTable.refresh();
        }catch(MissingChequingAccountException e){
            errorLabel.setText(e.toString());
        }
    }
    @FXML
    public void changeCurrency(){
        currency=currencyChoice.getValue();
    }
    /**
     * initializes the values of the GUI elements
     */
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
            if(activeUser instanceof StudentClient) clientTypeLabel.setText("Student Client, Graduation Date: "+((StudentClient)activeUser).getStatusExpiryDate());
            else if(activeUser instanceof CorporateClient) clientTypeLabel.setText("Corporate Client, Company: "+((CorporateClient)activeUser).getCompanyName());
            else if(activeUser instanceof VIPCLient) clientTypeLabel.setText("VIP Client");
            else clientTypeLabel.setText("Individual Client");
        });  
        accountsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    selectedAcount=(Account)accountsTable.getSelectionModel().getSelectedItem();
                    displayTransactions();
                }
            });
    }
}
