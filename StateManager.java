import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.Random;
public class StateManager {
    private int layer=1;
    private ArrayList<Client> clients=new ArrayList<>();
    private ArrayList<Account> accounts=new ArrayList<>();
    private Client activeUser=null;
    private Account openAccount=null;
    Scanner reader=new Scanner(System.in);

    public void run(){
        boolean running=true;
        while(running){
            switch(layer){
                case 0:
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
                                deciding=false;
                                break;
                            case 3:
                                System.out.println("enter the company name");
                                System.out.print("> ");
                                reader.nextLine();
                                String comapanyName=reader.nextLine();
                                activeUser=new CorporateClient(clientNum,name,password,new ArrayList<>(),comapanyName);
                                deciding=false;
                                break;
                            case 4:
                                activeUser=new VIPCLient(clientNum,name,password,new ArrayList<>());
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
        ArrayList<Account> userAccounts=new ArrayList<>();
        for(String accNum:activeUser.getAccounts()){
            for(Account account:accounts){
                if(account.getAccountNum().equals(accNum)){
                    userAccounts.add(account);
                    break;
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
            System.out.print("> ");
            int input=reader.nextInt();
            if(input==1)return 5;
            else if(input>userAccounts.size()+1)System.out.println("please enter a valid option");
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
                    running=false;
                    break;
                case 2:
                    account=new SavingsAccount(accountNum,0.0,10.0);
                    running=false;
                    break;
                case 3:
                    account=new InvestmentAccount(accountNum, 0.0, 10.0);
                    running=false;
                    break;
                case 4:
                    return 3;
                default:
                    System.out.println("please enter a valid option");
            }
        }
        //need to be able to tell when add account fails
        activeUser.addAccount(account.getAccountNum());
        accounts.add(account);
        openAccount=account;
        return 4;
    }
    private boolean signInAccount(String clientNum,String password){
        for(Client client:clients){
            if(client.getClientNum().equals(clientNum)&&client.getPassword().equals(password)){
                activeUser=client;
                return true;
            }
        }
        return false;
    }

    public int getLayer() {
        return layer;
    }
    public void setLayer(int layer) {
        this.layer = layer;
    }
    public ArrayList<Client> getClients() {
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
    }
}
