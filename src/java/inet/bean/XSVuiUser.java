/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

/**
 *
 * @author hanm
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public class XSVuiUser {

    private BigDecimal id;
    private String mobile;
    private String username;
    private String password;
    private int displayType;
    private int status;
    private Timestamp genDate;
    private Timestamp lastUpdate;
    private Timestamp lastLogin;
    private BigDecimal leftCoin;
    private BigDecimal lastCoin;
    private BigDecimal tempCoin;
    private Timestamp lastTrans;
    private String hashCode;
    private int follow;
    private int followed;
    private BigDecimal percentLo;
    private BigDecimal percentDe;
    private BigDecimal maxDayProfit;
    private BigDecimal maxDayCoin;
    private List<XSVuiMaxDayResult> maxDayResultList;
    private int rank;
    private String mobiShort;

    public String getMobiShort()
    {
      if ((this.mobile != null) && (!"".equals(this.mobile))) {
        this.mobiShort = (this.mobile.substring(0, this.mobile.length() - 3) + "xxx");
      }
      return this.mobiShort;
    }
    private String lo;
    private int point_lo=0;

    public int getPoint_lo() {
        return point_lo;
    }

    public void setPoint_lo(int point_lo) {
        this.point_lo = point_lo;
    }

    public int getPoint_de() {
        return point_de;
    }

    public void setPoint_de(int point_de) {
        this.point_de = point_de;
    }
    private int point_de=0;
    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }
    private String de;
    public XSVuiUser() {
    }
    
    public BigDecimal calcMaxDayCoin(){
        BigDecimal result = BigDecimal.ZERO;
        if(maxDayResultList != null && maxDayResultList.size() > 0){
            int size = maxDayResultList.size();
            XSVuiMaxDayResult maxDayResult = null;
            List<XSVuiChotSoResult> chotSoResultList = null;
            int chotSoSize = 0;
            XSVuiChotSoResult chotSoResult = null;
            
            for(int i = 0; i < size; i++){
                maxDayResult = maxDayResultList.get(i);
                chotSoResultList = maxDayResult.getResultList();
                
                if(chotSoResultList != null && chotSoResultList.size() > 0){
                    chotSoSize = chotSoResultList.size();
                    for(int j = 0; j < chotSoSize; j++){
                        chotSoResult = chotSoResultList.get(j);
                        result = result.add(new BigDecimal(chotSoResult.getReceiveCoin()));
                    }
                }
            }
        }
        return result;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getGenDate() {
        return genDate;
    }

    public void setGenDate(Timestamp genDate) {
        this.genDate = genDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public BigDecimal getLeftCoin() {
        return leftCoin;
    }

    public void setLeftCoin(BigDecimal leftCoin) {
        this.leftCoin = leftCoin;
    }

    public BigDecimal getLastCoin() {
        return lastCoin;
    }

    public void setLastCoin(BigDecimal lastCoin) {
        this.lastCoin = lastCoin;
    }

    public BigDecimal getTempCoin() {
        return tempCoin;
    }

    public void setTempCoin(BigDecimal tempCoin) {
        this.tempCoin = tempCoin;
    }

    public Timestamp getLastTrans() {
        return lastTrans;
    }

    public void setLastTrans(Timestamp lastTrans) {
        this.lastTrans = lastTrans;
    }

    public String getHashCode() {
        return hashCode;
    }

    public void setHashCode(String hashCode) {
        this.hashCode = hashCode;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public int getFollowed() {
        return followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public BigDecimal getPercentLo() {
        return percentLo;
    }

    public void setPercentLo(BigDecimal percentLo) {
        this.percentLo = percentLo;
    }

    public BigDecimal getPercentDe() {
        return percentDe;
    }

    public void setPercentDe(BigDecimal percentDe) {
        this.percentDe = percentDe;
    }

    public BigDecimal getMaxDayProfit() {
        return maxDayProfit;
    }

    public void setMaxDayProfit(BigDecimal maxDayProfit) {
        this.maxDayProfit = maxDayProfit;
    }

    public BigDecimal getMaxDayCoin() {
        return maxDayCoin;
    }

    public void setMaxDayCoin(BigDecimal maxDayCoin) {
        this.maxDayCoin = maxDayCoin;
    }

    public List<XSVuiMaxDayResult> getMaxDayResultList() {
        return maxDayResultList;
    }

    public void setMaxDayResultList(List<XSVuiMaxDayResult> maxDayResultList) {
        this.maxDayResultList = maxDayResultList;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    
}
