/**
 * 
 */
package qrmanager.dev;

/**
 * @author Roberto Fernandez 
 *
 */
public class QRManager implements QRReaderListener{
	private QRReaderSerialPort myQRReader;
	private DigitalIOInterface myDio;
	
	/** Constructor 
	 * @throws Exception */
	QRManager(String portName, int speed, int doChannel) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("QRManager: portName = " + portName + ", speed = " + speed + ", doChannel = " + doChannel);
		
		/** Create QRReaderSerialPort */
		setMyQRReader(new QRReaderSerialPort(portName, speed, this));
		System.out.println("QR reader serial port interface created");
		
		/** Create digital output interface */
		setMyDio(new DigitalIOImpl(doChannel));
		System.out.println("Digital output interface created");
	}

	/** This listener method is invoked by our QRReaderSerialPort
	 *  instance when a new QR has been read from serial port 
	 */
	public void qrDataReceived(String qr) {
		// TODO Auto-generated method stub
		System.out.println(" <<<<" + this + " qr read = " + qr);
		
		// Validate if QR matches Anahuac JSON structure
		
		// If QR valid, trigger momentary corresponding DO output
		// in order to open lane barrier
	}

	public void setMyDio(DigitalIOInterface dio) {
		this.myDio = dio;
	}
	
	public DigitalIOInterface getMyDio() {
		return myDio;
	}

	public void setMyQRReader(QRReaderSerialPort myQRReader) {
		this.myQRReader = myQRReader;
	}
	
	public QRReaderSerialPort getMyQRReader() {
		return myQRReader;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			QRManager qrm1 = new QRManager("COM3", 115200, 3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("QRMManager with parameters portName = COM3, speed = 115200 and doChannel = 3 created");

		try {
			// QRManager qrm2 = new QRManager("COM4", 115200, 4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println("QRMManager with parameters portName = COM4, speed = 115200 and doChannel = 4 created");
	}


}