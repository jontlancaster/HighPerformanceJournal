USE clearlinkjournal;

insert into usertype
values (1, 'ADMIN'),
(2, 'COACH'),
(3, 'USER');

insert into user (first_name, last_name, username, password, user_type)
values ('Jon', 'Lancaster', 'jonlan', 'pass', 1),
('Fernando', 'Salazar', 'fersal', 'ferSal', 1),
('Scuba', 'Steve', 'steve', 'passwd', 2),
('Fred', 'Rogers', 'mrrogers', 'neighborhood', 2),
('Big', 'Bird', 'bigbird', 'yellow', 3),
('Oscar', 'Grouch', 'grouchy', 'garbage', 3),
('The', 'Count', 'thecount', 'ILoveToCount', 3),
('Cookie', 'Monster', 'cookies', 'cookies', 3);

insert into journal (journal_name, user_id)
values ('Jons Journal', 1),
('fernandos journal', 2),
('scubas journal', 3),
('freds journal', 4),
('bigs journal', 5),
('oscars journal', 6),
('thes journal', 7),
('thes journal 2', 7),
('cookies journal', 8),
('cookies journal 2', 8),
('cookies journal 3', 8);


insert into journalentry (journal_id, positive_review, goal, momentum, mental_toughness, willingness, determination, motivation, attitude, personal_impact)
values (1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1),
(8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1);


