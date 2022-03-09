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

	// Diese Methode ist nur für die Objekte, die zum Spiel gehören
//	public long vergebenNeuerId() {
//		String sql = "INSERT assigned_ids VALUE(null)";
//		long neueId = 0;
//
//		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
//				ZugriffAufDB.PASSWORT); Statement verpackung = verbindung.createStatement();)
//		{
//			verpackung.execute(sql);
//		} catch (SQLException ausnahme) {
//			ausnahme.printStackTrace();
//		}
//		sql = "SELECT * FROM assigned_ids ORDER BY ASSIGNED_IDS DESC";
//		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
//				ZugriffAufDB.PASSWORT); Statement verpackung = verbindung.createStatement();
//				ResultSet ergebnis = verpackung.executeQuery(sql);)
//		{
//			neueId = ergebnis.getLong(1); 
//			
//		} catch (SQLException ausnahme) {
//			ausnahme.printStackTrace();
//		}
//		return neueId;
//		
//	}

	@Override
	public void addUser(User benutzer) {
		// Die Datenbank benutzt Auto-Increment, also setzten wir den ersten Wert auf
		// NULL
		String sql = "INSERT users VALUE (NULL, ?, ?, ?, ?, ?)";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); PreparedStatement verpackung = verbindung.prepareStatement(sql);) {
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, benutzer.isAngemeldet());
			String emailAdresse = null;
			String passwort = null;
			if (benutzer.isAngemeldet()) {
				AngemeldeterUser au = (AngemeldeterUser) benutzer;
				emailAdresse = au.getEmailAdresse();
				passwort = au.getPasswort();
			}
			verpackung.setString(4, emailAdresse);
			verpackung.setString(5, passwort);
			verpackung.execute();

		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}

		// Jetzt die von der Datenbank vergebene ID holen und diese dem neuen
		// User-Objekt übergeben
		sql = "SELECT * FROM users ORDER BY USER_ID DESC";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql);) {
			if (ergebnis.next()) {
				long neueId = ergebnis.getLong(1);
				benutzer.setUserId(neueId);
			}
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
		long id = benutzer.getUserId();
		String sql = "UPDATE users SET name = ?, ip_address = ?, is_anonym = ?, email_address = ?, password = ? " + "WHERE user_id = " + id;
		try(Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER, ZugriffAufDB.PASSWORT);
				PreparedStatement verpackung = verbindung.prepareStatement(sql)){
		
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, benutzer.isAngemeldet());
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(1, benutzer.getName());

		}catch(SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
	}

	/*
	 * Holt aus der Datenbank nur den ersten User, der die angegebene ID hat.
	 * Ignoriert also mögliche Duplikate, die sollte es sowieso nicht geben
	 **/
	@Override
	public User getUserById(long id) {
		String sql = "SELECT * FROM users WHERE user_id = " + id;
		User benutzer = null;
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql);) {
			if (ergebnis.next()) {
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
