package dataModel;

public class PortMapItem {
	private String srcIP,desIP;
	private int srcPort,desPort;
	
	public PortMapItem() {
		initial();
	}
	
	private void initial() {
		setSrcIP("None");
		setDesIP("None");
		setSrcPort("0");
		setDesPort("0");
	}
	public String getSrcIP() {
		return srcIP;
	}
	public void setSrcIP(String srcIP) {
		this.srcIP = srcIP;
	}
	public String getDesIP() {
		return desIP;
	}
	public void setDesIP(String desIP) {
		this.desIP = desIP;
	}
	public int getSrcPort(){
		return srcPort; 
	}
	public void setSrcPort(String srcPort) {
		setSrcPort(Integer.valueOf(srcPort));
	}
	public void setSrcPort(int srcPort){
		this.srcPort = srcPort;
	}
	public int getDesPort() {
		return desPort;
	}
	public void setDesPort(String desPort) {
		this.desPort = Integer.valueOf(desPort);
	}
	public void setDesPort(int desPort) {
		this.desPort = desPort;
	}
}
