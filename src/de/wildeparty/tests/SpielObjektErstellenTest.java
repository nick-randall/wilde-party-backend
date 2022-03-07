package de.wildeparty.tests;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.User;
import de.wildeparty.snapshot.Spiel;
import de.wildeparty.snapshot.SpielStatus;

public class SpielObjektErstellenTest {

	public static void main(String[] args) {

		User userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");
		User[] users = { userEins };

		Spiel game = new Spiel(users, SpielStatus.WARTEN, null);
		System.out.println(game);

	}

}
