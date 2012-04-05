package com.smud.service.data;

import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.smud.model.Item;

@Service
public class ItemRepository implements Repository<Item> {

	private RedisTemplate<String, String> redisTemplate;
	
	private static final String GLOBAL_ITEM_ID = "global:iid";
	private final RedisAtomicLong itemIdCounter;
//	private final ValueOperations<String, String> valueOps;

	@Autowired
	public ItemRepository(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
//		valueOps = redisTemplate.opsForValue();
		itemIdCounter = new RedisAtomicLong(GLOBAL_ITEM_ID, redisTemplate.getConnectionFactory());
	}

	@Override
	public Hashtable<String, String> hashfy(Item item) {
		
		Hashtable<String, String> hash = new Hashtable<String, String>();
		hash.put("code", String.valueOf(item.getCode()));
		hash.put("teste", "rafael");
		
		return hash;
	}

	@Override
	public Item objectfy(Map<Object, Object> map) {
		String itemCode = (String) map.get("code");
		
		Item item = new Item();
		item.setCode(Integer.parseInt(itemCode));
		
		return item;
	}

	@Override
	public boolean store(Item t) {
		
		long availableId = nextId();
		t.setId(availableId);
		
		Hashtable<String, String> hashfied = this.hashfy(t);
		String key = KeyUtils.ITEM.getKeyFor(availableId);
		BoundHashOperations<String, String, String> boundHashOps = redisTemplate.boundHashOps(key);
		boundHashOps.putAll(hashfied);
		
		return true;
	}

	@Override
	public Item getById(long itemId) {
		
		String key = KeyUtils.ITEM.getKeyFor(itemId);
		BoundHashOperations<String,Object,Object> hashOps = redisTemplate.boundHashOps(key);
		
		Item foundItem = objectfy(hashOps.entries());
		foundItem.setId(itemId);
		
		return foundItem;
	}

	
	@Override
	public long nextId() {
		long availableId = itemIdCounter.incrementAndGet();
		return availableId;
	}
	
}
