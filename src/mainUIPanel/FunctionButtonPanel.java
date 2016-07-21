package mainUIPanel;

import handle.ItemHandler;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import taskModel.PortTable;

public class FunctionButtonPanel extends JPanel
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public JButton startMapBtn;
    public JButton stopMapBtn;
    public JButton removeMapBtn;
    public ItemHandler itemHandler;
    public PortTable portTable;
    public MapAction mapAction;

    public void setjTable(PortTable portTable)
    {
        this.portTable = portTable;
    }

    public FunctionButtonPanel()
    {
        super();
        initial();
        placeFunctionButtonPanel();
    }

    private void initial()
    {
        startMapBtn = new JButton("start");
        stopMapBtn = new JButton("ֹͣstop");
        removeMapBtn = new JButton("remove");
        mapAction = new MapAction();
        startMapBtn.addActionListener(mapAction);
        stopMapBtn.addActionListener(mapAction);
        removeMapBtn.addActionListener(mapAction);
    }

    private void placeFunctionButtonPanel()
    {
        this.setLayout(new GridLayout(3, 1));
        this.add(startMapBtn);
        this.add(stopMapBtn);
        this.add(removeMapBtn);
    }

    class MapAction implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == startMapBtn)
            {
                starPortMap();
            } else if (e.getSource() == stopMapBtn)
            {
                stopMap();
            } else if (e.getSource() == removeMapBtn)
            {
                removeMap();
            }
        }

    }

    public void starPortMap()
    {
        if (-1 != portTable.getSelectedRow())
        {
            itemHandler.startMap(portTable.getSelectedRow());
        }
    }

    public void stopMap()
    {
        if (-1 != portTable.getSelectedRow())
        {
            itemHandler.stopMap(portTable.getSelectedRow());
        }
    }

    public void removeMap()
    {
        if (-1 != portTable.getSelectedRow())
        {
            itemHandler.removeItem(portTable.getSelectedRow());
        }
    }

    public void setItemHandel(ItemHandler itemHandle)
    {
        this.itemHandler = itemHandle;
    }

    public void setPortTable(PortTable portTable)
    {
        this.portTable = portTable;
    }

}
