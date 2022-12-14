package com.hongsi.mapleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MapletonApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.properties,"
            + "classpath:application.yml";

    public static void main(String[] args) {

        new SpringApplicationBuilder(MapletonApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);

    }

}
