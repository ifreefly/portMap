package portMap;

import handle.ItemHandler;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import taskModel.PortTable;
import mainUIPanel.FunctionButtonPanel;
import mainUIPanel.PortMapPanel;
import mainUIPanel.PortMapScrollPane;

public class UI
{
    public JFrame frame;
    public PortMapPanel portMapPanel;
    public PortMapScrollPane portMapScrollPane;
    public FunctionButtonPanel functionButtonPanel;
    public PortTable portTable;

    public ItemHandler itemHandle;

    public UI()
    {
        initial();
        frame.setLayout(new BorderLayout());
        frame.add(portMapPanel, BorderLayout.NORTH);
        frame.add(functionButtonPanel, BorderLayout.EAST);
        frame.add(portMapScrollPane, BorderLayout.CENTER);
        frame.setSize(600, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initial()
    {
        frame = new JFrame("PortMap");
        portMapPanel = new PortMapPanel();
        functionButtonPanel = new FunctionButtonPanel();
        portTable = new PortTable();
        portMapScrollPane = new PortMapScrollPane(portTable);
        itemHandle = new ItemHandler(PortTable.portItemTableModel);

        portMapPanel.setAddNewItemHandle(itemHandle);
        functionButtonPanel.setItemHandel(itemHandle);
        functionButtonPanel.setPortTable(portTable);
    }
}
