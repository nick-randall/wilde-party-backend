package de.wildeparty.tests;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.middletier.UserService;

public class TimestampUpdateTest {

	public static void main(String[] args) {

		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService userKnecht = new UserService(quelle);
		
		AnonymerUser lokalerBenutzer = new AnonymerUser("Ronny Richards", "127.4.23.2");
		userKnecht.anlegenNeuenUser(lokalerBenutzer);
		long neuerUserId = userKnecht.holenNeuenUser().getUserId();
		
		AnonymerUser benutzer = (AnonymerUser)userKnecht.getUserById(neuerUserId);
		System.out.println("Dieser " + benutzer.getClass().getSimpleName() + "User wurde zuletzt aktualisiert:" + benutzer.getSessionZuletztAktualisiert());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		userKnecht.anmeldenUser(benutzer, "Chrissy@christensen.com", "notapassword");
		AngemeldeterUser angemeldeterBenutzer = (AngemeldeterUser)userKnecht.getUserById(neuerUserId);
		System.out.println("Dieser " + angemeldeterBenutzer.getClass().getSimpleName() + "User wurde zuletzt aktualisiert:" + angemeldeterBenutzer.getSessionZuletztAktualisiert());
		
	}

}
