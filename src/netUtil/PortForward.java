package netUtil;

import handle.CSHandle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import dataModel.PortMapItem;

public class PortForward implements Runnable {

	private PortMapItem portMapItem;
	private ServerSocket srcSocket;
	private int isRun = 1;
	private CSHandle csHandle;
	
	public PortForward(PortMapItem portMapItem) {
		this.portMapItem = portMapItem;
		csHandle=new CSHandle();
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
				handleClientRequest.setCsHandle(csHandle);
				HandleResponseToClient handleResponseToClient = new HandleResponseToClient(
						socket, target);
				handleResponseToClient.setCsHandle(csHandle);
				handleClientRequest.start();
				handleResponseToClient.start();
				countClient++;
				System.out.println("当前连接的客户端有" + countClient + "个");

			}
			
			srcSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println(e.getMessage());
			//System.out.println("映射已被关闭");
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
			csHandle.setIsRun(isRun);
			if (!srcSocket.isClosed()) {
				System.out.println(srcSocket.isClosed());
				srcSocket.close();
			}
			System.out.println("Map has been stoped");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
