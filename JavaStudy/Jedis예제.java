package jeongpro;
 
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
 
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
public class jedistest {
	public static void main(String[] args) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		
		JedisPool pool = new JedisPool(jedisPoolConfig, "183.99.32.228", 6379, 1000, "jeongpro");
		//Jedis풀 생성(JedisPoolConfig, host, port, timeout, password)
		Jedis jedis = pool.getResource();//thread, db pool처럼 필요할 때마다 getResource()로 받아서 쓰고 다 쓰면 close로 닫아야 한다.
		
		//데이터 입력
		jedis.set("jeong", "pro");
		
		//데이터 출력
		System.out.println(jedis.get("jeong"));//pro
		
		//데이터 삭제
		jedis.del("jeong");
		System.out.println(jedis.get("jeong"));//null
		
		try {
			jedis.set("key", "value");
			//데이터 만료시간 지정
			jedis.expire("key", 5);//5초 동안만 "key"를 key로 갖는 데이터 유지
			Thread.sleep(4000);//쓰레드를 4초간 재우고
			System.out.println(jedis.get("key"));//value
			Thread.sleep(2000);//1초했더니 운좋으면 살아있어서 2초로 지정
			System.out.println(jedis.get("key"));//null
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		/* Lists 형태 입출력 */
		jedis.lpush("/home/jdk", "firstTask");
		jedis.lpush("/home/jdk", "secondTask");
		System.out.println(jedis.rpop("/home/jdk"));//firstTask
		System.out.println(jedis.rpop("/home/jdk"));//secondTask
		
		/* Sets 형태 입출력 */
		jedis.sadd("nicknames", "jeongpro");
		jedis.sadd("nicknames", "jdk");
		jedis.sadd("nicknames", "jeongpro");
		Set<String> nickname = jedis.smembers("nicknames");
		Iterator iter = nickname.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		/* Hashes 형태 입출력 */
		jedis.hset("user", "name", "jeongpro");
		jedis.hset("user", "job", "software engineer");
		jedis.hset("user", "hobby", "coding");
		
		System.out.println(jedis.hget("user","name"));//jeongpro
		Map<String, String> fields = jedis.hgetAll("user");
		System.out.println(fields.get("job"));//software engineer
		
		/* Sorted Sets 형태 입출력 */
		//Map을 미리 만들어서 넣을 수도 있음 zadd확인할 것
		jedis.zadd("scores", 6379.0, "PlayerOne");
		jedis.zadd("scores", 8000.0, "PlayerTwo");
		jedis.zadd("scores", 1200.5, "PlayerThree");
		
		System.out.println(jedis.zrangeByScore("scores", 0, 10000));
		//[PlayerThree, PlayerOne, PlayerTwo]
		//Sorted Sets는 잘모르겠으니 더 공부할 것.
		
		
		if(jedis != null) {
			jedis.close();
		}
		pool.close();
	}
}