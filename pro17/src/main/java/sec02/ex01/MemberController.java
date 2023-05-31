package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/member/*") // controller만 사용자에거 URL 노출됨. 
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO memberDAO;

	//서블릿이 최초로 실핼될 때 불러지는 매소드
	public void init() throws ServletException {
	
		memberDAO= new MemberDAO();
	 
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8"); // html 말고 xml, jason등 다양한 형태로 보내기 때문에 응답은 각각의 페이지에서 셋업해줌! 
		
		String action = request.getPathInfo();// url 내 "member/" 뒤에 붙는 값을 저장함! 
		String nextPage = "";
		
		
		
		if(action == null || action.equals("listMembers.do")) {
			// 회원 목록
			List<MemberVO> membersList = memberDAO.listMembers(); 
			nextPage = "/test03/listMembers.jsp";
			
			request.setAttribute("membersList", membersList);
			
		}else if (action.equals("/addMember.do")) {
			//회원 추가
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO v = new MemberVO(id, pwd, name, email); 
			memberDAO.addMember(v);
			nextPage = "/member/listMembers.do"; 
			
		}else if (action.equals("/modMember.do")) {
			//회원 수정
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			MemberVO v = new MemberVO(id, pwd, name, email); 
			memberDAO.addMember(v);
			
			nextPage = "/member.listMembers.jsp"; 
			
		}else if (action.equals("/delMember.do")) {
			//회원 삭제
			String id = request.getParameter("id");
	
			memberDAO.deleteMember(id);
			
			nextPage = "/member.listMembers.jsp"; 
			
			
			
		} else if (action.equals("/memberForm.do"))
			//회원 추가하는 입력폼 
			nextPage = "/test03/memberForm.jsp";
		
		else if (action.equals("/modMembersForm.do")) {
			//회원정보 수정
			String id = request.getParameter("id");
			
			MemberVO m = memberDAO.infoMember(id);
			
			request.setAttribute("memInfo", m);
			
			nextPage = "/test03/modMemberForm.jsp";
			
			
		
		}else {
			
			// 회원 목록 (지정안된 주소값이 들어온다면...) 
			List<MemberVO> membersList = memberDAO.listMembers(); 
			nextPage = "/test03/listMembers.jsp";
			
			request.setAttribute("membersList", membersList);
			
		}
		
		
		
		RequestDispatcher dp = request.getRequestDispatcher(nextPage);
		dp.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
