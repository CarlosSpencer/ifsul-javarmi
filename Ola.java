import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Ola extends Remote {

    String digaOla() throws RemoteException;
    // subentende-se que sera "public"
}