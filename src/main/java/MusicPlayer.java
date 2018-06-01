import java.util.Scanner;

public class MusicPlayer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Which Song would you like to play :: ");
		int response = scan.nextInt();

		Jukebox music = new Jukebox();
		if(response == 1) {
			music.setSong("Ambiance v2");
		}
		else if(response == 2) {
			music.setSong("Ambiance");
		}
		else if(response == 3) {
			music.setSong("Boss Minions");
		}
		else if(response == 4) {
			music.setSong("Real Villain");
		}
		else if(response == 5) {
			music.setSong("Shop");
		}
		else {
			music.setSong("fleche.wav");
		}
	} 
}
