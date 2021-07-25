import java.util.ArrayList;
// board class to hold initial properties and countdown timer
class Board {
	int [] size = {800,500};
	String image = "/pictures/5de8462cc431e.image_800x500.jpg";
	ArrayList<EnemyProjectile> enemyProjectile = new ArrayList<EnemyProjectile>();
	ArrayList<Upgrades> upgradeProjectile = new ArrayList<Upgrades>();
	int gameTime = 5;
	int initialTimer = gameTime;
	
	public void countDown() {
		initialTimer -= 1;
	}
	
	public void revert() {
		this.initialTimer = this.gameTime;
	}
	
	public void revertProjectile() {
		this.enemyProjectile.clear();
		this.upgradeProjectile.clear();
	}
	
}