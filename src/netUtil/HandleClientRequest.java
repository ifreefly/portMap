package netUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleClientRequest extends Thread {
	private Socket connectToClient;
	private Socket target;
	private int isRun = 1;

	public HandleClientRequest(Socket connectToClient, Socket target) {
		this.connectToClient = connectToClient;
		this.target = target;
	}

	public void run() {
		try {
			InputStream isFromClient = connectToClient.getInputStream();
			OutputStream osToTarget = target.getOutputStream();
			byte b[] = new byte[1024];
			int c = 0;
			while (1 == isRun) {
				c = isFromClient.read(b);
				System.out.println(osToTarget);
				osToTarget.write(b, 0, c);
//				osToTarget.flush();
				System.out.println("收到请求正在转发,内容是：" + b);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setIsRun(int isRun) {
		this.isRun = isRun;
	}
}
