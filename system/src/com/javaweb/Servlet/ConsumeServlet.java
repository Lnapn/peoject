package com.javaweb.Servlet;

import com.javaweb.Utils.DBUtil;
import com.javaweb.bean.Bill;
import com.javaweb.bean.Sel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet({"/consume/show","/consume/add","/consume/modify","/consume/select","/consume/del","/consume/alter"})
public class ConsumeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/consume/show".equals(servletPath)){
            doShow(request,response);
        }else if ("/consume/add".equals(servletPath)){
            doAdd(request,response);
        }else if ("/consume/modify".equals(servletPath)){
            doModify(request,response);
        }else if ("/consume/select".equals(servletPath)){
            doSelect(request,response);
        }else if ("/consume/del".equals(servletPath)){
            doDel(request,response);
        }else if ("/consume/alter".equals(servletPath)){
            doAlter(request,response);
        }
    }

    private void doAlter(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("no");
        String date = request.getParameter("date");
        String type = request.getParameter("type");
        String amount = request.getParameter("amount");
        String consumer = request.getParameter("consumer");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "update bill set date = ?,type = ?,amount = ?,consumer = ? where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,date);
            ps.setString(2,type);
            ps.setString(3,amount);
            ps.setString(4,consumer);
            ps.setString(5,no);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/consume/show");
            }else {
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

    private void doDel(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("no");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from bill where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/consume/show");
            }else {
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

    private void doModify(HttpServletRequest request, HttpServletResponse response) {
        List<Bill> bills = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs =null;
        String no = request.getParameter("no");
        int count = 0 ;
        try {
            conn = DBUtil.getConnection();
            String sql = "select date,type,amount,consumer from bill where no = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            rs = ps.executeQuery();
            if (rs.next()){
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String consumer = rs.getString("consumer");
                Bill bill = new Bill(no,date,type,amount,consumer);
                bills.add(bill);
                count++;
            }
            if (count == 1){
                request.setAttribute("Bill",bills);
                request.getRequestDispatcher("/add.jsp").forward(request,response);
            } else {
                response.sendRedirect("/system/manage.jsp");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) {
        String no = request.getParameter("no");
        String date = request.getParameter("date");
        String type = request.getParameter("type");
        String amount = request.getParameter("amount");
        String consumer = request.getParameter("consumer");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.getConnection();
            String sql = "insert into bill (no,date,type,amount,consumer) values (?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,no);
            ps.setString(2,date);
            ps.setString(3,type);
            ps.setString(4,amount);
            ps.setString(5,consumer);
            count = ps.executeUpdate();
            if (count == 1){
                response.sendRedirect("/system/consume/show");
            }else {
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
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bill> bills = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            String sql = "select no,date,type,amount,consumer from bill";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String no = rs.getString("no");
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");
                String consumer = rs.getString("consumer");
                Bill bill = new Bill(no,date,type,amount,consumer);
                bills.add(bill);
            }
            request.setAttribute("Bill",bills);
            request.getRequestDispatcher("/consume.jsp").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }
    private void doSelect(HttpServletRequest request, HttpServletResponse response) {
        String selName = "";
        String selType = "";
        String selYear = null;
        String selMonth = null;
        boolean nameFlag = false;
        boolean typeFlag = false;
        selName = request.getParameter("selname");
        selType = request.getParameter("seltype");
        selYear = request.getParameter("selyear");
        selMonth = request.getParameter("selmonth");
        nameFlag = Sel.judge_Name(selName);
        typeFlag = Sel.judge_Type(selType);
        if (nameFlag && typeFlag && !("".equals(selMonth)) && !("".equals(selYear))){ // 某个人一个月某种类型的消费
            doPersonTypeMonth(request,response,selName,selType,selYear,selMonth);
        }else if (nameFlag &&  !("".equals(selMonth)) && !("".equals(selYear))){
            doPersonMonth(request,response,selName,selYear,selMonth);
        }else if (typeFlag && !("".equals(selMonth)) && !("".equals(selYear))){
            doTypeMonth(request,response,selType,selYear,selMonth);
        }else if (nameFlag && typeFlag && !("".equals(selYear))){ // 某个人一年某种类型的消费
            doPersonTypeYear(request,response,selName,selType,selYear);
        }else if (nameFlag && !("".equals(selYear))){ // 某个人一年的消费
            doPersonYear(request,response,selName,selYear);
        }else if (typeFlag && !("".equals(selYear))) { // 某种类型一年的消费
            doTypeYear(request, response, selType, selYear);
        }
    }

    private void doTypeMonth(HttpServletRequest request, HttpServletResponse response, String selType, String selYear, String selMonth) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Integer.parseInt(selYear),Integer.parseInt(selMonth)-1,1);
        String date1 = sdf.format(cal.getTime());
        cal.add(2,1);
        cal.add(5,-1);
        String date2 = sdf.format(cal.getTime());
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selType);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selType);
            ps.executeUpdate();
            conn.commit();
            String sql2 = "select sum(amount) as sum from bill where type = ? and date between '" + date1 + "' and '" + date2 + "'";
            ps = conn.prepareStatement(sql2);
            ps.setString(1,selType);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("type",selType);
                request.setAttribute("sumMonth",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doPersonMonth(HttpServletRequest request, HttpServletResponse response, String selName, String selYear, String selMonth) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Integer.parseInt(selYear),Integer.parseInt(selMonth)-1,1);
        String date1 = sdf.format(cal.getTime());
        cal.add(2,1);
        cal.add(5,-1);
        String date2 = sdf.format(cal.getTime());
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        String sql2 = "select sum(amount) sum from bill where date between '" + date1 + "' and '" + date2 + "'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selName);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selName);
            ps.executeUpdate();
            conn.commit();
            if (!("Family".equals(selName))){
                sql2 = "select sum(amount) as sum from bill where consumer = ? and date between '" + date1 + "' and '" + date2 + "'";
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selName);
            }else{
                ps = conn.prepareStatement(sql2);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("name",selName);
                request.setAttribute("sumMonth",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doPersonTypeMonth(HttpServletRequest request, HttpServletResponse response, String selName, String selType, String selYear, String selMonth) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.set(Integer.parseInt(selYear),Integer.parseInt(selMonth)-1,1);
        String date1 = sdf.format(cal.getTime());
        cal.add(2,1);
        cal.add(5,-1);
        String date2 = sdf.format(cal.getTime());
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        String sql2 = "select sum(amount) sum from bill where type = ? and date between '" + date1 + "' and '" + date2 + "'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selName);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selName);
            ps.executeUpdate();
            conn.commit();
            if (!("Family".equals(selName))){
                sql2 = "select sum(amount) as sum from bill where consumer = ? and type = ? and date between '" + date1 + "' and '" + date2 + "'";
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selName);
                ps.setString(2,selType);
            }else{
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selType);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("name",selName);
                request.setAttribute("type",selType);
                request.setAttribute("sumMonth",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }


    private void doTypeYear(HttpServletRequest request, HttpServletResponse response, String selType, String selYear) {
        StringBuilder date0 = new StringBuilder();
        date0.append(selYear);
        date0.append("-01-01");
        date0.append(selYear);
        date0.append("-12-31");
        String date1 = date0.toString().substring(0,10);
        String date2 = date0.toString().substring(10,20);
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selType);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selType);
            ps.executeUpdate();
            conn.commit();
            String sql2 = "select sum(amount) as sum from bill where type = ? and date between '" + date1 + "' and '" + date2 + "'";
            ps = conn.prepareStatement(sql2);
            ps.setString(1,selType);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("type",selType);
                request.setAttribute("sumYear",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doPersonTypeYear(HttpServletRequest request, HttpServletResponse response, String selName, String selType, String selYear) {
        StringBuilder date0 = new StringBuilder();
        date0.append(selYear);
        date0.append("-01-01");
        date0.append(selYear);
        date0.append("-12-31");
        String date1 = date0.toString().substring(0,10);
        String date2 = date0.toString().substring(10,20);
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        String sql2 = "select sum(amount) sum from bill where type = ? and date between '" + date1 + "' and '" + date2 + "'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selName);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selName);
            ps.executeUpdate();
            conn.commit();
            if (!("Family".equals(selName))){
                sql2 = "select sum(amount) as sum from bill where consumer = ? and type = ? and date between '" + date1 + "' and '" + date2 + "'";
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selName);
                ps.setString(2,selType);
            }else{
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selType);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("name",selName);
                request.setAttribute("type",selType);
                request.setAttribute("sumYear",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

    private void doPersonYear(HttpServletRequest request, HttpServletResponse response, String selName, String selYear) {
        StringBuilder date0 = new StringBuilder();
        date0.append(selYear);
        date0.append("-01-01");
        date0.append(selYear);
        date0.append("-12-31");
        String date1 = date0.toString().substring(0,10);
        String date2 = date0.toString().substring(10,20);
        String sql1 = "insert into bill (date,type,amount,consumer) values (?,'生活',0,?)";
        String sql2 = "select sum(amount) sum from bill where date between '" + date1 + "' and '" + date2 + "'";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setDate(1, Date.valueOf(date1));
            ps.setString(2,selName);
            ps.executeUpdate();
            ps.setDate(1, Date.valueOf(date2));
            ps.setString(2,selName);
            ps.executeUpdate();
            conn.commit();
            if (!("Family".equals(selName))){
                sql2 = "select sum(amount) as sum from bill where consumer = ? and date between '" + date1 + "' and '" + date2 + "'";
                ps = conn.prepareStatement(sql2);
                ps.setString(1,selName);
            }else{
                ps = conn.prepareStatement(sql2);
            }

            rs = ps.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("sum");
                String sql3 = "delete from bill where date = ?";
                ps = conn.prepareStatement(sql3);
                ps.setString(1,date1);
                ps.execute();
                ps.setString(1,date2);
                ps.execute();
                conn.commit();
                request.setAttribute("name",selName);
                request.setAttribute("sumYear",sum);
            }
            request.getRequestDispatcher("/consume/show").forward(request,response);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
    }

}
