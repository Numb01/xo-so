/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.model;

import inet.bean.LotteryCompany;
import inet.pool.DBPoolX;
import inet.pool.DBPoolXName;
import inet.util.Logger;
import inet.util.UTF8Tool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author iNET
 */
public class LotteryCompanyDAO{

    private Logger logger=null;
    private DBPoolX poolX=null;

    public LotteryCompanyDAO() {
        try{
            logger=new Logger(this.getClass().getName());
            poolX=DBPoolX.getInstance(DBPoolXName.SERVICE_LOTTERY);
        }catch(Exception e){}
    }
    
    public Collection findCompaniesOpenToday() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        Vector result = null;
        LotteryCompany lotteryCompany=null;
        try {
            conn = poolX.getConnection();
            Calendar cal = Calendar.getInstance();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//1: CN, 2: T2,...
            String sDayOfWeek = (dayOfWeek == 1) ? "CN": ("" + dayOfWeek);
            strSQL =
                "SELECT CODE, NAME, REGION FROM LOTTERY_COMPANY " +
                "WHERE OPEN_DAYS LIKE '%" + sDayOfWeek + "%'";
            preStmt = conn.prepareStatement(strSQL);
            rs = preStmt.executeQuery();
            result = new Vector();
            while (rs.next()) {
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setCode(rs.getString(1));
                lotteryCompany.setCompany(rs.getString(2));
                lotteryCompany.setRegion(rs.getString(3));
                result.addElement(lotteryCompany);
            }
        } catch (SQLException e) {
            System.out.println("findCompaniesOpenToday: Error executing SQL " + strSQL + ">>>" +e.toString());
        } finally {
             poolX.releaseConnection(conn, preStmt, rs);
            
        }return result;
    }
    
    public Collection findCompaniesOpenToday(String region) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        ResultSet rs = null;
        String strSQL = null;
        LotteryCompany lotteryCompany=null;
        Vector result = null;
        try {
            conn = poolX.getConnection();

            Calendar cal = Calendar.getInstance();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);//1: CN, 2: T2,...
            String sDayOfWeek = (dayOfWeek == 1) ? "CN": ("" + dayOfWeek);

            strSQL =
                "SELECT CODE, NAME, REGION FROM LOTTERY_COMPANY " +
                "WHERE OPEN_DAYS LIKE '%" + sDayOfWeek + "%' AND REGION = UPPER(?) ORDER BY CODE ASC";
            preStmt = conn.prepareStatement(strSQL);
           // System.out.println("strSQL123="+strSQL);
            preStmt.setString(1, region);
            rs = preStmt.executeQuery();
            result = new Vector();
            LotteryCompany lottCom = null;
            while (rs.next()) {
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setCode(rs.getString(1));
                lotteryCompany.setCompany(rs.getString(2));
                lotteryCompany.setRegion(rs.getString(3));
                lotteryCompany.setCodeLowerCase(lotteryCompany.getCode().toLowerCase());
                lotteryCompany.setCodeReverseLowerCase(lotteryCompany.getCode().replace("XS", "SX").toLowerCase());
                lotteryCompany.setCompanyLink(UTF8Tool.coDau2KoDau(lotteryCompany.getCompany()).replaceAll("[^A-Za-z0-9-]", "-").toLowerCase());
                result.addElement(lotteryCompany);
            }
        } catch (SQLException e) {
            logger.error("findCompaniesOpenToday: Error executing SQL " + strSQL + ">>>" +e.toString());
        } finally {
        	poolX.releaseConnection(conn, preStmt, rs);
            
        }
        return result;
    }
    
    public List<LotteryCompany> findLotteryCompany(String regoin) {
        List<LotteryCompany> list=null;
        
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        LotteryCompany lotteryCompany=null;
        try{
            conn=poolX.getConnection();
            if("all".equals(regoin)){
                sql="select code,name,region,open_days from LOTTERY_COMPANY order by name asc";
                ps=conn.prepareStatement(sql);
            }else{
                sql="select code,name,region,open_days from LOTTERY_COMPANY where region=?  order by name asc";
                ps=conn.prepareStatement(sql);
                ps.setString(1, regoin);
            }
                        
            rs=ps.executeQuery();
            list=new ArrayList<LotteryCompany>();
            while(rs.next()){
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setCode(rs.getString(1));
                lotteryCompany.setCompany(rs.getString(2));
                lotteryCompany.setRegion(rs.getString(3));
                lotteryCompany.setOpendate(rs.getString(4));
                lotteryCompany.setCodeLowerCase(lotteryCompany.getCode().toLowerCase());
                lotteryCompany.setCodeReverseLowerCase(lotteryCompany.getCode().replace("XS", "SX").toLowerCase());
                lotteryCompany.setCompanyLink(UTF8Tool.coDau2KoDau(lotteryCompany.getCompany()).replaceAll("[^A-Za-z0-9-]", "-").toLowerCase());
                list.add(lotteryCompany);
            }
        }catch(SQLException e){
            logger.error("loi sql "+e.toString());
            System.out.println("loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi ngoai le "+e.toString());
            System.out.println("loi ngoai le "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    

    public LotteryCompany findCompany(String openDate,String regoin) {
        System.out.println("opendate --"+openDate+"--"+regoin);
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        LotteryCompany lotteryCompany=null;
        try{            
            conn=poolX.getConnection();            
            sql="select code,name,region from LOTTERY_COMPANY where upper(OPEN_DAYS) like upper('%"+openDate+"%') and region=? order by region asc";
            ps=conn.prepareStatement(sql);            
            ps.setString(1, regoin);
            rs=ps.executeQuery();            
            if(rs.next()){
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setCode(rs.getString(1));
                lotteryCompany.setCompany(rs.getString(2));
            }
        }catch(SQLException e){
            logger.error("[findCompanyByDay]loi sql "+e.toString());
            System.out.println("[findCompanyByDay]loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi ngoai le "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return lotteryCompany;
    }
    
    public LotteryCompany findCompanyCode(String code) {
        
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        LotteryCompany lotteryCompany=null;
        try{            
            conn=poolX.getConnection();            
            sql="select OPEN_DAYS,region from LOTTERY_COMPANY where  upper(code)=upper(?) ";
            ps=conn.prepareStatement(sql);            
            ps.setString(1, code);
            rs=ps.executeQuery();            
            if(rs.next()){
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setOpendate(rs.getString(1));
                lotteryCompany.setRegion(rs.getString(2));
            }
        }catch(SQLException e){
            logger.error("[findCompanyByDay]loi sql "+e.toString());
            System.out.println("[findCompanyByDay]loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi ngoai le "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return lotteryCompany;
    }
    
    public String findCompanyName(String code) {
        
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        String result="";
        try{
            conn=poolX.getConnection();
            sql="select name from LOTTERY_COMPANY where code=? order by region asc";
            ps=conn.prepareStatement(sql);
            ps.setString(1, code);            
            rs=ps.executeQuery();            
            if(rs.next()){
                result=rs.getString(1);
            }
        }catch(SQLException e){
            logger.error("[findCompanyByDay]loi sql "+e.toString());
            System.out.println("[findCompanyByDay]loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi ngoai le "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return result;
    }
    
    public List<LotteryCompany> findCompanyByDay(String openDate) {
        List<LotteryCompany> list=null;
        
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql="";
        LotteryCompany lotteryCompany=null;
        try{
            conn=poolX.getConnection();
            sql="select code,name,region from LOTTERY_COMPANY where OPEN_DAYS like '%"+openDate+"%' order by region asc";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            list=new ArrayList<LotteryCompany>();
            while(rs.next()){
                lotteryCompany=new LotteryCompany();
                lotteryCompany.setCode(rs.getString(1));
                lotteryCompany.setCompany(rs.getString(2));
                lotteryCompany.setRegion(rs.getString(3));
                list.add(lotteryCompany);
            }
        }catch(SQLException e){
            logger.error("[findCompanyByDay]loi sql "+e.toString());
            System.out.println("[findCompanyByDay]loi sql "+e.toString());
        }catch(Exception e){
            logger.error("loi ngoai le "+e.toString());
        }finally{
            poolX.releaseConnection(conn, ps, rs);
        }
        
        return list;
    }
    
    public static void main(String[] arg){
        LotteryCompanyDAO companyDAO=new LotteryCompanyDAO();
//        LotteryCompany lotteryCompany=companyDAO.findCompany("cn", "MT");
//        if(lotteryCompany!=null){System.out.println("khac null");}
        List<LotteryCompany> list= companyDAO.findLotteryCompany("MN");
        if(list!=null){
            System.out.println("do dai 123 "+list.size());          
            for(int i=0;i<list.size();i++){
                System.out.println("code "+list.get(i).getCode()+"--company name -- "+list.get(i).getCompany()+"----open date --"+list.get(i).getOpendate());
            }
        }else{
            System.out.println("null");
        }
    }
    
}
