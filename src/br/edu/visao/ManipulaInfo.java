package br.edu.visao;

import br.edu.entidade.DAO;
import org.bson.Document;

import javax.swing.*;

public class ManipulaInfo extends JFrame {
    private JTextField nomeField;
    private JTextField idadeField;
    private JTextArea habilidadesArea;

    public ManipulaInfo() {
        setTitle("Gerenciador de Fichas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        nomeField = new JTextField(20);
        idadeField = new JTextField(20);
        habilidadesArea = new JTextArea(5, 20);

        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("Idade:"));
        add(idadeField);
        add(new JLabel("Habilidades:"));
        add(new JScrollPane(habilidadesArea));

        JButton salvarButton = new JButton("Salvar Ficha");
        salvarButton.addActionListener(e -> salvarFicha());
        add(salvarButton);
    }

    private Document coletarDados() {
        Document ficha = new Document();
        ficha.append("nome", nomeField.getText());
        ficha.append("idade", Integer.parseInt(idadeField.getText()));
        ficha.append("habilidades", habilidadesArea.getText());
        return ficha;
    }

    private void salvarFicha() {
        try {
            Document ficha = coletarDados();
            DAO dao = new DAO();
            dao.abrirBanco();
            dao.inserir("fichas", ficha);
            dao.fecharBanco();
            JOptionPane.showMessageDialog(this, "Ficha salva com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar ficha: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ManipulaInfo().setVisible(true));
    }
}
