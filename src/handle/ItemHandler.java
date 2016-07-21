package handle;

import java.util.HashMap;
import java.util.Map;

import dataModel.PortMapItem;
import net.PortForward;
import taskModel.PortItemTableModel;

public class ItemHandler
{
    public PortItemTableModel portItemTableModel;
    public Map<PortMapItem, PortForward> portMap;

    private void initial()
    {
        portMap = new HashMap<PortMapItem, PortForward>();
    }

    public ItemHandler(PortItemTableModel portItemTableModel)
    {
        initial();
        this.portItemTableModel = portItemTableModel;
    }

    public void addNewItem(PortMapItem portMapItem)
    {
        portItemTableModel.addNewPortMap(portMapItem);
    }

    public void removeItem(int index)
    {
        stopMap(index);
        portItemTableModel.removePortMap(index);
    }

    public void startMap(int index)
    {
        PortMapItem portMapItem = portItemTableModel.getPortMapItem(index);
        PortForward portForward = portMap.get(portMapItem);
        if (null == portForward)
        {
            portForward = new PortForward(portMapItem);
            portMap.put(portMapItem, portForward);
            Thread t = new Thread(portForward);
            t.start();
        }
    }

    public void stopMap(int index)
    {
        PortMapItem portMapItem = portItemTableModel.getPortMapItem(index);
        PortForward portForward = portMap.get(portMapItem);
        if (null != portForward)
        {
            portForward.stopMap();
            portMap.remove(portMapItem);
        }
    }
}
