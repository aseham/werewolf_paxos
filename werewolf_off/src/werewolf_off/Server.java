/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_off;


import java.awt.print.Paper;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.io.PrintStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.parser.JSONParser;

/**
 *
 * @author moel
 */
public class Server implements Runnable{
    
    private String host;
    private int port;
    private Socket socket;
    private final String DEF_HOST = "loaclhost";
    private ServerSocket serverSocket;
    
    PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket fromClient;
    Thread thread;
    
    static int counter = 0;
    
    public Server(){
        this.host = host;
        this.port = port;
        this.socket = socket;
        try{
            serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void connect(String host, int port) throws IOException{
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
        System.out.println("Permainan dimulai");
    }
    
    public JSONObject receiveJSON() throws IOException{
        InputStream in = socket.getInputStream();
        ObjectInputStream o_in = new ObjectInputStream(in);
        JSONObject line = null;
        try{
            line = (JSONObject) o_in.readObject();
        } catch (ClassNotFoundException e){
            e.printStackTrace();;
        }
        
        return line;
    }
    
    public void sendJSON(JSONObject jobject) throws IOException{
        
        JSONObject jobject2 = new JSONObject();
        jobject2.put("key", new Paper());
        
        OutputStream out = socket.getOutputStream();
        ObjectOutputStream o_out = new ObjectOutputStream(out);
        o_out.writeObject(jobject2);
        out.flush();
    }
    
    public void run(){
        try{
            while(true){
                fromClient = serverSocket.accept();
                counter++;
                streamFromClient = new BufferedReader(new InputStreamReader(fromClient.getInputStream()));
                
                //InputStreamReader = new InputStreamReader(fromClient.getInputStream());
                String s = streamFromClient.readLine();
                System.out.println(s);
                streamToClient.println("Welcome "+s);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        
        try{
            fromClient.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
