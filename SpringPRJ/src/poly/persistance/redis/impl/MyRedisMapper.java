package poly.persistance.redis.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import poly.persistance.redis.IMyRedisMapper;

@Component("MyRedisMapper")
public class MyRedisMapper implements IMyRedisMapper {
	
	// Context-redis.xml에 정의된 redis 객체
	@Autowired
	public RedisTemplate<String, Object> redisDB;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public void doSaveData() throws Exception {
		
		log.info(this.getClass().getName() + ".getCacheData Service Start!!");
		
		String redisKey = "Test01";
		String saveData = "난 저장되는 데이터.";
		
		/*
		 * Redis 저장 및 읽기에 대한 데이터 타입 지정 (String 타입) 
		 */
		redisDB.setKeySerializer(new StringRedisSerializer());
		redisDB.setValueSerializer(new StringRedisSerializer());
		
		/*
		 * 데이터가 존재하면 바로 반환
		 */
		if (redisDB.hasKey(redisKey)) {
			String res = (String) redisDB.opsForValue().get(redisKey);
			
			log.info("res: " + res);
		} else {
			redisDB.opsForValue().set(redisKey, saveData);
			
			log.info("No Data!");
		}
	}
}
