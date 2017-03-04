/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.CapSo;
import inet.bean.Lottery;
import inet.bean.PhantichLoto;
import inet.request.ThongKeRequest;
import inet.util.StringPro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Duynv
 */
public class CauLoToCapSoController extends BaseController {

    private static final String sMaxCau = "30";
    private static final String sLon = "0";
    private static final String sNhay = "1";
    private static String sCau = null;
    private static String sEdate = null;
    private static String sDDMMYYYY = null;
    private static int ketqua = 0;
    private static List<PhantichLoto> listPhanTichLoto = null;
    private static List<Lottery> listLottery = null;
    private static List<List<Lottery>> list2Lottery = null;
    private static HashMap<String, Object> hListPhanTichLoto = null;
    private static HashMap<String, List<List<Lottery>>> hList2Lottery = null;
    private static List<CapSo> listCapSo = null;
    private static HashMap<String, CapSo> hCapso = null;
    private static CapSo sCapSo = null;
    private String so1 = null;
    private String so2 = null;

    public CauLoToCapSoController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if (hListPhanTichLoto == null) {
            hListPhanTichLoto = new HashMap<String, Object>();
        }
        if (hList2Lottery == null) {
            hList2Lottery = new HashMap<String, List<List<Lottery>>>();
        }
        if (hCapso == null) {
            hCapso = new HashMap<String, CapSo>();
        }
        if (sDDMMYYYY == null || !today.equals(sDDMMYYYY)) {
            hListPhanTichLoto.clear();
            hList2Lottery.clear();
            hCapso.clear();
            sDDMMYYYY = today;
            listLottery = null;
            listCapSo = null;
        }
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String cau = request.getParameter("cau");
        if (cau == null || "".equals(cau)) {
            cau = "4";
        }

        String capso = request.getParameter("capso");
        String vt1 = request.getParameter("vt1");
        String vt2 = request.getParameter("vt2");
        //duy them
        String capsoInput = request.getParameter("capsosoicau");
        //end duy them

        String edate = request.getParameter("edate");
        if (edate == null || "".equals(edate)) {
            edate = today;
        }

        String tinh = request.getParameter("tinh");
        if (tinh == null || "".equals(tinh)) {
            tinh = "XSTD";
        }

        String key = tinh + "_" + cau + "_" + edate.replaceAll("/", "");

        if (hListPhanTichLoto.containsKey(key)) {
            listPhanTichLoto = (List<PhantichLoto>) hListPhanTichLoto.get(key);
            listLottery = (List<Lottery>) hListPhanTichLoto.get("lottery_" + key);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            listPhanTichLoto = thongKeRequest.parserPhanTichLoTo(tinh, edate, sLon, sNhay, cau, sMaxCau);
            if (listPhanTichLoto != null && !listPhanTichLoto.isEmpty()) {
                hListPhanTichLoto.put(key, listPhanTichLoto);
                listLottery = thongKeRequest.parserLotteryPhanTichLoTo(tinh);
                if (listLottery != null && !listLottery.isEmpty()) {
                    hListPhanTichLoto.put("lottery_" + key, listLottery);
                }
            }
        }

