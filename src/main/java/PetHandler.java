package main.java;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class PetHandler implements HttpHandler {

    private final PetService petService = new PetService();

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";

        if ("GET".equals(exchange.getRequestMethod())) {
            response = createResponse(petService.getPets());
        }
        if ("POST".equals(exchange.getRequestMethod())) {
            InputStream inputStream = exchange.getRequestBody();
            String result = new BufferedReader(new InputStreamReader(inputStream))
                    .lines().collect(Collectors.joining("\n"));

            String[] resultArray = result.split(";");
            response = createResponse(petService.savePet(resultArray[0], resultArray[1]));

        }
        //возврашаю код ответа
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
    private String createResponse(List<PetEntity> petList) {
        String result = "";
        for (PetEntity pet : petList){
            result = result + "nickName: " + pet.getNickname() + ",type: " + pet.getType() + "\n";
        }
        return result;
    }
}
