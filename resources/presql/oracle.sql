/* 判断sql语句查询的数据是否存在的function */
create or replace function ifexists (in_sql in varchar2)
       return number
is
       