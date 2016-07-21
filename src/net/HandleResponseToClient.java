package net;

import handle.CSHandle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleResponseToClient extends Thread
{
    private Socket connectToClient;
    private Socket target;
    private CSHandle csHandle;
    private InputStream inputStream;
    private OutputStream osToClient;
    private boolean isRun = true;

    public HandleResponseToClient(Socket connectToClient, Socket target)
    {
        this.connectToClient = connectToClient;
        this.target = target;
    }

    @Override
    public void run()
    {
        try
        {
            inputStream = target.getInputStream();
            osToClient = connectToClient.getOutputStream();
            byte[] b = new byte[1024];
            int c = 0;
            while (isRun())
            {
                c = inputStream.read(b);
                System.out.println("read:" + b.toString());
                osToClient.write(b, 0, c);
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
            inputStream.close();
            osToClient.close();
            if (!connectToClient.isClosed())
            {
                connectToClient.close();
            }
            if (!target.isClosed())
            {
                target.close();
            }
            System.out.println("����ֹͣ�����ٽ�����Ӧ");
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
