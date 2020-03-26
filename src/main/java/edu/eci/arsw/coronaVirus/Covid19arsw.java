package edu.eci.arsw.coronaVirus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Corona Virus 19!
 *
 */
@SpringBootApplication
public class Covid19arsw
{
    public static void main( String[] args )
    {
        SpringApplication.run(Covid19arsw.class, args);
        System.out.println( "Corona Virus 19!" );
    }
}
