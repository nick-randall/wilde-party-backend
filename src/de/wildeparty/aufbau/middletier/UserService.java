package de.wildeparty.aufbau.middletier;

import java.util.List;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDao;

public class UserService {
	/**
	 * die datenquelle für den Service, zB die Datenbank, auf die den Service zugreift.
	 * Diese wird im Konstruktor angegeben
	 */

	private UserDao datenquelle;

	/**
	 * eine Referenz zu der gespeicherten Users-Tabelle aus der DAO-Implementierung
	 */
	private List<User> alleUsers;

	public UserService(UserDao quelle) {
		this.datenquelle = quelle;
		this.alleUsers = quelle.getAlleUsers();
	}
	
	public List<User> getAlleUsers() {
		return alleUsers;
	}
	
	/**
	 * 
	 * @param benutzer
	 * Legt einen neuen Benutzer in der Datenquelle an.
	 */
	
	public void neuenAnonymenUserAnlegen(AnonymerUser benutzer) {
		datenquelle.addUser(benutzer);
		
	}

	/**
	 * 
	 * @param ipAdresse
	 * @return ob es bereits einen Benutzer in der Datenquelle gibt, die diese ipAdresse hat.
	 */
	public boolean isIpAdresseVorhanden(String ipAdresse) {
		boolean isVorhanden = false;
		for (User benutzer : alleUsers) {
			if (benutzer.getIpAdresse().equals(ipAdresse)) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}
	/**
	 * 
	 * @return
	 */
	public User getUserById(long id) {
		User user = null;
		for (User benutzer: alleUsers ) {
			if(benutzer.getUserId() == id) {
				user = benutzer;
				break;
			}
		}
		return user;
	}

}
