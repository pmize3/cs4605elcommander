public class Bus {

	BusStop prevStop;
	PointD currentPoint;
	int id;
	int color;

	private static final int RED = 0;
	private static final int BLUE = 1;

	public Bus(int inID, int inColor) {

		id = inID;
		color = inColor;
	}

	
	
	public PointD getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(PointD currentPoint) {
		this.currentPoint = currentPoint;
	}

	public BusStop getPrevStop() {
		return prevStop;
	}

	public void setPrevStop(BusStop prevStop) {
		this.prevStop = prevStop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

}
