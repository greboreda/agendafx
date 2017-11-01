SET @johnDoeId = PERSON_SEQ.NEXTVAL;
SET @michaelJacksonId = PERSON_SEQ.NEXTVAL;

SET @firstPhoneId = PHONE_SEQ.NEXTVAL;
SET @secondPhoneId = PHONE_SEQ.NEXTVAL;

INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (@johnDoeId, 'John', 'Doe');
INSERT INTO PERSON (ID, FIRSTNAME, LASTNAME) VALUES (@michaelJacksonId, 'Michael', 'Jackson');

INSERT INTO PHONE(ID, `NUMBER`, PREFIX) VALUES (@firstPhoneId, '666666666', 34);
INSERT INTO PHONE(ID, `NUMBER`, PREFIX) VALUES (@secondPhoneId, '696969696', 34);

INSERT INTO PERSONPHONE(PERSONID, PHONEID) VALUES (@johnDoeId, @firstPhoneId);
INSERT INTO PERSONPHONE(PERSONID, PHONEID) VALUES (@michaelJacksonId, @secondPhoneId);

COMMIT;