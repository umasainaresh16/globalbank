package bankapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class moneywithdraw
 */
@WebServlet("/moneywithdraw")
public class moneywithdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public moneywithdraw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		double money=Double.parseDouble(request.getParameter("dmoney"));
		PrintWriter pw=response.getWriter();
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp","root","1234");
		HttpSession session1=request.getSession(false);
		String uname=(String)session1.getAttribute("username");
		int pin=(int)session1.getAttribute("pin");
		PreparedStatement ps1=con.prepareStatement("select balance from bank where username='"+uname+"'");
		ResultSet rs=ps1.executeQuery();
		double mon=0;
		while(rs.next()) {
		mon=rs.getDouble(1);
		}
		mon=mon-money;
			if(mon>0) {
				PreparedStatement ps=con.prepareStatement("update bank set balance =? where username=? and pin=?");
				ps.setDouble(1, mon);
				ps.setString(2, uname);
				ps.setInt(3, pin);
				ps.executeUpdate();
				response.sendRedirect("transationcomplete.html");;
		}
			else {
				pw.print("<!DOCTYPE html>\r\n"
						+ "<html lang=\"en\">\r\n"
						+ "<head>\r\n"
						+ "    <meta charset=\"UTF-8\">\r\n"
						+ "    <title>Insufficient Funds</title>\r\n"
						+ "    <style>\r\n"
						+ "        body {\r\n"
						+ "            font-family: Arial, sans-serif;\r\n"
						+ "            background-color: #f0f0f0;\r\n"
						+ "            text-align: center;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .container {\r\n"
						+ "            display: flex;\r\n"
						+ "            justify-content: center;\r\n"
						+ "            align-items: center;\r\n"
						+ "            height: 100vh;\r\n"
						+ "            flex-direction: column;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .links {\r\n"
						+ "            margin-top: 20px;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .links a {\r\n"
						+ "            text-decoration: none;\r\n"
						+ "            color: #007bff;\r\n"
						+ "            padding: 10px 20px;\r\n"
						+ "            margin: 5px;\r\n"
						+ "            border: 1px solid #007bff;\r\n"
						+ "            border-radius: 5px;\r\n"
						+ "            display: block;\r\n"
						+ "            width: 150px;\r\n"
						+ "            transition: background-color 0.3s, color 0.3s;\r\n"
						+ "        }\r\n"
						+ "\r\n"
						+ "        .links a:hover {\r\n"
						+ "            background-color: #007bff;\r\n"
						+ "            color: white;\r\n"
						+ "        }\r\n"
						+ "    </style>\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "    <div class=\"container\">\r\n"
						+ "        <p>Insufficient Funds</p>\r\n"
						+ "        <div class=\"links\">\r\n"
						+ "            <a href=\"Bankhome.html\">Home</a>\r\n"
						+ "            <br><br>\r\n"
						+ "            <a href=\"balance.html\">View Balance</a>\r\n"
						+ "            <br><br>\r\n"
						+ "            <a href=\"index.html\">Log out</a>\r\n"
						+ "        </div>\r\n"
						+ "    </div>\r\n"
						+ "</body>\r\n"
						+ "</html>\r\n"
						+ "");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
