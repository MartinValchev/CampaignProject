-- begin CAMPAIGNPROJECT_WEBSITE
create table CAMPAIGNPROJECT_WEBSITE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    WEBVERSION varchar(50) not null,
    --
    primary key (ID)
)^
-- end CAMPAIGNPROJECT_WEBSITE
-- begin CAMPAIGNPROJECT_BANNER_POSITION
create table CAMPAIGNPROJECT_BANNER_POSITION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    IMPRESSIONS_LIMIT integer not null,
    WEBSITE_ID uuid,
    --
    primary key (ID)
)^
-- end CAMPAIGNPROJECT_BANNER_POSITION
-- begin CAMPAIGNPROJECT_CAMPAIGN
create table CAMPAIGNPROJECT_CAMPAIGN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(50) not null,
    START_DATE date not null,
    END_DATE date not null,
    --
    primary key (ID)
)^
-- end CAMPAIGNPROJECT_CAMPAIGN
-- begin CAMPAIGNPROJECT_CAMPAIGN_BANNER_POSITION
create table CAMPAIGNPROJECT_CAMPAIGN_BANNER_POSITION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CAMPAIGN_ID uuid,
    BANNER_POSITION_ID uuid not null,
    IMPRESSIONS integer,
    START_DATE date not null,
    END_DATE date not null,
    --
    primary key (ID)
)^
-- end CAMPAIGNPROJECT_CAMPAIGN_BANNER_POSITION
-- begin CAMPAIGNPROJECT_BANNER_POSITION_OCCUPACY
create table CAMPAIGNPROJECT_BANNER_POSITION_OCCUPACY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    BANNER_POSITION_ID uuid not null,
    IMPRESSIONS_SUM integer,
    PERCENTIDGE double precision,
    OCCUPACY_DAY date not null,
    --
    primary key (ID)
)^
-- end CAMPAIGNPROJECT_BANNER_POSITION_OCCUPACY
