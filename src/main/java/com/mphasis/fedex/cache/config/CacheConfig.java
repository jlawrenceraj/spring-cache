package com.mphasis.fedex.cache.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

@Configuration
public class CacheConfig {

	@Value("${cache.redis.host}")
	private String host;
	@Value("${cache.redis.password}")
	private String password;
	@Value("${cache.redis.port}")
	private int port;

	@Value("${cache.redis.jedis.pool.max-total}")
	private int maxTotal;
	@Value("${cache.redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${cache.redis.jedis.pool.min-idle}")
	private int minIdle;

	@Bean
	public JedisClientConfiguration jedisClientConfig() {
		JedisClientConfiguration.JedisPoolingClientConfigurationBuilder poolingClientConfigurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration
				.builder();
		GenericObjectPoolConfig objectPoolConfig = new GenericObjectPoolConfig();
		objectPoolConfig.setMaxTotal(maxTotal);
		objectPoolConfig.setMaxIdle(maxIdle);
		objectPoolConfig.setMinIdle(minIdle);
		return poolingClientConfigurationBuilder.poolConfig(objectPoolConfig).build();
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
		redisConfig.setHostName(host);
		redisConfig.setPort(port);
		if(!StringUtils.isEmpty(password)) redisConfig.setPassword(password);
		return new JedisConnectionFactory(redisConfig,jedisClientConfig());
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		return redisTemplate;
	}

}
