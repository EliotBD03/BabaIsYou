public class Goal extends Map
{
    public final String skin = "@";
    public final int position = 22;

    /**
     * permet de savoir si le joueur a atteint l'objectif.
     * @param position position du joueur.
     * @return vrai si le joueur a atteint l'objectif, faux sinon.
     */
    public boolean isgoal(int position)
    {
        if (position == this.position)
            return true;
        return false;
    }
}