package listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener()
public class ServletRequestListener implements javax.servlet.ServletRequestListener {
    private ServletContext context = null;
    private static long reqNumber;

    String clientIP = "";
    String serverName = "";
    String url;
    String dateTime = new java.util.Date().toString();

    Connection con = null;
    Statement stmt;

    String username = "root";
    String password = "123456";
    String database = "jdbc:mysql://localhost/ListenerDb";

    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        this.context = sre.getServletContext();
        clientIP = request.getRemoteAddr();
        serverName = request.getServerName();
        synchronized (context) {
            if (request instanceof HttpServletRequest)
                url = ((HttpServletRequest)request).getRequestURI();
        }
        String query = "INSERT INTO Stats VALUES('" + clientIP + "', '" + serverName + "', '" + url + "', '" + dateTime + "', '" + (++reqNumber) + "', 'Initialized')";
        addToDb(query);
    }

    public void requestInitialized(ServletRequestEvent sre) {
        String query = "INSERT INTO Stats VALUES('" + clientIP + "', '" + serverName + "', '" + url + "', '" + dateTime + "', '" + reqNumber + "', 'Destroyed')";
        addToDb(query);
    }

    public void addToDb(String query) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(database, username, password);
            if (con != null) {
                stmt = con.createStatement();
                stmt.executeUpdate(query);
            }
        }
        catch(Exception e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
