// imports 
import processing.core.*;
//import java.util.ArrayList;
import ddf.minim.*;

// main class extends PApplet
public class FinalProj extends PApplet {
	
	// create global variables
	PImage bg; 
	PImage mainMenu;
	PImage playerImage;
	PImage enemyImage;
	PImage upgradeImage;
	EnemyProjectile enemy = new EnemyProjectile();
	Upgrades upgrade = new Upgrades();
	Player player = new Player();
	int chance;
	static Board board = new Board();
	static Tracker tracker = new Tracker();
	static Boolean start = false;
	static Boolean instruction = false;
	int framesCounter = 0;
	static int lvl = 0;
	static int enemyProj = 0;
	Minim minim = new Minim(this);
	AudioPlayer player1;
	AudioPlayer player2;
	AudioPlayer player3;
	AudioPlayer player4;
	AudioPlayer DoctorNoteSound;
	
	// main method 
	public static void main (String[] args) {
		// main loop 
		while (true) {
			// start the PApplet
			PApplet.main("FinalProj");
			break;
		}
	}	
	
	// processing setup method
	public void setup() {
		// load needed images from classes
		bg = loadImage(board.image);
		mainMenu = loadImage("/pictures/blackboard_800x500.jpg");
		playerImage = loadImage(player.image);
		enemyImage = loadImage(enemy.image);
		upgradeImage = loadImage(upgrade.image);
		
		// countdown settings
		textSize(20);
		fill(0,255,0);
		
		//load the sound files
		player1 = minim.loadFile("/sound/DOVA-SYNDROME OFFICIAL YouTube CHANNEL.mp3");
		player2 = minim.loadFile("/sound/Mario 64 Oof Sound Effect.mp3");	
		player3 = minim.loadFile("/sound/Mario Death - Sound Effect (HD).mp3");	
		player4 = minim.loadFile("/sound/Victory Sound Effect - SFX.mp3");	
		DoctorNoteSound = minim.loadFile("/sound/Ambulance - Sound Effect.mp3");	
		
	}	
	
	// processing settings method
	// size() should be same size as background image
	public void settings() {
		size(board.size[0],board.size[1]); 
	}
	
