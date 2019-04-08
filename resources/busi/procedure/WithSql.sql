WITH cte(ID,typename,PARENT_ID)
AS 
(
SELECT 
ID,
VALUESNAME AS TYPENAME,
NULL AS PARENT_ID
FROM V_MDMDATA 
WHERE DATADICTIONARYTYPECODE = '15' AND RECORDSTATUS = 1

UNION ALL 

select 
id,
typename,
PARENT_ID
from MDM_CHECKELEMENTTYPE
),

A AS
(
SELECT 
ID,
typename,
PARENT_ID 
FROM cte 
WHERE ID= $Id$
UNION ALL

SELECT 
cte.ID, 
cte.typename, 
cte.PARENT_ID 
FROM cte
INNER JOIN A ON cte.PARENT_ID = A.ID
)
select 
id,
elementtypeid,
elementcode,
elementname,
case when enabled = 3 then '起草'
when enabled = 1 then '启用'
when enabled = 2 then '禁用'
end as enabledtext,

case when checkmethod = 3 then '检查'
when checkmethod = 1 then '测量'
when checkmethod = 2 then '称重'
end as checkmethodtext,

case when  recordformat= 1 then '单值'
when recordformat = 2 then '表格值'
end as recordformattext,

remark,
create_date
from MDM_CHECKELEMENTINFO 
where elementtypeid in (SELECT ID FROM A)