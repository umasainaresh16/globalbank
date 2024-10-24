package bankapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registeration
 */
@WebServlet("/Registeration")
public class Registeration extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    /**
	     * Default constructor. 
	     */
	    public Registeration() {
	        // TODO Auto-generated constructor stub
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    public static long accountno() {
	    	long x = (long)(Math.random() * 9_000_000_000L) + 1_000_000_000L;
	    	return x;
	    }
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			String firstname=request.getParameter("firstname");
			String lastname=request.getParameter("lastname");
			String username=request.getParameter("username");
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			int pin=Integer.parseInt(request.getParameter("pin"));
			String address=request.getParameter("address");
			Long phoneno=Long.parseLong(request.getParameter("phoneno"));
			String district=request.getParameter("district");
			String state=request.getParameter("state");
			int pincode=Integer.parseInt(request.getParameter("pincode"));
			String dateStr=request.getParameter("dob");
			try {
				Date parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
	            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cvcorp","root","1234");
				PreparedStatement ps=con.prepareStatement("insert into bank(firstname,lastname,username,email,password,pin,address,phoneno,district,state,pincode,bod) values(?,?,?,?,?,?,?,?,?,?,?,?)");
				ps.setString(1,firstname);
				ps.setString(2,lastname);
				ps.setString(3,username);
				ps.setString(4,email);
				ps.setString(5,password);
				ps.setInt(6, pin);
				ps.setString(7,address);
				ps.setLong(8,phoneno);
				ps.setString(9,district);
				ps.setString(10,state);
				ps.setInt(11, pincode);
				ps.setDate(12,sqlDate);
				ps.executeUpdate();
				long accno=accountno();
				boolean acnumber=false;
				PreparedStatement ps1=con.prepareStatement("select accountno from bank");
				ResultSet rs1=ps1.executeQuery();
				while(rs1.next()) {
					long dbaccountno=rs1.getLong(1);
					if(dbaccountno==accno) {
						acnumber=false;
						break;
					}
					else
						acnumber=true;
				}
				if(acnumber==true) {
					PreparedStatement ps2=con.prepareStatement("update bank set accountno=? where username=?");
					ps2.setLong(1, accno);
					ps2.setString(2,username);
					ps2.executeUpdate();
				}
				else {
					
				}
				response.setContentType("text/html");
				response.sendRedirect("registrationcomplete.html");
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
