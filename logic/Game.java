package logic;



import audio.MusicPlayer;
import combatants.Player;
import combatants.Opponent;
import items.Pickaxe;
import items.Weapon;
import util.Validator;
import util.PlayByPlay;

public class Game {

  // color variables for art & text
  private static String reset = "\u001B[0m";
  private static String cyan = "\u001B[36m";
  private static String purple = "\u001B[35m";
  private static String blue = "\u001B[34m";
  private static String red = "\u001B[31m";
  private static String pink = "\u001B[95m";
  private static String yellow = "\u001B[33m";
  private static String green = "\u001B[32m";

  private static Player mainPlayer;
  private static Opponent currentOpponent;
  private static boolean gameOver;

  private Game() {}

  public static void main(String[] args) {
    
    // start the background track and utilities
    MusicPlayer myPlayer = new MusicPlayer();
    myPlayer.playBackgroundMusic("audio/welcomeMusic.wav");

    Validator input = new Validator();
    PlayByPlay llama = new PlayByPlay();

    printWelcome();

    boolean playAgain = true;

    // main loop to handle match replays
    while (playAgain) {
      
      resetGame();

      System.out.println(cyan + "\nMatch starting... " + reset + 
        mainPlayer.getPlayerName() + " vs " + 
        currentOpponent.getOpponentName());

      // prompt player for their drop location
      System.out.println("\n--- CHOOSE DROP LOCATION ---");
      System.out.println("1) Tilted Towers");
      System.out.println("2) Pleasant Park");
      
      int locationChoice = 0;
      while (locationChoice == 0) {
        System.out.print("Select drop zone: ");
        locationChoice = input.validatorChoiceMenu(1, 2);
      }

      Location dropZone;
      if (locationChoice == 1) {
        dropZone = new Location("Tilted Towers");
      } else {
        dropZone = new Location("Pleasant Park");
      }

      System.out.println("Landing at " + dropZone.getName() + "...");

      // allow the player to choose their chest loot
      System.out.println("\n--- CHOOSE YOUR CHEST ---");
      System.out.println(yellow + "1) Regular Chest (Safe: AR + Mini Shield)" + reset);
      System.out.println(blue + "2) Blue Chest (Risky: Pump + Full Shield / Pickaxe)" + reset);

      int chestChoice = 0;
      while (chestChoice == 0) {
        System.out.print("Select chest: ");
        chestChoice = input.validatorChoiceMenu(1, 2);
      }

      // the location object assigns loot to the player
      Weapon foundWeapon = dropZone.openChest(chestChoice, mainPlayer);
      mainPlayer.setWeapon(foundWeapon);

      if (foundWeapon.getName().contains("Pump")) {
        System.out.println(cyan + "JACKPOT! You found a " + 
          foundWeapon.getName() + " & a Full Shield!" + reset);
      } else if (foundWeapon.getName().contains("Pickaxe")) {
        System.out.println(yellow + "BUST! You only found a " + 
          foundWeapon.getName() + " & NO shield." + reset);
      } else {
        System.out.println("You opened a Regular Chest and found an " + 
          foundWeapon.getName() + "!");
      }

      // turn-based combat loop
      while (!gameOver) {
        
        System.out.println("\n=======================");
        System.out.println(yellow + "1) Standard Attack (Noob)" + reset);
        System.out.println(blue + "2) Heavy Attack (Sweat)" + reset);
        System.out.println("=======================");
        
        int choice = 0;
        while (choice == 0) {
          System.out.print(cyan + "Select attack: " + reset);
          choice = input.validatorChoiceMenu(1, 2);
        }

        int playerHit = mainPlayer.getWeapon().strike();
        
        // heavy attack risk and reward math
        if (choice == 2) {
          if (Math.random() < 0.5) {
            playerHit = 0; 
          } else {
            playerHit += 20; 
          }
        }

        // fire the sound effect for the player's weapon
        playWeaponSound(mainPlayer.getWeapon().getName(), myPlayer);

        llama.printStrike(mainPlayer.getPlayerName(), 
          mainPlayer.getWeapon().getName(), playerHit);
        
        int opponentHp = currentOpponent.getHealth();
        currentOpponent.setHealth(opponentHp - playerHit);
        System.out.println(green + "Opponent Health Now: " + 
          currentOpponent.getHealth() + reset);

        if (currentOpponent.getHealth() <= 0) {
          System.out.println(pink + "\n********** VICTORY ROYALE! **********\n" + reset);
          System.out.println(yellow + "      ___________");
          System.out.println("     '._==_=_==_.'");
          System.out.println("     .- \\     / -.");
          System.out.println("     | (|     |) |");
          System.out.println("      '-|     |-'");
          System.out.println("        \\     /");
          System.out.println("         '. .'");
          System.out.println("           |");
          System.out.println("        _.' '._");
          System.out.println("       [=======]" + reset);
          gameOver = true;
          break;
        } // end of victory ascii block

        System.out.println(red + "\n------ Opponent's Turn ------" + reset);

        int botHit = currentOpponent.getWeapon().strike();
        
        // fire the sound effect for the bot's weapon
        playWeaponSound(currentOpponent.getWeapon().getName(), myPlayer);

        llama.printStrike(currentOpponent.getOpponentName(), 
          currentOpponent.getWeapon().getName(), botHit);

        // check if shields absorb incoming damage
        if (mainPlayer.getShield() > 0) {
          int currentShield = mainPlayer.getShield();
          if (botHit >= currentShield) {
            mainPlayer.setShield(0);
            mainPlayer.setHealth(mainPlayer.getHealth() - 
                (botHit - currentShield));
            System.out.println(purple + "Your shield broke!" + reset);
          } else {
            mainPlayer.setShield(currentShield - botHit);
          }
        } else {
          int playerHp = mainPlayer.getHealth();
          mainPlayer.setHealth(playerHp - botHit);
        }

        System.out.println(green + "Your Health Now: " + mainPlayer.getHealth() + 
          " | Shield: " + mainPlayer.getShield() + reset);

        if (mainPlayer.getHealth() <= 0) {
          System.out.println(red + "\nYou were eliminated..." + reset);
          gameOver = true;
          break;
        }
      }

      // rematch sequence
      System.out.println("\nPlay again?");
      System.out.println("1) Yes");
      System.out.println("2) No");
      
      int replayChoice = 0;
      while (replayChoice == 0) {
        System.out.print("Select choice: ");
        replayChoice = input.validatorChoiceMenu(1, 2);
      }

      if (replayChoice == 2) {
        playAgain = false;
        System.out.println(red + 
          "Thanks for playing! Llama has left the building..." + reset);
          System.out.println(pink + 
            "Remember to keep it 100.  See you next time!" + reset);
      }
    }
  } // end main

