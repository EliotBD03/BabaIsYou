package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * cette classe permet à partir d'un fichier texte, d'extraire les données afin de jouer correctement au jeu
 */
public class Extract {
    private String[][] dataList;

    /**
     * constructeur permettant d'avoir directement les données(l'attribut "dataList").
     */
    public Extract(String filename)
    {
        if(validFormat(filename))
            setDataList(filename);
        else
            System.out.println("invalid format");
    }

    /**
     * accesseur au tableau de données
     */
    public String[][] getDataList(){
        if (this.dataList != null)
            return this.dataList;
        return null;
    }

    /**
     * méthode permettant de verifier le format du fichier
     * @param fileName le fichier qu'on va vérifier
     * @return vrai si c'est le bon format, faux sinon
     */

    public boolean validFormat(String fileName)
    {
        int i = fileName.lastIndexOf('.');
        String extension = fileName.substring(i + 1);
        return extension.equals("txt");
    }
    /**
     * on extrait les donnés de notre fichier pour les mettre sous la forme d'un tableau a deux dimensions
     *
     * @param map le fichier texte représentant la map du jeu
     */
    private void setDataList(String map) {
        try {

            //on effectue les instantiations nécessaires
            FileReader file = new FileReader(map);
            BufferedReader r1 = new BufferedReader(file);
            String line;

            //on évalue le nombre de lignes pour notre tableau à deux dimensions
            int i = 0;
            while (r1.readLine() != null)
                ++i;

            //on instancie ce le tableau de String
            String[][] res = new String[i][4];

            r1.close();

            //on instancie une deuxieme fois pour "rouvrir" le fichier
            FileReader file2 = new FileReader(map);
            BufferedReader r2 = new BufferedReader(file2);

            int k = 0; // la ligne de res
            //tant qu'on n'a pas atteint la fin de notre fichier
            while ((line = r2.readLine()) != null) {
                int j = 0; // évaluer la longueur de la chaine de la ligne "line" de notre fichier map
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
                    {
                        res[k][l] += line.charAt(j);
                        //pour vérifier si ceux sont bien des nombres après le mot
                        if(l > 0)
                            Integer.parseInt(res[k][l]);

                    }

                    j++;
                }
                if (res[k][3].equals(""))
                    res[k][3] += "0";
                k++;
            }
            r2.close();
            // on met notre attribut de classe comme étant le tableau res (pour pouvoir le manipuler par la suite)
            this.dataList = res;
        }
        catch (IOException error)
        {
            System.out.println("cannot find the file. Please make sure this is the right path");
        }
        catch (NumberFormatException error)
        {
            System.out.println("the format of the level isn't respected");
            dataList = null;
        }
    }
}