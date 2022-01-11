import java.io.*;
import java.util.*;

class Lotto
{
	String fName;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedReader brFile;
	FileReader fr;
	int stnumber;
	Vector<String> v = new Vector<String>();
	Vector<String> v2 = new Vector<String>();
	Hashtable<Integer, String> ht = new Hashtable<Integer, String>();
	int sortedint[];
	String namelist[] = new String[30];
	String lastline;
	int optionnb;
	
	void option(){
		try{
			p("옵션을 선택해주세요([1]:원하는 숫자만큼 랜덤출력 [2]:회원검색 [3]: LottoMulti(Hashtable) \n[4]: 랜덤뽑기(Vector교환)): ");
			String line = br.readLine();
			line = line.trim();
			int option = Integer.parseInt(line);
			if(option==1){
				optionnb = 1;
				return;
			}else if(option==2){
				optionnb = 2;
					return;
			}else if (option ==3)
			{
				optionnb =3;
				return;
			}else if(option==4){
				optionnb =4;
				return;
			}
			else{
				pln("1,2,3,4 넷 중 한가지의 옵션을 선택해주세요!");
				option();
			}
		}catch(IOException ie){
			pln("다시시작해주세요!");
			option();
		}catch(NumberFormatException ne){
			pln("숫자를 입력해주세요!");
			option();
		}
	}
	void inputstnumber(){
			p("몇 명의 학생을 뽑으시겠습니까?: ");
			try{
				String str1 = br.readLine();
				str1 = str1.trim();
				stnumber = Integer.parseInt(str1);
			}catch(IOException ie){
		}catch(NumberFormatException nfe){
			pln("숫자를 입력해주세요!");
			inputstnumber();
		}
	}
	void vectorIn(String str){
		v.add(str);
	}
	void vectorOut(){
		try{
				for(int i=0; i<v.size(); i++){
					String str = v.get(sortedint[i]);
					pln("당첨자 번호["+(sortedint[i]+1)+"]: " + str);
				}
			}catch(java.lang.ArrayIndexOutOfBoundsException ib){
		}
	}
	void compareVec(){
		Random r2 = new Random();
		
		int t =0;
			try{
					for(int i=0; i<stnumber; i++){
						t=r2.nextInt(v.size());
						 v2.add(v.get(t));
						 v.remove(t);
						//pln("당첨자 번호["+(sortedint[i]+1)+"]: " + str);
					}
					// for(String st1:v) pln(st1);
					 pln("-----------당첨자 목록-------------");
						 for(String st2:v2)pln(st2);
				}catch(java.lang.ArrayIndexOutOfBoundsException ib){
			}
		}
	
	void vectorOut2(){
		try{
			while(true){
				int check=0	;
				p("검색할 회원명: ");
				String name = br.readLine();
				for(int i=0; i<v.size(); i++){
					String str = v.get(i);
					if(name.equals(str))
					{	
						pln("회원이 존재합니다.");
						check++;
						break;
					}	
				}
				if(name.equals("exit")){
					break;
				}else if(name.equals("list")){
					for(String str: v) pln(str);
				}else if(check==0){
					pln("회원이 존재하지 않습니다.");
				}
				check = 0;
			}
			}catch(java.lang.ArrayIndexOutOfBoundsException ib){
		}catch(IOException ie){
		}
	}
	void inputFName(){
		p("#읽을 파일 이름(기본:우리반): ");
		try{
			fName = br.readLine();
			if(fName != null) fName = fName.trim();
			if(fName.length() == 0) fName = "우리반";
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			System.out.println(fName+"파일을 찾을 수 없음");
			inputFName();
		}catch(IOException ie){
		}
	}
		void inputFName2(){
		try{
			fr = new FileReader("우리반.txt");
			brFile = new BufferedReader(fr);
		}catch(FileNotFoundException fe){
			System.out.println("파일을 찾을 수 없음");
			inputFName();
		}
	}

	Random r = new Random();
	int i;
	void setIndex(){
		int size = 0;
		try{
			String line = "";
			while ((line = brFile.readLine()) != null){
				size++;
			} 
		}catch(IOException ie){
		}
		i = r.nextInt(size); //0~(n-1) //0~29
	}
	void list(){
		try{
			fr = new FileReader(fName+".txt");
			brFile = new BufferedReader(fr);

			int i = 0; 
			while(true){
				String line = brFile.readLine();
				if(line == null) break;
				if (line.length()!=0)
				{
					vectorIn(line);
				}
				i++;
			}
			//pln("#총: "+i+"명");
		}catch(IOException ie){
		}
	}
	void list2(){
		try{
			fr = new FileReader("우리반.txt");
			brFile = new BufferedReader(fr);

			int i = 0; 
			while(true){
				String line = brFile.readLine();
				if(line == null) break;
				lastline = line;
				if (line.length()!=0)
				{
					vectorIn(line);
				}
				
				i++;
			}
			//pln("#총: "+i+"명");
		}catch(IOException ie){
		}
	}
	void list3(){
		try{
			fr = new FileReader("우리반.txt");
			brFile = new BufferedReader(fr);

			int i = 0; 
			while(true){
				String line = brFile.readLine();
				if(line == null) break;
				lastline = line;
				if (line.length()!=0)
				{
					ht.put(i,line);
					i++;
				}
			}
			//pln("#총: "+i+"명");
		}catch(IOException ie){
		}
	}
	void takeRan(int stnumber){
		sortedint = new int[stnumber];
		Random r2 = new Random();
		for (int i=0;i<stnumber ;i++)
		{
			sortedint[i]=r2.nextInt(stnumber);
			for(int j=0 ; j<i; j++){
				if(sortedint[i]==sortedint[j]){
					i--;
				}
			}
		}
	}
	void sortedRan(int stnumber){
		sortedint = new int[stnumber];
		Random r2 = new Random();
		for (int i=0;i<stnumber ;i++)
		{
			sortedint[i]=r2.nextInt(stnumber);
			for(int j=0 ; j<i; j++){
				if(sortedint[i]==sortedint[j]){
					i--;
				}
			}
		}
	}
	void sortedRan2(int stnumber){
		sortedint = new int[stnumber];
		Random r2 = new Random();
		for (int i=0;i<stnumber ;i++)
		{
			sortedint[i]=r2.nextInt(30);
			for(int j=0 ; j<i; j++){
				if(sortedint[i]==sortedint[j]){
					i--;
				}
			}
		}
	}
	void hashOut(){
		Set<Integer> keys = ht.keySet();
		Random r1 = new Random();
		
		for (int i=0;i<stnumber ;i++ )
		{		
			if(ht.get(sortedint[i]) !=null){
				 pln("번호: 0"+(sortedint[i]+1)+", 이름: "+ht.get(sortedint[i]));
			}
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
		Lotto lo = new Lotto();
		lo.option();
		if (lo.optionnb == 1)
		{
			lo.inputstnumber();
			lo.inputFName();
			lo.list();
			lo.sortedRan(lo.stnumber);
			lo.vectorOut();
		}else if(lo.optionnb==2){
			lo.inputFName2();
			lo.list2();
			lo.vectorOut2();
		}else if(lo.optionnb==3){
			lo.inputstnumber();
			lo.inputFName();
			lo.list3();
			lo.sortedRan2(lo.stnumber);
			lo.hashOut();
		}else if(lo.optionnb==4){
			lo.inputstnumber();
				lo.inputFName();
				lo.list2();
			lo.compareVec();
		}
	}
}

