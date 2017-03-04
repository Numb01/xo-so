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
public class LiveMienTrungController extends BaseController {

    private static HashMap<String, List<Lottery>> hListLottery = new HashMap<String, List<Lottery>>();
    private static String sDDMMYYYY = null;

    public LiveMienTrungController() {
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
        List<String> listDuoi = null;
        Lotterys lotterys = null;
        String region = "MT";
        String key = null;
        int lengthProvince = 0;
        String province = null;

        String d = request.getParameter("d");

        String ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
        Today t = new Today();
        ThongKeRequest thongKeRequest = new ThongKeRequest();
        if ((t.getHour() == 17 && t.getMinute() >= 45) || t.getHour() > 17) {
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

        if (lotterys != null) {
            province = lotterys.getProvince();
        }
        if (province != null) {
            if (province.contains("+")) {
                String[] a = province.split("\\+");
                lengthProvince = a.length;
            }
        }
        String title = "XSMT - Ket qua xo so mien trung - Trực tiếp kết quả XSMT hôm nay";
        String des = "XSMT - XO SO MIEN TRUNG - SXMT - KQXSMT - XSMT truc tiep - Tường thuật trực tiếp kết quả xổ số miền trung nhanh nhất tại trường quay, cập nhật liên tục kết quả 24/24h.";
        String keyword = "xsmt, xo so mien trung, sxmt, xs mien trung, ket qua xo so mien trung, kqxsmt, ket qua xsmt, so xo mien trung, xo so mt";
        if (d != null) {
            title = "XSMT ngày " + d + " - Xem nhanh kết quả xổ số miền trung ngày " + d + " chính xác";
            des = "XSMT ngày " + d + " - Bảng kết quả xổ số ngày" + d + " chính xác - Trực tiếp kết quả xổ số ngày " + d + " nhanh nhất";
            keyword = "xsmt thu 2, xsmt thu 3, xsmt thu 4, xsmt thu 5, xsmt thu 6, xsmt thu 7, xsmt chu nhat";
        }

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("lotterys", lotterys);
        mod.addObject("listDuoi", listDuoi);
        mod.addObject("sDDMMYYYY", sDDMMYYYY);
        mod.addObject("lengthProvince", lengthProvince);
        if ("pc".equals(strMobile)) {
            mod.setViewName("/live_mt");
        } else {
            mod.setViewName("mobile/live_mt");
        }

        return mod;
    }

}
