package br.edu.visao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sistema de Fichas");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            frame.add(panel);

            JLabel titleLabel = new JLabel("Bem-vindo ao Gerenciador de Fichas");
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            panel.add(titleLabel);

            panel.add(Box.createRigidArea(new Dimension(0, 20)));

            JButton btnCarregar = new JButton("Carregar uma Ficha");
            btnCarregar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnCarregar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    carregarFicha();
                }
            });
            panel.add(btnCarregar);

            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            JButton btnNova = new JButton("Criar uma Ficha Nova");
            btnNova.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnNova.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    criarNovaFicha();
                }
            });
            panel.add(btnNova);

            frame.setVisible(true);
        });
    }

    private static void carregarFicha() {
        // Aqui você pode implementar a lógica para carregar uma ficha existente
        System.out.println("Abrindo interface principal com ficha carregada...");
        abrirInterfacePrincipal(true);
    }

    private static void criarNovaFicha() {
        // Aqui você pode implementar a lógica para criar uma nova ficha
        System.out.println("Abrindo interface principal para criar uma nova ficha...");
        abrirInterfacePrincipal(false);
    }

    private static void abrirInterfacePrincipal(boolean isCarregarFicha) {
        // Chamar a classe Ficha ou a interface principal, passando os parâmetros necessários
        Ficha.main(new String[]{isCarregarFicha ? "carregar" : "nova"});
    }
}

