package de.wildeparty.tests;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.middletier.UserService;

public class DecoratorTest {

	public static void main(String[] args) {
		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService userKnecht = new UserService(quelle);
		
		AnonymerUser userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");
		AngemeldeterUser neuerAngemeldeterUser = new AngemeldeterUser(userEins, "a@a.de", "hookey");
		System.out.println(neuerAngemeldeterUser);
		
		String emailAdresse = "test@test.com";
		String passwort = "neuesPasswort";
		AnonymerUser userEinsVomDB =  (AnonymerUser)userKnecht.getUserById(15);
		userKnecht.anmeldenUser(userEinsVomDB, emailAdresse, passwort);
//		AngemeldeterUser neuerAngemeldeterUser = (AngemeldeterUser) userKnecht.getUserById(userEinsVomDB.getUserId());
		User neuerUser = userKnecht.getUserById(userEinsVomDB.getUserId());
		
		System.out.println(neuerUser);
		
	}

}
