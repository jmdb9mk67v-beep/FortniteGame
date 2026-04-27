
package logic;

import combatants.Player;
import items.Weapon;
import items.AssaultRifle;
import items.PumpShotgun;
import items.Pickaxe;
import items.MiniShield;
import items.FullShield;

public class Location {

  private String name;

  public Location(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  // assigns the shield to the player and the weapon they found
  public Weapon openChest(int chestChoice, Player player) {
    if (chestChoice == 1) {
      player.setShield(new MiniShield().getShieldAmount());
      return new AssaultRifle();
    } else {
      if (Math.random() < 0.5) {
        player.setShield(new FullShield().getShieldAmount());
        return new PumpShotgun();
      } else {
        player.setShield(0);
        return new Pickaxe();
      }
    }
  } // end openChest
} // end class