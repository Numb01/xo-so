/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.listener;

import inet.bean.Lottery;
import inet.bean.LotteryCompany;
import inet.bean.Lotterys;
import inet.model.LotteryResultDAO;
import inet.request.LotteryRequest;
import inet.util.DatePro;
import inet.util.DateProc;
import inet.util.FileTool;
import inet.util.StringPro;
import inet.util.Today;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author hanhlm
 */
public class XSLive implements ServletContextListener, Runnable {

    private boolean running = true;
    public static List<LotteryCompany> listCompany = null;

    public static HashMap<String, Lottery> hLottery = null;
    public static HashMap<String, List<String>> hDauDuoi = null;

    public static List<Lottery> listLotteryMB = null;
    public static List<String> listDuoiMB = null;
    public static List<String> listDauMB = null;

    public static Lotterys listLotteryMT = null;
    public static List<String> listDuoiMT = null;
    public static int numSizeMT = 0;

    public static Lotterys listLotteryMN = null;
    public static List<String> listDuoiMN = null;
    public static int numSizeMN = 0;

    public static String sDDMMYYYY = null;

    private static String newsFolder = null;
    private static String realNewsFolder = null;

    private static boolean buildMB = false;
    private static boolean buildMT = false;
    private static boolean buildMN = false;

    private String ddmmyyyy = null;
    private String dayOfWeek = null;

    private static LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();

//    public static String lotteryResultMB=null;
//    public static String lotteryResultMT=null;
//    public static String lotteryResultMN=null;
    public void contextInitialized(ServletContextEvent contextEvent) {

        ServletContext c = contextEvent.getServletContext();
        if (c != null) {
            if (c.getInitParameter("buffer_folder") != null) {
                newsFolder = c.getInitParameter("buffer_folder");
                realNewsFolder = c.getRealPath("/") + "/" + c.getInitParameter("buffer_folder");
            } else {
                newsFolder = "buffer";
                realNewsFolder = c.getRealPath("/") + "/" + newsFolder;
            }
        }

        loadCompany();

        new Thread(this).start();
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Stop running Live XS");
        running = false;
    }

    private void loadCompany() {
        if (listCompany == null || listCompany.isEmpty() || listCompany.size() < 1) {
            LotteryRequest lotteryRequest = new LotteryRequest();
            listCompany = lotteryRequest.parserLotteryCompany();
        }
    }

