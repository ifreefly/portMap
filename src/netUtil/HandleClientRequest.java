package netUtil;

import handle.CSHandle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleClientRequest extends Thread {
	private Socket connectToClient;
	private Socket target;
	private CSHandle csHandle;
	private InputStream isFromClient;
	private OutputStream osToTarget;

	private int isRun = 1;

	public HandleClientRequest(Socket connectToClient, Socket target) {
		this.connectToClient = connectToClient;
		this.target = target;
	}

	public void run() {
		try {
			isFromClient = connectToClient.getInputStream();
			osToTarget = target.getOutputStream();
			byte b[] = new byte[1024];
			int c = 0;
			while (1 == isRun) {
				c = isFromClient.read(b);
				System.out.println(osToTarget);
				osToTarget.write(b, 0, c);
				System.out.println("收到请求正在转发,内容是：" + String.valueOf(b));
				isRun = csHandle.getIsRun();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSocket();
	}

	public void setIsRun(int isRun) {
		this.isRun = isRun;
	}

	public void setCsHandle(CSHandle csHandle) {
		this.csHandle = csHandle;
	}

	private void closeSocketAndIO() {
		try {
			System.out.println("正在关闭连接");
			isFromClient.close();
			osToTarget.close();
			if (!connectToClient.isClosed()) {
				connectToClient.close();
			}
			if (!target.isClosed()) {
				target.close();
			}
			System.out.println("HandleClientRequest close socket");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
