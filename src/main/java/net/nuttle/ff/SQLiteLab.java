package net.nuttle.ff;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteLab {
  public static void main( String args[] ) throws SQLException
  {
    Connection c = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      if (!isTableExists(c)) {
        createTable(c);
        createRecord(c);
      }
      getRecord(c);
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    } finally {
      if (c != null) {
        c.close();
      }
    }
    System.out.println("Opened database successfully");
  }
  
  public static boolean isTableExists(Connection c) {
    Statement stmt = null;
    String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='SCHEDULE'";
    try {
      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      return rs.next();
    } catch (SQLException e) {
      //Should log this to Log4j or similar, not just print stacktrace to console
      e.printStackTrace();
    }
    return false;
  }
  
  public static void createTable(Connection c) {
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "CREATE TABLE SCHEDULE " +
        "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
        " FEEDTIME DATETIME NOT NULL)";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void createRecord(Connection c) {
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "INSERT INTO SCHEDULE (FEEDTIME) VALUES('2016-09-06 10:00:00')";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  public static void getRecord(Connection c) {
    Statement stmt = null;
    try {
      stmt = c.createStatement();
      String sql = "SELECT FEEDTIME FROM SCHEDULE;";
      ResultSet rs = stmt.executeQuery(sql);
      String d = rs.getString(1);
      System.out.println(d);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
