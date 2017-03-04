/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.request.LotteryRequest;
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
public class KQXSTHUController extends BaseController {

    private HashMap<String, Object> hMap = new HashMap<String, Object>();
    private static String sDDMMYYYY = null;

    public KQXSTHUController() {
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

        String dayOfWeek = request.getParameter("d");

        List<Lottery> listLottery = null;
        List<List<String>> listLotteryDauMB = null;
        String key_lottery = "lottery_" + dayOfWeek + sDDMMYYYY;
        if (hMap.containsKey(key_lottery)) {
            listLottery = (List<Lottery>) hMap.get(key_lottery);
            listLotteryDauMB = (List<List<String>>) hMap.get(key_lottery + "_dau");
        } else {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listLottery = lotteryRequest.parserLotteryResultDayOfWeek("XSTD", dayOfWeek, "5");
            if (listLottery != null && !listLottery.isEmpty()) {
                hMap.put(key_lottery, listLottery);
                List<String> listDauMB = null;
                for (int i = 0; i < listLottery.size(); i++) {
                    listDauMB = StringPro.filterDauOrDuoi(listLottery.get(i), true);
                    if (listLotteryDauMB == null) {
                        listLotteryDauMB = new ArrayList<List<String>>();
                    }
                    listLotteryDauMB.add(listDauMB);
                }

                if (listLotteryDauMB != null && !listLotteryDauMB.isEmpty()) {
                    hMap.put(key_lottery + "_dau", listLotteryDauMB);
                }
            }
        }

        //the seo
        String strThu = "Thứ " + dayOfWeek;
        String str = null;
        String str2 = null;
        if (dayOfWeek.equals("2")) {
            str = "hai";
            str2 = "thứ 2";
        }
        if (dayOfWeek.equals("3")) {
            str = "ba";
            str2 = "thứ 3";
        }
        if (dayOfWeek.equals("4")) {
            str = "tu";
            str2 = "thứ 4";
        }
        if (dayOfWeek.equals("5")) {
            str = "nam";
            str2 = "thứ 5";
        }
        if (dayOfWeek.equals("6")) {
            str = "sau";
            str2 = "thứ 6";
        }
        if (dayOfWeek.equals("7")) {
            str = "bay";
            str2 = "thứ 7";
        }
        if (dayOfWeek.equals("cn")) {
            str = "cn";
            str2 = "chủ nhật";
        }
        if (dayOfWeek.equals("cn")) {
            strThu = "Chủ nhật";
        }
        String title = "SXMB " + str2 + " - XSMB " + str2 + " - Trực tiếp kết quả xổ số miền Bắc hôm nay";
        String des = "SXMB " + str2 + " - XSMB " + strThu + " - Trực tiếp kết quả xổ số miền bắc " + str2 + " nhanh, chính xác được nhất cập nhật liên tục";
        String keyword = "truc tiep xsmb, truc tiep xo so mien bac, xsmb truc tiep, truc tiep sxmb, sxmb truc tiep, truc tiep xo so, xstd truc tiếp";
        String strH1 = "Xổ số trực tuyến - Thống kê xổ số ";

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("strH1", strH1);

        mod.addObject("listLottery", listLottery);
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        mod.addObject("dayOfWeek", dayOfWeek);

        if ("pc".equals(strMobile)) {
            mod.setViewName("/kqxs_thu");
        } else {
            mod.setViewName("/mobile/kqxs_thu");
        }

        return mod;
    }

}
