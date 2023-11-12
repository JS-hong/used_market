package joongo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import joongo.dao.MemberDAO;
import joongo.vo.MemberVO;

@WebServlet("/User/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private MemberDAO mdao;
	private String url;

	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		Connection con = (Connection) servletCtx.getAttribute("con");
		mdao = new MemberDAO(con);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cmd = request.getRequestURI().substring(request.getContextPath().length());
		session = request.getSession();

		if (cmd.equals("/User/MemberJoin.do")) {
			url = "/user/join.jsp";
		}

		if (cmd.equals("/User/MemberLogin.do")) {
			url = "/user/login.jsp";
		}
		
		if (cmd.equals("/User/MemberId.do")) {
			url = "/user/findId.jsp";
		}

		if (cmd.equals("/User/MemberModify.do")) {
			modifyView(request);
		}

		if (cmd.equals("/User/MemberJoinForm.do")) {
			join(request);// 리다이렉트 하고 그냥 return;
			response.sendRedirect(url);
			return;
		}
		if (cmd.equals("/User/MemberView.do")) {
			view(request);

		}
		if (cmd.equals("/User/MemberLoginForm.do")) {
			login(request);
			response.sendRedirect(url);
			return;
		}

		if (cmd.equals("/User/MemberModifyForm.do")) {
			modify(request);
			response.sendRedirect(url);
			return;
		}
		if (cmd.equals("/User/MemberRemove.do")) {
			remove(request);
			response.sendRedirect(url);
			return;
		}
		
		if (cmd.equals("/User/MemberFindId.do")) {
			findId(request);
			response.sendRedirect(url);
			return;
		}
		
		RequestDispatcher rdp = request.getRequestDispatcher(url);
		rdp.forward(request, response);
	}

	public void join(HttpServletRequest request) {
		MemberVO mvo = new MemberVO();
		String savePath = request.getServletContext().getRealPath("/resource/images");
		int maxSize = 1024 * 1024 * 5; // 최대 업로드 크기 5MB
		String encType = "UTF-8";

		MultipartRequest multi;
		try {
			multi = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy());

			mvo.setMid(multi.getParameter("mid"));
			mvo.setMpw(multi.getParameter("mpw"));
			mvo.setMname(multi.getParameter("mname"));
			mvo.setMbirth(multi.getParameter("mbirth"));
			mvo.setEmail(multi.getParameter("email"));
			mvo.setAddr(multi.getParameter("addr"));
			mvo.setAddrdetail(multi.getParameter("addrdetail"));
			mvo.setMphone(multi.getParameter("phone1") + "-" + multi.getParameter("phone2") + "-"
					+ multi.getParameter("phone3"));
			mvo.setGender(multi.getParameter("gender"));
			mvo.setMimage(multi.getFilesystemName("mimage"));

		} catch (IOException e) {

			e.printStackTrace();
		}
		if (mdao.insert(mvo)) {
			session.setAttribute("msg", "회원가입이 완료되었습니다.");
		} else {
			session.setAttribute("msg", "회원가입에 실패하였습니다.");
		}
		url = "/joongo/index.jsp";
	}

	public void view(HttpServletRequest request) {

		String mid = request.getParameter("mid");

		request.setAttribute("userInfo", mdao.select(mid));
		request.setAttribute("mid", mid);
		url = "/user/userInfo.jsp?mid=" + mid;
	}

	public void modifyView(HttpServletRequest request) {

		String mid = request.getParameter("mid");

		request.setAttribute("userInfo", mdao.select(mid));
		request.setAttribute("mid", mid);
		url = "/user/modify.jsp";
	}

	public void login(HttpServletRequest request) {
		String mid = request.getParameter("mid");
		String password = request.getParameter("mpw");

		MemberVO member = mdao.login(mid, password);

		if (member != null) { // 로그인 성공한 경우
			HttpSession session = request.getSession();
			session.setAttribute("mid", mid);
			request.setAttribute("userInfo", member);
			url = "/joongo/util/message.jsp?msg=login";
		} else { // 로그인 실패한 경우
			url = "/joongo/User/MemberLogin.do?msg=fail";
		}
	}

	public void remove(HttpServletRequest request) {
		String mid = request.getParameter("mid");
		try {
			if (mdao.delete(mid)) {
				session.setAttribute("msg", "회원정보가 삭제되었습니다.");
			} else {
				session.setAttribute("msg", "회원정보 삭제를 실패하였습니다.");
			}
		} catch (SQLException e) {
		
		}
		url = "/joongo/index.jsp";
	}
	
	public void findId(HttpServletRequest request) {
		String mname = request.getParameter("mname");
		String mbirth = request.getParameter("mbirth");
		String mphone = request.getParameter("mphone");
		
		String id = mdao.selectId(mname, mbirth, mphone);

		if (id == null) {
			
		} else {
		    url = "/joongo/User/MemberLogin.do?msg=findid&id=" + id;
		}
		
	}
		

	public void modify(HttpServletRequest request) {

		String mid = (String) session.getAttribute("mid");
		request.setAttribute("mid", mid);

		MemberVO mvo = new MemberVO();
		String savePath = request.getServletContext().getRealPath("/resource/images");
		int maxSize = 1024 * 1024 * 5; // 최대 업로드 크기 5MB
		String encType = "UTF-8";

		MultipartRequest multi;
		try {
			multi = new MultipartRequest(request, savePath, maxSize, encType, new DefaultFileRenamePolicy());

			// 업로드한 회원정보로 mvo 객체 업데이트
			mvo.setMid(multi.getParameter("mid"));
			mvo.setMname(multi.getParameter("mname"));
			mvo.setMbirth(multi.getParameter("mbirth"));
			mvo.setEmail(multi.getParameter("email"));
			mvo.setAddr(multi.getParameter("addr"));
			mvo.setAddrdetail(multi.getParameter("addrdetail"));
			mvo.setMphone(multi.getParameter("phone1") + "-" + multi.getParameter("phone2") + "-"
					+ multi.getParameter("phone3"));
			mvo.setGender(multi.getParameter("gender"));
			mvo.setMimage(multi.getFilesystemName("mimage"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (mdao.update(mvo)) {
				System.out.println("성공");
			} else {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
		url = "/joongo/User/MemberView.do?mid=" + mid;
	}

//  public void list(HttpServletRequest request) {
//  String keyword=request.getParameter("keyword");
//  String type=request.getParameter("type");
//  int pageNum=Integer.parseInt(request.getParameter("pageNum"));
//  int totalCnt=mdao.totalCount(type,keyword);
//  int pages=(int) Math.ceil(totalCnt/AMOUNT_PER_PAGE);//css 아래 페이지수   ceil은 올림
//  int end=(int) (Math.ceil(pageNum/NUM_PER_PAGE)*NUM_PER_PAGE);//각페이지의 끝
//  int start=(int) (end-(NUM_PER_PAGE-1));//각페이지의 시작 페이지
//     end = end>=pages?pages:end;
//  //이전|이후 버튼 활성화 여부
//  boolean prev=start>1;
//  boolean next=end<pages;
//  
//  
//  request.setAttribute("pageNum", pageNum);
//  request.setAttribute("pages", pages);
//  request.setAttribute("end", end);
//  request.setAttribute("start", start);
//  request.setAttribute("prev", prev);
//  request.setAttribute("next", next);
//  request.setAttribute("type", type);
//  request.setAttribute("keyword", keyword);
//  
//  request.setAttribute("boardList", mdao.selectAll(AMOUNT_PER_PAGE,pageNum,type,keyword));//페이징 
//  request.setAttribute("totalCnt", totalCnt);//토탈카운트조회
//  url="/board/list.jsp";
//}

}