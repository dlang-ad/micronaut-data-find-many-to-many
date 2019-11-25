
CREATE TABLE `book` (
    `id`                           varchar(36) PRIMARY KEY,
    `created_on`                   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified`                datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `name`                         varchar(50) NOT NULL
);

CREATE TABLE `genre` (
    `name`                         varchar(50) NOT NULL,
    `created_on`                   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified`                datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`name`)
);

CREATE TABLE `book_genre` (
    `book_id`    varchar(36) NOT NULL,
    `genre_name`   varchar(50) NOT NULL,
    `applicable`    boolean     NOT NULL,
    PRIMARY KEY (`book_id`, `genre_name`)
);
