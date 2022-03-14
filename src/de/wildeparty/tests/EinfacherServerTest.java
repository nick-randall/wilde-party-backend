package de.wildeparty.tests;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import de.wildeparty.aufbau.backend.UserDAOImplMitDB;
import de.wildeparty.aufbau.backend.UserDao;
import de.wildeparty.aufbau.middletier.UserService;
import de.wildeparty.aufbau.server.DerServer;

public class EinfacherServerTest {

	public static void main(String[] args) {
		
		UserDAOImplMitDB quelle = new UserDAOImplMitDB();
		UserService knecht = new UserService(quelle);
		DerServer neuerServer = DerServer.getInstance("127.0.0.1", 5566, knecht);
		
	}
	/*
	 * 
	 */

}
