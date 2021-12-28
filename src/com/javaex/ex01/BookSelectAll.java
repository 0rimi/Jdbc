package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookSelectAll {

	public static void main(String[] args) {


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
					System.out.println(query);
					
					//문자열을 쿼리문으로 만들기
					pstmt = conn.prepareStatement(query);
					
					//바인딩 생략(물음표가 없음)
					
					//실행(update 아니고 query)
					rs = pstmt.executeQuery();  //result set이라는 큰 테이블 덩어리
												//cursor라는 개념 : 한 row 단위로 읽어냄
					//cursor반복작업
					//rs의 next() 넥스트로 갈수있으면 트루, 못가면 펄스인 불린 메소드
					while(rs.next()) {
						int bookId = rs.getInt(1);	//순서알때는 컬럼 순서로 적어도 됨
						String title = rs.getString(2);
						String pubs = rs.getString(3);
						String pub_date = rs.getString(4);
						int author_id = rs.getInt(5);
						String author_name = rs.getString(6);
						String author_desc = rs.getString(7);
						
						System.out.println(bookId+","+title+","+pubs+","+pub_date+","+author_id
								+","+author_name+","+author_desc);
						}

					
					
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

}
