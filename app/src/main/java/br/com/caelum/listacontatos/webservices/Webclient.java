package br.com.caelum.listacontatos.webservices;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Webclient {

    public static final String URL = "https://www.caelum.com.br/mobile";

    public String pegaMedia(String jsonComAlunos) {

        try {
            MediaType tipo = MediaType
                    .parse("application/json; charset=utf-8");

            RequestBody corpoDaRequisicao = RequestBody
                    .create(tipo, jsonComAlunos);

            Request requisicao = new Request.Builder()
                    .url(URL)
                    .post(corpoDaRequisicao)
                    .build();


            OkHttpClient client = new OkHttpClient();

            Response resposta = client.newCall(requisicao).execute();


            ResponseBody corpoDaResposta = resposta.body();

            String jsonComMedia = corpoDaResposta.string();


            return jsonComMedia;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Deu algo errado";
    }

}
