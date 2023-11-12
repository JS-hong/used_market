package joongo.controller;
import java.io.IOException;
import java.sql.Connection; 
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList; import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy; import joongo.dao.ChatDAO;
import joongo.dao.MemberDAO;
import joongo.vo.ChatVO;
import joongo.vo.MemberVO; 


@WebServlet("/Chat/*")
public class ChatController extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final double AMOUNT_PER_PAGE = 5.0;// 한 페이지의 게시물 수
private static final double NUM_PER_PAGE = 5.0; // 한 페이지에 표시할 페이지 번호 수
private HttpSession session;
private ChatDAO cdao;
private String url;
public ChatController() {
    super();
}

public void init(ServletConfig config) throws ServletException {
	ServletContext servletCtx = config.getServletContext();
	Connection con = (Connection) servletCtx.getAttribute("con");
	cdao = new ChatDAO(con);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html;charset=UTF-8");
	String cmd = request.getRequestURI().substring(request.getContextPath().length());
	session = request.getSession();
	 
	  if(cmd.equals("/Chat/ChatList.do")) {
		    ChatList(request);
		    url = "/chat/chatRoomList.jsp";
		    forwardRequest(request, response, url);
		  } 
	  else if (cmd.equals("/Chat/MessageList.do")) {
		    chatMassageList(request, response);
	  }
}
private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
	  RequestDispatcher rdp = request.getRequestDispatcher(url);
	  rdp.forward(request, response);
	}
public void ChatList(HttpServletRequest request) {
	String mid = request.getParameter("mid");
	int pageNum=Integer.parseInt(request.getParameter("pageNum"));
	int totalCnt=cdao.totalCount(mid);
	int pages=(int) Math.ceil(totalCnt/AMOUNT_PER_PAGE);//css 아래 페이지수   ceil은 올림
	int end=(int) (Math.ceil(pageNum/NUM_PER_PAGE)*NUM_PER_PAGE);//각페이지의 끝
	int start=(int) (end-(NUM_PER_PAGE-1));//각페이지의 시작 페이지
		end = end>=pages?pages:end;
	//이전|이후 버튼 활성화 여부
	boolean prev=start>1;
	boolean next=end<pages;
		
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("pages", pages);
	request.setAttribute("end", end);
	request.setAttribute("start", start);
	request.setAttribute("prev", prev);
	request.setAttribute("next", next);
	
	request.setAttribute("ChatList", cdao.selectAll(AMOUNT_PER_PAGE,pageNum,mid));//페이징 
	request.setAttribute("totalCnt", totalCnt);//토탈카운트조회
	url = "/chat/chatRoomList.jsp?mid="+mid;
}

public void chatMassageList(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String chat_room_id = request.getParameter("chat_room_id");
    ArrayList<ChatVO> messageList = cdao.message_list(chat_room_id);
	
	System.out.println(chat_room_id);

	Gson gson = new Gson();
	String jsonMessageList = gson.toJson(messageList);

	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(jsonMessageList);
	}
}
