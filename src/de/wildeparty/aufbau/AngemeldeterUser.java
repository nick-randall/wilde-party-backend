package de.wildeparty.aufbau;

import java.util.regex.Pattern;

import de.wildeparty.aufbau.backend.User;

public class AngemeldeterUser extends AnonymerUserDecorator {

	private User user;

	private String emailAdresse;
	
	private String passwort;
	
	/**
	 * erstellt ein AngemeldeterUser aus einem AnonymerUser
	 * @param anonymerUser
	 * @param emailAdresse
	 */

	public AngemeldeterUser(User anonymerUser, String emailAdresse, String passwort) {
		super(anonymerUser);
		boolean isValidEmailAdresse = Pattern.matches("^\s+@\s+$", emailAdresse);
		if (!isValidEmailAdresse) {
			throw new IllegalArgumentException(emailAdresse + " ist keine richtige Email-Adresse");
		}
		boolean isValidPasswort = Pattern.matches("{8,20}", passwort);
		if (!isValidPasswort) {
			throw new IllegalArgumentException(passwort + " ist kein richtiges Passwort");
		}
		this.user = anonymerUser;
		this.emailAdresse = emailAdresse;
		this.setPasswort(passwort);
	}
	
	/**
	 * erstellt ein neuer AngemeldeterUser ohne zuerst ein anonymerUser erstellen zu müssen
	 * @param name
	 * @param ipAdresse
	 * @param emailAdresse
	 */
	public AngemeldeterUser(String name, String ipAdresse, String emailAdresse, String passwort) {
		super(new AnonymerUser(name, ipAdresse));
		this.emailAdresse = emailAdresse;
		this.setPasswort(passwort);
	}

	@Override
	public long getUserId() {
		return userId;
	}

	public String getEmailAdresse() {
		return emailAdresse;
	}

	public void setEmailAdresse(String emailAdresse) {
		boolean isEmailAdresse = Pattern.matches("^\s+@\s+$", emailAdresse);
		if (!isEmailAdresse) {
			throw new IllegalArgumentException(emailAdresse + " ist keine richtige Email-Adresse");
		}
		this.emailAdresse = emailAdresse;
	}
	

	@Override
	public void spielStarten(int anzahlSpieler) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getName() {
		return user.getName();
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public String getIpAdresse() {
		return ipAdresse;
	}

	@Override
	public boolean isAngemeldet() {
		return true;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public void setUserId(long id) {
		this.userId = id;
	}


}
