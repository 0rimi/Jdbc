package com.javaex.ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
	//필드
	
	//생성자
	public AuthorDao() {};
	
	//메소드 gs
	
	//메소드 일반
	public void authorInsert(String authorName, String authorDesc) {
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
		    // 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
		    
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			                                 //url,    id,      pw
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
		    
			// 3. SQL문 준비 / 바인딩 / 실행
		    
			 //문자열 만들기 --> ? 기호 주의
			String query = "";
		    query += "insert into author";
		    query += " values(seq_author_id.nextval,?,?)";
		    System.out.println(query);
		    
		    //문자열 쿼리문으로 만들기
		    pstmt = conn.prepareStatement(query);
		    
		    //바인딩
		    pstmt.setString(1, authorName);     //첫번째 물음표 데이터
		    pstmt.setString(2, authorDesc);     //두번째 물음표 데이터
		    
		    //실행
		    int count = pstmt.executeUpdate();
		    
	
		    // 4.결과처리
		    System.out.println(count+"건이 저장되었습니다.(작가)");
		    
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
	
	public void authorDelete(int num) {
		
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
		query += " delete from author ";
		query += " where author_id = ? ";
		System.out.println(query);
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setInt(1, num);
		
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
	
	public void authorUpdate(int index, String authorName, String authorDesc) {
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
		query += " update author ";
		query += " set author_name = ?, ";
		query += " 	author_desc = ? ";
		query += " where author_id = ? ";
		System.out.println(query);
		
		//문자열을 쿼리문으로 만들기
		pstmt = conn.prepareStatement(query);
		
		//바인딩
		pstmt.setString(1, authorName);
		pstmt.setString(2, authorDesc);
		pstmt.setInt(3, index);
		
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
	
	//주소값을 리턴하는거니까
	public List<AuthorVo> authorSelect(){
		
		//List만들기~
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
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
			query += " SELECT author_id, ";
			query += " 		  author_name, ";
			query += " 		  author_desc ";
			query += " FROM author ";
			
			//문자열을 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 생략(물음표가 없음)
			
			//실행(update 아니고 query)
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				
				int authorID = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
		
				//authorVo 1열이라고 생각하고 각 정보 넣어주기
				AuthorVo author = new AuthorVo(authorID,authorName,authorDesc);
				//넣어준 각 authorVo(의주소)를 리스트에 add해주기
				authorList.add(author);
				
				
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
						
		return authorList; //코드가 닫히기전에 주소를 뱉어내야함.
		
	}
	
	
	

}
