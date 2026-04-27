
package util;

import java.security.SecureRandom;

public class PlayByPlay {

  private SecureRandom randNum;

  public PlayByPlay() {
    this.randNum = new SecureRandom();
  } // end constructor

  public void printStrike(String attacker, String weapon, int damage) {
    int diceRoll = randNum.nextInt(3); 

    if (damage == 0) {
      if (diceRoll == 0) {
        System.out.println(attacker + " swings with fury and completely misses!");
      } else if (diceRoll == 1) {
        System.out.println(attacker + " fires wildly and hits nothing but air!");
      } else {
        System.out.println(attacker + " miscalculates and the " + weapon + " misses!");
      } // end inner if
    } else {
      if (diceRoll == 0) {
        System.out.println(attacker + " winds up and hits for " + damage + " damage!");
      } else if (diceRoll == 1) {
        System.out.println(attacker + " lands a clean shot with the " + 
          weapon + " for " + damage + " damage!");
      } else {
        System.out.println(attacker + " strikes with devastating force, dealing " + 
          damage + " damage!");
      } 
    } 
  } // end printStrike
} // end class
