/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_server;

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
import java.util.Random;
import org.json.JSONObject;

/**
 *
 * @author moel
 */
public class Werewolf_Server {
    
    private static String host;
    private static int port;
    private Socket socket;
    private final String DEF_HOST = "loaclhost";
    private ServerSocket serverSocket;
    
    PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket fromClient;
    Thread thread;
    
    static int counter = 0;
    static int n_player;
            
    public Werewolf_Server(){
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try{
            host = args[0];
            port = Integer.parseInt(args[1]);
            n_player = Integer.parseInt(args[2]);
            int n_werewolf=0;
            ServerSocket listener = new ServerSocket(port);
            Socket server;
            int i = 0;
            System.out.println("Server has been set up");
            ConnectionHandler[] connection = new ConnectionHandler[n_player];
            //waiting n_player
            while(i<n_player){    
                server = listener.accept();
                
                //random role
                Random r = new Random();

                int role = r.nextInt(2) + 1;
                if (role == 1 && n_werewolf<=n_player/3 ){
                    n_werewolf++;
                } else {
                    role=0;
                }
                connection[i]= new ConnectionHandler(i,role,server);
                Thread t = new Thread(connection[i]);
                t.start();
                i++;
            }
            
            System.out.println("Game Start");
            System.out.println(n_werewolf);
            System.out.println("Day #1");
            i=0;
            while(i<n_player){
                System.out.println("Player id:" + connection[i].player.getID());
                if(connection[i].player.getRole() == 0){
                    System.out.println("Player role: Villager");
                } else System.out.println("Player role: Werewolf");
                System.out.println("Player role:" + connection[i].player.getStatus());
                System.out.println("------------------------------------");
                i++;
            } 
        } catch (IOException ioe) {
          System.out.println("IOException on socket listen: " + ioe);
          ioe.printStackTrace();
        }
    }
    
}
