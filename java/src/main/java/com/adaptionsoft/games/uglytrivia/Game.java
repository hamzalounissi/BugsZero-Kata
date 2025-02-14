package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {

	// J'ai améliorer les déclarations de variable
    List<String> players = new ArrayList<>();
    int[] places = new int[6];
    int[] purses  = new int[6];
    boolean[] inPenaltyBox  = new boolean[6];
	LinkedList<String> popQuestions = new LinkedList<>();
	LinkedList<String> scienceQuestions = new LinkedList<>();
	LinkedList<String> sportsQuestions = new LinkedList<>();
	LinkedList<String> rockQuestions = new LinkedList<>();
    int currentPlayer = 0;
    boolean isGettingOutOfPenaltyBox;
    public  Game(){
    	for (int i = 0; i < 50; i++) {
			popQuestions.addLast("Pop Question " + i);
			scienceQuestions.addLast(("Science Question " + i));
			sportsQuestions.addLast(("Sports Question " + i));
			rockQuestions.addLast(createRockQuestion(i));
    	}
    }
	// Le type de retour n'est pas utilisé, je l'ai changé en void
	public void add(String playerName) {
	    players.add(playerName);
	    places[howManyPlayers()] = 0;
	    purses[howManyPlayers()] = 0;
	    inPenaltyBox[howManyPlayers()] = false;
	    System.out.println(playerName + " was added");
	    System.out.println("They are player number " + players.size());
	}
	public void roll(int roll) {
		System.out.println(players.get(currentPlayer) + " is the current player");
		System.out.println("They have rolled a " + roll);
		if (inPenaltyBox[currentPlayer]) {
			if (roll % 2 != 0) {
				isGettingOutOfPenaltyBox = true;
				System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
				movePlayerAndAskQuestion(roll);
			} else {
				System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
				isGettingOutOfPenaltyBox = false;
				}
		} else {
			movePlayerAndAskQuestion(roll);
		}
	}
	public boolean wasCorrectlyAnswered() {
		boolean winner;
		if (inPenaltyBox[currentPlayer]){
			if (isGettingOutOfPenaltyBox) {
				System.out.println("Answer was correct!!!!");
				currentPlayer++;
				// L'ajout des accolades rend le code plus lisible
				if (currentPlayer == players.size()){
					currentPlayer = 0;
				}
				purses[currentPlayer]++;
				System.out.println(players.get(currentPlayer)
						+ " now has "
						+ purses[currentPlayer]
						+ " Gold Coins.");
				// Pas besoin de déclarer une variable winner
				return didPlayerWin();
			} else {
				currentPlayer++;
				// L'ajout des accolades rend le code plus lisible
				if (currentPlayer == players.size()) {
					currentPlayer = 0;
				}
				return true;
			}
		} else {
			System.out.println("Answer was corrent!!!!");
			purses[currentPlayer]++;
			System.out.println(players.get(currentPlayer) 
					+ " now has "
					+ purses[currentPlayer]
					+ " Gold Coins.");
			// La c'est important de stocker le résultat de didPlayerWin()
			// puisque les traitements qui suivent vont impacter le résultat
			winner = didPlayerWin();
			currentPlayer++;
			if (currentPlayer == players.size()){
				currentPlayer = 0;
			}
			return winner;
		}
	}
	
	public boolean wrongAnswer(){
		System.out.println("Question was incorrectly answered");
		System.out.println(players.get(currentPlayer)+ " was sent to the penalty box");
		inPenaltyBox[currentPlayer] = true;
		
		currentPlayer++;
		if (currentPlayer == players.size()){
			currentPlayer = 0;
		}
		return true;
	}

	private String createRockQuestion(int index){
		return "Rock Question " + index;
	}
    // Methode isPlayable() n'est jamais utilisé, je l'ai supprimé
	private int howManyPlayers() {
		return players.size();
	}
	private void movePlayerAndAskQuestion(int roll) {
		places[currentPlayer] = places[currentPlayer] + roll;
		if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

		System.out.println(players.get(currentPlayer)
				+ "'s new location is "
				+ places[currentPlayer]);
		System.out.println("The category is " + currentCategory());
		askQuestion();
	}

	// Appeler equal permet de test la valeur et non pas les réferences, à priviligier
	private void askQuestion() {
		if ("Pop".equals(currentCategory()))
			System.out.println(popQuestions.removeFirst());
		if ("Science".equals(currentCategory()))
			System.out.println(scienceQuestions.removeFirst());
		if ("Sports".equals(currentCategory()))
			System.out.println(sportsQuestions.removeFirst());
		if ("Rock".equals(currentCategory()))
			System.out.println(rockQuestions.removeFirst());
	}

	// S'assurer q'une seule condition s'execute
	private String currentCategory() {
		if (places[currentPlayer] == 0) return "Pop";
		else if (places[currentPlayer] == 4) return "Pop";
		else if (places[currentPlayer] == 8) return "Pop";
		else if (places[currentPlayer] == 1) return "Science";
		else if (places[currentPlayer] == 5) return "Science";
		else if (places[currentPlayer] == 9) return "Science";
		else if (places[currentPlayer] == 2) return "Sports";
		else if (places[currentPlayer] == 6) return "Sports";
		else if (places[currentPlayer] == 10) return "Sports";
		else return "Rock";
	}
	private boolean didPlayerWin() {
		return !(purses[currentPlayer] == 6);
	}
}
