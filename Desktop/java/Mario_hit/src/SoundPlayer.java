import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundPlayer {
//사운드 재생시키는 클래스
	static Clip mainbgm;
	
	
	
	public static void setMusic(String file) {//file 경로를 받아 그 file 재생
		try {
			mainbgm = AudioSystem.getClip();
			AudioInputStream bgm = AudioSystem.getAudioInputStream(new File(file));
			mainbgm.open(bgm);
			if(file=="sound/mainbgm.wav") {//메인 bgm은 무한 루프
				mainbgm.loop(Clip.LOOP_CONTINUOUSLY);

			}
			else {//나머지는 1회 재생
				mainbgm.loop(0);	
			}
			
			}catch(Exception e){	
				e.printStackTrace();
			}
	}
	
	public static void Stop(){//음악 멈추기
		mainbgm.stop();
		mainbgm.close();
	}
	
	public static void waitStop(){//음악 멈추기
		mainbgm.stop();
	}

	public static void waitStart(){//음악 멈추기
		mainbgm.start();
	}
	
}
