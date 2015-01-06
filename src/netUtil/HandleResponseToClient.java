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
	private InputStream isFromTarget;
	private OutputStream osToClient;
	private int isRun = 1;

	public HandleResponseToClient(Socket connectToClient, Socket target) {
		this.connectToClient = connectToClient;
		this.target = target;
	}

	@Override
	public void run() {
		try {
			isFromTarget = target.getInputStream();
			osToClient = connectToClient.getOutputStream();
			byte[] b = new byte[1024];
			int c = 0;
			while (1 == isRun) {
				c = isFromTarget.read(b);
				System.out.println("收到响应了，正在转发:" + b.toString());
				osToClient.write(b, 0, c);
				isRun = csHandle.getIsRun();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeSocketAndIO();
	}

	public void setIsRun(int isRun) {
		this.isRun = isRun;
	}

	public void setCsHandle(CSHandle csHandle) {
		this.csHandle = csHandle;
	}
	
	private void closeSocketAndIO() {
		try {
			isFromTarget.close();
			osToClient.close();
			if (!connectToClient.isClosed()) {
				connectToClient.close();
			}
			if (!target.isClosed()) {
				target.close();
			}
			System.out.println("服务停止，不再接受响应");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
