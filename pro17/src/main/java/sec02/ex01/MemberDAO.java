package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	 private Connection con; //DB 연걸 변수
	 private PreparedStatement pstmt; // sql문 담아 미리 준비 하는 변수 
	 private DataSource ds;// connection pool에서 db 연결 정보 조회
	 
	 
	 public MemberDAO() {
		 try {
			 Context ctx = new InitialContext();//톰캣에 저장되어 있는 context 정보 조회를 위한 설정
			 Context env = (Context) ctx.lookup("java:/comp/env");// context에 저장되어 있는 환경(설정) 정보 조회용
			 ds = (DataSource)env.lookup("jdbc/oracle"); // connection pool 정보 조회
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	 
	 //맴버조회매소드
	 public ArrayList<MemberVO> listMembers (){
		 ArrayList<MemberVO> list = new ArrayList(); // MemberVO클래스형태로 회원정보 가져옴! DB와 작업할때는 try catch 항상 넣어줄것! 
		 
		 try {
			 con = ds.getConnection(); //회원정보가져와야하기때문에 데이터베이스에 연결 시도
			 String query = "SELECT * FROM T_MEMBER"; // 쿼리문 작성
			 pstmt = con.prepareStatement(query); // 변수 준비 상태의 변수에 데이터베이스접속쿼리 넣어줌.
			 ResultSet rs = pstmt.executeQuery();// 쿼리 실행 후 resultSet에 담음.// 

			 
			 //rs 한줄씩 확인 
			 while (rs.next()) 
			 {
				 String id = rs.getString("id"); 
				 String pwd = rs.getString("pwd");  
				 String name = rs.getString("name"); 
				 String email = rs.getString("email"); 
				 Date joinDate = rs.getDate("joinDate"); 
				 
				 // 한줄씩 확인한 회원 정보를 MemberVO에 담아줌. 
				 MemberVO vo = new MemberVO(); 
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
				 vo.setJoinDate(joinDate);
				 
				 //memberVO형태로 담긴 정보를 다시 arrayList에 담아준다. (맴버한명의 정보가 담김) 
				 list.add(vo);	 
			 }
			 
			 //resource 누수방지를 위해 사용 후 닫아줘야함.
			 rs.close();
			 con.close();
			 pstmt.close();//connection pool에 반환
					 
		 }catch (Exception e) {
			 e.printStackTrace();
		 }
		 		return list;
	 }
	 
	 
	 //회원 추가
	 public void addMember(MemberVO m) { 
	 
	 try 
	 {
		 con = ds.getConnection(); //회원정보가져와야하기때문에 데이터베이스에 연결 시도
		 String query = "INSERT INTO T_MEMBER VALUES(?,?,?,?)"; // 쿼리문 작성
		 pstmt = con.prepareStatement(query); // 변수 준비 상태의 변수에 데이터베이스접속쿼리 넣어줌.
		 
		
		 pstmt.setString(1, m.getId());
		 pstmt.setString(2, m.getPwd());
		 pstmt.setString(3, m.getEmail());
		 pstmt.setDate(4, m.getJoinDate());
		 
	}
		 	 
	 catch (Exception e) 
	 {
		 e.printStackTrace();
	 }
//	 con.close();
//	 pstmt.close();//connection pool에 반환
	 		
 }
	 
	 //회원삭제 매소드
	 public void deleteMember(String id) { 

		 try {
			 con= ds.getConnection(); 
			 String query = "DELETE FROM T_MEMBER WHERE ID= =?"; 
			 pstmt = con.prepareStatement(query); 
			 
			 pstmt.close();
			 
		 }catch(Exception e ) {
			 e.printStackTrace();
		 }
	 }
	 
	 
	 //회원정보수정 매소드
	 public void modMember(MemberVO v) { 
		 try {
			 con= ds.getConnection(); 
			 String query = "UPDATE T_MEMBER SET PWD = ?, NAME= ? , EMAIL = ? WHERE ID= =?"; 
			 
			 pstmt = con.prepareStatement(query); 
			
			 pstmt.setString(1, v.getPwd());
			 pstmt.setString(2, v.getName());
			 pstmt.setString(3, v.getEmail());
			 pstmt.setString(4, v.getId());
			 
			 pstmt.executeUpdate();
			 
			 pstmt.close();
			 con.close();
		
			 
		 }catch(Exception e ) {
			 e.printStackTrace();
		 }
		 

	 }
	 
	 
	 // 회원 정보 조회
	   public MemberVO infoMember(String id) {
	      MemberVO m = null;

	      try {
	         Connection con = ds.getConnection();
	         String query = "select * from t_member where id = ?";

	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, id);

	         ResultSet rs = pstmt.executeQuery();

	         while (rs.next()) {
	            m = new MemberVO();

	            m.setId(rs.getString("id"));
	            m.setPwd(rs.getString("pwd"));
	            m.setName(rs.getString("name"));
	            m.setEmail(rs.getString("email"));
	            m.setJoinDate(rs.getDate("joindate"));

	            break;
	         }
	         
	         System.out.println("infoMember확인용");
	         pstmt.close();
	         con.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return m;
	   }

	 // 회원 존재 여부 확인
	   public boolean isExisted(String id, String pwd) {
		  System.out.println("확인용");
	      boolean result = false;

	      try {
	         con = ds.getConnection();
	         // 스트링 블럭// 쿼리문이 길어지면 복잡해지기떄문에 문자열을 넣어서 가능함"""
	         String query = """  
	            select count(*) as result
	            from t_member
	            where id = ?
	              and pwd = ?
	         """;

	         pstmt = con.prepareStatement(query);

	         pstmt.setString(1, id);
	         pstmt.setString(2, pwd);

	         ResultSet rs = pstmt.executeQuery();

	         rs.next(); // 커서를 첫번째 레코드로 위치시킵니다.

	         result = rs.getInt("result") > 0;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return result;
	   }
	 
	   
	//이름으로 조회
	   public MemberVO searchMember(String name) {
	      MemberVO m = null;

	      try {
	         Connection con = ds.getConnection();
	         String query = "select * from t_member where name = ?";

	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, name);

	         ResultSet rs = pstmt.executeQuery();

	         while (rs.next()) {
	            m = new MemberVO();

	            m.setId(rs.getString("id"));
	            m.setPwd(rs.getString("pwd"));
	            m.setName(rs.getString("name"));
	            m.setEmail(rs.getString("email"));
	            m.setJoinDate(rs.getDate("joindate"));

	            break;
	         }
	           
	         pstmt.close();
	         con.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return m;
	   }

}//끝
