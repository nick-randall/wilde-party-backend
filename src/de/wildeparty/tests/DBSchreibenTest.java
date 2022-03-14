package de.wildeparty.tests;

import java.util.List;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.middletier.UserService;

public class DBSchreibenTest {
	public static void main(String[] args) {

		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService userKnecht = new UserService(quelle);

		AnonymerUser userEins = new AnonymerUser("Charlie Chatwick");
		AnonymerUser userZwei = new AnonymerUser("Bobby Brown");

		userKnecht.anlegenNeuenUser(userEins);
		AnonymerUser userEinsVonDB = (AnonymerUser) userKnecht.holenNeuenUser();
		userKnecht.anlegenNeuenUser(userZwei);
		AnonymerUser userZweiVonDB = (AnonymerUser) userKnecht.holenNeuenUser();

		System.out.println("*****");
		System.out.println(userEins.getToken());
		System.out.println(userZweiVonDB.getToken());

		System.out.println("User 1: " + userEinsVonDB);
		System.out.println("User 2: " + userZweiVonDB);

		String emailAdresse = "test@test.com";
		String passwort = "neuesPasswort";
		userKnecht.anmeldenUser(userEinsVonDB, emailAdresse, passwort);
		User angemeldeterUserEins = userKnecht.getUserById(userEinsVonDB.getUserId());
		AngemeldeterUser neuerUserAlsAngemeldet = (AngemeldeterUser) angemeldeterUserEins;
		System.out.println("------------");
		System.out.println("User 2: " + neuerUserAlsAngemeldet);

		System.out.println("---------");

		List<User> alleUser = userKnecht.getAlleUsers();
		alleUser.forEach(user -> System.out.println(user));
//		System.out.println(userKnecht.getUserById(0));
//		System.out.println(userKnecht.isIpAdresseVorhanden("127.0.0.1"));

	}
}
