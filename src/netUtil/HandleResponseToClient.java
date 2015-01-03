package netUtil;

import handle.CSHandle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleResponseToClient extends Thread {
	private Socket connectToClient;
	private Socket target;
	private CSHandle csHandle;
	private int isRun = 1;

	public HandleResponseToClient(Socket connectToClient, Socket target) {
		this.connectToClient = connectToClient;
		this.target = target;
	}

	@Override
	public void run() {
		try {
			InputStream isFromTarget = target.getInputStream();
			OutputStream osToClient = connectToClient.getOutputStream();
			byte [] b=new byte[1024];
			int c=0;
			while (1 == isRun) {
				c = isFromTarget.read(b);
				System.out.println("�յ���Ӧ�ˣ�����ת��:" + b.toString());
				osToClient.write(b, 0, c);
				isRun=csHandle.getIsRun();
			}
			isFromTarget.close();
			osToClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setIsRun(int isRun) {
		this.isRun = isRun;
	}
	
	public void setCsHandle(CSHandle csHandle) {
		this.csHandle = csHandle;
	}
}
