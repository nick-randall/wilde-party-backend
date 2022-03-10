package de.wildeparty.aufbau;

import java.time.LocalDateTime;
import java.util.Objects;

import de.wildeparty.aufbau.backend.User;

/**
 * Ein AnonymerUser hat keine email-Adresse hinterlegt und hat deshalb keine
 * weiterbestehende Identität
 * 
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

	/**
	 * Ein vom Server erteilter String, der den User identifiziert
	 */

	private String sessionId;

	/**
	 *  
	 */

	private LocalDateTime sessionZuletztAktualisiert;

	/**
	 * Konstruktor für lokale Objekte, die 
	 * noch keinen Primary Key, keine sessionId oder sessionTimestamp von der Datenbank
	 * zugeteilt wurden
	 * 
	 * @param name
	 * @param ipAdresse
	 */

	public AnonymerUser(String name, String ipAdresse) {
		super();
		this.name = name;
		this.ipAdresse = ipAdresse;
	}

	/**
	 * Konstruktor für Objekte, die von der Datenbank geladen wurden
	 * 
	 * @param userId
	 * @param name
	 * @param ipAdresse
	 */

	public AnonymerUser(long userId, String name, String ipAdresse, String sessionId, LocalDateTime sessionZuletztAktualisiert) {
		super();
		this.userId = userId;
		this.name = name;
		this.ipAdresse = ipAdresse;
		this.sessionId = sessionId;
		this.sessionZuletztAktualisiert = sessionZuletztAktualisiert;
	}

	/**
	 * @return the sessionId
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
		return "AnonymerUser [userId=" + userId + ", name=" + name + ", ipAdresse=" + ipAdresse + ", sessionId="
				+ sessionId + ", sessionZuletztAktualisiert=" + sessionZuletztAktualisiert + "]";
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
	public LocalDateTime getSessionZuletztAktualisiert() {
		return sessionZuletztAktualisiert;
	}

	@Override
	public void setSessionZuletztAktualisiert(LocalDateTime zeit) {
		sessionZuletztAktualisiert = zeit;

	}

	@Override
	public int hashCode() {
		return Objects.hash(userId);
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
		AnonymerUser other = (AnonymerUser) obj;
		return this.getUserId() == other.getUserId();
	}

}
