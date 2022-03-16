package de.wildeparty.aufbau.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import de.wildeparty.aufbau.AnonymerUser;
import de.wildeparty.aufbau.backend.User;
import de.wildeparty.aufbau.middletier.AuswahlKriteriums;
import de.wildeparty.aufbau.middletier.UserService;
import de.wildeparty.aufbau.middletier.ZuVieleException;

public class Home extends HttpHandlerMitAuth {

	public Home(UserService userKnecht) {
		this.userKnecht = userKnecht;
		this.gson = new Gson();
	}

	@Override
	public void handle(HttpExchange austausch) throws IOException {
		super.handle(austausch);
		
		Headers senderHeaders = austausch.getRequestHeaders();

		OutputStream koerper = austausch.getResponseBody();

			String token = senderHeaders.get("Authorization").get(0);
			if (token.contains("Bearer ")) {
				token = token.replace("Bearer ", "");
				System.out.println("Token from client: " + token);


//				User benutzer = userKnecht.
				System.out.println("Token gibt es in der Datenbank? " + userKnecht.isTokenVorhanden(token));
				System.out.println("Token ist aktuell? " + userKnecht.isTokenAktuell(token));

				if (!userKnecht.isTokenAktuell(token) || !userKnecht.isTokenVorhanden(token)) {
					System.out.println("Token not valid");
					austausch.sendResponseHeaders(401, 0);
//					 koerper.write(verpackterToken.getBytes());            
					koerper.close();
				} else {
					Predicate<User> hasToken = AuswahlKriteriums.hasToken(token);
					try {
						User benutzer = userKnecht.getUser(hasToken);
						
//						Map<String, String> neuerName = new HashMap<>();
//						neuerName.put("userName", benutzer.getName());
//						String verpackterName = gson.toJson(neuerName);
						String verpackterBenutzer = gson.toJson(benutzer);
						
						System.out.println(verpackterBenutzer);
						koerper.write(verpackterBenutzer.getBytes());
						koerper.close();
					} catch (ZuVieleException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// TODO: refresh token
				}
			}
		}
//	}

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
