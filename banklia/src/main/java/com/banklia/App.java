package com.banklia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.Date;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static HashMap<String,ArrayList<Account>> accounts=new HashMap<>();
    private static HashMap<String,ArrayList<Client>> clients=new HashMap<>();
    private static ArrayList<Transaction> transactions;
    private static LoadInfo sessionInfo;
    private static Gson gson=new Gson();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("welcome.fxml"));
        stage.setScene(new Scene(loader.load()));
        ((WelcomePageController)loader.getController()).setData(clients,accounts,transactions,stage);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    private static void loadData(){
        clients.put("students",new ArrayList<>());
        clients.put("individuals",new ArrayList<>());
        clients.put("corporates",new ArrayList<>());
        clients.put("vips",new ArrayList<>());
        accounts.put("chequings",new ArrayList<>());
        accounts.put("savings",new ArrayList<>());
        accounts.put("investments",new ArrayList<>());
        try{
            File studentFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\studentClients.json");
            FileReader studentReader=new FileReader(studentFile);
            Type studentType=new TypeToken<ArrayList<StudentClient>>() {}.getType();
            ArrayList<StudentClient> studentClients=gson.fromJson(studentReader,studentType);
            studentReader.close();
            for(StudentClient student:studentClients){
                clients.get("students").add(student);
            }
        }catch(Exception e){
            System.out.println("can't read student clients: "+e);
        }
        try{
            File individualFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\individualClients.json");
            FileReader individualReader=new FileReader(individualFile);
            Type individualType=new TypeToken<ArrayList<IndividualClient>>() {}.getType();
            ArrayList<IndividualClient> individualClients=gson.fromJson(individualReader,individualType);
            individualReader.close();
            for(IndividualClient individual:individualClients){
                clients.get("individuals").add(individual);
            }
        }catch(Exception e){
            System.out.println("can't read individual clients: "+e);
        }
        try{
            File corporateFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\corporateClients.json");
            FileReader corporateReader=new FileReader(corporateFile);
            Type corporateType=new TypeToken<ArrayList<CorporateClient>>() {}.getType();
            ArrayList<CorporateClient> corporateClients=gson.fromJson(corporateReader,corporateType);
            corporateReader.close();
            for(CorporateClient corporate:corporateClients){
                clients.get("corporates").add(corporate);
            }
        }catch(Exception e){
            System.out.println("can't read corporate clients: "+e);
        }
        try{
            File vipFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\vipClients.json");
            FileReader vipReader=new FileReader(vipFile);
            Type vipType=new TypeToken<ArrayList<VIPCLient>>() {}.getType();
            ArrayList<VIPCLient> vipClients=gson.fromJson(vipReader,vipType);
            vipReader.close();
            for(VIPCLient vip:vipClients){
                clients.get("vips").add(vip);
            }
        }catch(Exception e){
            System.out.println("can't read vip clients: "+e);
        }
        try{
            File chequingFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\chequingAccounts.json");
            FileReader chequingReader=new FileReader(chequingFile);
            Type chequingType=new TypeToken<ArrayList<ChequingAccount>>() {}.getType();
            ArrayList<ChequingAccount> chequingAccounts=gson.fromJson(chequingReader,chequingType);
            chequingReader.close();
            for(ChequingAccount chequing:chequingAccounts){
                accounts.get("chequings").add(chequing);
            }
        }catch(Exception e){
            System.out.println("can't read chequing accounts: "+e);
        }
        try{
            File investmentFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\investmentAccounts.json");
            FileReader investmentReader=new FileReader(investmentFile);
            Type investmentType=new TypeToken<ArrayList<InvestmentAccount>>() {}.getType();
            ArrayList<InvestmentAccount> investmentAccounts=gson.fromJson(investmentReader,investmentType);
            investmentReader.close();
            for(InvestmentAccount investment:investmentAccounts){
                accounts.get("investments").add(investment);
            }
        }catch(Exception e){
            System.out.println("can't read investment accounts: "+e);
        }
        try{
            File savingsFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\savingsAccounts.json");
            FileReader savingsReader=new FileReader(savingsFile);
            Type savingsType=new TypeToken<ArrayList<SavingsAccount>>() {}.getType();
            ArrayList<SavingsAccount> savingsAccounts=gson.fromJson(savingsReader,savingsType);
            savingsReader.close();
            for(SavingsAccount savings:savingsAccounts){
                accounts.get("savings").add(savings);
            }
        }catch(Exception e){
            System.out.println("can't read savings accounts: "+e);
        }
        //exclude from refactoring when i do it
        try{
            File transactionsFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\transactionHistory.json");
            FileReader transactionsReader=new FileReader(transactionsFile);
            Type transactionsType=new TypeToken<ArrayList<Transaction>>() {}.getType();
            transactions=gson.fromJson(transactionsReader,transactionsType);
            transactionsReader.close();
        }catch(IOException e){
            System.out.println("can't read trasnsaction history: "+e);
        }
        try{
            File infoFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\openData.json");
            FileReader infoReader=new FileReader(infoFile);
            sessionInfo=gson.fromJson(infoReader,LoadInfo.class);
            infoReader.close();
            if(sessionInfo==null){
                sessionInfo=new LoadInfo(new Date(), null);
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    private static void updateAccounts(int monthsToCatchUp){
        Random r=new Random();
        if(monthsToCatchUp>0){
            sessionInfo=new LoadInfo(new Date(), null);
        }
        for (int i = 0; i < monthsToCatchUp; i++) {
            for (Account account : accounts.get("chequings")) {
                try{
                    ((ChequingAccount) account).applyMonthlyFee();
                    //transactions.add(new Transaction(account.getAccountNum()+String.valueOf(r.nextInt(999999)), "monthly fee", , STYLESHEET_MODENA, STYLESHEET_CASPIAN, monthsToCatchUp, i))
                }catch(InsufficientFundsException e){
                    System.out.println(e);
                }
            }
            for (Account account : accounts.get("savings")) {
                SavingsAccount savings = (SavingsAccount) account;
                try{
                    savings.applyMonthlyFee();
                    savings.applyInterest();
                }catch(InsufficientFundsException e){
                    System.out.println(e);
                }
            }
            for (Account account : accounts.get("investments")) {
                InvestmentAccount inv = (InvestmentAccount) account;
                try{
                    inv.applyMonthlyFee();
                    inv.applyInterest();
                }catch(InsufficientFundsException e){
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        loadData();
        int monthsSinceUpdate=(int)((new Date().getTime()-sessionInfo.getLastUpdate().getTime())/2629746000L);
        updateAccounts(monthsSinceUpdate);
        try{
            File infoFile=new File("src\\main\\resources\\com\\banklia\\jsonFiles\\openData.json");
            FileWriter infoWriter=new FileWriter(infoFile);
            gson.toJson(sessionInfo,infoWriter);
            infoWriter.close();
            }catch(IOException e){
                System.out.println(e);
            }
        launch();
    }

}