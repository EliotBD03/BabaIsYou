package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvironmentTest {

    /**
     * ce test vérifie si le mot issu du fichier .txt (ex : baba) est valide
     */
    @Test
    void getMapOTest()
    {
        String[] levels = ExtractTest.getLevels();
        Environment environment = new Environment();
        int i = 0;
        while(i <= levels.length - 1)
        {
            Extract extract = new Extract(levels[i]);
            environment.setMap(extract.getDataList());
            System.out.println("level : " + i);
            assertNotNull(Environment.getMapO());
            i++;
        }
    }
}