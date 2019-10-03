package com.poc.redisdemo.redispoc;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;



@SpringBootApplication
@EnableCaching
@EntityScan("com.poc.redisdemo.*")  
public class RedisApp implements CommandLineRunner {

	  private final UserRepository userRepository;
	  private final Logger LOG = LoggerFactory.getLogger(getClass());

	  @Autowired
	  public RedisApp(UserRepository userRepository) {
	    this.userRepository = userRepository;
	  }

	  public static void main(String[] args) {
	    SpringApplication.run(RedisApp.class, args);
	  }

	  @Override
	  public void run(String... strings) {
	    //Populating embedded database here
	    LOG.info("Saving users. Current user count is {}.", userRepository.count());
	    User aryan = new User("Aryan", 2000);
	    User vicky = new User("Vicky", 29000);
	    User lewis = new User("Lewis", 550);

	    userRepository.save(aryan);
	    userRepository.save(vicky);
	    userRepository.save(lewis);
	    LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	  }
	}
