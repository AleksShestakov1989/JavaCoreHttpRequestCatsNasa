package task2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import task1.Cats;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();
        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=fvzFn3VRC8ANa1TqPzw7WEIhs0sGkV414Ha7Axdy");
        CloseableHttpResponse response = httpClient.execute(request);//отправка запроса

        ObjectMapper mapper = new ObjectMapper();
        Post post = mapper.readValue(response.getEntity().getContent(), Post.class);
        //System.out.println(post);

        try (InputStream in = new URL(post.getUrl()).openStream()) {
            Files.copy(in, Paths.get(post.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("File save in " + post.getName());


    }
}
