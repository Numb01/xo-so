/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.util;

import java.util.Properties;
import javax.mail.internet.InternetAddress;
import vn.util.MailUtil;

/**
 *
 * @author 1st.max
 */
public class MailUntil {
    public static int sentMail(String email, String title, String content) {
        MailUtil mailUtil = new MailUtil();
        int result = 0;
        try {
            Properties properties = mailUtil.loadProperties(null, "/inet/mail/config/combat.properties");
            System.out.println("User:" + properties.getProperty("mail.smtp.user"));
            InternetAddress[] internetAddress = new InternetAddress[1];
            internetAddress[0] = new InternetAddress("hotro.xsmb.vn@gmail.com");
            String from = "tungnx@inet.vn";
            result = mailUtil.sendMail(from, internetAddress, null, title, content);
            if (result == 1) {
                System.out.println("gui mail thanh cong");
            } else {
                System.out.println("gui mail that bai");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
