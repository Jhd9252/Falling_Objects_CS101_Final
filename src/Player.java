
public class Player {
	String[] grade = {"A+", "A-", "B+", "B-", "C+", "C-", "D+", "D-", "F"};
	int currentGrade = 0;
	int xCoord = 800/2;
	int yCoord = 500 - 66;
	int width = 66;
	int height = 66;
	int speed = 30;
	String image = "/pictures/lightbulb_moment_1_66x66-removebg-preview.png";
	
	public void revert() {
		this.currentGrade = 0;
		this.xCoord = 800/2;
		this.yCoord = 500 - 66;
		this.width = 66;
		this.height = 66;
		this.speed = 30;
	}
	
	public void DoctorNote() {
		this.width = 66;
		this.height = 66;
		this.speed = 30;
	}
}
