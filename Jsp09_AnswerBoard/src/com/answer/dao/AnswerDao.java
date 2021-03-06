package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {

	String SELECT_LIST_SQL = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ ";
	String SELECT_ONE_SQL = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? " ;
	String INSERT_SQL = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL,GROUPNOSEQ.NEXTVAL,1,0,?,?,?,SYSDATE) ";
	String UPDATE_SQL = " UPDATE ANSWERBOARD SET TITLE = ?,CONTENT = ? WHERE BOARDNO = ? ";
	String DELETE_SQL = " DELETE FROM ANSWERBOARD WHERE BOARDNO = ? ";		
	String UPDATE_ANSWER_SQL = " UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ +1 WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?) ";
	String INSERT_ANSWER_SQL = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL,(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?),(SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=?)+1,(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1,?,?,?,SYSDATE) ";		
	String DELETE_ANSWER_SQL = " DELETE FROM ANSWERBOARD WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) AND GROUPSQ >= (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?) ";
	String SELECT_TITLETAB_SQL = "SELECT TITLETAB FROM ANSWERBOARD WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) AND GROUPSQ >= (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?)ORDER BY GROUPNO DESC, GROUPSQ ";
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insertBoard(AnswerDto dto);
	public int updateBoard(AnswerDto dto);
	public int deleteBoard(int boardno);
	
	//부모의 board number를 넣어줌
	public int updateAnswer(int parentboardno);
	public int insertAnswer(AnswerDto dto);
	public int deleteanswer(int boardno);
	public List<AnswerDto> selecttitletab(int boardno);
}
