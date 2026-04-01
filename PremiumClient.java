import java.util.ArrayList;

abstract public class PremiumClient extends Client{

    public PremiumClient(String clientNum, String name, String password, ArrayList<String> accounts) {
        super(clientNum, name, password, accounts);
    }
}
