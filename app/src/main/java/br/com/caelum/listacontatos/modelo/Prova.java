package br.com.caelum.listacontatos.modelo;

import java.io.Serializable;
import java.util.List;

public class Prova implements Serializable{
    private String materia;
    private String data;
    private List<String> topicos;

    public Prova(String materia, String data) {

        this.materia = materia;
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public String getData() {
        return data;
    }


    @Override
    public String toString() {
        return materia;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }
}
