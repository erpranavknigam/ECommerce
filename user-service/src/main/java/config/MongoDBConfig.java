package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.ecommerce.app.userservice.repository.mongo")
public class MongoDBConfig {

}
