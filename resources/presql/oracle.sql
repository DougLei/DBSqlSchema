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


/* for xml path('') 的function */
create or replace function for_xml_path (in_select_sql in varchar2, in_root_name in varchar2 default 'ROW'/* 匹配sqlserver 的for xml path参数值 */)
       return clob
is
       final_in_root_name varchar2(30);
       xml_content clob;
       
       type dynamic_cursor_type is ref cursor; -- 定义一个动态游标类型
       select_sql_dynamic_cursor dynamic_cursor_type; -- 定义一个动态游标类型的参数
       
begin
       final_in_root_name := in_root_name;
       
       open select_sql_dynamic_cursor for in_select_sql;
       loop
            fetch 
            exit when select_sql_dynamic_cursor%notfound;
       end loop;
       close cursor select_sql_dynamic_cursor;
/*
       cursor_id number;
       cnt number;
       desctab dbms_sql.desc_tab;
       


       cursor_id := dbms_sql.open_cursor();
       dbms_sql.parse(cursor_id, in_select_sql, dbms_sql.native);
       dbms_sql.describe_columns(cursor_id, cnt, desctab);
       
       for i in 1 .. desctab.count loop
           dbms_output.put_line(rpad(desctab(i).col_name, 30) || rpad(desctab(i).col_type, 3));
       end loop;
       dbms_sql.close_cursor(cursor_id);
*/

       return xml_content;
end;