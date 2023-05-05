package com.sysmap.demo.services;

import com.sysmap.demo.entities.User;

public interface IEventService {
    void send(User event);
}
