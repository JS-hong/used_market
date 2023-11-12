package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import joongo.util.DBConn;
import joongo.vo.AddrVO;
import joongo.vo.MemberVO;

public class AddrDAO {
	private Connection con;
	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private boolean result;

	public AddrDAO() {
		super();
		con = DBConn.getConnection();
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public AddrVO select(String id) {
		AddrVO avo = null;
	    try {   
	        query = "SELECT * FROM addr WHERE mid = ?";
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, id);
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	        	avo = new AddrVO();
	            avo.setMid(rs.getString("MID"));
	            avo.setAddr(rs.getString("ADDR"));
	            avo.setAddr2(rs.getString("ADDR2"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DBConn.close(pstmt, rs);
	    }
	    return avo;   
	}
	
	public boolean update(AddrVO avo) throws SQLException {
	    try {
	        query = "UPDATE addr SET addr2 = ? WHERE mid = ?";
	        
	        String[] address = avo.getAddr2().split(" ");
	        String city = address[0];
	        String district = address[1];
	        String detail = address[2];

	        String fullAddress = city + " " + district + " " + detail;

	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, fullAddress);
	        pstmt.setString(2, avo.getMid());

	        int result = pstmt.executeUpdate();
	        return result > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null)
	            pstmt.close();
	    }
	    return false;
	}

}
