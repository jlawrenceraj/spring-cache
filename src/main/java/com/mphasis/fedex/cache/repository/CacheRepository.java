package com.mphasis.fedex.cache.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mphasis.fedex.cache.model.Cache;

@Repository
public interface CacheRepository extends CrudRepository<Cache, String>{
	
}
