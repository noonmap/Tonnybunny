select * from user_entity;

insert into user_entity 
values (1, now(), now(), "email1", 0, 0, "nickname1", "pw1", "010", 0, "image", 0, "usercode");
insert into user_entity 
values (2, now(), now(), "email2", 1, 0, "nickname2", "pw2", "020", 0, "image2", 0, "usercode2");
select * from user_entity;

select * from ytonny_entity;

select * from ytonny_apply_entity;

select * from ytonny_quotation_entity;
select * from ytonny_quotation_image_entity;

select * from common_code_entity;
select * from common_group_code_entity;
