CREATE SCHEMA IF NOT EXISTS "stc-assessment" AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS "stc-assessment"."Permission_groups"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999 CACHE 1 ),
    group_name character varying COLLATE pg_catalog."default",
    CONSTRAINT "Permission_groups_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS "stc-assessment"."Item"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999 CACHE 1 ),
    type character varying COLLATE pg_catalog."default",
    name character varying COLLATE pg_catalog."default",
    permission_group_id integer,
    parent_id integer,
    CONSTRAINT "Item_pkey" PRIMARY KEY (id),
    CONSTRAINT "item_parent_FK" FOREIGN KEY (parent_id)
        REFERENCES "stc-assessment"."Item" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID,
    CONSTRAINT "item_permission_group_FK" FOREIGN KEY (permission_group_id)
        REFERENCES "stc-assessment"."Permission_groups" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS "stc-assessment"."Files"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9999999 CACHE 1 ),
    "binary" bytea,
    item_id integer,
    CONSTRAINT "Files_pkey" PRIMARY KEY (id),
    CONSTRAINT "file_item_FK" FOREIGN KEY (item_id)
        REFERENCES "stc-assessment"."Item" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS "stc-assessment"."Permissions"
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 999999 CACHE 1 ),
    user_email character varying COLLATE pg_catalog."default",
    permission_level character varying COLLATE pg_catalog."default",
    group_id integer,
    CONSTRAINT "Permissions_pkey" PRIMARY KEY (id),
    CONSTRAINT "Permission_groups_FK" FOREIGN KEY (group_id)
        REFERENCES "stc-assessment"."Permission_groups" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;



