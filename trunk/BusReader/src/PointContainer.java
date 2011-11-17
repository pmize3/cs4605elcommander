
public class PointContainer {

	private PointD top;
	private PointD bottom;

	public PointContainer(double x1, double y1, double x2, double y2) {

		top = new PointD(x1, y1);
		bottom = new PointD(x2, y2);

	}

	public boolean isLocation(PointD inPoint) {
		boolean ret = true;

		if (!(inPoint.getX() > bottom.getX())) {

			ret = false;
			//System.out.println("inPoint:" + inPoint.getX());
			//System.out.println("bottom:" + bottom.getX());
			System.out.println("Fail 1");

		}

		if (!(inPoint.getY() > bottom.getY())) {

			ret = false;
			System.out.println("Fail 2");
		}

		if (!(inPoint.getX() < top.getX())) {

			ret = false;
			System.out.println("Fail 3");
		}

		if (!(inPoint.getY() < top.getY())) {
			System.out.printf("inPoint:%f >\n" +
							  "    top:%f\n", inPoint.getY(),top.getY());
			ret = false;
			System.out.println("Fail 4");
		}
		
		return ret;

	}

}
