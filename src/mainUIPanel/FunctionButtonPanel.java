package mainUIPanel;

import handle.ItemHandle;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import taskModel.PortTable;

public class FunctionButtonPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JButton startMap;
	public JButton stopMap;
	public JButton removeMap;
	public ItemHandle itemHandle;
	public PortTable portTable;
	public MapAction mapAction;

	public void setjTable(PortTable portTable) {
		this.portTable = portTable;
	}

	public FunctionButtonPanel() {
		super();
		initial();
		placeFunctionButtonPanel();
		// TODO Auto-generated constructor stub
	}

	private void initial() {
		startMap = new JButton("¿ªÊ¼Ó³Éä");
		stopMap = new JButton("Í£Ö¹Ó³Éä");
		removeMap = new JButton("É¾³ýÓ³Éä");
		mapAction = new MapAction();
		startMap.addActionListener(mapAction);
		stopMap.addActionListener(mapAction);
		removeMap.addActionListener(mapAction);
	}

	private void placeFunctionButtonPanel() {
		this.setLayout(new GridLayout(3, 1));
		this.add(startMap);
		this.add(stopMap);
		this.add(removeMap);
	}

	class MapAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == startMap) {
				starPortMap();
			} else if (e.getSource() == stopMap) {
				stopMap();
			} else if (e.getSource() == removeMap) {
				removeMap();
			}
		}

	}

	public void starPortMap() {
		// TODO
		if (-1 != portTable.getSelectedRow()) {
			itemHandle.startMap(portTable.getSelectedRow());
		}
	}

	public void stopMap() {
		// TODO
		if (-1 != portTable.getSelectedRow()) {
			itemHandle.stopMap(portTable.getSelectedRow());
		}
	}

	public void removeMap() {
		// TODO
		if (-1 != portTable.getSelectedRow()) {
			itemHandle.removeItem(portTable.getSelectedRow());
		}
	}

	public void setItemHandel(ItemHandle itemHandle) {
		this.itemHandle = itemHandle;
	}

	public void setPortTable(PortTable portTable) {
		this.portTable = portTable;
	}

}
