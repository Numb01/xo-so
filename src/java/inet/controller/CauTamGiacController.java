/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.bean.CauTamGiac;
import inet.bean.ResultCauTamGiac;
import inet.request.ThongKeRequest;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author hanhlm
 */
public class CauTamGiacController extends BaseController {

    private HashMap<String, ResultCauTamGiac> hListResultCauTamGiac = null;
    private String sDDMMYYYY = null;

    public CauTamGiacController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
        if (hListResultCauTamGiac == null) {
            hListResultCauTamGiac = new HashMap<String, ResultCauTamGiac>();
        }
        if (!today.equals(sDDMMYYYY)) {
            hListResultCauTamGiac.clear();
        }
        sDDMMYYYY = today;
    }

    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String cau = request.getParameter("cau");
        String sdate = request.getParameter("sdate");
        String code = request.getParameter("code");
        String giai = request.getParameter("giai");
        String capso = request.getParameter("capso");

        if (cau == null || "".equals(cau)) {
            cau = "2";
        }
        if (sdate == null || "".equals(sdate)) {
            sdate = today;
        }
        if (code == null || "".equals(code)) {
            code = "XSTD";
        }
        if (giai == null || "".equals(giai)) {
            giai = "00";
        }

        String key = cau + code + giai + sdate.replaceAll("/", "");

        ResultCauTamGiac list = null;

        if (hListResultCauTamGiac.containsKey(key)) {
            list = hListResultCauTamGiac.get(key);
        } else {
            ThongKeRequest thongKeRequest = new ThongKeRequest();
            list = thongKeRequest.parserCauTamGiac(sdate, cau, giai, code);
            if (list != null) {
                hListResultCauTamGiac.put(key, list);
            }
        }

        if (list != null) {
            if (capso != null && !"".equals(capso)) {
                list = loadCauTamGiac(list, capso);
                mod.addObject("capso", capso);
            }
            mod.addObject("cautamgiac", list.getCauTamGiac());
            mod.addObject("resultcautamgiac", list.getResultCauTamGiac());

        }

        //the seo
        String title = "XSMB - Soi cầu tam giác xổ số miền bắc siêu chuẩn";
        String des = "Soi cầu tam giác - Soi cầu loto tam giác xổ số miền Bắc cực chuẩn, phân tích thống kê đưa ra con số chuẩn xác nhất trong ngày.";
        String keyword = "soi cau mb tam giac, soi cau tam giac, soi cau tam giac mien bac, soi cau tam giac mb";
        String strH1 = "Cầu loto tam giác";

        mod.addObject("title", title);
        mod.addObject("des", des);
        mod.addObject("keyword", keyword);
        mod.addObject("strH1", strH1);

        mod.addObject("cau", cau);
        mod.addObject("sdate", sdate);
        mod.addObject("code", code);
        mod.addObject("giai", giai);

        if ("pc".equals(strMobile)) {
            mod.setViewName("/cautamgiac");
        } else {
            mod.setViewName("/mobile/cautamgiac");
        }

        return mod;
    }

    private ResultCauTamGiac loadCauTamGiac(ResultCauTamGiac resultCauTamGiac, String capso) {

        String temp1 = "<span class=\"CAULOTO_new_TAMGIAC_box_kq\">";
        String temp2 = "</span>";

        CauTamGiac cauTamGiac = null;
        for (int i = 0; i < resultCauTamGiac.getResultCauTamGiac().size(); i++) {
            cauTamGiac = resultCauTamGiac.getResultCauTamGiac().get(i);
            if (cauTamGiac.getCaploto().equals(capso)) {
                break;
            }
        }

        String temp3 = "";
        String temp5 = "";
        String temp6 = "";
        String temp7 = "";
        String temp8 = "";
        for (int i = resultCauTamGiac.getCauTamGiac().size() - 1; i > -1; i--) {
            resultCauTamGiac.getCauTamGiac().get(i).setCautamgiac(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac().replaceAll(temp1, "").replaceAll(temp2, ""));

            resultCauTamGiac.getCauTamGiac().get(i).setVitri1(cauTamGiac.getVitri1());
            resultCauTamGiac.getCauTamGiac().get(i).setVitri2(cauTamGiac.getVitri2());

            int len = 0;
            int vitri1 = cauTamGiac.getVitri1();
            int vitri2 = cauTamGiac.getVitri2();
            for (StringTokenizer tokenizer = new StringTokenizer(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac(), "-"); tokenizer.hasMoreTokens();) {
                String str = tokenizer.nextToken();
                len = str.length();
                if (vitri1 < len) {
                    String start = str.substring(0, vitri1);
                    String temp4 = str.substring(vitri1, vitri1 + 1);
                    String end = str.substring(vitri1 + 1, len);
                    temp3 = temp4;
                    temp8 = temp4;
                    temp5 = temp1 + temp4 + temp2;
                    temp6 = start + "x" + end;
//                    System.out.println("temp5==========" + temp5 + "==========temp6=======" + temp6);
                    resultCauTamGiac.getCauTamGiac().get(i).setCautamgiac(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac().replace(str, temp6));
                    break;
                }
                vitri1 -= len;
            }

            for (StringTokenizer tokenizer = new StringTokenizer(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac(), "-"); tokenizer.hasMoreTokens();) {
                String str = tokenizer.nextToken();
                len = str.length();
                if (vitri2 < len) {
                    String start = str.substring(0, vitri2);
                    String temp4 = str.substring(vitri2, vitri2 + 1);
                    String end = str.substring(vitri2 + 1, len);
                    temp3 += temp4;
                    temp8 = temp4 + temp8;
                    temp7 = temp1 + temp4 + temp2;
                    temp6 = start + "y" + end;
//                    System.out.println("temp7==========" + temp7 + "==========temp6=======" + temp6);
                    resultCauTamGiac.getCauTamGiac().get(i).setCautamgiac(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac().replace(str, temp6));
                    break;
                }
                vitri2 -= len;
            }
            if (i > 0) {
                System.out.println("temp3====" + temp3);
                System.out.println(resultCauTamGiac.getCauTamGiac().get(i - 1).getLoto());
                resultCauTamGiac.getCauTamGiac().get(i - 1).setLoto(resultCauTamGiac.getCauTamGiac().get(i - 1).getLoto().replaceAll(temp1, "").replaceAll(temp2, ""));
                resultCauTamGiac.getCauTamGiac().get(i - 1).setLoto(resultCauTamGiac.getCauTamGiac().get(i - 1).getLoto().replaceAll(temp3, temp1 + temp3 + temp2));
                resultCauTamGiac.getCauTamGiac().get(i - 1).setLoto(resultCauTamGiac.getCauTamGiac().get(i - 1).getLoto().replaceAll(temp8, temp1 + temp8 + temp2));
            }
            resultCauTamGiac.getCauTamGiac().get(i).setCautamgiac(resultCauTamGiac.getCauTamGiac().get(i).getCautamgiac().replace("x", temp5).replace("y", temp7));
        }

        return resultCauTamGiac;
    }

    public static void main(String[] arg) {
        String str = "48,54,98,90,10,72,01,04,24,43,29,07,67,54,60,76,11,61,70,11,02,85,62,76,74,12,54";
        String rep = "10";
        System.out.println(str.replaceAll("10", "rep"));
//        String str="03354-3689-947-31-4";
//        int vitri1=1;
//        int vitri2=10;
//        int len=0;        
//        String temp1="<span class=\"red\">";
//        String temp2="</span>";
//        String temp3="";
//        String temp4="";
//        String temp5="";
//        for(StringTokenizer tokenizer=new StringTokenizer(str,"-");tokenizer.hasMoreTokens();){
//            String string=tokenizer.nextToken();
//            len=string.length();
//            if(vitri1<len){
//                String start=string.substring(0,vitri1);
//                String temp=string.substring(vitri1,vitri1+1);
//                String end=string.substring(vitri1+1,len);
//                temp3=temp1+temp+temp2;
//                temp4=start+"x"+end;
//                str=str.replace(string, temp4);
//                System.out.println(string+"==="+temp3+"======"+str);
//                break;
//            }
//            vitri1-=len;
//        }
//        
//        for(StringTokenizer tokenizer=new StringTokenizer(str,"-");tokenizer.hasMoreTokens();){
//            String string=tokenizer.nextToken();
//            len=string.length();
//            if(vitri2<len){
//                String start=string.substring(0,vitri2);
//                String temp=string.substring(vitri2,vitri2+1);
//                String end=string.substring(vitri2+1,len);
//                temp5=temp1+temp+temp2;
//                temp4=start+"y"+end;
//                str=str.replace(string, temp4);
//                System.out.println(string+"==="+temp5+"======"+str);
//                break;
//            }
//            vitri2-=len;
//        }
//        
//        str=str.replace("x", temp3).replace("y", temp5);
//        System.out.println(str);
    }

}
