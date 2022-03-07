package de.wildeparty.aufbau;

import java.util.regex.Pattern;

public abstract class AngemeldeterUser extends AnonymerUserDecorator {

protected User user;
	
	private String emailAdresse;

	public AngemeldeterUser(User user, String emailAdresse) {
		boolean isEmailAdresse = Pattern.matches("^\s+@\s+$", emailAdresse);
		if(!isEmailAdresse) {
			throw new IllegalArgumentException(emailAdresse + " ist keine richtige Email-Adresse");
		}
		this.user = user;
		this.emailAdresse = emailAdresse;
	}
	
	
	
	@Override
	public long getUserId() {
		return userId;
	}
	
	public String getEmailAdresse() {
		return emailAdresse;
	}

	public void setEmailAdresse(String emailAdresse) {
		boolean isEmailAdresse = Pattern.matches("^\s+@\s+$", emailAdresse);
		if(!isEmailAdresse) {
			throw new IllegalArgumentException(emailAdresse + " ist keine richtige Email-Adresse");
		}
		this.emailAdresse = emailAdresse;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public String getIpAdresse() {
		return ipAdresse;
	}


}
