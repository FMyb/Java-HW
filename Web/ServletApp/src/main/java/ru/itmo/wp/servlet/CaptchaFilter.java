package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * @author Yaroslav Ilin
 */

public class CaptchaFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Object loginCaptcha = session.getAttribute("login_captcha");
        if (loginCaptcha == null) {
            if (request.getMethod().equals("GET")) {
                int captchaVal = (int) (Math.random() * 900) + 100;
                session.setAttribute("captcha_val", Integer.toString(captchaVal));
                session.setAttribute("login_captcha", "in_process");
                response.setContentType("text/html");
                response.getWriter().print(getCaptchaForm(captchaVal));
                response.getWriter().flush();
            }
        } else if (loginCaptcha.toString().equals("in_process")) {
            if (request.getMethod().equals("GET")) {
                String captchaVal = request.getParameter("captcha_val");
                if (captchaVal != null) {
                    if (captchaVal.equals(session.getAttribute("captcha_val").toString())) {
                        session.setAttribute("login_captcha", "complete");
                        response.sendRedirect(request.getRequestURI());
                        return;
                    } else {
                        session.setAttribute("captcha_val", Integer.toString((int) (Math.random() * 900) + 100));
                    }
                }
                response.setContentType("text/html");
                response.getWriter().print(getCaptchaForm(Integer.parseInt(session.getAttribute("captcha_val").toString())));
                response.getWriter().flush();
            }
        } else if (loginCaptcha.toString().equals("complete")) {
            super.doFilter(request, response, chain);
        }
    }

    private static String getCaptchaForm(int captchaVal) {
        String png = Base64.getEncoder().encodeToString(ImageUtils.toPng(Integer.toString(captchaVal)));
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<form>\n" +
                "  <img src=\"data:image/jpg;base64," + png + "\"> </br>\n" +
                "  <input name=\"captcha_val\">\n" +
                "</form> \n" +
                "Write right captcha" +
                "</body>\n" +
                "</html>";
    }
}
