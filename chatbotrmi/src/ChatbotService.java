import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatbotService extends Remote {
    String getResponse(String question) throws RemoteException;
}
