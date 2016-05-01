/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_off;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author moel
 */
public class Client {
    private int player_id;
    private boolean is_alive;
    private String ip_address; // client ip address
    private int port;
    private String usename;
    private boolean isWerewolf; // false for civilians
    private boolean isProposer;
    private int n_proposal;
    private int former_kpu;
    
    private Socket clientSocket;
    
    public Client(int id, int p){
        player_id = id;
        port = p;
        n_proposal = 0;
        
        former_kpu = -99; // initialize
        isProposer = false;
    }
    
    //Setter
    public void becomeProposer(boolean b){ //Set isProporser
        isProposer = b;
    }
    
    public void setPort(int p){
        port = p;
    }

    public void setPlayerID(int id) {
        this.player_id = id;
    }

    public void setProposalNum(int proposalNum) {
        this.n_proposal = proposalNum;
    }

    public void setIpAddress(String ip) {
        this.ip_address = ip;
    }
    
    public void setPreviouskpu(int f_kpu) {
        this.former_kpu = f_kpu;
    }
    
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }    
    
    //Getter
    public int getPlayerID() {
        return player_id;
    }
    
    public int getProposalNum() {
        return n_proposal;
    }

    public String getIpAddress() {
        return ip_address;
    }
    
    public int getPreviouskpu() {
        return former_kpu;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

/* contoh yang UDP    
    public void sentToClient(String adr, int p, String message)throws SocketException, UnknownHostException, IOException{
        DatagramSocket sender = new DatagramSocket();
        InetAddress ip = InetAddress.getByName(adr);
        byte[] sendData = new byte[1024];
        sendData = message.getBytes();
        
        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, ip, p);
        sender.send(sendPacket);
        sender.close();      
    }
*/
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
            System.out.println((JSONObject)temp.toString());
        } else System.out.println("Error");
    }
    
    
}
