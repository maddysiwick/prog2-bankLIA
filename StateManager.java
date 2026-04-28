import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Random;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
public class StateManager {
    private int layer=1;
    private HashMap<String,ArrayList<Client>> clients=new HashMap<>();
    private HashMap<String,ArrayList<Account>> accounts=new HashMap<>();
    private ArrayList<Account> userAccounts;
    private Client activeUser=null;
    private Account openAccount=null;
    private Gson gson=new Gson();
    Scanner reader=new Scanner(System.in);

    public StateManager(){
        clients.put("students",new ArrayList<>());
        clients.put("individuals",new ArrayList<>());
        clients.put("corporates",new ArrayList<>());
        clients.put("vips",new ArrayList<>());
        accounts.put("chequings",new ArrayList<>());
        accounts.put("savings",new ArrayList<>());
        accounts.put("investments",new ArrayList<>());
        loadData();
    }

    public void run(){
        boolean running=true;
        while(running){
            switch(layer){
                case 0:
                    saveData();
                    running=false;break;
                case 1:
                    layer=layer1();break;
                case 2:
                    layer=layer2();break;
                case 3:
                    layer=layer3();break;
                case 4:
                    layer=layer4();break;
                case 5:
                    layer=layer5();break;
                default:
                    System.out.println("oops smth went wrong :(");
            }
        }
    }
    private int layer1(){
        boolean running=true;
        while(running){
            System.out.println("""
                what would you like to do?
        
                1- sign in
                2- create account
                3- quit
                """);
            System.out.print("> ");
            switch(reader.nextInt()){
                case 1:
                    boolean signedIn=false;
                    while(!signedIn){
                        System.out.println("enter your client number");
                        System.out.print("> ");
                        String clientNum=reader.next();
                        System.out.println("enter your password");
                        System.out.print("> ");
                        String password=reader.next();
                        signedIn=signInAccount(clientNum,password);
                        if(!signedIn){
                            System.out.println("client number or password incorrect, enter 1 to try again or anything else to go back");
                            System.out.print("> ");
                            if(reader.nextInt()!=1) return 1;
                        }
                    }
                    System.out.println("sucessfully signed in as "+activeUser.getName());
                    return 2;
                case 2:
                    Random r=new Random();
                    String clientNum=String.valueOf(r.nextInt(999999));
                    System.out.println("enter your name");
                    System.out.print("> ");
                    reader.nextLine();
                    String name=reader.nextLine();
                    System.out.println("enter a password");
                    System.out.print("> ");
                    String password=reader.next();
                    boolean deciding=true;
                    while(deciding){
                        System.out.println("""
                            what type of client are you?

                            1- individual 
                            2- student
                            3- corporate 
                            4- VIP
                            
                        """);
                        System.out.print("> ");
                        switch(reader.nextInt()){
                            case 1:
                                activeUser=new IndividualClient(clientNum,name,password,new ArrayList<>());
                                clients.get("individuals").add(activeUser);
                                deciding=false;
                                break;
                            case 2:
                                System.out.println("what year will you graduate?");
                                System.out.print("> ");
                                int gradYear=reader.nextInt();
                                System.out.println("what month of that year will you graduate");
                                System.out.print("> ");
                                int gradMonth=reader.nextInt();
                                activeUser=new StudentClient(clientNum,name,password,new ArrayList<>(),new GregorianCalendar(gradYear,gradMonth-1,29).getTime());
                                clients.get("students").add(activeUser);
                                deciding=false;
                                break;
                            case 3:
                                System.out.println("enter the company name");
                                System.out.print("> ");
                                reader.nextLine();
                                String comapanyName=reader.nextLine();
                                activeUser=new CorporateClient(clientNum,name,password,new ArrayList<>(),comapanyName);
                                clients.get("corporates").add(activeUser);
                                deciding=false;
                                break;
                            case 4:
                                activeUser=new VIPCLient(clientNum,name,password,new ArrayList<>());
                                clients.get("vips").add(activeUser);
                                deciding=false;
                                break;
                            default:
                                System.out.println("please enter one of the options");
                        }
                    }
                    return 2;
                case 3:
                    return 0;
                default:
                    System.out.println("please enter a valid option");
            }
        }
        System.out.println("probably never should have got here smth is up");
        return 1;
    }
    private int layer2(){
        boolean running=true;
        while(running){
            System.out.println("""
                what would you like to do?
        
                1- see personal details
                2- see accounts
                3- sign out
                4- quit
                """);
            System.out.print("> ");
            switch(reader.nextInt()){
                case 1:
                    System.out.printf("""
                            client number: #%s
                            name: %s
                            more to come but im lazy
                            """,activeUser.getClientNum(),activeUser.getName());
                    System.out.println("enter anyting to quit");
                    System.out.print("> ");
                    reader.next();
                    return 2;
                case 2:
                    return 3;
                case 3:
                    System.out.println("goodbye "+activeUser.getName());
                    activeUser=null;
                    return 1;
                case 4:
                    return 0;
                default:
                    System.out.println("please enter a valid option");
            }
        }
        System.out.println("never should get here smth went wrong");
        return 2;
    }
    private int layer3(){
        boolean running=true;
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
        while(running){
            System.out.println("""
                enter the number for what you would like to open
        
                1- open new account
                """);
                int count=1;
                for(Account account:userAccounts){
                    count++;
                    System.out.println(count+"- "+account);
                }
                System.out.println((count+1)+"- back");
            System.out.print("> ");
            int input=reader.nextInt();
            if(input==1)return 5;
            else if(input==userAccounts.size()+2) return 2;
            else if(input>userAccounts.size()+2)System.out.println("please enter a valid option");
            else{
                openAccount=userAccounts.get(input-2);
                return 4;
            }
        }
        System.out.println("shouldnt get here, smth is probably wrong");
        return 3;
    }
    private int layer4(){
        boolean running=true;
        while(running){
            System.out.println(openAccount);
            System.out.println("date opened: "+openAccount.getDateOpened());
            System.out.println("""
                what would you like to do?
        
                1- withdraw
                2- deposit
                3- transfer
                4- back
                """);
            System.out.print("> ");
            switch(reader.nextInt()){
                case 1:
                    System.out.println("enter an amount to withdraw");
                    System.out.print("> ");
                    try{
                        openAccount.withdraw(reader.nextDouble());
                    }
                    catch(InsufficientFundsException e){
                        System.out.print(e);
                    }
                    break;
                case 2:
                    System.out.println("enter an amount to deposit");
                    System.out.print("> ");
                    openAccount.deposit(reader.nextDouble());
                    break;
                case 3:
                    System.out.println("dealing with this later");
                    break;
                case 4:
                    return 3;
                default:
                    System.out.println("please enter a valid option");
            }
        }
        System.out.println("never should get here smth is wrong");
        return 4;
    }
    private int layer5(){
        boolean running=true;
        Random r=new Random();
        String accountNum=String.valueOf(r.nextInt(999999));
        Account account=null;
        boolean hasChequing=false;
        for(Account userAccount:userAccounts){
            if(userAccount instanceof ChequingAccount){
                hasChequing=true;
            }
        }
        while(running){
            System.out.println("""
                what type of account would you like to open
        
                1- chequing account
                2- savings account
                3- investment account
                4- back
                """);
            System.out.print("> ");
            switch(reader.nextInt()){
                case 1:
                    account=new ChequingAccount(accountNum,0.0, 10.0);
                    accounts.get("chequings").add(account);
                    running=false;
                    break;
                case 2:
                    if(hasChequing){
                        account=new SavingsAccount(accountNum,0.0,10.0);
                        accounts.get("savings").add(account);
                        running=false;
                    }
                    else System.out.println("sorry, you must make a chequing account before opening another type");
                    break;
                case 3:
                    if(hasChequing){
                        account=new InvestmentAccount(accountNum, 0.0, 10.0);
                        accounts.get("investments").add(account);
                        running=false;
                    }
                    else System.out.println("sorry, you must make a chequing account before opening another type");
                    break;
                case 4:
                    return 3;
                default:
                    System.out.println("please enter a valid option");
            }
        }
        //need to be able to tell when add account fails
        activeUser.addAccount(account.getAccountNum());
        openAccount=account;
        return 4;
    }
    private boolean signInAccount(String clientNum,String password){
        for(String clientType:clients.keySet()){
            for(Client client:clients.get(clientType)){
                System.out.println(client.getName());
                if(client.getClientNum().equals(clientNum)&&client.getPassword().equals(password)){
                    activeUser=client;
                    return true;
            }
        }
        }
        return false;
    }
    private void loadData(){
        //TODO OH MY GOD REFACTOR THIS IS FUCKING DISGUSTING
        try{
            File studentFile=new File("jsonFiles/studentClients.json");
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
            File individualFile=new File("jsonFiles/individualClients.json");
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
            File corporateFile=new File("jsonFiles/corporateClients.json");
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
            File vipFile=new File("jsonFiles/vipClients.json");
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
            File chequingFile=new File("jsonFiles/chequingAccounts.json");
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
            File investmentFile=new File("jsonFiles/investmentAccounts.json");
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
            File savingsFile=new File("jsonFiles/savingsAccounts.json");
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
    }
    private void saveData(){
        //TODO FIX THIS ITS EVEN LESS DEFENSIBLE USE A LOOP OMFG
        try{
            File studentFile=new File("jsonFiles/studentClients.json");
            FileWriter studentWriter=new FileWriter(studentFile);
            gson.toJson(clients.get("students"),studentWriter);
            studentWriter.close();
        }catch(Exception e){
            System.out.println("can't write student clients: "+e);
        }
        try{
            File individualFile=new File("jsonFiles/individualClients.json");
            FileWriter individualWriter=new FileWriter(individualFile);
            gson.toJson(clients.get("individuals"),individualWriter);
            individualWriter.close();
        }catch(Exception e){
            System.out.println("can't write individual clients: "+e);
        }
        try{
            File corporateFile=new File("jsonFiles/corporateClients.json");
            FileWriter corporateWriter=new FileWriter(corporateFile);
            gson.toJson(clients.get("corporates"),corporateWriter);
            corporateWriter.close();
        }catch(Exception e){
            System.out.println("can't write corporate clients: "+e);
        }
        try{
            File vipFile=new File("jsonFiles/vipClients.json");
            FileWriter vipWriter=new FileWriter(vipFile);
            gson.toJson(clients.get("vips"),vipWriter);
            vipWriter.close();
        }catch(Exception e){
            System.out.println("can't write vip clients: "+e);
        }
        try{
            File chequingFile=new File("jsonFiles/chequingAccounts.json");
            FileWriter chequingWriter=new FileWriter(chequingFile);
            gson.toJson(accounts.get("chequings"),chequingWriter);
            chequingWriter.close();
        }catch(Exception e){
            System.out.println("can't write chequing accounts: "+e);
        }
        try{
            File investmentFile=new File("jsonFiles/investmentAccounts.json");
            FileWriter investmentWriter=new FileWriter(investmentFile);
            gson.toJson(accounts.get("investments"),investmentWriter);
            investmentWriter.close();
        }catch(Exception e){
            System.out.println("can't write investment accounts: "+e);
        }
        try{
            File savingsFile=new File("jsonFiles/savingsAccounts.json");
            FileWriter savingsWriter=new FileWriter(savingsFile);
            gson.toJson(accounts.get("savings"),savingsWriter);
            savingsWriter.close();
        }catch(Exception e){
            System.out.println("can't write savings accounts: "+e);
        }
    }

    public int getLayer() {
        return layer;
    }
    public void setLayer(int layer) {
        this.layer = layer;
    }
    //TODO fix these
    /*public ArrayList<Client> getClients() {
        return clients;
    }
    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }*/
}
