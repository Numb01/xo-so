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
public class TanSuat {
    private String capso;
    private String openDate;
    private int solanve;
    private boolean special;

    public boolean isSpecial() {
        return special;
    }

    public void setSpecial(boolean special) {
        this.special = special;
    }
    public String getCapso() {
        return capso;
    }

    public void setCapso(String capso) {
        this.capso = capso;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public int getSolanve() {
        return solanve;
    }

    public void setSolanve(int solanve) {
        this.solanve = solanve;
    }
    
}
