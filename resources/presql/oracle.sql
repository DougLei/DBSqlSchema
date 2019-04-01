/* 判断sql语句查询的数据是否存在的function */
create or replace function ifexists_function (in_sql in clob)
       return number
is
       final_sql clob;
       final_count number(1);
begin
     final_sql := 'select count(1) from dual where exists ('|| in_sql ||')';
     execute immediate final_sql into final_count;
     return final_count;
end;

