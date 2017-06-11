import javafx.scene.Group;
import javafx.scene.layout.Pane;

public class GameView extends App {

	private Group fieldGroup = new Group();
	static Group checkerGroup = new Group();
	private Field field = null;
	private static Field[][] fieldTab = new Field[8][8]; // tablica pól planszy
	private static double field_size = 50;
	private static double radius = 15;
	private boolean isServer;

	public GameView(boolean isServer) {
		this.isServer = isServer;
	}

	public Pane createBoard() {
		Pane pane = new Pane();
		pane.setPrefSize(400, 400);
		pane.getChildren().addAll(fieldGroup, checkerGroup);
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				field = new Field((i + j) % 2, i, j);
				fieldTab[i][j] = field;
				fieldGroup.getChildren().add(field);
			}

		CheckerType checkerType;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {

				if ((i % 2 == 1 && j % 2 == 0 && i != 3 && i != 4) || (i % 2 == 0 && j % 2 == 1 && i != 3 && i != 4)) {
					if (isServer) {
						if (i < 3)
							checkerType = CheckerType.RED;
						else
							checkerType = CheckerType.WHITE;
					} else {
						if (i > 4)
							checkerType = CheckerType.RED;
						else
							checkerType = CheckerType.WHITE;

					}
					Checker checker = new Checker(checkerType, i, j, isServer);
					fieldTab[i][j].setChecker(checker);
					checkerGroup.getChildren().add(checker);

				}
			}

		return pane;
	}

	public static void changeChecker(int oldX, int oldY, int newX, int newY, boolean isRelocated) {
		Checker checker = fieldTab[oldX][oldY].getChecker();
		fieldTab[oldX][oldY].setChecker(null);
		fieldTab[newX][newY].setChecker(checker);
		if (isRelocated) {
			checker.relocate(field_size * newY + (field_size / 2 - radius),
					field_size * newX + (field_size / 2 - radius));
		}
	}

	static void removeChecker(int newX, int newY) {
		checkerGroup.getChildren().remove(fieldTab[newX][newY].getChecker());
		fieldTab[newX][newY].setChecker(null);
	}

	static void setKing(int newX, int newY) {
		Checker checker = fieldTab[newX][newY].getChecker();
		checker.setKing();
	}

	public static Field getField(int x, int y) {
		return fieldTab[x][y];
	}
}
