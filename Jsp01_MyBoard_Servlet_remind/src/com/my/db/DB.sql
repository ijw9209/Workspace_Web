DROP SEQUENCE MYSEQ;
DROP TABLE MYBOARD;

CREATE SEQUENCE MYSEQ;
CREATE TABLE MYBOARD(
	MYNO NUMBER PRIMARY KEY,
	MYNAME VARCHAR2(500) NOT NULL,
	MYTITLE VARCHAR2(2000) NOT NULL,
	MYCONTENT VARCHAR2(4000) NOT NULL,
	MYDATE DATE NOT NULL
);
INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL,'관리자','TEST','TEST1234',SYSDATE);
SELECT MYNO,MYNAME,MYTITLE,MYCONTENT,MYDATE FROM MYBOARD ORDER BY MYNO DESC;

DROP TABLE MEMBERMY;
DROP SEQUENCE MEMBERMYSEQ;

CREATE SEQUENCE MEMBERMYSEQ;

CREATE TABLE MEMBERMY(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	
	CONSTRAINT MEMBERMY_PK PRIMARY KEY(MYNO),
	CONSTRAINT MEMBERMY_UK_ID UNIQUE(MYID),
	CONSTRAINT MEMBERMY_UK_PHONE UNIQUE(MYPHONE),
	CONSTRAINT MEMBERMY_UK_EMAIL UNIQUE(MYEMAIL),
	CONSTRAINT MEMBERMY_CK_ENABLED CHECK(MYENABLED IN('Y','N'))
);
INSERT INTO MEMBERMY VALUES(MEMBERMYSEQ.NEXTVAL,'LLL','111','이름','경기도 고양시','010-1234-5678','ADS@NAVER.COM','Y','ADMIN');
INSERT INTO MEMBERMY VALUES(MEMBERMYSEQ.NEXTVAL,'ccc','111','123','경기도 고양시','010-5555-5123','asdH@NAVER.COM','Y','USER');


SELECT * FROM MEMBERMY;