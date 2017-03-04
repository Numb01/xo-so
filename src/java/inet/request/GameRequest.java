/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.request;

import com.google.gson.Gson;
import inet.bean.Base;
import inet.bean.CountRow;
import inet.bean.DuDoanJson;
import inet.bean.LichSuDuDoan;
import inet.bean.ProfileUser;
import inet.bean.ProfileUserJson;
import inet.bean.RankXuanTocDo;
import inet.bean.User;
import inet.bean.UserJson;
import inet.bean.UserProfileJson;
import inet.bean.XuanTocDo;
import inet.util.Contant;
import inet.util.DataEncrypter;
import inet.util.HttpURLRequest;
import java.net.URLEncoder;
import java.util.List;

/**
 *
 * @author hanhlm
 */
public class GameRequest {

    public List<XuanTocDo> parserRankXuanTocDo(String page,String row) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_XUAN_TOC_DO;   
            url=url.replace("PAGE", page);
            url=url.replace("rpp", row);
            String string = HttpURLRequest.sendPost(url,"");
            if (string.contains("status")) {                
                RankXuanTocDo rankXuanTocDo=(RankXuanTocDo)gson.fromJson(string, RankXuanTocDo.class);
                if(rankXuanTocDo.getStatus()==0){return rankXuanTocDo.getContent();}
                else{return  null;}
            }
        } catch (Exception e) {
            
            System.out.println("=======GameRequest=======>>parserRankXuanTocDo=======>>" + e.toString());
        }
        return null;
    }
    
    public List<XuanTocDo> parserTopLaoLang(String page,String row) {
        try {
            Gson gson = new Gson();
            String url = Contant.URL_TOP_LAO_LANG;     
            url=url.replace("PAGE", page);
            url=url.replace("rpp", row);
            String string = HttpURLRequest.sendPost(url,"");
            if (string.contains("status")) {                
                RankXuanTocDo rankXuanTocDo=(RankXuanTocDo)gson.fromJson(string, RankXuanTocDo.class);
                if(rankXuanTocDo.getStatus()==0){return rankXuanTocDo.getContent();}
                else{return  null;}
            }
        } catch (Exception e) {
            
            System.out.println("=======GameRequest=======>>parserTopLaoLang=======>>" + e.toString());
        }
        return null;
    }
    
     public static List<User> getNhanThuong(String page,String row){
        try{
            Gson gson=new Gson();
            String url=Contant.URL_NHAN_THUONG;
            url=url.replace("PAGE", page);
            url=url.replace("rpp", row);
            String string=HttpURLRequest.sendGet(url);
            UserJson userJson=(UserJson)gson.fromJson(string, UserJson.class);
            
            if(userJson.getStatus()==0){
                return userJson.getContent();
            }
        }catch(Exception e){}
        
        return null;
    }
    
    public static int getCountRow(String opt){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_XUAN_TOC_COUNT;
            url=url.replace("CMD", opt);            
            String string=HttpURLRequest.sendGet(url);
            CountRow countRow=(CountRow)gson.fromJson(string, CountRow.class);
            
            if(countRow.getStatus()==0){
                return countRow.getContent();
            }
        }catch(Exception e){}
        
        return 0;
    }
    
    public static int getCountFollow(String userId){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_COUNT_FOLLOW;
            url=url.replace("USERID",userId);            
            String string=HttpURLRequest.sendGet(url);
            System.out.println("string===>>"+string);
            CountRow countRow=(CountRow)gson.fromJson(string, CountRow.class);            
            if(countRow.getStatus()==0){
                return countRow.getContent();
            }
        }catch(Exception e){
            
        }
        
        return 0;
    }
    
    public static int getCountFollower(String userId){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_COUNT_FOLLOWER;
            url=url.replace("USERID",userId);            
            String string=HttpURLRequest.sendGet(url);
            System.out.println("string===>>"+string);
            CountRow countRow=(CountRow)gson.fromJson(string, CountRow.class);            
            if(countRow.getStatus()==0){
                return countRow.getContent();
            }
        }catch(Exception e){
            
        }
        
        return 0;
    }
    
    public static int getCountTransaction(String userId){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_COUNT_TRANSACTION;
            url=url.replace("USERID",userId);            
            String string=HttpURLRequest.sendGet(url);
            System.out.println("string===>>"+string);
            CountRow countRow=(CountRow)gson.fromJson(string, CountRow.class);            
            if(countRow.getStatus()==0){
                return countRow.getContent();
            }
        }catch(Exception e){
            
        }
        
        return 0;
    }
    
    public static ProfileUser register(String dataEncrypt,String data,String appPackage){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_REGISTER;
            url=url.replace("DATAENCRYPT", dataEncrypt);
            url=url.replace("DATA", data);
//            url=url.replace("APPPACKAGE", appPackage);
//            url=url.replace("GAMEID", ""+Contant.GAME_ID);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);
            ProfileUserJson  profileUserJson=(ProfileUserJson)gson.fromJson(string, ProfileUserJson.class);
            if(profileUserJson.getStatus()==0){
                return profileUserJson.getContent();
            }
        }catch(Exception e){}
         
         
        return null; 
    }
    
    public static void getOtp(String dataEncrypt){
         try{
            DataEncrypter dataEncrypter=new DataEncrypter();
            Gson gson=new Gson();
            String url=Contant.URL_GET_OTP;
            url=url.replace("DATAENCRYPT", dataEncrypt);            
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+dataEncrypter.decrypt(string));
            
        }catch(Exception e){
             System.out.println("e==="+e.toString());
        }
         
   }
    
   public static String changePassword(String dataEncrypt){
         try{
            DataEncrypter dataEncrypter=new DataEncrypter();
            Gson gson=new Gson();
            String url=Contant.URL_CHANGE_PASSWORD;
            url=url.replace("DATAENCRYPT", dataEncrypt);            
            String string=HttpURLRequest.sendGet(url);    
            string =dataEncrypter.decrypt(string);
            System.out.println("String "+string);
            Base base=(Base)gson.fromJson(string, Base.class);
            if(base.getStatus()==0){
                return base.getDesc();
            }
            
        }catch(Exception e){
             System.out.println("e==="+e.toString());
        }
        
         return null;
   }
    
    public static ProfileUser login(String dataEncrypt,String data,String appPackage){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_LOGIN;
            url=url.replace("DATAENCRYPT", dataEncrypt);
            url=url.replace("DATA", data);
//            url=url.replace("APPPACKAGE", appPackage);
//            url=url.replace("GAMEID", ""+Contant.GAME_ID);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);
            ProfileUserJson  profileUserJson=(ProfileUserJson)gson.fromJson(string, ProfileUserJson.class);
            if(profileUserJson.getStatus()==0){
                return profileUserJson.getContent();
            }
        }catch(Exception e){}
         
         
        return null; 
    }
    
    public static ProfileUser loginFace(String data){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_LOGIN_FACE;
            url=url.replace("DATA", data);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);
            ProfileUserJson  profileUserJson=(ProfileUserJson)gson.fromJson(string, ProfileUserJson.class);
            if(profileUserJson.getStatus()==0){
                return profileUserJson.getContent();
            }
        }catch(Exception e){}
         
         
        return null; 
    }
    
    public static User getProfile(String userId){
         try{
            Gson gson=new Gson();
            String url=Contant.URL_GET_PROFILE;
            url=url.replace("USERID", userId);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);
            UserProfileJson userJson  =(UserProfileJson)gson.fromJson(string, UserProfileJson.class);
            if(userJson.getStatus()==0){
                return userJson.getContent();
            }
        }catch(Exception e){
             System.out.println("loi excep "+e.toString());
        }
         
         
        return null; 
    }
    
    public static void updateProfile(int inetId,String userId,String fullname,String email,String cfullname){
         try{
            DataEncrypter dataEncrypter=new DataEncrypter();
            ProfileUser profileUser=new ProfileUser();   
            profileUser.setId(inetId);
            Gson gson=new Gson();            
            String data=dataEncrypter.dataEncrypt(userId+"|"+gson.toJson(profileUser)+"|123456789");
            String url=Contant.URL_UPDATE_PROFILE;
            url=url.replace("USERID", userId);
            url=url.replace("CFULLNAME",cfullname);
            url=url.replace("FULLNAME",fullname);
            url=url.replace("EMAIL", email);      
            url=url.replace("DATAENCRYPT", URLEncoder.encode(data ));      
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);            
        }catch(Exception e){
             System.out.println("loi excep "+e.toString());
        }
                 
    }
    
    public static void updatePass(String data){
         try{                                      
            DataEncrypter dataEncrypter=new DataEncrypter();         
            Gson gson=new Gson();
            String url=Contant.URL_UPDATE_PASS;
            url=url.replace("DATA", data);              
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+dataEncrypter.decrypt(string));   
            //System.out.println("String "+string);   
        }catch(Exception e){            
             System.out.println("loi excep "+e.toString());
        }
                 
    }
    
    public static void getHistoryTransaction(String userId,String eventId,String page,String rowPage,String date){
        try{                                      
            Gson gson=new Gson();
            String url=Contant.URL_HISTORY_TRANSACTION;
            url=url.replace("USERID",userId);
            url=url.replace("EVENTID",eventId);            
            url=url.replace("ROWPAGE",rowPage);
            url=url.replace("PAGE",page);
            url=url.replace("DATE",date);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);   
        }catch(Exception e){            
             System.out.println("loi excep "+e.toString());
        }
    }
    
    public static List<User> getFollow(String userId,String page,String rowPage){
        try{                                      
            Gson gson=new Gson();
            String url=Contant.URL_FOLLOW;
            url=url.replace("USERID",userId);
            url=url.replace("ROW",rowPage);
            url=url.replace("PAGE",page);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);   
            UserJson userJson=(UserJson)gson.fromJson(string, UserJson.class);
            if(userJson.getStatus()==0){
                return userJson.getContent();
            }
        }catch(Exception e){            
             System.out.println("loi excep "+e.toString());
        }
        
        return null;
    }
    
    public static List<User> getFollower(String userId,String page,String rowPage){
        try{                                      
            Gson gson=new Gson();
            String url=Contant.URL_FOLLOWER;
            url=url.replace("USERID",userId);
            url=url.replace("ROW",rowPage);
            url=url.replace("PAGE",page);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);   
            UserJson userJson=(UserJson)gson.fromJson(string, UserJson.class);
            if(userJson.getStatus()==0){
                return userJson.getContent();
            }
        }catch(Exception e){            
             System.out.println("loi excep "+e.toString());
        }
        
        return null;
    }
    
    
    public static LichSuDuDoan getDuDoan(String userId,String fId,String eId){
        try{                                      
            Gson gson=new Gson();
            String url=Contant.URL_DU_DOAN_CAO_THU;
            url=url.replace("USERID",userId);
            url=url.replace("FID",fId);
            url=url.replace("EID",eId);
            String string=HttpURLRequest.sendGet(url);    
            System.out.println("String "+string);   
            DuDoanJson duDoanJson=(DuDoanJson)gson.fromJson(string, DuDoanJson.class);
            if(duDoanJson.getStatus()==0){
                return duDoanJson.getContent();
            }
        }catch(Exception e){            
             System.out.println("loi excep "+e.toString());
        }
        return null;
    }
    
    public static void main(String[] args) {
        GameRequest gameRequest=new GameRequest();
//        List<XuanTocDo> list=gameRequest.parserTopLaoLang();
//        if(list==null){System.out.println("null");}
//        else{System.out.println(list.size());}
//        try{
//            String string = HttpURLRequest.sendPost(Contant.URL_TOP_LAO_LANG,"");
//            
//        }catch(Exception e){}
    }
}
