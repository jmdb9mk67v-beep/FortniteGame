
package combatants;

public class Player extends DefaultSkin {

  private String playerName;

  public Player(String playerName, String skinType) {
    // The super keyword sends the skinType 
    // up to the DefaultSkin blueprint or Java will get angry.
    // Parents First!
    super();
    this.playerName = playerName;
  } // end constructor

  public String getPlayerName() {
    return this.playerName;
  } 

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  } 

} // end class
