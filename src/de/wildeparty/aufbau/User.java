package de.wildeparty.aufbau;

public interface User {


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
		 * @return die IP-Adresse dieses Users. Diese ist nur wichtig, solange der User sich keine
		 * E-Mail-Adresse angelegt hat.
		 */
		
		public String getIpAdresse();
		
		
}
