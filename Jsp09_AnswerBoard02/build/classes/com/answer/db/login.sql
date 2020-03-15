DROP TABLE LOGINBOARD;
DROP SEQENCE LOGINBOARDSEQ;

CREATE SEQUENCE LOGINBOARDSEQ;
CREATE TABLE LOGINBOARD(
	MYNO NUMBER NOT NULL,
	MYID VARCHAR2(100) NOT NULL,
	MYPW VARCHAR2(100) NOT NULL,
	MYNAME VARCHAR2(400) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYENABLED VARCHAR2(10) NOT NULL,
	MYROLE VARCHAR2(50) NOT NULL,
	CONSTRAINT MYNO_PK PRIMARY KEY(MYNO),
	CONSTRAINT MYID_UK UNIQUE(MYID),
	CONSTRAINT MYPHONE_UK UNIQUE(MYPHONE),
	CONSTRAINT MYENABLED_CK CHECK(MYENABLED IN('Y','N'))
);

SELECT * FROM LOGINBOARD;
INSERT INTO LOGINBOARD VALUES(LOGINBOARDSEQ.NEXTVAL,'ADMIN','111','어드민','010-1234-1234','Y','ADMIN');
INSERT INTO LOGINBOARD VALUES(LOGINBOARDSEQ.NEXTVAL,'USER','111','유저','010-4321-4321','Y','USER');



)