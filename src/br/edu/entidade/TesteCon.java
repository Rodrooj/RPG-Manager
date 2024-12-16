package br.edu.entidade;

import com.mongodb.client.MongoDatabase;

public class TesteCon {
    public static void main(String[] args) {
        DAO dao = new DAO();
        dao.abrirBanco();

        try {
            MongoDatabase db = dao.verBanco();
            System.out.println("Nome do banco: " + db.getName());
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        dao.fecharBanco();
    }
}
