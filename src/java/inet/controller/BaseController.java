/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Lotterys;
import inet.listener.XSLive;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.StringPro;
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
public class BaseController extends AbstractController {

    protected String canonical = null;
    protected String today = null;
    protected String strMobile = "";
    protected String todayOfWeek = "";
    //duy them cho phan duong link tuyet doi

//    private final String strUrl = "http://localhost:8080";
     private final String strUrl = "http://sxmb.vn";

    public BaseController() {
    }

    protected void loadBase() {
        today = DateProc.getDateString(DateProc.createTimestamp());
        todayOfWeek = DatePro.getDateOfWeekDDMMYYYY(today);
    }

    private synchronized void getUserAgent(HttpServletRequest request) {
        try {
            synchronized (strMobile) {
                boolean isMobile = WapTool.isMobileDevice(request);
                //System.out.println("isMobile=="+isMobile);
                if (isMobile) {
                    strMobile = "mobile";
                } else {
                    strMobile = "pc";
                }
                //strMobile = "pc";
            }

        } catch (Exception e) {
            System.out.println("loi " + e.toString());
        }

    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = new ModelAndView();
        String url = request.getRequestURL().toString();
        if (url.indexOf("www") > 0) {
            try {
                String uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
                if (uri != null) {
                    url = "http://sxmb.vn" + uri;
                } else {
                    url = "http://sxmb.vn";
                }
                response.setStatus(301);
                response.setHeader("location", url);
            } catch (Exception e) {
            }
        }

        canonical = (String) request.getAttribute("javax.servlet.forward.request_uri");
        if (canonical != null) {
            canonical = "http://sxmb.vn" + canonical;
            if (checkToUpcase(canonical)) {
                canonical = canonical.toLowerCase();
                response.sendRedirect(canonical);
            }
        }
        if (canonical == null || canonical.equals("")) {
            canonical = "http://sxmb.vn";
        }

        loadBase();

        getUserAgent(request);

        mod.addObject("canonical", canonical);
        mod.addObject("today", today);
        mod.addObject("listCompany", XSLive.listCompany);
        mod.addObject("strUrl", strUrl);
        return mod;
    }

    protected Lotterys findLotterys(List<Lottery> list, String ddmmyyyy) {
        Lotterys lotterys = null;
        LotteryCompany lotteryCompany = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (lotterys == null) {
                    lotterys = new Lotterys();
                    lotterys.setOpenDate(ddmmyyyy);
                    lotterys.setCode(list.get(i).getCode());
                    lotterys.setProvince(list.get(i).getProvince());
                    lotterys.setSpecial(list.get(i).getSpecial());
                    lotterys.setFirst(list.get(i).getFirst());
                    lotterys.setSecond(list.get(i).getSecond());
                    lotterys.setThird(list.get(i).getThird());
                    lotterys.setFourth(list.get(i).getFourth());
                    lotterys.setFifth(list.get(i).getFifth());
                    lotterys.setSixth(list.get(i).getSixth());
                    lotterys.setSeventh(list.get(i).getSeventh());
                    lotterys.setEighth(list.get(i).getEighth());
                    lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
                    lotterys.setLink(list.get(i).getCode().toLowerCase() + "-xo-so-" + lotteryCompany.getCompanyLink() + ".html");
                } else {
                    lotterys.setProvince(lotterys.getProvince() + "+" + list.get(i).getProvince());
                    lotterys.setCode(lotterys.getCode() + "+" + list.get(i).getCode());
                    lotterys.setSpecial(lotterys.getSpecial() + "+" + list.get(i).getSpecial());
                    lotterys.setFirst(lotterys.getFirst() + "+" + list.get(i).getFirst());
                    lotterys.setSecond(lotterys.getSecond() + "+" + list.get(i).getSecond());
                    lotterys.setThird(lotterys.getThird() + "+" + list.get(i).getThird());
                    lotterys.setFourth(lotterys.getFourth() + "+" + list.get(i).getFourth());
                    lotterys.setFifth(lotterys.getFifth() + "+" + list.get(i).getFifth());
                    lotterys.setSixth(lotterys.getSixth() + "+" + list.get(i).getSixth());
                    lotterys.setSeventh(lotterys.getSeventh() + "+" + list.get(i).getSeventh());
                    lotterys.setEighth(lotterys.getEighth() + "+" + list.get(i).getEighth());
                    lotteryCompany = findLotteryCompanyOfCode(list.get(i).getCode());
                    lotterys.setLink(lotterys.getLink() + "+" + list.get(i).getCode().toLowerCase() + "-xo-so-" + lotteryCompany.getCompanyLink() + ".html");
                }
            }

        }

        return lotterys;
    }

    protected LotteryCompany findLotteryCompanyOfCode(String code) {
        LotteryCompany lotteryCompany = null;
        if (XSLive.listCompany == null || XSLive.listCompany.isEmpty()) {
            return lotteryCompany;
        }
        for (int i = 0; i < XSLive.listCompany.size(); i++) {
            if (code.equalsIgnoreCase(XSLive.listCompany.get(i).getCode())) {
                lotteryCompany = XSLive.listCompany.get(i);
            }
        }

        return lotteryCompany;
    }

    protected List<String> findDuoi(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (listDuoi == null) {
                    listDuoi = StringPro.filterDauOrDuoi(list.get(i), true);
                } else {
                    listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), true);
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    protected List<String> findDau(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (listDuoi == null) {
                    listDuoi = StringPro.filterDauOrDuoi(list.get(i), false);
                } else {
                    listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), false);
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    private boolean checkToUpcase(String str) {
        boolean result = false;
        String[] arr = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M"};
        for (int i = 0; i < arr.length; i++) {
            if (str.contains(arr[i])) {
                result = true;
                break;
            }
        }

        return result;
    }

}