  // matches the weapon name to the correct audio file
  private static void playWeaponSound(String name, MusicPlayer player) {
    if (name.contains("Pump")) {
      player.playSoundEffect("audio/shottie.wav");
    } else if (name.contains("Rifle")) {
      player.playSoundEffect("audio/rifle.wav");
    } else if (name.contains("Pickaxe")) {
      player.playSoundEffect("audio/axe.wav");
    }
  }

  // reinstantiates objects to reset the match state
  public static void resetGame() {
    mainPlayer = null;
    currentOpponent = null;
    mainPlayer = new Player("Lam", "Dominion");
    mainPlayer.setWeapon(new Pickaxe()); 
    mainPlayer.setShield(0);
    
    currentOpponent = new Opponent("Bot", "Jonesy");
    currentOpponent.setWeapon(new items.AssaultRifle());
    
    gameOver = false;
  }

  // renders the ascii welcome art
  public static void printWelcome() {
    System.out.println(cyan + 
      "=================================================" + reset);
    System.out.println(purple + 
      "  _____ ___  ____ _____ _   _ ___ _____ _____ " + reset);
    System.out.println(purple + 
      " |  ___/ _ \\|  _ \\_   _| \\ | |_ _|_   _|  ___|" + reset);
    System.out.println(blue +   
      " | |_ | | | | |_) || | |  \\| || |  | | | |_   " + reset);
    System.out.println(blue +   
      " |  _|| |_| |  _ < | | | |\\  || |  | | |  _|  " + reset);
    System.out.println(cyan +   
      " |_|   \\___/|_| \\_\\|_| |_| \\_|___| |_| |_____|" + reset);
    System.out.println(cyan + 
      "=================================================" + reset);
    System.out.println(red + "               Prepare to drop!                  \n" + reset);
  }

} // end class