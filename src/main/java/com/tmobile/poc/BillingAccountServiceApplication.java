package com.tmobile.poc;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.tmobile.poc.service.BillingAccountService;
import com.tmobile.poc.vo.BillingAccountSummaryVO;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BillingAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingAccountServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

//	@PostConstruct
//	public static void init() {
//		BillingAccountSummaryVO account = new BillingAccountSummaryVO();
//		account.setCustomerId(1);
//		account.setPhoneNumber("470-985-0288");
//		account.setLastStmtBal(new Double(100.00));
//		account.setUnbilledDebits(new Double(25.00));
//		account.setUnbilledCredits(new Double(15.00));
//		account.setUnbilledPayments(new Double(50.00));
//		account.setCurrentBal(account.getLastStmtBal() + account.getUnbilledDebits()
//				- account.getUnbilledCredits() - account.getUnbilledPayments());
//		BillingAccountService.accountMap.put(1, account);
//	}
}
