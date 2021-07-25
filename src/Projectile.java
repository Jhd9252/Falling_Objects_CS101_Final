
// basic projectile class with initial properties and abstract update method
abstract class Projectile {
	String image;
	int speed;
	int xCoord;
	int yCoord;
	abstract public int update();
}