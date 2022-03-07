package de.wildeparty.aufbau;

/**
 * Ein AnonymerUser hat keine email-Adresse hinterlegt und hat deshalb
 * keine weiterbestehende Identität
 * @author nick
 *
 */

public class AnonymerUser implements User {

	

	/**
	 * Die einmalige ID für diesen User
	 */
	
	private long userId;
	
	/**
	 * Der selbstgewaehlte Name dieses Users
	 */
	
	private String name;
	
	/**
	 * die IP-Adresse dieses Users. 
	 * 
	 */
	
	private String ipAdresse;
	

	
	public AnonymerUser(String name, String ipAdresse) {
		super();
		this.name = name;
		this.ipAdresse = ipAdresse;
	}

	public void setIpAdresse(String ipAdresse) {
		this.ipAdresse = ipAdresse;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public String getName() {
		return name;
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
	public String toString() {
		return "AnonymerUser [userId=" + userId + ", name=" + name + ", ipAdresse=" + ipAdresse + "]";
	}

	
	
}
