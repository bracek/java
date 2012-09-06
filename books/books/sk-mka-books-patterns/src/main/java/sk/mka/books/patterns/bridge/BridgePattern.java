package sk.mka.books.patterns.bridge;

/** "Client" */
class BridgePattern {
	public static void main(String[] args) {
		Shape[] shapes = new Shape[] {
				new CircleShape(1, 2, 3, new DrawingAPI1()),
				new CircleShape(5, 7, 11, new DrawingAPI2()), };

		for (Shape shape : shapes) {
			shape.resizeByPercentage(2.5);
			shape.draw();
		}
	}
}