package com.mphasis.fedex.cache.model;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("RedisCache")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cache {

	private String key;
	private String value;
	
}
