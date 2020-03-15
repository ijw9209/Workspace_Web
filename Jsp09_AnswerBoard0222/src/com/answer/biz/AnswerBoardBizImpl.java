package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerBoardDao;
import com.answer.dao.AnswerBoardDaoImpl;
import com.answer.dto.AnswerBoardDto;

public class AnswerBoardBizImpl implements AnswerBoardBiz {

	AnswerBoardDao dao = new AnswerBoardDaoImpl();
	@Override
	public List<AnswerBoardDto> selectlist() {
		// TODO Auto-generated method stub
		return dao.selectlist();
	}

	@Override
	public AnswerBoardDto selectone(int boardno) {
		// TODO Auto-generated method stub
		return dao.selectone(boardno);
	}

	@Override
	public int insert(AnswerBoardDto dto) {
		// TODO Auto-generated method stub
		return dao.insert(dto);
	}

	@Override
	public int update(AnswerBoardDto dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		// TODO Auto-generated method stub
		return dao.delete(boardno);
	}

	@Override
	public int updatedelflag(int boardno) {
		
		return dao.updatedelflag(boardno);
	}

	@Override
	public int answerProc(AnswerBoardDto dto) {
		AnswerBoardDao dao = new AnswerBoardDaoImpl();
		
		int updateres = dao.updateanswer(dto.getBoardno());
		int insertres = dao.insertanswer(dto);
		
		
		return (updateres + insertres);
	}



}
