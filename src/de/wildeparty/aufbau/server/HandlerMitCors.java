package de.wildeparty.aufbau.server;
import java.io.IOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public abstract class HandlerMitCors implements HttpHandler {

	/**
	 * Dieser Abstrakte-Handler erlaubt erbende Klassen das 
	 * Testing auf einem lokalen Browser. 
	 * Ohne Cors (Cross-Origin-Resource-Sharing) dürften Requests.
	 * Das ist eine Sicherheitskontrolle, also ist es nur für
	 * Testing empfohlen
	 *  
	 */
	@Override 
    public void handle (HttpExchange austausch) throws IOException{
		Headers kopf = austausch.getResponseHeaders();
		kopf.add("Access-Control-Allow-Origin", "*");
	}

	
}
