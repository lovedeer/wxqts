package com.wxqts.platform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan("com.wxqts.platform.dao")
@ServletComponentScan 
public class WxqtsplatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(WxqtsplatformApplication.class, args);
	}
}
