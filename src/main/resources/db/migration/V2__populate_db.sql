INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
    ('John Doe', '1985-04-12', 'Senior', 6000),
    ('Jane Smith', '1990-08-21', 'Middle', 4500),
    ('Alice Johnson', '1995-12-30', 'Junior', 3000),
    ('Bob Brown', '2000-01-15', 'Trainee', 800),
    ('Charlie Black', '1988-03-19', 'Senior', 7000),
    ('Diana White', '1993-05-23', 'Middle', 4800),
    ('Eve Green', '1982-09-11', 'Senior', 6500),
    ('Frank Gray', '1998-11-02', 'Junior', 3500),
    ('Grace Blue', '2001-07-14', 'Trainee', 900),
    ('Hank Purple', '1987-10-28', 'Middle', 5000);

INSERT INTO client (NAME) VALUES
    ('Client A'),
    ('Client B'),
    ('Client C'),
    ('Client D'),
    ('Client E');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
    (1, '2023-01-01', '2023-06-30'),
    (1, '2023-02-01', '2023-08-31'),
    (2, '2023-03-01', '2023-09-30'),
    (2, '2023-04-01', '2023-12-31'),
    (3, '2023-05-01', '2024-01-31'),
    (3, '2023-06-01', '2024-03-31'),
    (4, '2023-07-01', '2024-05-31'),
    (4, '2023-08-01', '2024-07-31'),
    (5, '2023-09-01', '2024-09-30'),
    (5, '2023-10-01', '2024-12-31');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 4), (2, 5), (2, 6),
    (3, 7), (3, 8), (3, 9),
    (4, 10), (4, 1), (4, 2),
    (5, 3), (5, 4), (5, 5),
    (6, 6), (6, 7), (6, 8),
    (7, 9), (7, 10), (7, 1),
    (8, 2), (8, 3), (8, 4),
    (9, 5), (9, 6), (9, 7),
    (10, 8), (10, 9), (10, 10);