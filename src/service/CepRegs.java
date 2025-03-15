package service;

import com.google.gson.internal.bind.util.ISO8601Utils;
import model.ViaCep;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// Gravar os ceps salvos em um arquivo
public class CepRegs {

    public static void saveFile(List<ViaCep> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ViaCep item : list) {
                writer.write(String.valueOf(item));
                writer.newLine();
            }
            System.out.println("Salvo no arquivo com sucesso!");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao salvar o arquivo: " + e.getMessage());
        }
    }
    }

