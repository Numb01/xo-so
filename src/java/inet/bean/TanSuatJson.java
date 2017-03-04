/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.util.List;

/**
 *
 * @author hanhlm
 */
public class TanSuatJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<List<TanSuat>> getList() {
        return list;
    }

    public void setList(List<List<TanSuat>> list) {
        this.list = list;
    }
    private List<List<TanSuat>> list;
}
