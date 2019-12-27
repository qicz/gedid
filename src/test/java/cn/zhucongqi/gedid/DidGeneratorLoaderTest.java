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

import cn.zhucongqi.gedid.core.GeneratorConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cn.zhucongqi.gedid.core.DidGenerator;

class DidGeneratorLoaderTest {

	DidLoader loader;
	
	@BeforeEach
	void setUp() throws Exception {
		loader = DidLoader.init(GeneratorConfig.defaultRedisConfig());
	}

	@Test
	void test() {
		DidGenerator user = loader.follow("user");
		
		Long id = user.current();
		System.out.println("start user id =>"+id + "loader.current =>"+loader.current("user"));
		
		DidGenerator order = loader.follow("order");
		
		id = order.current();
		System.out.println("start order id =>"+id + "loader.current =>"+loader.current("order"));
		
		new Thread() {
			public void run() {
				for (int j = 0; j < 10; j++) {
					DidGenerator user = loader.follow("user");

					Long id = user.next();
					System.out.println(Thread.currentThread().getName()+"> user id=>"+id);
				}
			}

		}.start();
		
		new Thread() {
			public void run() {
				for (int j = 0; j < 10; j++) {
					DidGenerator user = loader.follow("order");
					
					Long id = user.next();
					System.out.println(Thread.currentThread().getName()+"> order id=>"+id);
				}
			}
			
		}.start();

	}

}
