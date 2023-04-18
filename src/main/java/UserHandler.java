package main.java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserHandler implements HttpHandler {
    private final UserService userService = new UserService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // инициализировать стринг
        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = createResponse(userService.getUsers());
        }

        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream inputStream = exchange.getRequestBody();
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));

            String[] resultArray = result.split(";");
            response = createResponse(userService.saveUser(resultArray[0],resultArray[1],resultArray[2]));
        }

        // возврашаю код ответа
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

    private String createResponse(List<UserEntity> userList) {
        String result = "";
        for (UserEntity user : userList) {
            result = result + "name: " + user.getName() + ", passNum: " + user.getPassNum() + ", balance: " + user.getBalance() + "\n";
        }
        return result;
    }
}
