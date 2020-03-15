package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDao;
import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {

	AnswerDao dao = new AnswerDaoImpl();
	@Override
	public List<AnswerDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		// TODO Auto-generated method stub
		return dao.selectOne(boardno);
	}

	@Override
	public int insertBoard(AnswerDto dto) {
		// TODO Auto-generated method stub
		return dao.insertBoard(dto);
	}

	@Override
	public int updateBoard(AnswerDto dto) {
		// TODO Auto-generated method stub
		return dao.updateBoard(dto);
	}

	@Override
	public int deleteBoard(int boardno) {
		// TODO Auto-generated method stub
		return dao.deleteBoard(boardno);
	}

	@Override
	public int answerProc(AnswerDto dto) {
		AnswerDao dao = new AnswerDaoImpl();
		int res1 = dao.updateAnswer(dto.getBoardno());
		int res2 = dao.insertAnswer(dto);
		int res = res1 + res2;
		return res;
	}

	@Override
	public int deleteanswer(int boardno) {
		
		return dao.deleteanswer(boardno);
	}

}
