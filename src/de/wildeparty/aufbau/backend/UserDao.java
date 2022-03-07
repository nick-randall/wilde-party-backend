package de.wildeparty.aufbau.backend;

import java.util.List;

public interface UserDao {
	/**
	 * Erstellt eine Liste aller gespeicherten Objekte
	 * @return
	 */
	public List<User> getAlleUsers();
	
	/** Ein Objekt wird der Datenhaltung zugefügt*/
	public void addUser(User benutzer);
	
	/** Ein Objekt wird aus der Datenhaltung entfernt*/
	public void deleteUser(User benutzer);
	
	/**Ein Objekt in der Datenhaltung aktualisieren*/
	public void updateUser(User benutzer);
	
	/**Sucht aus der Datenhaltung das Objekt mit der angegebenID(Primary Key)*/
	public User getUserById(long id);
}
