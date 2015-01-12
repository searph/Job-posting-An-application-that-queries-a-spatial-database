create table student(
                     student_ID varchar(10),
                     student_location SDO_GEOMETRY,
                     primary key(student_ID)
                     );
                     
create table building(
                     building_ID varchar(10),
                     building_name varchar(50),
                     building_location SDO_GEOMETRY,
                     primary key(building_ID)
                     );
                     
create table announcementsystem(
                     as_ID varchar(10),
                     as_location SDO_GEOMETRY,
                     as_radius int,
                     circle SDO_GEOMETRY,
                     primary key(as_ID)
                     );
                     
insert into user_sdo_geom_metadata values('student', 'student_location',SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X',0,820,0.001),SDO_DIM_ELEMENT('Y',0,580,0.001)),NULL);

insert into user_sdo_geom_metadata values('building', 'building_location',SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X',0,820,0.001),SDO_DIM_ELEMENT('Y',0,580,0.001)),NULL);

insert into user_sdo_geom_metadata values('announcementsystem', 'as_location',SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X',0,820,0.001),SDO_DIM_ELEMENT('Y',0,580,0.001)),NULL);

insert into user_sdo_geom_metadata values('announcementsystem', 'circle',SDO_DIM_ARRAY(SDO_DIM_ELEMENT('X',0,820,0.001),SDO_DIM_ELEMENT('Y',0,580,0.001)),NULL);

create index student_idx ON student(student_location) INDEXTYPE IS MDSYS.SPATIAL_INDEX;

create index building_idx ON building(building_location) INDEXTYPE IS MDSYS.SPATIAL_INDEX;

create index as1_idx ON announcementsystem(as_location) INDEXTYPE IS MDSYS.SPATIAL_INDEX;

create index as2_idx ON announcementsystem(circle) INDEXTYPE IS MDSYS.SPATIAL_INDEX;
                     
