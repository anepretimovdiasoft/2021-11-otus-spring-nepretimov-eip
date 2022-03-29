package ru.diasoft;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.diasoft.domain.Enrollee;
import ru.diasoft.domain.Graduate;

@MessagingGateway
public interface University {

    @Gateway(requestChannel = "enrolleeChanel", replyChannel = "studentChannel")
    Graduate process(Enrollee enrollee);
}
