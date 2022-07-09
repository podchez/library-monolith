package com.podchez.librarymonolith.integration.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ActiveProfiles("test")
@Sql({
        "classpath:sql/V1__Create_initial_tables.sql",
        "classpath:sql/V2__Insert_initial_values.sql",
})
@SpringBootTest
public @interface IntegrationTest {
}
