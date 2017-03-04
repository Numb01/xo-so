/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.request;

import com.google.gson.Gson;
import inet.bean.CapSo;
import inet.bean.CapSoJson;
import inet.bean.Lottery;
import inet.bean.LotteryJson;
import inet.bean.NhipTanSuat;
import inet.bean.NhipTanSuatJson;
import inet.bean.PhantichLoto;
import inet.bean.PhantichLotoJson;
import inet.util.Contant;
import inet.util.HttpURLRequest;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duynv
 */
public class NhipTanSuatRequest {

    public List<NhipTanSuat> parserThongKeNhipTanSuat(String numOfWeek, String code, String dayofweek, String capso) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_THONG_KE_NHIP_TAN_SUAT;
            url = url.replace("numofweek", numOfWeek).replace("code", code).replace("dayofweek", dayofweek).replace("capso", capso);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                NhipTanSuatJson nhipTanSuatJson = (NhipTanSuatJson) gson.fromJson(string, NhipTanSuatJson.class);
                return nhipTanSuatJson.getList();
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======NhipTanSuatRequest=======>>parserThongKeNhipTanSuat=======>>" + e.toString());
        }
        return null;
    }
    
    private List<PhantichLoto> fillterPhanTichLotoDB(List<PhantichLoto> list, int cau) {
        if (list == null || list.isEmpty()) {
            return list;
        }
        List<PhantichLoto> listPhanTichLoto = null;
        for (PhantichLoto lst : list) {
            if (lst.getDodai() >= cau) {
                if (listPhanTichLoto == null) {
                    listPhanTichLoto = new ArrayList<PhantichLoto>();
                }
                listPhanTichLoto.add(lst);
            }
        }

        return listPhanTichLoto;
    }

    public List<PhantichLoto> parserPhanTichLoToDB(String startdate, String lon, String nhay, String cau, String maxcau) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_PHAN_TICH_LOTODB;
            url = url.replace("startdate", startdate).replace("lon", lon);
            url = url.replace("nhay", nhay).replace("maxcau", maxcau).replace("cau", cau);
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                int iCau = Integer.parseInt(cau);
                PhantichLotoJson phantichLotoJson = (PhantichLotoJson) gson.fromJson(string, PhantichLotoJson.class);
                return phantichLotoJson.getList(); //fillterPhanTichLotoDB(phantichLotoJson.getList(), iCau);
            }
        } catch (Exception e) {
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======NhipTanSuatRequest=======>>parserPhanTichLoToDB=======>>" + e.toString());
        }
        return null;
    }

    public List<Lottery> parserLotteryDBResult() {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_LOTTERY_PHAN_TICH_LOTODB;
            String string = HttpURLRequest.sendGet(url);
            if (string.contains("list")) {
                LotteryJson lotteryJson = (LotteryJson) gson.fromJson(string, LotteryJson.class);
                return lotteryJson.getList();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            System.out.println("=======NhipTanSuatRequest=======>>parserThongKeLotoCapSo=======>>" + e.toString());
        }
        return null;
    }

    private List<CapSo> orderLoto(List<CapSo> list, String order) {
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
    
    public static void main(String[] args) {
        NhipTanSuatRequest request = new NhipTanSuatRequest();
        List<NhipTanSuat> lstNhipTanSuat = request.parserThongKeNhipTanSuat("3", "XSTD", "all", "45");
        for (NhipTanSuat nts : lstNhipTanSuat) {
            System.out.println("Cap so: " + nts.getCapso());
            System.out.println("Ngay ve: " + nts.getNgayve());
            System.out.println("Giai ve: " + nts.getGiaive());
            System.out.println("Thu ve: " + nts.getThuve());
            System.out.println("-----------------------------------------");
        }
    }
}
