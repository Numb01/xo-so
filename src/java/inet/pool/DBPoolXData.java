package inet.pool;

import java.io.*;
import java.util.*;
import inet.util.*;

public class DBPoolXData {
    //private static final String fileName = "D://dbpool.conf";
    private static  String fileName = DBPoolXData.class.getResource("dbpool.conf").getFile();
    private static InputStream input;
    //Thong tin 1 DB
    private Vector poolnames;
    private ConnectionParameter parameter;

    //Chứa tất cả các DB
    private static Vector poolDataList = new Vector();
    public static Vector getPoolDataList() {
        return poolDataList;
    }
    public ConnectionParameter getParameter() {
      return parameter;
    }
    public void setParameter(ConnectionParameter parameter) {
      this.parameter = parameter;
    }
    public Vector getPoolnames() {
      return poolnames;
    }
    public void setPoolnames(Vector poolnames) {
      this.poolnames = poolnames;
    }
    @Override
	public String toString() {
        return getPoolnames() + " = " + parameter.toString();
    }
    private static boolean loaded = false;
    public static boolean isLoaded() {
        return loaded;
    }
  public static void loadData() {	
        Vector v = new Vector();
        try {
            BufferedReader in = null;
        	if(input!=null){
        		in = new BufferedReader(new InputStreamReader(input));
        	}else{
        		in=new BufferedReader(new FileReader(fileName));
        	}
        	
            String line = in.readLine();
            for (; line != null; line = in.readLine()) {
                line = line.trim();
                if ("".equals(line) || line.startsWith("#")) {
                    continue;
                }         
                //System.out.println(line);
                int idx = line.indexOf("=");
                if (idx <= 0) {
                    System.out.println("   Seperator '=' is NOT found.");
                    continue;
                }

                String name = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();
                //System.out.println("value = " + value);
                if (name == null || name.equals("") ||
                    value == null || value.equals("")) {
                    continue;
                }
                DBPoolXData data = new DBPoolXData();
                data.setPoolnames((Vector) StringTool.parseString(name, ","));
                ConnectionParameter parameter = new ConnectionParameter();

                Collection c = StringTool.parseString(value, ",");
                if (c.size() < 5) {
                    System.out.println("   Invalid DB parameter <driver>,<url>,<user><passEncrypted>,<count>: " + value);
                    continue;
                }
                Iterator it = c.iterator();
                parameter.setDriver( (String) it.next());
                parameter.setUrl( (String) it.next());
                parameter.setUser( (String) it.next());
                String password = (String) it.next();
                parameter.setPassword(password);
                try {
                    parameter.setPassword(Encrypter.decrypt(password));
                } catch (Exception ex) {
                    System.out.println("   DBPoolXData: invalid encrypted password = " + password);
                    ex.printStackTrace();
                }
                try {
                    parameter.setCount(Integer.parseInt( (String) it.next()));
                } catch (Exception ex) {
                }
                //Count = 0 -> 10
                if (parameter.getCount() < 1) parameter.setCount(1);
                if (parameter.getCount() > 10) parameter.setCount(10);

                data.setParameter(parameter);
              
                v.add(data);
            }
            loaded = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        poolDataList.clear();
        poolDataList.addAll(v);
        
    }
      
    public static void main(String[] args) {
    	System.out.println("Filename: " + fileName);
    	System.out.println("encrip:: " + Encrypter.encrypt("weblogic7008"));
        System.out.println("hko9s7sBLin+RcPmEjuFXg==="+Encrypter.decrypt("C67aYOoFMok="));
        DBPoolXData.loadData();
    }
	public static String getFileName() {
		return fileName;
	}
	public static void setFileName(String fileName) {
		DBPoolXData.fileName = fileName;
	}
	public static InputStream getInput() {
		return input;
	}
	public static void setInput(InputStream input) {
		DBPoolXData.input = input;
	}
}

class ConnectionParameter {
    private String driver;
    private String url;
    private String user;
    private String password;//clear text
    private int count; //number of connections

    public void setDriver(String s) {
        this.driver = s;
    }
    public void setUrl(String s) {
        this.url = s;
    }
    public void setUser(String s) {
        this.user = s;
    }
    public void setPassword(String s) {
        this.password = s;
    }
    public void setCount(int s) {
        this.count = s;
    }

    public String getDriver() {
        return this.driver;
    }
    public String getUrl() {
        return this.url;
    }
    public String getUser() {
        return this.user;
    }
    public String getPassword() {
        return this.password;
    }
    public int getCount() {
        return this.count;
    }

    @Override
	public String toString() {
        return "<" + getDriver() + ">,<" + getUrl() +  ">,<" + getUser()+ ">,<***>,<" + getCount() + ">";
    }

    /*public static void main(String[] args) {
        DBPoolXData.loadData();
    }*/
}

