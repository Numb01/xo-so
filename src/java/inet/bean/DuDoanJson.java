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
public class DuDoanJson {
    private int status;
    private String desc;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LichSuDuDoan getContent() {
        return content;
    }

    public void setContent(LichSuDuDoan content) {
        this.content = content;
    }
    private LichSuDuDoan content;
}
