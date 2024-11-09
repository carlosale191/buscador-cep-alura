package model;

import com.google.gson.annotations.SerializedName;

public record ViaCep(@SerializedName("cep") String cep,
                     @SerializedName("logradouro") String logradouro,
                     @SerializedName("bairro") String bairro,
                     @SerializedName("uf") String uf) {

    @Override
    public String toString() {
        return "Endereço: " +
                "CEP: '" + cep + '\'' +
                ", logradouro: '" + logradouro + '\'' +
                ", bairro: '" + bairro + '\'' +
                ", UF: '" + uf + '\'' +
                '}';
    }
}
