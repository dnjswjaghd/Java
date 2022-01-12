package dangdang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread{
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBar.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image flareImage;
	private Image judgeImage;

	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;

	
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
		
	}
	
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteSImage, 353, 0,null);
		g.drawImage(noteRouteDImage, 457, 0,null);
		g.drawImage(noteRouteFImage, 561, 0,null);
		g.drawImage(noteRouteSpaceImage, 665, 0,null);
		g.drawImage(noteRouteJImage, 972, 0,null);
		g.drawImage(noteRouteKImage, 1076, 0,null);
		g.drawImage(noteRouteLImage, 1180, 0,null);
		g.drawImage(noteRouteLineImage, 453, 0,null);
		g.drawImage(noteRouteLineImage, 557, 0,null);
		g.drawImage(noteRouteLineImage, 661, 0,null);
		g.drawImage(noteRouteLineImage, 968, 0,null);
		g.drawImage(noteRouteLineImage, 1072, 0,null);
		g.drawImage(noteRouteLineImage, 1176, 0,null);
		g.drawImage(gameInfoImage, 353, 660, null);
		g.drawImage(judgementLineImage, 353, 580, null);
		for(int i = 0; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(!note.isProceeded()) {
				noteList.remove(i);
				i--;
			}else {
				note.screenDraw(g);
			}
		}

		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Bold Italic", Font.BOLD, 25));
		g.drawString(titleName, 355, 685);
		g.drawString(difficulty, 1190, 685);
		
		g.setColor(Color.DARK_GRAY);
		g.drawString("S", 393 , 610 );
		g.drawString("D", 497 , 610 );
		g.drawString("F", 601 , 610 );
		g.drawString("Space", 780 , 610 );
		g.drawString("J", 1012 , 610 );
		g.drawString("K", 1116 , 610 );
		g.drawString("L", 1220 , 610 );
		
		g.setFont(new Font("Elephant", Font.BOLD, 25));
		Integer IScore =(Integer)Main.score;
		g.drawString(IScore.toString(),760, 685);
		g.drawImage(flareImage,390, 250,null);
		g.drawImage(judgeImage,590, 380,null);
		
		
		
	}
	public void judgeEvent(String judge) {
		if(judge.contentEquals("None")) {
			//flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
			//judgeImage = new ImageIcon(Main.class.getResource("../images/missImage.png")).getImage();
			//missImage넣어줘야함
		}
		if(judge.contentEquals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/missImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
			//missImage넣어줘야함
		}else if(judge.contentEquals("Late")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/lateImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/goodImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/greatImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/perfectImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}else if(judge.contentEquals("Early")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/earlyImage.png")).getImage();
			flareImage = new ImageIcon(Main.class.getResource("../images/flareImage.png")).getImage();
		}
	}
	public void pressS() {
		judge("S");
		noteRouteSImage =  new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressSpace() {
		judge("Space");
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBarPressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseSpace() {
		noteRouteSpaceImage = new ImageIcon(Main.class.getResource("../images/noteRouteSpaceBar.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("notePressedSound.mp3",false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}
	@Override
	public void run() {
		dropNotes();
		System.out.println("드랍노트끝");//점수를 보내는 메소드 
	}
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	public void judge(String input) {
		for (int i = 0 ; i< noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("그건아마우리의잘못은아닐거야") && difficulty.equals("Hard")) {
			int startTime = 4420 - Main.REACH_TIME*1000;//4,-480
			int gap = 96;
			beats = new Beat[] {
					new Beat(startTime +gap*9, "S")
					, new Beat(startTime +gap*22, "J")
					, new Beat(startTime +gap*22, "K")
					, new Beat(startTime +gap*28, "Space")
					, new Beat(startTime +gap*34, "J")
					, new Beat(startTime +gap*34, "K")
					, new Beat(startTime +gap*40, "F")
					, new Beat(startTime +gap*46, "F")				
					, new Beat(startTime +gap*50, "J")
					, new Beat(startTime +gap*54, "D")
					, new Beat(startTime +gap*54, "F")
					, new Beat(startTime +gap*54, "J")
					, new Beat(startTime +gap*54, "K")
					, new Beat(startTime +gap*54, "Space")
					, new Beat(startTime +gap*82, "J")
					, new Beat(startTime +gap*82, "L")
					, new Beat(startTime +gap*86, "S")
					, new Beat(startTime +gap*90, "D")
					, new Beat(startTime +gap*100, "S")
					, new Beat(startTime +gap*104, "D")
					, new Beat(startTime +gap*108, "F")
					, new Beat(startTime +gap*112, "J")
					, new Beat(startTime +gap*116, "K")
					, new Beat(startTime +gap*120, "L")
					, new Beat(startTime +gap*124, "Space")
					, new Beat(startTime +gap*128, "Space")
					, new Beat(startTime +gap*132, "J")
					, new Beat(startTime +gap*136, "S")
					, new Beat(startTime +gap*140, "Space")
					, new Beat(startTime +gap*146, "D")
					, new Beat(startTime +gap*152, "F")
					, new Beat(startTime +gap*158, "J")
					, new Beat(startTime +gap*164, "K")
					, new Beat(startTime +gap*170, "L")
					, new Beat(startTime +gap*176, "Space")
					, new Beat(startTime +gap*179, "Space")
					, new Beat(startTime +gap*182, "J")
					, new Beat(startTime +gap*188, "J")
					, new Beat(startTime +gap*194, "Space")
					, new Beat(startTime +gap*200, "D")
					, new Beat(startTime +gap*206, "F")
					, new Beat(startTime +gap*212, "J")
					, new Beat(startTime +gap*215, "K")
					, new Beat(startTime +gap*218, "L")
					, new Beat(startTime +gap*224, "S")
					, new Beat(startTime +gap*230, "K")
					, new Beat(startTime +gap*236, "D")
					, new Beat(startTime +gap*239, "J")
					, new Beat(startTime +gap*245, "F")
					, new Beat(startTime +gap*251, "F")
					, new Beat(startTime +gap*255, "S")
					, new Beat(startTime +gap*261, "D")
					, new Beat(startTime +gap*267, "F")
					, new Beat(startTime +gap*273, "J")
					, new Beat(startTime +gap*279, "K")
					, new Beat(startTime +gap*285, "L")
					, new Beat(startTime +gap*291, "Space")
					, new Beat(startTime +gap*294, "S")
					, new Beat(startTime +gap*294, "D")
					, new Beat(startTime +gap*300, "D")
					, new Beat(startTime +gap*300, "F")
					, new Beat(startTime +gap*306, "J")
					, new Beat(startTime +gap*306, "K")
					, new Beat(startTime +gap*309, "Space")
					, new Beat(startTime +gap*309, "F")
					, new Beat(startTime +gap*309, "J")
					, new Beat(startTime +gap*315, "Space")
					, new Beat(startTime +gap*315, "D")
					, new Beat(startTime +gap*315, "K")
					, new Beat(startTime +gap*321, "Space")
					, new Beat(startTime +gap*321, "S")
					, new Beat(startTime +gap*321, "L")
					, new Beat(startTime +gap*327, "J")
					, new Beat(startTime +gap*330, "F")
					, new Beat(startTime +gap*333, "L")
					, new Beat(startTime +gap*336, "S")
					, new Beat(startTime +gap*342, "J")
					, new Beat(startTime +gap*345, "F")
					, new Beat(startTime +gap*348, "L")
					, new Beat(startTime +gap*351, "S")
					, new Beat(startTime +gap*360, "J")
					, new Beat(startTime +gap*363, "F")
					, new Beat(startTime +gap*366, "L")
					, new Beat(startTime +gap*369, "S")
					, new Beat(startTime +gap*372, "K")
					, new Beat(startTime +gap*375, "D")
					, new Beat(startTime +gap*378, "J")
					, new Beat(startTime +gap*381, "F")
					, new Beat(startTime +gap*384, "L")
					, new Beat(startTime +gap*387, "S")
					, new Beat(startTime +gap*390, "K")
					, new Beat(startTime +gap*393, "D")
					, new Beat(startTime +gap*399, "S")
					, new Beat(startTime +gap*402, "D")
					, new Beat(startTime +gap*405, "F")
					, new Beat(startTime +gap*408, "L")
					, new Beat(startTime +gap*411, "K")
					, new Beat(startTime +gap*414, "J")
					, new Beat(startTime +gap*420, "S")
					, new Beat(startTime +gap*423, "D")
					, new Beat(startTime +gap*426, "F")
					, new Beat(startTime +gap*429, "L")
					, new Beat(startTime +gap*432, "K")
					, new Beat(startTime +gap*435, "J")
					, new Beat(startTime +gap*441, "S")
					, new Beat(startTime +gap*444, "D")
					, new Beat(startTime +gap*447, "F")
					, new Beat(startTime +gap*450, "L")
					, new Beat(startTime +gap*453, "K")
					, new Beat(startTime +gap*456, "J")
					, new Beat(startTime +gap*462, "Space")
					, new Beat(startTime +gap*468, "Space")
					, new Beat(startTime +gap*474, "D")
					, new Beat(startTime +gap*474, "F")
					, new Beat(startTime +gap*474, "J")
					, new Beat(startTime +gap*474, "K")
					, new Beat(startTime +gap*477, "S")
					, new Beat(startTime +gap*477, "F")
					, new Beat(startTime +gap*477, "J")
					, new Beat(startTime +gap*477, "L")
					, new Beat(startTime +gap*483, "Space")
					, new Beat(startTime +gap*489, "Space")
					, new Beat(startTime +gap*495, "Space")
					, new Beat(startTime +gap*501, "Space")
					, new Beat(startTime +gap*510, "L")
					, new Beat(startTime +gap*513, "S")
					, new Beat(startTime +gap*516, "K")
					, new Beat(startTime +gap*519, "D")
					, new Beat(startTime +gap*522, "J")
					, new Beat(startTime +gap*525, "F")		
					, new Beat(startTime +gap*528, "L")
					, new Beat(startTime +gap*531, "S")
					, new Beat(startTime +gap*534, "K")
					, new Beat(startTime +gap*537, "D")
					, new Beat(startTime +gap*541, "J")
					, new Beat(startTime +gap*544, "F")				
					, new Beat(startTime +gap*547, "L")
					, new Beat(startTime +gap*550, "S")
					, new Beat(startTime +gap*553, "K")
					, new Beat(startTime +gap*556, "D")
					, new Beat(startTime +gap*559, "J")
					, new Beat(startTime +gap*562, "F")
					, new Beat(startTime +gap*565, "L")
					, new Beat(startTime +gap*568, "S")
					, new Beat(startTime +gap*571, "K")
					, new Beat(startTime +gap*574, "D")
					, new Beat(startTime +gap*577, "J")
					, new Beat(startTime +gap*580, "F")
					, new Beat(startTime +gap*583, "L")
					, new Beat(startTime +gap*587, "S")
					, new Beat(startTime +gap*590, "K")
					, new Beat(startTime +gap*593, "D")
					, new Beat(startTime +gap*596, "J")
					, new Beat(startTime +gap*599, "F")
					, new Beat(startTime +gap*602, "L")
					, new Beat(startTime +gap*605, "S")
					, new Beat(startTime +gap*608, "K")
					, new Beat(startTime +gap*611, "D")
					, new Beat(startTime +gap*614, "J")
					, new Beat(startTime +gap*617, "F")
					, new Beat(startTime +gap*623, "Space")
					, new Beat(startTime +gap*629, "Space")
					, new Beat(startTime +gap*635, "Space")
			};
		}else if(titleName.equals("그건아마우리의잘못은아닐거야") && difficulty.equals("Easy")) {
			int startTime = 4420 - Main.REACH_TIME*1000;//4,-480
			int gap = 96;
			beats = new Beat[] {
					new Beat(startTime +gap*9, "S")
					, new Beat(startTime +gap*22, "J")
					, new Beat(startTime +gap*22, "K")
					, new Beat(startTime +gap*28, "Space")
					, new Beat(startTime +gap*34, "J")				
					, new Beat(startTime +gap*54, "J")
					, new Beat(startTime +gap*54, "K")			
					, new Beat(startTime +gap*82, "J")
					, new Beat(startTime +gap*82, "L")
					, new Beat(startTime +gap*90, "D")
					, new Beat(startTime +gap*124, "Space")
					, new Beat(startTime +gap*128, "Space")
					, new Beat(startTime +gap*132, "J")
					, new Beat(startTime +gap*136, "S")			
					, new Beat(startTime +gap*146, "D")
					, new Beat(startTime +gap*152, "F")
					, new Beat(startTime +gap*158, "J")
					, new Beat(startTime +gap*164, "K")	
					, new Beat(startTime +gap*200, "D")					
					, new Beat(startTime +gap*212, "J")
					, new Beat(startTime +gap*215, "K")		
					, new Beat(startTime +gap*255, "S")
					, new Beat(startTime +gap*261, "D")					
					, new Beat(startTime +gap*273, "J")
					, new Beat(startTime +gap*279, "K")
					, new Beat(startTime +gap*285, "L")
					, new Beat(startTime +gap*291, "Space")					
					, new Beat(startTime +gap*306, "K")
					, new Beat(startTime +gap*309, "Space")
					, new Beat(startTime +gap*309, "F")					
					, new Beat(startTime +gap*315, "D")
					, new Beat(startTime +gap*315, "K")
					, new Beat(startTime +gap*321, "Space")
					, new Beat(startTime +gap*321, "S")		
					, new Beat(startTime +gap*348, "L")
					, new Beat(startTime +gap*351, "S")			
					, new Beat(startTime +gap*363, "F")
					, new Beat(startTime +gap*366, "L")
					, new Beat(startTime +gap*372, "K")
					, new Beat(startTime +gap*375, "D")
					, new Beat(startTime +gap*399, "S")				
					, new Beat(startTime +gap*405, "F")
					, new Beat(startTime +gap*408, "L")				
					, new Beat(startTime +gap*420, "S")
					, new Beat(startTime +gap*423, "D")
					, new Beat(startTime +gap*426, "F")				
					, new Beat(startTime +gap*468, "Space")
					, new Beat(startTime +gap*474, "D")				
					, new Beat(startTime +gap*474, "J")
					, new Beat(startTime +gap*474, "K")
					, new Beat(startTime +gap*477, "S")			
					, new Beat(startTime +gap*510, "L")
					, new Beat(startTime +gap*513, "S")
					, new Beat(startTime +gap*516, "K")
					, new Beat(startTime +gap*519, "D")
					, new Beat(startTime +gap*522, "J")
					, new Beat(startTime +gap*525, "F")		
					, new Beat(startTime +gap*528, "L")				
					, new Beat(startTime +gap*550, "S")
					, new Beat(startTime +gap*553, "K")
					, new Beat(startTime +gap*556, "D")
					, new Beat(startTime +gap*559, "J")
					, new Beat(startTime +gap*562, "F")				
					, new Beat(startTime +gap*571, "K")
					, new Beat(startTime +gap*574, "D")
					, new Beat(startTime +gap*577, "J")
					, new Beat(startTime +gap*580, "F")			
					, new Beat(startTime +gap*587, "S")
					, new Beat(startTime +gap*590, "K")
					, new Beat(startTime +gap*593, "D")
					, new Beat(startTime +gap*596, "J")
					, new Beat(startTime +gap*599, "F")				
					, new Beat(startTime +gap*605, "S")
					, new Beat(startTime +gap*608, "K")
					, new Beat(startTime +gap*611, "D")
					, new Beat(startTime +gap*614, "J")
					, new Beat(startTime +gap*617, "F")					
					, new Beat(startTime +gap*629, "Space")
					, new Beat(startTime +gap*635, "Space")
			};
		}else if(titleName.equals("회전목마")&& difficulty.equals("Hard")) {
	         int startTime = 2600 - Main.REACH_TIME*1000;
	         int gap = 100;
	         beats = new Beat[] { //hard mode            
	               new Beat(startTime + gap, "S"),
	               new Beat(startTime + gap*2, "D"),
	               new Beat(startTime + gap*4, "F"), //+3
	               new Beat(startTime + gap*7, "F"), //+7
	               new Beat(startTime + gap*14, "Space"), //+8
	               
	               new Beat(startTime + gap*22, "L"), 
	               new Beat(startTime + gap*24, "K"),
	               new Beat(startTime + gap*26, "J"), //+3
	               new Beat(startTime + gap*29, "J"), //+8
	               new Beat(startTime + gap*37, "Space"), //+8
	               
	               new Beat(startTime + gap*45, "S"),
	               new Beat(startTime + gap*47, "D"),
	               new Beat(startTime + gap*49, "F"), //+3
	               new Beat(startTime + gap*52, "Space"), //+8
	               new Beat(startTime + gap*60, "Space"), //+8
	               
	               new Beat(startTime + gap*68, "L"),
	               new Beat(startTime + gap*70, "K"),
	               new Beat(startTime + gap*72, "J"), //+3
	               new Beat(startTime + gap*75, "Space"), //+8
	               new Beat(startTime + gap*83, "Space"), //+9
	               
	               new Beat(startTime + gap*92, "S"),
	               new Beat(startTime + gap*92, "D"),
	               new Beat(startTime + gap*94, "S"),
	               new Beat(startTime + gap*94, "D"),
	               new Beat(startTime + gap*96, "K"),
	               new Beat(startTime + gap*96, "L"), //+3
	               new Beat(startTime + gap*99, "K"),
	               new Beat(startTime + gap*99, "L"), //+8
	               new Beat(startTime + gap*107, "F"), 
	               new Beat(startTime + gap*107, "J"), //+8
	               
	               new Beat(startTime + gap*115, "D"),
	               new Beat(startTime + gap*115, "F"),
	               new Beat(startTime + gap*117, "D"),
	               new Beat(startTime + gap*117, "F"),
	               new Beat(startTime + gap*119, "J"),
	               new Beat(startTime + gap*119, "K"), //+3
	               new Beat(startTime + gap*122, "J"),
	               new Beat(startTime + gap*122, "K"), //+8
	               new Beat(startTime + gap*130, "Space"), //+8
	               
	               new Beat(startTime + gap*138, "F"),
	               new Beat(startTime + gap*140, "J"),
	               new Beat(startTime + gap*142, "Space"), //+3
	               new Beat(startTime + gap*145, "Space"), //+28
	               //잔잔한 인트로 끝, 둥둥탁 시작
	               new Beat(startTime + gap*173, "Space"), //+5
	               new Beat(startTime + gap*178, "F"), //+6
	               new Beat(startTime + gap*184, "Space"), 
	               new Beat(startTime + gap*190, "J"),
	               new Beat(startTime + gap*196, "Space"),
	               new Beat(startTime + gap*202, "Space"), //+4
	               new Beat(startTime + gap*206, "Space"), //+6 
	               //반박 빠르게 들어감         
	               //가사시작                        
	               new Beat(startTime + gap*212, "S"), //내가 +5
	               new Beat(startTime + gap*217, "F"), //슬플  +6
	               new Beat(startTime + gap*223, "L"), //때 마
	               new Beat(startTime + gap*229, "J"), //다 +5
	               new Beat(startTime + gap*234, "F"), //이 노
	               new Beat(startTime + gap*240, "J"), //래가 +6
	               new Beat(startTime + gap*246, "D"), //찾아
	               new Beat(startTime + gap*246, "K"), //찾아
	               new Beat(startTime + gap*252, "Space"), //와 +6
	                                          //세상
	               new Beat(startTime + gap*258, "S"), //이 둥
	               new Beat(startTime + gap*264, "S"), //근 것
	               new Beat(startTime + gap*264, "D"),//근 것 +5
	               new Beat(startTime + gap*269, "L"), //처럼 +6
	               new Beat(startTime + gap*275, "K"), //우린
	               new Beat(startTime + gap*275, "L"), //우린 +3 OK
	               new Beat(startTime + gap*278, "Space"), //+6
	               new Beat(startTime + gap*284, "Space"), //동글 +5
	               new Beat(startTime + gap*289, "Space"),   //동글 +4
	               new Beat(startTime + gap*293, "F"), // +2
	               new Beat(startTime + gap*295, "J"),
	               new Beat(startTime + gap*297, "F"),
	               new Beat(startTime + gap*299, "J"), //+6
	               //OK
	               //다음 소절                        //인생
	               new Beat(startTime + gap*305, "Space"),//은 회 +6
	               new Beat(startTime + gap*311,"S"), //전 목 +6
	               new Beat(startTime + gap*311,"D"), //전 목 +6
	               new Beat(startTime + gap*317, "D"), //마 +6
	               new Beat(startTime + gap*317, "F"), //마 +6
	               new Beat(startTime + gap*323, "Space"),//우린
	               new Beat(startTime + gap*329, "K"), //매일 +5
	               new Beat(startTime + gap*329, "L"), //매일 +5
	               new Beat(startTime + gap*334, "J"), //달려 
	               new Beat(startTime + gap*334, "K"), //달려 
	               new Beat(startTime + gap*339, "Space"), //가.. +6
	                                          
	               new Beat(startTime + gap*345, "F"), //언제 +5
	               new Beat(startTime + gap*350, "S"), //쯤
	               new Beat(startTime + gap*350, "D"), //쯤
	               new Beat(startTime + gap*355, "Space"), //끝(사이)나 +6 
	               new Beat(startTime + gap*361, "J"), //난  +5
	               new Beat(startTime + gap*366, "K"), //잘(사이)몰 +5
	               new Beat(startTime + gap*366, "L"), //잘(사이)몰 +5
	               new Beat(startTime + gap*371, "Space"), //라~ + 15
	               new Beat(startTime + gap*386, "F"), // +2
	               new Beat(startTime + gap*386, "J"), // +2
	               new Beat(startTime + gap*388, "F"), //+9
	               new Beat(startTime + gap*388, "Space"), //+9
	               new Beat(startTime + gap*388, "J"), //+9
	               //OK 1214.1809
	               new Beat(startTime + gap*397, "F"), //어
	               new Beat(startTime + gap*397, "J"),
	               //new Beat(startTime + gap*397, "F"), //+3
	               
	               new Beat(startTime + gap*400, "F"), //머!
	               new Beat(startTime + gap*400, "J"),
	               new Beat(startTime + gap*400, "Space"), //+3
	               
	               new Beat(startTime + gap*403, "Space"), //+5
	               
	               new Beat(startTime + gap*408, "S"), //벌
	               new Beat(startTime + gap*408, "L"),
	               //new Beat(startTime + gap*408, "F"), //+3
	               
	               new Beat(startTime + gap*411, "S"), //써!
	               new Beat(startTime + gap*411, "L"),
	               new Beat(startTime + gap*411, "Space"), //+4
	               
	               new Beat(startTime + gap*415, "Space"), //+5
	                                          //정
	               new Beat(startTime + gap*420, "S"), //신없이 +6
	               new Beat(startTime + gap*426, "D"), //달려왔
	               new Beat(startTime + gap*432, "F"), //어
	               new Beat(startTime + gap*438, "J"),
	               new Beat(startTime + gap*438, "K"),
	               new Beat(startTime + gap*438, "L"), //+3 
	                                          
	               new Beat(startTime + gap*441, "Space"), //스피드
	               new Beat(startTime + gap*444, "L"), //업!
	               new Beat(startTime + gap*450, "K"), //+5 
	               new Beat(startTime + gap*455, "J"), //어제(로)
	               new Beat(startTime + gap*460, "S"),
	               new Beat(startTime + gap*460, "D"),
	               new Beat(startTime + gap*460, "F"), //+6
	               new Beat(startTime + gap*466, "S"),
	               new Beat(startTime + gap*466, "D"),  //돌아(가)는 시 
	               new Beat(startTime + gap*466, "F"),  //돌아(가)는 시 
	               new Beat(startTime + gap*472, "J"),  //(곌) 보 
	               new Beat(startTime + gap*472, "K"),  //(곌) 보 +5
	               new Beat(startTime + gap*477, "Space"), //다 +5
	               new Beat(startTime + gap*482, "Space"), //가 OK +11
	                                          //어려
	               new Beat(startTime + gap*493, "D"), //워 +5
	               new Beat(startTime + gap*493, "F"), //워 +5
	               new Beat(startTime + gap*498, "D"),
	               new Beat(startTime + gap*498, "F"), //+6
	               new Beat(startTime + gap*504, "J"), //어른(이)+6
	               new Beat(startTime + gap*504, "K"), //어른(이)+6
	               new Beat(startTime + gap*510, "J"), //되어(가)는 +4
	               new Beat(startTime + gap*510, "K"), //되어(가)는 +4
	               new Beat(startTime + gap*514, "S"), //+3
	               new Beat(startTime + gap*517, "L"),
	               new Beat(startTime + gap*520, "D"),
	               new Beat(startTime + gap*523, "K"),
	               new Beat(startTime + gap*526, "Space"), //+6
	               new Beat(startTime + gap*532, "Space"), //+6
	               
	               new Beat(startTime + gap*538, "S"), //온더(로)드 +5
	               new Beat(startTime + gap*538, "D"), //온더(로)드 +5
	               new Beat(startTime + gap*543, "S"), // +6
	               new Beat(startTime + gap*543, "D"), // +6
	               new Beat(startTime + gap*549, "K"),
	               new Beat(startTime + gap*549, "L"),
	               new Beat(startTime + gap*555, "K"), //+4
	               new Beat(startTime + gap*555, "L"), //+4
	               new Beat(startTime + gap*559, "D"), //+3
	               new Beat(startTime + gap*562, "F"),
	               new Beat(startTime + gap*565, "K"),
	               new Beat(startTime + gap*568, "J"),
	               new Beat(startTime + gap*571, "Space"), //+5
	               
	               new Beat(startTime + gap*576, "Space"), // Big noise +6
	               new Beat(startTime + gap*582, "F"), //+5
	               new Beat(startTime + gap*582, "J"), //+5
	               new Beat(startTime + gap*587, "F"),
	               new Beat(startTime + gap*587, "K"),
	               new Beat(startTime + gap*592, "F"), //+6
	               new Beat(startTime + gap*592, "L"), //+6
	               new Beat(startTime + gap*598, "J"), //어렸을 때처럼
	               new Beat(startTime + gap*598, "F"),
	               new Beat(startTime + gap*604, "J"), //+4
	               new Beat(startTime + gap*604, "D"), //+4
	               new Beat(startTime + gap*608, "J"), //+5
	               new Beat(startTime + gap*608, "S"), //+5
	               new Beat(startTime + gap*613, "Space"), //바뀌지 않는 +7
	               new Beat(startTime + gap*620, "Space"),  //(걸) +6 
	               
	               new Beat(startTime + gap*626, "S"), //(바로 들어감)찾아나섰단 말야
	               new Beat(startTime + gap*626, "D"), //(바로 들어감)찾아나섰단 말야
	               new Beat(startTime + gap*632, "D"),
	               new Beat(startTime + gap*632, "F"),
	               new Beat(startTime + gap*637, "K"),
	               new Beat(startTime + gap*637, "L"),
	               new Beat(startTime + gap*642, "J"), //+5
	               new Beat(startTime + gap*642, "K"), //+5
	               new Beat(startTime + gap*647, "Space"), //+5
	               new Beat(startTime + gap*652, "F"), // 좋았어 (난)~ +12 OK
	               new Beat(startTime + gap*652, "J"), // 좋았어 (난)~ +12 OK
	               
	               new Beat(startTime + gap*664, "Space"), //So~ +7
	               new Beat(startTime + gap*671, "S"), // +5
	               new Beat(startTime + gap*676, "D"),
	               new Beat(startTime + gap*681, "F"),
	               new Beat(startTime + gap*686, "S"), //+6
	               new Beat(startTime + gap*686, "D"), //+6
	               new Beat(startTime + gap*686, "F"), //+6
	               new Beat(startTime + gap*692, "L"), //+5
	               new Beat(startTime + gap*697, "K"),
	               new Beat(startTime + gap*702, "J"),
	               new Beat(startTime + gap*707, "J"), //+6
	               new Beat(startTime + gap*707, "K"), //+6
	               new Beat(startTime + gap*707, "L"), //+6
	               
	               new Beat(startTime + gap*713, "Space"),
	               new Beat(startTime + gap*719, "F"), //승호(가)
	               new Beat(startTime + gap*719, "J"), //승호(가)
	               new Beat(startTime + gap*725, "J"), //(좋)았을
	               new Beat(startTime + gap*725, "K"), //(좋)았을
	               new Beat(startTime + gap*731, "Space"), //(때)처럼+6
	               new Beat(startTime + gap*737, "Space"), //만~ +5
	               //
	               
	               new Beat(startTime + gap*742, "S"), //내가 +5
	               new Beat(startTime + gap*747, "F"), //슬플  +6
	               new Beat(startTime + gap*753, "L"), //때 마 
	               new Beat(startTime + gap*759, "J"), //다  +5
	               new Beat(startTime + gap*764, "F"), //이 노 
	               new Beat(startTime + gap*769, "J"), //래가 +6 
	               new Beat(startTime + gap*775, "D"), //찾아 +5
	               new Beat(startTime + gap*780, "K"), //와  +6 OK
	                                          
	                                          //세상
	               new Beat(startTime + gap*786, "D"), //이 둥 +5
	               new Beat(startTime + gap*791, "D"), //근 것
	               new Beat(startTime + gap*791, "F"),//근 것 +6 
	               new Beat(startTime + gap*797, "K"), //처럼 +6
	               new Beat(startTime + gap*803, "J"), //우린
	               new Beat(startTime + gap*803, "K"), //우린 +5
	               new Beat(startTime + gap*808, "F"), //+5
	               new Beat(startTime + gap*808, "Space"), //+5
	               new Beat(startTime + gap*813, "Space"), //+12
	               new Beat(startTime + gap*813, "J"), //+12
	               new Beat(startTime + gap*825, "D"),
	               new Beat(startTime + gap*825, "F"),// +4
	               new Beat(startTime + gap*829, "J"),
	               new Beat(startTime + gap*829, "K"), //+5 OK
	               
	                                             //인생
	               new Beat(startTime + gap*834, "Space"),//은 회
	               new Beat(startTime + gap*839, "S"), //전 목 +6
	               new Beat(startTime + gap*839, "D"), //전 목 +6
	               new Beat(startTime + gap*845, "D"), //마
	               new Beat(startTime + gap*845, "F"), //마
	               new Beat(startTime + gap*851, "Space"),//우린
	               new Beat(startTime + gap*857, "K"), //매일
	               new Beat(startTime + gap*857, "L"), //매일
	               new Beat(startTime + gap*863, "J"), //달려
	               new Beat(startTime + gap*863, "K"), //달려
	               new Beat(startTime + gap*869, "F"), //가.. +6
	               new Beat(startTime + gap*869, "Space"), //가.. +6
	               new Beat(startTime + gap*869, "J"), //가.. +6
	                                          
	               new Beat(startTime + gap*875, "F"), //언제 +5
	               new Beat(startTime + gap*880, "S"), //쯤
	               new Beat(startTime + gap*880, "D"), //쯤
	               new Beat(startTime + gap*885, "Space"), //끝나 
	               new Beat(startTime + gap*890, "J"), //난 
	               new Beat(startTime + gap*895, "K"), //잘몰
	               new Beat(startTime + gap*895, "L"), //잘몰
	               new Beat(startTime + gap*900, "Space"), //라~ +5 
	               new Beat(startTime + gap*905, "Space"), //+8
	               new Beat(startTime + gap*913, "F"), 
	               new Beat(startTime + gap*913, "Space"), //빙 +4
	               new Beat(startTime + gap*913, "J"),
	               new Beat(startTime + gap*917, "F"),
	               new Beat(startTime + gap*917, "Space"), //빙 OK
	               new Beat(startTime + gap*917, "J"), // +6
	               
	               new Beat(startTime + gap*923, "S"), //돌아가는 +5
	               new Beat(startTime + gap*928, "F"), //+6
	               new Beat(startTime + gap*934, "D"), 
	               new Beat(startTime + gap*940, "L"), //+5 
	               new Beat(startTime + gap*945, "J"), 
	               new Beat(startTime + gap*950, "K"), 
	               new Beat(startTime + gap*955, "F"),
	               new Beat(startTime + gap*955, "Space"), 
	               new Beat(startTime + gap*960, "Space"),
	               new Beat(startTime + gap*960, "J"),//+6
	               
	               new Beat(startTime + gap*966, "F"), //영(원)히  
	               new Beat(startTime + gap*972, "K"), //계속 
	               new Beat(startTime + gap*978, "J"),
	               new Beat(startTime + gap*984, "D"),
	               new Beat(startTime + gap*990, "Space"), //+5
	               new Beat(startTime + gap*995, "Space"), //+6
	               
	               new Beat(startTime + gap*1001, "F"),
	               new Beat(startTime + gap*1001, "Space"), //빙 +5 
	               new Beat(startTime + gap*1001, "J"),
	               new Beat(startTime + gap*1007, "F"),
	               new Beat(startTime + gap*1007, "Space"), //빙 OK
	               new Beat(startTime + gap*1007, "J"), //+6
	               
	               new Beat(startTime + gap*1013, "D"), //돌아가는 +5
	               new Beat(startTime + gap*1018, "J"), //+5
	               new Beat(startTime + gap*1023, "K"), 
	               new Beat(startTime + gap*1028, "F"), //+5 
	               new Beat(startTime + gap*1033, "F"),
	               new Beat(startTime + gap*1033, "J"),
	               new Beat(startTime + gap*1038, "F"),
	               new Beat(startTime + gap*1038, "J"), 
	               new Beat(startTime + gap*1043, "Space"), 
	               new Beat(startTime + gap*1048, "Space"), //+6
	               
	               new Beat(startTime + gap*1054, "S"), //인생은 회전목마~ +6
	               new Beat(startTime + gap*1054, "D"), //인생은 회전목마~ +6
	               new Beat(startTime + gap*1060, "D"),
	               new Beat(startTime + gap*1060, "F"),
	               new Beat(startTime + gap*1066, "K"), //마지막에 느려짐
	               new Beat(startTime + gap*1066, "L"), //마지막에 느려짐
	               new Beat(startTime + gap*1072, "J"),
	               new Beat(startTime + gap*1072, "K"),
	               new Beat(startTime + gap*1078, "F"),
	               new Beat(startTime + gap*1078, "J"),
	               new Beat(startTime + gap*1084, "F"),
	               new Beat(startTime + gap*1084, "J"),
	               new Beat(startTime + gap*1090, "F"),
	               new Beat(startTime + gap*1090, "Space"), //OK
	               new Beat(startTime + gap*1090, "J")
	               
	         };
	      }else if(titleName.equals("회전목마")&& difficulty.equals("Easy")) {
	          int startTime = 2600 - Main.REACH_TIME*1000;
	          int gap = 100;
	          beats = new Beat[] { //easy mode
	                new Beat(startTime + gap, "S"),
	                new Beat(startTime + gap*2, "D"),
	                new Beat(startTime + gap*4, "F"), //+3
	                new Beat(startTime + gap*7, "F"), //+15
	                
	                new Beat(startTime + gap*22, "L"),
	                new Beat(startTime + gap*24, "K"),
	                new Beat(startTime + gap*26, "J"), //+3
	                new Beat(startTime + gap*29, "J"), //+16
	                
	                new Beat(startTime + gap*45, "S"),
	                new Beat(startTime + gap*47, "D"),
	                new Beat(startTime + gap*49, "F"), //+3
	                new Beat(startTime + gap*52, "Space"), //+16
	                
	                new Beat(startTime + gap*68, "L"),
	                new Beat(startTime + gap*70, "K"),
	                new Beat(startTime + gap*72, "J"), //+3
	                new Beat(startTime + gap*75, "Space"), //+17
	                
	                new Beat(startTime + gap*92, "S"),
	                new Beat(startTime + gap*92, "D"),
	                new Beat(startTime + gap*94, "S"),
	                new Beat(startTime + gap*94, "D"),
	                new Beat(startTime + gap*96, "K"),
	                new Beat(startTime + gap*96, "L"), //+3
	                new Beat(startTime + gap*99, "K"),
	                new Beat(startTime + gap*99, "L"), //+16
	                
	                new Beat(startTime + gap*115, "D"),
	                new Beat(startTime + gap*115, "F"),
	                new Beat(startTime + gap*117, "D"),
	                new Beat(startTime + gap*117, "F"),
	                new Beat(startTime + gap*119, "J"),
	                new Beat(startTime + gap*119, "K"), //+3
	                new Beat(startTime + gap*122, "J"),
	                new Beat(startTime + gap*122, "K"), //+16
	                
	                new Beat(startTime + gap*138, "F"),
	                new Beat(startTime + gap*140, "J"),
	                new Beat(startTime + gap*142, "Space"), //+3
	                new Beat(startTime + gap*145, "Space"), //+28
	                //잔잔한 인트로 끝, 둥둥탁 시작
	                new Beat(startTime + gap*173, "Space"), //+5
	                new Beat(startTime + gap*178, "F"), //+6
	                new Beat(startTime + gap*184, "Space"), 
	                new Beat(startTime + gap*190, "J"),
	                new Beat(startTime + gap*196, "Space"),
	                new Beat(startTime + gap*202, "Space"), //+4
	                new Beat(startTime + gap*206, "Space"), //+6 
	                //반박 빠르게 들어감         
	                //가사시작                        
	                new Beat(startTime + gap*212, "S"), //내가 +5
	                new Beat(startTime + gap*217, "F"), //슬플  +6
	                new Beat(startTime + gap*223, "D"), //때 마
	                new Beat(startTime + gap*229, "F"), //다 +5
	                new Beat(startTime + gap*234, "L"), //이 노
	                new Beat(startTime + gap*240, "J"), //래가 +6
	                new Beat(startTime + gap*246, "K"), //찾아
	                new Beat(startTime + gap*252, "J"), //와 +6
	                                           //세상
	                new Beat(startTime + gap*258, "S"), //이 둥
	                new Beat(startTime + gap*258, "D"), //이 둥 +5 
	                new Beat(startTime + gap*263, "D"), //근 것
	                new Beat(startTime + gap*263, "F"),//근 것 +6
	                new Beat(startTime + gap*269, "K"), //처럼
	                new Beat(startTime + gap*269, "L"), //처럼
	                new Beat(startTime + gap*275, "J"), //우린
	                new Beat(startTime + gap*275, "K"), //우린 +4 OK
	                //new Beat(startTime + gap*281, "Space"), //+5
	                new Beat(startTime + gap*284, "Space"), //동글 +5
	                new Beat(startTime + gap*289, "Space"),   //동글 +4
	                //new Beat(startTime + gap*293, "S"), // +2
	                new Beat(startTime + gap*295, "F"),
	                new Beat(startTime + gap*295, "J"),
	                //new Beat(startTime + gap*297, "F"),
	                new Beat(startTime + gap*299, "F"),
	                new Beat(startTime + gap*299, "J"), //+6
	                //OK
	                //다음 소절                        //인생
	                new Beat(startTime + gap*305, "Space"),//은 회 +6
	                new Beat(startTime + gap*311,"S"), //전 목 +6
	                new Beat(startTime + gap*317, "L"), //마 +6
	                new Beat(startTime + gap*323, "D"),//우린
	                new Beat(startTime + gap*329, "K"), //매일 +5
	                new Beat(startTime + gap*334, "F"), //달려 
	                new Beat(startTime + gap*339, "J"), //가.. +6
	                                           
	                new Beat(startTime + gap*345, "S"), //언제 +5
	                new Beat(startTime + gap*350, "D"), //쯤
	                new Beat(startTime + gap*355, "F"), //끝(사이)나 +6 
	                new Beat(startTime + gap*361, "L"), //난  +5
	                new Beat(startTime + gap*366, "K"), //잘(사이)몰 +5
	                new Beat(startTime + gap*371, "J"), //라~ + 15
	                new Beat(startTime + gap*386, "Space"), // +2
	                new Beat(startTime + gap*388, "Space"), //+9
	                
	                new Beat(startTime + gap*397, "S"), //어
	                new Beat(startTime + gap*397, "D"),
	                new Beat(startTime + gap*397, "F"), //+3
	                
	                new Beat(startTime + gap*400, "J"), //머!
	                new Beat(startTime + gap*400, "K"),
	                new Beat(startTime + gap*400, "L"), //+3
	                
	                new Beat(startTime + gap*403, "Space"), //+5
	                
	                new Beat(startTime + gap*408, "S"), //벌
	                new Beat(startTime + gap*408, "D"),
	                new Beat(startTime + gap*408, "F"), //+3
	                
	                new Beat(startTime + gap*411, "J"), //써!
	                new Beat(startTime + gap*411, "K"),
	                new Beat(startTime + gap*411, "L"), //+4
	                
	                new Beat(startTime + gap*415, "Space"), //+5
	                                           //정
	                new Beat(startTime + gap*420, "S"), //신없이 +6
	                new Beat(startTime + gap*426, "D"), //달려왔
	                new Beat(startTime + gap*432, "L"), //어
	                new Beat(startTime + gap*438, "K"), 
	                                           //스피드
	                new Beat(startTime + gap*444, "F"), //업!
	                new Beat(startTime + gap*450, "J"), //+5 
	                new Beat(startTime + gap*455, "D"), //어제(로)
	                new Beat(startTime + gap*460, "K"), //+6
	                new Beat(startTime + gap*466, "D"),  //돌아(가)는 시 
	                new Beat(startTime + gap*466, "F"),  //돌아(가)는 시 
	                new Beat(startTime + gap*472, "J"),  //(곌) 보 
	                new Beat(startTime + gap*472, "K"),  //(곌) 보 +5
	                new Beat(startTime + gap*477, "Space"), //다 +5
	                new Beat(startTime + gap*482, "Space"), //가 OK +11
	                                           //어려
	                new Beat(startTime + gap*493, "F"), //워 +5
	                new Beat(startTime + gap*498, "J"), //+6
	                new Beat(startTime + gap*504, "F"), //어른(이)+6
	                new Beat(startTime + gap*510, "J"), //되어(가)는 +4
	                new Beat(startTime + gap*514, "S"), //+3
	                new Beat(startTime + gap*517, "L"),
	                new Beat(startTime + gap*520, "D"),
	                new Beat(startTime + gap*523, "K"),
	                new Beat(startTime + gap*526, "Space"), //+6
	                new Beat(startTime + gap*532, "Space"), //+6
	                
	                new Beat(startTime + gap*538, "S"), //온더(로)드 +5
	                new Beat(startTime + gap*543, "S"), // +6
	                new Beat(startTime + gap*549, "L"),
	                new Beat(startTime + gap*555, "L"), //+4
	                new Beat(startTime + gap*559, "D"), //+3
	                new Beat(startTime + gap*562, "F"),
	                new Beat(startTime + gap*565, "J"),
	                new Beat(startTime + gap*568, "K"),
	                new Beat(startTime + gap*571, "Space"), //+5
	                
	                new Beat(startTime + gap*576, "F"), // Big noise +6
	                new Beat(startTime + gap*582, "D"), //+5
	                new Beat(startTime + gap*587, "S"),
	                new Beat(startTime + gap*592, "J"), //+6
	                new Beat(startTime + gap*598, "K"),
	                new Beat(startTime + gap*604, "L"), //+4
	                new Beat(startTime + gap*608, "Space"), //+5
	                new Beat(startTime + gap*613, "Space"), //바뀌지 않는(걸) +7
	                                              //(바로 들어감)찾아나섰단 말야
	                new Beat(startTime + gap*620, "Space"),  //+6
	                new Beat(startTime + gap*626, "D"),
	                new Beat(startTime + gap*632, "F"), 
	                new Beat(startTime + gap*637, "J"),
	                new Beat(startTime + gap*642, "K"), //+5
	                new Beat(startTime + gap*647, "Space"), //+5
	                new Beat(startTime + gap*652, "Space"), // 좋았어 (난)~ +12 OK
	                
	                new Beat(startTime + gap*664, "Space"), //So~ +7
	                new Beat(startTime + gap*671, "S"), //+5
	                new Beat(startTime + gap*676, "D"),
	                new Beat(startTime + gap*681, "F"),
	                new Beat(startTime + gap*686, "F"), //+6
	                new Beat(startTime + gap*692, "J"), //+5
	                new Beat(startTime + gap*697, "K"),
	                new Beat(startTime + gap*702, "L"),
	                new Beat(startTime + gap*707, "L"), //+6
	                
	                new Beat(startTime + gap*713, "Space"),
	                new Beat(startTime + gap*719, "F"),
	                new Beat(startTime + gap*725, "J"),
	                new Beat(startTime + gap*731, "F"), //+6
	                new Beat(startTime + gap*737, "J"), //승호가 좋았을 때 처럼만~ +5
	                //
	                
	                new Beat(startTime + gap*742, "S"), //내가 +5
	                new Beat(startTime + gap*747, "F"), //슬플  +6
	                new Beat(startTime + gap*753, "D"), //때 마 
	                new Beat(startTime + gap*759, "F"), //다  +5
	                new Beat(startTime + gap*764, "L"), //이 노 
	                new Beat(startTime + gap*769, "J"), //래가 +6 
	                new Beat(startTime + gap*775, "K"), //찾아 +5
	                new Beat(startTime + gap*780, "J"), //와  +6 OK
	                                           
	                                           //세상
	                new Beat(startTime + gap*786, "S"), //이 둥 +5
	                new Beat(startTime + gap*786, "D"), //이 둥
	                new Beat(startTime + gap*791, "D"), //근 것
	                new Beat(startTime + gap*791, "F"),//근 것 +6
	                new Beat(startTime + gap*797, "K"), //처럼 
	                new Beat(startTime + gap*797, "L"), //처럼 +6
	                new Beat(startTime + gap*803, "J"), //우린
	                new Beat(startTime + gap*803, "K"), //우린 +5
	                new Beat(startTime + gap*808, "Space"), //+5
	                new Beat(startTime + gap*813, "Space"), //+12
	                new Beat(startTime + gap*825, "D"),
	                new Beat(startTime + gap*825, "F"),// +4
	                new Beat(startTime + gap*829, "J"),
	                new Beat(startTime + gap*829, "K"), //+5 OK
	                
	                                              //인생
	                new Beat(startTime + gap*834, "Space"),//은 회
	                new Beat(startTime + gap*839,"S"), //전 목 +6
	                new Beat(startTime + gap*845, "L"), //마
	                new Beat(startTime + gap*851, "D"),//우린
	                new Beat(startTime + gap*857, "K"), //매일
	                new Beat(startTime + gap*863, "F"), //달려
	                new Beat(startTime + gap*869, "J"), //가.. +6
	                                           
	                new Beat(startTime + gap*875, "S"), //언제 +5
	                new Beat(startTime + gap*880, "D"), //쯤
	                new Beat(startTime + gap*885, "F"), //끝나 
	                new Beat(startTime + gap*890, "L"), //난 
	                new Beat(startTime + gap*895, "K"), //잘몰
	                new Beat(startTime + gap*900, "J"), //라~ +5 
	                new Beat(startTime + gap*905, "Space"), //+8
	                new Beat(startTime + gap*913, "F"),
	                new Beat(startTime + gap*913, "Space"), //빙 +4
	                new Beat(startTime + gap*913, "J"),
	                new Beat(startTime + gap*917, "F"),
	                new Beat(startTime + gap*917, "Space"), //빙 OK
	                new Beat(startTime + gap*917, "J"), // +6
	                
	                new Beat(startTime + gap*923, "S"), //돌아가는 +5
	                new Beat(startTime + gap*928, "D"), //+6
	                new Beat(startTime + gap*934, "F"), 
	                new Beat(startTime + gap*940, "L"), //+5 
	                new Beat(startTime + gap*945, "K"), 
	                new Beat(startTime + gap*950, "J"), 
	                new Beat(startTime + gap*955, "Space"), 
	                new Beat(startTime + gap*960, "Space"), //+6
	                
	                new Beat(startTime + gap*966, "F"), //영(원)히  
	                new Beat(startTime + gap*972, "J"), //계속 
	                new Beat(startTime + gap*978, "D"),
	                new Beat(startTime + gap*984, "K"),
	                new Beat(startTime + gap*990, "Space"), //+5
	                new Beat(startTime + gap*995, "Space"), //+6
	                
	                new Beat(startTime + gap*1001, "F"),
	                new Beat(startTime + gap*1001, "Space"), //빙 +5 
	                new Beat(startTime + gap*1001, "J"),
	                new Beat(startTime + gap*1007, "F"),
	                new Beat(startTime + gap*1007, "Space"), //빙 OK
	                new Beat(startTime + gap*1007, "J"), //+6
	                
	                new Beat(startTime + gap*1013, "S"), //돌아가는 +5
	                new Beat(startTime + gap*1018, "D"), //+5
	                new Beat(startTime + gap*1023, "F"), 
	                new Beat(startTime + gap*1028, "L"), //+5 
	                new Beat(startTime + gap*1033, "K"), 
	                new Beat(startTime + gap*1038, "J"), 
	                new Beat(startTime + gap*1043, "Space"), 
	                new Beat(startTime + gap*1048, "Space"), //+6
	                
	                new Beat(startTime + gap*1054, "S"), //인생은 회전목마~ +6
	                new Beat(startTime + gap*1060, "D"),
	                new Beat(startTime + gap*1066, "L"), //마지막에 느려짐
	                new Beat(startTime + gap*1072, "K"), 
	                new Beat(startTime + gap*1078, "F"),
	                new Beat(startTime + gap*1078, "J"),
	                new Beat(startTime + gap*1084, "F"),
	                new Beat(startTime + gap*1084, "J"),
	                new Beat(startTime + gap*1090, "F"),
	                new Beat(startTime + gap*1090, "Space"), //OK
	                new Beat(startTime + gap*1090, "J")
	          };
	       }else if(titleName.equals("비밀번호486")&& difficulty.equals("Hard")) {
	           int startTime = 1000;
	           int gap = 137;
	           beats = new Beat[] {
	                 new Beat(startTime, "S"),
	                 new Beat(startTime +gap*1, "F"),
	                 new Beat(startTime +gap*3, "J"),
	                 new Beat(startTime +gap*5, "K"),
	                 new Beat(startTime +gap*7, "L"),
	                 new Beat(startTime +gap*9, "Space"),
	                 new Beat(startTime +gap*11, "D"),
	                 new Beat(startTime +gap*13, "F"),
	                 new Beat(startTime +gap*15, "S"),
	                 new Beat(startTime +gap*17, "J"),
	                 new Beat(startTime +gap*19, "D"),
	                 new Beat(startTime +gap*21, "F"),
	                 new Beat(startTime +gap*23, "L"),
	                 new Beat(startTime +gap*25, "S"),
	                 new Beat(startTime +gap*27, "D"),
	                 new Beat(startTime +gap*29, "J"),
	                 new Beat(startTime +gap*31, "K"),
	                 new Beat(startTime +gap*33, "S"),
	                 new Beat(startTime +gap*35, "D"),
	                 new Beat(startTime +gap*37, "F"),
	                 new Beat(startTime +gap*39, "L"),
	                 new Beat(startTime +gap*41, "Space"),
	                 new Beat(startTime +gap*43, "S"),
	                 new Beat(startTime +gap*45, "F"),
	                 new Beat(startTime +gap*47, "J"),
	                 new Beat(startTime +gap*51, "K"),
	                 new Beat(startTime +gap*53, "L"),
	                 new Beat(startTime +gap*55, "J"),
	                 new Beat(startTime +gap*57, "K"),
	                 new Beat(startTime +gap*59, "L"),
	                 new Beat(startTime +gap*61, "S"),
	                 new Beat(startTime +gap*63, "D"),
	                 new Beat(startTime +gap*65, "F"),
	                 new Beat(startTime +gap*67, "J"),
	                 new Beat(startTime +gap*69, "K"),
	                 new Beat(startTime +gap*71, "L"),
	                 new Beat(startTime +gap*73, "S"),
	                 new Beat(startTime +gap*75, "D"),
	                 new Beat(startTime +gap*79, "D"),
	                 new Beat(startTime +gap*81, "F"),
	                 new Beat(startTime +gap*83, "L"),
	                 new Beat(startTime +gap*85, "S"),
	                 new Beat(startTime +gap*87, "D"),
	                 new Beat(startTime +gap*91, "J"),
	                 new Beat(startTime +gap*93, "K"),
	                 new Beat(startTime +gap*95, "S"),
	                 new Beat(startTime +gap*97, "D"),
	                 new Beat(startTime +gap*99, "F"),
	                 new Beat(startTime +gap*101, "L"),
	                 new Beat(startTime +gap*103, "Space"),
	                 new Beat(startTime +gap*105, "S"),
	                 new Beat(startTime +gap*107, "F"),
	                 new Beat(startTime +gap*109, "J"),
	                 new Beat(startTime +gap*111, "K"),
	                 new Beat(startTime +gap*113, "L"),
	                 new Beat(startTime +gap*115, "J"),
	                 new Beat(startTime +gap*117, "K"),
	                 new Beat(startTime +gap*121, "L"),
	                 new Beat(startTime +gap*123, "S"),
	                 new Beat(startTime +gap*125, "D"),
	                 new Beat(startTime +gap*127, "F"),
	                 new Beat(startTime +gap*129, "J"),
	                 new Beat(startTime +gap*131, "K"),
	                 new Beat(startTime +gap*135, "L"),
	                 new Beat(startTime +gap*137, "S"),
	                 new Beat(startTime +gap*139, "D"),
	                 new Beat(startTime +gap*140, "F"),
	                 new Beat(startTime +gap*141, "L"),
	                 new Beat(startTime +gap*143, "S"),
	                 new Beat(startTime +gap*145, "D"),
	                 new Beat(startTime +gap*147, "J"),
	                 new Beat(startTime +gap*148, "K"),
	                 new Beat(startTime +gap*149, "S"),
	                 new Beat(startTime +gap*150, "D"),
	                 new Beat(startTime +gap*151, "F"),
	                 new Beat(startTime +gap*152, "L"),
	                 new Beat(startTime +gap*153, "Space"),
	                 new Beat(startTime +gap*154, "S"),
	                 new Beat(startTime +gap*155, "F"),
	                 new Beat(startTime +gap*156, "J"),
	                 new Beat(startTime +gap*157, "K"),
	                 new Beat(startTime +gap*158, "L"),
	                 new Beat(startTime +gap*159, "J"),
	                 new Beat(startTime +gap*160, "K"),
	                 new Beat(startTime +gap*161, "L"),
	                 new Beat(startTime +gap*163, "S"),
	                 new Beat(startTime +gap*165, "D"),
	                 new Beat(startTime +gap*167, "F"),
	                 new Beat(startTime +gap*169, "J"),
	                 new Beat(startTime +gap*170, "K"),
	                 new Beat(startTime +gap*171, "L"),
	                 new Beat(startTime +gap*173, "S"),
	                 new Beat(startTime +gap*175, "D"),
	                 new Beat(startTime +gap*177, "J"),
	                 new Beat(startTime +gap*179, "K"),
	                 new Beat(startTime +gap*180, "L"),
	                 new Beat(startTime +gap*181, "J"),
	                 new Beat(startTime +gap*182, "K"),
	                 new Beat(startTime +gap*184, "L"),
	                 new Beat(startTime +gap*185, "S"),
	                 new Beat(startTime +gap*186, "D"),
	                 new Beat(startTime +gap*188, "F"),
	                 new Beat(startTime +gap*190, "J"),
	                 new Beat(startTime +gap*191, "K"),
	                 new Beat(startTime +gap*192, "L"),
	                 new Beat(startTime +gap*193, "S"),
	                 new Beat(startTime +gap*195, "D"),
	                 new Beat(startTime +gap*197, "F"),
	                 new Beat(startTime +gap*200, "L"),
	                 new Beat(startTime +gap*202, "S"),
	                 new Beat(startTime +gap*203, "D"),
	                 new Beat(startTime +gap*204, "J"),
	                 new Beat(startTime +gap*205, "K"),
	                 new Beat(startTime +gap*207, "S"),
	                 new Beat(startTime +gap*209, "D"),
	                 new Beat(startTime +gap*211, "F"),
	                 new Beat(startTime +gap*212, "L"),
	                 new Beat(startTime +gap*214, "Space"),
	                 new Beat(startTime +gap*216, "S"),
	                 new Beat(startTime +gap*218, "F"),
	                 new Beat(startTime +gap*220, "J"),
	                 new Beat(startTime +gap*222, "K"),
	                 new Beat(startTime +gap*224, "L"),
	                 new Beat(startTime +gap*226, "J"),
	                 new Beat(startTime +gap*228, "K"),
	                 new Beat(startTime +gap*230, "L"),
	                 new Beat(startTime +gap*231, "S"),
	                 new Beat(startTime +gap*232, "D"),
	                 new Beat(startTime +gap*234, "F"),
	                 new Beat(startTime +gap*236, "J"),
	                 new Beat(startTime +gap*238, "K"),
	                 new Beat(startTime +gap*240, "L"),
	                 new Beat(startTime +gap*241, "S"),
	                 new Beat(startTime +gap*242, "D"),
	                 new Beat(startTime +gap*243, "L"),
	                 new Beat(startTime +gap*245, "Space"),
	                 new Beat(startTime +gap*247, "S"),
	                 new Beat(startTime +gap*250, "F"),
	                 new Beat(startTime +gap*251, "J"),
	                 new Beat(startTime +gap*252, "K"),
	                 new Beat(startTime +gap*254, "L"),
	                 new Beat(startTime +gap*256, "J"),
	                 new Beat(startTime +gap*258, "K"),
	                 new Beat(startTime +gap*260, "L"),
	                 new Beat(startTime +gap*261, "S"),
	                 new Beat(startTime +gap*262, "D"),
	                 new Beat(startTime +gap*264, "F"),
	                 new Beat(startTime +gap*270, "J"),
	                 new Beat(startTime +gap*272, "K"),
	                 new Beat(startTime +gap*274, "L"),
	                 new Beat(startTime +gap*276, "S"),
	                 new Beat(startTime +gap*280, "L"),
	                 new Beat(startTime +gap*282, "Space"),
	                 new Beat(startTime +gap*284, "S"),
	                 new Beat(startTime +gap*285, "F"),
	                 new Beat(startTime +gap*286, "J"),
	                 new Beat(startTime +gap*288, "K"),
	                 new Beat(startTime +gap*290, "L"),
	                 new Beat(startTime +gap*292, "J"),
	                 new Beat(startTime +gap*294, "K"),
	                 new Beat(startTime +gap*298, "L"),
	                 new Beat(startTime +gap*300, "S"),
	                 new Beat(startTime +gap*303, "D"),
	                 new Beat(startTime +gap*306, "F"),
	                 new Beat(startTime +gap*310, "J"),
	                 new Beat(startTime +gap*313, "K"),
	                 new Beat(startTime +gap*318, "L"),
	                 new Beat(startTime +gap*320, "S"),      
	           };
	        }else if(titleName.equals("비밀번호486")&& difficulty.equals("Easy")) {
	           int startTime = 1000;
	           int gap = 137;
	           beats = new Beat[] {
	                 new Beat(startTime, "S"),
	                 new Beat(startTime +gap*1, "F"),
	                 new Beat(startTime +gap*3, "J"),
	                 new Beat(startTime +gap*5, "K"),
	                 new Beat(startTime +gap*7, "L"),
	                 new Beat(startTime +gap*9, "Space"),
	                 new Beat(startTime +gap*11, "D"),
	                 new Beat(startTime +gap*25, "S"),
	                 new Beat(startTime +gap*27, "D"),
	                 new Beat(startTime +gap*29, "J"),
	                 new Beat(startTime +gap*41, "Space"),
	                 new Beat(startTime +gap*43, "S"),
	                 new Beat(startTime +gap*45, "F"),
	                 new Beat(startTime +gap*47, "J"),
	                 new Beat(startTime +gap*53, "L"),
	                 new Beat(startTime +gap*55, "J"),
	                 new Beat(startTime +gap*57, "K"),
	                 new Beat(startTime +gap*73, "S"),
	                 new Beat(startTime +gap*75, "D"),
	                 new Beat(startTime +gap*79, "D"),
	                 new Beat(startTime +gap*91, "J"),
	                 new Beat(startTime +gap*95, "S"),
	                 new Beat(startTime +gap*97, "D"),
	                 new Beat(startTime +gap*99, "F"),
	                 new Beat(startTime +gap*117, "K"),
	                 new Beat(startTime +gap*121, "L"),
	                 new Beat(startTime +gap*123, "S"),
	                 new Beat(startTime +gap*129, "J"),
	                 new Beat(startTime +gap*140, "F"),
	                 new Beat(startTime +gap*141, "L"),
	                 new Beat(startTime +gap*153, "Space"),
	                 new Beat(startTime +gap*154, "S"),
	                 new Beat(startTime +gap*155, "F"),
	                 new Beat(startTime +gap*156, "J"),
	                 new Beat(startTime +gap*157, "K"),
	                 new Beat(startTime +gap*160, "K"),
	                 new Beat(startTime +gap*161, "L"),
	                 new Beat(startTime +gap*165, "D"),
	                 new Beat(startTime +gap*167, "F"),
	                 new Beat(startTime +gap*169, "J"),
	                 new Beat(startTime +gap*170, "K"),
	                 new Beat(startTime +gap*171, "L"),
	                 new Beat(startTime +gap*173, "S"),
	                 new Beat(startTime +gap*179, "K"),
	                 new Beat(startTime +gap*180, "L"),
	                 new Beat(startTime +gap*195, "D"),
	                 new Beat(startTime +gap*197, "F"),
	                 new Beat(startTime +gap*200, "L"),
	                 new Beat(startTime +gap*202, "S"),
	                 new Beat(startTime +gap*207, "S"),
	                 new Beat(startTime +gap*209, "D"),
	                 new Beat(startTime +gap*211, "F"),
	                 new Beat(startTime +gap*212, "L"),
	                 new Beat(startTime +gap*214, "Space"),
	                 new Beat(startTime +gap*216, "S"),
	                 new Beat(startTime +gap*218, "F"),
	                 new Beat(startTime +gap*240, "L"),
	                 new Beat(startTime +gap*243, "L"),
	                 new Beat(startTime +gap*245, "Space"),
	                 new Beat(startTime +gap*247, "S"),
	                 new Beat(startTime +gap*250, "F"),
	                 new Beat(startTime +gap*252, "K"),
	                 new Beat(startTime +gap*254, "L"),
	                 new Beat(startTime +gap*256, "J"),
	                 new Beat(startTime +gap*258, "K"),
	                 new Beat(startTime +gap*260, "L"),
	                 new Beat(startTime +gap*261, "S"),
	                 new Beat(startTime +gap*262, "D"),
	                 new Beat(startTime +gap*264, "F"),
	                 new Beat(startTime +gap*286, "J"),
	                 new Beat(startTime +gap*288, "K"),
	                 new Beat(startTime +gap*290, "L"),
	           };
	        }else if(titleName.equals("신호등")&& difficulty.equals("Hard")) {
	         int startTime = 4000 - Main.REACH_TIME*1000;
	         int gap = 295;
	         beats = new Beat[] {
	               new Beat(startTime +gap*9, "S")
	               , new Beat(startTime +gap*10, "D")
	               , new Beat(startTime +gap*11, "F")
	               , new Beat(startTime +gap*15, "J")
	               , new Beat(startTime +gap*16, "K")
	               , new Beat(startTime +gap*17, "L")
	               , new Beat(startTime +gap*20, "D")
	               , new Beat(startTime +gap*22, "J")
	               , new Beat(startTime +gap*24, "S")
	               , new Beat(startTime +gap*26, "J")
	               , new Beat(startTime +gap*28, "F")
	               , new Beat(startTime +gap*30, "L")
	               , new Beat(startTime +gap*33, "Space")
	               , new Beat(startTime +gap*33, "F")
	               , new Beat(startTime +gap*33, "J")
	               , new Beat(startTime +gap*36, "D")
	               , new Beat(startTime +gap*38, "F")
	               , new Beat(startTime +gap*40, "F")
	               , new Beat(startTime +gap*49, "S")
	               , new Beat(startTime +gap*52, "K")
	               , new Beat(startTime +gap*54, "D")
	               , new Beat(startTime +gap*54, "L")
	               , new Beat(startTime +gap*56, "L")
	               , new Beat(startTime +gap*57, "F")
	               , new Beat(startTime +gap*59, "J")
	               , new Beat(startTime +gap*61, "J")
	               , new Beat(startTime +gap*63, "F")
	               , new Beat(startTime +gap*63, "J")
	               , new Beat(startTime +gap*63, "Space")
	               , new Beat(startTime +gap*70, "K")
	               , new Beat(startTime +gap*72, "L")
	               , new Beat(startTime +gap*73, "Space")
	               , new Beat(startTime +gap*77, "L")
	               , new Beat(startTime +gap*79, "J")
	               , new Beat(startTime +gap*79, "K")
	               , new Beat(startTime +gap*83, "L")
	               , new Beat(startTime +gap*85, "Space")
	               , new Beat(startTime +gap*85, "S")
	               , new Beat(startTime +gap*90, "D")
	               , new Beat(startTime +gap*92, "J")
	               , new Beat(startTime +gap*94, "S")
	               , new Beat(startTime +gap*96, "L")
	               , new Beat(startTime +gap*98, "S")
	               , new Beat(startTime +gap*98, "F")
	               , new Beat(startTime +gap*98, "K")
	               , new Beat(startTime +gap*102, "Space")
	               , new Beat(startTime +gap*102, "D")
	               , new Beat(startTime +gap*106, "J")
	               , new Beat(startTime +gap*108, "K")
	               , new Beat(startTime +gap*110, "J")
	               , new Beat(startTime +gap*112, "S")
	               , new Beat(startTime +gap*118, "F")
	               , new Beat(startTime +gap*120, "F")
	               , new Beat(startTime +gap*120, "Space")
	               , new Beat(startTime +gap*125, "S")
	               , new Beat(startTime +gap*127, "J")
	               , new Beat(startTime +gap*128, "S")
	               , new Beat(startTime +gap*129, "K")
	               , new Beat(startTime +gap*130, "S")
	               , new Beat(startTime +gap*131, "L")
	               , new Beat(startTime +gap*132, "F")
	               , new Beat(startTime +gap*134, "Space")   
	               , new Beat(startTime +gap*135, "Space")   
	               , new Beat(startTime +gap*136, "Space")
	               , new Beat(startTime +gap*137, "Space")
	               , new Beat(startTime +gap*139, "J")
	               , new Beat(startTime +gap*140, "K")
	               , new Beat(startTime +gap*141, "L")
	               , new Beat(startTime +gap*142, "F")
	               , new Beat(startTime +gap*144, "F")
	               , new Beat(startTime +gap*145, "D")
	               , new Beat(startTime +gap*147, "J")
	               , new Beat(startTime +gap*149, "J")
	               , new Beat(startTime +gap*151, "S")
	               , new Beat(startTime +gap*152, "L")
	               , new Beat(startTime +gap*154, "Space")
	               , new Beat(startTime +gap*156, "S")
	               , new Beat(startTime +gap*158, "D")
	               , new Beat(startTime +gap*160, "S")
	               , new Beat(startTime +gap*162, "D")
	               , new Beat(startTime +gap*163, "Space")
	               , new Beat(startTime +gap*165, "L")
	               , new Beat(startTime +gap*167, "L")
	               , new Beat(startTime +gap*169, "K")
	               , new Beat(startTime +gap*171, "J")
	               , new Beat(startTime +gap*173, "S")
	               , new Beat(startTime +gap*173, "L")
	               , new Beat(startTime +gap*175, "J")
	               , new Beat(startTime +gap*175, "D")
	               , new Beat(startTime +gap*177, "K")
	               , new Beat(startTime +gap*177, "Space")
	               , new Beat(startTime +gap*179, "F")
	               , new Beat(startTime +gap*181, "D")
	               , new Beat(startTime +gap*183, "S")
	               , new Beat(startTime +gap*185, "L")
	               , new Beat(startTime +gap*187, "K")
	               , new Beat(startTime +gap*190, "J")
	               , new Beat(startTime +gap*191, "L")
	               , new Beat(startTime +gap*192, "L")
	               , new Beat(startTime +gap*194, "S")
	               , new Beat(startTime +gap*197, "F")
	               , new Beat(startTime +gap*197, "Space")
	               , new Beat(startTime +gap*197, "J")
	         };
	      }else if(titleName.equals("신호등")&& difficulty.equals("Easy")) {
	         int startTime = 4000 - Main.REACH_TIME*1000;
	         int gap = 350;
	         beats = new Beat[] {
	               new Beat(startTime +gap*9, "S")
	               , new Beat(startTime +gap*11, "F")
	               , new Beat(startTime +gap*15, "J") 
	               , new Beat(startTime +gap*17, "L")
	               , new Beat(startTime +gap*20, "D")
	               , new Beat(startTime +gap*22, "J")
	               , new Beat(startTime +gap*24, "F")
	               , new Beat(startTime +gap*26, "L")
	               , new Beat(startTime +gap*26, "Space")
	               , new Beat(startTime +gap*33, "F")
	               , new Beat(startTime +gap*35, "J")
	               , new Beat(startTime +gap*39, "S")
	               , new Beat(startTime +gap*41, "L")
	               , new Beat(startTime +gap*45, "Space")
	               , new Beat(startTime +gap*47, "K")
	               , new Beat(startTime +gap*49, "S")
	               , new Beat(startTime +gap*51, "D")
	               , new Beat(startTime +gap*51, "Space")
	               , new Beat(startTime +gap*55, "J")
	               , new Beat(startTime +gap*57, "F")
	               , new Beat(startTime +gap*59, "J")
	               , new Beat(startTime +gap*63, "Space")
	               , new Beat(startTime +gap*65, "K")
	               , new Beat(startTime +gap*67, "L")
	               , new Beat(startTime +gap*70, "L")
	               , new Beat(startTime +gap*72, "J")
	               , new Beat(startTime +gap*74, "K")
	               , new Beat(startTime +gap*76, "L")
	               , new Beat(startTime +gap*80, "Space")
	               , new Beat(startTime +gap*82, "S")
	               , new Beat(startTime +gap*84, "D")
	               , new Beat(startTime +gap*86, "J")
	               , new Beat(startTime +gap*88, "S")
	               , new Beat(startTime +gap*90, "L")
	               , new Beat(startTime +gap*95, "F")
	               , new Beat(startTime +gap*97, "K")
	               , new Beat(startTime +gap*99, "Space")
	               , new Beat(startTime +gap*101, "D")
	               , new Beat(startTime +gap*105, "J")
	               , new Beat(startTime +gap*117, "J")
	               , new Beat(startTime +gap*119, "S")
	               , new Beat(startTime +gap*121, "F")
	               , new Beat(startTime +gap*124, "Space")
	               , new Beat(startTime +gap*126, "S")
	               , new Beat(startTime +gap*130, "S")
	               , new Beat(startTime +gap*132, "K")
	               , new Beat(startTime +gap*135, "L")
	               , new Beat(startTime +gap*137, "F")
	               , new Beat(startTime +gap*139, "Space")   
	               , new Beat(startTime +gap*143, "Space")
	               , new Beat(startTime +gap*145, "J")
	               , new Beat(startTime +gap*147, "K")
	               , new Beat(startTime +gap*150, "L")
	               , new Beat(startTime +gap*152, "F")
	               , new Beat(startTime +gap*155, "S")
	               , new Beat(startTime +gap*157, "D")
	               , new Beat(startTime +gap*158, "S")
	               , new Beat(startTime +gap*159, "Space")
	               , new Beat(startTime +gap*163, "J")
	               , new Beat(startTime +gap*165, "F")
	               , new Beat(startTime +gap*169, "J")
	               , new Beat(startTime +gap*173, "F")
	               , new Beat(startTime +gap*173, "Space")
	         };
	     }else if(titleName.equals("덤디덤디")&& difficulty.equals("Hard")) {
	    	 int startTime = 1500 - Main.REACH_TIME*1000;
	            int gap = 250;
	            beats = new Beat[] {
	                  new Beat(startTime +gap, "S")//엥~ 엥엥엥~
	                  , new Beat(startTime +gap*4, "D")
	                  , new Beat(startTime +gap*4, "K")
	                  , new Beat(startTime +gap*6, "F")
	                  , new Beat(startTime +gap*6, "J")
	                  , new Beat(startTime +gap*8, "K")
	                  , new Beat(startTime +gap*8, "L")
	                  , new Beat(startTime +gap*11, "S")
	                  , new Beat(startTime +gap*11, "L")
	                  , new Beat(startTime +gap*13, "J")
	                  , new Beat(startTime +gap*13, "F")
	                  , new Beat(startTime +gap*15, "K")
	                  , new Beat(startTime +gap*15, "S")
	                  , new Beat(startTime +gap*16, "Space")
	                  , new Beat(startTime +gap*18, "S")
	                  , new Beat(startTime +gap*19, "J")
	                  , new Beat(startTime +gap*20, "K")//뜨거운~ 태양에 살짝 미친 나
	                  , new Beat(startTime +gap*24, "L")
	                  , new Beat(startTime +gap*24, "D")
	                  , new Beat(startTime +gap*26, "K")
	                  , new Beat(startTime +gap*27, "S")//살짝 미친 나~                  
	                  , new Beat(startTime +gap*29, "L")
	                  , new Beat(startTime +gap*29, "K")
	                  , new Beat(startTime +gap*30, "S")
	                  , new Beat(startTime +gap*30, "D")
	                  , new Beat(startTime +gap*31, "Space")//여기까지 살짝 미친 나~
	                  , new Beat(startTime +gap*33, "D")
	                  , new Beat(startTime +gap*33, "F")
	                  , new Beat(startTime +gap*34, "F")
	                  , new Beat(startTime +gap*35, "K")
	                  , new Beat(startTime +gap*36, "L")
	                  , new Beat(startTime +gap*36, "L")
	                  , new Beat(startTime +gap*37, "Space")//헤엄을 치듯 춤을 추고~
	                  , new Beat(startTime +gap*38, "Space")
	                  , new Beat(startTime +gap*40, "L")
	                  , new Beat(startTime +gap*41, "Space")
	                  , new Beat(startTime +gap*42, "S")
	                  , new Beat(startTime +gap*42, "L")
	                  , new Beat(startTime +gap*43, "L")
	                  , new Beat(startTime +gap*43, "D")
	                  , new Beat(startTime +gap*44, "S")
	                  , new Beat(startTime +gap*45, "S")
	                  , new Beat(startTime +gap*45, "F")
	                  , new Beat(startTime +gap*47, "Space")
	                  , new Beat(startTime +gap*48, "Space")
	                  , new Beat(startTime +gap*50, "S")
	                  , new Beat(startTime +gap*52, "D")
	                  , new Beat(startTime +gap*52, "F")
	                  , new Beat(startTime +gap*54, "F")
	                  , new Beat(startTime +gap*54, "J")
	                  , new Beat(startTime +gap*56, "J")
	                  , new Beat(startTime +gap*56, "K")
	                  , new Beat(startTime +gap*58, "K")
	                  , new Beat(startTime +gap*58, "L")
	                  , new Beat(startTime +gap*60, "L")
	                  , new Beat(startTime +gap*60, "Space")
	                  , new Beat(startTime +gap*62, "S")
	                  , new Beat(startTime +gap*65, "J")
	                  , new Beat(startTime +gap*66, "K")
	                  , new Beat(startTime +gap*67, "L")
	                  , new Beat(startTime +gap*70, "Space")
	                  , new Beat(startTime +gap*71, "F")
	                  , new Beat(startTime +gap*73, "D")
	                  , new Beat(startTime +gap*75, "S")
	                  , new Beat(startTime +gap*78, "Space")
	                  , new Beat(startTime +gap*82, "Space")//음악을 더 크게 틀고싶어~ 전에
	                  , new Beat(startTime +gap*83, "Space")
	                  , new Beat(startTime +gap*84, "Space")
	                  , new Beat(startTime +gap*85, "L")
	                  , new Beat(startTime +gap*87, "K")
	                  , new Beat(startTime +gap*89, "J")
	                  , new Beat(startTime +gap*91, "L")
	                  , new Beat(startTime +gap*92, "J")
	                  , new Beat(startTime +gap*93, "D")
	                  , new Beat(startTime +gap*94, "S")
	                  , new Beat(startTime +gap*95, "F")
	                  , new Beat(startTime +gap*96, "J")
	                  , new Beat(startTime +gap*97, "K")
	                  , new Beat(startTime +gap*97, "L")
	                  , new Beat(startTime +gap*99, "S")
	                  , new Beat(startTime +gap*99, "F")
	                  , new Beat(startTime +gap*100, "Space")
	                  , new Beat(startTime +gap*101, "J")
	                  , new Beat(startTime +gap*101, "L")
	                  , new Beat(startTime +gap*103, "S")
	                  , new Beat(startTime +gap*103, "F")
	                  , new Beat(startTime +gap*105, "L")
	                  , new Beat(startTime +gap*105, "K")
	                  , new Beat(startTime +gap*107, "D")
	                  , new Beat(startTime +gap*107, "Space")
	                  , new Beat(startTime +gap*109, "K")
	                  , new Beat(startTime +gap*111, "F")
	                  , new Beat(startTime +gap*113, "Space")
	                  , new Beat(startTime +gap*115, "Space")
	                  , new Beat(startTime +gap*117, "F")
	                  , new Beat(startTime +gap*118, "D")
	                  , new Beat(startTime +gap*118, "S")
	                  , new Beat(startTime +gap*120, "Space")
	                  , new Beat(startTime +gap*122, "L")
	                  , new Beat(startTime +gap*123, "L")
	                  , new Beat(startTime +gap*124, "K")
	                  , new Beat(startTime +gap*126, "D")
	                  , new Beat(startTime +gap*126, "F")
	                  , new Beat(startTime +gap*128, "F")
	                  , new Beat(startTime +gap*129, "S")
	                  , new Beat(startTime +gap*130, "Space")
	                  , new Beat(startTime +gap*132, "S")
	                  , new Beat(startTime +gap*132, "Space")
	                  , new Beat(startTime +gap*135, "D")
	                  , new Beat(startTime +gap*137, "D")
	                  , new Beat(startTime +gap*137, "Space")
	                  , new Beat(startTime +gap*140, "Space")
	                  , new Beat(startTime +gap*145, "Space")
	                  , new Beat(startTime +gap*150, "Space")
	                  , new Beat(startTime +gap*152, "S")
	                  , new Beat(startTime +gap*152, "F")
	                  , new Beat(startTime +gap*154, "J")
	                  , new Beat(startTime +gap*154, "L")
	                  , new Beat(startTime +gap*155, "S")
	                  , new Beat(startTime +gap*156, "D")
	                  , new Beat(startTime +gap*157, "F")
	                  , new Beat(startTime +gap*158, "F")
	                  , new Beat(startTime +gap*161, "Space")
	                  , new Beat(startTime +gap*162, "S")
	                  , new Beat(startTime +gap*164, "F")
	                  , new Beat(startTime +gap*166, "J")
	                  , new Beat(startTime +gap*166, "J")
	                  , new Beat(startTime +gap*168, "L")
	                  , new Beat(startTime +gap*169, "J")
	                  , new Beat(startTime +gap*170, "Space")
	                  , new Beat(startTime +gap*172, "D")
	                  , new Beat(startTime +gap*172, "S")
	                  , new Beat(startTime +gap*174, "K")
	                  , new Beat(startTime +gap*174, "J")
	                  , new Beat(startTime +gap*176, "S")
	                  , new Beat(startTime +gap*178, "D")
	                  , new Beat(startTime +gap*178, "F")
	                  , new Beat(startTime +gap*180, "J")
	                  , new Beat(startTime +gap*180, "K")
	                  , new Beat(startTime +gap*182, "Space")
	                  , new Beat(startTime +gap*183, "Space")
	                  , new Beat(startTime +gap*184, "S")
	                  , new Beat(startTime +gap*186, "F")
	                  , new Beat(startTime +gap*186, "S")
	                  , new Beat(startTime +gap*188, "Space")
	                  , new Beat(startTime +gap*190, "J")
	                  , new Beat(startTime +gap*192, "L")
	                  , new Beat(startTime +gap*193, "K")
	                  , new Beat(startTime +gap*194, "J")
	                  , new Beat(startTime +gap*196, "D")
	                  , new Beat(startTime +gap*198, "Space")
	                  , new Beat(startTime +gap*200, "Space")
	                  , new Beat(startTime +gap*201, "S")
	                  , new Beat(startTime +gap*201, "F")
	                  , new Beat(startTime +gap*203, "S")
	                  , new Beat(startTime +gap*204, "Space")
	                  , new Beat(startTime +gap*206, "D")
	                  , new Beat(startTime +gap*208, "Space")
	                  , new Beat(startTime +gap*210, "Space")
	                  , new Beat(startTime +gap*212, "S")
	                  , new Beat(startTime +gap*214, "F")
	                  , new Beat(startTime +gap*215, "S")
	                  , new Beat(startTime +gap*216, "F")
	                  , new Beat(startTime +gap*216, "S")
	                  , new Beat(startTime +gap*217, "S")
	                  , new Beat(startTime +gap*218, "F")
	                  , new Beat(startTime +gap*219, "J")
	                  , new Beat(startTime +gap*219, "L")
	                  , new Beat(startTime +gap*220, "L")
	                  , new Beat(startTime +gap*221, "J")
	                  , new Beat(startTime +gap*222, "L")
	                  , new Beat(startTime +gap*225, "D")
	                  , new Beat(startTime +gap*225, "S")
	                  , new Beat(startTime +gap*226, "Space")
	                  , new Beat(startTime +gap*228, "K")
	                  , new Beat(startTime +gap*231, "D")
	                  , new Beat(startTime +gap*233, "K")
	                  , new Beat(startTime +gap*235, "Space")
	                  , new Beat(startTime +gap*237, "S")
	                  , new Beat(startTime +gap*237, "L")
	                  , new Beat(startTime +gap*239, "D")
	                  , new Beat(startTime +gap*239, "K")
	                  , new Beat(startTime +gap*241, "F")
	                  , new Beat(startTime +gap*241, "J")
	                  , new Beat(startTime +gap*244, "L")
	                  , new Beat(startTime +gap*246, "K")
	                  , new Beat(startTime +gap*248, "J")
	                  , new Beat(startTime +gap*252, "K")
	                  , new Beat(startTime +gap*254, "J")
	                  , new Beat(startTime +gap*256, "L")
	                  , new Beat(startTime +gap*258, "D")
	                  , new Beat(startTime +gap*260, "S")
	                  , new Beat(startTime +gap*262, "F")
	                  , new Beat(startTime +gap*263, "S")
	                  , new Beat(startTime +gap*263, "F")
	                  , new Beat(startTime +gap*264, "J")
	                  , new Beat(startTime +gap*264, "K")
	                  , new Beat(startTime +gap*265, "K")
	                  , new Beat(startTime +gap*267, "L")
	                  , new Beat(startTime +gap*268, "K")
	                  , new Beat(startTime +gap*269, "J")
	                  , new Beat(startTime +gap*269, "S")
	                  , new Beat(startTime +gap*270, "D")
	                  , new Beat(startTime +gap*271, "F")
	                  , new Beat(startTime +gap*272, "Space")
	                  , new Beat(startTime +gap*274, "Space")
	                  , new Beat(startTime +gap*274, "S")
	                  , new Beat(startTime +gap*275, "D")
	                  , new Beat(startTime +gap*276, "F")
	                  , new Beat(startTime +gap*278, "J")
	                  , new Beat(startTime +gap*279, "K")
	                  , new Beat(startTime +gap*280, "L")
	                  
	                  , new Beat(startTime +gap*281, "Space")//덤디덤디 덤디덤디 휘휙~
	                  , new Beat(startTime +gap*282, "Space")
	                  , new Beat(startTime +gap*284, "Space")
	                  , new Beat(startTime +gap*286, "J")
	                  , new Beat(startTime +gap*286, "L")
	                  , new Beat(startTime +gap*287, "S")
	                  , new Beat(startTime +gap*288, "D")
	                  , new Beat(startTime +gap*289, "F")
	                  , new Beat(startTime +gap*290, "S")
	                  , new Beat(startTime +gap*290, "F")
	                  , new Beat(startTime +gap*291, "D")
	                  , new Beat(startTime +gap*293, "Space")
	                  , new Beat(startTime +gap*294, "Space")
	                  , new Beat(startTime +gap*296, "Space")
	                  , new Beat(startTime +gap*298, "Space")
	                  , new Beat(startTime +gap*300, "J")
	                  , new Beat(startTime +gap*300, "L")
	                  , new Beat(startTime +gap*300, "K")
	                  , new Beat(startTime +gap*301, "K")
	                  , new Beat(startTime +gap*305, "Space")
	                  , new Beat(startTime +gap*306, "Space")
	                  , new Beat(startTime +gap*307, "Space")
	                  , new Beat(startTime +gap*310, "S")
	                  , new Beat(startTime +gap*311, "D")
	                  , new Beat(startTime +gap*312, "F")
	                  , new Beat(startTime +gap*313, "J")
	                  , new Beat(startTime +gap*314, "K")
	                  , new Beat(startTime +gap*315, "L")
	                  , new Beat(startTime +gap*317, "J")
	                  , new Beat(startTime +gap*319, "L")
	                  , new Beat(startTime +gap*320, "Space")//덤디덤디 덤디덤디 휘휙~
	                  , new Beat(startTime +gap*321, "Space")
	                  , new Beat(startTime +gap*322, "Space")
	                  , new Beat(startTime +gap*325, "S")
	                  , new Beat(startTime +gap*325, "F")
	                  , new Beat(startTime +gap*326, "D")
	                  , new Beat(startTime +gap*326, "S")
	                  , new Beat(startTime +gap*328, "S")
	                  , new Beat(startTime +gap*328, "D")
	                  , new Beat(startTime +gap*330, "Space")
	                  , new Beat(startTime +gap*331, "Space")
	                  , new Beat(startTime +gap*332, "Space")
	                  , new Beat(startTime +gap*333, "Space")
	                  , new Beat(startTime +gap*335, "J")
	                  , new Beat(startTime +gap*336, "K")
	                  , new Beat(startTime +gap*336, "J")
	                  , new Beat(startTime +gap*336, "L")
	                  , new Beat(startTime +gap*340, "Space")
	                  , new Beat(startTime +gap*341, "Space")
	                  , new Beat(startTime +gap*342, "Space")
	                  , new Beat(startTime +gap*343, "Space")
	                  , new Beat(startTime +gap*346, "S")
	                  , new Beat(startTime +gap*347, "D")
	                  , new Beat(startTime +gap*348, "F")
	                  , new Beat(startTime +gap*349, "J")
	                  , new Beat(startTime +gap*350, "K")
	                  , new Beat(startTime +gap*351, "L")
			};
		}else if(titleName.equals("덤디덤디")&& difficulty.equals("Easy")) {
			int startTime = 1500 - Main.REACH_TIME*1000;
            int gap = 250;
            beats = new Beat[] {
                  new Beat(startTime +gap, "S")
                  , new Beat(startTime +gap*4, "D")
                  , new Beat(startTime +gap*6, "F")
                  , new Beat(startTime +gap*8, "K")
                  , new Beat(startTime +gap*8, "L")
                  , new Beat(startTime +gap*10, "S")
                  , new Beat(startTime +gap*12, "J")
                  , new Beat(startTime +gap*14, "K")
                  , new Beat(startTime +gap*15, "Space")
                  , new Beat(startTime +gap*18, "S")
                  , new Beat(startTime +gap*19, "J")
                  , new Beat(startTime +gap*20, "K")
                  , new Beat(startTime +gap*21, "L")
                  , new Beat(startTime +gap*24, "D")
                  , new Beat(startTime +gap*26, "K")
                  , new Beat(startTime +gap*28, "S")                  
                  , new Beat(startTime +gap*30, "L")
                  , new Beat(startTime +gap*32, "Space")
                  , new Beat(startTime +gap*36, "J")
                  , new Beat(startTime +gap*36, "K")
                  , new Beat(startTime +gap*38, "F")
                  , new Beat(startTime +gap*40, "L")
                  , new Beat(startTime +gap*42, "S")
                  , new Beat(startTime +gap*44, "D")
                  , new Beat(startTime +gap*46, "F")
                  , new Beat(startTime +gap*48, "Space")
                  , new Beat(startTime +gap*50, "S")
                  , new Beat(startTime +gap*52, "D")
                  , new Beat(startTime +gap*54, "F")
                  , new Beat(startTime +gap*56, "J")
                  , new Beat(startTime +gap*58, "K")
                  , new Beat(startTime +gap*60, "L")
                  , new Beat(startTime +gap*60, "Space")
                  , new Beat(startTime +gap*62, "S")
                  , new Beat(startTime +gap*65, "J")
                  , new Beat(startTime +gap*66, "K")
                  , new Beat(startTime +gap*67, "L")
                  , new Beat(startTime +gap*70, "Space")
                  , new Beat(startTime +gap*71, "F")
                  , new Beat(startTime +gap*73, "D")
                  , new Beat(startTime +gap*75, "S")
                  , new Beat(startTime +gap*78, "Space")
                  , new Beat(startTime +gap*82, "Space")
                  , new Beat(startTime +gap*85, "L")
                  , new Beat(startTime +gap*87, "K")
                  , new Beat(startTime +gap*89, "J")
                  , new Beat(startTime +gap*91, "L")
                  , new Beat(startTime +gap*93, "D")
                  , new Beat(startTime +gap*96, "F")
                  , new Beat(startTime +gap*99, "S")
                  , new Beat(startTime +gap*101, "Space")
                  , new Beat(startTime +gap*103, "S")
                  , new Beat(startTime +gap*105, "L")
                  , new Beat(startTime +gap*107, "D")
                  , new Beat(startTime +gap*109, "K")
                  , new Beat(startTime +gap*111, "F")
                  , new Beat(startTime +gap*113, "Space")
                  , new Beat(startTime +gap*115, "Space")
                  , new Beat(startTime +gap*117, "F")
                  , new Beat(startTime +gap*118, "D")
                  , new Beat(startTime +gap*118, "S")
                  , new Beat(startTime +gap*120, "Space")
                  , new Beat(startTime +gap*122, "L")
                  , new Beat(startTime +gap*124, "K")
                  , new Beat(startTime +gap*126, "J")
                  , new Beat(startTime +gap*128, "F")
                  , new Beat(startTime +gap*129, "S")
                  , new Beat(startTime +gap*130, "Space")
                  , new Beat(startTime +gap*132, "S")
                  , new Beat(startTime +gap*135, "D")
                  , new Beat(startTime +gap*137, "D")
                  , new Beat(startTime +gap*140, "Space")
                  , new Beat(startTime +gap*145, "Space")
                  , new Beat(startTime +gap*150, "Space")
                  , new Beat(startTime +gap*152, "S")
                  , new Beat(startTime +gap*152, "F")
                  , new Beat(startTime +gap*154, "J")
                  , new Beat(startTime +gap*154, "L")
                  , new Beat(startTime +gap*155, "S")
                  , new Beat(startTime +gap*156, "D")
                  , new Beat(startTime +gap*157, "F")
                  , new Beat(startTime +gap*161, "Space")
                  , new Beat(startTime +gap*162, "S")
                  , new Beat(startTime +gap*164, "F")
                  , new Beat(startTime +gap*166, "J")
                  , new Beat(startTime +gap*168, "L")
                  , new Beat(startTime +gap*170, "Space")
                  , new Beat(startTime +gap*172, "D")
                  , new Beat(startTime +gap*174, "K")
                  , new Beat(startTime +gap*176, "S")
                  , new Beat(startTime +gap*178, "D")
                  , new Beat(startTime +gap*178, "F")
                  , new Beat(startTime +gap*180, "J")
                  , new Beat(startTime +gap*180, "K")
                  , new Beat(startTime +gap*182, "Space")
                  , new Beat(startTime +gap*184, "S")
                  , new Beat(startTime +gap*186, "F")
                  , new Beat(startTime +gap*188, "Space")
                  , new Beat(startTime +gap*190, "J")
                  , new Beat(startTime +gap*192, "L")
                  , new Beat(startTime +gap*194, "K")
                  , new Beat(startTime +gap*196, "D")
                  , new Beat(startTime +gap*198, "Space")
                  , new Beat(startTime +gap*200, "Space")
                  , new Beat(startTime +gap*201, "S")
                  , new Beat(startTime +gap*204, "F")
                  , new Beat(startTime +gap*206, "D")
                  , new Beat(startTime +gap*208, "Space")
                  , new Beat(startTime +gap*209, "Space")
                  , new Beat(startTime +gap*215, "S")
                  , new Beat(startTime +gap*216, "F")
                  , new Beat(startTime +gap*217, "S")
                  , new Beat(startTime +gap*218, "F")
                  , new Beat(startTime +gap*219, "J")
                  , new Beat(startTime +gap*220, "L")
                  , new Beat(startTime +gap*221, "J")
                  , new Beat(startTime +gap*222, "L")
                  , new Beat(startTime +gap*225, "D")
                  , new Beat(startTime +gap*228, "K")
                  , new Beat(startTime +gap*231, "D")
                  , new Beat(startTime +gap*233, "K")
                  , new Beat(startTime +gap*235, "Space")
                  , new Beat(startTime +gap*237, "S")
                  , new Beat(startTime +gap*239, "D")
                  , new Beat(startTime +gap*241, "F")
                  , new Beat(startTime +gap*244, "L")
                  , new Beat(startTime +gap*246, "K")
                  , new Beat(startTime +gap*248, "J")
                  , new Beat(startTime +gap*252, "K")
                  , new Beat(startTime +gap*254, "J")
                  , new Beat(startTime +gap*256, "L")
                  , new Beat(startTime +gap*258, "D")
                  , new Beat(startTime +gap*260, "S")
                  , new Beat(startTime +gap*262, "F")
                  , new Beat(startTime +gap*280, "Space")//덤디덤디 덤디덤디 휘휙~
                  , new Beat(startTime +gap*286, "Space")
                  , new Beat(startTime +gap*288, "Space")
                  , new Beat(startTime +gap*290, "S")
                  , new Beat(startTime +gap*291, "D")
                  , new Beat(startTime +gap*293, "Space")
                  , new Beat(startTime +gap*294, "Space")
                  , new Beat(startTime +gap*296, "Space")
                  , new Beat(startTime +gap*298, "Space")
                  , new Beat(startTime +gap*300, "J")
                  , new Beat(startTime +gap*301, "K")
                  , new Beat(startTime +gap*305, "Space")
                  , new Beat(startTime +gap*306, "Space")
                  , new Beat(startTime +gap*307, "Space")
                  , new Beat(startTime +gap*308, "Space")
                  
                  , new Beat(startTime +gap*310, "S")
                  , new Beat(startTime +gap*311, "D")
                  , new Beat(startTime +gap*312, "F")
                  , new Beat(startTime +gap*313, "J")
                  , new Beat(startTime +gap*314, "K")
                  , new Beat(startTime +gap*315, "L")

                  , new Beat(startTime +gap*320, "Space")//덤디덤디 덤디덤디 휘휙~
                  , new Beat(startTime +gap*321, "Space")
                  , new Beat(startTime +gap*322, "Space")
                  , new Beat(startTime +gap*325, "S")
                  , new Beat(startTime +gap*326, "D")
                  , new Beat(startTime +gap*330, "Space")
                  , new Beat(startTime +gap*331, "Space")
                  , new Beat(startTime +gap*332, "Space")
                  , new Beat(startTime +gap*333, "Space")
                  , new Beat(startTime +gap*335, "J")
                  , new Beat(startTime +gap*336, "K")
                  , new Beat(startTime +gap*340, "Space")
                  , new Beat(startTime +gap*341, "Space")
                  , new Beat(startTime +gap*342, "Space")
                  , new Beat(startTime +gap*343, "Space")
                  , new Beat(startTime +gap*346, "S")
                  , new Beat(startTime +gap*347, "D")
                  , new Beat(startTime +gap*348, "F")
                  , new Beat(startTime +gap*349, "J")
                  , new Beat(startTime +gap*350, "K")
                  , new Beat(startTime +gap*351, "L")
			};
			
		}
			
		/*
		new Beat(1000, "S"),
		new Beat(2000, "D")
		, new Beat(1000, "F")
		, new Beat(5000, "Space")
		, new Beat(3000, "J")
		, new Beat(2500, "K")
		, new Beat(2000, "L")
		*/
		
		int i = 0;
		gameMusic.start();
		while(i< beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note= new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
