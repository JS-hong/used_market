package joongo.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import joongo.dao.categoryDAO;
import joongo.dao.favoriteproductDAO;
import joongo.dao.productDAO;
import joongo.dao.productimageDAO;
import joongo.vo.productVO;



@WebServlet("/Product/*")
@MultipartConfig(location="c:\\temp", maxFileSize = -1, maxRequestSize = -1, fileSizeThreshold = 1024)
public class ProductController extends HttpServlet {
	private static final double AMOUNT_PER_PAGE=6.0;//한 페이지의 게시물 수
	private static final double NUM_PER_PAGE=5.0; // 한 페이지에 표시할 페이지 번호 수
	
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private productDAO pdao;
	private productimageDAO pidao;
	private favoriteproductDAO fpdao;
	private categoryDAO cdao;
	private String url;

	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx=config.getServletContext();
		Connection con=(Connection) servletCtx.getAttribute("con");
		pdao=new productDAO(con);
		fpdao=new favoriteproductDAO(con);
		pidao=new productimageDAO(con);
		cdao=new categoryDAO(con);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cmd=request.getRequestURI().substring(request.getContextPath().length());
		session=request.getSession();
	
		if(cmd.equals("/Product/List.do")) {
			productlist(request);
		}
		if(cmd.equals("/Product/WriteForm.do")) {
			productWriteForm(request);
		}
		if(cmd.equals("/Product/Write.do")) {
			productwrite(request);//리다이렉트 하고  그냥 return;
			response.sendRedirect(url);
			return;
		}
		if(cmd.equals("/Product/View.do")) {
			productview(request);
			
		}
		if(cmd.equals("/Product/ModifyForm.do")) {
			productModifyForm(request);
		}
		if(cmd.equals("/Product/Modify.do")) {
			productModify(request);//리다이렉트 하고  그냥 return;
			response.sendRedirect(url);
			return;
		}
		if(cmd.equals("/Product/Remove.do")) {
			productRemove(request);
			response.sendRedirect(url);
			return;
		}
		if(cmd.equals("/Product/BuySell.do")) {
			buySellList(request);
		}
		if(cmd.equals("/Product/FavorList.do")) {
			favorList(request);
		}
		if(cmd.equals("/Product/FavorAdd.do")) {
			addFavor(request);
		}
		if(cmd.equals("/Product/FavorRemove.do")) {
			removeFavor(request);
		}
		if(cmd.equals("/Product/FavorList.do")) {
			favorList(request);
		}
		RequestDispatcher rdp=request.getRequestDispatcher(url);
		rdp.forward(request,response);
		
	
	}

	
	public void buySellList(HttpServletRequest request) {
		String type=request.getParameter("type");
		String mid=request.getParameter("mid");
		int totalCnt=pdao.totalCount(0,"","",type,mid);
		
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int pages=(int) Math.ceil(totalCnt/AMOUNT_PER_PAGE);//css 아래 페이지수   ceil은 올림
		int end=(int) (Math.ceil(pageNum/NUM_PER_PAGE)*NUM_PER_PAGE);//각페이지의 끝
		int start=(int) (end-(NUM_PER_PAGE-1));//각페이지의 시작 페이지
			end = end>=pages?pages:end;
		//이전|이후 버튼 활성화 여부
		boolean prev=start>1;
		boolean next=end<pages;
		request.setAttribute("type", type);
		request.setAttribute("mid", mid);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pages", pages);
		request.setAttribute("end", end);
		request.setAttribute("start", start);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		request.setAttribute("buysell", pdao.buySellSelectAll(AMOUNT_PER_PAGE, pageNum, type, mid));
		url="/product/buySellProduct.jsp";
	}
	public void productlist(HttpServletRequest request) {
		String keyword=request.getParameter("keyword");
		String location=request.getParameter("location");
		
		int category=Integer.parseInt(request.getParameter("category"));
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int totalCnt=pdao.totalCount(category,location,keyword,"","");
		
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
		request.setAttribute("category", category);
		request.setAttribute("location", location);
		request.setAttribute("keyword", keyword);
		request.setAttribute("cname", cdao.categoryname(category));
		request.setAttribute("productList", pdao.productSelectAll(AMOUNT_PER_PAGE,pageNum,category,keyword,location));//페이징 
		request.setAttribute("categoryList", cdao.categoryselect());
		url="/product/productlist.jsp";
	}
	public void productWriteForm(HttpServletRequest request) {//완
		request.setAttribute("categoryList", cdao.categoryselect());
		request.setAttribute("mid", (String) session.getAttribute("sid"));
		//주소도 받아와야됨
		 url = "/product/productAdd.jsp";
	}
	public void productModifyForm(HttpServletRequest request) {//완
		request.setAttribute("categoryList", cdao.categoryselect());
		//주소도 받아와야됨
		request.setAttribute("pid", request.getParameter("pid"));
		request.setAttribute("sellerid",request.getParameter("sellerid"));
		request.setAttribute("cid",	Integer.parseInt(request.getParameter("cid")));
		request.setAttribute("price",Integer.parseInt(request.getParameter("price")));
		request.setAttribute("ptitle",  request.getParameter("ptitle"));
		request.setAttribute("pcontent", request.getParameter("pcontent"));
		request.setAttribute("plocation", request.getParameter("plocation"));
		url = "/product/productmodify.jsp";
	}
	public void productModify(HttpServletRequest request) {	
		productVO pvo=new productVO();

	    String savePath = request.getServletContext().getRealPath("/resources/image/");
	    String encType = "UTF-8";
	    
	    String sellerid=request.getParameter("sellerid");//sellerid
    	int pid=Integer.parseInt(request.getParameter("pid"));
    	int pageNum=Integer.parseInt( request.getParameter("pageNum"));
	    try {
	        request.setCharacterEncoding(encType);
	        
	        pvo.setCid(Integer.parseInt(request.getParameter("cid")));
	        pvo.setPrice(Integer.parseInt(request.getParameter("price")));
	        pvo.setPtitle(request.getParameter("ptitle"));
	        pvo.setPcontent(request.getParameter("pcontent"));
	        pvo.setPlocation(request.getParameter("plocation"));
	        pvo.setPid(pid);
	       
	    	
	        boolean isFirstImage = true;
	        for (Part part : request.getParts()) {
	            if (part.getName().equals("images")) {
	                String fileName = part.getSubmittedFileName();

	                if (fileName != null && !fileName.isEmpty()) {
	                    part.write(savePath + fileName);

	                    if (isFirstImage) { // 첫 번째 이미지를 썸네일로 설정
	                        isFirstImage = false;
	                        pvo.setThumbnail(fileName);
	                        pdao.productUpdate(pvo); 
	                        pidao.imageDelete(pid);
	                    } else { // 나머지 이미지들
	                    	
	                        pidao.imageInsert(pid, fileName);
	                    }
	                }
	            }
	        } 
	    } catch (IOException | ServletException e) {
	        e.printStackTrace();
	    }

	    url = "/joongo/Product/View.do?pid="+pid+"&id="+sellerid+"&pageNum="+pageNum;
	}
	
	
	public void favorList(HttpServletRequest request) {
		
		String mid=request.getParameter("mid");
		int totalCnt=fpdao.myfavorcnt(mid);
		
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		int pages=(int) Math.ceil(totalCnt/AMOUNT_PER_PAGE);//css 아래 페이지수   ceil은 올림
		int end=(int) (Math.ceil(pageNum/NUM_PER_PAGE)*NUM_PER_PAGE);//각페이지의 끝
		int start=(int) (end-(NUM_PER_PAGE-1));//각페이지의 시작 페이지
			end = end>=pages?pages:end;
		//이전|이후 버튼 활성화 여부
		boolean prev=start>1;
		boolean next=end<pages;
		
		request.setAttribute("mid", mid);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("pages", pages);
		request.setAttribute("end", end);
		request.setAttribute("start", start);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		
		request.setAttribute("favor", pdao.favorProductSelectAll(AMOUNT_PER_PAGE, pageNum, mid));
		url="/product/favorProduct.jsp";
	}
	public void addFavor(HttpServletRequest request) {
		int pageNum=Integer.parseInt( request.getParameter("pageNum"));	//쿼리스트링으로 받은 파라미터값
		request.setAttribute("pageNum", pageNum);
		
		String sellerid=request.getParameter("id");//sellerid
		int pid=Integer.parseInt(request.getParameter("pid"));
	    String mid=(String) session.getAttribute("mid");
	    fpdao.addFavoriteProduct(mid, pid);
	    
	    request.setAttribute("favorCnt", fpdao.favorcnt(pid));
	    request.setAttribute("isfavor",  fpdao.isFavoriteProduct(mid, pid)); 
		request.setAttribute("sellerid", sellerid);
		request.setAttribute("pid", pid);
		request.setAttribute("productImg", pidao.imageSelect(pid));
		request.setAttribute("productView", pdao.productSelect(pid));
		url="/product/productdetail.jsp";
	}
	public void removeFavor(HttpServletRequest request) {
		int pageNum=Integer.parseInt( request.getParameter("pageNum"));	//쿼리스트링으로 받은 파라미터값
		request.setAttribute("pageNum", pageNum);
		
		String sellerid=request.getParameter("id");//sellerid
		int pid=Integer.parseInt(request.getParameter("pid"));
		String mid=(String) session.getAttribute("mid");
		fpdao.removeFavoriteProduct(mid, pid);
		
		request.setAttribute("favorCnt", fpdao.favorcnt(pid));
		request.setAttribute("isfavor",  fpdao.isFavoriteProduct(mid, pid)); 
		request.setAttribute("sellerid", sellerid);
		request.setAttribute("pid", pid);
		request.setAttribute("productImg", pidao.imageSelect(pid));
		request.setAttribute("productView", pdao.productSelect(pid));
		url="/product/productdetail.jsp";
	}
	
	public void productwrite(HttpServletRequest request) {
	    productVO pvo = new productVO();

	    String savePath = request.getServletContext().getRealPath("/resources/image/");
	    String encType = "UTF-8";

	    try {
	        request.setCharacterEncoding(encType);

	        pvo.setSellerid(request.getParameter("sellerid"));
	        pvo.setCid(Integer.parseInt(request.getParameter("cid")));
	        pvo.setPrice(Integer.parseInt(request.getParameter("price")));
	        pvo.setPtitle(request.getParameter("ptitle"));
	        pvo.setPcontent(request.getParameter("pcontent"));
	        pvo.setPlocation(request.getParameter("plocation"));
	        int pid = pdao.currValPid();
	        boolean isFirstImage = true;
	        for (Part part : request.getParts()) {
	            if (part.getName().equals("images")) {
	                String fileName = part.getSubmittedFileName();

	                if (fileName != null && !fileName.isEmpty()) {
	                    part.write(savePath + fileName);

	                    if (isFirstImage) { // 첫 번째 이미지를 썸네일로 설정
	                        isFirstImage = false;
	                        pvo.setThumbnail(fileName);
	                        pdao.productInsert(pvo);                                      
	                    } else { // 나머지 이미지들
	                        pidao.imageInsert(pid, fileName);
	                    }
	                }
	            }
	        } 
	    } catch (IOException | ServletException e) {
	        e.printStackTrace();
	    }

	    url = "/joongo/Product/List.do?pageNum=1&category=0&keyword=&location=";
	}

	public void productview(HttpServletRequest request) {
	int pageNum=Integer.parseInt( request.getParameter("pageNum"));	//쿼리스트링으로 받은 파라미터값
	request.setAttribute("pageNum", pageNum);
	
	String sellerid=request.getParameter("id");//sellerid
	int pid=Integer.parseInt(request.getParameter("pid"));

      String mid=(String) session.getAttribute("mid");

    request.setAttribute("isfavor",  fpdao.isFavoriteProduct(mid, pid)); 
    request.setAttribute("favorCnt", fpdao.favorcnt(pid));
	request.setAttribute("sellerid", sellerid);
	request.setAttribute("pid", pid);
	
	pdao.updateViewCnt(pid);
	
	
	request.setAttribute("productImg", pidao.imageSelect(pid));
	request.setAttribute("productView", pdao.productSelect(pid));
	url="/product/productdetail.jsp";
	}

	public void productRemove(HttpServletRequest request) {
	int pid=Integer.parseInt(request.getParameter("pid"));	
	
	pdao.productDelete(pid);

	int pageNum=Integer.parseInt( request.getParameter("pageNum"));
	int category=Integer.parseInt(request.getParameter("category"));
	String location=request.getParameter("location");
	String keyword=request.getParameter("keyword");
	url="/joongo/Product/List.do?pageNum="+pageNum+"&category="+category+"&location="+location+"&keyword="+keyword;
	}
	
	
}
