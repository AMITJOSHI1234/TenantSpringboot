package com.yash.tenantmanagement.config;

/**
* This is a Configuration for swagger
* @author amit joshi
*/
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "TenantMangementSystem",
				description = "Contains operations related User and Property",
				summary = "A Tenant Management System is a simple software tool that helps landlords and property",
				contact = @Contact(
						name = "Amit",
						email = "amit@test.com"
						)
				),
		servers = {
				@Server(
					description = "Dev",
					url = "http://localhost:8080"
						),
				@Server(
						description = "Test",
						url = "http://localhost:8080"
						)
		}
		)
public class SwaggerConfig {

	 
	}

