package utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:movies.properties")
public class Constants {

    @Value( "${movie-crawler.base-url}" )
    public static String BASE_URL;

}
