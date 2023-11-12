package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import joongo.util.DBConn;
import joongo.vo.categoryVO;

public class categoryDAO {
	private String query; //쿼리문 저장 필드
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	private boolean result;//DML 처리 결과 여부 저장

	public categoryDAO(Connection con) {
		this.con=con;
	}
	
	public List<categoryVO> categoryselect(){
		List<categoryVO> categorylist=new ArrayList<>();
		categoryVO cvo=null;
		try {
			query="SELECT * FROM category ";	
			pstmt=con.prepareStatement(query);
			
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {//조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				cvo=new categoryVO();
				cvo.setCid(rs.getInt("cid"));
				cvo.setCname(rs.getString("cname"));


				//List객체에 추가
				categorylist.add(cvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return categorylist;
	}
	
	public String categoryname(int cid) {
		String cname=null;
		try {
			query="SELECT cname FROM category WHERE cid=?";	
			
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,cid);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cname=rs.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return cname;
	}
}
