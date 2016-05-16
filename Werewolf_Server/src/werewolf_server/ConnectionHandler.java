/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_server;

import java.io.*;
import java.net.*;
import java.util.Random;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author moel
 */
public class ConnectionHandler implements Runnable {
    private Socket server;
    private String line,input;
    public Player player;
    private String state;
    
    int nWerewolf = 0;
    int nVillager = 0;
    
   // private int game_state;
    
    //nama-nama status
    private final String init = "INIT";
    private final String siang = "SIANG";
    private final String malam = "MALAM";
    private final String end = "END";
    
    public ConnectionHandler(int _id,int _role,Socket _server){
        this.server = _server;
        this.player = new Player();
        player.setID(_id);
        player.setRole(_role);
        System.out.println("Player #"+ _id + "has join\n");
    }
    
    
    @Override
    public void run() {
        
        try{    
        //INITIALIZATION
        
        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        DataInputStream in = new DataInputStream(server.getInputStream());
        
        //ASKING USERNAME
        //mengirimkan status pada user
        out.writeUTF(init);
        String input = in.readUTF();
        System.out.println(input+" is online");
        //player.setName(input);
        out.writeUTF(Integer.toString(player.getID()));
        state = malam;
        Boolean done = false;
        
        while (!done){
            if(state.equals(malam)){
                out.writeUTF(malam);
            } else if(state.equals(siang)){
                out.writeUTF(siang);
            } else if(state.equals(end)){
                out.writeUTF("Permainan Berakhir");
            }
            
        }
        } catch (IOException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
