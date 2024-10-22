package com.insurance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
     public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // The application context will load the JPA configuration, connect to the database, and generate the schema
        System.out.println("Schema generated and application context loaded!");
    }
}
