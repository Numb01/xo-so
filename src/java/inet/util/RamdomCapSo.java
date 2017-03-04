/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.util.Random;

/**
 *
 * @author hanhlm
 */
public class RamdomCapSo {
    public static String  getCapSo(){
        Random r=new Random();
        int i=r.nextInt(100);
        String result="";
        if(i<10){result="0"+i;}
        else{result=""+i;}
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(RamdomCapSo.getCapSo());
    }
}
