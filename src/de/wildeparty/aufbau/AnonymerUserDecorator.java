package de.wildeparty.aufbau;

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
	 * @return den selbstgewählten Username;
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
	public int hashCode() {
		return Objects.hash(user.getUserId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnonymerUserDecorator other = (AnonymerUserDecorator) obj;
		return user.getUserId() == other.getUserId();
	}

}
