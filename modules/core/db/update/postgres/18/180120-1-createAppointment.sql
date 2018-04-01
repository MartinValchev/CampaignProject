create table CAMPAIGNPROJECT_APPOINTMENT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    START_DATE date,
    END_DATE date,
    IS_DELETED boolean,
    MPERSON_ID uuid,
    --
    primary key (ID)
);
