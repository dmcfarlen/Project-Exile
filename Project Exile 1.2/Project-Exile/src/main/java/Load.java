import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Load
{
    public Player player;
    
    public void main(String[] args) throws JsonParseException, JsonMappingException, IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        
        player = mapper.readValue(new File("save.json"), Player.class);
        Dungeon1 dungeon = mapper.readValue(new File("dungeon1.json"), Dungeon1.class);

        System.out.print("Name: " + player.name + "\n" +
                         "Level: " + player.level + "\n" +
                         "Health: " + player.health + "\n" +
                         "Mana: " + player.mana + "\n" + 
                         "Exp: " + player.exp + "\n" +
                         "Attack: " + player.attack + "\n" +
                         "Defense: " + player.defense + "\n" +
                         "Crit Chance: " + player.critChance + "\n" +
                         "Crit Mult: " + player.critMult + "\n\n");
        
        System.out.println(dungeon.isCompleted());
        dungeon.playDialogue("yeet");
    }
}