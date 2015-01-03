package netUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import dataModel.PortMapItem;

public class PortForward implements Runnable {

	private PortMapItem portMapItem;
	private ServerSocket srcSocket;
	static int isRun = 1;

	public PortForward(PortMapItem portMapItem) {
		this.portMapItem = portMapItem;
		initServerSocket();
	}

	@Override
	public void run() {
		System.out.println("map Started....");
		int countClient = 0;
		try {
			while (isRun == 1) {
				Socket socket = srcSocket.accept();
				Socket target = new Socket(portMapItem.getDesIP(),
						portMapItem.getDesPort());
				
				HandleClientRequest handleClientRequest = new HandleClientRequest(
						socket, target);
				HandleResponseToClient handleResponseToClient = new HandleResponseToClient(
						socket, target);
				handleClientRequest.start();
				handleResponseToClient.start();
				countClient++;
				System.out.println("当前连接的客户端有" + countClient + "个");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("映射已被关闭");
		}
	}

	private void initServerSocket() {
		try {
			srcSocket = new ServerSocket(portMapItem.getSrcPort(), 0,
					InetAddress.getByName(portMapItem.getSrcIP()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopMap() {
		try {
			isRun = 0;
			if (!srcSocket.isClosed()) {
				System.out.println(srcSocket.isClosed());
				srcSocket.close();
			}
			System.out.println("Map has been stoped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println("异常是这里打印的");
		}

	}
}
