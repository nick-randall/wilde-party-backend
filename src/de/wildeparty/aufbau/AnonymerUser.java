package de.wildeparty.aufbau;

import java.util.Objects;

import de.wildeparty.aufbau.backend.User;

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
	
	private final String ipAdresse;
	

	
	public AnonymerUser(String name, String ipAdresse) {
		super();
		this.name = name;
		this.ipAdresse = ipAdresse;
	}
	
	public AnonymerUser(long userId, String name, String ipAdresse) {
		super();
		this.userId = userId;
		this.name = name;
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

	@Override
	public boolean isAngemeldet() {
		return false;
	}

	@Override
	public void setUserId(long id) {
		this.userId = id;		
	}

	@Override
	public int hashCode() {
		return Objects.hash(ipAdresse, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnonymerUser other = (AnonymerUser) obj;
		return  userId == other.userId;
	}

	
	
	
}
