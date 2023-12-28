create sequence hibernate_sequence;
create table CHART(
                      id bigint primary key,
                      point_list text,
                      coin_id varchar(50),
                      version integer default 0
);
create table EXCHANGE_RATES(
                       id bigint primary key,
                       name_coin varchar(50),
                       unit varchar(10),
                       value_currency double precision,
                       version integer default 0
);
create table MARKETCAP(
                          id bigint primary key ,
                          coin_id varchar(50),
                          name varchar(50),
                          symbol varchar(50),
                          image varchar(250),
                          fluctuation float,
                          high double precision,
                          low double precision,
                          total_volume double precision,
                          cap_time_stamp timestamp default CURRENT_TIMESTAMP,
                          current_price double precision,
                          market_cap bigint,
                          market_cap_rank integer,
                          chart bigint,
                          constraint fk_chart foreign key (chart) references CHART(id),
                              version integer default 0
);

create table TREND(
    id bigint primary key,
    coin_id varchar(50),
    version integer default 0
)

