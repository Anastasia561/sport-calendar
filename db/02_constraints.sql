-- foreign keys
-- Reference: card_match (table: card)
ALTER TABLE card
    ADD CONSTRAINT card_match_foreignkey
        FOREIGN KEY (match_id)
            REFERENCES match (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: goal_match (table: goal)
ALTER TABLE goal
    ADD CONSTRAINT goal_match_foreignkey
        FOREIGN KEY (match_id)
            REFERENCES match (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_Group (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_Group_foreignkey
        FOREIGN KEY (group_id)
            REFERENCES "group" (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_competition (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_competition_foreignkey
        FOREIGN KEY (competition_id)
            REFERENCES competition (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_stadium (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_stadium_foreignkey
        FOREIGN KEY (stadium_id)
            REFERENCES stadium (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_stage (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_stage_foreignkey
        FOREIGN KEY (stage_id)
            REFERENCES stage (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_team (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_home_team_foreignkey
        FOREIGN KEY (home_team_id)
            REFERENCES team (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_team (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_away_team_foreignkey
        FOREIGN KEY (away_team_id)
            REFERENCES team (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: match_team (table: match)
ALTER TABLE match
    ADD CONSTRAINT match_winner_team_foreignkey
        FOREIGN KEY (winner_team_id)
            REFERENCES team (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: player_team (table: player)
ALTER TABLE player
    ADD CONSTRAINT player_team_foreignkey
        FOREIGN KEY (team_id)
            REFERENCES team (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: stadium_country (table: stadium)
ALTER TABLE stadium
    ADD CONSTRAINT stadium_country_foreignkey
        FOREIGN KEY (country_id)
            REFERENCES country (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: team_country (table: team)
ALTER TABLE team
    ADD CONSTRAINT team_country_foreignkey
        FOREIGN KEY (country_id)
            REFERENCES country (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;
