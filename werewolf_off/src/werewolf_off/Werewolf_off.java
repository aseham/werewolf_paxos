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
  
  public static void main(String[] args) {
    // TODO code application logic here
    System.out.print("Masukkan jumlah player: ");
    Scanner in = new Scanner(System.in);
    nPlayer = in.nextInt();
    boolean[] role; role = new boolean[nPlayer];
    role = shuffle(nPlayer,Math.round(nPlayer/3));
    for (int i=0;i<nPlayer;i++) {
      System.out.print("Player #"+(i)+" joins the game");
      if (role[i])
        System.out.println(", and is the werewolf.");
      else
        System.out.println(".");
    }
  }
    
}
