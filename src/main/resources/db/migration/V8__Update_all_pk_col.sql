ALTER TABLE question MODIFY COLUMN id bigint(20);
ALTER TABLE comment MODIFY COLUMN parent_id bigint(20);