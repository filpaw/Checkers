
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;
/**
 * Klasa opisuje ksztalt, kolor pola oraz rozmiar pola jakie wystepuje na planszy
 * @author Filip
 *
 */
public class Field extends Rectangle {

	public double field_size = 50;
	public Checker checker = null;

	/**
	 * Konstruktor pola ustawia wymiary tego pola na podstawie parametrów
	 * oraz jego kolor
	 * Konstruktor posiada równie¿ funkcjonalnoœc wypisujaca na ekran checkera
	 * @param color
	 * @param x
	 * @param y
	 */
	public Field(int color, int x, int y) { // if 0-white , if 1-green

		setWidth(field_size);
		setHeight(field_size);

		relocate(y * field_size, x * field_size);

		if (color == 0)
			setFill(Color.valueOf("#feb"));
		else
			setFill(Color.valueOf("#582"));
		setOnMousePressed(e -> {
			System.out.println(this.checker);
		});
	}

	/**
	 * Metoda ustawia pionek w polu
	 * @param checker
	 */
	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	/**
	 * Metoda zwraca pionek z tego pola
	 * @return
	 */
	public Checker getChecker() {
		return this.checker;
	}

	/**
	 * Metoda sprawdza czy na polu znajduje sie pionek
	 * @return
	 */
	public boolean hasChecker() {
		if (this.checker != null)
			return true;
		else
			return false;
	}

}
