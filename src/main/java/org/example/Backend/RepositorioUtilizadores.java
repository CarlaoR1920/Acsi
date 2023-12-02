package org.example.Backend;
import java.util.ArrayList;
public class RepositorioUtilizadores {
    private ArrayList<Utilizador> utilizadores;

    public RepositorioUtilizadores() {

        utilizadores = new ArrayList<>();

    }

    public ArrayList<Utilizador> getUtilizadores() {

        return utilizadores;

    }

    public void setUtilizadores(ArrayList<Utilizador> utilizadores) {

        this.utilizadores = utilizadores;

    }

    public Utilizador get(int i) {
        return utilizadores.get(i);
    }

    public void adicionarUtilizador(Utilizador utilizador) {

        if (utilizadores.contains(utilizador)) {

            System.out.println("O utilizador já se encontra na Lista.");
        } else {
            utilizadores.add(utilizador);
        }
    }

    public void removerUtilizador(Utilizador utilizador) {

        if (utilizadores.contains(utilizador)) {

            utilizadores.remove(utilizador);

        } else {

            System.out.println("O utilizador nao se encontra na lista");

        }

    }

    public int getTamanhoUtilizadores() {

        return utilizadores.size();

    }

    public boolean existeUtilizador(String username) {

        for (Utilizador u : utilizadores) {

            if (u != null && u.getUsername().equals(username)) {

                return true;

            }

        }

        return false;
    }
}
