package com.ddy.spide.acquire_web_data.model;

import java.security.Principal;

public class MyPrincipal implements Principal {

    private String name;

    public MyPrincipal(String user) {
        this.name = user;
    }

    @Override
    public String getName() {
        return name;
    }
}
