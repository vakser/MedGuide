INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Bisoprolol', 'Bisoprolol');
INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Doxazosin', 'Doxazosin');
INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Metformin', 'Metformin');
INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Fentanyl', 'Fentanyl');
INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Alteplase', 'Actilyse');
INSERT INTO `med_assistant`.`medications` (`active_ingredient`, `name`) VALUES ('Acetylsalicylic acid', 'Aspirin');

INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Headache');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Dizziness');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Blurry vision');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Excessive hunger');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Chest pain');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Shortness of breath');
INSERT INTO `med_assistant`.`signs` (`name`) VALUES ('Fear of death');

INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('BP measurement');
INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('Chest X-ray');
INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('HbA1c test');
INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('Urine glucose test');
INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('ECG');
INSERT INTO `med_assistant`.`diagnostic_procedures` (`name`) VALUES ('Cardiac enzymes');

INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Bisoprolol', 10, 'Bisoprolol');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Bisoprolol', 10, 'Monocor');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Bisoprolol', 10, 'Zebeta');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Doxazosin', 10, 'Doxazosin');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Doxazosin', 10, 'Cardura');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Metformin', 10, 'Metformin');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Metformin', 10, 'Fortamet');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Metformin', 10, 'Riomet');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Fentanyl', 10, 'Fentanyl');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Alteplase', 10, 'Actilyse');
INSERT INTO `med_assistant`.`medications_in_storage` (`active_ingredient`, `amount`, `name`) VALUES ('Acetylsalicylic acid', 10, 'Aspirin');

INSERT INTO `med_assistant`.`roles` (`name`, `description`) VALUES ('Admin', 'Manage everything');
INSERT INTO `med_assistant`.`roles` (`name`, `description`) VALUES ('Physician', 'View list of diseases and prescribe medications');
INSERT INTO `med_assistant`.`roles` (`name`, `description`) VALUES ('Pharmacist', 'View prescriptions, dispence prescribed medications, update medication quantity in storage');

INSERT INTO `med_assistant`.`users` (`first_name`, `last_name`, `email`, `password`) VALUES ('John', 'Doe', 'john@gmail.com', '$2a$10$NwmXzEBWKde4fPmcOErtA.Tx/.IToY29i8w/PC8LyoV1OCiXn.HuO');
INSERT INTO `med_assistant`.`users` (`first_name`, `last_name`, `email`, `password`) VALUES ('Charles', 'Oliveira', 'charles@gmail.com', '$2a$10$R504iRnhqFnd.loByFrAceOm7t/x3t1fiVkI/pXIMMPtXt/qBm3ta');
INSERT INTO `med_assistant`.`users` (`first_name`, `last_name`, `email`, `password`) VALUES ('Chad', 'Darby', 'chad@gmail.com', '$2a$10$obkLVBUgTYtSkx325fWbuuMoviRufdMuOn.yK1sWL78cSmKNfAxIO');

INSERT INTO `med_assistant`.`user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `med_assistant`.`user_role` (`user_id`, `role_id`) VALUES (2, 2);
INSERT INTO `med_assistant`.`user_role` (`user_id`, `role_id`) VALUES (3, 3);