-- Insert data for User entity
INSERT INTO users (user_name, name, email, password, about)
VALUES ('admin','admin' ,'admin@example.com', '$2a$10$FgMf44MrR0brclqh8WLWjOInsKcmGCzX7E9jfYK2VB2WMlbemly4S', 'admin');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('usersarah','Sarah' ,'sarah@example.com', '$2a$10$fLdyiDajozr68SgEjLo64eAxU02IT3YpF0H9V7OGJL2ia6mqDpkQK', 'Software engineer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('usermichael','Michael', 'michael@example.com', '$2a$10$0n3xaeaToXbiDza7ARsEx.8/DOKjQaIuhYpwZnV5Sj6nLwTSZNJSC', 'Data scientist');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('useremily','Emily', 'emily@example.com', '$2a$10$eSzkJY93HDqUjVDnddTQPO4a9DnaUErG8x6qxfTK4n8cAjEgFHNv2', 'Front-end developer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('userdaniel','Daniel', 'daniel@example.com', '$2a$10$IE.rG7b8ibrWynGpbaSFeu1XTmC8QA/7Jml/qfG5qHoAeS7QlyV86', 'Game developer');

-- Give admin role to admin user
INSERT INTO user_role(user,role)
VALUEs (1,501);

-- Give user role to others
INSERT INTO user_role(user,role)
VALUEs (2,502);

INSERT INTO user_role(user,role)
VALUEs (3,502);

INSERT INTO user_role(user,role)
VALUEs (4,502);

INSERT INTO user_role(user,role)
VALUEs (5,502);

-- Insert data for Category entity
INSERT INTO categories (name, description)
VALUES ('Art', 'Creative arts and design');

INSERT INTO categories (name, description)
VALUES ('Health', 'Health and wellness');

INSERT INTO categories (name, description)
VALUES ('Travel', 'Travel experiences');

INSERT INTO categories (name, description)
VALUES ('Food', 'Culinary delights');

-- Insert data for Post entity
INSERT INTO post (post_title, content, image_name, added_date, user_id, category_id)
VALUES ('Digital Art Showcase', 'A collection of digital art pieces.', 'digital-art.png', NOW(), 2, 1);

INSERT INTO post (post_title, content, image_name, added_date, user_id, category_id)
VALUES ('Healthy Lifestyle Tips', 'Tips for a healthier life.', 'healthy-lifestyle.png', NOW(), 3, 2);

-- Insert data for Comment entity
INSERT INTO comments (content, post_id, user_id)
VALUES ('Beautiful artwork!', 1,4);

INSERT INTO comments (content, post_id, user_id)
VALUES ('I appreciate the health tips.', 2,5);