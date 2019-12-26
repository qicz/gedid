/*
 * Copyright 2018 Jobsz (zcq@zhucongqi.cn)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
*/
package cn.zhucongqi.gedid.core.redis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.zhucongqi.gedid.core.GeneratorConfig;
import cn.zhucongqi.gedid.core.IGenerator;
import cn.zhucongqi.gedid.kit.StrKit;
import redis.clients.jedis.Jedis;

public class RedisIGenerator implements IGenerator {

	/**
	 * Jedis client.
	 */
	private Jedis jedis;
	
	/**
	 * Business name.
	 */
	private String name;
	
	/**
	 * Start Id
	 */
	private Long startId;
	
	/**
	 * Resources lock.
	 */
	private final Lock lock;
	
	public RedisIGenerator(GeneratorConfig iConfig) {
		this.jedis = new Jedis(iConfig.getIp(), iConfig.getPort());
		String auth = iConfig.getAuth();
		if (StrKit.notBlank(auth)) {
			this.jedis.auth(auth);
		}
		this.startId = iConfig.getStartId();
		this.lock = new ReentrantLock();
	}

	@Override
	public boolean follow(String name) {
		this.name = name;
		this.lock.lock();
		try {
			// if the key (this.name) cannot exist, create and set the value with `this.startId - 1`.<br/>
			// if the key (this.name) is existed , no operation is performed.
			this.jedis.setnx(this.name, String.valueOf((this.startId - 1)));
		} finally {
			this.lock.unlock();
		}
		return true;
	}

	@Override
	public Long next() {
		this.lock.lock();
		long nextId = 0L;
		try {
			nextId = this.jedis.incr(this.name);
		} finally {
			this.lock.unlock();
		}
		return nextId;
	}
	
}
