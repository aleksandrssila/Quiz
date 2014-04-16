package rmi.rmiserver;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.rmiinterface.Constant;

public class RmiServer {
	
	public static void main (String[] args) throws RemoteException, AlreadyBoundException{
		
			RemoteServerImpl rImpl = new RemoteServerImpl();
			Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
			registry.bind(Constant.RMI_ID, rImpl);	
			System.out.println("---- Server started ----");
			System.out.println("");
			
	}

}
