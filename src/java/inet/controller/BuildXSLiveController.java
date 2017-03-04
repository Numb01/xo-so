/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.listener.XSLive;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.WapTool;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author hanhlm
 */
public class BuildXSLiveController extends AbstractController{

    public BuildXSLiveController() {
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = new ModelAndView();

        String region = request.getParameter("r");

        String ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());
        String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(ddmmyyyy);

        List<Lottery> listLottery = null;
        List<String> listDuoi = null;
        List<String> listDau = null;
        Lotterys lotterys = null;
        int numSize = 0;
        if ("MB".equalsIgnoreCase(region)) {
            listLottery = XSLive.listLotteryMB;
            listDuoi = XSLive.listDuoiMB;
            listDau = XSLive.listDauMB;
        } else if ("MT".equalsIgnoreCase(region)) {
            lotterys = XSLive.listLotteryMT;
            numSize = XSLive.numSizeMT;
            listDuoi = XSLive.listDuoiMT;
            if (numSize == 2) {
                numSize = 3;
            } else if (numSize == 3) {
                numSize = 2;
            }
        } else if ("MN".equalsIgnoreCase(region)) {
            lotterys = XSLive.listLotteryMN;
            numSize = XSLive.numSizeMN;
            listDuoi = XSLive.listDuoiMN;
            if (numSize == 2) {
                numSize = 3;
            } else if (numSize == 3) {
                numSize = 2;
            }            
        }

        mod.addObject("listLottery", listLottery);
        mod.addObject("lotterys", lotterys);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("listDau", listDau);
        mod.addObject("numSize", numSize);
        mod.addObject("region", region);
        mod.addObject("ddmmyyyy", ddmmyyyy);
        mod.addObject("dayOfWeek", dayOfWeek);
        
        String strMobile="pc";        
        try{
            boolean isMobile = WapTool.isMobileDevice(request);            
            if(isMobile){
                strMobile="mobile";
            }            
        }catch(Exception e){}
        
//        if("pc".equals(strMobile)){
//            mod.setViewName("/ajax/live");            
//        }else{
//            mod.setViewName("/ajax/mobile/live");            
//        }
        
        return mod;
    }

}
