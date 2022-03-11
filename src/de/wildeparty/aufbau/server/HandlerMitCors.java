package de.wildeparty.aufbau.server;
import java.io.IOException;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public abstract class HandlerMitCors implements HttpHandler {

	@Override 
    public void handle (HttpExchange austausch) throws IOException{
		Headers kopf = austausch.getResponseHeaders();
		kopf.add("Access-Control-Allow-Origin", "*");
	}

	
}
