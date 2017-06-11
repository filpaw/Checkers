
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.*;

public class Field extends Rectangle {

	public double field_size = 50;
	private int x, y; // koordynaty planszy
	public Checker checker = null;

	public Field(int color, int x, int y) { // if 0-white , if 1-green

		setWidth(field_size);
		setHeight(field_size);

		relocate(y * field_size, x * field_size);

		if (color == 0)
			setFill(Color.valueOf("#feb"));
		else
			setFill(Color.valueOf("#582"));
		setOnMousePressed(e -> {
			// System.out.println(x+","+y);
			System.out.println(this.checker);
		});
		this.x = x;
		this.y = y;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	public Checker getChecker() {
		return this.checker;
	}

	public boolean hasChecker() {
		if (this.checker != null)
			return true;
		else
			return false;
	}

}
