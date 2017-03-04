/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.pool;

import inet.util.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author hanhlm
 */
public  class DBPoolX2 {
    private static Logger logger = new Logger("DBPoolX");
    private final static int MAX_CONNECTIONS = 5; //max connections per pool
    private static LinkedList pool = null;
    private DBPoolXData data = null;
    
//    static {//Make connections
//        System.out.println("b1");
//        if (!DBPoolXData.isLoaded()) DBPoolXData.loadData();
//        buildPools();        
//    }
    
    private static void buildPools() {        
        DBPoolXData data = null;
        for (int i=0; i < DBPoolXData.getPoolDataList().size(); i++) {
            data = (DBPoolXData) DBPoolXData.getPoolDataList().get(i);          
            if (data != null){
                System.out.println("b2");
                int start=0;                
                if(pool!=null){
                    //System.out.println("pool.size=="+pool.size());
                    if(pool.size()<MAX_CONNECTIONS){
                        start=MAX_CONNECTIONS-pool.size();
                    }else{start=MAX_CONNECTIONS;}                    
                }                
                for(int p=start;p<MAX_CONNECTIONS;p++){       
                    System.out.println("data=="+data.toString());
                    buildPool(data);
                }                
            }
        }
    }
    
    private static void buildPool(DBPoolXData data) {    	
        try {
            //T?o s? connection ??n 1 DB --> ??a vào 1 pool            
            Connection conn = null;
            //for (int j=0; j < data.getParameter().getCount(); j++) {
                conn = makeDBConnection(data.getParameter());                
                if (conn != null){
                    if(pool==null){pool=new LinkedList();}
                    pool.addLast(conn);
                    System.out.println("pool==="+pool.size());
                }
                    
            //}
                        
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Khong noi dc voi database roi !");
        }

    }
    
    public synchronized Connection getConnection() {
        Connection conn = null;
        try {        	
        	synchronized (this){
                    if(pool==null){
                        if (!DBPoolXData.isLoaded()) DBPoolXData.loadData();
                        buildPools();  
                    }

                    if(pool.size()> 0){
                        //System.out.println("pool.size=="+pool.size());
                            conn = (java.sql.Connection) pool.removeFirst();                                
                    }else{
                            conn = makeDBConnection(this.data.getParameter());
                    }
        	}
        	if(conn.isClosed()){
        		pool.remove(conn);
        		conn=null;
        	}
        	while (conn == null){
            	conn = makeDBConnection(this.data.getParameter());
            }
           
        } catch (Exception ex) {
            System.out.println("getConnection: " + ex.getMessage());
            ex.printStackTrace();
            try { Thread.sleep(100); } catch (Exception ex2) { }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                } else {
                    Thread.sleep(1000);
                }
            } catch (Exception ex) { }
        }
        return conn;
    }
    
    
    private static Connection makeDBConnection(ConnectionParameter param) throws SQLException {
        Connection conn = null;
        try {
            Class.forName(param.getDriver());
            conn = DriverManager.getConnection(param.getUrl(), param.getUser(), param.getPassword());
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public void releaseConnection(Connection conn, PreparedStatement preStmt) {
        try {
            if (preStmt != null)  preStmt.close();

            if (conn == null || conn.isClosed()) return;

            synchronized (pool) {
                if (pool.size() >= MAX_CONNECTIONS) {
                    conn.close();
                    return;
                }
                pool.addLast(conn);
                pool.notify();
            }
        } catch (SQLException e) {}
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {}

        releaseConnection(conn, preStmt);
    }

    public void releaseConnection(Connection conn, PreparedStatement preStmt, Statement stmt, ResultSet rs) {
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {}

        releaseConnection(conn, preStmt, rs);
    }
}
