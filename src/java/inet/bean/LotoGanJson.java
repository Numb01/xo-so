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
public class LotoGanJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<LotoGan> getList() {
        return list;
    }

    public void setList(List<LotoGan> list) {
        this.list = list;
    }
    private String code;
    private List<LotoGan> list;
}
