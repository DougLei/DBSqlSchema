CREATE PROCEDURE procinsertSCM_PURCHASEDELIVER
  @Ids VARCHAR(2500),
  @purchasedeptid varchar(50),
  @purchasemanid varchar(50),
  @Message VARCHAR(200) OUTPUT
AS
DECLARE @tempTable TABLE
(
  ID varchar(50),
  R INT
)
DECLARE @DELIVERCODE VARCHAR(50)
DECLARE @PURCHASEID VARCHAR(50)
DECLARE @R INT
DECLARE @NUM INT

INSERT INTO @tempTable(ID,R)
SELECT T.value ,ROW_NUMBER() OVER( order BY T.value ASC ) AS R FROM [dbo].F_Split(@Ids,',') T
LEFT JOIN SCM_PURCHASE R ON T.value=R.ID

IF EXISTS (SELECT ID FROM SCM_PURCHASE
WHERE ID IN (SELECT ID FROM @tempTable) and RECORDSTATUS=9 )/*待更新成正式状态*/
BEGIN
	SET @Message = 'warning:选中行的领出记录下，存在记录状态为【起草】记录，请重新选择。'
END
ELSE if not exists(select 1 from SCM_PURCHASEDETAILS where PURCHASEID IN (SELECT ID FROM @tempTable))
BEGIN
	SET @Message = 'warning:子表无数据，不能确认！';
	return
END
ELSE if exists( select 1 from SCM_PURCHASEDETAILS
	where PURCHASEID IN (SELECT ID FROM @tempTable)
	GROUP BY MATERIALID,PURCHASEID
	HAVING (COUNT(MATERIALID))>1
	)
BEGIN
	SET @Message = 'warning:子表有重复数据，不能确认！';
	return
END
ELSE
BEGIN
	set @R=1
	set @NUM=0
	SELECT @NUM = COUNT(*) FROM @tempTable 
	while(@R<=@NUM)
	BEGIN
		SET @PURCHASEID = NEWID()
		declare @EightYear varchar(8) /*年月日*/
		declare @ShunXuHao varchar(10)/*流水号*/
		select @EightYear=convert(varchar(8),getdate(),112)
		
		select @ShunXuHao=max(right(DELIVERCODE,3)) from SCM_PURCHASEDELIVER
			where substring(isnull(DELIVERCODE,''),5,8)=@EightYear
		if(@ShunXuHao is null) set @ShunXuHao='001'
		else
		begin
			set @ShunXuHao=@ShunXuHao+1+''
			while(len(@ShunXuHao)<3)
			begin
				set @ShunXuHao='0'+@ShunXuHao
			end
		end
		set @DELIVERCODE='CGDH'+@EightYear+@ShunXuHao
		INSERT INTO SCM_PURCHASEDELIVER
		(DELIVERCODE,DATAFROM,REFCODE,SUPPLIERID,PURCHASEDEPTID,DELIVERTIME,
		PURCHASEMANID,SORTCODE,REMARK,DELETEFLAG,RECORDSTATUS,
		ID, CUSTOMER_ID, PROJECT_ID,CREATE_DATE,
		LAST_UPDATE_DATE, CREATE_USER_ID, LAST_UPDATE_USER_ID)
		select @DELIVERCODE,7,PURCHASECODE,SUPPLIERID,@purchasedeptid,GETDATE(),
		@purchasemanid,'','',1,1,
		@PURCHASEID, CUSTOMER_ID, PROJECT_ID, GETDATE(),
		GETDATE(), @purchasemanid, @purchasemanid
		from SCM_PURCHASE
		where ID IN (SELECT t.ID FROM @tempTable t  where R = @R);

		INSERT INTO SCM_PURCHASEDELIVERDETAILS
		(DELIVERID,MATERIALID,DELIVERNUMBER,
		ID, CUSTOMER_ID, PROJECT_ID,CREATE_DATE,
			LAST_UPDATE_DATE, CREATE_USER_ID, LAST_UPDATE_USER_ID)
		SELECT 
		@PURCHASEID,S.MATERIALID,S.PURCHASENUMBER,
		NEWID(), S.CUSTOMER_ID, S.PROJECT_ID, GETDATE(),
		GETDATE(), @purchasemanid, @purchasemanid
		from SCM_PURCHASEDETAILS S
		LEFT JOIN SCM_PURCHASE R ON S.PURCHASEID=R.ID
		where s.PURCHASEID IN (SELECT t.ID FROM @tempTable t where R = @R) 
		SET @R = @R + 1
	END
	SET @Message = 'success:操作完成'
END