package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Environment map;
    private static final String pathTest0 = new File("src/main/resources/level/test/test2.txt").getAbsolutePath();
    private static final String pathTest1 = new File("src/main/resources/level/test/test3.txt").getAbsolutePath();
    /**
     * à faire avant chaque test,
     * on initialise la map
     */
    @BeforeEach
    void setUp()
    {
        Extract extract = new Extract(pathTest0);
        map = new Environment();
        map.setMap(extract.getDataList());
    }
    /**
     * cet test a pour but de vérifier la condition de victoire
     */
    @Order(0)
    @Test
    void win()
    {
        Baba baba = new Baba();
        baba.move("z");
        System.out.print("Before rule established : ");
        // on vérifie que la règles n'est pas mise en place (FlagIsWin)
        assertFalse(Item.win());
        System.out.println("winStatus = false");
        //on fait en sorte que ça le devienne
        baba.move("s");
        baba.move("d");
        baba.move("q");
        //on atteint l'objectif et on vérifie
        baba.move("z");
        System.out.print("After rule established : ");
        assertTrue(Item.win());
        System.out.println("winStatus = true");
    }

    /**
     * Ce test a pour but de tester les conditions de défaite.
     * En somme, on ne sait plus rien contrôler
     */
    @Order(1)
    @Test
    void isGameOver()
    {
        //même procédé que le test du dessus, mais en sens inverse
        Baba baba = new Baba();
        System.out.println("Before the move, we have : BabaIsYou");
        assertFalse(Item.isGameOver());
        baba.move("s");
        baba.move("d");
        baba.move("d");
        System.out.print("After we have : ");
        assertTrue(Item.isGameOver());
        System.out.println("NOTHING => GAME OVER");

    }

    @Order(2)
    @Test
    void move()
    {
        Extract extract = new Extract(pathTest1);
        map = new Environment();
        map.setMap(extract.getDataList());
        Baba baba = new Baba();
        //on vérifie qu'on n'a pas le droit de dépasser les bordures de la carte (ici en y)
        System.out.print("cannot go up : ");
        assertFalse(baba.canMoveY(BigAlgorithm.getTabperm(),-1 , 0));
        System.out.println("OK");
        //on vérifie qu'on n'a pas le droit de dépasser les bordures de la carte (ici en x)
        System.out.print("cannot go left : ");
        assertFalse(baba.canMoveX(BigAlgorithm.getTabperm(), 0, -1));
        System.out.println("OK");
        //on vérifie qu'on n'a pas le droit de passer à travers quelque chose qui est stop
        System.out.print("cannot go left because of a wall which is stop : ");
        assertFalse(baba.thingIsPushingX(BigAlgorithm.getTabperm(), 0, 1));
        System.out.println("OK");

        baba.move("s");
        baba.move("s");
        //on vérifie qu'on n'a pas bougé en essayant de pousser un objet derriere lequel
        //se trouve un objet qui est stop
        System.out.print("cannot push down because of a wall : ");
        assertTrue(Environment.getMapO()[2][1].thingIsYou(BigAlgorithm.getTabperm()));
        System.out.println("OK");

        System.out.println("pushing...");
        baba.move("d");
        baba.move("d");
        baba.move("d");
        //on vérifie qu'après avoir poussé des objets et qu'on a atteint un objet qui est stop,
        //on reste à la même position
        assertTrue(Environment.getMapO()[2][3].thingIsYou(BigAlgorithm.getTabperm()));
        System.out.println("cannot push anymore : OK");
        System.out.println("moved the rule block");
        baba.move("s");
        baba.move("z");
        baba.move("d");
        //on vérifie qu'après avoir modifié la règle avec objet + stop
        //on puisse pousser à nouveau
        //Dans ce cas-ci, ça se vérifie baba n'est plus à sa position initiale après
        //avoir fait un input vers la droite
        assertNull(Environment.getMapO()[2][3]);
        System.out.println("Can push");
    }
}