import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound_hit {
//���� ���� ��  ĳ���� Ŭ�� �� ���� �����Ű�� Ŭ����
	static Clip hitbgm;
	
	public void startmusic() {
		try {
			hitbgm = AudioSystem.getClip();
			AudioInputStream bgm = AudioSystem.getAudioInputStream(new File("sound/hit.wav"));//���� �� ȿ���� ������
			hitbgm.stop();
			hitbgm.open(bgm);
			hitbgm.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
