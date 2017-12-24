package runner;

import rmi.RemoteHelper;
import ui.Main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClientRunner {
    private RemoteHelper remoteHelper;

    public ClientRunner() {
        linkToServer();
        ;
    }


    private void linkToServer() {
        try {
            remoteHelper = RemoteHelper.getInstance();
            remoteHelper.setRemote(Naming.lookup("rmi://127.0.0.1:8887/DataRemoteObject"));
            System.out.println("linked");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args){
        ClientRunner cr = new ClientRunner();
       Main.main(args);
    }
}
