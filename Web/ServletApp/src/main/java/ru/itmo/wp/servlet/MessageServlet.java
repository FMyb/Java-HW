package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Yaroslav Ilin
 */

public class MessageServlet extends HttpServlet {
    private final List<Message> messages = Collections.synchronizedList(new ArrayList<>());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String json;
        String user = "";
        switch (uri) {
            case "/message/auth":
                if (request.getParameter("user") != null) {
                    user = request.getParameter("user");
                    session.setAttribute("user", user);
                } else if (session.getAttribute("user") != null) {
                    user = session.getAttribute("user").toString();
                }
                json = new Gson().toJson(user);
                response.getWriter().print(json);
                response.getWriter().flush();
                break;
            case "/message/findAll":
                json = new Gson().toJson(getMessages());
                response.getOutputStream().write(json.getBytes(StandardCharsets.UTF_8));
                response.getWriter().flush();
                break;
            case "/message/add":
                user = session.getAttribute("user").toString();
                if (user.isEmpty()) {
                    response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                }
                messages.add(new Message(user, request.getParameter("text")));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        response.setContentType("application/json");
    }

    private synchronized List<Message> getMessages() {
        return new ArrayList<>(messages);
    }

    private static class Message {
        private final String user;
        private final String text;

        private Message(String user, String text) {
            this.user = user;
            this.text = text;
        }

        @Override
        public String toString() {
            return "{\"user\":\"" + user + "\",\"text\":\"" + text + "\"}";
        }
    }
}
