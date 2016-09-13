package com.rofine.gp.portal;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import com.rofine.gp.domain.organization.target.execute.ObjectTargetExecuteDomainServiceStub;
import com.rofine.gp.platform.bean.ApplicationContextUtil;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link AccountsConfiguration}. This is a deliberate separation of concerns.
 * 
 * @author Paul Chapman
 */
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.rofine.gp.*" })
@EntityScan(basePackages = { "com.rofine.gp.*"})
@ComponentScan(basePackages = { "com.rofine.gp.*"})
@ImportResource(locations={"classpath:spring-shiro.xml"})
@EnableDiscoveryClient
public class WebServer {

	protected Logger logger = Logger.getLogger(WebServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for accounts-server.properties or
		// accounts-server.yml
		System.setProperty("spring.config.name", "web-server");

		ApplicationContext applicationContext = SpringApplication.run(WebServer.class, args);
		
		ApplicationContextUtil.setApplicationContext(applicationContext);
	}
}
