package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import joongo.util.DBConn;

public class favoriteproductDAO {
	
	private String query; //쿼리문 저장 필드
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	private boolean result;//DML 처리 결과 여부 저장

	public favoriteproductDAO(Connection con) {
		this.con=con;
	}
	
	public int favorcnt(int pid) {//관심도-> 이 게시물에대해 몇명이 좋아요를 눌럿는가
		int cnt=0;
		  try{
	        	query = "SELECT COUNT(*) FROM favoriteproduct WHERE pid=?";
	        	
	        	pstmt = con.prepareStatement(query);

	            pstmt.setInt(1, pid);
	            rs = pstmt.executeQuery(); 
	             if (rs.next()) {
	                   cnt = rs.getInt(1);
	                   		
	                }
	        }catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConn.close(pstmt,rs);
			}
		  return cnt;
	}
	public int myfavorcnt(String mid) {//그 사람의 좋아하는 게시물 숫자
		int cnt=0;
		try{
			query = "SELECT COUNT(*) FROM favoriteproduct WHERE mid=?";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, mid);
			rs = pstmt.executeQuery(); 
			if (rs.next()) {
				cnt = rs.getInt(1);
				
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return cnt;
	}
	
	 public boolean isFavoriteProduct(String mid, int pid) {//있으면 true , 없으면 false
	      
	        try{
	        	query = "SELECT COUNT(*) FROM favoriteproduct WHERE mid=? AND pid=?";
	        	
	        	pstmt = con.prepareStatement(query);
	        	
	            pstmt.setString(1, mid);
	            pstmt.setInt(2, pid);
	            rs = pstmt.executeQuery(); 
	             if (rs.next()) {
	                   int count = rs.getInt(1);
	                   		result = count>0;
	                }
	        }catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConn.close(pstmt,rs);
			}
	        
	        return result;
	    
	 }
	    // 내 관심목록에 해당 상품을 추가하는 메서드
	    public boolean addFavoriteProduct(String memberId, int productId) {//다시 해야됨
	        
	        try {
	        	query = "INSERT INTO favoriteproduct (fpnum,mid, pid) VALUES (favoriteid_seq.NEXTVAL,?, ?)";
	        	PreparedStatement pstmt = con.prepareStatement(query) ;
	            pstmt.setString(1, memberId);
	            pstmt.setInt(2, productId);
	            int pst = pstmt.executeUpdate();
	            if(pst !=0) {
		        	 result= true;
		         } else {
		        	 result= false;
		         }
	        }catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConn.close(pstmt,rs);
			}
	        return result;
	    }

	    // 내 관심목록에서 해당 상품을 삭제하는 메서드
	   public boolean removeFavoriteProduct(String memberId, int productId)  {
	       
	        try {
	        	 query = "DELETE FROM favoriteproduct WHERE mid=? AND pid=?";
	        	PreparedStatement pstmt = con.prepareStatement(query);
	            pstmt.setString(1, memberId);
	            pstmt.setInt(2, productId);
	            int pst = pstmt.executeUpdate();
	            if(pst !=0) {
		        	 result= true;
		         } else {
		        	 result= false;
		         }
	        }catch (SQLException e) {
				e.printStackTrace();
			}finally {
				DBConn.close(pstmt,rs);
			}
	        return result;
	    }

	}

