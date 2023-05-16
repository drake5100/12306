package cn.edu.guet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TicketServlet", value = "/TicketServlet")
public class TicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        /*
        Tomcat把数据封装到HttpServletRequest对象中的时候，自动把编码转变了，所以需要我们重新转变回来
         */

        System.out.println("余票查询");
        String fromStation = request.getParameter("fromStation");
        String toStation = request.getParameter("toStation");
        String departureDate = request.getParameter("departureDate");

        System.out.println("起始站:"+fromStation);
        System.out.println("终点站:"+toStation);
        System.out.println("出发日期:"+departureDate);

        String allTickets=TicketSearch.search(fromStation,toStation,departureDate);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(allTickets);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }
}
