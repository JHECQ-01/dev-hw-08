SELECT ID AS NAME,
       DATE_PART('year', AGE(FINISH_DATE, START_DATE)) * 12 + DATE_PART('month', AGE(FINISH_DATE, START_DATE)) AS DURATION
FROM project
WHERE DATE_PART('year', AGE(FINISH_DATE, START_DATE)) * 12 + DATE_PART('month', AGE(FINISH_DATE, START_DATE)) = (
    SELECT MAX(DATE_PART('year', AGE(FINISH_DATE, START_DATE)) * 12 + DATE_PART('month', AGE(FINISH_DATE, START_DATE)))
    FROM project
);