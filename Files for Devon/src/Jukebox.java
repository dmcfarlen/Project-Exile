import java.io.FileInputStream;
import java.io.IOException;
import sun.audio.*;

public class Jukebox {
	private static AudioPlayer MGP = AudioPlayer.player;
	private static AudioStream BGM;
	private static AudioData MD;
	private static ContinuousAudioDataStream loop = null;
	private static String fileName;
	
	public static void stop() {
		MGP.stop(loop);
	}
	public static void setSong(String fileName) {
		Jukebox.fileName = fileName;
		start();
	}
	private static void start() {
		try {
			BGM = new AudioStream(new FileInputStream(fileName));
			MD = BGM.getData();
			loop = new ContinuousAudioDataStream(MD);
		}catch(IOException error) {}
		
		MGP.start(loop);
		System.out.println("Finished");
	}
}
