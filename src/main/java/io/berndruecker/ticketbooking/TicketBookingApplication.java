package io.berndruecker.ticketbooking;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = { "classpath:ticket-booking.bpmn" })
public class TicketBookingApplication {

  @Value("${zeebeRegion}")
  String zeebeRegion;

  public static void main(String[] args) {
    TicketBookingApplication app = new TicketBookingApplication();
    System.out.print("----------\n\n\n");
    System.out.print(app.zeebeRegion);
    System.out.print("\n----------\n\n\n");
    SpringApplication.run(TicketBookingApplication.class, args);

    // System.out.print("----------\n\n\n");
    // System.out.print(zeebeRegion);
    // System.out.print("\n----------\n\n\n");
    // SpringApplication.run(TicketBookingApplication.class, args);
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public Queue paymentResponseQueue(){
    return new Queue("paymentResponse",true);
  }

}
