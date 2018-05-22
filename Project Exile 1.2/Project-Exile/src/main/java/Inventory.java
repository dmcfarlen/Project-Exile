import java.util.List;

public class Inventory
{
    List<Equipment> equip;
    List<Consumeable> consume;
    Player player;
    
    public Inventory(List<Equipment> e, List<Consumeable> c, Player p)
    {
        equip = e;
        consume = c;
        player = p;
    }
    
    public void printConsume()
    {
        for(int i = 0; i < consume.size(); i++)
        {
            System.out.println(i + ". " + consume.get(i).getName() + ":" + consume.get(i).getAmount());
        }
    }
    
    public void printEquip()
    {
        for(int i = 0; i < equip.size(); i++)
        {
            System.out.println(i + ". " + equip.get(i).getName() + ":" + equip.get(i).getAmount());
        }
    }
    
    public void addConsume(Consumeable cons)
    {
        boolean inList = false;
        int count = 0;
        
        for(Consumeable con : consume)
        {
            if(con.getName().equals(cons.getName()))
            {
                inList = true;
            }
            count++;
        }
        
        if(inList)
            consume.get(count).addAmount();
        else
            consume.add(cons);
            
    }
    
    public void addEquip(Equipment equi)
    {
        boolean inList = false;
        int count = 0;
        
        for(Equipment equ : equip)
        {
            if(equ.getName().equals(equi.getName()))
            {
                inList = true;
            }
            count++;
        }
        
        if(inList)
            equip.get(count).addAmount();
        else
            equip.add(equi);
            
    }