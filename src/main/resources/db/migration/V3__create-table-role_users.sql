CREATE TABLE "role_user"
(
    user_id  UUID NOT NULL,
    role_id  INT NOT NULL,  -- Ajustado para UUID para corresponder à tabela roles
    PRIMARY KEY(user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
