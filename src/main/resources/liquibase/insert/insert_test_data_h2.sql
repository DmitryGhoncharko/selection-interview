INSERT INTO question_type (question_type_name, date_created, last_date_updated, is_deleted) VALUES ('Type 1', current_timestamp, current_timestamp, false);
INSERT INTO question_type (question_type_name, date_created, last_date_updated, is_deleted) VALUES ('Type 2', current_timestamp, current_timestamp, false);

INSERT INTO question (question_body, question_type_id, date_created, last_date_updated, is_deleted) VALUES ('Question 1', 1, current_timestamp, current_timestamp, false);
INSERT INTO question (question_body, question_type_id, date_created, last_date_updated, is_deleted) VALUES ('Question 2', 2, current_timestamp, current_timestamp, false);

INSERT INTO answer (answer_body, answer_correct, question_id, date_created, last_date_updated, is_deleted) VALUES ('Answer 1', true, 1, current_timestamp, current_timestamp, false);
INSERT INTO answer (answer_body, answer_correct, question_id, date_created, last_date_updated, is_deleted) VALUES ('Answer 2', false, 1, current_timestamp, current_timestamp, false);
