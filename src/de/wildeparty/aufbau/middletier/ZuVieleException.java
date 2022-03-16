package de.wildeparty.aufbau.middletier;

public class ZuVieleException extends Exception {
	public ZuVieleException(String nachricht) {
		System.out.println(nachricht);
	}
}
