package net;

import handle.CSHandle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleClientRequest extends Thread
{
    private Socket connectToClient;
    private Socket target;
    private CSHandle csHandle;
    private InputStream isFromClient;
    private OutputStream osToTarget;

    private boolean isRun = true;

    public HandleClientRequest(Socket connectToClient, Socket target)
    {
        this.connectToClient = connectToClient;
        this.target = target;
    }

    public void run()
    {
        try
        {
            isFromClient = connectToClient.getInputStream();
            osToTarget = target.getOutputStream();
            byte b[] = new byte[1024];
            int c = 0;
            while (isRun())
            {
                c = isFromClient.read(b);
                System.out.println(osToTarget);
                osToTarget.write(b, 0, c);
                System.out.println("write" + String.valueOf(b));
                setRun(csHandle.isRun());
            }

        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        closeSocketAndIO();
    }

    public void setCsHandle(CSHandle csHandle)
    {
        this.csHandle = csHandle;
    }

    private void closeSocketAndIO()
    {
        try
        {
            System.out.println("close io");
            isFromClient.close();
            osToTarget.close();
            if (!connectToClient.isClosed())
            {
                connectToClient.close();
            }
            if (!target.isClosed())
            {
                target.close();
            }
            System.out.println("HandleClientRequest close socket");
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
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
