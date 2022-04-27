 package com.BookIng.qna.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.BookIng.qna.vo.QnaVO;
import com.BookIng.util.db.DB;
import com.webjjang.util.PageObject;

public class QnaDAO {

	// 전연변수 - 모든 메소드에서 사용이 가능 하다.
	// 필요한 객체 선언
	// 연결 객체
	Connection con = null;
	// 실행 객체
	PreparedStatement pstmt = null;
	// 결과 객체
	ResultSet rs = null;
	
	// 1-1. 질문답변 리스트 메소드 (데이터를 가져온다.)
	public List<QnaVO> list(PageObject pageObject) throws Exception {
		// 데이터 확인
		System.out.println("1.QnaDAO.list()");
		// 리턴 데이터 변수 선언
		List<QnaVO> list = null;
		
		// 검색을 하는 경우
		boolean searchCondition =  pageObject.getWord() != null && !pageObject.getWord().equals("");
				
		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상 처리 
			
			// 1. 드라이버 확인 -> DB.java에서 statice 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			// 3-1. 원본 데이터 가져오기
			String sql = " SELECT q.no, q.title, q.id, m.name, q.writeDate, q.hit, q .refNo, q.ordNo, q.levNo, q.parentNo "
					+ " FROM qna q, member m " + search(pageObject,"q.")  // 실질적인 데이터 (원본데이터 가져오기)
					+ ((searchCondition)?" AND (q.id = m.id)" : " WHERE q.id = m.id ")
					+ " ORDER BY q.refNo DESC, q.ordNO ";
			// 3-2. 순서번호 붙이기 
			sql = " SELECT rownum rnum, no, title, id, name, writeDate, hit, "
					+ " refNo, ordNo, levNo, parentNo FROM (" + sql + ") ";
			// 3-3. 페이지에 맞는 데이터 가져오기
			sql = " SELECT rnum, no, title, id, name, writeDate, hit, "
					+ " refNo, ordNo, levNo, parentNo "
					+ " FROM (" + sql + ") WHERE rnum between ? AND ? " ;
			// 3-4. 데이터 확인 
			System.out.println("2.QnaDAO.list().sql : " + sql);
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql); 
			int idx = 1;
			// 조건에 해당되는 pstmt 세팅 
			idx = searchSetData(pageObject, pstmt, idx);
			// startRow, endRow의 계산은 setTotalRow()를 호출해야하만 나온다. 아니면 0 이되서 데이터가 나오지 않는다.
			pstmt.setLong(idx++, pageObject.getStartPage()); // 시작 번호 -1 : 1페이지 정보
			pstmt.setLong(idx++, pageObject.getEndRow()); // 끝 번호 - 10 : 1페이지 정보
			
