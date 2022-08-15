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
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/user/show","/user/login","/user/add","/user/del","/user/modify","/user/alter","/user/exit"})
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/user/login".equals(servletPath)){
            doLogin(request,response);
        }else if ("/user/show".equals(servletPath)){
            doShow(request,response);
        }else if ("/user/add".equals(servletPath)){
            doAdd(request,response);
        }else if ("/user/del".equals(servletPath)){
            doDel(request,response);
        }else if ("/user/modify".equals(servletPath)){
            doModify(request,response);
        }else if ("/user/alter".equals(servletPath)){
            doAlter(request,response);
        }else if ("/user/exit".equals(servletPath)){
            doExit(request,response);
        }
    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/system");
                response.addCookie(cookie);
            }
        }
        if (session != null){
            session.removeAttribute("User");
            session.invalidate();
        }
        response.sendRedirect("/system/welcome");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) {
        int no = 0;
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String phone = null;
        String age = null;
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
                String no1 = rs.getString("no");
                no = Integer.parseInt(no1);
                phone = rs.getString("phone");
                age = rs.getString("age");
                flag = true;
            }
            if (flag){
                HttpSession session = request.getSession();
                User login = new User(no,user,password,phone,age);
                session.setAttribute("User",login);
                String free = request.getParameter("free");
                if ("1".equals(free)){
                    Cookie cookie1 = new Cookie("user",user);
                    Cookie cookie2 = new Cookie("password",password);
                    cookie1.setMaxAge(60 * 60 * 24 * 10);
                    cookie2.setMaxAge(60 * 60 * 24 * 10);
                    cookie1.setPath("/system");
                    cookie2.setPath("/system");
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }
                response.sendRedirect("/system/manage.jsp");
            }else {
                response.sendRedirect("/system/welcome");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doAlter(HttpServletRequest request, HttpServletResponse response) {
        String no1 = request.getParameter("no");
        int no = Integer.parseInt(no1);
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String age = request.getParameter("age");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update Family set fname = ?,phone = ?,age = ? where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,phone);
            ps.setString(3,age);
            ps.setInt(4,no);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/user/show");
            }else {
                response.sendRedirect("/system/welcome.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doModify(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String no1 = request.getParameter("no");
        int no = Integer.parseInt(no1);
        List<User> users = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select password,fname,phone,age from family where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            rs = ps.executeQuery();
            if (rs.next()){
                String fname = rs.getString("fname");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String age = rs.getString("age");
                User user = new User(no,fname,password,phone,age);
                users.add(user);
                request.setAttribute("Users",users);
                request.getRequestDispatcher("/userAdd.jsp").forward(request,response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) {
        String no1 = request.getParameter("no");
        int no = Integer.parseInt(no1);
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from family where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/user/show");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) {
        String no1 = request.getParameter("no");
        int no = Integer.parseInt(no1);
        String user = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String age = request.getParameter("age");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into family (no,fname,password,phone,age) values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,no);
            ps.setString(2,user);
            ps.setString(3,password);
            ps.setString(4,phone);
            ps.setString(5,age);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/user/show");
            }else{
                response.sendRedirect("/system/manage.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
    }

    private void doShow(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select no,fname,password,phone,age from family order by no asc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("no");
                String fname = rs.getString("fname");
                String password = request.getParameter("password");
                String phone = rs.getString("phone");
                String age = rs.getString("age");
                User user = new User(no,fname,password,phone,age);
                users.add(user);
            }
            request.setAttribute("Users",users);
            request.getRequestDispatcher("/family.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
