/*
 * Copyright © 2019 collin (1634753825@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.smartframework.cloud.starter.mybatis.plus.test.prepare.mybatisplus.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.smartframework.cloud.starter.mybatis.plus.common.mapper.SmartMapper;
import org.smartframework.cloud.starter.mybatis.plus.test.prepare.mybatisplus.entity.ProductInfoEntity;

/**
 * 商品信息base mapper
 *
 * @author collin
 * @date 2021-03-23
 */
@Mapper
public interface ProductInfoBaseMapper extends SmartMapper<ProductInfoEntity> {

}