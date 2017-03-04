/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.request;

import com.google.gson.Gson;
import inet.bean.CapSo;
import inet.bean.CapSoJson;
import inet.bean.CauTamGiac;
import inet.bean.CauTamGiacJson;
import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.LotteryJson;
import inet.bean.LotteryResult;
import inet.bean.LotterySpecial;
import inet.bean.LotterySpecialJson;
import inet.bean.Lotterys;
import inet.bean.Mobat;
import inet.bean.MobatJson;
import inet.bean.PhantichLoto;
import inet.bean.PhantichLotoJson;
import inet.bean.ResultCauTamGiac;
import inet.bean.TanSuat;
import inet.bean.TanSuatJson;
import inet.listener.XSLive;
import inet.util.Contant;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.HttpURLRequest;
import inet.util.StringPro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hanhlm
 */
public class ThongKeRequest {

    public List<PhantichLoto> fillterPhanTichLoto(List<PhantichLoto> list, int cau) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        List<PhantichLoto> listPhanTichLoto = null;
        PhantichLoto phantichLoto = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDodai() >= cau) {
                if (listPhanTichLoto == null) {
                    listPhanTichLoto = new ArrayList<PhantichLoto>();
                }
                listPhanTichLoto.add(list.get(i));
            }
        }

        for (int i = 0; i < listPhanTichLoto.size() - 1; i++) {
            for (int j = i + 1; j < listPhanTichLoto.size(); j++) {
                phantichLoto = listPhanTichLoto.get(i);
                if (listPhanTichLoto.get(i).getDodai() > listPhanTichLoto.get(j).getDodai()) {
                    listPhanTichLoto.set(i, listPhanTichLoto.get(j));
                    listPhanTichLoto.set(j, phantichLoto);
                }
            }
        }

        return listPhanTichLoto;
    }

    public List<CapSo> orderLoto(List<CapSo> list, String order) {
        //if(order==null||"".equals(order)){return list;}
        if (list == null || list.isEmpty()) {
            return list;
        }
        CapSo capSo1 = null;
        CapSo capSo2 = null;
        int so1 = 0;
        int so2 = 0;
        String[] arrStr = null;
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                capSo1 = list.get(i);
                capSo2 = list.get(j);
                if ("gan_max".equals(order)) {
                    if (capSo1.getGancucdai() < capSo2.getGancucdai()) {
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                } else if ("ngay_chua_ve%gan_max".equals(order)) {
                    float tile1 = (float) capSo1.getSongaychuave() / capSo1.getGancucdai();
                    float tile2 = (float) capSo2.getSongaychuave() / capSo2.getGancucdai();

                    //System.out.println("tile1|tile2==>>"+tile1+"|"+tile2);
                    if (tile1 < tile2) {
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                } else if ("ngay_chua_ve".equals(order)) {
                    if (capSo1.getSongaychuave() < capSo2.getSongaychuave()) {
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                } else if ("lan_xuat_hien".equals(order)) {
                    if (capSo1.getSolanxuathien() < capSo2.getSolanxuathien()) {
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                } else if ("ngay_ve_gan_nhat".equals(order)) {
                    if (capSo1.getDdmmyyyy() == null || "".equals(capSo1.getDdmmyyyy())) {
                        break;
                    } else {
                        arrStr = capSo1.getDdmmyyyy().split("/");
                        so1 = Integer.parseInt(arrStr[2] + arrStr[1] + arrStr[0]);
                        arrStr = capSo2.getDdmmyyyy().split("/");
                        so2 = Integer.parseInt(arrStr[2] + arrStr[1] + arrStr[0]);

                        if (so2 < so1) {
                            list.set(i, capSo2);
                            list.set(j, capSo1);
                        }
                    }
                } else {
                    if (Integer.parseInt(capSo1.getCapso()) > Integer.parseInt(capSo2.getCapso())) {
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                }
            }
        }

        return list;
    }

    private List<LotterySpecial> subLotterySpecial(List<LotterySpecial> list) {

        if (list == null || list.isEmpty()) {
            return list;
        }
        LotterySpecial lotterySpecial = null;
        for (int i = 0; i < list.size(); i++) {
            lotterySpecial = list.get(i);
            if (!lotterySpecial.getOpenDate().contains("nbsp")) {
                lotterySpecial.setSpecial(lotterySpecial.getSpecial().substring(3, 5));
            }
        }

        return list;
    }

    public List<CapSo> addString(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        CapSo capSo = null;
        CapSo capSo1 = null;
        String special = "";
        String ddmmyyyy = "no";
        int songay = 0;
        List<CapSo> list1 = new ArrayList<CapSo>();
        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            if (!ddmmyyyy.equals("no")) {
                songay = (int) DatePro.getNumberDay(ddmmyyyy, capSo.getDdmmyyyy());
                for (int j = 1; j < songay; j++) {
                    ddmmyyyy = DateProc.TimestampPlusDay2DDMMYYYY(DateProc.String2Timestamp(ddmmyyyy), 1);
                    capSo1 = new CapSo();
                    capSo1.setCapso("");
                    capSo1.setDayOfWeek(DatePro.getDateOfWeekDDMMYYYY(ddmmyyyy).replace("8", "cn"));
                    capSo1.setChanle("no");
                    capSo1.setDdmmyyyy(ddmmyyyy);
                    list1.add(capSo1);
                    //System.out.println("ddmmyyyy ==="+ddmmyyyy+"==="+capSo1.getDayOfWeek());
                }
            }

            special = capSo.getSpecial();
            special = special.substring(0, 3) + "<span class=\"red\">" + special.substring(3, 5) + "</span>";
            capSo.setSpecial(special);
            list1.add(capSo);

            ddmmyyyy = capSo.getDdmmyyyy();
        }

        return list1;
    }

    public List<CapSo> addStringLotterySpecial(List<CapSo> list) {
        if (list == null || list.isEmpty()) {
            return list;
        }

        CapSo capSo = null;
        String special = "";
        StringPro sp = new StringPro();

        String strCapso = "";
        String capdao = "";

        LotteryResult lottery = null;

        for (int i = 0; i < list.size(); i++) {
            capSo = list.get(i);
            special = capSo.getLotteryResult1().getSpecial();
            strCapso = special.substring(3, 5);
            capdao = strCapso.substring(1, 2) + strCapso.substring(0, 1);
            special = special.substring(0, 3) + "<span class=\"PTLT_text_DD\">" + special.substring(3, 5) + "</span>";
            capSo.getLotteryResult1().setSpecial(special);

            special = capSo.getLotteryResult2().getSpecial();
            special = special.substring(0, 3) + "<span class=\"PTLT_text_DD\">" + special.substring(3, 5) + "</span>";
            capSo.getLotteryResult2().setSpecial(special);

            lottery = sp.replaceLotteryResult(capSo.getLotteryResult2(), strCapso);
            if (lottery != null) {
                capSo.setLotteryResult2(lottery);
            } else {
                lottery = sp.replaceLotteryResult(capSo.getLotteryResult2(), capdao);
                if (lottery != null) {
                    capSo.setLotteryResult2(lottery);
                }
            }

            list.set(i, capSo);
        }

        return list;
    }

    //-------------------------------------------------------------------------
    public List<PhantichLoto> parserPhanTichLoTo(String code, String startdate, String lon, String nhay, String cau, String maxcau) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_PHANTICHLOTO;
            url = url.replace("startdate", startdate).replace("lon", lon);
            url = url.replace("nhay", nhay).replace("maxcau", maxcau).replace("cau", cau);
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                int iCau = Integer.parseInt(cau);
                PhantichLotoJson phantichLotoJson = (PhantichLotoJson) gson.fromJson(string, PhantichLotoJson.class);
                return phantichLotoJson.getList();//fillterPhanTichLoto(phantichLotoJson.getList(),iCau);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public List<PhantichLoto> parserPhanTichLoToDB(String code, String startdate, String lon, String nhay, String cau, String maxcau, String isdb) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_PHANTICHLOTODB;
            url = url.replace("startdate", startdate).replace("lon", lon);
            url = url.replace("nhay", nhay).replace("maxcau", maxcau).replace("cau", cau).replace("isdb", isdb);
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                int iCau = Integer.parseInt(cau);
                PhantichLotoJson phantichLotoJson = (PhantichLotoJson) gson.fromJson(string, PhantichLotoJson.class);
                return phantichLotoJson.getList();//fillterPhanTichLoto(phantichLotoJson.getList(),iCau);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public List<PhantichLoto> parserPhanTichLoToThu(String code, String startdate, String lon, String nhay, String cau, String maxcau, String dayOfWeek) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_PHANTICHLOTOTHU;
            url = url.replace("startdate", startdate).replace("lon", lon);
            url = url.replace("nhay", nhay).replace("maxcau", maxcau).replace("cau", cau);
            url = url.replace("dayofweek", dayOfWeek);
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                int iCau = Integer.parseInt(cau);
                PhantichLotoJson phantichLotoJson = (PhantichLotoJson) gson.fromJson(string, PhantichLotoJson.class);
                return phantichLotoJson.getList();//fillterPhanTichLoto(phantichLotoJson.getList(),iCau);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public List<PhantichLoto> parserPhanTichLoToDBThu(String code, String startdate, String lon, String nhay, String cau, String maxcau, String dayOfWeek, String isdb) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_PHAN_TICH_LOTODBTHU;
            url = url.replace("startdate", startdate).replace("lon", lon);
            url = url.replace("nhay", nhay).replace("maxcau", maxcau).replace("cau", cau).replace("isdb", isdb);
            url = url.replace("dayofweek", dayOfWeek);
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                int iCau = Integer.parseInt(cau);
                PhantichLotoJson phantichLotoJson = (PhantichLotoJson) gson.fromJson(string, PhantichLotoJson.class);
                return phantichLotoJson.getList();//fillterPhanTichLoto(phantichLotoJson.getList(),iCau);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public ResultCauTamGiac parserCauTamGiac(String sdate, String cau, String giai, String code) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_CAUTAMGIAC;
            url = url.replace("sdate", sdate).replace("cau", cau);
            url = url.replace("giai", giai).replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                CauTamGiacJson cauTamGiacJson = (CauTamGiacJson) gson.fromJson(string, CauTamGiacJson.class);
                return orderAddNull(cauTamGiacJson.getList());//fillterPhanTichLoto(phantichLotoJson.getList(),iCau);
                //return cauTamGiacJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserCauTamGiac=======>>" + e.toString());
        }
        return null;
    }

    private ResultCauTamGiac addCauTamGiacNull(ResultCauTamGiac list, String dau) {

        if (list == null) {
            return list;
        }

        List<CauTamGiac> listcauTamGiac = list.getResultCauTamGiac();
        if (listcauTamGiac == null || listcauTamGiac.isEmpty()) {
            return list;
        }

        CauTamGiac cauTamGiac = null;

        int m = 0;
        int k = 0;
        for (int i = 0; i < listcauTamGiac.size(); i++) {
            if (listcauTamGiac.get(i).getCaploto().startsWith(dau)) {
                k = Integer.parseInt(listcauTamGiac.get(i).getCaploto().substring(1, 2));
                for (int n = m; n < k; n++) {
                    cauTamGiac = new CauTamGiac();
                    cauTamGiac.setVitri1(-1);
                    cauTamGiac.setVitri2(-1);
                    cauTamGiac.setCaploto(dau + n);
                    listcauTamGiac.add(cauTamGiac);
                }
                m = k;
            }
        }

        if (0 < m && m < 10) {
            for (int n = m + 1; n < 10; n++) {
                cauTamGiac = new CauTamGiac();
                cauTamGiac.setVitri1(-1);
                cauTamGiac.setVitri2(-1);
                cauTamGiac.setCaploto(dau + n);
                listcauTamGiac.add(cauTamGiac);
            }
        } else {
            for (int n = m; n < 10; n++) {
                cauTamGiac = new CauTamGiac();
                cauTamGiac.setVitri1(-1);
                cauTamGiac.setVitri2(-1);
                cauTamGiac.setCaploto(dau + n);
                listcauTamGiac.add(cauTamGiac);
            }
        }

        return list;
    }

    private ResultCauTamGiac orderAddNull(ResultCauTamGiac list) {
        if (list == null) {
            return list;
        }
        list = orderCauTamGiac(list);
        for (int i = 0; i < 10; i++) {
            list = addCauTamGiacNull(list, "" + i);
        }

        list = orderCauTamGiac(list);

        return list;
    }

    private ResultCauTamGiac orderCauTamGiac(ResultCauTamGiac list) {
        CauTamGiac cauTamGiac = null;
        List<CauTamGiac> listcauTamGiac = list.getResultCauTamGiac();
        if (listcauTamGiac == null || listcauTamGiac.isEmpty()) {
            return list;
        }

        int so1 = 0;
        int so2 = 0;
        for (int i = 0; i < listcauTamGiac.size() - 1; i++) {
            for (int j = i + 1; j < listcauTamGiac.size(); j++) {
                so1 = Integer.parseInt(listcauTamGiac.get(i).getCaploto());
                so2 = Integer.parseInt(listcauTamGiac.get(j).getCaploto());
                if (so1 > so2) {
                    cauTamGiac = listcauTamGiac.get(i);
                    listcauTamGiac.set(i, listcauTamGiac.get(j));
                    listcauTamGiac.set(j, cauTamGiac);
                }
            }
        }
        String key = "";
        Map<String, CauTamGiac> mKey = new HashMap<String, CauTamGiac>();
        Map<String, String> mStep = new HashMap<String, String>();
        List<CauTamGiac> list1 = new ArrayList<CauTamGiac>();
        int j = 0;
        int k = 0;
        for (int i = 0; i < listcauTamGiac.size(); i++) {
            key = listcauTamGiac.get(i).getCaploto();
            if (!mKey.containsKey(key)) {
                list1.add(listcauTamGiac.get(i));
                mKey.put(key, listcauTamGiac.get(i));
                mStep.put(key, "" + j);
                j++;
            } else {
                cauTamGiac = (CauTamGiac) mKey.get(key);
                if (mKey.get(key).getVitri1() < listcauTamGiac.get(i).getVitri1()) {
                    mKey.put(key, listcauTamGiac.get(i));
                    k = Integer.parseInt(mStep.get(key));
                    list1.set(k, listcauTamGiac.get(i));
                }
            }
        }
        list.setResultCauTamGiac(list1);
        return list;
    }

    public List<Lottery> parserLotteryPhanTichLoTo(String code) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERYPHANTICHLOTO;
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserLotteryPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public List<Lottery> parserLotteryPhanTichLoToDB(String code) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_PHAN_TICH_LOTODB;
            url = url.replace("code", code);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserLotteryPhanTichLoTo=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeLotoCapSo(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_LOTO_CAP_SO;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoCapSo=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeLotoTheoDan(String code, String numOfWeek, String dan) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_LOTO_DAN;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            url = url.replace("dan", dan);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return capSoJson.getDanhsachcapso();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoTheoDan=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietCapSo(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DACBIET_CAP_SO;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSo=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietCapSoHangChuc(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_CAP_SO_HANG_CHUC;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachhangchuc")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachhangchuc(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSoHangChuc=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietCapSoHangDonVi(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_CAP_SO_HANG_DON_VI;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachhangdonvi")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachhangdonvi(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSoHangDonVi=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeLotoCapSoHangChuc(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_LO_TO_HANG_CHUC;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoCapSoHangChuc=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeLotoCapSoHangDonVi(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_LO_TO_DON_VI;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoCapSoHangDonVi=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietTong(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_TONG;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachtong")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietTong=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietHieu(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_HIEU;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachhieu(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietHieu=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietCham(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_CHAM;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachcapso(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCham=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietChiaHetCho3(String code, String numOfWeek, String order, String du) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_CHIA_HET_CHO_3;
            if ("1".equals(du)) {
                url = Contant.URL_THONG_KE_DAC_BIET_CHIA_HET_CHO_3_DU_1;
            } else if ("2".equals(du)) {
                url = Contant.URL_THONG_KE_DAC_BIET_CHIA_HET_CHO_3_DU_2;
            }
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachchiahetcho3")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachchiahetcho3(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietChiaHetCho3=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietChanLe(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_CAP_SO_CHAN_LE;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return orderLoto(capSoJson.getDanhsachchanle(), order);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietChanLe=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeLoRoi(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_LO_ROI;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return addStringLotterySpecial(orderLoto(capSoJson.getDanhsachcapso(), order));
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLoRoi=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeDacBietGiaiSo(String code, String numOfWeek, String order) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_DAC_BIET_GIAI_SO;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachcapso")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return addString(orderLoto(capSoJson.getDanhsachcapso(), order)); //orderLoto(addString(capSoJson.getDanhsachcapso()),order);//
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietGiaiSo=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeNgayNayNamTruoc(String code, String numOfWeek) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_NGAY_NAY_NAM_TRUOC;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachngaynaynamxua")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return addString(capSoJson.getDanhsachngaynaynamxua());
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeNgayNayNamTruoc=======>>" + e.toString());
        }
        return null;
    }

    public List<CapSo> parserThongKeNgayNayThangTruoc(String code, String numOfWeek) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_NGAY_NAY_THANG_TRUOC;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("danhsachngaynaynamxua")) {
                CapSoJson capSoJson = (CapSoJson) gson.fromJson(string, CapSoJson.class);
                return addString(capSoJson.getDanhsachngaynaynamxua());
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeNgayNayThangTruoc=======>>" + e.toString());
        }
        return null;
    }

    public List<Mobat> parserMoBat(String code, String sdate) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_MO_BAT;
            url = url.replace("code", code).replace("sdate", sdate);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                MobatJson mobatJson = (MobatJson) gson.fromJson(string, MobatJson.class);
                return mobatJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserMoBat=======>>" + e.toString());
        }
        return null;
    }

    public List<LotterySpecial> parserThongKeTheoThu(String code, String sdate, String edate) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_RESULT_SPECIAL_START_TO_END;
            url = url.replace("code", code).replace("sdate", sdate).replace("edate", edate);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotterySpecialJson lotterySpecialJson = (LotterySpecialJson) gson.fromJson(string, LotterySpecialJson.class);
                return subLotterySpecial(lotterySpecialJson.getList());
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeTheoThu=======>>" + e.toString());
        }
        return null;
    }

    public List<List<TanSuat>> parserThongKeTanSuatLoto(String numOfWeek, String code) {

        try {
            Gson gson = new Gson();
            String url = Contant.URL_TANSUATLOTO;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                TanSuatJson tanSuatJson = (TanSuatJson) gson.fromJson(string, TanSuatJson.class);
                return tanSuatJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeTanSuatLoto=======>>" + e.toString());
        }

        return null;
    }

    public List<List<TanSuat>> parserThongKeTanSuatCapLoto(String numOfWeek, String code) {

        try {
            Gson gson = new Gson();
            String url = Contant.URL_TANSUATCAPLOTO;
            url = url.replace("code", code).replace("numofweek", numOfWeek);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                TanSuatJson tanSuatJson = (TanSuatJson) gson.fromJson(string, TanSuatJson.class);
                return tanSuatJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeTanSuatLoto=======>>" + e.toString());
        }

        return null;
    }

    public List<Lottery> parserThongKeTrucTiepMienTrung(String ddmmyyyy, String region) {

        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_RESULT_OF_REGION;
            url = url.replace("ddmmyyyy", ddmmyyyy).replace("region", region);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeTrucTiepMienTrung=======>>" + e.toString());
        }

        return null;
    }

    public List<Lottery> parserLotteryResult(String code, String start, String end) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_RESULT_START_TO_END;
            url = url.replace("code", code);
            url = url.replace("sdate", start);
            url = url.replace("edate", end);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Lottery> parserLotteryResultRegionStartToEnd(String region, String start, String end) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_RESULT_REGION;
            url = url.replace("region", region);
            url = url.replace("sdate", start);
            url = url.replace("edate", end);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] arg) {
        ThongKeRequest thongKeRequest = new ThongKeRequest();
        List<Lottery> lsList = thongKeRequest.parserThongKeTrucTiepMienTrung("31/03/2016", "MT");
        if (!lsList.isEmpty()) {
            for (Lottery lottery : lsList) {
                System.out.println("Province:" + lottery.getProvince());
                System.out.println("fist:" + lottery.getFirst());
            }
        }

    }
}
