package com.mayby.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public DataSource dataSource() {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		// HikariCP로 커넥션 풀 set
		factoryBean.setDataSource(dataSource());
		// Mapper.xml 파일 get
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/*.xml"));
		// alias인 VO의 위치 설정
		factoryBean.setTypeAliasesPackage("com.mayby.*.vo");
		// 마이바티스 설정과 관련된 빈을 설정
		factoryBean.setConfiguration(mybatisConfg());
		return factoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	// application.properties에서 mybatis.configuration으로 시작하는 모든 설정을 읽어 빈으로 등록
	public org.apache.ibatis.session.Configuration mybatisConfg() {
		return new org.apache.ibatis.session.Configuration();
	}
}
