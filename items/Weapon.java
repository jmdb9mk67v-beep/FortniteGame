
package items;

import java.security.SecureRandom;

public class Weapon {

  private String name;
  private int maxDamage;
  private String actionVerb;
  private SecureRandom randNum;

  public Weapon(String wName, int wDamage, String wVerb) {
    this.name = wName;
    this.maxDamage = wDamage;
    this.actionVerb = wVerb;
    this.randNum = new SecureRandom();
  } // end constructor

  public String getName() {
    return this.name;
  } 

  public int getMaxDamage() {
    return this.maxDamage;
  } 

  public String getActionVerb() {
    return this.actionVerb;
  } 

  // This handles the random number generator
  public int strike() {
    return randNum.nextInt(this.maxDamage + 1);
  } // end strike
} // end class
