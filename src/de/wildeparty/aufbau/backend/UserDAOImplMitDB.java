package de.wildeparty.aufbau.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.wildeparty.aufbau.AnonymerUser;

public class UserDAOImplMitDB {

	private List<User> alleUsers;

	public UserDAOImplMitDB() {
		List<User> alleUsers = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql)) {

			while (ergebnis.next()) {
				long userId = ergebnis.getLong(0);
				String name = ergebnis.getString(1);
				String ipAdresse = ergebnis.getString(2);
				AnonymerUser user = new AnonymerUser(name, ipAdresse);
				if(ergebnis.)
			}
			
		} catch (SQLException ausnahme) {
			// TODO better error handling
			ausnahme.printStackTrace();
		}
		

	}

}
