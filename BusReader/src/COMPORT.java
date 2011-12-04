import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.*;
import java.util.*;


public class COMPORT  implements SerialPortEventListener
{
    private CommPortIdentifier portId;
    private Enumeration portList;
    private InputStream in;
    private OutputStream out;
    private boolean data;
    private byte[] chunk;
    public COMPORT( String dev )
    {
    	try
		{
			connect ( dev );
			//this.flushIn();
			data = false;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void connect ( String portName ) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            System.out.println("Error: Port is currently in use");
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            
            if ( commPort instanceof SerialPort )
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                
                in = serialPort.getInputStream();
                out =  serialPort.getOutputStream();
                //serialPort.addEventListener(this);
    			//serialPort.notifyOnDataAvailable(true);
    			//serialPort.
            }
            else
            {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }     
    }
    
    public int bytesAvaliable() throws IOException
    {
    	return in.available();
    }
    
    public void writeByte( byte data ) throws IOException
    {
    	//out.write(0xfb);
    	chunk = null;
    	out.write(data);
    	out.flush();
    	//out.flush();
    }
    
    public long read() throws IOException, InterruptedException
    {
    	//try{
    	long ret = 0;
    	String temp = "";
    	while( in.available() <2 )
    	{
    		Thread.sleep(10);
    	}
    	/*for(byte i : chunk )
    	{
    		temp += (char)i;
    	}*/
    	byte one = (byte)in.read();
    	byte two = (byte)in.read();
    	System.out.printf("Byte one: %x, Byte Two: %x\n", one, two);
    	ret = one & 0x000000ff;
    	System.out.printf("Ret Now: %d\n", ret);
    	ret += two * 256;
    	System.out.printf("Ret Now: %d\n", ret);
    	chunk = null;
    	data = false;
    	//System.out.println("IHAS STRING: " + temp);
    	return ret;//Integer.parseInt(temp);
    	
    	/*}
    	catch(Exception e){return 0;}*/
    }
    
    static void listPorts()
    {
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while ( portEnum.hasMoreElements() ) 
        {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            System.out.println(portIdentifier.getName()  +  " - " +  getPortTypeName(portIdentifier.getPortType()) );
        }        
    }
    
    static String getPortTypeName ( int portType )
    {
        switch ( portType )
        {
            case CommPortIdentifier.PORT_I2C:
                return "I2C";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Parallel";
            case CommPortIdentifier.PORT_RAW:
                return "Raw";
            case CommPortIdentifier.PORT_RS485:
                return "RS485";
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            default:
                return "unknown type";
        }
    }

	@Override
	public void serialEvent(SerialPortEvent arg0)
	{
		//System.out.print("lol");
		if (arg0.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int available = in.available();
				chunk = new byte[available];
				in.read(chunk, 0, available);
				data = true;
				// Displayed results are codepage dependent
				//System.out.print( new String(chunk));
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		
	}
}
