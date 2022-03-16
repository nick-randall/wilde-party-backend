package de.wildeparty.aufbau.middletier;

import java.util.function.Predicate;

import de.wildeparty.aufbau.backend.User;

public class AuswahlKriteriums {

	public static final Predicate<User> hasToken(String token) {
		return benutzer -> benutzer.getToken().equals(token);
	}

}
