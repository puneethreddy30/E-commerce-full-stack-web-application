import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class All 
{
	HttpServletRequest req;
	PrintWriter out;
	public String url,user;
	HttpSession session; 
	//String usernam = session.getAttribute("username").toString();

	public All(HttpServletRequest req, PrintWriter out) 
	{
		this.req = req;
		this.out = out;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}

	public void includeHtml(String ip)
	{
		String username;
		String op = ConvertHtml(ip);
		if (ip == "site_header.html") 
		{
			if (session.getAttribute("username")!=null)
			{
				username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				op = op
						
						+ "<li><a href='Account'><b>Hello ,"+username+"</b></a></li>"
						+ "<li><a href='logout'>Logout</a></li>"
						+ "<li><a href='track'>Track order</a></li>"
						+ "<li><a href='mreviews'>My Reviews</a></li>";
			}
			else 
			{
				op = op + "<li><a href='Login'>Login</a></li><li><a href='Register'>Register</a></li>";
			}
			 op = op
					+ "<li><a href='cart1'>Cart</a></li><li><a href='ser1.html'>Search</a></li></ul></div></nav>";
			out.print(op);
		} else
			out.print(op);
	}
	

	public String getFullURL() 
	{
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);
		if ((serverPort != 80) && (serverPort != 443)) 
		{
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}


	public String ConvertHtml(String ip) 
	{
		String op = null;
		try {
			String webPage = url + ip;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			op = sb.toString();
		} catch (Exception e) {
		}
		return op;
	}
	
	public String forSession()
	{
	String ser=session.getAttribute("username").toString();
	return ser;
	}
}
