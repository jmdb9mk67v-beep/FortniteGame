
package combatants;

public class Opponent extends DefaultSkin {

  private String opponentName;

  public Opponent(String opponentName, String skinType) {
    super();
    this.opponentName = opponentName;
  } // end constructor

  public String getOpponentName() {
    return this.opponentName;
  } 

  public void setOpponentName(String opponentName) {
    this.opponentName = opponentName;
  }

} // end class