    public void run() {

        Today today = null;
        while (running) {
            // load company
            loadCompany();

            today = new Today();
            //System.out.println("ok chay di");
            try {
                ddmmyyyy = DateProc.getDateString(DateProc.createTimestamp());
                dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(ddmmyyyy);
                if (sDDMMYYYY == null || !ddmmyyyy.equals(sDDMMYYYY)) {
                    synchronized (this) {
                        listLotteryMB = null;
                        listDuoiMB = null;

                        listLotteryMT = null;
                        listDuoiMT = null;

                        listLotteryMN = null;
                        listDuoiMN = null;

                        hLottery = null;
                        hDauDuoi = null;

                        buildMB = false;
                        buildMT = false;
                        buildMN = false;
                    }

                    sDDMMYYYY = ddmmyyyy;

                }
                //System.out.println("today===="+today.getMinute());

                //build html buffer
//                if ((today.getHour() == 18 && today.getMinute() > 45) || (today.getHour() > 18)) {
//                    if (!buildMB && listLotteryMB != null && !listLotteryMB.isEmpty()) {
//                        if (listLotteryMB.get(0).getSpecial() != null && !listLotteryMB.get(0).getSpecial().equals("")) {
//                            buildBufferHtmlXSMB("pc");
//                            buildBufferHtmlXSMB("mobile");
//                            buildMB = true;
//                        }
//                    }
//                }
//                if((today.getHour()==17&&today.getMinute()>45)||(today.getHour()>17)){
//                    if(!buildMT&&listLotteryMT!=null&&listLotteryMT.getSpecial()!=null&&listLotteryMT.getSpecial().contains("+")){
//                        buildBufferHtmlXSMT("pc");
////                        buildBufferHtmlXSMT("mobile");
//                        buildMT=true;
//                    }
//                }
//                
//                
//                if((today.getHour()==16&&today.getMinute()>45)||(today.getHour()>16)){
//                    if(!buildMN&&listLotteryMN!=null&&listLotteryMN.getSpecial()!=null&&listLotteryMN.getSpecial().contains("+")){
//                        buildBufferHtmlXSMN("pc");
////                        buildBufferHtmlXSMN("mobile");
//                        buildMN=true;
//                    }
//                }
                if (today.getHour() == 16 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam                    
                    builKQXS("MN");
                    // buildBufferHtmlXSMN("pc");
//                    buildBufferHtmlXSMN("mobile");
                    Thread.sleep(5000);
                } else if (today.getHour() == 17 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    builKQXS("MT");
//                    buildBufferHtmlXSMT("pc");
//                    buildBufferHtmlXSMT("mobile");
                    Thread.sleep(5000);
                } else if (today.getHour() == 18 && today.getMinute() <= 45) {
                    // lay ket qua xo so mien nam
//                    if(listLotteryMN==null){builKQXS("MN");}
//                    if(listLotteryMT==null){builKQXS("MT");}
                    builKQXS("MB");
//                    buildBufferHtmlXSMB("pc");
//                    buildBufferHtmlXSMB("mobile");
                    Thread.sleep(5000);
                } else if (today.getHour() == 16 && today.getMinute() > 45) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else if (today.getHour() == 17 && today.getMinute() > 45) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    if (listLotteryMT == null) {
                        builKQXS("MT");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else if ((today.getHour() == 18 && today.getMinute() > 45) || today.getHour() > 18) {
                    if (listLotteryMN == null) {
                        builKQXS("MN");
                    }
                    if (listLotteryMT == null) {
                        builKQXS("MT");
                    }
                    if (listLotteryMB == null) {
                        builKQXS("MB");
                    }
                    Thread.sleep(1000 * 60 * 5);
                } else {
                    Thread.sleep(1000 * 60 * 5);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("XSLive==========>>>>>>>>>>>" + ex.toString());
            }

        }

    }

    private void builKQXS(String region) {

        if (hLottery == null) {
            hLottery = new HashMap<String, Lottery>();
            hDauDuoi = new HashMap<String, List<String>>();
        }
        LotteryRequest lotteryRequest = new LotteryRequest();
        List<Lottery> listLottery = lotteryRequest.parserLotteryResultNewest(region);
//        List<Lottery> listLottery = new ArrayList<Lottery>();
//        Lottery l=new Lottery();
//        l.setOpenDate("14/02/2017");
//        l.setFirst("78568");
//        listLottery.add(l);
//        listLottery = checkLottery(listLottery);
        if ("MB".equalsIgnoreCase(region)) {
            listLottery = checkAddLoadingMB(listLottery);
        } else {
            listLottery = checkLottery(listLottery);
        }

        // ket qua xo so mien bac
        if ("MB".equals(region)) {
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    listLotteryMB = listLottery;
                    listDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                    listDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
                }
            } else {
                listLottery = new ArrayList<Lottery>();
                listLottery.add(addLotteryNull("Mien Bac", "XSTD"));
                listLotteryMB = listLottery;
                listDuoiMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), true);
                listDauMB = StringPro.filterDauOrDuoi(listLotteryMB.get(0), false);
            }
        } else if ("MT".equals(region)) {
            // ket qua xo so mien trung
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    listLotteryMT = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMT = findDuoi(listLottery);
                    numSizeMT = listLottery.size();
                }
            } else {
                List<LotteryCompany> listComp = getCompanyDayOfWeek("MT");
                if (listComp != null && !listComp.isEmpty()) {
                    for (int i = 0; i < listComp.size(); i++) {
                        if (listLottery == null) {
                            listLottery = new ArrayList<Lottery>();
                        }
                        listLottery.add(addLotteryNull(listComp.get(i).getCompany(), listComp.get(i).getCode()));
                    }
                    listLotteryMT = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMT = findDuoi(listLottery);
                    numSizeMT = listLottery.size();
                }

            }

        } else if ("MN".equals(region)) {
            // ket qua xo so mien nam 
            if (listLottery != null && !listLottery.isEmpty()) {
                synchronized (this) {
                    listLotteryMN = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMN = findDuoi(listLottery);
                    numSizeMN = listLottery.size();
                }
            } else {
                List<LotteryCompany> listComp = getCompanyDayOfWeek("MN");
                if (listComp != null && !listComp.isEmpty()) {
                    for (int i = 0; i < listComp.size(); i++) {
                        if (listLottery == null) {
                            listLottery = new ArrayList<Lottery>();
                        }
                        listLottery.add(addLotteryNull(listComp.get(i).getCompany(), listComp.get(i).getCode()));
                    }
                    listLotteryMN = findLotterys(listLottery, sDDMMYYYY);
                    listDuoiMN = findDuoi(listLottery);
                    numSizeMN = listLottery.size();
                }

            }

        }
    }

    private Lotterys findLotterys(List<Lottery> list, String ddmmyyyy) {
        Lotterys lotterys = null;
        LotteryCompany lotteryCompany = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                hLottery.put(list.get(i).getCode(), list.get(i));
                hDauDuoi.put(list.get(i).getCode() + "_duoi", StringPro.filterDauOrDuoi(list.get(i), true));
                hDauDuoi.put(list.get(i).getCode() + "_dau", StringPro.filterDauOrDuoi(list.get(i), false));
                if (lotterys == null) {
                    lotterys = new Lotterys();
                    lotterys.setOpenDate(ddmmyyyy);
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
                    lotterys.setLink(lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + "-" + lotteryCompany.getCodeReverseLowerCase() + ".html");
                } else {
                    lotterys.setProvince(lotterys.getProvince() + "+" + list.get(i).getProvince());
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
                    lotterys.setLink(lotterys.getLink() + "+" + lotteryCompany.getCodeLowerCase() + "-ket-qua-xo-so-" + lotteryCompany.getCompanyLink() + "-" + lotteryCompany.getCodeReverseLowerCase() + ".html");
                }
            }

        }

        return lotterys;
    }

    private List<String> findDuoi(List<Lottery> list) {
        List<String> listDuoi = null;
        List<String> listDuoi1 = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                listDuoi1 = StringPro.filterDauOrDuoi(list.get(i), true);
                if (listDuoi == null) {
                    listDuoi = listDuoi1;
                } else {
                    for (int j = 0; j < 10; j++) {
                        listDuoi.set(j, listDuoi.get(j) + "+" + listDuoi1.get(j));
                    }
                }
            }
        }
        return listDuoi;
    }

    private LotteryCompany findLotteryCompanyOfCode(String code) {
        LotteryCompany lotteryCompany = null;
        if (listCompany == null || listCompany.isEmpty()) {
            return lotteryCompany;
        }
        for (int i = 0; i < listCompany.size(); i++) {
            if (code.equalsIgnoreCase(listCompany.get(i).getCode())) {
                lotteryCompany = listCompany.get(i);
            }
        }

        return lotteryCompany;
    }

    private Lottery addLotteryNull(String tinh, String code) {
        Lottery lottery = new Lottery();
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        if ("XSTD".equals(code)) {
            lottery.setCode(code);
            lottery.setProvince(tinh);
            lottery.setOpenDate(sDDMMYYYY);
            lottery.setSpecial(linkImageLoading);
            lottery.setFirst(linkImageLoading);
            lottery.setSecond(linkImageLoading + "-" + linkImageLoading);
            lottery.setThird(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFifth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSeventh(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
        } else {
            lottery.setCode(code);
            lottery.setProvince(tinh);
            lottery.setOpenDate(sDDMMYYYY);
            lottery.setSpecial(linkImageLoading);
            lottery.setFirst(linkImageLoading);
            lottery.setSecond(linkImageLoading);
            lottery.setThird(linkImageLoading + "-" + linkImageLoading);
            lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setFifth(linkImageLoading);
            lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            lottery.setSeventh(linkImageLoading);
            lottery.setEighth(linkImageLoading);
        }

        return lottery;
    }

    private List<LotteryCompany> getCompanyDayOfWeek(String region) {
        LotteryCompany lotteryCompany = null;
        List<LotteryCompany> listComp = null;
        String dayOfWeek = DatePro.getDateOfWeekDDMMYYYY(sDDMMYYYY);
        for (int i = 0; i < listCompany.size(); i++) {
            lotteryCompany = listCompany.get(i);
            if (lotteryCompany.getOpendate().contains(dayOfWeek) && lotteryCompany.getRegion().equals(region)) {
                if (listComp == null) {
                    listComp = new ArrayList<LotteryCompany>();
                }
                listComp.add(lotteryCompany);
            }
        }

        return listComp;
    }

    private List<Lottery> checkLottery(List<Lottery> listLottery) {
        if (listLottery == null || listLottery.isEmpty()) {
            return null;
        }
        if (!sDDMMYYYY.equals(listLottery.get(0).getOpenDate())) {
            return null;
        }
        Lottery lottery = null;
        for (int i = 0; i < listLottery.size(); i++) {
            lottery = listLottery.get(i);
            lottery.setSpecial(checkAddLoading(lottery.getSpecial()));
            lottery.setFirst(checkAddLoading(lottery.getFirst()));
            lottery.setSecond(checkAddLoading(lottery.getSecond()));
            lottery.setThird(checkAddLoading(lottery.getThird()));
            lottery.setFourth(checkAddLoading(lottery.getFourth()));
            //System.out.println("lottery.setFourth====>>>>"+lottery.getFourth());
            lottery.setFifth(checkAddLoading(lottery.getFifth()));
            lottery.setSixth(checkAddLoading(lottery.getSixth()));
            lottery.setSeventh(checkAddLoading(lottery.getSeventh()));
            lottery.setEighth(checkAddLoading(lottery.getEighth()));

            listLottery.set(i, lottery);
        }

        return listLottery;
    }

    private List<Lottery> checkAddLoadingMB(List<Lottery> listLottery) {
        if (listLottery == null || listLottery.isEmpty()) {
            return null;
        }
        if (!sDDMMYYYY.equals(listLottery.get(0).getOpenDate())) {
            return null;
        }

        Lottery lottery = null;
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        for (int i = 0; i < listLottery.size(); i++) {
            lottery = listLottery.get(i);
            if (lottery.getSpecial() == null || "".equals(lottery.getSpecial())) {
                lottery.setSpecial(linkImageLoading);
            }

            if (dayOfWeek == "3" || dayOfWeek == "6") {
                if (lottery.getSpecial_xsmb() == null || "".equals(lottery.getSpecial_xsmb())) {
                    lottery.setSpecial_xsmb(linkImageLoading);
                }
            }

            if (lottery.getFirst() == null || "".equals(lottery.getFirst())) {
                lottery.setFirst(linkImageLoading);
            }
            if (lottery.getSecond() == null || "".equals(lottery.getSecond())) {
                lottery.setSecond(linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSecond().contains("-")) {
                lottery.setSecond(lottery.getSecond() + "-" + linkImageLoading);
            }
            if (lottery.getThird() == null || "".equals(lottery.getThird())) {
                lottery.setThird(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getThird().contains("-")) {
                lottery.setThird(lottery.getThird() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getThird().split("-");
                if (arrStr.length < 6) {
                    for (int j = 0; j < (6 - arrStr.length); j++) {
                        lottery.setThird(lottery.getThird() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getFourth() == null || "".equals(lottery.getFourth())) {
                lottery.setFourth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getFourth().contains("-")) {
                lottery.setFourth(lottery.getFourth() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getFourth().split("-");
                if (arrStr.length < 4) {
                    for (int j = 0; j < (4 - arrStr.length); j++) {
                        lottery.setFourth(lottery.getFourth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getFifth() == null || "".equals(lottery.getFifth())) {
                lottery.setFifth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getFifth().contains("-")) {
                lottery.setFifth(lottery.getFifth() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getFifth().split("-");
                if (arrStr.length < 6) {
                    for (int j = 0; j < (6 - arrStr.length); j++) {
                        lottery.setFifth(lottery.getFifth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getSixth() == null || "".equals(lottery.getSixth())) {
                lottery.setSixth(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSixth().contains("-")) {
                lottery.setSixth(lottery.getSixth() + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getSixth().split("-");
                if (arrStr.length < 3) {
                    for (int j = 0; j < (3 - arrStr.length); j++) {
                        lottery.setSixth(lottery.getSixth() + "-" + linkImageLoading);
                    }
                }
            }
            if (lottery.getSeventh() == null || "".equals(lottery.getSeventh())) {
                lottery.setSeventh(linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else if (!lottery.getSeventh().contains("-")) {
                lottery.setSeventh(lottery.getSeventh() + "-" + linkImageLoading + "-" + linkImageLoading + "-" + linkImageLoading);
            } else {
                String[] arrStr = lottery.getSeventh().split("-");
                if (arrStr.length < 4) {
                    for (int j = 0; j < (4 - arrStr.length); j++) {
                        lottery.setSeventh(lottery.getSeventh() + "-" + linkImageLoading);
                    }
                }
            }

            listLottery.set(i, lottery);
        }

        return listLottery;
    }

    private static String checkAddLoading(String str) {
        String linkImageLoading = "<img src=\"http://static.xoso.wap.vn/images/xo_so_truc_tiep.gif\">";
        String result = "";
        if (str == null || "".equals(str)) {
            return linkImageLoading;
        }

        String[] arrStr = str.split("-");
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '-') {
                count++;
            }
        }

        if (arrStr.length == 0) {
            for (int i = 0; i < count; i++) {
                result = result + "-" + linkImageLoading;
            }
        } else {
            for (int i = 0; i < arrStr.length; i++) {
                result = result + "-" + arrStr[i];
            }

            int addCount = count - (arrStr.length - 1);
            for (int i = 0; i < addCount; i++) {
                result = result + "-" + linkImageLoading;
            }
        }
        result = result.replaceFirst("-", "");
        return result;
    }

    public static void main(String[] args) {
        String str = "a-b-c-d-e";
        int i = 0;
        for (StringTokenizer stringTokenizer = new StringTokenizer(str, "-"); stringTokenizer.hasMoreTokens(); i++) {
            String token = stringTokenizer.nextToken();
            System.out.println("token==>>" + token + "==" + i);
        }
    }

    private List<String> filterDauOrDuoi(Lottery lottery, boolean isDauOrDuoi) {
        List result = new ArrayList();
        String str = "";
        String str1 = "";
        String str2 = "";
        String strDacbiet = "";
        int dauDacbiet = 0;

        if (isDauOrDuoi) {
            for (int i = 0; i < 10; i++) {
                str1 = "";
                str2 = "";

                if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
                    str = subRight(lottery.getSpecial(), 2);
                    if (str.startsWith(i + "")) {
                        str1 = str.substring(1, 2) + " ";
                        strDacbiet = str.substring(1, 2);
                        dauDacbiet = i;
                    }

                }

                if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
                    str = subRight(lottery.getFirst(), 2);
                    if (str.startsWith(i + "")) {
                        str2 = str.substring(1, 2);

                        str1 = str1 + str2 + " ";
                    }

                }

                String[] arrlottery = null;
                if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
                    arrlottery = lottery.getSecond().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
                    arrlottery = lottery.getThird().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
                    arrlottery = lottery.getFourth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
                    arrlottery = lottery.getFifth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
                    arrlottery = lottery.getSixth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
                    arrlottery = lottery.getSeventh().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
                    arrlottery = lottery.getEighth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.startsWith(i + "")) {
                            str2 = str.substring(1, 2);

                            str1 = str1 + str2 + " ";
                        }
                    }
                }

                if (!"".equals(str1)) {
                    result.add(str1.substring(0, str1.length() - 1));
                } else {
                    result.add("&nbsp;");
                }
            }
        } else {
            for (int i = 0; i < 10; i++) {
                str1 = "";
                str2 = "";

                if ((lottery.getSpecial() != null) && (!"".equals(lottery.getSpecial()))) {
                    str = subRight(lottery.getSpecial(), 2);
                    if (str.endsWith(i + "")) {
                        str1 = str.substring(0, 1) + " ";
                        strDacbiet = str.substring(0, 1);
                        dauDacbiet = i;
                    }

                }

                if ((lottery.getFirst() != null) && (!"".equals(lottery.getFirst()))) {
                    str = subRight(lottery.getFirst(), 2);
                    if (str.endsWith(i + "")) {
                        str2 = str.substring(0, 1);

                        str1 = str1 + str2 + " ";
                    }

                }

                String[] arrlottery = null;
                if ((lottery.getSecond() != null) && (!"".equals(lottery.getSecond()))) {
                    arrlottery = lottery.getSecond().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getThird() != null) && (!"".equals(lottery.getThird()))) {
                    arrlottery = lottery.getThird().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFourth() != null) && (!"".equals(lottery.getFourth()))) {
                    arrlottery = lottery.getFourth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getFifth() != null) && (!"".equals(lottery.getFifth()))) {
                    arrlottery = lottery.getFifth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSixth() != null) && (!"".equals(lottery.getSixth()))) {
                    arrlottery = lottery.getSixth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getSeventh() != null) && (!"".equals(lottery.getSeventh()))) {
                    arrlottery = lottery.getSeventh().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }

                    }

                }

                if ((lottery.getEighth() != null) && (!"".equals(lottery.getEighth()))) {
                    arrlottery = lottery.getEighth().split("-");
                    for (int j = 0; j < arrlottery.length; j++) {
                        str = subRight(arrlottery[j], 2);
                        if (str.endsWith(i + "")) {
                            str2 = str.substring(0, 1);

                            str1 = str1 + str2 + " ";
                        }
                    }
                }

                if (!"".equals(str1)) {
                    result.add(str1.substring(0, str1.length() - 1));
                } else {
                    result.add("&nbsp;");
                }
            }

        }

        return shortAscOrDesc(result, true, strDacbiet, dauDacbiet);
    }

    private List<String> shortAscOrDesc(List<String> list, boolean ascOrDesc, String strDacbiet, int dauDacbiet) {
        List list1 = null;
        if ((list == null) || (list.isEmpty())) {
            return null;
        }
        String string = "";
        String str = "";
        String[] arrStr = null;
        int tg1 = 0;
        int tg2 = 0;
        if (ascOrDesc) {
            for (int i = 0; i < list.size(); i++) {
                string = (String) list.get(i);
                if (!string.contains(" ")) {
                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    list1.add(string);
                } else {
                    arrStr = string.split(" ");
                    for (int j = 0; j < arrStr.length - 1; j++) {
                        for (int k = j + 1; k < arrStr.length; k++) {
                            tg1 = Integer.parseInt(arrStr[j]);
                            tg2 = Integer.parseInt(arrStr[k]);
                            if (tg1 > tg2) {
                                arrStr[j] = ("" + tg2);
                                arrStr[k] = ("" + tg1);
                            }
                        }
                    }

                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    str = "";
                    for (int j = 0; j < arrStr.length; j++) {
                        str = str + arrStr[j] + " ";
                    }
                    list1.add(str.substring(0, str.length() - 1));
                }
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                string = (String) list.get(i);
                if (!string.contains(" ")) {
                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    list1.add(string);
                } else {
                    arrStr = string.split(" ");
                    for (int j = 0; j < arrStr.length - 1; j++) {
                        for (int k = j + 1; k < arrStr.length; k++) {
                            tg1 = Integer.parseInt(arrStr[j]);
                            tg2 = Integer.parseInt(arrStr[k]);
                            if (tg1 < tg2) {
                                arrStr[j] = ("" + tg2);
                                arrStr[k] = ("" + tg1);
                            }
                        }
                    }

                    if (list1 == null) {
                        list1 = new ArrayList();
                    }
                    str = "";
                    for (int j = 0; j < arrStr.length; j++) {
                        str = str + arrStr[j] + " ";
                    }
                    list1.add(str.substring(0, str.length() - 1));
                }
            }
        }

        if (list1 != null) {
            for (int i = 0; i < list1.size(); i++) {
                if (i == dauDacbiet) {
                    str = strDacbiet;
                    str = "<span style=\"color: #FF3322\">" + str + "</span>";
                    list1.set(i, ((String) list1.get(i)).replaceFirst(strDacbiet, str));
                    break;
                }
            }
        }

        return list1;
    }

    private String subRight(String temp, int i) {
        if (temp == null) {
            return "";
        }
        String result = "";
        result = temp.substring(temp.length() - i, temp.length());
        return result;
    }

    private void buildBufferHtmlXSMN(String mobile) {
        String html = "";
        if (listLotteryMN != null) {
            int numSize = 4;
            if (numSizeMN == 3) {
                numSize = 2;
            } else if (numSizeMN == 2) {
                numSize = 3;
            }

            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
            html += "<div class=\"Centre_BANGKQ_title\">";
            html += "KẾT QUẢ XỔ SỐ MIỀN NAM  - THỨ " + dayOfWeek + "," + ddmmyyyy;
            html += "</div>";
            html += "<div class=\"Centre_BANGKQ_bg\">";
            html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";

            html += "<tr class=\"XSMN_table_row_1 font_14\">";
            html += "<td class=\"XSMN_table_col_1 do\">Tỉnh</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " do\">" + token + "</td>";
            }
            html += "</tr>";
            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải tám</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getEighth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải bảy</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSeventh(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải sáu</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSixth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải năm</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFifth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải tư</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFourth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải ba</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getThird(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải nhì</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSecond(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải nhất</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getFirst(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1 do\">Đặc Biệt</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getSpecial(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17 do\">" + token + "</td>";
            }
            html += "</tr>";

            html += "</table>";
            html += "<div class=\"Centre_BANGKQ_table_note\">";
            html += "Nhận <span class=\"do\">KQXS Miền Nam</span> hàng ngày: Soạn <span class=\"do\">KQ MN</span> gửi <span class=\"do\">8185</span><br />";
            html += "<span class=\"do\">Trực tiếp KQXS Miền Nam</span> hàng ngày: Soạn <span class=\"do\">KQ MN</span> gửi <span class=\"do\">8185</span>";
            html += "</div>";
            html += "</div>";
            html += "<div class=\"BannerDV\"><img src=\"images/BannerDV.jpg\"/>";
            html += "</div>";

            html += "<div class=\"XSMN_DAUDUOI\">";
            html += "<div class=\"XSMN_DAUDUOI_table\">";
            html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";
            html += "<tr class=\"XSMN_DAUDUOI_table_row_1\">";
            html += "<td class=\"XSMN_DAUDUOI_table_col_1 do\">Tỉnh</td>";

            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMN.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_DAUDUOI_table_col_" + numSize + " do\">" + token + "</td>";
            }
            html += "</tr>";

            for (int j = 0; j < listDuoiMN.size(); j++) {
                html += "<tr class=\"XSMN_DAUDUOI_table_row_1\">";
                html += "<td class=\"XSMN_DAUDUOI_table_col_1\">Đầu <span class=\"do\">" + j + "</span></td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(listDuoiMN.get(j), "+"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"XSMN_DAUDUOI_table_col_" + numSize + "\">" + token + "</td>";
                }
                html += "</tr>";
            }

            html += "</table>";
            html += "</div>";
            html += "</div>";

            //lotteryResultMN=html;
        }

        if (!"mobile".equals(mobile)) {
            FileTool.saveToFile(html, realNewsFolder + "/xsmn.html");
        } else {
            FileTool.saveToFile(html, realNewsFolder + "/mobile/xsmn.html");
        }
    }

    private void buildBufferHtmlXSMT(String mobile) {
        String html = "";
        if (listLotteryMT != null) {
            int numSize = 4;
            if (numSizeMT == 3) {
                numSize = 2;
            } else if (numSizeMT == 2) {
                numSize = 3;
            }

            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";
            html += "<div class=\"Centre_BANGKQ_title\">";
            html += "KẾT QUẢ XỔ SỐ MIỀN TRUNG  - THỨ " + dayOfWeek + "," + ddmmyyyy;
            html += "</div>";
            html += "<div class=\"Centre_BANGKQ_bg\">";
            html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";

            html += "<tr class=\"XSMN_table_row_1 font_14\">";
            html += "<td class=\"XSMN_table_col_1 do\">Tỉnh</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " do\">" + token + "</td>";
            }
            html += "</tr>";
            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải tám</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getEighth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải bảy</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSeventh(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải sáu</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSixth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải năm</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFifth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải tư</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFourth(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải ba</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getThird(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">";
                for (StringTokenizer stringTokenizer1 = new StringTokenizer(token, "-"); stringTokenizer1.hasMoreTokens();) {
                    String token1 = stringTokenizer1.nextToken();
                    html += token1 + "<br/>";
                }
                html += "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1\">Giải nhì</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSecond(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_1\">";
            html += "<td class=\"XSMN_table_col_1\">Giải nhất</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getFirst(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17\">" + token + "</td>";
            }
            html += "</tr>";

            html += "<tr class=\"XSMN_table_row_2\">";
            html += "<td class=\"XSMN_table_col_1 do\">Đặc Biệt</td>";
            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getSpecial(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_table_col_" + numSize + " font_17 do\">" + token + "</td>";
            }
            html += "</tr>";

            html += "</table>";
            html += "<div class=\"Centre_BANGKQ_table_note\">";
            html += "Nhận <span class=\"do\">KQXS Miền Trung</span> hàng ngày: Soạn <span class=\"do\">KQ MT</span> gửi <span class=\"do\">8185</span><br />";
            html += "<span class=\"do\">Trực tiếp KQXS Miền Trung</span> hàng ngày: Soạn <span class=\"do\">KQ MT</span> gửi <span class=\"do\">8185</span>";
            html += "</div>";
            html += "</div>";
            html += "<div class=\"BannerDV\"><img src=\"images/BannerDV.jpg\"/>";
            html += "</div>";

            html += "<div class=\"XSMN_DAUDUOI\">";
            html += "<div class=\"XSMN_DAUDUOI_table\">";
            html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";
            html += "<tr class=\"XSMN_DAUDUOI_table_row_1\">";
            html += "<td class=\"XSMN_DAUDUOI_table_col_1 do\">Tỉnh</td>";

            for (StringTokenizer stringTokenizer = new StringTokenizer(listLotteryMT.getProvince(), "+"); stringTokenizer.hasMoreTokens();) {
                String token = stringTokenizer.nextToken();
                html += "<td class=\"XSMN_DAUDUOI_table_col_" + numSize + " do\">" + token + "</td>";
            }
            html += "</tr>";

            for (int j = 0; j < listDuoiMT.size(); j++) {
                html += "<tr class=\"XSMN_DAUDUOI_table_row_1\">";
                html += "<td class=\"XSMN_DAUDUOI_table_col_1\">Đầu <span class=\"do\">" + j + "</span></td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(listDuoiMT.get(j), "+"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"XSMN_DAUDUOI_table_col_" + numSize + "\">" + token + "</td>";
                }
                html += "</tr>";
            }

            html += "</table>";
            html += "</div>";
            html += "</div>";

            //lotteryResultMN=html;
        }

        if (!"mobile".equals(mobile)) {
            FileTool.saveToFile(html, realNewsFolder + "/xsmt.html");
        } else {
            FileTool.saveToFile(html, realNewsFolder + "/mobile/xsmt.html");
        }
    }

    private void buildBufferHtmlXSMB(String mobile) {
        String html = "";
        if (listLotteryMB != null && !listLotteryMB.isEmpty()) {
            Lottery lottery = listLotteryMB.get(0);
            html = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />";

            if ("pc".equals(mobile)) {
                html += "<div class=\"Left_BANGKQ_title\">";
                html += "KẾT QUẢ XỔ SỐ MIỀN BẮC  - THỨ " + dayOfWeek + "," + ddmmyyyy;
                html += "</div>";
                html += "<div class=\"Left_background\">";
                html += "<div class=\"Left_BANGKQ_left\">";
                html += "<div class=\"Centre_BANGKQ_bg\">";
                html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";
                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1 do";

                if (dayOfWeek == "3" || dayOfWeek == "6") {
                    html += " rowspan=\"2\"";
                }

                html += "\">ĐẶC BIỆT</td>";

                if (dayOfWeek == "3" || dayOfWeek == "6") {
                    html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                    html += "<td class=\"Centre_BANGKQ_table_col_2 do\" colspan=\"12\">";
                    html += lottery.getSpecial_xsmb();
                    html += "</td>";
                    html += "</tr>";
                }

                html += "<td class=\"Centre_BANGKQ_table_col_2 do\" colspan=\"12\">" + lottery.getSpecial() + "</td>";
                html += "</tr>";
                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">GIẢI NHẤT</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"12\">" + lottery.getFirst() + "</td>";
                html += "</tr>";
                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">GIẢI NHÌ</td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSecond(), "-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"6\">" + token + "</td>";
                }
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td rowspan=\"2\" class=\"Centre_BANGKQ_table_col_1\">GIẢI BA</td>";
                int i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    if (i > 2) {
                        break;
                    }
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"4\">" + token + "</td>";
                }
                html += "</tr>";

                i = 0;
                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i > 2) {
                        html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"4\">" + token + "</td>";
                    }
                }
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">GIẢI TƯ</td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFourth(), "-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"3\">" + token + "</td>";
                }
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td rowspan=\"2\" class=\"Centre_BANGKQ_table_col_1\">GIẢI NĂM</td>";
                i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    if (i > 2) {
                        break;
                    }
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"4\">" + token + "</td>";
                }
                html += "</tr>";

                i = 0;
                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i > 2) {
                        html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"4\">" + token + "</td>";
                    }
                }
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">GIẢI SÁU</td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSixth(), "-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"4\">" + token + "</td>";
                }
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">GIẢI BẢY</td>";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSeventh(), "-"); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    html += "<td class=\"Centre_BANGKQ_table_col_2\" colspan=\"3\">" + token + "</td>";
                }
                html += "</tr>";

                html += "</table>";

                html += "</div>";
                html += "</div>";
                html += "<div class=\"Left_BANGKQ_right\">";
                html += "<div class=\"Centre_DAUDUOI_table\">";
                html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"1\" bgcolor=\"#d1d1d1\">";
                html += "<tr class=\"Centre_DAUDUOI_table_row_1\">";
                html += "<td style=\"text-align:center;";

                if (dayOfWeek == "3" || dayOfWeek == "6") {
                    html += " Centre_DAUDUOI_line_1";
                }
                html += "\">Đầu</td>";

                html += "<td style=\"text-align:left; padding:0 0 0 10px;\">LOTO</td>";
                html += "</tr>";
                for (int j = 0; j < listDuoiMB.size(); j++) {
                    html += "<tr class=\"Centre_DAUDUOI_table_row_2";
                    if (dayOfWeek == "3" || dayOfWeek == "6") {
                        html += " Centre_DAUDUOI_line_3";
                    }
                    html += "\">";

                    html += "<td class=\"Centre_DAUDUOI_table_col_1 do";
                    if (dayOfWeek == "3" || dayOfWeek == "6") {
                        html += " Centre_DAUDUOI_line_1";
                    }
                    html += "\">" + j + "</td>";
                    html += "<td class=\"Centre_DAUDUOI_table_col_2\">" + listDuoiMB.get(j) + "</td>";
                }

                html += "</table>";
                html += "</div>";
                html += "</div>";
                html += "<div class=\"both\"></div>";

                html += "<div class=\"Centre_BANGKQ_table_note\">";
                html += "Nhận <span class=\"do\">KQXS Miền Bắc</span> hàng ngày: Soạn <span class=\"do\">KQ MB</span> gửi <span class=\"do\">8185</span><br />";
                html += "<span class=\"do\">Trực tiếp KQXS Miền Bắc</span> hàng ngày: Soạn <span class=\"do\">KQ MB</span> gửi <span class=\"do\">8185</span>";
                html += "</div>";
                html += "</div>";

                FileTool.saveToFile(html, realNewsFolder + "/xsmb.html");

            } else {
                int i = 0;
                html += "<div class=\"KQXSMB_title\">";
                html += "KẾT QUẢ XỔ SỐ MIỀN BẮC ";
                html += "<p>ngày " + ddmmyyyy + "</p>";
                html += "</div>";
                html += "<div class=\"Left_BANGKQ_left\">";
                html += "<div class=\"Centre_BANGKQ_bg\">";
                html += "<div class=\"Centre_BANGKQ_bg_1\">";
                html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\" bgcolor=\"#d1d1d1\">";
                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1 do";

                if (dayOfWeek == "3" || dayOfWeek == "6") {
                    html += "rowspan=\"2\"";
                }

                html += "\">DB</td>";

                if (dayOfWeek == "3" || dayOfWeek == "6") {
                    html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                    html += "<td class=\"Centre_BANGKQ_table_col_2 do\">";
                    html += "</td>";
                    html += "</tr>";
                }

                html += "<td class=\"Centre_BANGKQ_table_col_2 do\">" + lottery.getSpecial() + "</td>";
                html += "</tr>";
                html += "<tr >";
                html += "<td class=\"Centre_BANGKQ_table_col_1 Centre_BANGKQ_table_bg_1\">G1</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2 Centre_BANGKQ_table_bg_1\">" + lottery.getFirst() + "</td>";
                html += "</tr>";
                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">G2</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\">";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSecond(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i == 0) {
                        html += token + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    } else {
                        html += token;
                    }

                }
                html += "</td>";
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td rowspan=\"2\" class=\"Centre_BANGKQ_table_col_1\">G3</td>";

                i = 0;
                html += "<td rowspan=\"2\" class=\"Centre_BANGKQ_table_col_2\" >";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    if (i > 2) {
                        break;
                    }
                    String token = stringTokenizer.nextToken();
                    if (i < 2) {
                        html += token + "&nbsp;&nbsp;&nbsp;&nbsp;";
                    } else {
                        html += token;
                    }

                }
                i = 0;
                html += "<br />";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i > 2) {
                        if (i < 5) {
                            html += token + "&nbsp;&nbsp;&nbsp;&nbsp;";
                        } else {
                            html += token;
                        }
                    }
                }
                html += "</td>";
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\"></tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">G4</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\">";
                i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFourth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i < 4) {
                        html += token + "&nbsp;&nbsp";
                    } else {
                        html += token;
                    }

                }
                html += "</td>";
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td rowspan=\"2\" class=\"Centre_BANGKQ_table_col_1\">G5</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\" >";
                i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    if (i > 2) {
                        break;
                    }
                    String token = stringTokenizer.nextToken();
                    if (i < 2) {
                        html += token + "&nbsp;&nbsp;&nbsp;&nbsp;";
                    } else {
                        html += token;
                    }

                }

                i = 0;
                html += "<br />";
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i > 2) {
                        if (i < 5) {
                            html += token + "&nbsp;&nbsp;&nbsp;&nbsp;";
                        } else {
                            html += token;
                        }
                    }
                }
                html += "</td>";
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\"></tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">G6</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\" >";
                i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSixth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i < 2) {
                        html += token + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    } else {
                        html += token;
                    }

                }
                html += "</td>";
                html += "</tr>";

                html += "<tr class=\"Centre_BANGKQ_table_bg_1\">";
                html += "<td class=\"Centre_BANGKQ_table_col_1\">G7</td>";
                html += "<td class=\"Centre_BANGKQ_table_col_2\" >";
                i = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lottery.getSeventh(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (i < 3) {
                        html += token + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    } else {
                        html += token;
                    }
                }
                html += "</td>";
                html += "</tr>";

                html += "</table>";

                html += "</div>";
                html += "<div class=\"Centre_BANGKQ_bg_2\">";
                html += "<table width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"0\" bgcolor=\"#d1d1d1\">";
                for (int j = 0; j < listDuoiMB.size(); j++) {
                    if (j == 0) {
                        html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                        html += "<td class=\"Centre_BANGKQ_table_col_3";
                        if (dayOfWeek == "3" || dayOfWeek == "6") {
                            html += " Centre_DAUDUOI_line_3";
                        }
                        html += "\" style=\"text-align:center;\"><strong class=\"do\">" + j + "</strong></td>";
                        html += "<td class=\"Centre_BANGKQ_table_col_4";
                        if (dayOfWeek == "3" || dayOfWeek == "6") {
                            html += " Centre_DAUDUOI_line_4";
                        }
                        html += "\" style=\"text-align:left; padding:0 0 0 5px;\">" + listDuoiMB.get(j) + "</td>";
                        html += "</tr>";
                    } else {
                        html += "<tr class=\"Centre_BANGKQ_table_bg\">";
                        html += "<td class=\"Centre_BANGKQ_table_col_3";
                        if (dayOfWeek == "3" || dayOfWeek == "6") {
                            html += " Centre_DAUDUOI_line_3";
                        }
                        html += "\"><strong class=\"do\">" + j + "</strong></td>";
                        html += "<td class=\"Centre_BANGKQ_table_col_4";
                        if (dayOfWeek == "3" || dayOfWeek == "6") {
                            html += " Centre_DAUDUOI_line_4";
                        }
                        html += "\">" + listDuoiMB.get(j) + "</td>";
                        html += "</tr>";
                    }

                }

                html += "</table>";
                html += "</div>";
                html += "<div class=\"both\"></div>";
                html += "</div>";
                html += "</div>";
                html += "<div class=\"Centre_BANGKQ_table_note\">";
                html += "<span class=\"do\">Trực tiếp KQXS Miền Bắc</span> hàng ngày:<br /> Soạn <span class=\"do\">KQ MB</span> gửi <span class=\"do\">8185</span>";
                html += "</div>";
                FileTool.saveToFile(html, realNewsFolder + "/mobile/xsmb.html");
            }

        }
    }

}
