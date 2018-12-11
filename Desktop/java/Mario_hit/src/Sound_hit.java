import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound_hit {
//게임 진행 중  캐릭터 클릭 시 사운드 재생시키는 클래스
	static Clip hitbgm;
	
	public void startmusic() {
		try {
			hitbgm = AudioSystem.getClip();
			AudioInputStream bgm = AudioSystem.getAudioInputStream(new File("sound/hit.wav"));//때릴 떄 효과음 가져옴
			hitbgm.stop();
			hitbgm.open(bgm);
			hitbgm.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
