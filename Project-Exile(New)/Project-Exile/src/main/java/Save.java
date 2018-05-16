import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Save
{
	public static void main(String[] args) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		Player player = new Player();
		
		player.setName("Chance");
		player.setLevel(1);
		player.setHealth(15);
		player.setMana(60);
		player.setExp(0);
		player.setAtack(8);
		player.setDefense(0);
		player.setCritChance(3);
		player.setCritMult(1.5);
		player.setCompleted(true);
		
		mapper.writeValue(new File("save.json"), player);
	}
}
