-- Passwords are 'password' hashed with BCrypt
INSERT INTO users (name, email, password, role, is_active, created_at) VALUES 
('Admin User', 'admin@fitzone.com', '$2b$10$NlSXQWrA201m7CjbTvf1O.hrLL5c9YalN0HhOSlFgPzLHanHi8wvG', 'ROLE_ADMIN', true, CURRENT_TIMESTAMP),
('Trainer John', 'john@fitzone.com', '$2b$10$NlSXQWrA201m7CjbTvf1O.hrLL5c9YalN0HhOSlFgPzLHanHi8wvG', 'ROLE_TRAINER', true, CURRENT_TIMESTAMP),
('Member Jane', 'jane@fitzone.com', '$2b$10$NlSXQWrA201m7CjbTvf1O.hrLL5c9YalN0HhOSlFgPzLHanHi8wvG', 'ROLE_MEMBER', true, CURRENT_TIMESTAMP);

INSERT INTO memberships (type, price, duration_days, features) VALUES 
('BASIC', 29.00, 30, '["Unlimited classes","Locker room"]'),
('PRO', 59.00, 30, '["Unlimited classes","Personal trainer session"]'),
('ELITE', 99.00, 30, '["PRO + nutrition plan","priority booking","guest pass"]');

INSERT INTO members (user_id, membership_id, join_date, expiry_date, status) VALUES 
(3, 2, CURRENT_DATE, DATEADD('DAY', 30, CURRENT_DATE), 'ACTIVE');

INSERT INTO trainers (user_id, bio, speciality, rating, photo_url) VALUES 
(2, 'Experienced HIIT trainer', 'HIIT', 4.8, 'https://example.com/john.jpg');

INSERT INTO gym_classes (name, description, category, intensity, trainer_id, day_of_week, start_time, duration_min, capacity, current_count, image_url, is_active) VALUES 
('Morning HIIT', 'High intensity interval training', 'HIIT', 'ADVANCED', 1, 'MONDAY', '07:00:00', 45, 20, 0, 'https://example.com/hiit.jpg', true);
