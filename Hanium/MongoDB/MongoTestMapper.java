package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import poly.persistance.mongo.IMongoTestMapper;

@Component("MongoTestMapper")
// 개발자가 직접 작성한 Class를 Bean으로 동록하기 위한 어노테이션
public class MongoTestMapper implements IMongoTestMapper{
	
	@Autowired
	private MongoTemplate mongodb;
	// 생성자나 세터 등을 사용하여 의존성 주입을 하려고 할 때, 해당 빈을 찾아서 주입해주는 annotation
	
	Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void insertWords() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String, Object>> test() throws Exception {
	// List형태로 데이터를 넘겨주기위한 메서드 test()
	
//		DBObject res = mongodb.getCollection("news").find().one();
		DBCursor res = mongodb.getCollection("news").find();
		// res 변수에 mongodb collection 이름이 news인 것을 찾아 정보를 저장
		List<Map<String, Object>> rList = new ArrayList<>();
		
		while(res.hasNext()) {
			DBObject obj = res.next();
			
			Map<String, Object> rMap = new HashMap<>();
			
			rMap.put("name", (String)obj.get("name"));
			// 키 이름이 name이고 mongodb name에 있는 정보를 (String) 후 put
			rMap.put("age", (Integer)obj.get("age"));
			// 키 이름이 age이고 mongodb age에 있는 정보를 (interger) 후 put
			rMap.put("sentence", (List<String>)obj.get("sentence"));
			// 키 이름 sentence에 mongodb sentence에 있는 정보를 형변환 후 put
			
			rList.add(rMap);
		}
		
		return rList;
	}
	
	// Mongodb에 조건을 추가한 메서드
	@Override
	public List<Map<String, Object>> selectWithCondition(DBObject query) throws Exception {
		DBCursor res = mongodb.getCollection("news").find(query);
		// find(query) = 컬렉션 이름이 news인 데이터를 res에 대입
		// iterator처럼 쓰는 것
		
		List<Map<String, Object>> rList = new ArrayList<>();
		while(res.hasNext()) {
			DBObject obj = res.next();
			// obj에 res를 대입
			Map<String, Object> rMap = new HashMap<>();
			rMap.put("name", (String)obj.get("name"));
			// 키 name에 name데이터를 put
			rMap.put("age", (Integer)obj.get("age"));
			rMap.put("sentence", (List<String>)obj.get("sentence"));
			
			rList.add(rMap);
		}
		
		return rList;
	}

	@Override
	public void insert(Object obj) throws Exception {
		mongodb.insert(obj, "news");
	}

	
}