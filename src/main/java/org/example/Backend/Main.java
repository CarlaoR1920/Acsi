package org.example.Backend;

import org.example.Frontend.Login;
import org.example.Kafka.Producer1;
import org.json.JSONObject;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //Login login = new Login();
        Producer1 prod1 = new Producer1();

        JSONObject utilizador = new JSONObject();
        utilizador.put("username", "exemploUser");
        utilizador.put("email", "exemplo@email.com");
        utilizador.put("password", "senha123");
        utilizador.put("nome", "Nome Sobrenome");

        JSONObject json = new JSONObject();
        json.put("tipo", "Insert");
        json.put("utilizador", utilizador);

        String jsonString = json.toString();

        prod1.prod1(jsonString);
    }
}
