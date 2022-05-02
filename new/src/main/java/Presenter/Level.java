package Presenter;

import java.io.File;

public class Level
{
    private static int currentIndex = 0;
    private static int previousCurrentIndex;
    private static int lastIndex = 0;

    private static final String pathLevel = new File("src/main/resources/level/default").getAbsolutePath();
    private static final String pathAccessLevel = new File("src/main/resources/users/access_level.txt").getAbsolutePath();

    private static final String level0 = pathLevel + File.separator + "level0.txt";
    private static final String level1 = pathLevel + File.separator + "level1.txt";
    private static final String level2 = pathLevel + File.separator + "level2.txt";
    private static final String level3 = pathLevel + File.separator + "level3.txt";
    private static final String level4 = pathLevel + File.separator + "level4.txt";
    private static final String level5 = pathLevel + File.separator + "level5.txt";
    private static final String level6 = pathLevel + File.separator + "level6.txt";
    private static final String level7 = pathLevel + File.separator + "level7.txt";

    private static final String[] levels = {"C:\\Users\\julie\\OneDrive\\Bureau\\myProject\\BabaIsYou\\new\\src\\main\\resources\\level\\test\\test.txt",level0,level1,level2,level3,level4,level5,level6,level7};

    public Level(String id)
    {
        setCurrentIndex(0);
        if(id == null)
            setAllAccess();
        else
            searchAccess(id);
    }

    public boolean setCurrentIndex(int index)
    {
        if(lastIndex >= index)
        {
            currentIndex = index;
            return true;
        }
        return false;
    }

    private void setAllAccess()
    {
        lastIndex = levels.length -1;
    }

    public String getCurrentLevel()
    {
        return levels[currentIndex];
    }

    public int getCurrentIndex(){return currentIndex;}

    public String getLastLevel()
    {
        return levels[lastIndex];
    }

    public boolean goNext()
    {
        if(currentIndex == levels.length -1)
            return false;
        currentIndex += 1;
        return true;
    }

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

    public void fromSave()
    {
        currentIndex =  previousCurrentIndex;
    }

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
