package handle;

import java.util.HashMap;
import java.util.Map;

import netUtil.PortForward;
import dataModel.PortMapItem;
import taskModel.PortItemTableModel;
import util.HashEncrypt;

public class ItemHandle {
	public PortItemTableModel portItemTableModel;
	public Map<String, PortForward> portMap;

	private void initial() {
		portMap = new HashMap<String, PortForward>();
	}

	public ItemHandle(PortItemTableModel portItemTableModel) {
		initial();
		this.portItemTableModel = portItemTableModel;
	}

	public void addNewItem(PortMapItem portMapItem) {
		portItemTableModel.addNewPortMap(portMapItem);
	}

	public void removeItem(int index) {
		stopMap(index);
		portItemTableModel.removePortMap(index);
	}

	public void startMap(int index) {
		PortMapItem portMapItem = portItemTableModel.getPortMapItem(index);
		PortForward portForward = portMap.get(caculateHash(portMapItem));
		if (null == portForward) {
			portForward = new PortForward(portMapItem);
			portMap.put(caculateHash(portMapItem), portForward);
			Thread t = new Thread(portForward);
			t.start();
		}
	}

	public void stopMap(int index) {
		PortMapItem portMapItem = portItemTableModel.getPortMapItem(index);
		PortForward portForward = portMap.get(caculateHash(portMapItem));
		if (null != portForward) {
			// 确保该映射存在
			portForward.stopMap();
			portMap.remove(caculateHash(portMapItem));
		}
	}

	private String caculateHash(PortMapItem portMapItem) {
		return HashEncrypt.MD5Hash(portMapItem.getSrcIP()
				+ String.valueOf(portMapItem.getSrcPort())
				+ portMapItem.getDesIP()
				+ String.valueOf(portMapItem.getDesPort()));
	}
}
