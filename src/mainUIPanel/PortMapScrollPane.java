package mainUIPanel;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import taskModel.PortTable;

public class PortMapScrollPane extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public PortMapScrollPane(PortTable portTable){
		super(portTable);
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
}
