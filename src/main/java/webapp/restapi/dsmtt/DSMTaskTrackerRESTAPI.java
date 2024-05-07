package webapp.restapi.dsmtt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DSMTaskTrackerRESTAPI {

	public static void main(String[] args) {
		SpringApplication.run(DSMTaskTrackerRESTAPI.class, args);
		System.out.println("Started");
	}

}