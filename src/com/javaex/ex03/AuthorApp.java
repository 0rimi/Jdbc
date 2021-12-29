package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		List<AuthorVo> List;
		AuthorDao authorDao = new AuthorDao();
		
		//작가등록
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민","17대 국회의원");
		authorDao.authorInsert(vo03);
		
		System.out.println("=======================");
		List = authorDao.authorSelect();
		for(int i=0; i<List.size(); i++) {
			System.out.println(List.get(i).getAuthorId()+","+
						List.get(i).getAuthorName()+","+
						List.get(i).getAuthorDesc());
		}
		
		//작가수정
		AuthorVo editVo = new AuthorVo(2,"박경리(수정)","경상남도 통영(수정)");
		authorDao.authorUpdate(editVo);
		
		System.out.println("=======================");
		List = authorDao.authorSelect();
		for(int i=0; i<List.size(); i++) {
			System.out.println(List.get(i).getAuthorId()+","+
						List.get(i).getAuthorName()+","+
						List.get(i).getAuthorDesc());
		}
		
		
		//작가삭제
		authorDao.authorDelete(2);
		System.out.println("=======================");
		List = authorDao.authorSelect();
		for(int i=0; i<List.size(); i++) {
			System.out.println(List.get(i).getAuthorId()+","+
						List.get(i).getAuthorName()+","+
						List.get(i).getAuthorDesc());
		}

	}

}
