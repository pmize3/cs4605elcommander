import java.util.ArrayList;


public class BusStop {

	ArrayList<PointContainer> busStop = new ArrayList<PointContainer>();
	int port;

	public BusStop(int portNum){
		
		port = portNum;
		
	}
	
	public ArrayList<PointContainer> getBusStop() {
		return busStop;
	}

	public void setBusStop(ArrayList<PointContainer> busStop) {
		this.busStop = busStop;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void addPointContainer(double x1, double y1, double x2, double y2){
	
		busStop.add(new PointContainer(x1, y1, x2, y2));
		
	}
	
	public boolean inBusStop(PointD point){
		
		boolean ret = false;
		
		for( int i = 0; i < busStop.size(); i++){
			
			if(busStop.get(i).isLocation(point)){
				ret = true;
			}
		}
		
		return ret;
	}
	
	public void process(int port){
		
		//Arduino Interface
		
		//temp.pinWriteDigital(port, true);
	}
}
