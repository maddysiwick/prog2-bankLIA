import java.util.ArrayList;
abstract public class Client{
    protected String clientNum;
    protected String name;
    protected String password;
    protected ArrayList<String> accounts;

    public Client(String clientNum, String name, String password, ArrayList<String> accounts) {
        this.clientNum = clientNum;
        this.name = name;
        this.password = password;
        this.accounts = accounts;
    }

    public void addAccount(String accountNum){
        
    }

    public String getClientNum() {
        return clientNum;
    }
    public void setClientNum(String clientNum) {
        this.clientNum = clientNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<String> getAccounts() {
        return accounts;
    }
    public void setAccounts(ArrayList<String> accounts) {
        this.accounts = accounts;
    }
}