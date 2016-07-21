package taskModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

import dataModel.PortMapItem;

public class PortItemTableModel extends AbstractTableModel
{
    private static final long serialVersionUID = 1L;

    protected String headers[] = { "srcIP", "desIP", "srcPort", "desPort" };
    protected List<PortMapItem> portMapItemList;
    protected Map<String, PortMapItem> mapLookUp;

    public PortItemTableModel()
    {
        initial();
    }

    private void initial()
    {
        portMapItemList = new ArrayList<PortMapItem>();
        mapLookUp = new HashMap<String, PortMapItem>();
    }

    public void addNewPortMap(PortMapItem portMapItem)
    {
        portMapItemList.add(portMapItem);
        mapLookUp.put(portMapItem.getSrcIP() + String.valueOf(portMapItem.getSrcPort()) + portMapItem.getDesIP()
                + String.valueOf(portMapItem.getDesPort()), portMapItem);
        fireTableRowsInserted(0, 0);
    }

    public void removePortMap(int index)
    {
        portMapItemList.remove(index);
        fireTableRowsDeleted(0, 0);
    }

    public Object getValueAt(int rowIndex, int coloumnIndex)
    {
        Object value = "None";

        switch (coloumnIndex)
        {
        case 0:
            value = this.portMapItemList.get(rowIndex).getSrcIP();
            break;
        case 1:
            value = this.portMapItemList.get(rowIndex).getSrcPort();
            break;
        case 2:
            value = this.portMapItemList.get(rowIndex).getDesIP();
            break;
        case 3:
            value = this.portMapItemList.get(rowIndex).getDesPort();
            break;
        }

        return value;
    }

    @Override
    public String getColumnName(int coloumn)
    {
        return headers[coloumn];
    }

    @Override
    public int getRowCount()
    {
        // TODO Auto-generated method stub
        return portMapItemList.size();
    }

    @Override
    public int getColumnCount()
    {
        // TODO Auto-generated method stub
        return this.headers.length;
    }

    public PortMapItem getPortMapItem(int index)
    {
        return portMapItemList.get(index);
    }

}
