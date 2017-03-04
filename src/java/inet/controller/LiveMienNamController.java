/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.Lottery;
import inet.bean.Lotterys;
import inet.request.ThongKeRequest;
import inet.util.DateProc;
import inet.util.Today;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Duynv
 */
public class LiveMienNamController extends BaseController {

    private static HashMap<String, List<Lottery>> hListLottery = new HashMap<String, List<Lottery>>();
    private static String sDDMMYYYY = null;

    public LiveMienNamController() {
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

        List<Lottery> lstLotterys = null;
        List<Lottery> lstLotterys2 = null;
        List<String> listDuoi = null;
        List<String> listDuoi2 = null;
        Lotterys lotterys = null;
        Lotterys lotterys2 = null;
        String region = "MN";
        String key = null;
        String d = request.getParameter("d");
        String ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
        Today t = new Today();
        ThongKeRequest thongKeRequest = new ThongKeRequest();
        int lengthProvince = 0;
        String province = null;

        if ((t.getHour() == 16 && t.getMinute() >= 45) || t.getHour() > 16) {
            ddmmyyyy = today;
        }
        if (d != null) {
            ddmmyyyy = d;
        }
        key = region + ddmmyyyy;
        if (hListLottery.containsKey(key)) {
            lstLotterys = hListLottery.get(key);
        } else {
            lstLotterys = thongKeRequest.parserThongKeTrucTiepMienTrung(ddmmyyyy, region);
            if (lstLotterys != null) {
                hListLottery.put(key, lstLotterys);
            }
        }
        if (lstLotterys != null) {
            lotterys = findLotterys(lstLotterys, ddmmyyyy);
            listDuoi = findDuoi(lstLotterys);
        }
        if (province != null) {
            if (province.contains("+")) {
                String[] a = province.split("\\+");
                lengthProvince = a.length;
            }
        }

        String title = "XSMN - Ket qua xo so mien nam - Trực tiếp kết quả SXMN hôm nay";
        String des = "XSMN - XO SO MIEN NAM - SXMN - KQXSMN - Tường thuật trực tiếp kết quả xổ số miền Nam nhanh nhất tại trường quay - Cập nhật bảng kết quả mới nhất hôm nay.";
        String keyword = "xsmn, sxmn, xs mien nam, sx mien nam, xo so mien nam, so xo mien nam, kqxsmn, ket qua xsmn, xsmn hom nay";
        if (d != null) {
            title = "XSMN ngày " + d + " - Xem nhanh kết quả xổ số miền nam ngày " + d + " chính xác";
            des = "XSMN ngày " + d + " - Bảng kết quả xổ số ngày" + d + " chính xác - Trực tiếp kết quả xổ số ngày " + d + " nhanh nhất";
            keyword = "xsmn thu 2, xsmn thu 3, xsmn thu 4, xsmn thu 5, xsmn thu 6, xsmn thu 7, xsmn chu nhat";
        }

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("lotterys", lotterys);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("sDDMMYYYY", sDDMMYYYY);
        mod.addObject("lengthProvince", lengthProvince);
        if ("pc".equals(strMobile)) {
            mod.setViewName("/live_mn");
        } else {
            mod.setViewName("mobile/live_mn");
        }
        return mod;
    }

}
