package de.wildeparty.tests;


import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.middletier.UserService;

public class DBSchreibenTest {
	public static void main(String[] args) {
		
		AnonymerUser userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");

		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService userKnecht = new UserService(quelle);
		
		userKnecht.anlegenNeuenAnonymenUser(userEins);
		
		userKnecht.anlegenNeuenAnonymenUser(userEins);
		
		AnonymerUser userEinsVomDB =  (AnonymerUser) userKnecht.holenNeuenUser();
		System.out.println("--------");
		System.out.println(userEinsVomDB);
		System.out.println("--------");

		
		
		//quelle.addUser(userEins);
//		quelle.deleteUser(userEins);
//		User userZwei = userKnecht.getUserById(15);
//		userZwei.setName("Horst mcFarrow");
//		
//		
//		userKnecht.updateUser(userZwei);
		
		String emailAdresse = "test@test.com";
		String passwort = "neuesPasswort";
//		AnonymerUser userFromDB =  (AnonymerUser)userKnecht.getUserById(15);
		userKnecht.machenAngemeldetenAusAnonymenUser(userEinsVomDB, emailAdresse, passwort);
//		AngemeldeterUser neuerAngemeldeterUser = (AngemeldeterUser) userKnecht.getUserById(userEinsVomDB.getUserId());
		User neuerUser = userKnecht.getUserById(userEinsVomDB.getUserId());

//		System.out.println(neuerAngemeldeterUser);
		System.out.println(neuerUser);

		System.out.println("---------");
		
		
		
		System.out.println(userKnecht.getAlleUsers());
//		System.out.println(userKnecht.getUserById(0));
//		System.out.println(userKnecht.isIpAdresseVorhanden("127.0.0.1"));

		
	}
}
