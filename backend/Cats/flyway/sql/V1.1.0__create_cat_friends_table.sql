CREATE TABLE cat_friends
(
    cat_id    BIGINT REFERENCES cats (id) ON DELETE CASCADE,
    friend_id BIGINT REFERENCES cats (id) ON DELETE CASCADE,
    PRIMARY KEY (cat_id, friend_id)
);