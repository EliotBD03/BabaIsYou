package Presenter;

import java.io.File;

public class Level
{
    //l'index du niveau qu'on est en train de faire
    private static int currentIndex;
    //l'index du niveau de la sauvegarde
    private static int previousCurrentIndex;
    //le dernier niveau jouable
    private static int lastIndex ;
    //le chemin du dossier contenant les niveaux
    private static final String pathLevel = new File("src/main/resources/level/default").getAbsolutePath();
    //le chemin du fichier contentant les accès aux niveaux
    private static final String pathAccessLevel = new File("src/main/resources/users/access_level.txt").getAbsolutePath();

    //chemin du niveau 1
    private static final String level0 = pathLevel + File.separator + "level0.txt";
    //ect...
    private static final String level1 = pathLevel + File.separator + "level1.txt";
    private static final String level2 = pathLevel + File.separator + "level2.txt";
    private static final String level3 = pathLevel + File.separator + "level3.txt";
    private static final String level4 = pathLevel + File.separator + "level4.txt";
    private static final String level5 = pathLevel + File.separator + "level5.txt";
    private static final String level6 = pathLevel + File.separator + "level6.txt";
    private static final String level7 = pathLevel + File.separator + "level7.txt";

    //tableau comprenant tous les niveaux disponibles
    private static final String[] levels = {level0,level1,level2,level3,level4,level5,level6,level7};

    /**
     * constructeur qui va établir le niveau courant
     * en fonction du nom donné en paramètre
     * @param id nom utilisateur
     */
    public Level(String id)
    {
        setCurrentIndex(0);
        if(id == null)
            setAllAccess();
        else
            searchAccess(id);
    }

    /**
     * cette méthode est utilisée pour accéder à un
     * niveau en particulier
     * @param index l'index du niveau
     * @return vrai si on a pu y accéder, faux sinon
     */
    public boolean setCurrentIndex(int index)
    {
        if(lastIndex >= index)
        {
            currentIndex = index;
            return true;
        }
        return false;
    }

    /**
     * Cette méthode est utilisée dans le cadre où
     * le joueur n'a indiqué aucun nom. Il a accès
     * à tous les niveaux.
     */
    private void setAllAccess()
    {
        lastIndex = levels.length -1;
    }

    /**
     * accesseur
     * @return le niveau courant
     */
    public String getCurrentLevel()
    {
        return levels[currentIndex];
    }

    /**
     * accesseur
     * @return l'index courant
     */
    public int getCurrentIndex(){return currentIndex;}

    /**
     * accesseur
     * @return le dernier niveau jouable
     */
    public String getLastLevel()
    {
        return levels[lastIndex];
    }

    /**
     * méthode utilisée pour passer au
     * niveau suivant
     * @return vrai si on a pu passer au niveau suivant, faux sinon
     */
    public boolean goNext()
    {
        if(currentIndex == levels.length -1)
        {
            Score.endScore();
            return false;
        }
        currentIndex += 1;
        return true;
    }

    /**
     * Méthode utilisée pour accéder au niveau d'accès d'un joueur.
     * Si aucun nom n'a été trouvé, on écrit les accès basiques.
     * @param id le nom du joueur
     */
    private void searchAccess(String id)
    {
        Info info = new Info(pathAccessLevel);
        String access = info.getUserInfo(id);
        if(access == null)
            info.writeInfo(id + " 0000000");
        else
        {
            lastIndex = Integer.parseInt(String.valueOf(access.charAt(access.length() - 1)));
            previousCurrentIndex =  Integer.parseInt(String.valueOf(access.charAt(0)));
            System.out.println("le dernier indice accessible est : " + lastIndex + "et le dernier utilisé : " + previousCurrentIndex);
        }
    }

    /**
     * va charger le dernier index de sauvegarde
     */
    public void fromSave()
    {
        currentIndex =  previousCurrentIndex;
    }

    /**
     * méthode utilisée pour écrire les niveaux d'accès
     * actuels d'un joueur dans un fichier
     * @param id le nom du joueur
     */
    public void writeLevelAccess(String id)
    {
        if(lastIndex < currentIndex)
            lastIndex = currentIndex;
        Info info = new Info(pathAccessLevel);
        String res = "";
        int i = 0;
        while(i <= lastIndex)
        {
            res += i;
            i++;
        }
        info.writeInfoUser(currentIndex + res, id);
    }

}
