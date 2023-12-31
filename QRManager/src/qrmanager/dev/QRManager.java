/**
 * 
 */
package qrmanager.dev;

/**
 * @author Roberto Fernandez 
 *
 */
public class QRManager implements SerialPortListener{
	private RxTxSerialPort myQRReader;
	private DigitalIOInterface myDio;
	private int doChannel;
	
	/** Constructor 
	 * @throws Exception */
	QRManager(String portName, int speed, int doChannel) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QRManager: portName = " + portName + ", speed = " + speed + ", doChannel = " + doChannel);
		
		/** Create RxTxReaderSerialPort */
		setMyQRReader(new RxTxSerialPort(portName, speed, this));
		System.out.println("QR reader serial port interface created");
		
		// Save the digital output channel bound to this QRManager instance
		this.doChannel = doChannel; 
		
		/** Get digital output interface singleton instance */
		setMyDio(DigitalSerialIOImpl.getInstance());
		System.out.println("Digital output interface created");
	}

	/** This listener method is invoked by our QRReaderSerialPort
	 *  instance when a new QR has been read from serial port 
	 */
	public void dataReceived(String qr) {
		// TODO Auto-generated method stub
		System.out.println(" <<<<" + this + " qr read = " + qr);
		
		// Validate if QR matches Anahuac JSON structure
		
		// If QR valid, trigger momentary corresponding DO output
		// in order to open lane barrier
		this.getMyDio().setOutputOn(doChannel); 
		try {
			Thread.sleep(1000); // TODO: take from configuration
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.getMyDio().setOutputOff(doChannel);
	}

	public void setMyDio(DigitalIOInterface dio) {
		this.myDio = dio;
	}
	
	public DigitalIOInterface getMyDio() {
		return myDio;
	}

	public void setMyQRReader(RxTxSerialPort myQRReader) {
		this.myQRReader = myQRReader;
	}
	
	public RxTxSerialPort getMyQRReader() {
		return myQRReader;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// QR Reader in serial COM3, digital output channel is 2
			QRManager qrm1 = new QRManager("COM3", 115200, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("QRMManager with parameters portName = COM3, speed = 115200 and doChannel = 2 created");

		try {
			// QR Reader in serial COM4, digital output channel is 3
			QRManager qrm2 = new QRManager("COM6", 115200, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("QRMManager with parameters portName = COM6, speed = 115200 and doChannel = 3 created");
		
		try {
			// QR Reader in serial COM8, digital output channel is 4
			QRManager qrm2 = new QRManager("COM8", 115200, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("QRMManager with parameters portName = COM8, speed = 115200 and doChannel = 4 created");		
	}
}
