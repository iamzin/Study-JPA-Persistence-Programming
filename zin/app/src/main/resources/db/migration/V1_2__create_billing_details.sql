CREATE TABLE billing_details
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    type     VARCHAR(31)  NULL,
    account  VARCHAR(255) NOT NULL,
    bankname VARCHAR(255) NOT NULL,
    user_id  BIGINT       NOT NULL,
    CONSTRAINT pk_billingdetails PRIMARY KEY (id)
);

ALTER TABLE billing_details
    ADD CONSTRAINT FK_BILLING_DETAILS_USER_ID FOREIGN KEY (user_id) REFERENCES users (id);
