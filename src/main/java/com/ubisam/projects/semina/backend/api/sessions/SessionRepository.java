package com.ubisam.projects.semina.backend.api.sessions;


import com.ubisam.projects.semina.backend.domain.Session;

import io.u2ware.common.data.jpa.repository.RestfulJpaRepository;

public interface SessionRepository extends RestfulJpaRepository<Session,String>{

}
