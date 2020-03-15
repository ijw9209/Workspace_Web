package com.answer.biz;

import java.util.List;

import com.answer.dto.AnswerBoardDto;

public interface AnswerBoardBiz {
	
	public List<AnswerBoardDto> selectlist();
	public AnswerBoardDto selectone(int boardno);
	public int insert(AnswerBoardDto dto);
	public int update(AnswerBoardDto dto);
	public int delete(int boardno);
	
	public int answerProc(AnswerBoardDto dto);
	public int updatedelflag(int boardno);
	public List<AnswerBoardDto> pagelist(int start , int end);
	
}
