
/**
 * DefaultSkin.java
 * parent class for both the
 * Player and the Opponent.
 * Base Blueprint for all combatants
 */
package combatants;

import items.Weapon;
import items.Pickaxe;

public class DefaultSkin {

  private int health;
  private int shield;
  private Weapon currentWeapon;

  public DefaultSkin() {
    this.health = 100;
    this.shield = 0;
    // Every combatant automatically spawns holding a Pickaxe object
    this.currentWeapon = new Pickaxe(); 
  } // end constructor

  /**
   * go getters and setters 
   */

  public int getHealth() {
    return this.health;
  } // end getHealth

  public void setHealth(int newHealth) {
    this.health = newHealth;
  } // end setHealth

  public int getShield() {
    return this.shield;
  } // end getShield

  public void setShield(int newShield) {
    this.shield = newShield;
  } // end setShield

  public Weapon getWeapon() {
    return this.currentWeapon;
  } // end getWeapon

  public void setWeapon(Weapon newWeapon) {
    this.currentWeapon = newWeapon;
  } // end setWeapon

} // end class