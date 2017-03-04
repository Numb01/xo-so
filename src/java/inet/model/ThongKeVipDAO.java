/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.LotteryResult;
import inet.bean.ThongKeVip;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class ThongKeVipDAO {
    private Logger logger = null;
    private DBPoolX poolX = null;
    private List<LotteryResult> listLottery=null;

    public ThongKeVipDAO() {
        logger = new Logger(this.getClass().getName());
        try {
            poolX = DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public ThongKeVip findThongKeVip(String code,String ddmmyyyy){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        
        try{            
            conn=poolX.getConnection();
            sql="SELECT DAU,DUOI,VIP,XIEN,VE_NHIEU,VE_IT,TO_CHAR(GEN_DATE,'DD/MM/YYYY'),CODE" +
            		" FROM LOTTERY_TKVIP WHERE UPPER(CODE) = ? AND TO_CHAR(GEN_DATE,'DD/MM/YYYY')=?";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setString(2, ddmmyyyy);
            rs=ps.executeQuery();
            if(rs.next()){
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
            System.out.println("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return thongKeVip;
    }
    
    public List<ThongKeVip> findThongKeVip(String code,int numrow){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        ThongKeVip thongKeVip=null;
        String sql="";
        List<ThongKeVip> list=null;
//        sdate=sdate+" 00:00:00";
//        edate=edate+" 23:59:59";
        try{
            conn=poolX.getConnection();
            sql="SELECT DAU,DUOI,VIP,XIEN,VE_NHIEU,VE_IT,TO_CHAR(GEN_DATE,'DD/MM/YYYY'),CODE FROM "+
               "(SELECT DAU,DUOI,VIP,XIEN,VE_NHIEU,VE_IT,GEN_DATE ,CODE,ROW_NUMBER() OVER(ORDER BY GEN_DATE DESC) r" +
            		" FROM LOTTERY_TKVIP WHERE UPPER(CODE) = ? ) WHERE r < ? ORDER BY GEN_DATE DESC";
            
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);
            ps.setInt(2, numrow);
            rs=ps.executeQuery();
            while(rs.next()){
                
                if(list==null){list=new ArrayList<ThongKeVip>();}
                
                thongKeVip=new ThongKeVip();
                thongKeVip.setDau(rs.getString(1));
                thongKeVip.setDuoi(rs.getString(2));
                thongKeVip.setVip(rs.getString(3));
                thongKeVip.setXien(rs.getString(4));
                thongKeVip.setVenhieu(rs.getString(5));
                thongKeVip.setVeit(rs.getString(6));
                thongKeVip.setGen_date(rs.getString(7));                
                thongKeVip.setCode(rs.getString(8));                
                
                list.add(thongKeVip);
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi exp "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    
}
