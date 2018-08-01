package com.taotao.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.taotao.content.jedis.JedisClient;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {

	/**
	 * 操作单机版
	 */

	@Test
	public void testJedisSingle() {
		Jedis jedis = new Jedis("192.168.25.129", 6379);
		jedis.set("mytest", "1000");
		String value = jedis.get("mytest");
		System.out.println(value);
	}

	/**
	 * 使用jedis连接池
	 */

	@Test
	public void testJedisPool() {
		JedisPool jedisPool = new JedisPool("192.168.25.129", 6379);
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get("hello");
		System.out.println(value);
		jedis.close();
		jedisPool.close();
	}

	/**
	 * 操作redis集群 
	 * 使用jedisCluster
	 */
	@Test
	public void testJedisCluster() {
		Set<HostAndPort> hosts = new HashSet<HostAndPort>();
		hosts.add(new HostAndPort("192.168.25.129",7001));
		hosts.add(new HostAndPort("192.168.25.129",7002));
		hosts.add(new HostAndPort("192.168.25.129",7003));
		hosts.add(new HostAndPort("192.168.25.129",7004));
		hosts.add(new HostAndPort("192.168.25.129",7005));
		hosts.add(new HostAndPort("192.168.25.129",7006));
		JedisCluster jedisCluster = new JedisCluster(hosts);
		jedisCluster.set("jedisClusster","redisCluster");
		String value = jedisCluster.get("jedisClusster");
		System.out.println(value);
		//关闭jedisCluster
		jedisCluster.close();
	}
	
	/**
	 * spring整合 redis测试
	 * */
	@Test
	public void testJedisClientPool() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisClient jedisClient = context.getBean(JedisClient.class);
		String value = jedisClient.get("hello");
		System.out.println(value);
	}
}
