package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerBoardDto;

public interface AnswerBoardDao {

	String SELECT_LIST_SQL = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ " ;
	String SELECT_ONE_SQL = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";
	String INSERT_SQL = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL,GROUPNOSEQ.NEXTVAL,1,0,'N',?,?,?,SYSDATE)";
	String UPDATE_SQL = " UPDATE ANSWERBOARD SET TITLE = ?, CONTENT = ? WHERE BOARDNO = ? ";
	String DELETE_SQL = " DELETE FROM ANSWERBOARD WHERE BOARDNO = ? ";
	
	String UPDATE_ANSWER_SQL = " UPDATE ANSWERBOARD SET GROUPSQ = GROUPSQ +1 " + 
			" WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) " + 
			" AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?) ";
	String INSERT_ANSWER_SQL = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL, " + 
			" (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?), " + 
			" (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO=?)+1, " + 
			" (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1, " + 
			" 'N',?,?,?,SYSDATE) ";
	
	String DELETE_ANSWER_SQL = " UPDATE ANSWERBOARD SET DELFLAG = ? WHERE BOARDNO = ? ";
	public List<AnswerBoardDto> selectlist();
	public AnswerBoardDto selectone(int boardno);
	public int insert(AnswerBoardDto dto);
	public int update(AnswerBoardDto dto);
	public int delete(int boardno);
	
	public int updateanswer(int boardno);
	public int insertanswer(AnswerBoardDto dto);
	public int updatedelflag(int boardno);
	public List<AnswerBoardDto> pagelist(int start , int end);
	
}
