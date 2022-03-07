package de.wildeparty.aufbau;

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
	 * @return die IP-Adresse dieses Users. Diese ist nur wichtig, solange der User sich keine
	 * E-Mail-Adresse angelegt hat.
	 */
	
	public abstract String getIpAdresse();
	
}
