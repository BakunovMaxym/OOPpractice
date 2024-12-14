-- H2 2.3.232;
SET DB_CLOSE_DELAY -1;        
;             
CREATE USER IF NOT EXISTS "SA" SALT 'a6ca8818f77bdbb0' HASH '7ce3df4f21aca2ac69945329dcd208d3ec7284a64f7cf594b8cc6d5b09a99880' ADMIN;         
CREATE SEQUENCE "PUBLIC"."DAYS_SEQ" START WITH 1 RESTART WITH 1601 INCREMENT BY 50;           
CREATE SEQUENCE "PUBLIC"."LESSON_SEQ" START WITH 1 RESTART WITH 4801 INCREMENT BY 50;         
CREATE CACHED TABLE "PUBLIC"."flyway_schema_history"(
    "installed_rank" INTEGER NOT NULL,
    "version" CHARACTER VARYING(50),
    "description" CHARACTER VARYING(200) NOT NULL,
    "type" CHARACTER VARYING(20) NOT NULL,
    "script" CHARACTER VARYING(1000) NOT NULL,
    "checksum" INTEGER,
    "installed_by" CHARACTER VARYING(100) NOT NULL,
    "installed_on" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "execution_time" INTEGER NOT NULL,
    "success" BOOLEAN NOT NULL
);               
ALTER TABLE "PUBLIC"."flyway_schema_history" ADD CONSTRAINT "PUBLIC"."flyway_schema_history_pk" PRIMARY KEY("installed_rank");
-- 2 +/- SELECT COUNT(*) FROM PUBLIC.flyway_schema_history;   
INSERT INTO "PUBLIC"."flyway_schema_history" VALUES
(-1, NULL, '<< Flyway Schema History table created >>', 'TABLE', '', NULL, 'SA', TIMESTAMP '2024-12-11 21:23:28.57635', 0, TRUE),
(1, '1', 'init lesson table', 'SQL', 'V1__init_lesson_table.sql', -799447110, 'SA', TIMESTAMP '2024-12-11 21:23:28.647348', 19, TRUE);
CREATE INDEX "PUBLIC"."flyway_schema_history_s_idx" ON "PUBLIC"."flyway_schema_history"("success" NULLS FIRST);               
CREATE CACHED TABLE "PUBLIC"."LESSONS"(
    "ID" INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL,
    "NAME" CHARACTER VARYING(255) NOT NULL,
    "TEACHER" CHARACTER VARYING(255) NOT NULL,
    "CLASSROOM" INTEGER NOT NULL
);         
ALTER TABLE "PUBLIC"."LESSONS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_2" PRIMARY KEY("ID");      
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.LESSONS; 
CREATE CACHED TABLE "PUBLIC"."DAY_LESSONS"(
    "DAY_ID" INTEGER NOT NULL,
    "LESSON_ID" INTEGER NOT NULL
);             
-- 14 +/- SELECT COUNT(*) FROM PUBLIC.DAY_LESSONS;            
INSERT INTO "PUBLIC"."DAY_LESSONS" VALUES
(2, 5),
(2, 8),
(2, 9),
(3, 1),
(3, 6),
(4, 4),
(4, 7),
(4, 8),
(5, 4),
(5, 7),
(5, 8),
(1, 2),
(1, 7),
(1, 9);       
CREATE CACHED TABLE "PUBLIC"."DAYS"(
    "ID" INTEGER NOT NULL,
    "NAME" CHARACTER VARYING(255)
);       
ALTER TABLE "PUBLIC"."DAYS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_1" PRIMARY KEY("ID");         
-- 5 +/- SELECT COUNT(*) FROM PUBLIC.DAYS;    
INSERT INTO "PUBLIC"."DAYS" VALUES
(1, U&'\041f''\044f\0442\043d\0438\0446\044f'),
(2, U&'\0412\0456\0432\0442\043e\0440\043e\043a'),
(3, U&'\0421\0435\0440\0435\0434\0430'),
(4, U&'\0427\0435\0442\0432\0435\0440'),
(5, U&'\042f\043a\0438\0439\0441\044c \043f\0440\0438\043a\043e\043b');          
CREATE CACHED TABLE "PUBLIC"."LESSON"(
    "CLASSROOM" INTEGER NOT NULL,
    "ID" INTEGER NOT NULL,
    "NAME" CHARACTER VARYING(255),
    "TEACHER" CHARACTER VARYING(255)
);           
ALTER TABLE "PUBLIC"."LESSON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_85" PRIMARY KEY("ID");      
-- 12 +/- SELECT COUNT(*) FROM PUBLIC.LESSON; 
INSERT INTO "PUBLIC"."LESSON" VALUES
(123, 1, U&'\044f\043a\0438\0439\0441\044c \043a\0440\0443\0442\0438\0439 \043f\0440\0435\0434\043c\0435\0442', U&'\043a\0440\0443\0442\0438\0439 \0432\0447\0438\0442\0435\043b\044c'),
(102, 2, U&'\0423\043a\0440\0430\0457\043d\0441\044c\043a\0430 \043c\043e\0432\0430', U&'\041e\043b\0435\043d\0430 \041f\0435\0442\0440\0456\0432\043d\0430'),
(203, 3, U&'\0406\0441\0442\043e\0440\0456\044f \0423\043a\0440\0430\0457\043d\0438', U&'\041f\0435\0442\0440\043e \0421\0435\043c\0435\043d\043e\0432\0438\0447'),
(205, 4, U&'\0411\0456\043e\043b\043e\0433\0456\044f', U&'\0413\0430\043b\0438\043d\0430 \0412\0430\0441\0438\043b\0456\0432\043d\0430'),
(208, 5, U&'\0424\0456\0437\0438\043a\0430', U&'\0421\0435\0440\0433\0456\0439 \041c\0438\0445\0430\0439\043b\043e\0432\0438\0447'),
(107, 6, U&'\0425\0456\043c\0456\044f', U&'\0410\043d\043d\0430 \042e\0440\0456\0457\0432\043d\0430'),
(210, 7, U&'\0413\0435\043e\0433\0440\0430\0444\0456\044f', U&'\041c\0430\0440\0456\044f \0412\043e\043b\043e\0434\0438\043c\0438\0440\0456\0432\043d\0430'),
(305, 8, U&'\0406\043d\0444\043e\0440\043c\0430\0442\0438\043a\0430', U&'\041e\043b\0435\043a\0441\0430\043d\0434\0440 \0421\0435\0440\0433\0456\0439\043e\0432\0438\0447'),
(402, 9, U&'\0424\0456\0437\0438\0447\043d\0430 \043a\0443\043b\044c\0442\0443\0440\0430', U&'\042e\0440\0456\0439 \041e\043b\0435\043a\0441\0430\043d\0434\0440\043e\0432\0438\0447'),
(303, 10, U&'\0410\043d\0433\043b\0456\0439\0441\044c\043a\0430 \043c\043e\0432\0430', U&'\041a\0430\0442\0435\0440\0438\043d\0430 \0410\043d\0434\0440\0456\0457\0432\043d\0430'),
(303, 1552, U&'\0410\043d\0433\043b\0456\0439\0441\044c\043a\0430 \043c\043e\0432\0430', U&'\041a\0430\0442\0435\0440\0438\043d\0430 \0410\043d\0434\0440\0456\0457\0432\043d\0430'),
(1, 3152, 'ls1', 'teacher1'); 
CREATE GLOBAL TEMPORARY TABLE "PUBLIC"."HTE_DAYS"(
    "ID" INTEGER,
    "RN_" INTEGER NOT NULL,
    "NAME" CHARACTER VARYING(255)
);     
ALTER TABLE "PUBLIC"."HTE_DAYS" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_D" PRIMARY KEY("RN_");    
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.HTE_DAYS;
CREATE GLOBAL TEMPORARY TABLE "PUBLIC"."HTE_LESSON"(
    "CLASSROOM" INTEGER,
    "ID" INTEGER,
    "RN_" INTEGER NOT NULL,
    "NAME" CHARACTER VARYING(255),
    "TEACHER" CHARACTER VARYING(255)
);  
ALTER TABLE "PUBLIC"."HTE_LESSON" ADD CONSTRAINT "PUBLIC"."CONSTRAINT_8" PRIMARY KEY("RN_");  
-- 0 +/- SELECT COUNT(*) FROM PUBLIC.HTE_LESSON;              
ALTER TABLE "PUBLIC"."DAY_LESSONS" ADD CONSTRAINT "PUBLIC"."FKNOH7YTXU64DISQ8DNH0YBI6JI" FOREIGN KEY("LESSON_ID") REFERENCES "PUBLIC"."LESSON"("ID") NOCHECK; 
ALTER TABLE "PUBLIC"."DAY_LESSONS" ADD CONSTRAINT "PUBLIC"."FK2VGWV8TJMJY3UYBL4TFOFLRKM" FOREIGN KEY("DAY_ID") REFERENCES "PUBLIC"."DAYS"("ID") NOCHECK;      
