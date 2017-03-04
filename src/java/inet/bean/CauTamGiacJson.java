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
public class CauTamGiacJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public List<ResultCauTamGiac> getList() {
//        return list;
//    }
//
//    public void setList(List<ResultCauTamGiac> list) {
//        this.list = list;
//    }
//    private List<ResultCauTamGiac> list;
    
    private ResultCauTamGiac list;

    public ResultCauTamGiac getList() {
        return list;
    }

    public void setList(ResultCauTamGiac list) {
        this.list = list;
    }
}
