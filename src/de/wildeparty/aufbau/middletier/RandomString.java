package de.wildeparty.aufbau.middletier;

import java.security.SecureRandom;

/**
 * Erstellt ein neuer String mit 24 Random-Zeichen.
 * 
 * @author nick
 *
 */

public class RandomString {

	/**
	 * Der Inhalt des Strings
	 */
	final private String inhalt;

	public RandomString() {
		SecureRandom wuerfel = new SecureRandom();
		char[] kette = new char[24];
		for (int i = 0; i < 24; i++) {
			// Die Unicode Chars 65 bis 122 sind die Groß- und Kleinbuchstaben sowie "[	\	]	^	_ `"
			char a = (char)wuerfel.nextInt(65, 122);
			kette[i] = a;
		}
		inhalt = new String(kette);
	}

	public String getInhalt() {
		return inhalt;
	}

}
