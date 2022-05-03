package Model;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BigAlgorithmTest
{
    private Environment map;
    private static final String pathTest = new File("src/main/resources/level/test").getAbsolutePath();

    private static final String test0 = pathTest + File.separator + "test0.txt";
    private static final String test1 = pathTest + File.separator + "test1.txt";

    private static final String[] tests = {test0, test1};


    /**
     * ce test a pour but de vérifier si une règle est supprimé après avoir déplacé un bloc
     */

    @Test
    void removeTest()
    {
        System.out.println(tests[0]);
        Extract extract = new Extract(tests[0]);
        map = new Environment();
        map.setMap(extract.getDataList());
        //ceux sont les règles qui ne seront pas modifiés lors du test
        Enum[] notTested = {Rules.BABA, Rules.YOU};
        //l'état de notre jeu avant déplacement
        Enum[][] before = BigAlgorithm.getTabperm();
        Baba baba = new Baba();
        baba.move("z");
        map.actualiseMap();
        //l'état de notre jeu après déplacement en y
        Enum[][] after = BigAlgorithm.getTabperm();
        int i = 0;
        //on va vérifier s'il y a au moins une des règles qui est supprimé
        while(i <= before.length - 1)
        {
            System.out.println("before: " + before[i][0] + " " + before[i][1] + " after : " + after[i][0] + " " + after[i][1]);
            if(before[i][0] != null && after[i][0] != null)
            {
                //quand c'est le cas, on quitte la boucle
                assertNotEquals(before[i][0], after[i][0]);
                break;
            }
            i++;
        }
        //on fait pareil sauf dans le cas d'un déplacement de bloc en x
        before = BigAlgorithm.getTabperm();
        baba.move("s");
        baba.move("d");
        after = BigAlgorithm.getTabperm();
        i = 0;
        while(i <= before.length - 1)
        {
            System.out.println("before: " + before[i][0] + " " + before[i][1] + " after : " + after[i][0] + " " + after[i][1]);
            if(before[i][0] != null && after[i][0] != null && before[i][0] != notTested[0])
                assertNotEquals(before[i][0], after[i][0]);
            i++;
        }
    }

    /**
     * ce test a pour but de vérifier si une règle à bien été ajouté après déplacement
     */
    @Test
    void newRulesTest()
    {
        // on initialise la carte
        Extract extract = new Extract(tests[1]);
        map = new Environment();
        map.setMap(extract.getDataList());
        //les résultats attendus après déplacement de bloc
        Enum[] expected = {Rules.WALL, Rules.STOP};
        Enum[] expected2 = {Rules.FLAG, Rules.WIN};
        //pareil que pour le test du dessus
        Enum[][] before = BigAlgorithm.getTabperm();
        Baba baba = new Baba();
        baba.move("z");
        map.actualiseMap();
        Enum[][] after = BigAlgorithm.getTabperm();
        int i = 0;
        System.out.println("VERTICAL : \n");
        while(i <= before.length -1 )
        {
            System.out.println("before: " + before[i][0] + " " + before[i][1] + " after : " + after[i][0] + " " + after[i][1]);
            //on vérifie qu'aucune règle attendue soit déjà dans le tableau (avant déplacement)
            assertNotEquals(before[i][0], expected[0]);
            //si le premier élément attendu se retrouve dans le tableau,
            //on s'assure que le deuxième s'y retrouve aussi
            if(after[i][1] == expected[1])
                assertEquals(after[i][0], expected[0]);
            i++;
        }
        // même procédé sauf que le déplacement se fait en x
        before = after;
        baba.move("d");
        map.actualiseMap();
        after = BigAlgorithm.getTabperm();
        i = 0;
        System.out.println("HORIZONTAL : \n");
        while(i <= before.length - 1)
        {
            System.out.println("before: " + before[i][0] + " " + before[i][1] + " after : " + after[i][0] + " " + after[i][1]);
            assertNotEquals(before[i][0], expected2[0]);
            if(after[i][1] == expected2[1])
                assertEquals(after[i][0], expected2[0]);
            i++;
        }
    }
}