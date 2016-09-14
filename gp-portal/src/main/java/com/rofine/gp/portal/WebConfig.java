package com.rofine.gp.portal;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.rofine.gp.domain.organization.target.execute.ObjectTargetExecuteDomainServiceStub;
import com.rofine.gp.domain.organization.target.service.ObjectTargetExecuteDomainService;

@Configuration
public class WebConfig {

	public static final String TARGET_EXECUTE_SERVICE_URL = "http://TARGET-EXECUTE-SERVICE";

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

		DelegatingFilterProxy proxy = new DelegatingFilterProxy("shiroFilter");
		proxy.setTargetFilterLifecycle(true);

		filterRegistrationBean.setFilter(proxy);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");

		return filterRegistrationBean;
	}

	/**
	 * A customized RestTemplate that has the ribbon load balancer build in.
	 * Note that prior to the "Brixton"
	 * 
	 * @return
	 */
	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * The AccountService encapsulates the interaction with the micro-service.
	 * 
	 * @return A new service instance.
	 */
	@Bean
	public ObjectTargetExecuteDomainService objectTargetExecuteDomainService() {
		return new ObjectTargetExecuteDomainServiceStub(TARGET_EXECUTE_SERVICE_URL);
	}

}
