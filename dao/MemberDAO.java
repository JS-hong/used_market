package joongo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import joongo.util.DBConn;
import joongo.vo.MemberVO;

public class MemberDAO {
	private Connection con;
	private String query;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private boolean result;

	public MemberDAO(Connection con) {
		this.con = con;
	}

	public boolean insert(MemberVO mvo) {
		try {
			String memberQuery = "INSERT INTO member (mid, mpw, mname, mbirth, email, addr, addrdetail, mphone, gender, mimage, mdate, manner, mcondition) "
					+ "VALUES (?, ?, ?, ?, ?, ?, NVL(?, ' '), ?, ?, NVL(?, 'default.png'), SYSDATE, 7, '기본')";

			pstmt = con.prepareStatement(memberQuery);
			pstmt.setString(1, mvo.getMid());
			pstmt.setString(2, mvo.getMpw());
			pstmt.setString(3, mvo.getMname());
			pstmt.setString(4, mvo.getMbirth());
			pstmt.setString(5, mvo.getEmail());
			pstmt.setString(6, mvo.getAddr());
			pstmt.setString(7, mvo.getAddrdetail());
			pstmt.setString(8, mvo.getMphone());
			pstmt.setString(9, mvo.getGender());
			pstmt.setString(10, mvo.getMimage());

			int result = pstmt.executeUpdate();

			// member 테이블에 삽입 완료 후, 다른 테이블에 주소값 삽입
			if (result != 0) {
				String addrQuery = "INSERT INTO addr (mid, addr) VALUES (?, ?)";

				String[] address = mvo.getAddr().split(" ");
				String city = address[0];
				String district = address[1];
				String detail = address[2];

				String fullAddress = city + " " + district + " " + detail;

				pstmt = con.prepareStatement(addrQuery);
				pstmt.setString(1, mvo.getMid());
				pstmt.setString(2, fullAddress);

				int addrResult = pstmt.executeUpdate();
				return addrResult > 0;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				DBConn.close(pstmt);
			}
		}
		return false;
	}

	public MemberVO login(String mid, String password) {
		MemberVO member = null;

		try {

			String query = "SELECT * FROM member WHERE mid = ? AND mpw = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberVO();
				member.setMid(rs.getString("mid"));
				member.setMname(rs.getString("mname"));
				member.setEmail(rs.getString("email"));
				member.setMphone(rs.getString("mphone"));
				member.setMimage(rs.getString("mimage"));
				member.setManner(rs.getInt("manner"));
				member.setMdate(rs.getDate("mdate"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}

		return member;
	}

	public MemberVO select(String id) {
		MemberVO mvo = null;
		try {
			query = "SELECT * FROM member WHERE mid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mvo = new MemberVO();
				mvo.setMid(rs.getString("MID"));
				mvo.setMpw(rs.getString("MPW"));
				mvo.setMname(rs.getString("MNAME"));
				mvo.setMbirth(rs.getString("MBIRTH"));
				mvo.setEmail(rs.getString("EMAIL"));
				mvo.setAddr(rs.getString("ADDR"));
				mvo.setAddrdetail(rs.getString("ADDRDETAIL"));
				mvo.setMphone(rs.getString("MPHONE"));
				mvo.setGender(rs.getString("GENDER"));
				mvo.setMimage(rs.getString("MIMAGE"));
				mvo.setManner(rs.getDouble("MANNER")); // manner 값을 설정하는 부분
				mvo.setMdate(rs.getDate("MDATE")); // mdate 값을 설정하는 부분
				mvo.setMcondition(rs.getString("MCONDITION"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return mvo;
	}

	public boolean update(MemberVO mvo) throws SQLException {
	    try {
	        query = "UPDATE member SET mname = ?, mbirth = ?, email = ?, addr = ?, addrdetail = ?, "
	                + "mphone = ?, mimage = COALESCE(?, mimage) WHERE mid = ?";
	        pstmt = con.prepareStatement(query);
	        pstmt.setString(1, mvo.getMname());
	        pstmt.setString(2, mvo.getMbirth());
	        pstmt.setString(3, mvo.getEmail());
	        pstmt.setString(4, mvo.getAddr());
	        pstmt.setString(5, mvo.getAddrdetail());
	        pstmt.setString(6, mvo.getMphone());
	        pstmt.setString(7, mvo.getMimage());
	        pstmt.setString(8, mvo.getMid());
	        int result = pstmt.executeUpdate();
	        return result > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (pstmt != null) {
	            pstmt.close();
	        }
	    }
	    return false;
	}



	public boolean delete(String mid) throws SQLException {
		try {
			query = "DELETE FROM member WHERE mid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
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

	public boolean chkId(String mid) throws SQLException {
		try {
			query = "SELECT mid FROM member WHERE mid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
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

	public String selectId(String mname, String birth, String phone) {
		String id = null;
		try {
			query = "SELECT mid FROM member WHERE mname = ? AND mbirth = ? AND mphone = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mname);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getString("MID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return id;
	}

	public boolean chkPw(String mid, String phone) {
		boolean result = false;
		try {
			query = "SELECT mpw FROM member WHERE mid = ?  AND mphone = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mid);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt, rs);
		}
		return result;
	}

	public boolean updatePw(String mid, String newPw) {
		try {
			query = "UPDATE member SET mpw = ? WHERE mid = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, newPw);
			pstmt.setString(2, mid);
			int result = pstmt.executeUpdate();

			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(pstmt);
		}
		return false;
	}

}
