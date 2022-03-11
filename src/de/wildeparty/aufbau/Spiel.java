package de.wildeparty.aufbau;

import java.util.Arrays;

import de.wildeparty.aufbau.backend.User;
import de.wildeparty.snapshot.Snapshot;

/**
 * Das Spielobjekt. Die Darstellung eines Spiels in Java. Enthält das
 * Spiel-"Snapshot", also die aktuelle State des Spiels
 * 
 * 
 * @author nick
 *
 */

public class Spiel {

	/**
	 * Die einmalige ID des Spiels.
	 */
	private long id;

	/**
	 * Spielstatus ist ein ENUM: Ein spiel kann den Status warten, aktiv oder
	 * beendet haben.
	 */

	private User[] spieler;

	/**
	 * Die Zonen im Spiel, die Karten enthalten können, jedoch keinem Spieler
	 * zugewiesen werden: Das sind das Kartendeck und der Ablagestapel.
	 */

	private SpielStatus status;

	/**
	 * User, die sich bei diesem Spiel angemeldet haben. Die länge des Spielerarrays
	 * wird vom User festgelegt, der das Spielobjekt anlegt. So könnte z.B. ein User
	 * ein Spiel für 3 Spieler anlegen. Das Spiel geht dann erst los, wenn alle 3
	 * freien Plätze von Usern belegt sind.
	 * 
	 * Oder, (später, nicht in dieser Version) AI-Spieler, die diesem Spiel
	 * zugewiesen wurden.
	 */

	private Snapshot snapshot;

	@Override
	public String toString() {
		return "Spiel [id=" + id + ", spieler=" + Arrays.toString(spieler) + ", status=" + status + ", snapshot="
				+ snapshot + "]";
	}


	public Spiel(User[] spieler, SpielStatus status, Snapshot snapshot) {
		this.status = status;
		this.spieler = spieler;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SpielStatus getStatus() {
		return status;
	}

	public void setStatus(SpielStatus status) {
		this.status = status;
	}

	public User[] getSpieler() {
		return spieler;
	}

	public void setSpieler(User[] spieler) {
		this.spieler = spieler;
	}


}
