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
public class ResultCauTamGiac {
    private List<CauTamGiac> cauTamGiac;

    public List<CauTamGiac> getCauTamGiac() {
        return cauTamGiac;
    }

    public void setCauTamGiac(List<CauTamGiac> cauTamGiac) {
        this.cauTamGiac = cauTamGiac;
    }

    public List<CauTamGiac> getResultCauTamGiac() {
        return resultCauTamGiac;
    }

    public void setResultCauTamGiac(List<CauTamGiac> resultCauTamGiac) {
        this.resultCauTamGiac = resultCauTamGiac;
    }
    private List<CauTamGiac> resultCauTamGiac;
}
