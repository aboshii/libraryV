package com.library.springlibrary.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

//session component
@Getter
@Service
@Scope(scopeName = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS) //TARGET_CLASS used for declaring CGLib as proxy instance library
public class VisitCounter {
    private int visitCount;

    public void visitCounterCountUp() {
        visitCount++;
    }
}