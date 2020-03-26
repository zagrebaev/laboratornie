create table study_group (
    id int not null AUTO_INCREMENT,
    name text,
    primary key (id)
);

create table student_local (
    id int not null,
    surname text,
    name text,
    second_name text,
    study_group_id int,
    primary key (id),
    foreign key (study_group_id) references study_group(id)
);

create table student (
    id int not null,
    surname text,
    name text,
    second_name text,
    study_group_id int,
    primary key (id),
    foreign key (study_group_id) references study_group(id)
);

create table subject (
    id int not null AUTO_INCREMENT,
    name text,
    short_name text,
    primary key (id)
);

create table exam_type (
    id int not null AUTO_INCREMENT,
    type text,
    primary key (id)
);

create table study_plan (
    id int not null AUTO_INCREMENT,
    subject_id int,
    exam_type_id int,
    primary key (id),
    foreign key (subject_id) references subject(id),
    foreign key (exam_type_id) references exam_type(id)
);

create table mark (
    id int not null AUTO_INCREMENT,
    name text,
    value text,
    primary key (id)
);

create table journal (
    id int not null AUTO_INCREMENT,
    student_id int,
    study_plan_id int,
    in_time bit,
    count int,
    mark_id int,
    primary key (id),
    foreign key (student_id) references student(id),
    foreign key (study_plan_id) references study_plan(id),
    foreign key (mark_id) references mark(id)
);

insert into STUDY_GROUP
values ( 1, 'ИКБО-03-16' );

insert into SUBJECT
values (1, 'Проектирование информационных систем', 'ПрИС'),
       (2, 'Системы искусственного интеллекта', 'СИИ'),
       (3, 'Программная инженерия', 'ПИ'),
       (4, 'Национальная система информационной безопасности', 'НСИБ'),
       (5, 'Системный анализ', 'СисАнал'),
       (6, 'Распределенные базы данных', 'РБД'),
       (7, 'Системное программное обеспечение', 'СПО');

insert into EXAM_TYPE
values (1, 'Экзамен'),
       (2, 'Зачет'),
       (3, 'Зачет с оценкой'),
       (4, 'Курсовая');

insert into STUDY_PLAN
values (1, 1, 1),
       (2, 1, 4),
       (3, 2, 1),
       (4, 3, 1),
       (5, 4, 2),
       (6, 5, 1),
       (7, 6, 2),
       (8, 7, 1);

insert into MARK
values (1, 'Отлично', 5),
       (2, 'Хорошо', 4),
       (3, 'Удовлетворительно', 3),
       (4, 'Неудовлетворительно', 2),
       (5, 'Зачет', 'з'),
       (6, 'Незачет', 'н'),
       (7, 'Неявка', '');

insert into STUDENT_LOCAL
values ( 2004, 'Загребаев', 'Максим', 'Дмитриевич', 1 );