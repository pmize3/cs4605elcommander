import java.util.Enumeration;


public class Main
{

	/**
	 * @author Chris Bayruns
	 * @param args
	 * @throws InterruptedException 
	 */

	public static void main(String[] args) throws InterruptedException//arg 1
	{
		//CCommand temp = new CCommand(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[2]),Integer.parseInt(args[3]));
		//CCommand temp = new CCommand(0,0,1,13);
		//CCommand temp2 = new CCommand(0,0,0,13);
		/*while(true)
		{
			System.out.print((char)temp.genOutChar());
			Thread.sleep(1000);*/
			//System.out.print((char)temp.genOutChar());
			
		//}
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM2");
		elCommander temp = new elCommander("/dev/ttyACM2");
		temp.pinWriteDigital(13, true);
		Thread.sleep(4000);
		temp.pinWriteDigital(13, false);
		//COMPORT.listPorts();
		Thread.sleep(100);
		long bla =  temp.pinReadAnalog(5);
		System.out.printf("ARead: %d\n", bla);
		bla =  temp.pinReadAnalog(4);
		System.out.printf("ARead: %d\n", bla);
	}

}
