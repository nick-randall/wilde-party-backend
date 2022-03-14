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

	@Override
	public void setUserId(long id) {
		this.user.setUserId(id);
	}

	@Override
	public String getToken() {
		return this.user.getToken();
	}

	@Override
	public void setToken(String id) {
		this.user.setToken(id);
	}


	@Override
	public LocalDateTime getTokenZuletztAktualisiert() {
		return user.getTokenZuletztAktualisiert();
	}


	@Override
	public void setTokenZuletztAktualisiert(LocalDateTime zeit) {
		user.setTokenZuletztAktualisiert(zeit);
	}
	


}
