package qrmanager.dev;

/** QRReaderListener
 * 
 * @author Roberto Fernandez 
 *
 * This interface is used to get asynchronous QR read events
 * from a QRReaderSerialPort instance.
 */
public interface QRReaderListener {
	public void qrDataReceived(String qr);
}