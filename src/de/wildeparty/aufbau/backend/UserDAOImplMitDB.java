package de.wildeparty.aufbau.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import de.wildeparty.aufbau.AngemeldeterUser;
import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.middletier.RandomString;

public class UserDAOImplMitDB implements UserDao {

	private List<User> alleBenutzer;

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
				long userId = ergebnis.getLong(1);
				String name = ergebnis.getString(2);
				String ipAdresse = ergebnis.getString(3);
				String sessionId = ergebnis.getString(7);
				LocalDateTime sessionZuletztAktualisiert = ergebnis.getTimestamp(8).toLocalDateTime();
				User benutzer = new AnonymerUser(userId, name, ipAdresse, sessionId, sessionZuletztAktualisiert);

				boolean isAngemeldet = ergebnis.getBoolean(4);
				if (isAngemeldet) {
					String emailAdresse = ergebnis.getString(5);
					String passwort = ergebnis.getString(6);
					benutzer = new AngemeldeterUser((AnonymerUser) benutzer, emailAdresse, passwort);

				}
				alleUsersTemp.add(benutzer);
			}

		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		alleBenutzer = alleUsersTemp;
		
		return alleBenutzer;
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

	/**
	 * Legt einen neuen AnonymenUser an, also einen User ohne Login-Daten
	 */

	@Override
	public void addUser(User benutzer) {
		// Die Datenbank benutzt Auto-Increment, also setzten wir den UserId auf
		// NULL. Ein anonymer User braucht auch keine email-Adresse und kein Passwort
		// (Spalten 5 + 6)
		// Auch die session_timeout (Spalte 8) wird von der Datenbank mit Timestamp
		// Automatisch gesetzt
		

		String sql = "INSERT users VALUE (NULL, ?, ?, ?, NULL, NULL, ?, NULL)";
		String sessionId = new RandomString().getInhalt();
		
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); PreparedStatement verpackung = verbindung.prepareStatement(sql);) {
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, false);
			verpackung.setString(4, sessionId);
			verpackung.execute();

		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}

		// Jetzt die von der Datenbank vergebene ID und TimeStamp holen und diese dem neuen
		// User-Objekt übergeben
		sql = "SELECT * FROM users ORDER BY USER_ID DESC";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql);) {
			if (ergebnis.next()) {
				long neueId = ergebnis.getLong(1);
				benutzer.setUserId(neueId);
				LocalDateTime sessionZuletzAktualisert = ergebnis.getTimestamp(8).toLocalDateTime();
				benutzer.setSessionZuletztAktualisiert(sessionZuletzAktualisert);
				
			}
		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		benutzer.setSessionId(sessionId);
		benutzer.getSessionZuletztAktualisiert();
		alleBenutzer.add(benutzer);

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
		alleBenutzer.remove(benutzer);

	}

	/**
	 * Hier einen Benutzer übergeben mit neuen Parametern...aber die gleiche User-Id
	 */

	@Override
	public void updateUser(User benutzer) {
		long id = benutzer.getUserId();
		String sql = "UPDATE users SET name = ?, ip_address = ?, is_registered = ?, email_address = ?, password = ?, session_id = ? "
				+ "WHERE user_id = " + id;
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT); PreparedStatement verpackung = verbindung.prepareStatement(sql)) {

			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, benutzer.isAngemeldet());
			verpackung.setString(6, benutzer.getSessionId());


			if (benutzer.isAngemeldet()) {
				AngemeldeterUser au = (AngemeldeterUser) benutzer;
				verpackung.setString(4, au.getEmailAdresse());
				verpackung.setString(5, au.getPasswort());
			}
			verpackung.execute();
		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		User alterBenutzer = null;
		if (benutzer.isAngemeldet()) {
			alterBenutzer = (AngemeldeterUser) benutzer;
		} else {
			alterBenutzer = (AnonymerUser) benutzer;
		}
		benutzer.setSessionZuletztAktualisiert(LocalDateTime.now());
		alleBenutzer.remove(alterBenutzer);

		alleBenutzer.add(benutzer);
	}

	/*
	 * Holt aus der Datenbank nur den ersten User, der die angegebene ID hat. Würde
	 * also mögliche Duplikate ignorieren, die Datenbank sollte dies aber verbieten
	 * durch Auto-Increment
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
				String sessionId = ergebnis.getString(7);
				LocalDateTime sessionZuletztAktualisiert = ergebnis.getTimestamp(8).toLocalDateTime();
				benutzer = new AnonymerUser(userId, name, ipAdresse, sessionId, sessionZuletztAktualisiert);
				boolean isAngemeldet = ergebnis.getBoolean(4);
				if (isAngemeldet) {
					String emailAdresse = ergebnis.getString(5);
					String passwort = ergebnis.getString(6);
					benutzer = new AngemeldeterUser((AnonymerUser) benutzer, emailAdresse, passwort);
				}

			}
		} catch (SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		return benutzer;
	}

}
