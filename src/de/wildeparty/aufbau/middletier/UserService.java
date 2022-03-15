package de.wildeparty.aufbau.middletier;

import java.time.LocalDateTime;
import java.util.List;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.backend.UserDao;

public class UserService {
	/**
	 * die datenquelle für den Service, zB die Datenbank, auf die den Service
	 * zugreift. Diese wird im Konstruktor angegeben
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
	 * @param benutzer Legt einen neuen Benutzer in der Datenquelle an.
	 * 
	 * @return den neu angelegten Benutzer
	 */

	public void anlegenNeuenUser(AnonymerUser benutzer) {
		datenquelle.addUser(benutzer);
	}

	public void loeschenUser(User benutzer) {
		datenquelle.deleteUser(benutzer);
	}

	/**
	 * 
	 * @param ipAdresse
	 * @return ob es bereits einen Benutzer in der Datenquelle gibt, die diesen
	 *         Token hat.
	 */
	public boolean isTokenVorhanden(String token) {
		boolean isVorhanden = false;
		for (User benutzer : alleUsers) {
			if (benutzer.getToken().equals(token)) {
				isVorhanden = true;
			}
		}
		return isVorhanden;
	}

	public boolean isTokenAktuell(String token) {
		boolean isAktuell = false;
		int abgelaufenNach = 1;
		for (User benutzer : alleUsers) {
			if (benutzer.getToken().equals(token)) {
				LocalDateTime jetzt = LocalDateTime.now();
				LocalDateTime zuletztAktualisiert = benutzer.getTokenZuletztAktualisiert();				
				isAktuell = zuletztAktualisiert.plusMinutes(abgelaufenNach).isAfter(jetzt);
			}
		}
		return isAktuell;
	}

	/**
	 * 
	 * @return den ersten User mit dieser ID;
	 */
	public User getUserById(long id) {
		User user = null;
		for (User benutzer : alleUsers) {
			if (benutzer.getUserId() == id) {
				user = benutzer;
				break;
			}
		}

		return user;
	}

	public void updateUser(User benutzer) {
		datenquelle.updateUser(benutzer);
	}

	/**
	 * 
	 * @return den neuesten User (also den zuletzt in der DB angelegten).
	 */

	public User holenNeuenUser() {
		User neuerUser = alleUsers.get(alleUsers.size() - 1);
		return neuerUser;
	}

	/**
	 * 
	 * @param benutzer
	 * @param emailAdresse
	 * @param passwort
	 */
	public void anmeldenUser(AnonymerUser benutzer, String emailAdresse, String passwort) {
		AngemeldeterUser angemeldeterBenutzer = new AngemeldeterUser(benutzer, emailAdresse, passwort);

		datenquelle.updateUser(angemeldeterBenutzer);
	}

}
