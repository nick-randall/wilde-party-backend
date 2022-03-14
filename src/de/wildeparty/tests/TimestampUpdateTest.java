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

		AnonymerUser lokalerBenutzer = new AnonymerUser("Ronny Richards");
		userKnecht.anlegenNeuenUser(lokalerBenutzer);
		long neuerUserId = userKnecht.holenNeuenUser().getUserId();

		AnonymerUser benutzer = (AnonymerUser) userKnecht.getUserById(neuerUserId);
		System.out.println("Dieser " + benutzer.getClass().getSimpleName() + "s Token: " + benutzer.getToken()
				+ " wurde zuletzt aktualisiert:" + benutzer.getTokenZuletztAktualisiert());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		userKnecht.anmeldenUser(benutzer, "Chrissy@christensen.com", "notapassword");
		AngemeldeterUser angemeldeterBenutzer = (AngemeldeterUser) userKnecht.getUserById(neuerUserId);
		System.out
				.println("Dieser " + angemeldeterBenutzer.getClass().getSimpleName() + "s Token: " + benutzer.getToken()
						+ "wurde zuletzt aktualisiert:" + angemeldeterBenutzer.getTokenZuletztAktualisiert());

	}

}
