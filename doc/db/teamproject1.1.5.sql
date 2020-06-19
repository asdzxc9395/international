drop table expense;

create table expense( 
   expense_no INTEGER 	 NOT NULL        -- 순번
   , name VARCHAR(50) 	    	         -- 사용내역
   , use_date DATE                             -- 사용일
   , use_price VARCHAR(30)                       -- 사용금액
   , approve_price VARCHAR(30)                  -- 승인금액
   , process_status VARCHAR(30)                  -- 처리상태
   , registration_date DATE DEFAULT CURRENT_DATE          -- 등록일
   , receipt VARCHAR(255)                          -- 영수증
   , process_date DATETIME                 -- 처리일시
   , remark VARCHAR(100)                	          -- 비고
);

ALTER TABLE expense
  ADD CONSTRAINT PK_expense -- 게시글 기본키
    PRIMARY KEY (
      expense_no -- 게시글번호
    );

ALTER TABLE expense
  MODIFY COLUMN expense_no INTEGER NOT NULL AUTO_INCREMENT
 