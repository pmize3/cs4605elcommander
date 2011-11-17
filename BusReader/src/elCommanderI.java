
public interface elCommanderI {

	public long pinReadAnalog( int pin);
	
	public boolean pinReadDigital( int pin);
	
	public void pinWriteDigital( int pin, boolean value );
	
}
