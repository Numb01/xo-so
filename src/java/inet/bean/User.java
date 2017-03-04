/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

/**
 *
 * @author hanhlm
 */
public class User {
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getInetId() {
        return inetId;
    }

    public void setInetId(int inetId) {
        this.inetId = inetId;
    }

    public int getCau() {
        return cau;
    }

    public void setCau(int cau) {
        this.cau = cau;
    }

    public String getDbBet() {
        return dbBet;
    }

    public void setDbBet(String dbBet) {
        this.dbBet = dbBet;
    }

    public String getLotoBet() {
        return lotoBet;
    }

    public void setLotoBet(String lotoBet) {
        this.lotoBet = lotoBet;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getTotalMarkDb() {
        return totalMarkDb;
    }

    public void setTotalMarkDb(int totalMarkDb) {
        this.totalMarkDb = totalMarkDb;
    }

    public int getTotalMarkLoto() {
        return totalMarkLoto;
    }

    public void setTotalMarkLoto(int totalMarkLoto) {
        this.totalMarkLoto = totalMarkLoto;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    private String username;
    private String email;
    private String fullname;
    private String avatar;
    private int inetId;
    private int cau;
    private String dbBet;
    private String lotoBet;
    private int mark;
    private int totalMarkDb;
    private int totalMarkLoto;
    private String genDate;
    private int bac;
    private String content;
    private String mobile;
    private String dateWinDb;
    private String dateWinLoto;
    private int isBetToday;
    private String dbBetToday;
    private String lotoBetToday;
    private int eventId;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getDateWinDb() {
        return dateWinDb;
    }

    public void setDateWinDb(String dateWinDb) {
        this.dateWinDb = dateWinDb;
    }

    public String getDateWinLoto() {
        return dateWinLoto;
    }

    public void setDateWinLoto(String dateWinLoto) {
        this.dateWinLoto = dateWinLoto;
    }

    public int getIsBetToday() {
        return isBetToday;
    }

    public void setIsBetToday(int isBetToday) {
        this.isBetToday = isBetToday;
    }

    public String getDbBetToday() {
        return dbBetToday;
    }

    public void setDbBetToday(String dbBetToday) {
        this.dbBetToday = dbBetToday;
    }

    public String getLotoBetToday() {
        return lotoBetToday;
    }

    public void setLotoBetToday(String lotoBetToday) {
        this.lotoBetToday = lotoBetToday;
    }

    public String getNearestDateBet() {
        return nearestDateBet;
    }

    public void setNearestDateBet(String nearestDateBet) {
        this.nearestDateBet = nearestDateBet;
    }

    public String getNearestDbBet() {
        return nearestDbBet;
    }

    public void setNearestDbBet(String nearestDbBet) {
        this.nearestDbBet = nearestDbBet;
    }

    public String getNearestLotoBet() {
        return nearestLotoBet;
    }

    public void setNearestLotoBet(String nearestLotoBet) {
        this.nearestLotoBet = nearestLotoBet;
    }
    private String nearestDateBet;
    private String nearestDbBet;
    private String nearestLotoBet;
    
}
