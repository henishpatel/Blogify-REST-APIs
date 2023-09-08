-- Insert data for User entity
INSERT INTO users (user_name, name, email, password, about)
VALUES ('usersarah','Sarah' ,'sarah@example.com', 'sarahpass', 'Software engineer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('usermichael','Michael', 'michael@example.com', 'michaelpass', 'Data scientist');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('useremily','Emily', 'emily@example.com', 'emilypass', 'Front-end developer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('userdaniel','Daniel', 'daniel@example.com', 'danielpass', 'Game developer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('usersophia','Sophia', 'sophia@example.com', 'sophiapass', 'Graphic designer');

INSERT INTO users (user_name, name, email, password, about)
VALUES ('usersophia','Sophia', 'sophia@example.com', 'sophiapass', 'Graphic designer');

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
VALUES ('Digital Art Showcase', 'A collection of digital art pieces.', 'digital-art.png', NOW(), 1, 2);

INSERT INTO post (post_title, content, image_name, added_date, user_id, category_id)
VALUES ('Healthy Lifestyle Tips', 'Tips for a healthier life.', 'healthy-lifestyle.png', NOW(), 2, 1);

-- Insert data for Comment entity
INSERT INTO comments (content, post_id)
VALUES ('Beautiful artwork!', 1);

INSERT INTO comments (content, post_id)
VALUES ('I appreciate the health tips.', 2);