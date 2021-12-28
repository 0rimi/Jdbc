package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorSelect {

	public static void main(String[] args) {
		
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
					query += " SELECT author_id id, ";
					query += " 		  author_name, ";
					query += " 		  author_desc ";
					query += " FROM author ";
					//System.out.println(query);
					
					//문자열을 쿼리문으로 만들기
					pstmt = conn.prepareStatement(query);
					
					//바인딩 생략(물음표가 없음)
					
					//실행(update 아니고 query)
					rs = pstmt.executeQuery();  //result set이라는 큰 테이블 덩어리
												//cursor라는 개념 : 한 row 단위로 읽어냄
					//cursor반복작업
					//rs의 next() 넥스트로 갈수있으면 트루, 못가면 펄스인 불린 메소드
					while(rs.next()) {
						/*
						int authorID = rs.getInt("id"); //author_id라는 int컬럼
						String authorName = rs.getString("author_name");
						String authorDesk = rs.getString("author_desc");
						*/
						int authorID = rs.getInt(1);	//순서알때는 컬럼 순서로 적어도 됨
						String authorName = rs.getString(2);
						String authorDesc = rs.getString(3);
						
						//authorVo 1열이라고 생각하고 각 정보 넣어주기
						AuthorVo author = new AuthorVo(authorID,authorName,authorDesc);
						//넣어준 각 authorVo(의주소)를 리스트에 add해주기
						authorList.add(author);
						
						//System.out.println(author.toString());
						
					}
					
					//출력
					//authorList 하나가 한열 < 임!
					for(int i=0; i<authorList.size(); i++) {
						authorList.get(i).showinfo();
					}
					
					//원하는 정보만 출력가능
					System.out.println(authorList.get(1).getAuthorName());
					
					
					
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
