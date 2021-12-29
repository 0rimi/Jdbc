package com.javaex.ex02;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		List<AuthorVo> List;
		//리스트 선언
		
		AuthorDao authorDao = new AuthorDao();
		
		//작가등록
		authorDao.authorInsert("이문열", "경북 영양");
		authorDao.authorInsert("박경리", "경상남도 통영");
		authorDao.authorInsert("유시민", "17대 국회의원");
		
		
		List = authorDao.authorSelect();
		//List<AuthorVo> List = authorDao.authorSelect();
		                //이게 곧 List<AuthorVo> authoList
		//출력
		for(int i=0; i<List.size(); i++) {
			//System.out.println(authorList.get(i));
			System.out.println(List.get(i).getAuthorId()
					+","+List.get(i).getAuthorName()+","+
					List.get(i).getAuthorDesc());
		}
		
		System.out.println("==================");
		
		//작가 수정
		authorDao.authorUpdate(1,"김문열","경상북도 영양");
		
		//출력
		List = authorDao.authorSelect();
		for(int i=0; i<List.size(); i++) {
			//System.out.println(authorList.get(i));
			System.out.println(List.get(i).getAuthorId()
					+","+List.get(i).getAuthorName()+","+
					List.get(i).getAuthorDesc());
		}
		
		
		System.out.println("==================");
		
		//작가 삭제
		authorDao.authorDelete(1);
		
		//출력
		List = authorDao.authorSelect();
		for(int i=0; i<List.size(); i++) {
			//System.out.println(authorList.get(i));
			System.out.println(List.get(i).getAuthorId()
					+","+List.get(i).getAuthorName()+","+
					List.get(i).getAuthorDesc());
		}

	}

}
