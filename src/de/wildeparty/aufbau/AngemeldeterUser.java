package de.wildeparty.aufbau;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.wildeparty.aufbau.backend.User;

public class AngemeldeterUser extends AnonymerUserDecorator {

	private User user;

	private String emailAdresse;
	
	private String passwort;
	
	/**
	 * Decorator Pattern
	 * erstellt ein AngemeldeterUser aus einem AnonymerUser
	 * @param anonymerUser
	 * @param emailAdresse
	 * @param passwort
	 */

	public AngemeldeterUser(User user, String emailAdresse, String passwort) {
		super(user);
	    Pattern VALID_EMAIL = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Pattern VALID_PASSWORT = Pattern.compile("{8,20}", Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_EMAIL.matcher(emailAdresse);
		boolean isValidEmailAdresse = matcher.find();
		if (!isValidEmailAdresse) {
			throw new IllegalArgumentException(emailAdresse + " ist keine richtige Email-Adresse");
		}
		
		Matcher matcherZwei = VALID_PASSWORT.matcher(passwort);
		boolean isValidPasswort = matcherZwei.find();
		
		if (!isValidPasswort) {
			throw new IllegalArgumentException(passwort + " ist kein richtiges Passwort");
		}
		this.user = user;
		this.emailAdresse = emailAdresse;
		this.passwort = passwort;
	}
	

//	/**
//	 * erstellt ein neuer AngemeldeterUser ohne zuerst ein anonymerUser erstellen zu müssen
//	 * @param name
//	 * @param emailAdresse
//	 */
//	public AngemeldeterUser(long userId, String name, String ipAdresse, String emailAdresse, String passwort) {
//		super(new AnonymerUser(userId, name, ipAdresse));
//		this.emailAdresse = emailAdresse;
//		this.setPasswort(passwort);
//	}


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
//
//	@Override
//	public String getIpAdresse() {
//		return user.getIpAdresse();
//	}

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
	public String toString() {
		return "AngemeldeterUser [emailAdresse=" + emailAdresse + ", passwort=" + passwort + ", userId=" + getUserId()
				+ ", name=" + getName() + ", token=" + getToken() + ", tokenZuletztAktualisiert=" + getTokenZuletztAktualisiert() + "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(user.getUserId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (hashCode() == obj.hashCode())
			return true;
		if (getClass() != obj.getClass())
			return false;
		AngemeldeterUser other = (AngemeldeterUser) obj;
		return user.getUserId() == other.getUserId();
	}
	
	
	


}
