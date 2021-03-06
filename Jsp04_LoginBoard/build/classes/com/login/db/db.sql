DROP SEQUENCE MEMBERBOARDSEQ;
DROP TABLE MEMBERBOARD;

CREATE SEQUENCE MEMBERBOARDSEQ;

--번호 , 아이디, 비밀번호 , 이름 , 주소 , 전화번호 
--이메일, 가입여부(Y/N),등급(ADMIN/USER/...)
CREATE TABLE MEMBERBOARD(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYENABLED VARCHAR2(2) NOT NULL,
	MYROLE VARCHAR2(100) NOT NULL,
	
	CONSTRAINT MYMEMBER_PK PRIMARY KEY(MYNO),
	CONSTRAINT MYMEMBER_UK_ID UNIQUE(MYID),
	CONSTRAINT MYMEMBER_UK_PHONE UNIQUE(MYPHONE),
	CONSTRAINT MYMEMBER_UK_EMAIL UNIQUE(MYEMAIL),
	CONSTRAINT MYMEMBER_CK_ENABLED CHECK(MYENABLED IN('Y','N'))
);

INSERT INTO MEMBERBOARD VALUES(MEMBERBOARDSEQ.NEXTVAL,'LLL','111','이름','경기도 고양시','010-1234-5678','ADS@NAVER.COM','Y','ADMIN');
INSERT INTO MEMBERBOARD VALUES(MEMBERBOARDSEQ.NEXTVAL,'ccc','111','123','경기도 고양시','010-5555-5123','asdH@NAVER.COM','Y','USER');
INSERT INTO MEMBERBOARD VALUES(MEMBERBOARDSEQ.NEXTVAL,'bbb','111','ad','경기도 고양시','010-1234-5111','111@NAVER.COM','Y','USER');
UPDATE MEMBERBOARD SET MYPW = '111', MYADDR = '경기도',MYPHONE='010-0000-0000' ,MYEMAIL = '000@a.ccom' WHERE MYNO = '2';
SELECT * FROM MEMBERBOARD;
UPDATE MEMBERBOARD SET MYROLE = 'ADMIN' WHERE MYNO = 7;
DELETE FROM MEMBERBOARD WHERE MYNO='21';
DELETE FROM MEMBERBOARD WHERE MYNO='2';
