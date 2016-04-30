/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_off;

/**
 *
 * @author User
 */
public class Player {
  private boolean status = true;
  private boolean role;
  private String IPaddress;
  
    /**
     *
     * @param _role
     */
  public Player(boolean _role) {
    role = _role;
    status = true;
  }
  
  public boolean getStatusPlayer() {
    return status;
  }
  
  public boolean getRolePlayer() {
    return role;
  }
  
  public void killPlayer() {
    status = false;
  }
}
