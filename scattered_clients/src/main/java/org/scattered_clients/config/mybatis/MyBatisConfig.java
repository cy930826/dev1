package org.scattered_clients.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.scattered_clients.dao")
public class MyBatisConfig {

}
