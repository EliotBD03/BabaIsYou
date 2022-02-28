public class Map
{
    public String map[] = {"X","X","X","X","X","X","X","X","X","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X"," "," "," "," "," "," "," "," ","X",
            "X","X","X","X","X","X","X","X","X","X"
    };

    /**
     * permet d'afficher la carte dans la console
     * @return la carte à afficher
     */
    public String getMap()
    {
        String res = "";
        for(byte i = 0; i < this.map.length; i++)
        {
            if (i % 10 == 0)
                res += "\n";
            res += this.map[i];
        }
        return res;
    }


}
