package bankapp;

import java.io.IOException;
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
 * Servlet implementation class moneydeposit
 */
@WebServlet("/moneydeposit")
public class moneydeposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public moneydeposit() {
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
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp","root","1234");
		HttpSession session=request.getSession(false);
		String uname=(String)session.getAttribute("username");
		int pin=(int)session.getAttribute("pin");
		PreparedStatement ps1=con.prepareStatement("select balance from bank where username='"+uname+"'");
		ResultSet rs=ps1.executeQuery();
		double mon=0;
		while(rs.next()) {
		mon=rs.getDouble(1);
		}
		money=money+mon;
		PreparedStatement ps=con.prepareStatement("update bank set balance =? where username=? and pin=?");
		ps.setDouble(1, money);
		ps.setString(2, uname);
		ps.setInt(3, pin);
		ps.executeUpdate();
		response.sendRedirect("transationcomplete.html");
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
