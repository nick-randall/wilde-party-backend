package de.wildeparty.aufbau.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;

public class UserDAOImplMitDB implements UserDao {

	private List<User> alleUsers;

	public UserDAOImplMitDB() {
		getAlleUsers();

	}

	@Override
	public List<User> getAlleUsers() {
		List<User> alleUsersTemp = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql)) {

			while (ergebnis.next()) {
				User benutzer;
				long userId = ergebnis.getLong(1);
				String name = ergebnis.getString(2);
				String ipAdresse = ergebnis.getString(3);
				benutzer = new AnonymerUser(userId, name, ipAdresse);
				boolean isAnonym = ergebnis.getBoolean(4);
				if (!isAnonym) {
					String emailAdresse = ergebnis.getString(5);
					String passwort = ergebnis.getString(6);
					benutzer = new AngemeldeterUser(benutzer, emailAdresse, passwort);
				}
				alleUsersTemp.add(benutzer);
			}

		} catch (SQLException ausnahme) {
			// TODO better error handling
			ausnahme.printStackTrace();
		}
		alleUsers = alleUsersTemp;
		return alleUsers;
	}

	@Override
	public void addUser(User benutzer) {
		String sql = "INSERT users VALUE (0, ?, ?, ?, ?)";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); PreparedStatement verpackung = verbindung.prepareStatement(sql);) {
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, benutzer instanceof AnonymerUser);
			String emailAdresse = null;
			if (benutzer.isAngemeldet()) {
				AngemeldeterUser au = (AngemeldeterUser) benutzer;
				emailAdresse = au.getEmailAdresse();
			}
			verpackung.setString(4, emailAdresse);
			verpackung.execute();

		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		alleUsers.add(benutzer);

	}

	@Override
	public void deleteUser(User benutzer) {
		String sql = "DELETE FROM users WHERE user_id = " + benutzer.getUserId();
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); Statement verpackung = verbindung.createStatement();) {
			verpackung.execute(sql);
		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		alleUsers.remove(benutzer);

	}

	@Override
	public void updateUser(User benutzer) {
		// TODO Auto-generated method stub

	}

	// Holt nur den ersten User, der die angegebene ID hat. Ignoriert also mögliche Duplikate,
	// die sollte es sowieso nicht geben
	@Override
	public User getUserById(long id) {
		String sql = "SELECT * FROM users WHERE user_id = " + id;
		User benutzer = null;
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql);) {
			if(ergebnis.next()) {
				long userId = ergebnis.getLong(1);
				String name = ergebnis.getString(2);
				String ipAdresse = ergebnis.getString(3);
				benutzer = new AnonymerUser(userId, name, ipAdresse);
				boolean isAnonym = ergebnis.getBoolean(4);
				if (!isAnonym) {
					String emailAdresse = ergebnis.getString(5);
					String passwort = ergebnis.getString(6);
					benutzer = new AngemeldeterUser(benutzer, emailAdresse, passwort);
				}
			
			}
			
		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		return benutzer;
	}

}
