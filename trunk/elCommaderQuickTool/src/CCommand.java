
public class CCommand
{
	private int analog;
	private int dataDirection;
	private int val;
	private int index;
	
	public CCommand( int analogIn, int dataDirectionIn, int valIn, int indexIn )
	{
		analog = analogIn;
		dataDirection = dataDirectionIn;
		val = valIn;
		index = indexIn;
	}
	
	public byte genOutChar()
	{
		byte ret = 0x0;
		ret = (byte) ((analog & 0x1) << 7);
		ret += ((byte) ((dataDirection & 0x1) << 6));
		ret += ((byte) ((val & 0x1) << 5));
		ret += ((byte) (( index & 0x1f)));
		return ret;
	}
}
