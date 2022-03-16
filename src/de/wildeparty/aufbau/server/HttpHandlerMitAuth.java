package de.wildeparty.aufbau.server;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.middletier.UserService;

/**
 * Eine HttpHandler-Klasse, die eine "Auth"-Schicht sicherstellt.
 * Die handle-Methode der Klasse bearbeitet Requests, die keine 
 * Authorisation-Token beinhalten. Es wird einen neuen User in der
 * Datenbank erstellt und deren Token an den Client verschickt.
 * @author nick
 *
 */

public abstract class HttpHandlerMitAuth extends HandlerMitCors{

	protected UserService userKnecht;
	
	protected Gson gson;
	
	@Override
	public void handle(HttpExchange austausch) throws IOException {
		super.handle(austausch);
		
		Headers senderHeaders = austausch.getRequestHeaders();
		
		OutputStream koerper = austausch.getResponseBody();

		if (!senderHeaders.containsKey("Authorization")) {
			System.out.println("Client schickt Request ohne Token");
			
			AnonymerUser neuerAnonymerBenutzer = new AnonymerUser(null);
			userKnecht.anlegenNeuenUser(neuerAnonymerBenutzer);
			AnonymerUser anonymerBenutzerAusDerDB = (AnonymerUser) userKnecht.getNeuenUser();

			// erstellt einen JSON-String wie : {"token": "dJlUXVVvBoNHBFhMIBY\WGX^"}
			Map<String, String> neuerToken = new HashMap<>();
			neuerToken.put("token", anonymerBenutzerAusDerDB.getToken());
			String verpackterToken = gson.toJson(neuerToken);
			

			austausch.sendResponseHeaders(200, verpackterToken.length());

			koerper.write(verpackterToken.getBytes());
			koerper.close();

		}
	}

	
}
