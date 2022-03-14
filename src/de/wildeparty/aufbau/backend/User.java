package de.wildeparty.aufbau.backend;

import java.time.LocalDateTime;

public interface User {

		/**
		 * Setzt die UserId dieses Users. Dieses Attribut wird gesetzt nachdem die Datenbank es
		 * vergeben hat.  
		 * @param id 
		 */
		public void setUserId(long id);

		/**
		 * Die einmalige ID für diesen User
		 */
		
		public long getUserId();
		
		/**
		 * 
		 * @return den selbstgewählten Username;
		 */
		
		public String getName();
		
		/**
		 * @param name
		 */
		
		public void setName(String name);
		
		/**
		 * 
		 * @return ob dieser User instanceof AngemeldeterUser ist
		 */
		public boolean isAngemeldet();
		
		/**
		 * Setzt die aktuelle SessionId für diesen User
		 * @param id
		 */
		
		public void setToken(String token);
		
		/**
		 * 
		 * @return die aktuelle SessionId für diesen User
		 */
		
		public String getToken();
		
		/**
		 * 
		 * @return wann die SessionId für diesen User zuletzt aktualisert wurde
		 */
		
		public LocalDateTime getTokenZuletztAktualisiert();
		
		/**
		 * Setzt wann die sessionId für diesen User zuletzt aktualisert wurde.
		 * Diese Zeit  wird von der Datenbank als Timestamp vergeben
		 * @param zeit
		 */
		
		public void setTokenZuletztAktualisiert(LocalDateTime zeit);
				
		
}
