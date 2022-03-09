package de.wildeparty.tests;


import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.middletier.UserService;

public class DBSchreibenTest {
	public static void main(String[] args) {
		
		User userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");
		User[] users = { userEins };

		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService userKnecht = new UserService(quelle);
		
		quelle.addUser(userEins);
//		quelle.deleteUser(userEins);
		
		
		System.out.println(userKnecht.getAlleUsers());
//		System.out.println(userKnecht.getUserById(0));
//		System.out.println(userKnecht.isIpAdresseVorhanden("127.0.0.1"));

		
	}
}
