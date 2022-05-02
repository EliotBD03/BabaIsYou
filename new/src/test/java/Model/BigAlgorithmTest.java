package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class BigAlgorithmTest
{
    private Environment map;
    @BeforeEach
    public void setUp()
    {
        Extract extract = new Extract( new File("src/main/resources/level/test").getAbsolutePath() + File.separator + "test.txt");
        map = new Environment();
        map.setMap(extract.getDataList());
    }

    @Test
    void actualiseTest()
    {
        Enum[][] before = BigAlgorithm.getTabperm();
        Baba baba = new Baba();
        baba.move("z");
        map.actualiseMap();
        Enum[][] after = BigAlgorithm.getTabperm();
        int i = 0;
        while(i <= before.length - 1)
        {
            System.out.println(before[i][0] + " " + before[i][1] + " " + after[i][0] + " " + after[i][1]);
            i++;
        }
        assertNotEquals(before, after);
        before = BigAlgorithm.getTabperm();
        baba.move("s");
        baba.move("d");
        after = BigAlgorithm.getTabperm();
        int j = 0;
        while(j <= before.length - 1)
        {
            System.out.println(before[j][0] + " " + before[j][1] + " " + after[j][0] + " " + after[j][1]);
            j++;
        }
        assertNotEquals(before, after);
    }
}