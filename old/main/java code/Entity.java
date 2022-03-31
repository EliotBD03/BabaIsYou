public class Entity
{
    public String entityName; // entity has name
    public double entityXcor; public double entityYcor; // entity has x and y coordinate on the map
    public boolean isMove; public boolean isDie; public boolean isVisible; // entity has properties
    
    public Entity()
    {
        this.entityName = "noEntityName";
        this.entityXcor = 0; this.entityYcor = 0;
        this.isMove = false; this.isDie = false; this.isVisible = true;
    }
    public Entity(String _entityName, 
                         double _entityXcor, double _entityYcor,
                         boolean _isMove, boolean _isDie, boolean _isVisible)
    {
        this.entityName = _entityName; // assign name to entity
        this.entityXcor = _entityXcor; this.entityYcor = _entityYcor; // assign x and y coordinates to entity
        this.isMove = _isMove; this.isDie = _isDie; this.isVisible = _isVisible; // assign properties to entity
    }
    public Entity createRock(double rockXcor, double rockYcor) // rock entity builder
    {
        String rockName = "rock"; // rock entity name
        boolean rockIsMove = false; boolean rockIsDie = false; boolean rockIsVisible = true; // default rock properties
        Entity rock = new Entity(rockName, rockXcor, rockYcor, rockIsMove, rockIsDie, rockIsVisible); // create entity with rock properties
        return rock;
    }
    public Entity createWall(double wallXcor, double wallYcor) // wall entity builder
    {
        String wallName = "wall"; // wall entity name
        boolean wallIsMove = false; boolean wallIsDie = false; boolean wallIsVisible = true;
        Entity wall = new Entity(wallName, wallXcor, wallYcor, wallIsMove, wallIsDie, wallIsVisible); // create entity with wall properties
        return wall;
    }
    public Entity createBaba(double babaXcor, double babaYcor) // baba entity builder
    {
        String babaName = "baba"; // baba entity name
        boolean babaIsMove = true; boolean babaIsDie = false; boolean babaIsVisible = true; // default baba properties
        Entity baba = new Entity(babaName, babaXcor, babaYcor, babaIsMove, babaIsDie, babaIsVisible); // create entity with baba properties
        return baba;
    }
}