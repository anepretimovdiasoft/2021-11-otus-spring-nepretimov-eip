package ru.diasoft;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.diasoft.domain.Enrollee;
import ru.diasoft.domain.Graduate;

@IntegrationComponentScan
@ComponentScan
@Configuration
@EnableIntegration
public class App {

    @Bean
    public QueueChannel enrolleeChanel() {

        return MessageChannels.queue(10).get();
    }

    @Bean
    public PublishSubscribeChannel studentChannel() {

        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel graduateChannel() {

        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {

        return Pollers.fixedRate( 100 ).maxMessagesPerPoll( 2 ).get();
    }

    @Bean
    public IntegrationFlow universityFlow() {
        return IntegrationFlows.from("enrolleeChanel")
                .handle("universityService", "enrollment")
                .channel("studentChannel")
                .handle("universityService", "study")
                .channel("graduateChannel")
                .handle("universityService", "graduation")
                .get();
    }

    public static void main(String[] args) throws InterruptedException {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);


        University university = ctx.getBean(University.class);

        int count = 0;
        while (true) {
            Thread.sleep(1000);

            Enrollee enrollee = new Enrollee("Valera" + count);
            count++;
            System.out.println("Новый абитуриент: " + enrollee.getName());
            Graduate graduate = university.process(enrollee);
            System.out.println("Новый выпускник: " + graduate.getName());
        }

    }


}
