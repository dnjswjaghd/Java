import java.io.*;

class HandleFile 
{

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader br3 = new BufferedReader(new InputStreamReader(System.in));
	String fname;
	FileInputStream fis;
	OutputStream os = System.out;
	FileOutputStream fos;
	String fcopy;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	int opnb;
	String str;
	String path;
	String cutname;

	HandleFile(){
		try{
			option();
			inputFname();
			fis = new FileInputStream(fname);
			bis = new BufferedInputStream(fis, 4096);
		}catch(FileNotFoundException fe){
			System.out.println(fname+"이란 파일을 못찾음");
			new HandleFile();
		}catch(IOException ie){
		}
		try{
			fos = new FileOutputStream(fcopy);
			bos = new BufferedOutputStream(fos, 4096);
			copy();
		}catch(FileNotFoundException fe){
			
			System.out.println("지정 경로에 폴더 없음 만드시겠습니까?(y/n): ");
			try{
				String str2=br.readLine();
				if(str2.equals("y")){
					cut();
					File f = new File(path);
					f.mkdirs();
					pln("처음부터 경로를 다시 입력해주세요.");
					new HandleFile();
				}else if (str2.equals("n"))
				{
					pln("종료합니다.");
					System.exit(0);
				}
			}catch(IOException ie){}
		}
	}
	void inputFname() throws IOException{
		p("복사할 파일명: ");
		fname = br.readLine();
		p("파일 생성 위치: ");
		fcopy = br2.readLine();
	}
	void cut(){
		int i = fcopy.lastIndexOf("\\");
		//System.out.println("i: " + i);
		path = fcopy.substring(0,i+1);
		//System.out.println("경로이름: " + path);
	}
	void createD(){
		File f = new File(fcopy);
		if(f.exists()){
			System.out.println("존재함");
		}else{
			System.out.println("존재하지않음.. 만들까요(y/n)?");
			f.mkdirs();
		}
	}
	void copy(){
		byte bs[] = new byte[1024];
		try{
			int i = 0;
			while((i=bis.read(bs)) != -1){
				bos.write(bs, 0, i);
			}
			bos.flush();
			System.out.println("생성("+fcopy+") 완료!!");
		}catch(IOException ie){
		}finally{
			try{
				bis.close();
				bos.close();
				fis.close();
				fos.close();
			}catch(IOException ie){}
		}
	}
	void option(){
		p("1.복사 2.잘라내기");
		try{
		String str = br3.readLine();
		str = str.trim();
		opnb = Integer.parseInt(str);
		}catch(IOException ie){
		}
	}
	void del(){
		File f = new File(fname);
		f.delete();
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) 
	{
		HandleFile hf = new HandleFile();
		if(hf.opnb ==2)
		{
			//hf.p("fname: "+hf.fname);
			hf.del();
			//hf.p("파일삭제됨");
		}


	}
}
