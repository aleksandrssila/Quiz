package rmi.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.rmiinterface.Constant;

public class Server {
	
	public static void main (String[] args) throws RemoteException, AlreadyBoundException{
		
			QuizRemoteImpl rImpl = new QuizRemoteImpl();
			Registry registry = LocateRegistry.createRegistry(Constant.RMI_PORT);
			registry.bind(Constant.RMI_ID, rImpl);	
			System.out.println("---- Server started ----");
			System.out.println("");
			
	}

}
