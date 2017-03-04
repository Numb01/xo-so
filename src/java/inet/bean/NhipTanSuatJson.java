/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

import java.util.List;

/**
 *
 * @author Duynv
 */
public class NhipTanSuatJson {
    private int status;
    private List<NhipTanSuat> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<NhipTanSuat> getList() {
        return list;
    }

    public void setList(List<NhipTanSuat> list) {
        this.list = list;
    }
}
