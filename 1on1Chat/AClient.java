import java.net.*;
import java.io.*;

class AClient extends Thread{

	Socket s;
	String ip = "192.168.0.15"; //at home : 115.91.81.106
	int port = 4000;
	OutputStream os;
	DataOutputStream dos;
	String ipServer;

	InputStream is; //node 
	DataInputStream dis; //filter 
	AClient(){
		try{
			s = new Socket(ip, port);
			pln("서버("+ip+")와 접속 성공!!");
			
			
			ipServer = s.getInetAddress().getHostAddress();
			os = s.getOutputStream();
			is=s.getInputStream();
			start();
			speak();
			
		}catch(UnknownHostException ne){
			pln("네트웍에서 해당 서버("+ip+")를 찾을 수 없음");
		}catch(IOException ie){
		}finally{
			try{
				if(s != null) s.close();
			}catch(IOException ie){}
		}
	}
	@Override
	public void run(){
			while(true){
				listen();
			}
	}

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	void speak(){ //keyboard -> socket 
		dos = new DataOutputStream(os);
		try{
			String msg = "";
			while(true){
				msg = br.readLine();
				dos.writeUTF(msg);
				dos.flush();
			}
		}catch(IOException ie){
			pln("서버가 다운됨.. 2초 후에 종료하겠습니다");
			try{
				Thread.sleep(2000);
			}catch(InterruptedException iee){}
			System.exit(0);
		}finally{
			try{
				if(dos != null) dos.close();
				if(os != null) os.close();
			}catch(IOException ie){}
		}
	}
	void listen(){ //socket -> monitor 
		dis = new DataInputStream(is);
		try{
			while(true){
				String msg = dis.readUTF();
				pln("Server("+ipServer+")>> " + msg);
			}
		}catch(IOException ie){
			pln("Server("+ipServer+")퇴장!!");
		}finally{
			try{
				if(dis != null) dis.close();
				if(is != null) is.close();
			}catch(IOException ie){}
		}
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) {
		AClient c = new AClient();
	}
}
