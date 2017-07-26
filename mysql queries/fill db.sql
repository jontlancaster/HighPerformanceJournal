USE clearlinkjournal;

insert into users (first_name, last_name, username, password, created_date) -- The hashed password is pass
values ('Jon', 'Lancaster', 'jonlan', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Fernando', 'Salazar', 'fersal', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Scuba', 'Steve', 'steve', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Fred', 'Rogers', 'mrrogers', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Big', 'Bird', 'bigbird', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Oscar', 'Grouch', 'grouchy', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('The', 'Count', 'thecount', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate()),
  ('Cookie', 'Monster', 'cookies', '$2a$10$7G.D7OQFyAtQ9fZD6gF1eO.3EaQbAXF6izItCm7KCUcP2xE5ElgX2', curdate());

insert into user_roles (username, role)
values ('jonlan', 'ROLE_ADMIN'),
  ('jonlan', 'ROLE_COACH'),
  ('jonlan', 'ROLE_USER'),
  ('fersal', 'ROLE_ADMIN'),
  ('fersal', 'ROLE_COACH'),
  ('fersal', 'ROLE_USER'),
  ('steve', 'ROLE_COACH'),
  ('steve', 'ROLE_USER'),
  ('mrrogers', 'ROLE_COACH'),
  ('mrrogers', 'ROLE_USER'),
  ('bigbird', 'ROLE_USER'),
  ('grouchy', 'ROLE_USER'),
  ('thecount', 'ROLE_USER'),
  ('cookies', 'ROLE_USER');

insert into journals (journal_name, user_id, created_date)
values ('Jons Journal', 1, curdate()),
  ('fernandos journal', 2, curdate()),
  ('scubas journal', 3, curdate()),
  ('freds journal', 4, curdate()),
  ('bigs journal', 5, curdate()),
  ('oscars journal', 6, curdate()),
  ('thes journal', 7, curdate()),
  ('cookies journal', 8, curdate());

insert into journal_entries (journal_id, positive_review, goal, momentum, mental_toughness, willingness, determination, motivation, attitude, personal_impact, created_date)
values (1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (1, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (2, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (3, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (4, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (5, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (6, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (7, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate()),
  (8, 'positive review', 'goal', 'momentum', 1, 1, 1, 1, 1, 1, curdate());


