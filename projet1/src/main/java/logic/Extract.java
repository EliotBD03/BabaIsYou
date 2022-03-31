package logic;

/**
 * permet de lire notre fichier
 */
import java.io.BufferedReader;
/**
 * permet de savoir si on peut lire le fichier
 */
import java.io.FileReader;
/**
 * permet de générer une exception au cas où ça ne marche pas
 */
import java.io.IOException;
/**
 * cette classe a pour but d'extraire les donnees d'un fichier texte
 */

public class Extract {
    private String[][] dataList;

    public Extract(String filename)
    {
        setDataList(filename);
    }

    public String[][] getDataList() {
        if (this.dataList != null)
            return this.dataList;
        return null;
    }

    /**
     * on extrait les donnés de notre fichier pour les mettre sous la forme d'un tableau a deux dimensions
     *
     * @param map le fichier texte representant la map du jeu
     */
    public void setDataList(String map) {
        try {

            //on effectue les intanciations necessaires
            FileReader file = new FileReader(map);
            BufferedReader r1 = new BufferedReader(file);
            String line;

            //on evalue le nombre de ligne pour notre tableau à deux dimensions
            int i = 0;
            while ((line = r1.readLine()) != null)
                ++i;

            //on instancie ce le tableau de String
            String[][] res = new String[i][4];

            r1.close();

            //on instancie une deuxieme fois pour "réouvrir" le fichier
            FileReader file2 = new FileReader(map);
            BufferedReader r2 = new BufferedReader(file2);

            int k = 0; // la ligne de res
            //tant qu'on a pas atteint la fin de notre fichier
            boolean flag = true;
            while ((line = r2.readLine()) != null) {
                int j = 0; // evaluer la longueur de la chaine de la ligne "line" de notre fichier map
                int l = 0; //la colonne de res
                res[k][0] = "";
                res[k][1] = "";
                res[k][2] = "";
                res[k][3] = ""; // on efface les valeurs null dans notre tableau
                //tant qu'on a pas atteint la fin de la ligne ET que l'indice de notre sous tableau <= 2
                while (j <= line.length() - 1 && l <= 3) {
                    if (line.charAt(j) == ' ')
                        l++;
                    else
                        res[k][l] += line.charAt(j);
                    j++;
                }
                if (res[k][3] == "")
                    res[k][3] += "0";
                k++;
            }
            r2.close();
            // on met notre attribut de classe comme etant le tableau res (pour pouvoir le manipuler par la suite)
            this.dataList = res;
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}