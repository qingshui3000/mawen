ALTER TABLE question ALTER COLUMN view_count SET DEFAULT 0;
ALTER TABLE question ALTER COLUMN like_count not null SET DEFAULT 0;
ALTER TABLE question ALTER COLUMN comment_count not null SET DEFAULT 0;