	//processing draw method (default: 60 frames per second)
	public void draw() {
		if(start == false) {
			//creating main menu, allowing user to select difficulty levels
			background(mainMenu);			
			fill(255,255,255);
			textSize(90); 
			text("Pass Your Class!", board.size[0] / 2 - 330, board.size[1]/ 2 - 100);
			textSize(14); 
			text("(Please hover mouse over a difficulty level)", board.size[0] / 2 - 150, board.size[1]/ 2 + 80);
			//the levels
			fill(100, 100, 50);
			stroke(255);
			rect(board.size[0] / 2 - 240, board.size[1]/ 2, 100, 50);
			rect(board.size[0] / 2 - 130, board.size[1]/ 2, 100, 50); 
			rect(board.size[0] / 2 - 20, board.size[1]/ 2, 100, 50); 
			rect(board.size[0] / 2 + 90, board.size[1]/ 2, 100, 50); 
			fill(0);
			textSize(20);
			text("1", board.size[0] / 2 - 195, board.size[1]/ 2 + 10, 100, 50);
			text("2", board.size[0] / 2 - 85, board.size[1]/ 2 + 10, 100, 50);
			text("3", board.size[0] / 2 + 25, board.size[1]/ 2 + 10, 100, 50);
			text("4", board.size[0] / 2 + 135, board.size[1]/ 2 + 10, 100, 50);
			
			if(mouseX > board.size[0] / 2 - 240 && mouseX < board.size[0] / 2 - 140 && mouseY > board.size[1]/ 2 && mouseY < board.size[1]/ 2 + 50) {
				lvl = 1;
				start = true;
			}
			else if(mouseX > board.size[0] / 2 - 130 && mouseX < board.size[0] / 2 - 30 && mouseY > board.size[1]/ 2 && mouseY < board.size[1]/ 2 + 50) {
				lvl = 2;
				start = true;
			}
			else if(mouseX > board.size[0] / 2 - 20 && mouseX < board.size[0] / 2 + 80 && mouseY > board.size[1]/ 2 && mouseY < board.size[1]/ 2 + 50) {
				lvl = 3;
				start = true;
			}
			else if(mouseX > board.size[0] / 2 + 90 && mouseX < board.size[0] / 2 + 190 && mouseY > board.size[1]/ 2 && mouseY < board.size[1]/ 2 + 50) {
				lvl = 4;
				start = true;
			}
		}
		
		//play bgm once game starts
		if(player1.isPlaying() == false && start && board.initialTimer > 0  && instruction) {
			player1.pause();
			player1.rewind();
			player1.play();
		}
		else if(board.initialTimer == 0 && player1.isPlaying()) {
			player1.pause();
			player1.rewind();
		}
		
		//provide instruction on how to play
		if(instruction == false && start) {
			background(bg);
			text("1. Use arrow keys to move and dodge", board.size[0] / 2 - 170, board.size[1]/ 2 + 100);
			text("2. Dodge and Survive 60 seconds to win", board.size[0] / 2 - 180, board.size[1]/ 2 + 150);
			text("(Click anywhere on the game to start)", board.size[0] / 2 - 170, board.size[1]/ 2 + 200);
			
		}
		else if(player.currentGrade != 8 && board.initialTimer > 0 && start && instruction) {
			//initialize the board
			if(enemyProj == 0) {
				for(int ii = 0; ii < lvl; ii++) {
					board.enemyProjectile.add(new EnemyProjectile());
				}
				chance = (int)(10 * Math.random());
				if(chance > 7) {
					board.upgradeProjectile.add(new Upgrades());
				}
				enemyProj++;
			}
			if(framesCounter % 60 == 0 && framesCounter != 0) {
				for(int ii = 0; ii < lvl; ii++) {
					board.enemyProjectile.add(new EnemyProjectile());
				}
				chance = (int)(10 * Math.random());
				if(chance > 7) {
					board.upgradeProjectile.add(new Upgrades());
				}
				// countdown - 1 second (checked)
				board.countDown();
				// elapsedTimer stat + 1 (checked)
				tracker.trackTime();
				
				framesCounter++;
			}
			else {
				framesCounter++;
			}
			
			background(bg);
			image(playerImage, player.xCoord, player.yCoord, player.width, player.height);
			text("Survive!: " + board.initialTimer, 50, board.size[1] - 100);
			text("Grade: " + player.grade[player.currentGrade], 50, board.size[1] - 50);
	
			//spawn enemy projectiles and detect collision
			for(EnemyProjectile p : board.enemyProjectile) {
				p.update();
				image(enemyImage, p.xCoord, p.yCoord);
				//collision
				if (player.xCoord < p.xCoord + p.width && player.xCoord + player.width > p.xCoord && player.yCoord < p.yCoord + p.height && player.height + player.yCoord > p.yCoord) {
					player.currentGrade += 1;
					player.width += 10;
					player.height += 10;
					board.enemyProjectile.remove(p);
					
					//collision sound effect
					player2.pause();
					player2.rewind();
					player2.play();
					break;
				}
			}
			
			for(Upgrades p : board.upgradeProjectile) {
				try {
					p.update();
					image(upgradeImage, p.xCoord, p.yCoord);
					//collision
					if (player.xCoord < p.xCoord + p.width && player.xCoord + player.width > p.xCoord && player.yCoord < p.yCoord + p.height && player.height + player.yCoord > p.yCoord) {
						player.width += 10;
						player.height += 10;
						board.upgradeProjectile.remove(p);
						board.enemyProjectile.clear();
						player.DoctorNote();
						
						//collision sound effect
						DoctorNoteSound.pause();
						DoctorNoteSound.rewind();
						DoctorNoteSound.play();
						break;
					}				
				}
				catch(Exception IndexOutOfBoundsException){
					break;
				}
			}
		}
		//check if player lose or win, and allow for restart if user click anywhere with mouse
		if(player.currentGrade == 8 && start && instruction) {
			background(bg);
			if(player3.isPlaying() == false) {
				player3.play();
			}
			board.initialTimer = 0;
			text("Congratulations! You failed! ", board.size[0]/2 - 120, board.size[1]/2 + 80);
			text("Time Survived: " + tracker.getTime() + " seconds", board.size[0]/2 - 110, board.size[1]/2 + 130);
			text("Final Grade: " + player.grade[player.currentGrade], board.size[0]/2 - 60, board.size[1]/2 + 180);
			text("(Click anywhere to go back to Menu screen)", board.size[0]/2 - 200, board.size[1]/2 + 230);
		}
		else if(start && board.initialTimer == 0 && instruction){
			background(bg);
			if(player4.isPlaying() == false) {
				player4.play();
			}
			text("You just got lucky this time: ", board.size[0]/2 - 110, board.size[1]/2 + 80);
			text("Time Elapsed: " + tracker.getTime(), board.size[0]/2 - 70, board.size[1]/2 + 130);
			text("Final Grade: " + player.grade[player.currentGrade], board.size[0]/2 - 65, board.size[1]/2 + 180);
			text("(Click anywhere to go back to Menu screen)", board.size[0]/2 - 200, board.size[1]/2 + 230);
		}
	}	
	
	//processing mousePressed method
	public void mousePressed(){
		//check if user read instruction and clicked
		if(start && instruction == false) {
			instruction = true;
		}
		//check if user wants to restart and clicked
		if(start && instruction && player.currentGrade == 8 || start && instruction && board.initialTimer == 0) {
			start = false;
			instruction = false;
			enemyProj = 0;
			framesCounter = 0;
			lvl = 0;
			player.revert();
			board.revert();
			tracker.revert();
			board.revertProjectile();
			player3.pause();
			player3.rewind();
			player4.pause();
			player4.rewind();
		}
	}
	//processing keyPressed method
	public void keyPressed() {
		//control player movement
		if (key == CODED) {
			if (keyCode == UP && player.yCoord >= 0) {
				player.yCoord -= player.speed;
			}
			if (keyCode == DOWN && player.yCoord + player.height <= 500) {
				player.yCoord += player.speed;
			}
			if (keyCode == LEFT && player.xCoord >= 0) {
				player.xCoord -= player.speed;
			}
			if (keyCode == RIGHT && player.xCoord + player.width <= 800) {
				player.xCoord += player.speed;
			}
		}
	}
}

class Upgrades extends Projectile {
	String image = "/pictures/doctorNote.png";
	int width = 66;
	int height = 66;
	int xCoord = (int)(Math.random() * (800 - this.width)) + 1;
	int yCoord = 10;
	int speed = 5;
	public int update() {
		this.yCoord += speed;
		return yCoord;
	}
}
class PlayerProjectile extends Projectile {
	public int update() {
		return yCoord;
	}
}

class EnemyProjectile extends Projectile {
	String image = "/pictures/homework_66x66.gif";
	int width = 66;
	int height = 66;
	int xCoord = (int)(Math.random() * (800 - this.width)) + 1;
	int yCoord = 10;
	int speed = 5;
	public int update() {
		this.yCoord += speed;
		return yCoord;
	}
}
