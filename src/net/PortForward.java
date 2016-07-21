package net;

import handle.CSHandle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import dataModel.PortMapItem;

public class PortForward implements Runnable
{

    private PortMapItem portMapItem;
    private ServerSocket srcSocket;
    private boolean isRun = true;
    private CSHandle csHandle;

    public PortForward(PortMapItem portMapItem)
    {
        this.portMapItem = portMapItem;
        csHandle = new CSHandle();
        initServerSocket();
    }

    @Override
    public void run()
    {
        System.out.println("map Started....");
        int countClient = 0;
        try
        {
            while (isRun())
            {
                Socket socket = srcSocket.accept();
                Socket target = new Socket(portMapItem.getDesIP(), portMapItem.getDesPort());
                HandleClientRequest handleClientRequest = new HandleClientRequest(socket, target);
                handleClientRequest.setCsHandle(csHandle);
                HandleResponseToClient handleResponseToClient = new HandleResponseToClient(socket, target);
                handleResponseToClient.setCsHandle(csHandle);
                handleClientRequest.start();
                handleResponseToClient.start();
//                countClient++;
//                System.out.println("client is " + countClient);

            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println("Map has been stoped");
        }
    }

    private void initServerSocket()
    {
        try
        {
            srcSocket = new ServerSocket(portMapItem.getSrcPort(), 0, InetAddress.getByName(portMapItem.getSrcIP()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void stopMap()
    {
        try
        {
            setRun(false);
            csHandle.setRun(isRun());
            if (!srcSocket.isClosed())
            {
                System.out.println(srcSocket.isClosed());
                srcSocket.close();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public boolean isRun()
    {
        return isRun;
    }

    public void setRun(boolean isRun)
    {
        this.isRun = isRun;
    }
}
