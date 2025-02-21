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
package org.smartframework.cloud.starter.redis.test.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.smartframework.cloud.starter.redis.adapter.IRedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.Set;

class RedisAdapterImplIntegrationTest extends AbstractRedisIntegrationTest {

    @Autowired
    private IRedisAdapter redisAdapter;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @BeforeEach
    void beforeTest() {
        Set<String> keys = stringRedisTemplate.keys("*");
        stringRedisTemplate.delete(keys);
    }

    @Test
    void testSetString() {
        String key = "SetStringkey";
        String value = "SetStringvalue";
        redisAdapter.setString(key, value, null);
        redisAdapter.setString(key, value, 1000 * 60L);
        String expectedValue = redisAdapter.getString(key);
        Assertions.assertThat(value).isEqualTo(expectedValue);
    }

    @Test
    void testDelete() {
        String key = "deletekey";
        String value = "deletevalue";
        redisAdapter.setString(key, value, null);
        String expectedValue1 = redisAdapter.getString(key);
        Assertions.assertThat(value).isEqualTo(expectedValue1);

        Boolean result1 = redisAdapter.delete(key);
        Assertions.assertThat(result1).isTrue();

        Boolean result2 = redisAdapter.delete(key);
        Assertions.assertThat(result2).isFalse();

        String expectedValue2 = redisAdapter.getString(key);
        Assertions.assertThat(expectedValue2).isNull();
    }

    @Test
    void testBatchDelete() {
        String key1 = "batchdeletekey1";
        String value1 = "batchdeletevalue1";
        redisAdapter.setString(key1, value1, null);
        String expectedValue1 = redisAdapter.getString(key1);
        Assertions.assertThat(value1).isEqualTo(expectedValue1);

        String key2 = "batchdeletekey2";
        String value2 = "batchdeletevalue2";
        redisAdapter.setString(key2, value2, null);
        String expectedValue2 = redisAdapter.getString(key2);
        Assertions.assertThat(value2).isEqualTo(expectedValue2);

        Boolean result1 = redisAdapter.delete(Arrays.asList(key1, key2));
        Assertions.assertThat(result1).isTrue();

        Boolean result2 = redisAdapter.delete(Arrays.asList(key1, key2));
        Assertions.assertThat(result2).isFalse();

        String expectedValue12 = redisAdapter.getString(key1);
        String expectedValue22 = redisAdapter.getString(key2);
        Assertions.assertThat(expectedValue12).isNull();
        Assertions.assertThat(expectedValue22).isNull();
    }

    @Test
    void testSetObject() {
        String key = "SetObjectkey";
        SetObject setObject = new SetObject("test");
        redisAdapter.setObject(key, setObject, null);
        SetObject expectedValue = redisAdapter.getObject(key, new TypeReference<SetObject>() {
        });
        Assertions.assertThat(setObject.getName()).isEqualTo(expectedValue.getName());
    }

    @Test
    void testSetNX() {
        String key = "SetNXkey";
        String value = "SetNXvalue";
        Boolean result1 = redisAdapter.setNx(key, value, 1000 * 60L);
        Assertions.assertThat(result1).isTrue();

        Boolean result2 = redisAdapter.setNx(key, value, 1000 * 60L);
        Assertions.assertThat(result2).isFalse();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class SetObject {
        private String name;
    }

}