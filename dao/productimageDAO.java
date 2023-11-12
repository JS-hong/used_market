package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import joongo.util.DBConn;
import joongo.vo.productimageVO;

public class productimageDAO {
	private String query; //쿼리문 저장 필드
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	private boolean result;//DML 처리 결과 여부 저장
public productimageDAO(Connection con) {
	this.con=con;
}
public boolean imageInsert(int pid,String image_url) {
	 try {
         //insert 쿼리문
         query = "INSERT INTO productimage(pimgid,pid,image_url,pidate) VALUES(productimgid_seq.NEXTVAL,?,?,Sysdate)";
         
         pstmt = con.prepareStatement(query);
        
       
         pstmt.setInt(1, pid);
         pstmt.setString(2, image_url);
         		       
         
         int pst = pstmt.executeUpdate();
         if(pst !=0) {
        	 result= true;
         } else {
        	 result= false;
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBConn.close(pstmt);
      }
  return result;
}

public List<productimageVO> imageSelect(int pid) {
	 
	 List<productimageVO> pivoList = new ArrayList<>();
    try {  
        String query = "SELECT * FROM productimage WHERE pid=?";
        pstmt = con.prepareStatement(query);
        pstmt.setInt(1, pid);
        ResultSet rs = pstmt.executeQuery(); // ResultSet을 얻어야 합니다.
        
        while (rs.next()) { // while문을 사용하여 모든 결과를 처리합니다.
            productimageVO pivo = new productimageVO();
            pivo.setPimgid(rs.getInt("pimgid"));
            pivo.setImageurl(rs.getString("image_url"));
            pivoList.add(pivo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        DBConn.close(pstmt);
    }
    return pivoList;
}


public boolean imageDelete(int pid) {//모두 지우고 새로 insert하는방식
	try {
		query="DELETE FROM productimage WHERE pid=?";	
		pstmt=con.prepareStatement(query);
		pstmt.setInt(1, pid);
		int pst = pstmt.executeUpdate();
        if(pst !=0) {
        	result= true;
         } else {
        	 result= false;
         }
	}catch (SQLException e) {
         e.printStackTrace();
      } finally {
         DBConn.close(pstmt);
      }

	return result;
}
}
