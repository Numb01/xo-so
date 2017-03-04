package inet.pool;

import java.util.*;
import java.sql.*;
import inet.util.*;

public class DBPoolX {
    private static Logger logger = new Logger("DBPoolX");
    public final static int MAX_CONNECTIONS = 5; //max connections per pool
   
    private static Map poolTable = new Hashtable(); //name (String) - pool (LinkedList)
    private static Map dataTable  = new Hashtable(); //name (String) - data (DBPoolXData)
    private static Map dbpoolTable = new Hashtable(); //name (String) - DBPool (DBPoolX)
    
    private int quantity=0;
    private LinkedList pool = null;
    private DBPoolXData data = null;
    private String name = null;
    public String getName() { return name; }

    static {//Make connections
        if (!DBPoolXData.isLoaded()) DBPoolXData.loadData();
        buildPools();
    }

    private DBPoolX(String name, LinkedList pool, DBPoolXData data) {
        this.name = name;
        this.pool = pool;
        this.data = data;
    }
    private DBPoolX(String name, LinkedList pool, DBPoolXData data,int quantity) {
        this.name = name;
        this.pool = pool;
        this.data = data;
        
    }

    public static DBPoolX getInstance(String poolName) throws DBPoolNotFoundException {
        DBPoolX poolX = (DBPoolX) dbpoolTable.get(poolName);
        if (poolX == null) throw new DBPoolNotFoundException("Pool not found: " + poolName);

        return poolX;
    }


    private static void buildPools() {
        DBPoolXData data = null;
        for (int i=0; i < DBPoolXData.getPoolDataList().size(); i++) {
            data = (DBPoolXData) DBPoolXData.getPoolDataList().get(i);
          
            if (data != null) buildPool(data);
        }
    }

    private static void buildPool(DBPoolXData data) {
    	System.out.println("data=="+data.toString());
        try {
            //T?o s? connection ??n 1 DB --> ??a vào 1 pool
            LinkedList pool = new LinkedList();
            Connection conn = null;
            for (int j=0; j < data.getParameter().getCount(); j++) {
                conn = makeDBConnection(data.getParameter());
                
                if (conn != null) pool.addLast(conn);
            }

            
            String poolName = null;
            for (int j=0; j < data.getPoolnames().size(); j++) {
                poolName = (String) data.getPoolnames().get(j);
                poolTable.put(poolName, pool);
                dataTable.put(poolName, data);
                dbpoolTable.put(poolName, new DBPoolX(poolName, pool, data,data.getParameter().getCount()));
                
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.out.println("Khong noi dc voi database roi !");
        }

    }

    // Remove and close all connections in pool
    public static void releaseAll() {
        logger.log("Closing connections in ALL pools... ");

        String poolName = null;
        DBPoolX dbPoolX = null;
        for (Iterator it = dbpoolTable.keySet().iterator(); it.hasNext(); ) {
            poolName = (String) it.next();
            dbPoolX  = (DBPoolX) dbpoolTable.get(poolName);
            dbPoolX.release();
        }
    }
    private java.sql.Connection conn = null;
    public synchronized Connection getConnection() {
        conn = null;
        try {
        	
        	synchronized (pool){
        		if(pool.size()> 0){
           		 	conn = (java.sql.Connection) pool.removeFirst();
                                //System.out.println("pool.size=="+pool.size());
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

    public void putConnection(java.sql.Connection conn) {
        try {

            // Ignore closed connection
            if (conn == null || conn.isClosed()) {
                logger.log("putConnection: conn is null or closed: " + conn);
                return;
            }

            synchronized (pool) {
                if (pool.size() >= MAX_CONNECTIONS) {
                    conn.close();
                    return;
                }
                pool.addLast(conn);
                pool.notify();
            }
        } catch (SQLException ex) {}
    }

    // Remove and close all connections in pool
    public void release() {
        logger.log("Closing connections in pool " + getName());
        synchronized (pool) {
            for (int i = 0; i < pool.size(); i++) {
                conn = (Connection) pool.removeFirst();
                if (conn == null) continue;
                try {
                    conn.close();
                } catch (Exception e) {
                    logger.error( "release: Cannot close connection! (maybe closed?)");
                }
            }
            pool.clear();
            quantity=0;
        }
        
    }

    public int size() {
        synchronized (pool) {
            return pool.size();
        }
    }

    public boolean isEmpty() {
        synchronized (pool) {
            return pool.isEmpty();
        }
    }

    @Override
	public void finalize() {
        logger.log("Pool " + getName() + " called finalize()");
        release();
    }

    //--------------------------------------------------------------------------
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

    public static void main(String args[]) {
     
        try {
//            System.out.println(DBPoolX.getInstance("cache"));
            System.out.println(Encrypter.decrypt("C67aYOoFMok="));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        DBPoolX.releaseAll();
    }
    public static void load() {}

    public void releaseConnection(Connection conn, PreparedStatement preStmt) {
        try {
            if (preStmt != null)  preStmt.close();

            if (conn == null || conn.isClosed()) return;

            synchronized (pool) {
                if (pool.size() >= MAX_CONNECTIONS) {
                    conn.close();
                    return;
                }
                quantity--;
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
