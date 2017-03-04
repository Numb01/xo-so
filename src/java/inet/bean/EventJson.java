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
public class EventJson {

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

    public Event getContent() {
        return content;
    }

    public void setContent(Event content) {
        this.content = content;
    }
    private int status;
    private String desc;
    private Event content;
}
