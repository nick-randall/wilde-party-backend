package de.wildeparty.tests;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;

public class DecoratorTest {

	public static void main(String[] args) {
		AnonymerUser userEins = new AnonymerUser("Charlie Chatwick", "127.0.0.1");
		AngemeldeterUser neuerAngemeldeterUser = new AngemeldeterUser(userEins, "a@a.de", "hookey");
		System.out.println(neuerAngemeldeterUser);
		
		
	}

}
