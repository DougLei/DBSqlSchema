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
create or replace function for_xml_path (in_select_sql in varchar2, in_root_node_name in varchar2 default 'ROW')
       return clob
is
       xml_content sys.xmltype;
       content clob;
       
       xml_row_content clob; 
       s_count number(4) :=1;
       node_name varchar2(120);
begin
       select extract(xmltype(dbms_xmlgen.getxml(in_select_sql)), '/ROWSET/ROW') into xml_content from dual; -- 将查询结果集转换成xml对象
       content := xml_content.getClobVal(); -- 获取xml字符串
       
       select extract(xml_content, '/ROW[1]').getClobVal() into xml_row_content from dual; --取出来第一个xml row对象，并将其转换成字符串
       xml_row_content := replace(replace(xml_row_content, '<ROW>'), '</ROW>'); -- 将<ROW>节点移除
      
       while instr(xml_row_content, '<', 1, s_count) > 0 loop -- 循环判断，是否还有<，如果有，证明还有没有处理的节点
             if s_count =1 then
                 node_name := substr(xml_row_content, instr(xml_row_content, '<', 1, s_count), instr(xml_row_content, '>', 1, s_count)); 
             else
                 node_name := substr(xml_row_content, instr(xml_row_content, '<', 1, s_count), instr(xml_row_content, '>', 1, s_count) - instr(xml_row_content, '>', 1, s_count-1));
             end if;
             
             /* _x007C__x007C_ 是 ||                    _x002B_ 是 + */
             if instr(node_name, '_x007C__x007C_', 1) > 0 or instr(node_name, '_x002B_', 1) > 0 then -- 如果是有||或+处理的字段，则要移除掉对应的节点，只保留值
                node_name := rtrim(ltrim(node_name, '<'), '>');
                content := replace(replace(content, '<'||node_name||'>'), '</'||node_name||'>');
             end if;
             s_count := s_count +2;
       end loop; 

       if in_root_node_name = 'ROW' then
          return content;
       elsif nvl(in_root_node_name, '_in_root_node_name_is_empty_') = '_in_root_node_name_is_empty_' then
          return replace(replace(content, '<ROW>'), '</ROW>');
       else
          return replace(replace(content, '<ROW>', '<'||in_root_node_name||'>'), '</ROW>', '</'||in_root_node_name||'>');
       end if; 
end;