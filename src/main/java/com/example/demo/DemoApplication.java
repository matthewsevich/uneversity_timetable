package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


//
//    private final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
//    @Autowired
//    private CustomerRepository repository;
//
//    @EventListener(ApplicationReadyEvent.class)
//    public void runAfterStartup() {
//        List allCustomers = this.repository.findAll();
//        logger.info("Number of customers: " + allCustomers.size());
//
//        Customer newCustomer = new Customer();
//        newCustomer.setFirstName("John");
//        newCustomer.setLastName("Doe");
//        logger.info("Saving new customer...");
//        this.repository.save(newCustomer);
//
//        allCustomers = this.repository.findAll();
//        logger.info("Number of customers: " + allCustomers.size());
//    }
}
