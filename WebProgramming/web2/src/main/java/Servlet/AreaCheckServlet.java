package Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/check")
public class AreaCheckServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String x = req.getParameter("x");
        String y = req.getParameter("y");
        String r = req.getParameter("r");

        boolean res = check(x,y,r);
        HttpSession session = req.getSession();
        String date = String.valueOf(session.getAttribute("startTime"));
        if (session.getAttribute("resultList") == null){
            session.setAttribute("resultList", new ArrayList<String>());
        }
        ArrayList<String> resultList = (ArrayList<String>) session.getAttribute("resultList");
        String result;
        if(res) {
            result = "Попал";
        } else {
            result = "Не попал";
        }
        resultList.add(generateRow(x, y, r, date, result));
        session.setAttribute("resultList", resultList);
        resp.sendRedirect("resultTable.jsp");

    }

    private boolean check(String X, String Y, String R) {
        double x = Double.parseDouble(X);
        double y = Double.parseDouble(Y);
        double r = Double.parseDouble(R);
        if(x*x + y*y <= (r*r)/4) {
            return true;
        } else if(r==(y-x)/2) {
            return true;
        } else if(-y<=r&&x/2>=-r) {
            return true;
        } else {
            return false;
        }
    }

    private String generateRow(String x, String y, String r, String startTime, String result){
        return "<tr>\n" +
                "            <td class=\"result-table-td\">" + x + "</td>\n" +
                "            <td class=\"result-table-td\">" + y + "</td>\n" +
                "            <td class=\"result-table-td\">" + r + "</td>\n" +
                "            <td class=\"result-table-td\">\n"+ startTime + "</td>\n" +
                "            <td class=\"result-table-td last-element-of-row\">" + result + "</td>\n" +
                "        </tr>";
    }
}
