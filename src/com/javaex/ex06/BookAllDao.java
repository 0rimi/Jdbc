package com.javaex.ex06;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookAllDao {
	
	public void BookAllInsert(String title, String pubs, String pubDate, int authorId) {
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
		    
			// 3. SQL문 준비 / 바인딩 / 실행
		    
			 //문자열 만들기 --> ? 기호 주의
			String query = "";
			query += " INSERT into book ";
			query += " VALUES(seq_book_id.nextval, ?, ?, ?, ?) ";
			System.out.println(query);
					    
		    //문자열 쿼리문으로 만들기
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩
		    pstmt.setString(1, title);
		    pstmt.setString(2, pubs);
		    pstmt.setString(3, pubDate);
		    pstmt.setInt(4, authorId);
		    
		    //실행
		    int count = pstmt.executeUpdate();
		    System.out.println(count+"건이 저장되었습니다.(작가)");
	
		    // 4.결과처리
		   
		    
		} catch (ClassNotFoundException e) {
		    System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		    System.out.println("error:" + e);
		} finally {
		   
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
		
	}
	
	public void BookAllDelete(int index) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		System.out.println("접속성공");
		
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
		
		
		// 4.결과처리
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
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
	}
	
	public void BookAllUpdate(int index, String title, String pubs, String pubDate, int authorId) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		// 2. Connection 얻어오기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		conn = DriverManager.getConnection(url, "webdb", "webdb");
		System.out.println("접속성공");
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		//문자열만들기
		String query = "";
		query += " update book ";
		query += " set  title = ?, ";
		query += " 		pubs = ?, ";
		query += " 		pub_date = ?, ";
		query += " 		author_id = ? ";
		query += " where book_id = ? ";
		System.out.println(query);
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, title);
		pstmt.setString(2, pubs);
		pstmt.setString(3, pubDate);
		pstmt.setInt(4, authorId);
		pstmt.setInt(5, index);
		
		//실행
		int count = pstmt.executeUpdate();
		System.out.println(count+"건이 업데이트 되었습니다.");
		
		
		// 4.결과처리
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
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
		
		
	}
	
	public List<BookallVo> BookAllSelect() {
		
		//List만들기~
		List<BookallVo> bookallList = new ArrayList<BookallVo>();
		//작가데이터 가져오기
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
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
		} catch (ClassNotFoundException e) {
		System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
		System.out.println("error:" + e);
		} finally {
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
						
		return bookallList; //코드가 닫히기전에 주소를 뱉어내야함.
				
		
	}

}
