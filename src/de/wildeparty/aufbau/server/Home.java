package de.wildeparty.aufbau.server;
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

public class Home extends HandlerMitCors {

	@Override
	public void handle(HttpExchange austausch) throws IOException {
		super.handle(austausch);
		OutputStream koerper = austausch.getResponseBody();
		koerper.write(999);
		austausch.sendResponseHeaders(200, 0);
		
	}

}
