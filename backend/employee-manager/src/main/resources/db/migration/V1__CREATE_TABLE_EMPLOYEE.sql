CREATE TABLE employee
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    job_title     VARCHAR(255)          NOT NULL,
    phone         VARCHAR(255)          NOT NULL,
    image_url     VARCHAR(255)          NOT NULL,
    employee_code VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_employee PRIMARY KEY (id)
);

ALTER TABLE employee
    ADD CONSTRAINT uc_employee_email UNIQUE (email);

ALTER TABLE employee
    ADD CONSTRAINT uc_employee_phone UNIQUE (phone);