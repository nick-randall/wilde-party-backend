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

public class UserDAOImplMitDB implements UserDao{

	private List<User> alleUsers;

	public UserDAOImplMitDB() {
		List<User> alleUsers = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER,
				ZugriffAufDB.PASSWORT);
				Statement verpackung = verbindung.createStatement();
				ResultSet ergebnis = verpackung.executeQuery(sql)) {

			while (ergebnis.next()) {
				User user;
				long userId = ergebnis.getLong(1);
				String name = ergebnis.getString(2);
				String ipAdresse = ergebnis.getString(3);
				user = new AnonymerUser(userId, name, ipAdresse);
				boolean isAnonymous = ergebnis.getBoolean(4);
				if(!isAnonymous) {
					String emailAdresse = ergebnis.getString(5);
					user = new AngemeldeterUser(user, emailAdresse);
				}
				alleUsers.add(user);
			}
			
		} catch (SQLException ausnahme) {
			// TODO better error handling
			ausnahme.printStackTrace();
		}
		this.alleUsers = alleUsers;

	}

	@Override
	public List<User> getAlleUsers() {
		
		return alleUsers;
	}

	@Override
	public void addUser(User benutzer) {
		String sql = "INSERT user VALUE (null, ?, ?, ?, ?)";
		try(Connection verbindung = DriverManager.getConnection(ZugriffAufDB.URL, ZugriffAufDB.USER, ZugriffAufDB.PASSWORT);
				PreparedStatement verpackung = verbindung.prepareStatement(sql);
				){
			verpackung.setString(1, benutzer.getName());
			verpackung.setString(2, benutzer.getIpAdresse());
			verpackung.setBoolean(3, benutzer instanceof AnonymerUser);
			String emailAdresse = null; 
			if (benutzer instanceof AngemeldeterUser) {
				AngemeldeterUser au = (AngemeldeterUser)benutzer;
				emailAdresse = au.getEmailAdresse();
			}
			verpackung.setString(4, emailAdresse);
			verpackung.execute();
			
		} catch(SQLException ausnahme) {
			ausnahme.printStackTrace();
		}
		
		
	}

	@Override
	public void deleteUser(User benutzer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User benutzer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
