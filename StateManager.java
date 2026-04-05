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
                    System.out.println("this page is under construction,come back later "+activeUser.getName());break;
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
