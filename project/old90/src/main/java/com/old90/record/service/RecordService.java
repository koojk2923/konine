package com.old90.record.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.old90.record.mapper.RecordMapper;
import com.old90.record.vo.RecordVO;
import com.webjjang.util.PageObject;

import lombok.extern.log4j.Log4j;


/**
 * 
 * @packageName	: com.old90.song.service
 * @fileName	: SongService.java
 * @author		: koo
 * @date		: 2022. 3. 19.
 * @description : 앨범 Service
 * =================================================
 *   DATE 		 	 AUTHOR 			  NOTE	
 * -------------------------------------------------
 * 2022. 3. 19.		  koo			 최초 생성
 *
 */


//자동으로 생성이 되게 하는 어노테이션
//@Controller - url과 관련, @Service - 처리, @Repository - DAO, @Compnent - 구성, @RestController - url과 관련 순수데이터(AJAX 사용할때)
@Service
@Log4j
@Qualifier("RecordService")
public class RecordService {

	// 의존성 자동 주입(Dependency Inject) -> 자동으로 하도록 지정하는 어노테이션 : @Autowired, @Inject
	@Inject
	private RecordMapper mapper;
	
	// 메인
	public List<RecordVO> recordList(PageObject pageObject) throws Exception {
		// 전체 데이터 개수 가져오기는 처리를 해야 시작 줄과 끝줄이 계산이 된다. 없으면 데이터를 안가져온다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 출력
		log.info("** main.RecordService.recordList().pageObejct -> " + pageObject);
		return mapper.recordList(pageObject);
	} // end of main()
	
	
	// 1.음반리스트
	public List<RecordVO> list(PageObject pageObject) throws Exception {
		// 전체 데이터 개수 가져오기는 처리를 해야 시작 줄과 끝줄이 계산이 된다. 없으면 데이터를 안가져온다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 출력
		log.info("1.RecordService.list().pageObejct -> " + pageObject);
		return mapper.list(pageObject);
	} // end of list()


	// 2.음반보기
	public RecordVO view (long no, int inc) throws Exception{
		log.info("2. RecordService.view().no, inc -> " + no + ", " + inc);
		if(inc == 1) mapper.increase(no);
		return mapper.view(no);
	} // end of view()
	
	// 3.음반등록 
	public int write(RecordVO vo) throws Exception {
		log.info("3. RecordService.write(). vo ->" + vo);
		return mapper.write(vo);
	} // end of write()
	
	// 4-1.음반 이미지 수정 처리
	public int changeImage(RecordVO vo) throws Exception {
		log.info("4-1. RecordService.changeImage(). vo ->" + vo);
		return mapper.changeImage(vo);
	} // end of update()
	
	// 4-2.음반 음원 수정 처리
	public int changeSong(RecordVO vo) throws Exception {
		log.info("4-2. RecordService.changeSong(). vo ->" + vo);
		return mapper.changeSong(vo);
	} // end of changeSong()
	
	// 4-3. 음반 수정
	public int update(RecordVO vo) throws Exception {
		log.info("4-3. RecordService.upate(). vo ->" + vo);
		return mapper.update(vo);
	} // end of update()
	
	// 5. 음반 삭제
	public int delete(RecordVO vo) throws Exception {
		log.info("5. RecordService.delete(). vo ->" + vo);
		return mapper.delete(vo);
	} // end of delete()
	
	
	// 6. 음원 순위 리스트 | 음원 조회수
	public List<RecordVO> songList(PageObject pageObject) throws Exception {
		// 전체 데이터 개수 가져오기는 처리를 해야 시작 줄과 끝줄이 계산이 된다. 없으면 데이터를 안가져온다.
		pageObject.setTotalRow(mapper.getTotalRow(pageObject));
		// 페이지 오브젝트 출력
		log.info("6. RecordService.songList().pageObejct -> " + pageObject);
		
		return mapper.songList(pageObject);
	} // end of songList()
	
	
	
	
} // end of class
