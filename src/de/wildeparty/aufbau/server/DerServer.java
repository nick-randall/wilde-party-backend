package de.wildeparty.aufbau.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class DerServer {

	private static DerServer server;

	private DerServer(String adresse, int port) {
		try {
			HttpServer server = HttpServer.create(new InetSocketAddress(adresse, port), 0);

			System.out.println("Server gestartet hier: " + server.getAddress());

			server.createContext("/home", new Home());
			server.setExecutor(null); // creates a default executor
			server.start();

		} catch (IOException ausnahme) {
			ausnahme.printStackTrace();
		}

	}

	public static DerServer getInstance(String adresse, int port) {
		if (server != null) {
			return server;
		}
		DerServer server = new DerServer(adresse, port);
		return server;

	}

}
