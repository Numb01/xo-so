/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.CapSo;
import inet.bean.Lottery;
import inet.request.LotteryRequest;
import inet.request.ThongKeRequest;
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
 * @author hanhlm
 */
public class IndexController extends BaseController {

    private HashMap<String, Object> hMap = new HashMap<String, Object>();
    private static String sDDMMYYYY = null;

    public IndexController() {
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

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(today), -1);
        Today t = new Today();
        if ((t.getHour() == 18 && t.getMinute() >= 45) || t.getHour() > 18) {
            ddmmyyyy = today;
        }

        String key_bangdacbiet = "bangdacbiet_" + ddmmyyyy;
        //bang dac biet
        List<CapSo> listBangDacBiet = null;
        if (hMap.containsKey(key_bangdacbiet)) {
            listBangDacBiet = (List<CapSo>) hMap.get(key_bangdacbiet);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            listBangDacBiet = thongKeRequest.parserThongKeDacBietGiaiSo("XSTD", "2", "ngay_ve_gan_nhat");
            if (listBangDacBiet != null && !listBangDacBiet.isEmpty()) {
                hMap.put(key_bangdacbiet, listBangDacBiet);
            }
        }

        List<Lottery> listLottery = null;
        List<List<String>> listLotteryDauMB = null;
        String key_lottery = "lottery_" + ddmmyyyy;
        if (hMap.containsKey(key_lottery)) {
            listLottery = (List<Lottery>) hMap.get(key_lottery);
            listLotteryDauMB = (List<List<String>>) hMap.get(key_lottery + "_dau");
        } else {
            String sDate = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), -7);
            LotteryRequest lotteryRequest = new LotteryRequest();
            listLottery = lotteryRequest.parserLotteryResult("XSTD", sDate, ddmmyyyy);
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

        List<CapSo> listCapso = null;
        String key_capso = "capso_" + ddmmyyyy;
        if (hMap.containsKey(key_capso)) {
            listCapso = (List<CapSo>) hMap.get(key_capso);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            listCapso = thongKeRequest.parserThongKeLotoCapSo("XSTD", "300", "ngay_chua_ve%gan_max");
            if (listCapso != null) {
                hMap.put(key_capso, listCapso);
            }
        }

        List<CapSo> listCapsoDB = null;
        String key_capsodb = "capso_dacbiet_" + ddmmyyyy;
        if (hMap.containsKey(key_capsodb)) {
            listCapsoDB = (List<CapSo>) hMap.get(key_capsodb);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            listCapsoDB = thongKeRequest.parserThongKeDacBietCapSo("XSTD", "50", "ngay_chua_ve");
            if (listCapsoDB != null) {
                hMap.put(key_capsodb, listCapsoDB);
            }
        }

        List<CapSo> listCapsoCham = null;
        String key_cham = "dacbiet_cham_" + ddmmyyyy;
        if (hMap.containsKey(key_cham)) {
            listCapsoCham = (List<CapSo>) hMap.get(key_cham);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            listCapsoCham = thongKeRequest.parserThongKeDacBietCapSoHangChuc("XSTD", "300", "ngay_chua_ve");
            if (listCapsoCham != null) {
                hMap.put(key_cham, listCapsoCham);
            }
        }
        String title = "SXMB - XSTD- XSHN - KQSXMB- XSMB - Kết quả xổ số Miền Bắc";
        String des = "SXMB - XSTD - XSHN - KQSXMB - SXHN - XSMB. Tường thuật ket qua xo so mien bac nhanh nhất,cập nhật nhanh so xo mien bac hôm nay ngay tại trường quay.";
        String keyword = "sxmb, xstd, kqxsmb, kqxsmb hom nay, xsmb hom nay";
        String strH1 = title;
        if (canonical != null && canonical.indexOf("xsmb") > 0) {
            title = "XSMB trực tiếp - Trực tiếp kết quả xổ số miền bắc hôm nay";
            des = "XSMB TRUC TIEP - Trực tiếp kết quả xổ số Miền Bắc hôm nay ngay tại trường quay. Xem kết quả xổ số miền Bắc trực tiếp nhanh kinh khủng";
            keyword = "truc tiep xsmb, truc tiep xo so mien bac, xsmb truc tiep, truc tiep sxmb, sxmb truc tiep, truc tiep xo so, xstd truc tiếp";
        }

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("strH1", strH1);
        mod.addObject("listBangDacBiet", listBangDacBiet);
        mod.addObject("listLottery", listLottery);
        mod.addObject("listLotteryDauMB", listLotteryDauMB);
        mod.addObject("listCapso", listCapso);
        mod.addObject("listCapsoDB", listCapsoDB);
        mod.addObject("listCapsoCham", listCapsoCham);
        mod.addObject("sDDMMYYYY", sDDMMYYYY);
        mod.addObject("ddmmyyyy", ddmmyyyy);

        if ("pc".equals(strMobile)) {
            if (canonical != null && canonical.indexOf("xsmb") > 0) {
                mod.setViewName("/live");
            } else {
                mod.setViewName("/index");
            }
        } else {
            if (canonical != null && canonical.indexOf("xsmb") > 0) {
                mod.setViewName("/mobile/live");
            } else {
                mod.setViewName("/mobile/index");
            }
        }
        return mod;
    }

}
