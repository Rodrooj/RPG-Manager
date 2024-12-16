package br.edu.entidade;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class DAO {
    private MongoClient mongoClient;
    private MongoDatabase database;

    private static final String CONNECTION_STRING = "mongodb+srv://Rodrigo:4BXfFTs2edxx&xY@cluster0.5nssi.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
    private static final String DATABASE_NAME = "RPG_Project";

    // Método para conectar ao MongoDB
    public void abrirBanco() {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            System.out.println("Conexão com o MongoDB estabelecida com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar ao MongoDB: " + e.getMessage());
        }
    }

    // Método para obter a instância do banco de dados
    public MongoDatabase verBanco() {
        if (database == null) {
            throw new IllegalStateException("A conexão com o MongoDB não foi estabelecida. Chame o método abrirBanco() primeiro.");
        }
        return database;
    }

    // Método para fechar a conexão
    public void fecharBanco() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão com o MongoDB encerrada.");
        }
    }

    // CREATE - Inserir um documento
    public void inserir(String collectionName, Document document) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(document);
        System.out.println("Documento inserido com sucesso na coleção: " + collectionName);
    }

    // READ - Obter todos os documentos
    public List<Document> listarDocumentos(String collectionName) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        List<Document> documents = new ArrayList<>();
        collection.find().into(documents);
        return documents;
    }

    // READ - Buscar por filtro
    public List<Document> procurarPorFiltro(String collectionName, Bson filter) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        List<Document> documents = new ArrayList<>();
        collection.find(filter).into(documents);
        return documents;
    }

    // UPDATE - Atualizar documentos com filtro
    public void atualizar(String collectionName, Bson filter, Bson update) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.updateMany(filter, update);
        System.out.println("Documentos atualizados na coleção: " + collectionName);
    }

    // DELETE - Remover documentos por filtro
    public void deletar(String collectionName, Bson filter) {
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.deleteMany(filter);
        System.out.println("Documentos removidos da coleção: " + collectionName);
    }
}
