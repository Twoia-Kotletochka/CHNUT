
import java.rmi.*;
import java.rmi.server.*;

interface Hello extends Remote {
    String sayHello() throws RemoteException;
}
