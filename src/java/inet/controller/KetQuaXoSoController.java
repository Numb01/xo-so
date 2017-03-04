/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.request.LotteryRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Duynv
 */
public class KetQuaXoSoController extends BaseController {

    private static HashMap<String, List<Lottery>> hListLottery = new HashMap<String, List<Lottery>>();
    private static String sDDMMYYYY = null;
    private LotteryCompany lotteryCompany = null;

    public KetQuaXoSoController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if (!today.equals(sDDMMYYYY)) {
            hListLottery.clear();
            sDDMMYYYY = today;
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);
        String code = request.getParameter("code");
        String link = request.getParameter("link");
        String ngay = request.getParameter("ngay");
        lotteryCompany = findLotteryCompanyOfCode(code);
        String region = lotteryCompany.getRegion();
        String company = lotteryCompany.getCompany();
        link = lotteryCompany.getCompanyLink();
        if (link != null && !"".equals(link)) {
            if (link.endsWith("-")) {
                link = link.substring(0, link.length() - 1);
            }
        }
        String link2 = link;
        String dayOfWeek = "";
        String ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
        LotteryRequest lotteryRequest = new LotteryRequest();
        List<Lottery> listLottery = null;
        List<List<String>> listDuoi = null;
        Today t = new Today();
        String key = "";
        String title = null;
        String des = null;
        String keyword = null;
        if (region.equalsIgnoreCase("MT")) {
            if ((t.getHour() == 17 && t.getMinute() > 45) || t.getHour() > 17) {
                ddmmyyyy = today;
            }
        } else if (region.equalsIgnoreCase("MN")) {
            if ((t.getHour() == 16 && t.getMinute() > 45) || t.getHour() > 16) {
                ddmmyyyy = today;
            }
        } else {
            if ((t.getHour() == 18 && t.getMinute() > 45) || t.getHour() > 18) {
                ddmmyyyy = today;
            }
        }
        String sDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(sDDMMYYYY), -35);
        if (ngay != null) {
            if (ngay.contains("-")) {
                ngay = ngay.replace("-", "/");
            }
            ddmmyyyy = ngay;
            key = code + "_ngay_" + ddmmyyyy;

        } else {
            key = code + ddmmyyyy;

        }

        dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(ddmmyyyy);

        if (hListLottery.containsKey(key)) {
            listLottery = hListLottery.get(key);
        } else {
            listLottery = lotteryRequest.parserLotteryResult(code.toUpperCase(), sDate, ddmmyyyy);

            if (listLottery != null) {
                hListLottery.put(key, listLottery);
            }
        }
        if (listLottery != null) {
            //listDuoi = findDuoi(listLottery);
            List<String> listDuoiCacTinh = null;
            for (int i = 0; i < listLottery.size(); i++) {
                listDuoiCacTinh = StringPro.filterDauOrDuoi(listLottery.get(i), true);
                if (listDuoi == null) {
                    listDuoi = new ArrayList<List<String>>();
                }
                listDuoi.add(listDuoiCacTinh);
            }
        }
        if (code != null && region != null && company != null) {
            title = StringPro.buildTileCacTinh(code, region, company);
            des = StringPro.buildDescCacTinh(code, region, company);
        }
        if (link != null) {
            if (link.contains("-")) {
                link = link.replace("-", " ");
                keyword = StringPro.buildKeyWordCacTinh(code.toLowerCase(), link.toLowerCase(), region);
            }

            if (!link.contains("-")) {
                keyword = StringPro.buildKeyWordCacTinh(code.toLowerCase(), link.toLowerCase(), region);
            }
        }
        mod.addObject("dayOfWeek", dayOfWeek);
        mod.addObject("listLottery", listLottery);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("region", region);
        mod.addObject("ddmmyyyy", ddmmyyyy);
        mod.addObject("company", company);
        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("link", link);
        mod.addObject("link2", link2);
        if (code != null) {
            mod.addObject("code", code.substring(2, code.length()));
        }

        if ("pc".equals(strMobile)) {
            mod.setViewName("/xosocactinh");
        } else {
            mod.setViewName("mobile/xosocactinh");
        }
        return mod;
    }

}
