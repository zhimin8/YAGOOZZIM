SELECT * FROM B_MEMBER;


DELETE FROM b_member WHERE m_id='1344758323';
commit;

alter table b_member add m_leaveyn VARCHAR2(10 byte); -- b_member 테이블 컬럼 추가

DELETE FROM b_member WHERE m_id='jimin';

UPDATE B_MEMBER SET M_LEAVEYN='N';

--0509지민 sql변경 완료.
ALTER TABLE BOARD_NOTICE ADD N_TYPE VARCHAR2(15);

ALTER TABLE BOARD_NOTICE ADD n_available int;

SELECT * FROM board_notice;

ALTER TABLE BOARD_NOTICE DROP COLUMN R_AVAILABLE;

ALTER TABLE b_member MODIFY (m_leaveyn VARCHAR2(8) DEFAULT '20020129' NOT NULL);

-- spring 변환 중 
select *from user_sequences;

select * from bb_playdata;
select * from bb_playdata where p_day in ('%'||'07.25'||'%', '0709', '%'||'07.27'||'%');
select *from bb_playdata where p_day in    (     07.29    ,    07.30    ,    07.31    );
INSERT INTO BB_PLAYDATA VALUES (P_NO.NEXTVAL, '07.30', '18:00', 'SK vs 두산');

select *from board_notice;
SELECT *FROM BOARD_FILE;

ALTER TABLE board_notice DROP COLUMN original_filepath;
ALTER TABLE board_notice DROP COLUMN rename_filepath;
commit
CREATE TABLE BOARD_FILE(F_IDX NUMBER, NOTICENO NUMBER NOT NULL, originFileName VARCHAR2(260 BYTE), renameFileName VARCHAR2(260 BYTE), savePath varchar2(260 byte), PRIMARY KEY (F_IDX));
SELECT *FROM BOARD_FILE;
CREATE SEQUENCE S_TB_FILE_IDX START WITH 100000 INCREMENT BY 1 NOMAXVALUE;
CREATE SEQUENCE BOARD_NOTICE_IDX START WITH 100000 INCREMENT BY 1 NOMAXVALUE;
DROP SEQUENCE BOARD_NOTICE;
commit

ALTER TABLE board_notice DROP COLUMN N_AVAILABLE;

select *from b_member;
update b_member set m_password = 'test080501!'  where m_id = 'test080301';


