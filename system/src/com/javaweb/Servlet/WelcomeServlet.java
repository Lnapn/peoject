package com.javaweb.Servlet;

import com.javaweb.Utils.DBUtil;
import com.javaweb.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = 0;
        Cookie[] cookies = request.getCookies();
        String user = null;
        String password = null;
        String phone = null;
        String age = null;
        if (cookies != null){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("user".equals(name)){
                    user = cookie.getValue();
                }
                if ("password".equals(name)){
                    password = cookie.getValue();
                }
            }
        }

        if (user != null && password != null){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            boolean flag = false;
            try {
                conn = DBUtil.getConnection();
                String sql = "select no,phone,age from family where fname = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,user);
                ps.setString(2,password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    flag = true;
                    String no1 = rs.getString("no");
                    no = Integer.parseInt(no1);
                    phone = rs.getString("phone");
                    age = rs.getString("age");
                }
                if (flag){
                    HttpSession session = request.getSession();
                    User login = new User(no,user,password,phone,age);
                    session.setAttribute("User",login);
                    response.sendRedirect("/system/manage.jsp");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
        }else {
            response.sendRedirect("/system/login.jsp");
        }
    }
}
