package de.wildeparty.aufbau.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import de.wildeparty.aufbau.middletier.UserService;

public class DerServer {

	private static DerServer server;

	private DerServer(String adresse, int port, UserService userKnecht) {

		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(adresse, port), 10);

			System.out.println("Server gestartet hier: " + server.getAddress());

			server.createContext("/home", new Home(userKnecht));
			server.createContext("/newname?", new TestHandler(userKnecht));
			server.setExecutor(null); // creates a default executor
			server.start();

		} catch (IOException ausnahme) {
			ausnahme.printStackTrace();
		}

	}

	public static DerServer getInstance(String adresse, int port, UserService userKnecht) {
		if (server != null) {
			return server;
		}
		DerServer server = new DerServer(adresse, port, userKnecht);
		return server;

	}

	public static class TestHandler implements HttpHandler {
		
		private UserService userKnecht;
		
		public TestHandler(UserService userKnecht) {
			this.userKnecht = userKnecht;
		}
		
		@Override
		public void handle(HttpExchange austausch) {
			System.out.println("test");
		}
	}

}
