import service.CepResearch;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CepResearch research = new CepResearch();
        research.menu();
    }
}
