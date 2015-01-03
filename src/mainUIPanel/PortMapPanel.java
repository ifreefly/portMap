package mainUIPanel;

import handle.ItemHandle;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dataModel.PortMapItem;

public class PortMapPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextField srcIP, desIP, srcPort, desPort;
	public JLabel srcIPLabel, desIPLabel, srcPortLabel, desPortLabel;
	public JButton addMap;
	public ItemHandle itemHandle;

	public ItemHandle getAddNewItemHandle() {
		return itemHandle;
	}

	public void setAddNewItemHandle(ItemHandle addNewItemHandle) {
		this.itemHandle = addNewItemHandle;
	}

	public PortMapPanel() {
		super();
		initial();
		placePanel();
	}

	private void initial() {
		srcIP = new JTextField(5);
		desIP = new JTextField(5);
		srcPort = new JTextField(5);
		desPort = new JTextField(5);
		srcIPLabel = new JLabel("源IP");
		desIPLabel = new JLabel("目的IP");
		srcPortLabel = new JLabel("源端口");
		desPortLabel = new JLabel("目的端口");
		addMap = new JButton("添加");
		addMap.addActionListener(new ItemHandleListener());
	}

	private void placePanel() {
		this.setLayout(new GridLayout(1, 9));
		this.add(srcIPLabel);
		this.add(srcIP);
		this.add(srcPortLabel);
		this.add(srcPort);
		this.add(desIPLabel);
		this.add(desIP);
		this.add(desPortLabel);
		this.add(desPort);
		this.add(addMap);
	}

	public void addPortMap() {
		if (isParamValid()) {
			PortMapItem portMapItem = new PortMapItem();
			portMapItem.setSrcIP(srcIP.getText());
			portMapItem.setSrcPort(srcPort.getText());
			portMapItem.setDesIP(desIP.getText());
			portMapItem.setDesPort(desPort.getText());
			itemHandle.addNewItem(portMapItem);
		}
	}

	private boolean isParamValid() {
		boolean paramValid = isIPValid(srcIP.getText())
				&& isIPValid(desIP.getText()) && isPortValid(srcPort.getText())
				&& isPortValid(desPort.getText());
		
		if(!paramValid)
			return false;
		
		return true;
	}

	private boolean isIPValid(String IP) {
		String ip[] = IP.split("\\.");
		if (4 != ip.length) {
			return false;
		}
		
		if (0 >= Integer.valueOf(ip[0]) || Integer.valueOf(ip[0]) > 255) {
			return false;
		}
		
		for (int i = 1; i < ip.length; i++) {
			if (0 > Integer.valueOf(ip[i]) || Integer.valueOf(ip[i]) > 255) {
				return false;
			}
		}

		return true;
	}

	private boolean isPortValid(String port) {
		return isPortValid(Integer.valueOf(port));
	}

	private boolean isPortValid(int port) {
		if (0 >= port || port > 65535)
			return false;

		return true;
	}

	class ItemHandleListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == addMap) {
				addPortMap();
			}
		}
	}
}
