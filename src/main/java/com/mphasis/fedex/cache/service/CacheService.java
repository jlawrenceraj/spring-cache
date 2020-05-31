package com.mphasis.fedex.cache.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mphasis.fedex.cache.model.Cache;
import com.mphasis.fedex.cache.repository.CacheRepository;

@Service
public class CacheService {

	@Autowired
	CacheRepository redisCacheRepository;
	
	public List<Cache> getCache() {
		List<Cache> redisCaches = new ArrayList<>();
		Iterable<Cache> redisIter = redisCacheRepository.findAll();
		redisIter.forEach(redisCaches::add);
		return redisCaches;
	}
	
	public Cache getCacheByCacheId(String id) {
		return redisCacheRepository.findById(id).get();
	}

	public Cache saveCache(Cache user) {
		return redisCacheRepository.save(user);
	}
}
