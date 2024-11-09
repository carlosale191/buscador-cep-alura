package service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exception.FormatValidationException;
import model.ViaCep;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CepResearch {
    private final String ADDRESS = "https://viacep.com.br/ws/";
    private final String ADDRESS_END = "/json/";
    private String apiData;
    private String cep = "";

    public void menu() throws IOException, InterruptedException {
        Scanner reader = new Scanner(System.in);
        List<ViaCep> listCeps = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!cep.equalsIgnoreCase("sair")) {
            System.out.println("Digite o CEP a ser procurado: ");
            System.out.println("Sair para finalizar");
            cep = reader.nextLine().trim();

            if (cep.length() > 8) {
                throw new FormatValidationException("Entrada de CEP inválida, deve haver 8 digítos, sem letras, espaços ou outros caracteres.");
            }

            if (cep.equalsIgnoreCase("sair")){
                break;
            }

            apiData = ADDRESS + cep.replaceAll("[^\\d]", "").trim() + ADDRESS_END;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(apiData))
                        .build();
                HttpResponse<String> response = client
                        .send(request,HttpResponse.BodyHandlers.ofString());

                if(response.statusCode() == 400) {
                    throw new FormatValidationException("Entrada de CEP inválida, deve haver 8 digítos, sem letras, espaços ou outros caracteres.");
                }
                String json = response.body();


                ViaCep myResearchJson = gson.fromJson(json, ViaCep.class);
                System.out.println(myResearchJson);
                listCeps.add(myResearchJson);

            } catch (IllegalArgumentException e) {
                System.out.println("Entrada de CEP inválida.");
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Houve um erro inesperado. " + e.getMessage());
            }

        }
    }
}
