/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package werewolf_off;

import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author User
 */
public class Werewolf_off {

  /**
   * @param args the command line arguments
   */
  private static int nPlayer;
  private static int nWerewolf, nVillager;
  private static int endState = 0;
    
  public static boolean[] shuffle(int n, int out) {
    boolean[] result; result = new boolean[n];
    int[] temp; temp = new int[n];
    for (int i=0;i<n;i++) {
      temp[i]=i;
      result[i]=false;
    }
    Random rng = new Random();
    for (int i=0;i<out;i++) {
      int num = rng.nextInt(n-i);
      result[temp[num]] = true;
      for (int j=num;j<n-i-1;j++) {
        temp[j] = temp[j+1];
      }
    }
    return result;
  }
  
  public static boolean endCondition() {
    if (nWerewolf > nVillager)
      endState = 1;  // werewolf menang
    else if (nWerewolf == 0)
      endState = 2;  // villager menang
    else endState = 0;  // game blum berakhir
    return (endState!=0);
  }
  
  public static void main(String[] args) {
    // TODO code application logic here
    System.out.print("Masukkan jumlah player: ");
    Scanner in = new Scanner(System.in);
    nPlayer = in.nextInt();
    
    boolean[] role; role = new boolean[nPlayer];
    System.out.println("You are the room master.");
    nWerewolf = Math.round(nPlayer/3);  // banyak werewolf = 1/3 total pemain, ditentukan acak
    nVillager = nPlayer - nWerewolf;
    role = shuffle(nPlayer,nWerewolf); 
    
    Player[] player = new Player[nPlayer];
    for (int i=0;i<nPlayer;i++) {
      player[i] = new Player(role[i]);
    }
    
    for (int i=1;i<nPlayer;i++) {
      System.out.println("Player #"+(i)+" joins the game.");
      //if (role[i])
      //  System.out.println(", and is the werewolf.");  // cuman ngetes distribusi werewolf doang
      //else
      //  System.out.println(".");
    }
    System.out.println("---------------------------\nGame has been started.");
    System.out.print("You are a ");
    if (role[0])
      System.out.println("werewolf.\n---------------------------");
    else
      System.out.println("villager.\n---------------------------");
    System.out.println("--- Night 1. The werewolves introduced at each other, meanwhile the villager sleeps. ");
    if (role[0]) {
      System.out.print("The werewolves are: Player #0");  // pemain dianggap player #0
      for (int i=1;i<nPlayer;i++)
        if (role[i]) System.out.println(", Player #"+(i));
    }
    System.out.println("--- Day 2. All villagers awake. There are "+nWerewolf+" werewolves in the game.");
    int days = 2;
    do {
      System.out.print("--- Night "+days+". ");
      int target;
      if (role[0]) {
        System.out.println("Vote a villager to be killed (type the player number): ");
        Scanner in2 = new Scanner(System.in);
        do {
          target = in2.nextInt();
        } while (role[target] || !player[target].getStatusPlayer());
        System.out.println("Waiting for agreement among the werewolves...");
        // define some paxos here
      } else {
        System.out.println("The villager sleeps.");
        do {
          Random rng = new Random();
          target = rng.nextInt(nPlayer);
        } while (role[target] || !player[target].getStatusPlayer());
      }
      days++;
      System.out.println("--- Day "+days+". Player #"+target+" is killed.");
      nVillager--; player[target].killPlayer();
      
      // villager's vote blum diimplementasiin
      
      
    } while (!endCondition());
    
    System.out.println("---------------------------\nGame is over.");
    switch (endState) {
      case 1 : System.out.println("The werewolves win!");
        break;
      case 2 : System.out.println("The villagers win!");
        break;
      default: System.out.println("Error found.");
    }
  }
    
}