			// 5. 실행
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기
			if (rs != null) {
				// 리스트 데이터는 여러개 이다. 반복문 사용해야 한다.
				// rs.next() -> 다음 포인터로 인동 시킨다. 맨처음 포인터는 -1이다. 데이터가 있으면 true가 리턴된다.
				while (rs.next()) {
					// list가 null 이면 데이터를 담을 수 없으므로 생성해 준다. -> 한번만 될 수 있도록 if 문으로 처리해준다.
					if (list == null)
						list = new ArrayList<QnaVO>();
					// 데이터 한개를 담기 위한 vo 생성
					QnaVO vo = new QnaVO();
					// setter를 이용해서 rs에서 가져온 데이터 담기
					vo.setNo(rs.getLong("no"));
					vo.setTitle(rs.getString("title"));
					vo.setId(rs.getString("id"));
					vo.setName(rs.getString("name"));
					vo.setWriteDate(rs.getString("writeDate"));
					vo.setHit(rs.getLong("hit"));
					vo.setRefNo(rs.getLong("refNo"));
					vo.setOrdNo(rs.getInt("ordNo"));
					vo.setLevNo(rs.getInt("levNo"));
					vo.setParentNo(rs.getLong("parentNo"));
					
					// list 객체에 데이터가 담긴 vo 객체를 넣는다.
					list.add(vo);
				} // end of while
			} // end of if
			
		} catch (Exception e) { // 예외 처리
			// TODO: handle exception
			// 개발자를 위 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally
		
		// 수집한 데이터를 넘긴다.
		return list;
	} // end of list
	
	// 1-2. 페이지 처리를 위해서 전체 데이터 개수를 가져오는 메소드
	public long getTotalRow(PageObject pageObject) throws Exception {
		
		// 실행 위치와 전달 데이터 확인
		System.out.println("1.QnaDAO.getTotalRow().pageObject : " + pageObject );
		// 리턴 데이터 변수 선언
		long totalRow = 0;
		
		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상 처리
			
			// 1. 드라이버 확인 -> DB.java에서 statice 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			String sql = " SELECT COUNT(*) FROM qna ";
			
			// 3-1. search 메소드 문장
			if(pageObject.getWord() != null && !pageObject.getWord().equals(""))
				// search(페이지 Object-검색, 별칭-필드 앞에 붙일 별칭)
				sql += search(pageObject, ""); // "" : 아무것도 붙이지 말라
			
			// 3-2. 데이터 확인 
			System.out.println("2.QnaDAO.getTotalRow().pageObject : " + sql);
			
			// 4. 실행객체 & 데이터 세팅 - 추가 (searchSetData)
			pstmt = con.prepareStatement(sql);		
			int idx = 1;
			
			// 4-1. 조건이 있는 경우 데이터 세팅을 해야한다.(searchSetData)
			idx = searchSetData(pageObject, pstmt, idx);
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			rs = pstmt.executeQuery();
			
			// 6 표시 또는 데이터 저장
			// 데이터가 한개만 나온다. if문으로 다 처리하도록 한다.
			// rs.next() -> 다음 포인터로 인동 시킨다. 맨처음 포인터는 -1이다. 데이터가 있으면 true가 리턴된다.
			if(rs != null && rs.next()) {
				// rs.get 데이터타입("select의 데이터 이름")
				totalRow = rs.getLong(1);
			} // end of if
					
		} catch (Exception e) { // 예외처리
			// TODO: handle exception
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally
		
		return totalRow;
	} // end of getTotalRow
	
	// 1-3. 검색에 대한 문자열을 붙이는 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private String search(PageObject pageObject, String alias) throws Exception {
		String condition = "";
		// -> !pageObject.getWord().equals("") : 문자열이 있기 한데 아무것도 없는 쌍따옴표로 찍은 문자열도 안된다.
		if(pageObject.getWord() != null && !pageObject.getWord().equals("")) {
			condition += " where ( 1 = 0 " ;
			if(pageObject.getKey().indexOf("t") != -1) // -1 이면 데이터가 없다는 것이다.
				condition += " or  " + alias + "title like ? ";
			if(pageObject.getKey().indexOf("i") != -1) // -1 이면 데이터가 없다는 것이다.
				condition += " or "  + alias + "id like ? ";
			condition += ")";
		} // end of if
		
		return condition;
	} // end of search
	
	// 1-4. 검색에 대한 문자여을 붙이 메서드 -- 만약에 word가 있는 경우만 조건을 붙인다.
	private int searchSetData(PageObject pageObject, PreparedStatement pstmt, int idx) throws Exception {
		
		String word = pageObject.getWord();
		
		// -> !pageObject.getWord().equals("") : 문자열이 있기 한데 아무것도 없는 쌍따옴표로 찍은 문자열도 안된다.
		if(word != null && !word.equals("")) {
			if(pageObject.getKey().indexOf("t") != -1) // -1 이면 데이터가 없다는 것이다.
				pstmt.setString(idx++, "%" + word + "%");
			if(pageObject.getKey().indexOf("i") != -1) // -1 이면 데이터가 없다는 것이다.
				pstmt.setString(idx++, "%" + word + "%");
		} // end of if
		
		return idx;
	} // end of search
	
	// 2. 질문 답변 보기
	// 2-1. 보기 데이터 가져오기
	public QnaVO view(long no) throws Exception{
		// 실행 위치와 전달 데이터 확인 
		System.out.println("1.QnaDAO.view().vo : " + no);	
		// 리턴 데이터 변수 선언
		QnaVO vo = null;
		
		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상 처리
			
			// 1. 드라이버 확인 -> DB.java에서 statice 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			String sql = " SELECT q.no, q.title, q.content, q.id, m.name, "
					+ " to_char(q.writeDate, 'yyyy-mm-dd') writeDate, q.hit, "
					+ " q.refNo, q.ordNo, q.levNo, q.parentNo "
					+ " FROM qna q, member m WHERE ( no = ? ) AND ( m.id = q.id ) ";
			
			// 3-1. 데이터 확인
			System.out.println("2.QnaDAO.view().sql : " + sql);
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			rs = pstmt.executeQuery();
			
			// 6. 데이터 담기 
			if (rs != null && rs.next()) {
				// rs.next() -> 다음 포인터로 인동 시킨다. 맨처음 포인터는 -1이다. 데이터가 있으면 true가 리턴된다.
				
				// 데이터 한개를 담기 위한 vo 생성
				vo = new QnaVO();
				vo.setNo(rs.getLong("no"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setWriteDate(rs.getString("writeDate"));
				vo.setHit(rs.getLong("hit"));
				vo.setRefNo(rs.getLong("refNo"));
				vo.setOrdNo(rs.getInt("ordNo"));
				vo.setLevNo(rs.getInt("levNo"));
				vo.setParentNo(rs.getLong("parentNo"));
				
			} // end of if
		} catch (Exception e) { // 예외 처리
			// TODO: handle exception
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();
			
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt, rs);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();
				
			} // end of try-catch in finally
		} // end of finally
		
		// 수집한 데이터를 넘긴다.
		return vo;	
	} // end of view

	// 2-2. 조회수 1증가 메소드
	public int increase(long no) throws Exception {
		
		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.increase().no : " + no);
		int result = 0;
			
		try { // 정상처리
			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			String sql = " update qna set hit = hit + 1 where no = ? ";
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			
			result = pstmt.executeUpdate();
			
			// 6. 표시
			System.out.println(no + "번 글의 조회수가 1증가 되었습니다.");
			System.out.println("QnaDAO.increase().no : 글의 조회수가 1증가 되었습니다.");
			
		} catch (Exception e) { // 예외처리
			// TODO: handle exception
			// 개발자를 위해서 오류 경로 추적 출력
			e.printStackTrace();
		} finally {  // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위해서 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		}  // end of finally
		
		// 결과 데이터를 넘긴다.
		return result;
	} // end of increase (조회수 증가 메소드)		
	
	// 3. 질문하기 메소드
	public int write(QnaVO vo) throws Exception {
		
		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.write().vo : " + vo) ;
		int result = 0;
		
		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상처리 
			
			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			String sql = " INSERT INTO qna(no, title, content, id, refNo, ordNo, levNO) "
					+ " VALUES(qna_seq.NEXTVAL, ?, ?, ?, qna_seq.NEXTVAL, 1, 0) ";
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			result = pstmt.executeUpdate();	
			
			// 6. 데이터 표시
			System.out.println("QnaDAO.write() - 질문이 등록이 되었습니다.");
			
		} catch (Exception e) { // 예외처리
			// TODO: handle exception			
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();		
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();		
			} // end of try-catch in finally
		} // end of finally
		
		// 결과 데이터를 넘긴다.
		return result;
	} // end of write
	
	// 4. 질문이나 답변 수정하기 메소드
	public int update(QnaVO vo) throws Exception {
		
		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.updapte().vo : " + vo) ;
		int result = 0;	
		// 결과 데이터를 넘긴다.
		
		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상 처리	
			
			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();	
			
			// 3. 실행할 sql문 작성
			String sql = " UPDATE qna set title = ?, content = ? where no = ? ";
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			result = pstmt.executeUpdate();			
			
			// 6 데이터 표시
			System.out.println("QnaDAO.update() - 질문이나 답변이 수정되었습니다.");			
			
		} catch (Exception e) { // 예외 처리
			// TODO: handle exception
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);			
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally
		
		// 결과 데이터를 넘긴다.
		return result;
	} // end of update
	
	// 5. 질문이나 답변 삭제
	public int delete(long no) throws Exception {
		
		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.delete().no : " + no);
		int result = 0;
		
		try { // 정상 처리
			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();
			
			// 3. 실행할 sql문 작성
			String sql = " DELETE FROM QNA WHERE no = ? ";
			
			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()		
			result = pstmt.executeUpdate();

			// 6. 표시
			System.out.println("QnaDAO.delete().no : 글이 삭제가 되었습니다.");
			
		} catch (Exception e) { // 예외처리
			// TODO: handle exception
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);			
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally
		
		// 결과 데이터를 넘긴다.
		return result;
	} // end of delete
	
	// 6-1. 답변하기의 순서 번호 1증가 - 관련번호(refNo)가 같고 순서번호(ordNo)와 같거나 큰 순서번호를 +시킨다.
	public int increaseOrdNo(QnaVO vo) throws Exception {
	
		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.increaseOrdNo().vo : " + vo);
		int result = 0;
		
		try { // 정상처리
			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();		
			
			// 3. 실행할 sql문 작성 - 관련변호(refNo)가 같고 순서번호(ordNo)와 같거나 큰 순서번호를 +1 시킨다.
			String sql = " UPDATE qna set ordNo = ordNo + 1 WHERE refNo = ? and ordNo >= ? ";

			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, vo.getRefNo());
			pstmt.setInt(2, vo.getOrdNo());
			
			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()			
			result = pstmt.executeUpdate();
			
			// 6. 표시
			System.out.println("QnaDAO.increaseOrdNo() - 순서번호 1증가 되었습니다.");
			System.out.println("QnaDAO.increaseOrdNo().result - " + result);
			
		} catch (Exception e) { // 예외처리
			// TODO: handle exception
			// 개발자를 위한 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위한 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally
		// 결과 데이터를 넘긴다.
		return result;
	} // end of increaseOrdNo(답변하기의 순서 번호 1증가)

	// 6-2. 답변 등록 메소드
	public int answer(QnaVO vo) throws Exception {
		// TODO Auto-generated method stub

		// 실행 위치와 전달 데이터 확인
		System.out.println("QnaDAO.answer().vo : " + vo);
		int result = 0;

		// 데이터 채우기 - DB에서 가져오므로 예외처리 필수
		try { // 정상 처리

			// 1. 드라이버 확인 --> DB.java에서 static 초기화 블록으로 해결
			// 2. 연결 객체 가져온다.
			con = DB.getConnection();

			// 3. 실행할 sql문 작성
			String sql = " INSERT INTO qna(no, title, content, id, refNo, ordNo, levNo, parentNo) "
					+ " VALUES(qna_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";

			// 4. 실행객체 & 데이터 세팅
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getId());
			pstmt.setLong(4, vo.getRefNo());
			pstmt.setInt(5, vo.getOrdNo());
			pstmt.setInt(6, vo.getLevNo());
			pstmt.setLong(7, vo.getParentNo());

			// 5. 실행 - select : executeQuery() / insert, update, delete : executeUpdate()
			result = pstmt.executeUpdate();

			// 6 데이터 표시
			System.out.println("QnaDAO.answer() - 답변이 등록되었습니다.");

		} catch (Exception e) { // 예외 처리
			// TODO: handle exception
			// 개발자를 위해서 오류 경로 추적 출력
			e.printStackTrace();
		} finally { // 반드시 처리 - 닫기(정상이든 예외이든 무좋건 처리)
			try {
				// 7. 사용한 객체 닫기
				DB.close(con, pstmt);
			} catch (Exception e) {
				// TODO: handle exception
				// 개발자를 위해서 오류 경로 추적 출력
				e.printStackTrace();
			} // end of try-catch in finally
		} // end of finally

		// 결과 데이터를 넘긴다.
		return result;
	} // end of answer(답변 등록)
	
	
} // end of class

