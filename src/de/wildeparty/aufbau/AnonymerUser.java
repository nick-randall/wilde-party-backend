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
	 * Ein vom Server erteilter String, der den User identifiziert
	 */

	private String token;

	/**
	 *  
	 */

	private LocalDateTime tokenZuletztAktualisiert;

	/**
	 * Konstruktor für lokale Objekte, die 
	 * noch keinen Primary Key, keine sessionId oder sessionTimestamp von der Datenbank
	 * zugeteilt wurden
	 * 
	 * @param name
	 * @param ipAdresse
	 */

	public AnonymerUser(String name) {
		super();
		this.name = name;
//		this.ipAdresse = ipAdresse;
	}

	/**
	 * Konstruktor für Objekte, die von der Datenbank geladen wurden
	 * 
	 * @param userId
	 * @param name
	 * @param ipAdresse
	 */

	public AnonymerUser(long userId, String name, String token, LocalDateTime tokenZuletztAktualisiert) {
		super();
		this.userId = userId;
		this.name = name;
//		this.ipAdresse = ipAdresse;
		this.token = token;
		this.tokenZuletztAktualisiert = tokenZuletztAktualisiert;
	}

	/**
	 * @return the sessionId
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param sessionId the sessionId to set
	 */
	public void setToken(String token) {
		this.token = token;
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

//	@Override
//	public String getIpAdresse() {
//		return ipAdresse;
//	}


	@Override
	public String toString() {
		return "AnonymerUser [userId=" + userId + ", name=" + name + ", token="
				+ token + ", tokenZuletztAktualisiert=" + tokenZuletztAktualisiert + "]";
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
	public LocalDateTime getTokenZuletztAktualisiert() {
		return tokenZuletztAktualisiert;
	}

	@Override
	public void setTokenZuletztAktualisiert(LocalDateTime zeit) {
		tokenZuletztAktualisiert = zeit;

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
