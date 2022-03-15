package de.wildeparty.aufbau.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.middletier.UserService;

public class Home extends HandlerMitCors {

	private UserService userKnecht;
	
	public Home(UserService userKnecht) {
		this.userKnecht = userKnecht;
	}
	
	@Override
	public void handle(HttpExchange austausch) throws IOException {
		super.handle(austausch);

		Headers senderHeaders = austausch.getRequestHeaders();
		
		OutputStream koerper = austausch.getResponseBody();

		if (!senderHeaders.containsKey("Authorization")) {
			AnonymerUser neuerAnonymerBenutzer = new AnonymerUser(null);
			userKnecht.anlegenNeuenUser(neuerAnonymerBenutzer);
			AnonymerUser anonymerBenutzerAusDerDB = (AnonymerUser) userKnecht.holenNeuenUser();
			
			Map<String, String> neuerToken = new HashMap<>();
			neuerToken.put("token", anonymerBenutzerAusDerDB.getToken());
			Gson gson = new Gson();
			String verpackterToken = gson.toJson(neuerToken);
			
			austausch.sendResponseHeaders(200, verpackterToken.length()); 

	        koerper.write(verpackterToken.getBytes());            
	        koerper.close();
			
		}

		else {
			String token = senderHeaders.get("Authorization").get(0);
			if (token.contains("Bearer ")) {
				token = token.replace("Bearer ", "");
				
//				User benutzer = userKnecht.
				System.out.println("Token gibt es in der Datenbank? " + userKnecht.isTokenVorhanden(token));
				System.out.println("Token ist aktuell? " + userKnecht.isTokenAktuell(token));
				
				if(!userKnecht.isTokenAktuell(token) || !userKnecht.isTokenVorhanden(token)) {
					System.out.println("Token not valid");
					austausch.sendResponseHeaders(401, 0); 
//					 koerper.write(verpackterToken.getBytes());            
				     koerper.close();
				}
				
				// TODO: refresh token
			}
			System.out.println("Token from client: " + token);
		}
	}
	
	
	public void lesenSenderKoerper(InputStream senderKoerper) {

//		InputStream senderKoerper = austausch.getRequestBody();
		BufferedInputStream bufferedSenderKoerper = new BufferedInputStream(senderKoerper);
		String koerperText = "";
		while (true) {

			int gelesen;
			try {
				gelesen = bufferedSenderKoerper.read();
			
			if (gelesen == -1) {
				break;
			}
			koerperText = koerperText + (char) gelesen;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(koerperText);

//		senderHeaders.forEach((header, props) -> {
//			System.out.println("header " + header + ": " + props);
//		});
//		System.out.println("********************");
//		austausch.getResponseHeaders().forEach((key, value) -> System.out.println(key + " " + value));
		

	}
	

}
