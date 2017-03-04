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
public class XuanTocDo {    
    private int userId;
    private String username;
    private String email;

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
    private String fullname;
    private String avatar;
    private int inetId;
    private int cau;
    private String dbBet;
    private String lotoBet;
    private int mark;
    private int totalMarkDb;
    private int bac;
    private String content;
    private int isBetToday;

    public int getIsBetToday() {
        return isBetToday;
    }

    public void setIsBetToday(int isBetToday) {
        this.isBetToday = isBetToday;
    }
}
