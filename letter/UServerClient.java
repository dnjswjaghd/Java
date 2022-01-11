import java.io.*;
import java.net.*;
import java.util.*;

class UServerClient extends Thread {
	String ip = "127.0.0.1"; //비대면학생: 115.91.81.106, 대면:192.168.0.143
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	int port = 7000;
	FileReader fr;
	BufferedReader brFile;
	String str;
	Vector<String> vName = new Vector<String>();
	Vector<String> vIp = new Vector<String>();

	UServerClient(){
		init();
	}
	void init(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		inputIp();
		inputFile();

		start();
		try{
			Thread.sleep(100);
		}catch(InterruptedException ie){}

		try{
			ds = new DatagramSocket();
			while(true){
				//p("전달할메세지: ");
				String msg = br.readLine();
				msg = msg.trim();
				byte[] buf = msg.getBytes();
				InetAddress ia = InetAddress.getByName(ip);
				dp = new DatagramPacket(buf, buf.length, ia, port);
				ds.send(dp);
				//pln("전송완료!!");
			}
		}catch(SocketException se){
		}catch(UnknownHostException ue){
			pln("네트웍상에 해당서버("+ip+")를 찾을 수 없음");
			init();
		}catch(IOException ie){
		}finally{
			if(ds != null) ds.close();
		}
	}
	
	void inputFile(){
		try{
			fr = new FileReader("ips.txt");
			brFile = new BufferedReader(fr);
		}catch(IOException ie){
			System.out.println("파일을 찾을 수 없음");
			inputFile();
		}
	}
	void inputIp(){
		try{
			p("IP(기본:127.0.0.1): ");
			ip = br.readLine();
			ip = ip.trim();
			//if(ip.length() == 0) ip="localhost";
			if(ip.length() == 0) ip="192.168.0.143";
		}catch(IOException ie){}
	}
	String nName;
	public void run(){
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		
		try{
			ds = new DatagramSocket(port);
			pln(port+"번에서 UDP서버 대기중..");
			byte[] buf = new byte[2048];
			dp = new DatagramPacket(buf, buf.length);
			

			while(true){
				ds.receive(dp);
				InetAddress ia = dp.getAddress();
				String ipClient = ia.getHostAddress();
				changeItoN();
				for(int i=0; i<vIp.size(); i++){
					if(ipClient.equals(vIp.get(i))){
						if(ipClient.equals("127.0.0.1")){
						nName = "Annonymous";
						break;
					}
						nName = vName.get(i);
						break;
					}
				}
				
				String msg = new String(buf);
				msg = msg.trim();
				pln(nName+"("+ipClient+")>> " + msg);
				for(int i=0; i<buf.length; i++) buf[i]=0;
			}
		}catch(SocketException se){
		}catch(IOException ie){
		}finally{
			if(ds != null) ds.close();
		}
	}
	String ipAddr;
	String name;
	void cut(String tmpstr) {
		str = tmpstr.trim();
		if (str.length()>3)
		{
			
			int idx = str.lastIndexOf(" ");
			name = str.substring(0, idx);
			name = name.trim();
			try{
				ipAddr = str.substring(idx+1);
				ipAddr = ipAddr.trim();
				//rate = Integer.parseInt(strRate);
				//System.out.println("rate: " + (rate+1));
			}catch(NumberFormatException ne){
				System.out.println("숫자의 형태가 아니네요");
			}
		}else if (str.length()==3)
		{
			name = str;
			ipAddr = "127.0.0.1";
			return;
		}
		
	}
	void changeItoN(){
		try{
			while(true){
					String line = brFile.readLine();
					if(line == null) break;
					cut(line);
					if (line.length()!=0){
							vName.add(name);
							vIp.add(ipAddr);
						}	
					}
					//for (String vn : vName) pln(vn);
					//for (String vi : vIp) pln(vi);
			}catch(IOException ie){
			}
		
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		new UServerClient();
	}
}

