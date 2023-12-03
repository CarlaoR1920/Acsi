package org.example.Backend;

import org.example.Frontend.Login;
import org.example.Kafka.Producer1;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Login login = new Login();
        Producer1 prod1 = new Producer1();
        prod1.prod1();
    }
}
