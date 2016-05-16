/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_client;

import java.io.IOException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import static java.lang.System.in;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 *
 * @author moel
 */
public class Werewolf_Client {
    private static String message;
    private static DataInputStream Din;
    private static DataOutputStream Dout;
    
    private Socket clientSocket;
    private Random random;
    
    public void sentToClient(String adr, int p, String message)throws SocketException, UnknownHostException, IOException{
        DatagramSocket sender = new DatagramSocket();
        InetAddress ip = InetAddress.getByName(adr);
        byte[] sendData = new byte[1024];
        sendData = message.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, ip, p);
        double rand = random.nextDouble();
        if(rand < 0.85){
            sender.send(sendPacket);
        }
        sender.close();      
    }
    
    //Unreliable receiver
    public void recvFromClient(String adr, int p) throws SocketException, IOException{
        DatagramSocket recv = new DatagramSocket(p);
        byte[] recvData = new byte[1024];
        
        while(true){
            DatagramPacket recvPacket =new DatagramPacket(recvData, recvData.length);
            recv.receive(recvPacket);
            
            String s = new String(recvPacket.getData(), 0, recvPacket.getLength());
            System.out.println("ReceivedD\n" +s);
        }
    }
    
    // Connect to server
    public void connect(String ip, int p)throws IOException{
        clientSocket = new Socket(ip,p);
    }
    
    // Send Message to Server
    public void sendToServer(Object o)throws IOException{
        OutputStream out = clientSocket.getOutputStream();
        ObjectOutputStream o_out = new ObjectOutputStream(out);
        o_out.writeObject(o);
    }
    
    // Rececive Message to Server
    public void recFromServer(Object o)throws IOException, ClassNotFoundException{
        Object temp;
        InputStream in = clientSocket.getInputStream();
        ObjectInputStream o_in = new ObjectInputStream(in);
        temp = (Object) o_in.readObject();
        
        if(temp instanceof JSONObject){
            System.out.println(temp.toString());
        } else System.out.println("Error");
    }
    
    private static void React() throws IOException{
        switch(message){
            case "init":
                break;
            case "malam":
                break;
            case "siang":
                break;
            case "end":
                System.out.println("Permainan berakhir");
                break;
            default: //do nothing
        }
    } 
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        
        Socket client = new Socket(serverName, port);
        InputStream inFromServer = client.getInputStream();
        OutputStream outToServer = client.getOutputStream();  
        Din = new DataInputStream(inFromServer);
        Dout = new DataOutputStream(outToServer);
        message = "INIT";
        //System.out.println("Masuk");
        while(!message.equals("END")){
            System.out.println("Masuk");
            message = Din.readUTF();
            React();
        }//*/
    }
    
}
