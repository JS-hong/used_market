package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import joongo.util.DBConn;
import joongo.vo.productVO;



public class productDAO {
	private String query; //쿼리문 저장 필드
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Connection con;
	private boolean result;//DML 처리 결과 여부 저장

	//기본생성자 - con 객체 초기화
	public productDAO(Connection con) {
		this.con=con;
}
	//상품 등록(완료)
	public boolean productInsert(productVO pvo) {
		
		  try {
		         //insert 쿼리문
		         query = "INSERT INTO product(pid,seller_id,buyer_id, cid,price, ptitle, pcontent,plocation,pdate,thumbnail) VALUES(productid_seq.CURRVAL,?,null,?,?,?,?,?,SYSDATE, NVL(?,'default.png'))";
		         
		         pstmt = con.prepareStatement(query);
		        
		         pstmt.setString(1, pvo.getSellerid());
		         pstmt.setInt(2, pvo.getCid());
		         pstmt.setInt(3, pvo.getPrice());
		         pstmt.setString(4, pvo.getPtitle());
		         pstmt.setString(5, pvo.getPcontent());
		         pstmt.setString(6, pvo.getPlocation());
		         pstmt.setString(7, pvo.getThumbnail());
		         	         
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
	//상품 리스트  검색+페이징(검색?)
	public List<productVO> productSelectAll(double amount,int pageNum,int category,String keyword,String location){//완
		List<productVO> pvolist=new ArrayList<>();
		productVO pvo=null;
		try {
			query="SELECT *         "
					+ "FROM  (SELECT ROWNUM AS rnum,p.*"
					+ "       FROM (SELECT * FROM product where 1=1 ";
			if (category>0) {//전체-> 0으로
			    query += "AND cid = " + category; // 카테고리 검색 조건 추가
			}
			if (!location.equals("")) {
			    query += "AND plocation = '" + location+"'"; // 지역 검색 조건 추가
			}
			if (!keyword.equals("")) {
			    query += "AND (ptitle LIKE '%" + keyword + "%' OR pcontent LIKE '%" + keyword + "%') "; // 키워드 검색 조건 추가
			}
			query +="	ORDER BY pid DESC ) p"
					+ "       WHERE ROWNUM <=?*?)"
					+ "WHERE rnum>?*?             ";	
			pstmt=con.prepareStatement(query);
			
			
	
			pstmt.setDouble(1,amount);
			pstmt.setInt(2,pageNum);
			pstmt.setDouble(3,amount);
			pstmt.setInt(4,(pageNum-1));
			
			
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {//조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				pvo=new productVO();
				pvo.setPid(rs.getInt("pid"));
				pvo.setSellerid(rs.getString("seller_id"));
				pvo.setBuyerid(rs.getString("buyer_id"));
				pvo.setPstate(rs.getInt("pstate"));
				pvo.setPtitle(rs.getString("ptitle"));
				pvo.setPlocation(rs.getString("plocation"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setViewcnt(rs.getInt("viewcnt"));
				pvo.setChatcnt(rs.getInt("chatcnt"));
				pvo.setThumbnail(rs.getString("thumbnail"));
				//List객체에 추가
				pvolist.add(pvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		
		return pvolist;
		}
	//관심 리스트  페이징
	public List<productVO> favorProductSelectAll(double amount,int pageNum,String mid){//완 
		List<productVO> pvolist=new ArrayList<>();
		productVO pvo=null;
		try {
			query="SELECT p.*,c.cname FROM product p "
					+ "INNER JOIN favoriteproduct fp ON p.pid = fp.pid "
					+ "INNER JOIN category c ON p.cid = c.cid "
					+ "WHERE fp.mid = ? "
					+ "ORDER BY fp.fpdate DESC ";	
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, mid);
			
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {//조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				pvo=new productVO();
				pvo.setPid(rs.getInt("pid"));
				pvo.setSellerid(rs.getString("seller_id"));
				pvo.setPtitle(rs.getString("ptitle"));
				pvo.setPlocation(rs.getString("plocation"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setViewcnt(rs.getInt("viewcnt"));
				pvo.setChatcnt(rs.getInt("chatcnt"));
				pvo.setThumbnail(rs.getString("thumbnail"));
				pvo.setCname(rs.getString("cname"));
				//List객체에 추가
				pvolist.add(pvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return pvolist;
	}
	//나의 판매,구매 리스트  페이징
	public List<productVO> buySellSelectAll(double amount,int pageNum,String type,String mid){//여기서 type은 판매인지 구매인지(buy or sell?)
		List<productVO> pvolist=new ArrayList<>();
		productVO pvo=null;
		try {
			query="SELECT * FROM  (SELECT ROWNUM AS rnum,p.* FROM (SELECT product.*, category.cname FROM product JOIN category ON product.cid = category.cid WHERE 1=1 ";
			
			if (type.equals("buy")) {
			    query += "AND buyer_id = '"+mid+"'" ;
			}
			if (type.equals("sell")) {
				query += "AND seller_id = '"+mid+"'" ;
			}
			query +="	ORDER BY pid DESC ) p WHERE ROWNUM <=?*?) WHERE rnum>?*?             ";	
			pstmt=con.prepareStatement(query);
		
			pstmt.setDouble(1,amount);
			pstmt.setInt(2,pageNum);
			pstmt.setDouble(3,amount);
			pstmt.setInt(4,(pageNum-1));
			rs=pstmt.executeQuery();			
			while(rs.next()==true) {//조회된 레코드가 있다면 MemberVo 객체 생성하여 해당 레코드 값 저장
				pvo=new productVO();
				pvo.setPid(rs.getInt("pid"));
				pvo.setCid(rs.getInt("cid"));
				pvo.setSellerid(rs.getString("seller_id"));
				pvo.setBuyerid(rs.getString("buyer_id"));
				pvo.setPstate(rs.getInt("pstate"));
				pvo.setPtitle(rs.getString("ptitle"));
				pvo.setPlocation(rs.getString("plocation"));
				pvo.setPrice(rs.getInt("price"));
				pvo.setThumbnail(rs.getString("thumbnail"));
				pvo.setCname(rs.getString("cname"));
				
				//List객체에 추가
				pvolist.add(pvo);
			}
			} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConn.close(pstmt,rs);
		}
		return pvolist;
	}
		

	//제품 상세보기(완료)
	public productVO productSelect(int pid) {
		productVO pvo = null; // pds 변수를 try 블록 외부에서 선언하여 접근 범위를 확장합니다.
	       try {  
	           String query = "SELECT p.*,c.cname FROM product p LEFT JOIN category c ON p.cid=c.cid WHERE p.pid=?";
	           pstmt = con.prepareStatement(query);
	           pstmt.setInt(1, pid);
	           ResultSet rs = pstmt.executeQuery(); // ResultSet을 얻어야 합니다.
	           
	           if (rs.next()) { // rs.next()를 사용하여 결과가 있는지 확인합니다.
	               pvo = new productVO();
	               pvo.setPid(rs.getInt("pid"));
	               pvo.setSellerid(rs.getString("seller_id"));
	               pvo.setBuyerid(rs.getString("buyer_id"));
	               pvo.setPtitle(rs.getString("ptitle"));
	               pvo.setPcontent(rs.getString("pcontent"));
				   pvo.setPlocation(rs.getString("plocation"));
				   pvo.setPrice(rs.getInt("price"));
				   pvo.setViewcnt(rs.getInt("viewcnt"));
				   pvo.setChatcnt(rs.getInt("chatcnt"));
				   pvo.setThumbnail(rs.getString("thumbnail"));
				   pvo.setCname(rs.getString("cname"));
				   pvo.setCid(rs.getInt("cid"));
				   pvo.setPstate(rs.getInt("pstate"));
	           }
	       } catch (SQLException e) {
	           e.printStackTrace();
	       } finally {
	           DBConn.close(pstmt);
	       }
	       return pvo;
	}
	//제품 삭제
	public boolean productDelete(int pid) {//완

		try {
			query="DELETE FROM product WHERE pid=?";	
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
	//제품 업데이트
	public boolean productUpdate(productVO pvo) {
		try {
			query="UPDATE product SET ptitle=?,cid=?,price=?,pcontent=?,plocation=?,thumbnail=? WHERE pid=?";	
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,pvo.getPtitle());
			pstmt.setInt(2, pvo.getCid());
			pstmt.setInt(3, pvo.getPrice());
			pstmt.setString(4, pvo.getPcontent());
			pstmt.setString(5, pvo.getPlocation());
			pstmt.setString(6, pvo.getThumbnail());
			pstmt.setInt(7, pvo.getPid());
			
			 int pst = pstmt.executeUpdate();
	         if(pst !=0) {
	        	 result =true;
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
	//조회수 업데이트
	public boolean updateViewCnt(int pid) {//완
	       result = false;

	        try {
	            // 조회수 증가 SQL 쿼리 생성
	            query = "UPDATE product SET viewcnt = viewcnt + 1 WHERE pid = ?";
	            pstmt = con.prepareStatement(query);

	            // SQL 쿼리의 인자 설정
	            pstmt.setInt(1, pid);

	            // SQL 쿼리 실행 및 결과 처리
	            int count = pstmt.executeUpdate();
	            if (count > 0) {
	                result = true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            DBConn.close(pstmt);
	        }

	        return result;
	    }
	
	public int totalCount(int category,String location,String keyword,String type,String mid) {//완
		int cnt=0;
		try {
			query="SELECT count(*) FROM product where 1=1 ";
			if (category>0) {//전체-> 0으로
			    query += "AND cid = " + category; // 카테고리 검색 조건 추가
			}
			if (!location.equals("") ) {
			    query += "AND plocation = '" + location+"'"; // 지역 검색 조건 추가
			}
			if (!keyword.equals("")) {
			    query += "AND (ptitle LIKE '%" + keyword + "%' OR pcontent LIKE '%" + keyword + "%') "; // 키워드 검색 조건 추가
			}
			if(type.equals("buy")) {
				query += "AND buyer_id='"+mid+"'";
			}
			if(type.equals("sell")) {
				query += "AND seller_id='"+mid+"'";
			}
			
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next() == true) {
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
	public int currValPid() {//현재의 pid값 도출하기
		int pid=0;
        try {
            query = "SELECT productid_seq.NEXTVAL FROM dual";
            pstmt = con.prepareStatement(query);
            
            rs = pstmt.executeQuery();
            if (rs.next() == true) {
           pid= rs.getInt(1);
           System.out.println(pid);
            }else {
            	System.out.println("다시 해보자");
            }
        
        }catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return pid;
	}
}
