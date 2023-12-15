CREATE TABLE users
(
    id       INT                              NOT NULL AUTO_INCREMENT,
    email    VARCHAR(45)                      NOT NULL,
    password VARCHAR(100)                     NOT NULL,
    name     VARCHAR(45)                      NULL,
    surname  VARCHAR(45)                      NULL,
    role     ENUM ('ROLE_USER', 'ROLE_ADMIN') NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX email_UNIQUE (email ASC) VISIBLE
);

CREATE TABLE tasks
(
    id          INT                                    NOT NULL AUTO_INCREMENT,
    title       VARCHAR(45)                            NOT NULL,
    description VARCHAR(45)                            NULL,
    author      VARCHAR(45)                            NOT NULL,
    status      ENUM ('WORKING_ON', 'DONE', 'WAITING') NOT NULL,
    priority    ENUM ('HIGH', 'MEDIUM', 'LOW')         NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX title_UNIQUE (title ASC) VISIBLE
);

CREATE TABLE comments
(
    id      INT          NOT NULL AUTO_INCREMENT,
    task_id INT          NOT NULL,
    text    VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
);

CREATE TABLE tasks_workers
(
    id      INT NOT NULL AUTO_INCREMENT,
    task_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE
);

