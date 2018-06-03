import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.audio.AudioPlayer;
import com.almasb.fxgl.audio.Music;

public class Jukebox {
	private static AudioPlayer player = FXGL.getAudioPlayer();
	private static Music song;
	public static void playBGM(String name) {
		player.setGlobalMusicVolume(0.3);
		song = player.loopBGM(name);
		player.playMusic(song);
	}
	public static void stopBGM() {
		player.stopMusic(song);
	}
}