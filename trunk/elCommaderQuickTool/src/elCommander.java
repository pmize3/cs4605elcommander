import java.io.IOException;


public class elCommander
{
	private COMPORT serial;

	public elCommander( String dev )
	{
		
		serial = new COMPORT(dev);
		serial.flushIn();
	}
	
	public void pinWriteDigital( int pin, boolean value )
	{
		if( pin >= 0 && pin <= 13 )
		{
			CCommand temp = new CCommand(0, 0, (value) ? 1 : 0, pin);
			try
			{
				serial.writeByte(temp.genOutChar());
				System.out.print("Wrote: " + (char)temp.genOutChar() + "\n");
				Thread.sleep(100);
				
				System.out.print("Read: " + serial.read() + "\n");
				//serial.flushIn();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean pinReadDigital( int pin)
	{
		boolean ret = false;
		if( pin >= 0 && pin <= 13 )
		{
			CCommand temp = new CCommand(0, 1, 0, pin);
			
			try
			{
				serial.writeByte(temp.genOutChar());
				System.out.print("Wrote: " + (char)temp.genOutChar() + "\n");
				Thread.sleep(100);
				/*while( serial.bytesAvaliable() <= 0 )
				{
				}*/
				int read = serial.read();
				System.out.print("Read: " + read + "\n");
				ret = (read) == 1 ? true : false;
				//serial.flushIn();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return ret;
	}
	
	public int pinReadAnalog( int pin)
	{
		int ret = 0;
		if( pin >= 0 && pin <= 13 )
		{
			CCommand temp = new CCommand(1, 1, 0, pin);
			
			try
			{
				serial.writeByte(temp.genOutChar());
				System.out.print("Wrote: " + (char)temp.genOutChar() + "\n");
				Thread.sleep(100);
				/*while( serial.bytesAvaliable() <= 0 )
				{
				}*/
				//
				//byte read2 = serial.readByte();
				int read = serial.read();
				ret = read;
				System.out.printf("ARead: %d\n", read);
				//serial.flushIn();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return ret;
	}
}