        //cau loto        
        if (capso != null && !"".equals(capso)) {
            key = capso + "_" + key;
            String capdao = capso.substring(1, 2) + capso.substring(0, 1);
            mod.addObject("capdao", capdao);
            mod.addObject("vt1", vt1);
            mod.addObject("vt2", vt2);
            if (listPhanTichLoto != null && !listPhanTichLoto.isEmpty()) {
                if (hList2Lottery.containsKey(key)) {
                    list2Lottery = hList2Lottery.get(key);
                } else {
                    list2Lottery = cauLoto(capso, cau);
                    if (list2Lottery != null) {
                        hList2Lottery.put(key, list2Lottery);
                    }
                }

                if (hCapso.containsKey(capso)) {
                    sCapSo = hCapso.get(capso);
                } else {
                    if (listCapSo == null) {
                        ThongKeRequest thongKeRequest = new ThongKeRequest();
                        listCapSo = thongKeRequest.parserThongKeLotoCapSo("XSTD", "5", "");
                    }

                    if (listCapSo != null) {
                        for (int i = 0; i < listCapSo.size(); i++) {
                            sCapSo = listCapSo.get(i);
                            if (capso.equals(sCapSo.getCapso())) {
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            list2Lottery = null;
        }
        if (listPhanTichLoto != null) {
            ketqua = 0;
            for (int i = 0; i < listPhanTichLoto.size(); i++) {
                if (listPhanTichLoto.get(i).getDodai() >= Integer.parseInt(cau)) {
                    ketqua++;
                }

            }

        }

        //the seo
        String title = "Soi cầu tìm ra cặp số loto miền bắc cực chuẩn";
        String des = "Soi cầu tìm cặp số loto xổ số miền bắc cực chuẩn - Đánh giá nhận định đưa ra cặp số loto có tỷ lệ khả năng về cao nhất, chính xác trong ngày.";
        String keyword = "soi cau mien bac, soi cau xo so mien bac, soi cau xsmb, soi cau mb, soi cau, cau mb, cau mien bac, cau xsmb, soi cau lo mien bac";
        String strH1 = "Cầu loto";

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("strH1", strH1);

        mod.addObject("sCapSo", sCapSo);
        mod.addObject("capso", capso);
        mod.addObject("ketqua", ketqua);
        mod.addObject("listPhanTichLoto", listPhanTichLoto);
        mod.addObject("list2Lottery", list2Lottery);
        mod.addObject("cau", cau);
        mod.addObject("edate", edate);
        mod.addObject("tinh", tinh);
        mod.addObject("capsoInput", capsoInput);

        if ("pc".equals(strMobile)) {
            mod.setViewName("/caulotocapso");
        } else {
            mod.setViewName("/mobile/phantichloto");
        }

        return mod;
    }
    //dùng để tô mầu vị trí
    private List<List<Lottery>> cauLoto(String capso, String cau) {
        List<List<Lottery>> list = null;
        List<Lottery> arrLottery = null;
        List<String> listCapso = null;
        if (listPhanTichLoto == null || listPhanTichLoto.isEmpty()) {
            return null;
        }
        if (listLottery == null || listLottery.isEmpty()) {
            return null;
        }

        int iCau = Integer.parseInt(cau);
        Lottery lottery = null;
        Lottery lottery1 = null;
        PhantichLoto phantichLoto = null;
        int start = 0;
        String result = null;
        String str = null;
        StringPro sp = new StringPro();
        for (int j = 0; j < listPhanTichLoto.size(); j++) {
            so1 = null;
            so2 = null;
            phantichLoto = listPhanTichLoto.get(j);
            if (capso.equals(phantichLoto.getCapso())) {
                System.out.println("capso===+++++++====================================>>>>>>>>" + capso);
                System.out.println("vi tri1========>>>>>>" + phantichLoto.getVitri1());
                System.out.println("vi tri2========>>>>>>" + phantichLoto.getVitri2());

                if (arrLottery == null) {
                    arrLottery = new ArrayList<Lottery>();
                }
                if (listCapso == null) {
                    listCapso = new ArrayList<String>();
                }
                for (int i = 0; i <= iCau; i++) {
                    lottery1 = new Lottery();
                    start = 0;
                    lottery = listLottery.get(i);
                    System.out.println("code============>>>>>>" + lottery.getCode());
                    lottery1.setOpenDate(lottery.getOpenDate());
                    // giai dac biet

                    str = lottery.getSpecial();
                    if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                        result = checkAddString(phantichLoto.getVitri1(), start, 5, str, 1);
                    } else {
                        result = checkAddString(phantichLoto.getVitri1(), start, 6, str, 1);
                    }

                    if (result != null) {
                        lottery1.setSpecial(result);
                    } else {
                        lottery1.setSpecial(lottery.getSpecial());
                    }

                    str = lottery1.getSpecial();
                    if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                        result = checkAddString(phantichLoto.getVitri2(), start, 5, str, 2);
                    } else {
                        result = checkAddString(phantichLoto.getVitri2(), start, 6, str, 2);
                    }

                    if (result != null) {
                        lottery1.setSpecial(result);
                    }

                    if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                        start = start + 5;
                    } else {
                        start = start + 6;
                    }

                    // giai nhat                
                    str = lottery.getFirst();
                    result = checkAddString(phantichLoto.getVitri1(), start, start + 5, str, 1);
                    if (result != null) {
                        lottery1.setFirst(result);
                    } else {
                        lottery1.setFirst(lottery.getFirst());
                    }

                    str = lottery.getFirst();
                    result = checkAddString(phantichLoto.getVitri2(), start, start + 5, str, 2);
                    if (result != null) {
                        lottery1.setFirst(result);
                    }

                    start = start + 5;

                    //giai nhi
                    int next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri1(), start, start + 5, str, 1);
                        if (result != null) {
                            lottery1.setSecond(lottery.getSecond().replace(str, result));
                        } else if (lottery1.getSecond() == null) {
                            lottery1.setSecond(lottery.getSecond());
                        }
                        start = start + 5;
                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri2(), start, start + 5, str, 2);
                        if (result != null) {
                            lottery1.setSecond(lottery1.getSecond().replace(str, result));
                        }
                        start = start + 5;
                    }

                    //giai ba
                    next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri1(), start, start + 5, str, 1);
                        if (result != null) {
                            lottery1.setThird(lottery.getThird().replace(str, result));
                        } else if (lottery1.getThird() == null) {
                            lottery1.setThird(lottery.getThird());
                        }
                        start = start + 5;
                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getThird(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri2(), start, start + 5, str, 2);
                        if (result != null) {
                            lottery1.setThird(lottery1.getThird().replace(str, result));
                        }
                        start = start + 5;
                    }

