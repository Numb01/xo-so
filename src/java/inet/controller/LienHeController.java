/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.controller;

import inet.util.MailUntil;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import vn.util.MailUtil;

/**
 *
 * @author Duynv
 */
public class LienHeController extends BaseController {

    private String resultMail = null;

    public LienHeController() {
    }

    @Override
    protected void loadBase() {
        super.loadBase(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mod = super.handleRequestInternal(request, response);

        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int result = 0;
        if (email != null && title != null && content != null) {
            MailUntil.sentMail(email, title, content);
        }

        mod.addObject("result", result);
        return mod;

    }

}
