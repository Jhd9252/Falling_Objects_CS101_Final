
public class Tracker {
	int enemiesKilled = 0;
	int upgradesUsed = 0;
	int timeElapsed = 0;
	int finalTime;
	public void trackTime() {
		timeElapsed += 1;
		
	}
	
	public int getTime() {
		finalTime = this.timeElapsed;
		return finalTime;
	}
	
	public void revert() {
		this.enemiesKilled = 0;
		this.upgradesUsed = 0;
		this.timeElapsed = 0;
	}
}
