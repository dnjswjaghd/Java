import java.io.*;
import java.net.*;
import java.util.*;

class MServer extends Thread  {
	ServerSocket ss;
	Socket s;
	int port = 3000;
	OneClientModul ocm;
	Vector<OneClientModul> v = new Vector<OneClientModul>();
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	MServer(){
		try{
			ss = new ServerSocket(port);
			pln(port+"번 포트에서 멀티서버 대기중...");
			start();
			while(true){
				s = ss.accept();//
				ocm = new OneClientModul(this);
				v.add(ocm);
				ocm.start();
			}
		}catch(IOException ie){
			pln(port+"번 포트는 이미 사용중임");
		}finally{
			try{
				if(ss != null) ss.close();
			}catch(IOException ie){}
		}
	}
	public void run(){
		try{
			while(true){
				String msg = br.readLine();
				if(msg.equals("option on")){
					String omsg="";
					while(!omsg.equals("option off")){
						p("옵션을 선택해주세요(list / kick / whisper / option off): ");
						omsg=br.readLine();
						if (omsg.equals("list")){
							for (OneClientModul ocm:v){
								pln(ocm.chatId);
							}
						}
						if (omsg.equals("kick")){
							p("누구를 추방하시겠습니까?: ");
							String outId = br.readLine();
							for(Iterator<OneClientModul> iter = v.iterator(); iter.hasNext();){
								OneClientModul tmpocm = iter.next();
								if(tmpocm.chatId.equals(outId)){
									p("삭제전 : "+v.size());
									iter.remove();
									tmpocm.closeAll();
									p("삭제 후: "+v.size());
									p("iter.remove 실행됨 해당 outId:"+outId);
								}
							}
							/*
							for (OneClientModul ocm : v){
								v.removeIf(v->ocm.chatId.equals(outId));
							}*/
						}
						if(omsg.equals("whisper")){
							p("귓속말 대상: ");
							String nickname = br.readLine();
							for (OneClientModul ocm : v){
								if(ocm.chatId.equals(nickname)){
									try{
										String wmsg="";
										while(true){
											wmsg = br.readLine();
											if(wmsg.equals("woff")) break;
											ocm.dos.writeUTF("관리자>>"+ wmsg);
											ocm.dos.flush();
										}
									}catch(IOException ie){
										pln("whisper에서 예외발생");
									}finally{
										
									}
								}
							}
							
						}
						
						msg="";
					}
				}
				//ocm.dos.writeUTF("관리자>> "+msg);
				//ocm.dos.flush();
				ocm.broadcast("관리자>> "+msg);//전체에 전달
			}
		}catch(IOException ie){
		}finally{
			ocm.closeAll();
		}
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) {
		MServer ms = new MServer();
	}
}
