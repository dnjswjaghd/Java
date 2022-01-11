import java.io.*;


class CopyDir
{
	FileInputStream fis;
	FileOutputStream fos;
	File startPath,destPath;
	String sp,dp;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	CopyDir(){
		try{
			p("복사할 파일/폴더 경로를 입력해주세요: ");
			sp=br.readLine();
			p("생성할 파일/폴더 경로를 입력해주세요: ");
			dp=br.readLine();
			startPath = new File(sp);
			destPath = new File(dp);
		}catch(IOException ie){
			pln("line 20 예외");
		}
	}
	void copy(File startPath, File destPath){
		destPath.mkdir();
		File filelist[] = startPath.listFiles();
		for(File fl : filelist){
			File temp = new File(destPath.getAbsolutePath() + File.separator + fl.getName());
			File temp2 = new File(dp);
			if (fl.isDirectory()){	
				temp.mkdir();
				copy(fl,temp);
			}else{
				try{
					fis = new FileInputStream(fl);
					fos = new FileOutputStream(temp);
				}catch(FileNotFoundException fe){
					pln("파일을 찾지 못하였습니다.");
				}
				byte bs[] = new byte[1024];
				int i=0;
				try{
					while((i=fis.read(bs)) != -1){
						fos.write(bs, 0, i);
					}
				}catch(Exception ie){
					pln("복사에 실패하였습니다.");
				}finally{
					try{
						fis.close();
						fos.close();
					}catch (IOException ie){
						pln("line 57 예외");
					}
				}
			}
		}
		//
	}
	void pln(String str){
		System.out.println(str);
	}
	void p(String str){
		System.out.print(str);
	}
	public static void main(String[] args) 
	{
		CopyDir cd = new CopyDir();
		cd.copy(cd.startPath,cd.destPath);
		cd.pln("=== 복사가 완료되었습니다 ===");
	}
}
