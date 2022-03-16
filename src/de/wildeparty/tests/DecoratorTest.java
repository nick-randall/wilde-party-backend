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
		
		AnonymerUser userEins = new AnonymerUser("Charlie Chatwick");
		userKnecht.anlegenNeuenUser(userEins);
		AnonymerUser userEinsVomDB =  (AnonymerUser) userKnecht.getNeuenUser();
		System.out.println("User " +  userEinsVomDB.getName() + " ist in der Datenquelle als " + userEinsVomDB.getClass().getSimpleName() + ".");
		System.out.println("User id ist " + userEinsVomDB.getUserId());
		
		String emailAdresse = "test@test.com";
		String passwort = "neuesPasswort";
		userKnecht.anmeldenUser(userEinsVomDB, emailAdresse, passwort);
		AngemeldeterUser angemeldeterUserEinsVomDB =  (AngemeldeterUser) userKnecht.getNeuenUser();
		System.out.println("User " + angemeldeterUserEinsVomDB.getName() + " ---angemeldet unter " + angemeldeterUserEinsVomDB.getEmailAdresse() + "--- ist in der Datenquelle.");
		System.out.println("User id ist " + angemeldeterUserEinsVomDB.getUserId());
	}

}
