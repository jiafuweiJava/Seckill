-- 思路就是先插入seckill_information 判断是否有重复秒杀或者超时
-- 如果正常，然后更新seckill_table
DELIMITER $$

CREATE PROCEDURE execute_seckill(
 IN v_seckill_id BIGINT,IN v_phone BIGINT, IN v_kill_time TIMESTAMP,OUT r_result INT
)
BEGIN 
	DECLARE insert_count int DEFAULT 0;		
START TRANSACTION;
	INSERT IGNORE INTO seckill_information(seckill_id,user_phone,create_time)
	VALUES(v_seckill_id,v_phone,v_kill_time);
	SELECT ROW_COUNT() INTO insert_count;
IF(insert_count=0) THEN		
	ROLLBACK;		
	set r_result=2;     				-- 重复秒杀
	
ELSEIF(insert_count<0) THEN
	ROLLBACK;
	set r_result=-2;					-- 出现超时

ELSE
 	UPDATE seckill_table SET num=num-1
 	WHERE id=v_seckill_id
	AND end_time> v_kill_time AND start_time<v_kill_time AND num>0;
 	
 	SELECT ROW_COUNT() INTO insert_count;
	IF (insert_count=0) THEN
	   	ROLLBACK;
	   	SET r_result=0;		-- 秒杀结束或者秒杀还没有开始

	ELSEIF (insert_count<0)   THEN
		ROLLBACK;
		SET r_result=-2;	-- 系统超时
	
	ELSE
		COMMIT;
		SET r_result=1;		-- 秒杀成功

 	END IF;
END IF;
END
$$