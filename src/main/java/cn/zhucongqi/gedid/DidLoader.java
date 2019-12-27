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
package cn.zhucongqi.gedid;

import java.util.concurrent.ConcurrentHashMap;

import cn.zhucongqi.gedid.core.DidGenerator;
import cn.zhucongqi.gedid.core.GeneratorConfig;
import cn.zhucongqi.gedid.kit.StrKit;

public class DidLoader {
	
	/**
	 * DidGenerator generatorConfig
	 */
	private GeneratorConfig generatorConfig;
	
	/**
	 * Business Mapping
	 */
	private ConcurrentHashMap<String, DidGenerator> businessMapping;
	
	/**
	 * Init DidGenerator Loader using Redis
	 * @param generatorConfig
	 * @return loader instance
	 */
	public static DidLoader init(GeneratorConfig generatorConfig) {
		return (new DidLoader(generatorConfig));
	}

	/**
	 * Follow Business.
	 * @param businessName
	 * @return DidGenerator instance.
	 */
	public DidGenerator follow(String businessName) {
		if (StrKit.isBlank(businessName)) {
			throw (new DidException("The businessName cannot be Empty."));
		}
		
		if (this.businessMapping.containsKey(businessName)) {
			return this.businessMapping.get(businessName);
		}

		DidGenerator didGenerator = new DidGenerator(this.generatorConfig);
		didGenerator.follow(businessName);

		this.businessMapping.put(businessName, didGenerator);

		return didGenerator;
	}

	/**
	 * the `businessName`'s current id.
	 * @param businessName
	 * @return if not found return -1L
	 */
	public Long current(String businessName) {
		if (StrKit.isBlank(businessName)) {
			throw (new DidException("The businessName cannot be Empty."));
		}

		if (this.businessMapping.containsKey(businessName)) {
			return this.businessMapping.get(businessName).current();
		}
		return -1L;
	}

	/**
	 * the `businessName`'s next id.
	 * @param businessName
	 * @return if not found return -1L
	 */
	public Long next(String businessName) {
		if (StrKit.isBlank(businessName)) {
			throw (new DidException("The businessName cannot be Empty."));
		}
		if (this.businessMapping.containsKey(businessName)) {
			return this.businessMapping.get(businessName).next();
		}
		return -1L;
	}

	private DidLoader() {
		;
	}
	
	private DidLoader(GeneratorConfig generatorConfig) {
		if (null == generatorConfig) {
			throw (new DidException("The generatorConfig cannot be null."));
		}

		if (StrKit.isBlank(generatorConfig.getIp())) {
			throw (new DidException("The generatorConfig's ip cannot be null."));
		}

		if (0 == generatorConfig.getPort()) {
			throw (new DidException("Set the generatorConfig's port."));
		}
		
		this.generatorConfig = generatorConfig;

		this.businessMapping = new ConcurrentHashMap<String, DidGenerator>();
	}

}
