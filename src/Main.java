import service.CepResearch;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var research = new CepResearch();
        research.menu();
    }
}
