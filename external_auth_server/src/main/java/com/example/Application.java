package com.example;

import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * You simply can test this with curl:
 * $ curl acme:acmesecret@localhost:9999/uaa/oauth/token -d grant_type=password -d username=user -d password=password
 * $ {"access_token":"232f6fe0-f117-4a0f-8dd6-9c7a7b5f1cd2","token_type":"bearer","refresh_token":"f9e87b15-f764-47b7-a34a-9665cd4d4967","expires_in":43199,"scope":"openid"}
 * $ TOKEN=232f6fe0-f117-4a0f-8dd6-9c7a7b5f1cd2
 * $ curl -H "Authorization: Bearer $TOKEN" localhost:9999/uaa/user
 * $ {"details":{"remoteAddress":"127.0.0.1","sessionId":null,"tokenValue":"232f6fe0-f117-4a0f-8dd6-9c7a7b5f1cd2","tokenType":"Bearer",.... 
 * 
 * @author straubec
 */
@SpringBootApplication
@EnableAuthorizationServer
@RestController
@EnableResourceServer
public class Application {

    private static final Logger LOG= Logger.getLogger( Application.class.getName() );
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    

    /**
     * User info endpoint. 
     * 
     * @param user
     * @return 
     */
    @RequestMapping("/user")
    public Principal user(Principal user) {
        LOG.log(Level.INFO, "reading user with name > {0}", user.getName());
        return user;
    }
}
