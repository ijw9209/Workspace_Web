DROP TABLE CALBOARD;
DROP SEQUENCE CALBOARDSEQ;

CREATE SEQUENCE CALBOARDSEQ;

CREATE TABLE CALBOARD(
	SEQ NUMBER PRIMARY KEY,
	ID VARCHAR2(2000) NOT NULL,
	TITLE VARCHAR2(1000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	MDATE VARCHAR2(12) NOT NULL,
	REGDATE DATE NOT NULL
);
 SELECT * FROM CALBOARD WHERE ID = 'kh' AND SUBSTR(MDATE,1,8) = '20190701' AND SEQ BETWEEN 1 AND 5
 
 SELECT R,SEQ,ID,TITLE,CONTENT,MDATE,REGDATE
 FROM(SELECT ROWNUM AS R,SEQ,ID,TITLE,CONTENT,MDATE,REGDATE 
 FROM CALBOARD WHERE ID = 'kh' AND SUBSTR(MDATE,1,8) = '20190701')
 WHERE R BETWEEN 1 AND 5;

 
 SELECT R, BOARDNO,TITLE,WRITER,REGDATE FROM "
				+ " (SELECT ROWNUM AS R,BOARDNO, TITLE, WRITER, REGDATE FROM "
				+ " (SELECT BOARDNO, TITLE, WRITER, REGDATE  FROM ANSWERBOARD ORDER BY BOARDNO)) "
				+ " WHERE R BETWEEN ? AND ?
 
 SELECT * FROM CALBOARD;

SELECT SEQ,ID,TITLE,CONTENT,MDATE,REGDATE FROM CALBOARD WHERE ID = ? AND SUBSTR(MADATE,1,8) = ?

SELECT *
FROM(SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN,
		SEQ, ID, TITLE, CONTENT , MDATE , REGDATE 
		FROM CALBOARD
		WHERE ID = 'kh' AND SUBSTR(MDATE,1,6) = '201907')
		WHERE RN BETWEEN 1 AND 3 ;