CREATE PROCEDURE  DeleteMaterialinfo
  @Ids VARCHAR(2500),
  @Message VARCHAR(200) OUTPUT
AS
DECLARE @tempTable TABLE
(
  ID varchar(50)
)
DECLARE @MATERIALNAME VARCHAR(2500)


INSERT INTO @tempTable(ID)
SELECT
  value
FROM
  [dbo].F_Split(@Ids,',')


SELECT @MATERIALNAME = STUFF((SELECT ','+MATERIALNAME FROM MDM_MATERIALINFO t WHERE ENABLED = '1' and ID IN (SELECT ID FROM @tempTable) FOR XML PATH('')), 1, 1, '')


IF (COUNT(@MATERIALNAME)>0)
BEGIN
  SET @Message = 'error:'+@MATERIALNAME+'在启用中，无法删除'
END

ELSE
BEGIN
  SET @Message = 'success:删除成功'
  DELETE FROM  MDM_MATERIALINFO
  WHERE ID IN (SELECT ID FROM @tempTable)
END