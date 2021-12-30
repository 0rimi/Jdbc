package com.javaex.ex08;

import java.util.List;
import java.util.Scanner;

public class BookAllApp {
	
	public static void main(String[] args) {
				
		BookAllDao bookalldao = new BookAllDao();
		AuthorDao authordao = new AuthorDao();
		
		//작가등록
		//작가이름, 출생지
		AuthorVo avo01 = new AuthorVo("이문열","경북 영양");
		authordao.authorInsert(avo01);
		AuthorVo avo02 = new AuthorVo("박경리","경상남도 통영");
		authordao.authorInsert(avo02);
		AuthorVo avo03 = new AuthorVo("유시민","17대 국회의원");
		authordao.authorInsert(avo03);
		AuthorVo avo04 = new AuthorVo("기안84","기안동에서 산 84년생");
		authordao.authorInsert(avo04);
		AuthorVo avo05 = new AuthorVo("강풀","온라인 만화가 1세대");
		authordao.authorInsert(avo05);
		AuthorVo avo06 = new AuthorVo("김영하","알쓸신잡");
		authordao.authorInsert(avo06);
		
		
		//책등록
		//책제목, 출판사, 출판일, 작가id
		BookallVo bvo01 = new BookallVo("우리들의 일그러진 영웅", "다림", "1998-02-22",1);
		bookalldao.BookInsert(bvo01);
		BookallVo bvo02 = new BookallVo("삼국지", "민음사", "2002-03-01",1);
		bookalldao.BookInsert(bvo02);
		BookallVo bvo03 = new BookallVo("토지", "마로니에북스", "2012-08-15",2);
		bookalldao.BookInsert(bvo03);
		BookallVo bvo04 = new BookallVo("유시민의 글쓰기 특강", "생각의 길", "2015-04-01",3);
		bookalldao.BookInsert(bvo04);
		BookallVo bvo05 = new BookallVo("패션왕", "중앙북스(books)", "2012-02-22",4);
		bookalldao.BookInsert(bvo05);
		BookallVo bvo06 = new BookallVo("순정만화", "재미주의", "2011-08-03",5);
		bookalldao.BookInsert(bvo06);
		BookallVo bvo07 = new BookallVo("오직두사람", "문학동네", "2017-05-04",6);
		bookalldao.BookInsert(bvo07);
		BookallVo bvo08 = new BookallVo("26년", "재미주의", "2012-02-04",5);
		bookalldao.BookInsert(bvo08);
		
		
		List<BookallVo> bList;
		
		//출력
		bList = bookalldao.BookAllSelect();
		//List<BookAllVo> bList = bookalldao.BookAllSelect();
		                //이게 곧 List<BookAllVo> bookList
		//Shape<Rectangle> rectangle = new~ 같은 너낌
		//근데 우리는 이미 메소드 안에 List<BookallVo>를 만들었으니까
		for(int i=0; i<bList.size(); i++) {
			System.out.println(bList.get(i).getBookId()
					+","+bList.get(i).getTitle()+","+
					bList.get(i).getPubs()+","+
					bList.get(i).getPubDate()+","+
					bList.get(i).getAuthorId()+","+
					bList.get(i).getAuthorName()+","+
					bList.get(i).getAuthorDesc());
		}
		
		
		System.out.println("===========================");
		
		//수정
		//고치려는거 작가 3번 유시민 > 이고잉 / 출신은 걍 서울특별시로 바꾸자
		//avo07의 정보를 토대로 고침
		AuthorVo avo07 = new AuthorVo(3,"이고잉","서울특별시");
		authordao.authorUpdate(avo07);
		
		//북아이디 4번 > 자바프로그래밍 입문, 위키북스 2015-04-01 로변경 작가번호유지
		//bvo09번의 정보로 고침
		BookallVo bvo09 = new BookallVo(4,"자바프로그래밍 입문","위키북스","2015-04-01",3);
		bookalldao.BookUpdate(bvo09);
		
		
		//출력
		bList = bookalldao.BookAllSelect();
		//List<BookAllVo> bList = bookalldao.BookAllSelect();
		                //이게 곧 List<BookAllVo> bookList
		for(int i=0; i<bList.size(); i++) {
			System.out.println(bList.get(i).getBookId()
					+","+bList.get(i).getTitle()+","+
					bList.get(i).getPubs()+","+
					bList.get(i).getPubDate()+","+
					bList.get(i).getAuthorId()+","+
					bList.get(i).getAuthorName()+","+
					bList.get(i).getAuthorDesc());
		}
		
		
		//검색어 입력 Scanner
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색어를 입력하세요 : ");
		String str = sc.nextLine();
		
		
		//출력
		bList = bookalldao.search(str);
		//List<BookAllVo> bList = bookalldao.BookAllSelect();
		                //이게 곧 List<BookAllVo> bookList
		for(int i=0; i<bList.size(); i++) {
			System.out.println(bList.get(i).getBookId()
					+","+bList.get(i).getTitle()+","+
					bList.get(i).getPubs()+","+
					bList.get(i).getPubDate()+","+
					bList.get(i).getAuthorId()+","+
					bList.get(i).getAuthorName()+","+
					bList.get(i).getAuthorDesc());
		}	
		
		
		sc.close();
		

		
		
		
	}

}
