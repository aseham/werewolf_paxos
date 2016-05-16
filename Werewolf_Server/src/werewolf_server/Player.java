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
    private int role; // 1 is werewolf, 0 is vilagers
    private String status;
    
    public Player(){
        id = 0;
        role = 0;
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
    
    public int getRole(){
        return role;
    };
    
    public void setRole(int role){
        this.role = role;
    };
    
    public String getStatus(){
        return status;
    };
    
    public void setStatus(String _stat){
        this.status = _stat;
    };
    
}
