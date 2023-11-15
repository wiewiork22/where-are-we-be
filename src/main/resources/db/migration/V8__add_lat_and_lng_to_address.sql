ALTER TABLE address
    ADD COLUMN lat double precision NOT NULL DEFAULT 0,
    ADD COLUMN lng double precision NOT NULL DEFAULT 0;