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
            BufferedReader reader = new BufferedReader(new FileReader("test.txt"));
            String line;
            while((line = reader.readLine()) != null)
                System.out.println(line);
            reader.close();
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