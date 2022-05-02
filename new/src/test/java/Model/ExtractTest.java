package Model;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ExtractTest {
    private static final String pathLevel = new File("src/main/resources/level/default").getAbsolutePath();

    private static final String level0 = pathLevel + File.separator + "level0.txt";
    private static final String level1 = pathLevel + File.separator + "level1.txt";
    private static final String level2 = pathLevel + File.separator + "level2.txt";
    private static final String level3 = pathLevel + File.separator + "level3.txt";
    private static final String level4 = pathLevel + File.separator + "level4.txt";
    private static final String level5 = pathLevel + File.separator + "level5.txt";
    private static final String level6 = pathLevel + File.separator + "level6.txt";
    private static final String level7 = pathLevel + File.separator + "level7.txt";

    private static final String[] levels = {level0,level1,level2,level3,level4,level5,level6,level7};

    public static String[] getLevels(){return levels;}
    /**
     * ce test a pour but de vérifier sur l'extension du fichier est bien respecté
     */
    @Test
    void validFormatTest(){
        int i = levels.length - 1;
        System.out.println("THE TXT FORMAT :");
        while(i >= 0)
        {
            Extract extract = new Extract(levels[i]);
            assertTrue(extract.validFormat(levels[i]));
            System.out.println(levels[i]  + "=> OK");
            i--;
        }
    }

    /**
     * ce test a pour but de vérifier si le contenu du fichier est écrit de la
     * bonne manière (pour les nombres)
     */

    @Test
    void setDataListTest()
    {
        int i = levels.length - 1;
        System.out.println("INSIDE THE FILE  :");
        while(i >= 0)
        {
            Extract extract = new Extract(levels[i]);
            assertNotNull(extract.getDataList());
            System.out.println(levels[i]  + " => OK");
            i--;
        }
    }
}