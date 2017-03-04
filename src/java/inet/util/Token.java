/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author hanhlm
 */
public class Token {
    public static String getToken(){
        String result=null;
        try{
            result=AESEncryption.encrypt(Contant.DOMAIN_URL, Contant.TOKEN);        
            result=URLEncoder.encode( result, "UTF-8");
        }catch(Exception e){
            System.out.println("token===>>"+e.toString());
        }
                
        return result;
    }
    
    public void parserParam(String i,String u,String f,String e ,String m,String v,String g,String b,String d ){
        if(i!=null&&!"".equals(i)){this.i=AESEncryption.decrypt(i, Contant.TOKEN);}
        if(u!=null&&!"".equals(u)){this.u=AESEncryption.decrypt(u, Contant.TOKEN);}
        if(f!=null&&!"".equals(f)){this.f=AESEncryption.decrypt(f, Contant.TOKEN);}
        if(e!=null&&!"".equals(e)){this.e=AESEncryption.decrypt(e, Contant.TOKEN);}
        if(m!=null&&!"".equals(m)){this.m=AESEncryption.decrypt(m, Contant.TOKEN);}
        if(v!=null&&!"".equals(v)){this.v=AESEncryption.decrypt(v, Contant.TOKEN);}
        if(g!=null&&!"".equals(g)){this.g=AESEncryption.decrypt(g, Contant.TOKEN);}
        if(b!=null&&!"".equals(b)){this.b=AESEncryption.decrypt(b, Contant.TOKEN);}
        if(d!=null&&!"".equals(d)){this.d=AESEncryption.decrypt(d, Contant.TOKEN);}
    }
            
        
    
    private String i=null;

    public String getI() {
        return i;
    }

    public String getU() {
        return u;
    }

    public String getF() {
        return f;
    }

    public String getE() {
        return e;
    }

    public String getM() {
        return m;
    }

    public String getV() {
        return v;
    }

    public String getG() {
        return g;
    }

    public String getB() {
        return b;
    }

    public String getD() {
        return d;
    }
    private String u=null;
    private String f=null;
    private String e=null;
    private String m=null;
    private String v=null;
    private String g=null;
    private String b=null;
    private String d=null;
    
    public static void main(String[] args) {
        //Token.parserParam("QQcgZdj2jiiazWi8sXmAXQ%3D%3D%0A", u, f, e, m, v, g, b, d);
        
        System.out.println("iiiiiiiii============>>>>"+AESEncryption.decrypt("QQcgZdj2jiiazWi8sXmAXQ==", Contant.TOKEN));
    }
}
