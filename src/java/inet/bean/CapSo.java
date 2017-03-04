/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.util.List;

/**
 *
 * @author iNET
 */
public class CapSo {
    String ngayve;

    public String getNgayve() {
        return ngayve;
    }

    public void setNgayve(String ngayve) {
        this.ngayve = ngayve;
    }
    String capso;

    public String getChanle() {
        return chanle;
    }

    public void setChanle(String chanle) {
        this.chanle = chanle;
    }

    public String getCapso() {
        return capso;
    }

    public void setCapso(String capso) {
        this.capso = capso;
    }

    public String getDdmmyyyy() {
        return ddmmyyyy;
    }

    public void setDdmmyyyy(String ddmmyyyy) {
        this.ddmmyyyy = ddmmyyyy;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    String ddmmyyyy;
    String dayOfWeek;
    
    int solanxuathien;

    public int getSolanxuathien() {
        return solanxuathien;
    }

    public void setSolanxuathien(int solanxuathien) {
        this.solanxuathien = solanxuathien;
    }

    public int getSongaychuave() {
        return songaychuave;
    }

    public void setSongaychuave(int songaychuave) {
        this.songaychuave = songaychuave;
    }

    public int getGancucdai() {
        return gancucdai;
    }

    public void setGancucdai(int gancucdai) {
        this.gancucdai = gancucdai;
    }
    int songaychuave;
    int gancucdai;
    String chanle;
    String special;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
    
    int numDayOfWeek;

    public int getNumDayOfWeek() {
        return numDayOfWeek;
    }

    public void setNumDayOfWeek(int numDayOfWeek) {
        this.numDayOfWeek = numDayOfWeek;
    }
    
    private String kep;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }
    
    LotteryResult lotteryResult1;

    public LotteryResult getLotteryResult1() {
        return lotteryResult1;
    }

    public void setLotteryResult1(LotteryResult lotteryResult1) {
        this.lotteryResult1 = lotteryResult1;
    }

    public LotteryResult getLotteryResult2() {
        return lotteryResult2;
    }

    public void setLotteryResult2(LotteryResult lotteryResult2) {
        this.lotteryResult2 = lotteryResult2;
    }
    LotteryResult lotteryResult2;
    
    private Lottery lottery;

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
}
