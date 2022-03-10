package de.wildeparty.aufbau;

import java.time.LocalDateTime;
import java.util.Objects;

import de.wildeparty.aufbau.backend.User;

public abstract class AnonymerUserDecorator implements User {

	private User user;

	/**
	 * Die einmalige ID für diesen User
	 */

	public AnonymerUserDecorator(User user) {
		this.user = user;
	}

	/**
	 * @return die userId dieses Users.
	 */

	public long getUserId() {
		return user.getUserId();
	}

	/**
	 * 
	 * @return den selbstgewählten Benutzername;
	 */
	@Override
	public String getName() {
		return user.getName();
	}

	/**
	 * @param name
	 */

	public void setName(String name) {
		user.setName(name);
	}

	/**
	 * 
	 * @param anzahlSpieler
	 */

	public abstract void spielStarten(int anzahlSpieler);

	/**
	 * 
	 * @return die IP-Adresse dieses Users. Diese ist nur wichtig, solange der User
	 *         sich keine E-Mail-Adresse angelegt hat.
	 */

	public String getIpAdresse() {
		return user.getIpAdresse();
	}
	
	@Override
	public void setUserId(long id) {
		this.user.setUserId(id);
	}

	@Override
	public String getSessionId() {
		return this.user.getSessionId();
	}

	@Override
	public void setSessionId(String id) {
		this.user.setSessionId(id);
	}


	@Override
	public LocalDateTime getSessionZuletztAktualisiert() {
		return user.getSessionZuletztAktualisiert();
	}


	@Override
	public void setSessionZuletztAktualisiert(LocalDateTime zeit) {
		user.setSessionZuletztAktualisiert(zeit);
	}
	


}
