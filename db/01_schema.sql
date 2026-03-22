-- Table: card
CREATE TABLE card
(
    id       bigserial    NOT NULL,
    minute   int          NOT NULL,
    type     varchar(100) NOT NULL,
    match_id bigint       NOT NULL,
    CONSTRAINT card_pk PRIMARY KEY (id)
);

-- Table: competition
CREATE TABLE competition
(
    id        bigserial    NOT NULL,
    public_id varchar(100) NOT NULL,
    name      varchar(250) NOT NULL,
    sport     varchar(100) NOT NULL,
    CONSTRAINT competition_pk PRIMARY KEY (id)
);

-- Table: country
CREATE TABLE country
(
    id   bigserial    NOT NULL,
    name varchar(100) NOT NULL,
    code varchar(10)  NOT NULL,
    CONSTRAINT country_pk PRIMARY KEY (id)
);

-- Table: goal
CREATE TABLE goal
(
    id       bigserial NOT NULL,
    minute   int       NOT NULL,
    match_id bigint    NOT NULL,
    CONSTRAINT goal_pk PRIMARY KEY (id)
);

-- Table: group
CREATE TABLE "group"
(
    id   bigserial    NOT NULL,
    name varchar(100) NOT NULL,
    CONSTRAINT group_pk PRIMARY KEY (id)
);

-- Table: match
CREATE TABLE match
(
    id             bigserial   NOT NULL,
    season         int         NOT NULL,
    date           date        NOT NULL,
    time_utc       time        NOT NULL,
    home_goals     int         NOT NULL,
    away_goals     int         NOT NULL,
    status         varchar(50) NOT NULL,
    message        varchar(250),
    group_id       bigint NULL,
    competition_id bigint      NOT NULL,
    stage_id       bigint      NOT NULL,
    stadium_id     bigint,
    home_team_id   bigint,
    away_team_id   bigint      NOT NULL,
    winner_team_id bigint,
    CONSTRAINT match_pk PRIMARY KEY (id)
);

-- Table: player
CREATE TABLE player
(
    id         bigserial    NOT NULL,
    first_name varchar(100) NOT NULL,
    last_name  varchar(100) NOT NULL,
    team_id    bigint       NOT NULL,
    CONSTRAINT player_pk PRIMARY KEY (id)
);

-- Table: stadium
CREATE TABLE stadium
(
    id         bigserial    NOT NULL,
    name       varchar(100) NOT NULL,
    country_id bigint       NOT NULL,
    CONSTRAINT stadium_pk PRIMARY KEY (id)
);

-- Table: stage
CREATE TABLE stage
(
    id        bigserial    NOT NULL,
    public_id varchar(50)  NOT NULL,
    name      varchar(100) NOT NULL,
    ordering  int          NOT NULL,
    CONSTRAINT stage_pk PRIMARY KEY (id)
);

-- Table: team
CREATE TABLE team
(
    id            bigserial    NOT NULL,
    name          varchar(250) NOT NULL,
    official_name varchar(250) NOT NULL,
    slug          varchar(250) NOT NULL,
    abbreviation  varchar(10)  NOT NULL,
    country_id    bigint       NOT NULL,
    CONSTRAINT team_pk PRIMARY KEY (id)
);
