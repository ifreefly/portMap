package util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class LocalNetwork
{
    public static List<String> getLocalIPs()
    {
        List<String> IPList = new ArrayList<String>();
        try
        {
            Enumeration<NetworkInterface> NIFs = NetworkInterface.getNetworkInterfaces();
            for (; NIFs.hasMoreElements();)
            {
                NetworkInterface nif = NIFs.nextElement();
                for (Enumeration<InetAddress> IPs = nif.getInetAddresses(); IPs.hasMoreElements();)
                {
                    InetAddress ip = IPs.nextElement();
                    IPList.add(ip.getHostAddress());
                }
            }
        } catch (SocketException e)
        {
            e.printStackTrace();
        }

        return IPList;
    }

}
