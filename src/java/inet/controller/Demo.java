/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.util.DatePro;

/**
 *
 * @author 1st.max
 */
public class Demo {

    public static void main(String[] args) {
//        String tinh = "quang-binh-";
//        if (tinh.endsWith("-")) {
//            System.out.println("dung roi");
//            tinh = tinh.substring(0, tinh.length()-1);
//            System.out.println("tinh la:" + tinh);
//        }
        String strthu = DatePro.getDateOfWeekDDMMYYYY("01-01-2017");
        System.out.println("" + strthu);

    }
}
