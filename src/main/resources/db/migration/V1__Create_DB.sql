

CREATE type status as enum ('NEW', 'ASSIGNED', 'ON_HOLD', 'APPROVED', 'CANCELED', 'DECLINED');
CREATE type phone_type as enum ('HOME', 'WORK', 'MOBILE');
CREATE type advisor_role as enum ('ASSOCIATE', 'PARTNER', 'SENIOR');


CREATE TABLE IF NOT EXISTS public.advisor(
    id bigserial PRIMARY KEY,
    first_name text not null,
    last_name text not null,
    email text not null unique,
    cognito_username text not null unique,
    advisor_role advisor_role not null
);

CREATE TABLE IF NOT EXISTS public.applicant
(
    id               bigserial PRIMARY KEY,
    first_name       text not null,
    last_name      text not null,
    email            text not null unique,
    cognito_username text not null unique,
    social_security_number text not null unique,
    city text not null,
    street text not null,
    address_number text not null,
    zip text not null,
    apt text not null
);

CREATE TABLE IF NOT EXISTS public.phone (
    id bigserial primary key,
    applicant_id bigint,
    number text not null,
    type phone_type not null,

    constraint "FK_phone_applicant" foreign key(applicant_id) references applicant(id)
);

CREATE TABLE IF NOT EXISTS public.application (
    id bigserial primary key,
    status status not null,
    money_amount bigint not null,
    created_on timestamp not null,
    assigned_on timestamp,
    resolved_on timestamp,
    applicant_id bigserial not null,
    advisor_id bigint,

    constraint "FK_application_applicant" foreign key(applicant_id) references applicant(id),
    constraint "FK_application_advisor" foreign key (advisor_id) references advisor(id) on update cascade on delete set null
);

INSERT INTO public.advisor (first_name, last_name, email, cognito_username, advisor_role) VALUES
('David', 'Super Advisor', 'advisor@gmail.com', 'advisor1', 'ASSOCIATE'),
('Brain', 'Bad Advisor', 'brain@gmail.com', 'advisor2', 'PARTNER'),
('Anton', 'my fantasy goes down', 'anton@gmail.com', 'advisor3', 'SENIOR'),
('Assigned', 'Dummy', 'assigned@gmail.com', 'advisor4', 'ASSOCIATE'),
('Not', 'Assigned', 'bro@gmail.com', 'advisor5', 'ASSOCIATE');

INSERT INTO public.applicant (first_name, last_name, email, cognito_username, social_security_number, city, street, address_number, zip, apt) VALUES
 ('Applicant', 'ApplicantSurname', 'aplicant@gmail.com', 'applicant1', '41512515', 'Lviv', 'Heroiv UPA', '24k4', '124155161', '22');

INSERT INTO public.phone (applicant_id, number, type) VALUES (1, '+323512512', 'WORK');
INSERT INTO public.phone (applicant_id, number, type) VALUES (1, '+43125125', 'HOME');

INSERT INTO public.application (status, money_amount, created_on, applicant_id, advisor_id)
VALUES ('ASSIGNED', 10000, '2000-01-01', 1, 4);

INSERT INTO public.application (status, money_amount, created_on, applicant_id)
VALUES ('NEW', 10000, '2000-01-01', 1), ('NEW', 10000, '2020-01-01', 1), ('NEW', 5000, '2000-01-01', 1), ('NEW', 5000, '2020-01-01', 1), ('NEW', 20000, '2020-01-01', 1);


