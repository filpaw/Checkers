/**
 * Zbi�r warto�ci typ�w pionka, tzn. s� dwa kolory pionka, w zale�no�ci od tego gdzie
 * znajduje sie pionek, przypisuje sie pionkowi CheckerType, czyli kolor
 * @author Filip
 *
 */
public enum CheckerType {

	RED(1), WHITE(-1);

	final int color;

	CheckerType(int color) {
		this.color = color;
	}
}
