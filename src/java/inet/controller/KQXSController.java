/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.LotteryRequest;
import inet.request.ThongKeRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.StringPro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class KQXSController extends BaseController {

    private HashMap<String, Object> hMap = new HashMap<String, Object>();
    private static HashMap<String, List<Lottery>> hListLottery = new HashMap<String, List<Lottery>>();
    private static String sDDMMYYYY = null;

    public KQXSController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        String strNow = DateProc.getDateString(DateProc.createTimestamp());

        if (!strNow.equals(sDDMMYYYY)) {
            hMap.clear();
            sDDMMYYYY = strNow;
        }
    }

    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String d = request.getParameter("d");
        LotteryRequest lotteryRequest = new LotteryRequest();
        ThongKeRequest thongKeRequest = new ThongKeRequest();

        List<Lottery> listLottery = null;
        List<List<String>> listLotteryDauMB = null;
        //
        List<Lottery> lstLotterysMT = null;
        List<String> listDuoiMT = null;
        Lotterys lotterysMT = null;
        //
        List<Lottery> lstLotterysMN = null;
        List<String> listDuoiMN = null;
        Lotterys lotterysMN = null;
        //

        String keyMB = "lotteryMB_" + d + sDDMMYYYY;
        String keyMT = "lotteryMT_" + d + sDDMMYYYY;
        String keyMN = "lotteryMN_" + d + sDDMMYYYY;

        if (hMap.containsKey(keyMB)) {
            listLottery = (List<Lottery>) hMap.get(keyMB);
            listLotteryDauMB = (List<List<String>>) hMap.get(keyMB + "_dau");
        } else {
            listLottery = lotteryRequest.parserLotteryResult("XSTD", d, d);
            if (listLottery != null && !listLottery.isEmpty()) {
                hMap.put(keyMB, listLottery);
                List<String> listDauMB = null;
                for (int i = 0; i < listLottery.size(); i++) {
                    listDauMB = StringPro.filterDauOrDuoi(listLottery.get(i), true);
                    if (listLotteryDauMB == null) {
                        listLotteryDauMB = new ArrayList<List<String>>();
                    }
                    listLotteryDauMB.add(listDauMB);
                }

                if (listLotteryDauMB != null && !listLotteryDauMB.isEmpty()) {
                    hMap.put(keyMB + "_dau", listLotteryDauMB);
                }
            }
        }
        //mien trung
        if (hListLottery.containsKey(keyMT)) {
            lstLotterysMT = hListLottery.get(keyMT);
        } else {
            lstLotterysMT = thongKeRequest.parserThongKeTrucTiepMienTrung(d, "MT");
            if (lstLotterysMT != null) {
                hListLottery.put(keyMT, lstLotterysMT);
            }
        }
        if (lstLotterysMT != null) {
            lotterysMT = findLotterys(lstLotterysMT, d);
            listDuoiMT = findDuoi(lstLotterysMT);
        }
        //mien nam
        if (hListLottery.containsKey(keyMN)) {
            lstLotterysMN = hListLottery.get(keyMN);
        } else {
            lstLotterysMN = thongKeRequest.parserThongKeTrucTiepMienTrung(d, "MN");
            if (lstLotterysMN != null) {
                hListLottery.put(keyMN, lstLotterysMN);
            }
        }
        if (lstLotterysMN != null) {
            lotterysMN = findLotterys(lstLotterysMN, d);
            listDuoiMN = findDuoi(lstLotterysMN);
        }

        //the seo  
        String strThu = DatePro.getDateOfWeekDDMMYYYY(d.replace("-", "/"));
        String thu = null;
        thu = "thứ " + strThu;
        if (strThu.equals("8")) {
            thu = "chủ nhật";
        }
        String title = "XSMB " + d + " - SXMB " + thu + " - Kết quả xổ số miền Bắc " + d;
        String des = "XSMB " + d + " - Xem trực tiếp kết quả xổ số miền Bắc " + d + " - Bảng kết quả sxmb " + d + " chính xác";
        String keyword = "xsmb thu 2, xsmb thu 3, xsmb thu 4, xsmb thu 5, xsmb thu 6, xsmb thu 7, xsmb chu nhat";
        String strH1 = "Xổ số trực tuyến - Thống kê xổ số ";

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("strH1", strH1);

        mod.addObject("listLottery", listLottery);
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        mod.addObject("d", d);
        mod.addObject("today", d);
        //
        mod.addObject("lotterysMT", lotterysMT);
        mod.addObject("listDuoiMT", listDuoiMT);
        //
        mod.addObject("lotterysMN", lotterysMN);
        mod.addObject("listDuoiMN", listDuoiMN);

        if ("pc".equals(strMobile)) {
            mod.setViewName("/kqxs");
        } else {
            mod.setViewName("/mobile/kqxs");
        }

        return mod;
    }

}
