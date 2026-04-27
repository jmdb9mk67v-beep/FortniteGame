
/**
 * Master Blueprint for Shields
 */
package items;

public class ShieldItem {

  private String name;
  private int shieldAmount;

  public ShieldItem(String sName, int sAmount) {
    this.name = sName;
    this.shieldAmount = sAmount;
  } // end constructor

  public String getName() {
    return this.name;
  } 

  public int getShieldAmount() {
    return this.shieldAmount;
  } 
} // end class