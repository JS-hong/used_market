package joongo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import joongo.util.*;
import joongo.vo.ChatVO;

public class ChatDAO {
	private String sql;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	private int result;
	private DataSource dataSource;
	
	public ChatDAO(Connection con) {
		this.con=con;
	}
	public ChatDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public boolean insertChatMessage(String uid,String sid,String message) {//메시지 입력받은거 저장하는거 받을 데이터 3개 필요
		try {
			sql = "insert into participant(participant_id,chat_room_id,user_id,created_at,chat_message) "
					+ "values(participant_seq.nextVal,?,?,SYSTIMESTAMP,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, uid);		    
			pstmt.setString(2, sid);
			pstmt.setString(3, message);
			rs = pstmt.executeQuery();			

			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return true;
	}

	public int room_check(String rid) {
		int cnt = 0;	
		try {
			sql="SELECT count(*) FROM chat_room where ?";	
			pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, rid);
	        pstmt.setString(2, rid);
	        
			rs = pstmt.executeQuery();
			if (rs.next() == true) {// 조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				cnt = rs.getInt(1);
			} else {
				cnt = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}	
		
	return result;
	}
	public void chat_room_update() {//채팅방 이름 변경 
			
	}			
	public void chat_room_delete() {//채팅방 나가기
		List<ChatVO> cvolist = new ArrayList<>();
		ChatVO cvo=null;
		try {
			sql = "update chat_room set " +
					"where first_id = ? OR second_user=? ";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {
				
				cvo=new ChatVO();
				cvo.setChat_room_id(rs.getString("id"));
				cvo.setProduct_id(rs.getInt("num"));
				cvo.setProduct_image(rs.getString("id"));
				cvo.setProduct_status(rs.getString("id"));
				cvo.setFirst_user_id(rs.getString("id"));
				cvo.setSecond_user_id(rs.getString("id")); 
	

				cvolist.add(cvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
	}	
	
	public int totalCount(String mid) {//총 채팅목록 수 빼오기
		int cnt = 0;
		String uid = mid;		
		try {
			sql="SELECT count(*) FROM chat_room where first_user_id=? OR second_user_id=?";	
			pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, mid);
	        pstmt.setString(2, mid);
	        
			rs = pstmt.executeQuery();
			if (rs.next() == true) {// 조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				cnt = rs.getInt(1);
			} else {
				cnt = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return cnt;
	}
	
	public ArrayList<ChatVO> selectChatList(String mid){//로그인 한 사람의 채팅 리스트 가져오기
		ArrayList<ChatVO> cvolist = new ArrayList<>();
		ChatVO cvo=null;
		String uid = mid;
		try {
			sql="SELECT * FROM chat_room where first_user_id=? OR second_user_id=?";	
			pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, uid);
	        pstmt.setString(2, uid);
			
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {
				cvo=new ChatVO();
				cvo.setChat_room_id(rs.getString("chat_room_id"));
				cvo.setProduct_id(rs.getInt("product_id"));
				cvo.setProduct_image(rs.getString("product_image"));
				cvo.setProduct_status(rs.getString("product_status"));
				cvo.setFirst_user_id(rs.getString("first_user_id")); 
				cvo.setSecond_user_id(rs.getString("second_user_id"));
	
				cvolist.add(cvo);
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return cvolist;	
	
	}	
	
	public ArrayList<ChatVO> selectAll(double amount,int pageNum,String mid){
		ArrayList<ChatVO> cvolist=new ArrayList<>();
		ChatVO cvo=null;
		try {
			sql=" SELECT * "
					+ "FROM  (SELECT ROWNUM AS rnum,c.* "
					+ "FROM (SELECT * FROM chat_room "
					+ "where first_user_id=? OR second_user_id=? ";
			
			sql += " ORDER BY create_date DESC ) c "
					+ "       WHERE ROWNUM <=?*?) "
					+ "WHERE rnum>?*?             ";	
			pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, mid);
	        pstmt.setString(2, mid);
			pstmt.setDouble(3,amount);
			pstmt.setInt(4,pageNum);
			pstmt.setDouble(5,amount);
			pstmt.setInt(6,(pageNum-1));
	
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {//조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				cvo=new ChatVO();
				cvo.setChat_room_id(rs.getString("chat_room_id"));
				cvo.setProduct_id(rs.getInt("product_id"));
				cvo.setProduct_image(rs.getString("product_image"));
				cvo.setProduct_status(rs.getString("product_status"));
				cvo.setFirst_user_id(rs.getString("first_user_id")); 
				cvo.setSecond_user_id(rs.getString("second_user_id"));
	
				cvolist.add(cvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return cvolist;
	}

	public ArrayList<ChatVO> message_list(String rid) {//채팅 메세지 가져오는 DAO 
		ArrayList<ChatVO> cvolist=new ArrayList<>();
		ChatVO cvo=null;
		try {
			sql=" SELECT chat_message,user_id from participant where chat_room_id=? ";
			pstmt=con.prepareStatement(sql);
	        pstmt.setString(1, rid);
	        rs=pstmt.executeQuery();			
			while(rs.next()==true) {
				cvo=new ChatVO();
				cvo.setChat_message(rs.getString("chat_message"));
				cvo.setUser_id(rs.getString("user_id"));	
				cvolist.add(cvo);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return cvolist;
	}
}
