import java.util.*;

public class Combat 
{
    private Minion minion;
    private Player player;

    public Combat(Minion minion, Player player)
    {
        this.minion = minion;
        this.player = player;
        Scanner input = new Scanner(System.in);

        while(player.getHealth() > 0 || minion.getHealth() > 0)
        {
            System.out.println("          What will you do?" + "\n" + 
                               "1. Attack     2. Defend     3. Item");
            int choice = input.nextInt();

            if(choice == 1)
                playerAttack();
            else if(choice == 3)
                System.out.println("This isnt in the game yet");

            if(choice != 2)
                minion.attack();
        }

        if(player.getHealth() <= 0)
            player.dead();

        if(minion.getHealth() <= 0)
            minion.died();

        input.close();
    }

    public String playerAttack()
    {
        int patk = player.getAtk();
        
        minion.attacked(patk);

        return "You did " + patk + " damage to " + minion.getName();
    }

    public String minionAttack()
    {
        int matk = minion.getAtk();
        
        player.attacked(matk);

        return "You took " + matk + " damage";
    }
}