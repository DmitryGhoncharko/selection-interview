
INSERT INTO question_type (question_type_name) VALUES ('Type 1');
INSERT INTO question_type (question_type_name) VALUES ('Type 2');



INSERT INTO question (question_body, question_type_id) VALUES ('Question 1', 1);
INSERT INTO question (question_body, question_type_id) VALUES ('Question 2', 2);



INSERT INTO answer (answer_body, answer_correct, question_id) VALUES ('Answer 1', true, 1);
INSERT INTO answer (answer_body, answer_correct, question_id) VALUES ('Answer 2', false, 1);

