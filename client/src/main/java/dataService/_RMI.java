package dataService;

import java.net.InetAddress;

public class _RMI {

	private static String ip = ":8888";
	
	private static void setIP() throws Exception {
		InetAddress address = InetAddress.getLocalHost();
		String serverip = address.getHostAddress();
		ip = serverip + ip;
	}
	
	public static String getIP(){
		try {
			setIP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
}
