-- country
INSERT INTO country (name, code)
VALUES ('Saudi Arabia', 'KSA'),
       ('Uzbekistan', 'UZB'),
       ('United Arab Emirates', 'UAE'),
       ('Qatar', 'QAT'),
       ('Iran', 'IRN'),
       ('Japan', 'JPN');

-- competition
INSERT INTO competition (public_id, name, sport)
VALUES ('afc-champions-league', 'AFC Champions League', 'Football');

-- stage
INSERT INTO stage (public_id, name, ordering)
VALUES ('ROUND OF 16', 'ROUND OF 16', 4),
       ('FINAL', 'FINAL', 7);

-- team
INSERT INTO team (name, official_name, slug, abbreviation, country_id)
VALUES ('Al Shabab', 'Al Shabab FC', 'al-shabab-fc', 'SHA', (SELECT id FROM country WHERE code = 'KSA')),
       ('Nasaf', 'FC Nasaf', 'fc-nasaf-qarshi', 'NAS', (SELECT id FROM country WHERE code = 'UZB')),
       ('Al Hilal', 'Al Hilal Saudi FC', 'al-hilal-saudi-fc', 'HIL', (SELECT id FROM country WHERE code = 'KSA')),
       ('Shabab Al Ahli', 'SHABAB AL AHLI DUBAI', 'shabab-al-ahli-club', 'SAH',
        (SELECT id FROM country WHERE code = 'UAE')),
       ('Al Duhail', 'AL DUHAIL SC', 'al-duhail-sc', 'DUH', (SELECT id FROM country WHERE code = 'QAT')),
       ('Al Rayyan', 'AL RAYYAN SC', 'al-rayyan-sc', 'RYN', (SELECT id FROM country WHERE code = 'QAT')),
       ('Al Faisaly', 'Al Faisaly FC', 'al-faisaly-fc', 'FAI', (SELECT id FROM country WHERE code = 'KSA')),
       ('Foolad', 'FOOLAD KHOUZESTAN FC', 'foolad-khuzestan-fc', 'FLD', (SELECT id FROM country WHERE code = 'IRN')),
       ('Urawa Reds', 'Urawa Red Diamonds', 'urawa-red-diamonds', 'RED', (SELECT id FROM country WHERE code = 'JPN'));

-- match

INSERT INTO match (season, date, time_utc, home_goals, away_goals, status, message,
                   competition_id, stage_id, stadium_id,
                   home_team_id, away_team_id, winner_team_id)
VALUES (2024,
        '2024-01-03',
        '00:00:00',
        1,
        2,
        'PLAYED',
        NULL,
        (SELECT id FROM competition WHERE public_id = 'afc-champions-league'),
        (SELECT id FROM stage WHERE public_id = 'ROUND OF 16'),
        NULL,
        (SELECT id FROM team WHERE slug = 'al-shabab-fc'),
        (SELECT id FROM team WHERE slug = 'fc-nasaf-qarshi'),
        (SELECT id FROM team WHERE slug = 'fc-nasaf-qarshi'));

INSERT INTO match (season, date, time_utc, home_goals, away_goals, status, message,
                   competition_id, stage_id, stadium_id,
                   home_team_id, away_team_id, winner_team_id)
VALUES (2024,
        '2024-01-03',
        '16:00:00',
        0,
        0,
        'SCHEDULED',
        NULL,
        (SELECT id FROM competition WHERE public_id = 'afc-champions-league'),
        (SELECT id FROM stage WHERE public_id = 'ROUND OF 16'),
        NULL,
        (SELECT id FROM team WHERE slug = 'al-hilal-saudi-fc'),
        (SELECT id FROM team WHERE slug = 'shabab-al-ahli-club'),
        NULL);

INSERT INTO match (season, date, time_utc, home_goals, away_goals, status,
                   competition_id, stage_id, stadium_id,
                   home_team_id, away_team_id)
VALUES (2024,
        '2024-01-04',
        '15:25:00',
        0,
        0,
        'SCHEDULED',
        (SELECT id FROM competition WHERE public_id = 'afc-champions-league'),
        (SELECT id FROM stage WHERE public_id = 'ROUND OF 16'),
        NULL,
        (SELECT id FROM team WHERE slug = 'al-duhail-sc'),
        (SELECT id FROM team WHERE slug = 'al-rayyan-sc'));

INSERT INTO match (season, date, time_utc, home_goals, away_goals, status,
                   competition_id, stage_id, stadium_id,
                   home_team_id, away_team_id)
VALUES (2024,
        '2024-01-04',
        '08:00:00',
        0,
        0,
        'SCHEDULED',
        (SELECT id FROM competition WHERE public_id = 'afc-champions-league'),
        (SELECT id FROM stage WHERE public_id = 'ROUND OF 16'),
        NULL,
        (SELECT id FROM team WHERE slug = 'al-faisaly-fc'),
        (SELECT id FROM team WHERE slug = 'foolad-khuzestan-fc'));

INSERT INTO match (season, date, time_utc, home_goals, away_goals,status,
                   competition_id, stage_id,
                   away_team_id)
VALUES (2024,
        '2024-01-19',
        '00:00:00',
        0,
        0,
        'SCHEDULED',
        (SELECT id FROM competition WHERE public_id = 'afc-champions-league'),
        (SELECT id FROM stage WHERE public_id = 'FINAL'),
        (SELECT id FROM team WHERE slug = 'urawa-red-diamonds'));