                    //giai tu            
                    next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 4, str, 1);
                        } else {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 5, str, 1);
                        }

                        if (result != null) {
                            lottery1.setFourth(lottery.getFourth().replace(str, result));
                        } else if (lottery1.getFourth() == null) {
                            lottery1.setFourth(lottery.getFourth());
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 4;
                        } else {
                            start = start + 5;
                        }

                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 4, str, 2);
                        } else {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 5, str, 2);
                        }

                        if (result != null) {
                            lottery1.setFourth(lottery1.getFourth().replace(str, result));
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 4;
                        } else {
                            start = start + 5;
                        }

                    }

                    //giai nam   
                    next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri1(), start, start + 4, str, 1);
                        if (result != null) {
                            lottery1.setFifth(lottery.getFifth().replace(str, result));
                        } else if (lottery1.getFifth() == null) {
                            lottery1.setFifth(lottery.getFifth());
                        }
                        start = start + 4;
                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getFifth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        result = checkAddString(phantichLoto.getVitri2(), start, start + 4, str, 2);
                        if (result != null) {
                            lottery1.setFifth(lottery1.getFifth().replace(str, result));
                        }
                        start = start + 4;
                    }

                    //giai sau     
                    next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 3, str, 1);
                        } else {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 4, str, 1);
                        }

                        if (result != null) {
                            lottery1.setSixth(lottery.getSixth().replace(str, result));
                        } else if (lottery1.getSixth() == null) {
                            lottery1.setSixth(lottery.getSixth());
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 3;
                        } else {
                            start = start + 4;
                        }

                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 3, str, 2);
                        } else {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 4, str, 2);
                        }

                        if (result != null) {
                            lottery1.setSixth(lottery1.getSixth().replace(str, result));
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 3;
                        } else {
                            start = start + 4;
                        }

                    }

                    //giai bay            
                    next = start;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 2, str, 1);
                        } else {
                            result = checkAddString(phantichLoto.getVitri1(), start, start + 3, str, 1);
                        }

                        if (result != null) {
                            lottery1.setSeventh(lottery.getSeventh().replace(str, result));
                        } else if (lottery1.getSeventh() == null) {
                            lottery1.setSeventh(lottery.getSeventh());
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 2;
                        } else {
                            start = start + 3;
                        }

                    }

                    start = next;
                    for (StringTokenizer stringTokenizer = new StringTokenizer(lottery1.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                        str = stringTokenizer.nextToken();
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 2, str, 2);
                        } else {
                            result = checkAddString(phantichLoto.getVitri2(), start, start + 3, str, 2);
                        }

                        if (result != null) {
                            lottery1.setSeventh(lottery1.getSeventh().replace(str, result));
                        }
                        if ("XSTD".equalsIgnoreCase(lottery.getCode())) {
                            start = start + 2;
                        } else {
                            start = start + 3;
                        }

                    }

                    if (lottery.getEighth() != null && !"".equals(lottery.getEighth())) {
                        next = start;

                        str = lottery.getEighth();
                        result = checkAddString(phantichLoto.getVitri1(), start, start + 2, str, 1);
                        if (result != null) {
                            lottery1.setEighth(result);
                        } else {
                            lottery1.setEighth(lottery.getEighth());
                        }

                        start = next;
                        str = lottery1.getEighth();
                        result = checkAddString(phantichLoto.getVitri2(), start, start + 2, str, 2);
                        if (result != null) {
                            lottery1.setEighth(result);
                        }
                    }

                    listCapso.add(so1 + so2);
                    arrLottery.add(lottery1);
                }

                String capdao = "";
                for (int l = 0; l < arrLottery.size() - 1; l++) {
                    capdao = listCapso.get(l + 1);
                    if (capso.length() == 2) {
                        lottery = sp.replaceResultLottery(arrLottery.get(l), capdao);
                        if (lottery != null) {
                            arrLottery.set(l, lottery);
                        } else {
                            capdao = capdao.substring(1, 2) + capdao.substring(0, 1);
                            lottery = sp.replaceResultLottery(arrLottery.get(l), capdao);
                            if (lottery != null) {
                                arrLottery.set(l, lottery);
                            } else {
                                arrLottery.set(l, arrLottery.get(l));
                            }
                        }
                    }
                }

                if (list == null) {
                    list = new ArrayList<List<Lottery>>();
                }
                list.add(arrLottery);
            }
        }

        return list;

    }
    
    private String checkAddString(int vitri, int start, int end, String str, int iVitri) {
        String temp1 = "<span class=\"PTLT_text_DD\">";
        String result = null;
        if (-1 < str.indexOf("<")) {
            end = end - temp1.length();
        }
        if (vitri >= start && vitri < end) {
            result = addString(str, vitri - start, 0, iVitri);
        }

        return result;
    }

    private String addString(String str, int i, int s, int iVitri) {
        String temp1 = "<span class=\"PTLT_text_DD\">";
        String temp2 = "</span>";
        if (-1 < str.indexOf("<") && str.indexOf("<") <= i) {
            i = i + temp1.length();
        }
        String start = "";
        String center = "";
        String end = "";
        String result = str;

        for (int j = 0; j < str.length(); j++) {
            if (j == i) {
                if (j > 0) {
                    start = str.substring(0, j);
                }
                center = str.substring(j, j + 1);
                if (iVitri == 1) {
                    so1 = center;
                } else {
                    so2 = center;
                }
                if (j < str.length() - 1) {
                    end = str.substring(j + 1, str.length());
                }
                result = start + temp1 + center + temp2 + end;
            }
        }

        return result;
    }

}
