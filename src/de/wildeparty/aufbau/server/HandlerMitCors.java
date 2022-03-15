package de.wildeparty.aufbau.server;
import java.io.IOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public abstract class HandlerMitCors implements HttpHandler {

	/**
	 * Dieser Abstrakte-HttpHandler erlaubt erbende Klassen das 
	 * Testing des Clients von einem anderen Port.
	 * Ohne Cors (Cross-Origin-Resource-Sharing) dürften keine Requests 
	 * von lokalem Browser ausgeführt werden.
	 * Das ist eine Sicherheitskontrolle, also ist es nur für
	 * Testing empfohlen
	 *  
	 */
	@Override 
    public void handle (HttpExchange austausch) throws IOException{
		Headers kopf = austausch.getResponseHeaders();
		// https://www.sjoerdlangkemper.nl/2018/09/12/authorization-header-and-cors/
		// You must specify a URL, a wildcard won’t work with authenticated requests.

		kopf.add("Access-Control-Allow-Origin", "http://localhost:3000");
		kopf.add("Access-Control-Allow-Credentials", "true");
		kopf.add("Access-Control-Allow-Headers", "withCredentials, Authentication, Content-Type, Authorization");
		kopf.add("Access-Control-Allow-Methods", "GET");
		kopf.add("Access-Control-Expose-Headers", "Content-Security-Policy, Location");
		kopf.add("Access-Control-Max-Age", "600");
	}

	
}
