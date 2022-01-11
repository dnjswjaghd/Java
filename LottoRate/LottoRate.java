import java.io.*;
import java.util.*;

class LottoRate
{
	
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	String fName;
	String str;
	String name;
	String strRate;
	Vector<String> v = new Vector<String>();
	Vector<String> v2 = new Vector<String>();
	int rate;
	int count =0;
	
	LottoRate(){
		
		inputFName();
		rateList();
		showWinner();
	}

	void inputFName(){
		p("#읽을 파일 이름(종료:exit): ");
		try{
			fName = br.readLine();
			if(fName != null) fName = fName.trim();
			if(fName.length() == 0) fName = "우리반";
			if(fName.equals("exit")) System.exit(0);
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			System.out.println(fName+"파일을 찾을 수 없음");
			inputFName();
		}catch(IOException ie){
		}
	}
	void rateList(){
		try{
			
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
			while(true){
					String line = brFile.readLine();
					if(line == null) break;
					str = line;
					cut();
					if (line.length()!=0)
					{
						if(rate==0){
							rate = 1;
							for (int i =0;i<rate ;i++ ){
								vectorIn(name);
							}
						}else if(rate>0){
							for (int i =0;i<rate ;i++ ){
								vectorIn(name);
							}
						}	
					}
					rate = 0;
			}
		}catch(IOException ie){
		}
		
	}
	void showWinner(){
		if(v.size()>100){
			pln("총 확률의 합은 100을 넘을 수 없습니다. 파일을 수정해주세요");
				v.removeAllElements();
				new LottoRate();
		}else{
			int vsize = v.size();
			Random r = new Random();
			int ranIndex =r.nextInt(vsize);
			pln("당첨자: "+ v.get(ranIndex));
			v.removeAllElements();
			new LottoRate();
		}
	}
	void vectorOut(){
				for(int i=0; i<v.size(); i++){
					String str = v.get(i);
					pln("vector ["+i+"]: " + str);
				}
	}
	void vectorIn(String str){
		v.add(str);
	}
	void cut() {
		str = str.trim();
		if (str.length()>3)
		{
			//System.out.println("str: " + str);
			int idx = str.lastIndexOf(" ");
			//System.out.println("idx: " + idx);	
			name = str.substring(0, idx);
			name = name.trim();
			//System.out.println("name: " + name);
			try{
				strRate = str.substring(idx+1);
				strRate = strRate.trim();
				rate = Integer.parseInt(strRate);
				//System.out.println("rate: " + (rate+1));
			}catch(NumberFormatException ne){
				System.out.println("확율이 숫자의 형태가 아니네요");
			}
		}else if (str.length()==3)
		{
			name = str;
			return;
		}
		
	}
	void p(String str){
		System.out.print(str);
	}
	void pln(String str){
		System.out.println(str);
	}
	public static void main(String[] args) 
	{
		LottoRate lr = new LottoRate();
		lr.inputFName();
		lr.rateList();
		lr.showWinner();

	}
}

