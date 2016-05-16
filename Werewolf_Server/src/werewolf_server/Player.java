/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_server;

import java.util.Random;

/**
 *
 * @author moel
 */
public class Player {
    private int id;
    private boolean isWerewolf;
    private String status;
    
    public Player(){
        id = 0;
        isWerewolf = false;
        status = "ALIVE";
    }
    
    public Player(int id){
        id = id;
    }
    
    public int getID(){
        return id;
    };
    
    public void setID(int id){
        this.id = id;
    };
    
    
    
}
