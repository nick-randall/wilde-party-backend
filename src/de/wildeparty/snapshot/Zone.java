package de.wildeparty.snapshot;

import java.util.List;

/**
 * Eine Zone ist ein Behälter für Karten. Karten existieren immer nur in Zonen.
 * Typischerweise werden Karten aus einer Zone mit Typ ZoneTyp.HAND des
 * jeweiligen Spielers gespielt. Danach werden sie in die neue Zone geschickt
 */
public class Zone {

	/**
	 * Die einmalige id der Zone;
	 */
	private long zoneId;

	/**
	 * Diese Zonentypen können nur bestimmte Karten enthalten: GUESTS,ENCHANTMENTS,
	 * UNWANTEDS, Diese Zonentypen können alle Arten von Karten enthalten: HAND,
	 * DISCARD, DECK
	 */

	private final ZoneTyp typ;

	/**
	 * Nur Karten dieses Types dürfen in diese Zone verschoben werden:
	 */

	private final KartenTyp akzeptierterKartentyp;

	/** Die Karten, die diese Zone aktuell enthält */

	private List<Karte> karten;

	public Zone(ZoneTyp typ, KartenTyp akzeptierterKartentyp, List<Karte> karten) {
		super();
		this.typ = typ;
		this.akzeptierterKartentyp = akzeptierterKartentyp;
		this.karten = karten;
	}

	public long getZoneId() {
		return zoneId;
	}

	public void setZoneId(long zoneId) {
		this.zoneId = zoneId;
	}

	public List<Karte> getKarten() {
		return karten;
	}

	public void setKarten(List<Karte> karten) {
		this.karten = karten;
	}

	public ZoneTyp getTyp() {
		return typ;
	}

	public KartenTyp getAkzeptierterKartentyp() {
		return akzeptierterKartentyp;
	}

}
