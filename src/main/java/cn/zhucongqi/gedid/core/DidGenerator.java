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
package cn.zhucongqi.gedid.core;

import cn.zhucongqi.gedid.core.redis.RedisGenerator;
import cn.zhucongqi.gedid.core.zookeeper.ZookeeperGenerator;

public class DidGenerator implements IGenerator {
	
	private final static String DID_PREFIX = "did_";

	/**
	 * Data Center
	 */
	private IGenerator iGenerator;
	
	public DidGenerator(GeneratorConfig config) {
		if (GeneratorConfig.Type.Redis.equals(config.getType())) {
			this.iGenerator = new RedisGenerator(config);
		} else {
			this.iGenerator = new ZookeeperGenerator(config);
		}
	}

	/**
	 * Follow The business with name.
	 *
	 * @param name
	 * @return true, follow success.
	 */
	@Override
	public boolean follow(String name) {
		this.iGenerator.follow(this.getName(name));
		return true;
	}

	/**
	 * Current id.
	 * @return
	 */
	@Override
	public Long current() {
		return this.iGenerator.current();
	}

	/**
	 * Get next Id.
	 */
	@Override
	public Long next() {
		return this.iGenerator.next();
	}
	
	private String getName(String name) {
		return DID_PREFIX + name;
	}
	
}
