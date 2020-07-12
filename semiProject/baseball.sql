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

ALTER TABLE b_member
MODIFY (m_leaveyn VARCHAR2(8) DEFAULT '20020129' NOT NULL);
