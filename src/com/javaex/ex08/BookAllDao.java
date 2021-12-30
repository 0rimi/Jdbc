package com.javaex.ex08;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAllDao {
	
	//필드
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String  pw = "webdb";
	
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null; 
	
	
	
	//메소드일반
	private void getconnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	private void close() {
	    // 5. 자원정리
	    try {
	        if (rs != null) {
	            rs.close();
	        }                
	        if (pstmt != null) {
	            pstmt.close();
	        }
	        if (conn != null) {
	            conn.close();
	        }
	    } catch (SQLException e) {
	        System.out.println("error:" + e);
	    }	
	}
	
	
	
	public void BookInsert(BookallVo bookallvo) {

		this.getconnection();

		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
		    
			 //문자열 만들기 --> ? 기호 주의
			String query = "";
			query += " INSERT into book ";
			query += " VALUES(seq_book_id.nextval, ?, ?, ?, ?) ";
					    
		    //문자열 쿼리문으로 만들기
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩
		    pstmt.setString(1, bookallvo.getTitle());
		    pstmt.setString(2, bookallvo.getPubs());
		    pstmt.setString(3, bookallvo.getPubDate());
		    pstmt.setInt(4, bookallvo.getAuthorId());
		    
		    //실행
		    int count = pstmt.executeUpdate();
		    System.out.println(count+"건이 저장되었습니다.(책)");
    
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		}
		
		this.close();
		
	}
	
	public void BookDelete(int index) {
		
		this.getconnection();
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열만들기
		String query = "";
		query += " delete from book ";
		query += " where book_id = ? ";
		System.out.println(query);
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setInt(1, index);
		
		//실행
		int count = pstmt.executeUpdate();
		System.out.println(count+"건이 삭제되었습니다.");
		
		}catch (SQLException e) {
		System.out.println("error:" + e);
		}	
		
		this.close();
		
	}
	
	public void BookUpdate(BookallVo bvo) {
		
		this.getconnection();
		
		try {
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열만들기
		String query = "";
		query += " update book ";
		query += " set  title = ?, ";
		query += " 		pubs = ?, ";
		query += " 		pub_date = ?, ";
		query += " 		author_id = ? ";
		query += " where book_id = ? ";
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, bvo.getTitle());
		pstmt.setString(2, bvo.getPubs());
		pstmt.setString(3, bvo.getPubDate());
		pstmt.setInt(4, bvo.getAuthorId());
		pstmt.setInt(5, bvo.getBookId());
		
		//실행
		int count = pstmt.executeUpdate();
		System.out.println(count+"건이 업데이트 되었습니다.(책)");
		
		
		// 4.결과처리
		} catch (SQLException e) {
		System.out.println("error:" + e);
		}
		
		this.close();		
		
	}
	
	public List<BookallVo> BookAllSelect() {
		
		//List만들기~
		List<BookallVo> bookallList = new ArrayList<BookallVo>();
		//작가데이터 가져오기
		
		this.getconnection();
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " SELECT b.book_id, ";
			query += " 		  b.title, ";
			query += " 		  b.pubs, ";
			query += " 		  to_char(b.pub_date,'YYYY-MM-DD') pub_date, ";
			query += " 		  a.author_id, ";
			query += " 		  a.author_name, ";
			query += " 		  a.author_desc ";
			query += " FROM book b, author a ";
			query += " WHERE b.author_id = a.author_id ";
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 생략(물음표가 없음)
			
			//실행(update 아니고 query)
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");	//순서알때는 컬럼 순서로 적어도 됨
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString(4);
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//bookAllVo 1열이라고 생각하고 각 정보 넣어주기
				BookallVo bookallVo = new BookallVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				//넣어준 각 bookAllVo(의주소)를 리스트에 add해주기
				bookallList.add(bookallVo);
				
				
			}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} 
		
		this.close();
						
		return bookallList; //코드가 닫히기전에 주소를 뱉어내야함.
				
		
	}
	
	//셀렉트를 할거긴 한데 특정 문자가 있는것을 할거야!
	public List<BookallVo> search(String str) {
		
		//List만들기~
		List<BookallVo> bookallList = new ArrayList<BookallVo>();
		//작가데이터 가져오기
		
		this.getconnection();
		
		try {
		
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += " SELECT b.book_id, ";
			query += " 		  b.title, ";
			query += " 		  b.pubs, ";
			query += " 		  to_char(b.pub_date,'YYYY-MM-DD') pub_date, ";
			query += " 		  a.author_id, ";
			query += " 		  a.author_name, ";
			query += " 		  a.author_desc ";
			query += " FROM book b full outer join  author a ";
			query += " ON b.author_id = a.author_id ";
			query += " WHERE title LIKE ? ";
			query += " OR pubs LIKE ? ";
			query += " OR author_name LIKE ? ";
			 
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, "%"+str+"%");
			pstmt.setString(2, "%"+str+"%");
			pstmt.setString(3, "%"+str+"%");
			
			//실행(update 아니고 query)
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");	//순서알때는 컬럼 순서로 적어도 됨
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString(4);
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				//bookAllVo 1열이라고 생각하고 각 정보 넣어주기
				BookallVo bookallVo = new BookallVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				//넣어준 각 bookAllVo(의주소)를 리스트에 add해주기
				bookallList.add(bookallVo);
				
				
			}
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} 
		
		this.close();
						
		return bookallList; //코드가 닫히기전에 주소를 뱉어내야함.
				
		
	}

}
