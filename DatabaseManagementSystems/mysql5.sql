CREATE TABLE RESULT (
	ROLL INTEGER,
	SCODE CHAR(5),
	MARKS INTEGER,
	PRIMARY KEY (ROLL, SCODE)
);

CREATE TABLE BACKPAPER (
	ROLL INTEGER,
	SCODE CHAR(5),
	PRIMARY KEY (ROLL, SCODE),
	FOREIGN KEY (ROLL, SCODE)
		REFERENCES RESULT(ROLL, SCODE)
);

DELIMITER //

CREATE TRIGGER BACKPAPER_DELETE_TRIGGER
	BEFORE INSERT ON RESULT
	FOR EACH ROW BEGIN
		IF NEW.MARKS >= 50 THEN
			DELETE FROM BACKPAPER
				WHERE NEW.ROLL = ROLL
				AND NEW.SCODE = SCODE;
		END IF;
	END; //

CREATE TRIGGER BACKPAPER_INSERT_TRIGGER
	AFTER INSERT ON RESULT
	FOR EACH ROW BEGIN
		IF NEW.MARKS < 50 THEN
			INSERT INTO BACKPAPER VALUES (
				NEW.ROLL,
				NEW.SCODE
			);
		END IF;
	END; //

DELIMITER ;
				
CREATE TABLE REQUEST (
	REQ_NO INTEGER PRIMARY KEY,
	REQ_DT DATE
);

CREATE TABLE SERVICE_LOG (
	ENTRY_NO INTEGER PRIMARY KEY,
	REQ_CNT INTEGER,
	SERVICE_DT DATE
);

DELIMITER //

CREATE TRIGGER SERVICE_LOG_TRIGGER
	AFTER DELETE ON REQUEST
	FOR EACH ROW BEGIN
		IF  THEN

		END IF;
	END; //

DELIMITER ;