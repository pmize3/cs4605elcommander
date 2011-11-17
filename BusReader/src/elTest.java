
public class elTest implements elCommanderI {

	@Override
	public long pinReadAnalog(int pin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean pinReadDigital(int pin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void pinWriteDigital(int pin, boolean value) {
		// TODO Auto-generated method stub
		
		System.out.println("Writing to: " + pin + " Value: " + value);

	}

}
