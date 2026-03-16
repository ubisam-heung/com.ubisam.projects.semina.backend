package com.ubisam.projects.semina.backend.api.sessions;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.ubisam.projects.semina.backend.domain.Session;

import io.u2ware.common.data.rest.core.annotation.HandleBeforeRead;

@Component
@RepositoryEventHandler
public class SessionHandler {

    @HandleBeforeRead
    public void HandleBeforeRead(Session session, Specification<Session> spec)throws Exception{

    }
}
