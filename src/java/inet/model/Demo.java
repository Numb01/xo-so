/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.model;

import inet.bean.Lottery;
import java.util.List;

/**
 *
 * @author 1st.max
 */
public class Demo {
    public static void main(String[] args) {
        LotteryResultDAO lotteryResultDAO=new LotteryResultDAO();
        List<Lottery> listLottery=lotteryResultDAO.findLotteryNewestRegion("MN","14/04/2016");
        if(listLottery!=null){
            for (Lottery lottery : listLottery) {
                System.out.println("special:"+lottery.getSpecial());
                System.out.println("special:"+lottery.getSpecial_xsmb());
                System.out.println("---------------------------------------");
            }
        }
        
    }
    
}
