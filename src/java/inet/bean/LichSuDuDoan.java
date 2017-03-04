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
public class LichSuDuDoan {
    private  int userId;
    private int eventId;
    private String dbBet;
    private String lotoBet;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate;
    }
    private int status;
    private String genDate;
}
