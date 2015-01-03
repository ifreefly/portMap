package netUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HandleResponseToClient extends Thread {
	private Socket connectToClient;
	private Socket target;
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
			// System.out.println("发生了什么");
			byte [] b=new byte[1024];
			int c=0;
			while (1 == isRun) {
				c = isFromTarget.read(b);
				System.out.println("收到响应了，正在转发:" + b);
				osToClient.write(b, 0, c);
//				osToClient.flush();
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
