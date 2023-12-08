package org.example.Backend;

public class Utilizador {
    private int id;
    private int tokens;

    private String username;
    private String email;
    private String password;
    private String nome;

    public Utilizador(int id, int tokens, String username, String email, String password, String nome) {
        this.id = id;
        this.tokens = tokens;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
    }
    public Utilizador( String username, String email, String password, String nome) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }



    public Utilizador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
