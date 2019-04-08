/*树根节点，并标记类型为1【标准件】*/
select   t1.ID,
t1.PARENT_ID,
case when  t1.PRODUCTTYPE=1 then '【产品】'+t1.PRODUCTCODENAME+'/'+t1.STAGENAME+'/'+t1.MATERIALNAME+'/'+t1.FIGURENUMBER+'/'+t1.VERSIONID+'/'+t1.XRECORDSTATUS 
 when  t1.PRODUCTTYPE=2  then '【标准件】'+t1.MATERIALNAME+'/'+t1.FIGURENUMBER+'/'+t1.VERSIONID+'/'+t1.XRECORDSTATUS 
 when  t1.PRODUCTTYPE=3  then '【通用件】'+t1.MATERIALNAME+'/'+t1.FIGURENUMBER+'/'+t1.VERSIONID+'/'+t1.XRECORDSTATUS  
 else  '' end   PRODUCTNAME,
t1.ID  as PRODUCTID,
'1'  AS  NODETYPE,
t1.RECORDSTATUS,
t1.CREATE_DATE
from 
(
SELECT
p.ID,
p.PRODUCTTYPE,
null  AS PARENT_ID,
isnull(xh.VALUESNAME,'') as PRODUCTCODENAME,
isnull(jd.VALUESNAME,'') as STAGENAME,
isnull(m.MATERIALNAME,'') as MATERIALNAME,
isnull(p.VERSIONID,'')  as  VERSIONID,
isnull(m.FIGURENUMBER,'')   as FIGURENUMBER,
case when p.RECORDSTATUS=1 then '起草' when  p.RECORDSTATUS=2 then '已送审'   when  p.RECORDSTATUS=3 then '审核通过'else '' end  
AS XRECORDSTATUS,
p.ID  as PRODUCTID,
'1'  AS  NODETYPE,
p.RECORDSTATUS,
p.CREATE_DATE
FROM MPM_PRODUCT p  
left join  MDM_MATERIALINFO  m on m.id=p.PRODUCTID
left join (  select  d1.id,d1.VALUESID,d1.VALUESNAME  from V_MdmData  d1  where  d1.DATADICTIONARYTYPECODE='04')  xh on xh.id=p.PRODUCTCODEID 
left join (  select  d1.id,d1.VALUESID,d1.VALUESNAME  from V_MdmData  d1  where  d1.DATADICTIONARYTYPECODE='05')  jd on jd.id=p.STAGEID 
where p.DELETEFLAG=1  ) t1 
UNION ALL
/*树子节点，并标记类型为2*/
SELECT 
pd.ID,
pd.PARENT_ID,
 case 
  when pd.PARTSTYPE=1 then '【型号件】'+isnull(m.MATERIALNAME,'')+'/'+isnull(m.FIGURENUMBER,'')  
  when pd.PARTSTYPE=2 then '【标准件】'+isnull(bzm.MATERIALNAME,'')+'/'+isnull(bzm.FIGURENUMBER,'') 
 when pd.PARTSTYPE=3 then '【通用件】'+isnull(bzm.MATERIALNAME,'')+'/'+isnull(bzm.FIGURENUMBER,'')   
 else isnull(m.MATERIALNAME,'')+'/'+isnull(m.FIGURENUMBER,'')   end  PRODUCTNAME,
pd.PRODUCTID,
'2' AS  NODETYPE,
p.RECORDSTATUS,
p.CREATE_DATE
FROM MPM_PRODUCTDETAILS pd   
left join  MPM_PRODUCT bz on bz.ID=pd.STANDARDPARTSID
left join  MDM_MATERIALINFO bzm  on bzm.ID=bz.PRODUCTID
left join  MDM_MATERIALINFO  m on m.id=pd.PARTSID 
left join   MPM_PRODUCT  p on p.ID=pd.PRODUCTID
left join (  select  d1.id,d1.VALUESID,d1.VALUESNAME  from V_MdmData  d1  where  d1.DATADICTIONARYTYPECODE='04')  xh on xh.id=p.PRODUCTCODEID 
left join (  select  d1.id,d1.VALUESID,d1.VALUESNAME  from V_MdmData  d1  where  d1.DATADICTIONARYTYPECODE='05')  jd on jd.id=p.STAGEID 
where pd.DELETEFLAG=1 