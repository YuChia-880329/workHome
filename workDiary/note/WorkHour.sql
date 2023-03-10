BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DIARY_CONTENT';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DIARY_WORK';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DIARY_PHASE';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE DIARY_PROJECT';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/

-- DIARY_PROJECT

CREATE TABLE DIARY_PROJECT
(
  PROJECT_ID INT NOT NULL,
  PROJECT_NAME VARCHAR2(50),
  CONSTRAINT DIARY_PROJECT_PK PRIMARY KEY (PROJECT_ID)  
);

COMMENT ON COLUMN DIARY_PROJECT.PROJECT_ID IS '專案編號';
COMMENT ON COLUMN DIARY_PROJECT.PROJECT_NAME IS '專案名稱';

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE DIARY_PROJECT_SEQ';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
CREATE SEQUENCE DIARY_PROJECT_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into DIARY_PROJECT (PROJECT_ID, PROJECT_NAME) values (DIARY_PROJECT_SEQ.NEXTVAL, '教育訓練');
Insert into DIARY_PROJECT (PROJECT_ID, PROJECT_NAME) values (DIARY_PROJECT_SEQ.NEXTVAL, '休假');
Insert into DIARY_PROJECT (PROJECT_ID, PROJECT_NAME) values (DIARY_PROJECT_SEQ.NEXTVAL, '會議');
Insert into DIARY_PROJECT (PROJECT_ID, PROJECT_NAME) values (DIARY_PROJECT_SEQ.NEXTVAL, '一般事務');
Insert into DIARY_PROJECT (PROJECT_ID, PROJECT_NAME) values (DIARY_PROJECT_SEQ.NEXTVAL, '其它活動');

-- DIARY_PHASE

CREATE TABLE DIARY_PHASE
(
  PHASE_ID INT NOT NULL,
  PHASE_NAME VARCHAR2(50),
  PROJECT_ID INT NOT NULL,
  CONSTRAINT DIARY_PHASE_PK PRIMARY KEY (PHASE_ID),
  CONSTRAINT DIARY_PHASE_PROJECT_ID_FK FOREIGN KEY (PROJECT_ID) REFERENCES DIARY_PROJECT (PROJECT_ID)
);

COMMENT ON COLUMN DIARY_PHASE.PHASE_ID IS '階段編號';
COMMENT ON COLUMN DIARY_PHASE.PHASE_NAME IS '階段名稱';
COMMENT ON COLUMN DIARY_PHASE.PROJECT_ID IS '所屬專案編號';

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE DIARY_PHASE_SEQ';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
CREATE SEQUENCE DIARY_PHASE_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '內部', 1);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '外部', 1);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '休假中', 2);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '工作會議', 3);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '研讀', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '實作', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '文件撰寫', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '教育訓練', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, 'Mail處理', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '工作日誌', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '員工旅遊', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '員工健檢', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '管理部事務', 4);
Insert into DIARY_PHASE (PHASE_ID, PHASE_NAME, PROJECT_ID) values (DIARY_PHASE_SEQ.NEXTVAL, '無法歸類', 5);


-- DIARY_WORK

CREATE TABLE DIARY_WORK
(
  WORK_ID INT NOT NULL,
  WORK_NAME VARCHAR2(50),
  PHASE_ID INT NOT NULL,
  CONSTRAINT DIARY_WORK_PK PRIMARY KEY (WORK_ID),
  CONSTRAINT DIARY_WORK_PHASE_ID_FK FOREIGN KEY (PHASE_ID) REFERENCES DIARY_PHASE (PHASE_ID)
);

COMMENT ON COLUMN DIARY_WORK.WORK_ID IS '工作項目編號';
COMMENT ON COLUMN DIARY_WORK.WORK_NAME IS '工作項目名稱';
COMMENT ON COLUMN DIARY_WORK.PHASE_ID IS '所屬階段編號';

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE DIARY_WORK_SEQ';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
CREATE SEQUENCE DIARY_WORK_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;

Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '新進人員教育訓練', 1);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '資深PG教育訓練', 1);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, 'SD培訓', 1);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '外部教育訓練', 2);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '研討會', 2);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '公假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '事假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '病假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '公傷假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '婚假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '喪假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '分娩假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '陪產假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '特休假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '補休假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '生理假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '特別假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '生日假', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '國定假日', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '其它假日', 3);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '公司會議', 4);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '部門會議', 4);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '專案會議', 4);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '外部會議', 4);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '工作資料研讀', 5);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '工作技術實作', 6);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '工作性文件撰寫', 7);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '講師', 8);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, 'Mail收發', 9);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '工作日誌填寫', 10);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '參加員工旅遊', 11);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '參加員工健檢', 12);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '管理部專屬事務', 13);
Insert into DIARY_WORK (WORK_ID, WORK_NAME, PHASE_ID) values (DIARY_WORK_SEQ.NEXTVAL, '自行填寫', 14);

-- DIARY_CONTENT

CREATE TABLE DIARY_CONTENT
(
  CONTENT_ID INT NOT NULL,
  CONTENT_DATE DATE NOT NULL,
  PROJECT_ID INT NOT NULL,
  PHASE_ID INT NOT NULL,
  WORK_ID INT NOT NULL,
  TEXT VARCHAR2(100),
  WORK_HOUR DECIMAL(3, 1) NOT NULL,
  CONSTRAINT DIARY_CONTENT_PK PRIMARY KEY (CONTENT_ID),
  CONSTRAINT DIARY_CONTENT_PROJECT_ID_FK FOREIGN KEY (PROJECT_ID) REFERENCES DIARY_PROJECT (PROJECT_ID),
  CONSTRAINT DIARY_CONTENT_PHASE_ID_FK FOREIGN KEY (PHASE_ID) REFERENCES DIARY_PHASE (PHASE_ID),
  CONSTRAINT DIARY_CONTENT_WORK_ID_FK FOREIGN KEY (WORK_ID) REFERENCES DIARY_WORK (WORK_ID)
);

COMMENT ON COLUMN DIARY_CONTENT.CONTENT_ID IS '日誌編號';
COMMENT ON COLUMN DIARY_CONTENT.CONTENT_DATE IS '日誌日期';
COMMENT ON COLUMN DIARY_CONTENT.PROJECT_ID IS '日誌專案編號';
COMMENT ON COLUMN DIARY_CONTENT.PHASE_ID IS '日誌階段編號';
COMMENT ON COLUMN DIARY_CONTENT.WORK_ID IS '日誌工作項目編號';
COMMENT ON COLUMN DIARY_CONTENT.TEXT IS '日誌文字描述';
COMMENT ON COLUMN DIARY_CONTENT.WORK_HOUR IS '工時';

BEGIN
  EXECUTE IMMEDIATE 'DROP SEQUENCE DIARY_CONTENT_SEQ';
  EXCEPTION
    WHEN OTHERS THEN NULL;
END;
/
CREATE SEQUENCE DIARY_CONTENT_SEQ
INCREMENT BY 1
START WITH 1
NOMAXVALUE
NOCYCLE
NOCACHE;