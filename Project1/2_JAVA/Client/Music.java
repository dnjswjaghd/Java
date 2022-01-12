package dangdang;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread{
	private Player player;
	private boolean isLoop; //반복재생할지말지결정
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	//public static boolean gameEnd = true;
	public Music(String name, boolean isLoop) {
		try {
			this.isLoop = isLoop;
			file = new File(Main.class.getResource("../music/"+name).toURI());
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public int getTime() {
		if(player ==null) {
			return 0;
		}
		return player.getPosition();
	}
	public void close() {
		isLoop = false;
		player.close();
	
		this.interrupt();
	}
	@Override
	public void run() {
		try {
			do {
				//gameEnd = false;
				player.play();
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				player = new Player(bis);
			}while(isLoop);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
