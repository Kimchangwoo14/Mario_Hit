import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public class SoundPlayer {
//���� �����Ű�� Ŭ����
	static Clip mainbgm;
	
	
	
	public static void setMusic(String file) {//file ��θ� �޾� �� file ���
		try {
			mainbgm = AudioSystem.getClip();
			AudioInputStream bgm = AudioSystem.getAudioInputStream(new File(file));
			mainbgm.open(bgm);
			if(file=="sound/mainbgm.wav") {//���� bgm�� ���� ����
				mainbgm.loop(Clip.LOOP_CONTINUOUSLY);

			}
			else {//�������� 1ȸ ���
				mainbgm.loop(0);	
			}
			
			}catch(Exception e){	
				e.printStackTrace();
			}
	}
	
	public static void Stop(){//���� ���߱�
		mainbgm.stop();
		mainbgm.close();
	}
	
	public static void waitStop(){//���� ���߱�
		mainbgm.stop();
	}

	public static void waitStart(){//���� ���߱�
		mainbgm.start();
	}
	
}
