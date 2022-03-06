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
 * cette classe ne comporte que des méthodes 
 */

public class Extract
{
    /**
     * ne permet pour l'instant que d'utiliser un fichier et le lire 
     */
    public static void extract(){

        try
        {
            BufferedReader r1 = new BufferedReader(new FileReader("test.txt"));
            String line;
            int i = 0;
            while((line = r1.readLine()) != null)
                ++i;
            String[] res = new String[i];
            i = 0;
            r1.close();
            BufferedReader r2 = new BufferedReader(new FileReader("test.txt"));
            while((line = r2.readLine()) != null){
                res[i] = line;
                ++i;
            }
            r2.close();
            for(i = 0; i <= res.length - 1; ++i)
                System.out.println(res[i]);

        }
        catch(IOException error)
        {
            error.printStackTrace();
        }
    } 

    public static void main(String[] arg)
    {
        extract();
    }
}

// en attente du type de retour 