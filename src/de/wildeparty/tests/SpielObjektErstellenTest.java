package de.wildeparty.tests;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.Spiel;
import de.wildeparty.aufbau.SpielStatus;
import de.wildeparty.aufbau.backend.User;

public class SpielObjektErstellenTest {

	public static void main(String[] args) {

		User userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");
		User[] users = { userEins };

		Spiel game = new Spiel(users, SpielStatus.WARTEN, null);
		
		
		
		System.out.println(game);

	}

}
