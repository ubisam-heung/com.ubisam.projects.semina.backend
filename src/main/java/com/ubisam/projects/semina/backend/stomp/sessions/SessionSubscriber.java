package com.ubisam.projects.semina.backend.stomp.sessions;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ubisam.projects.semina.backend.api.sessions.SessionRepository;
import com.ubisam.projects.semina.backend.domain.Session;

import io.u2ware.common.stomp.client.WebsocketStompClient;
import io.u2ware.common.stomp.client.WebsocketStompClientHandler;
import io.u2ware.common.stomp.client.config.WebsocketStompProperties;

@Component
public class SessionSubscriber implements WebsocketStompClientHandler{


    protected @Autowired SessionRepository sessionRepository;
    protected @Autowired ObjectMapper mapper;
    protected @Autowired WebsocketStompProperties properties;

    @Override
    public String getDestination() {
        return properties.getSubscriptions().get("sessions");
    }


    @Override
    public void handleFrame(WebsocketStompClient client, JsonNode message) {

        System.out.println("RECEIVED: "+ message);

        Long timestamp = message.get("timestamp").asLong();
        String principal = message.get("principal").asText();
        JsonNode payloadNode = message.get("payload");
        String state = null;
        if (payloadNode != null && payloadNode.has("state")) {
            state = payloadNode.get("state").asText();
        } else if (payloadNode != null && payloadNode.isValueNode()) {
            state = payloadNode.asText();
        } else {
            state = null;
        }

        Session session = new Session();
        session.setPrincipal(principal);
        session.setTimestamp(timestamp);
        session.setState(state);
        sessionRepository.save(session);


        ObjectNode send = mapper.valueToTree(session);
        System.out.println("Saved session message: " + send);
        client.send("/app/ubisam", send);
    }
}
