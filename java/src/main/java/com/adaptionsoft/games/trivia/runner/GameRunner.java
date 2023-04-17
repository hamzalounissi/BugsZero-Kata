
package com.adaptionsoft.games.trivia.runner;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;


public class GameRunner {
	public static void main(String[] args) {
		Random rand = new Random();
		playGame(rand);
	}
	public static void playGame(Random rand) {
		// Ce champ n'a pas d'utilité en tant que variable de classe, je l'ai déclaré dans la méthode
		boolean notAWinner;

		Game aGame = new Game();
		aGame.add("Chet");
		aGame.add("Pat");
		aGame.add("Sue");

		do {
			aGame.roll(rand.nextInt(5) + 1);
			if (rand.nextInt(9) == 7) {
				notAWinner = aGame.wrongAnswer();
			} else {
				notAWinner = aGame.wasCorrectlyAnswered();
			}
		} while (notAWinner);
	}
}
