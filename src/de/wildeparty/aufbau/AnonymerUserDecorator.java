package de.wildeparty.aufbau;

import de.wildeparty.aufbau.backend.User;

public abstract class AnonymerUserDecorator implements User {

	protected User user;
	
	/**
	 * Die einmalige ID für diesen User
	 */
	
	protected long userId;
	
	/**
	 * Der selbstgewaehlte Name dieses Users
	 */
	
	protected String name;
	
	/**
	 * die gespeicherte IP-Adresse dieses Users. 
	 * 
	 */
	
	protected String ipAdresse;
	
	/**
	 * @return die userId dieses Users.
	 */
	
	public AnonymerUserDecorator(User user) {
		this.user = user;
	}
	
	
	public abstract long getUserId();
	
	/**
	 * 
	 * @return den selbstgewählten Username;
	 */
	
	public abstract String getName();
	
	/**
	 * @param name
	 */
	
	public abstract void setName(String name);
	
	/**
	 * 
	 * @param anzahlSpieler
	 */
	
	public abstract void spielStarten(int anzahlSpieler);
	
	/**
	 * 
	 * @return die IP-Adresse dieses Users. Diese ist nur wichtig, solange der User sich keine
	 * E-Mail-Adresse angelegt hat.
	 */
	
	public abstract String getIpAdresse();
	
}
