package de.wildeparty.aufbau.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class Home extends HandlerMitCors {

	@Override
	public void handle(HttpExchange austausch) throws IOException {
		super.handle(austausch);
		
		Headers sender = austausch.getRequestHeaders();
		
		InetSocketAddress senderAdresse = austausch.getRemoteAddress();
		String ipAdresse = senderAdresse.getAddress().toString();
		
//		knecht
		
		Headers kopf = austausch.getResponseHeaders();
		
		OutputStream koerper = austausch.getResponseBody();
		byte[] nachricht = "Frieden!".getBytes();
		austausch.sendResponseHeaders(200, nachricht.length);
		koerper.write(nachricht);
		

	}

}
