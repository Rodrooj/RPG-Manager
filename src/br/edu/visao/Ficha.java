package br.edu.visao;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import br.edu.entidade.DAO;
import org.bson.Document;

public class Ficha extends JFrame {

    private static final Dimension FRAME_SIZE = new Dimension(1200, 800);
    private static final Insets DEFAULT_INSETS = new Insets(10, 10, 10, 10);
    private static final Color BACKGROUND_COLOR = new Color(255, 255, 255);

    // Método utilitário para configurar o GridBagConstraints
    private GridBagConstraints createGbc(int x, int y, int width, int height) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = DEFAULT_INSETS;
        return gbc;
    }

    public Ficha() {
        setTitle("Ficha do Jogador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(FRAME_SIZE);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc;

        // Header Panel
        gbc = createGbc(0, 0, 4, 1);
        mainPanel.add(createHeaderPanel(), gbc);

        // Attributes Panel
        gbc = createGbc(0, 1, 1, 2);
        mainPanel.add(createAttributesPanel(), gbc);

        // Bottom Panel
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        bottomPanel.add(createPerceptionPanel());
        bottomPanel.add(createLanguagesAndProficiencyPanel());
        gbc = createGbc(0, 3, 2, 1);
        mainPanel.add(bottomPanel, gbc);

        // Saving Throws and Bonuses Panel
        JPanel leftTopPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        leftTopPanel.setOpaque(false);
        leftTopPanel.add(createBonusesPanel());
        leftTopPanel.add(createSavingThrowsPanel());
        gbc = createGbc(1, 1, 1, 1);
        mainPanel.add(leftTopPanel, gbc);

        // Skills Panel
        gbc = createGbc(1, 2, 1, 1);
        mainPanel.add(createSkillsPanel(), gbc);

        // Combat Panel
        gbc = createGbc(2, 1, 1, 1);
        mainPanel.add(createCombatPanel(), gbc);

        // Attacks and Spells Panel
        gbc = createGbc(2, 2, 1, 1);
        mainPanel.add(createAttacksAndSpellsPanel(), gbc);

        // Money and Equipment Panel
        gbc = createGbc(2, 3, 1, 1);
        mainPanel.add(createMoneyAndEquipmentPanel(), gbc);

        // Background Panel
        gbc = createGbc(3, 1, 1, 1);
        mainPanel.add(createBackgroundPanel(), gbc);

        // Big Text Panel
        gbc = createGbc(3, 2, 1, 2);
        mainPanel.add(createBigTextPanel(), gbc);

        // Final Setup
        add(new JScrollPane(mainPanel)); // Scroll in case content overflows
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = stylePanel("Informações do Personagem");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Expande os campos horizontalmente

        // Lista de informações e posições
        String[][] infoPositions = {
                {"Nome do Personagem:", "0", "2"},
                {"Classe e Nível:", "2", "1"},
                {"Antecedente:", "4", "1"},
                {"Nome do Jogador:", "6", "1"},
                {"Raça:", "2", "3"},
                {"Tendência:", "4", "3"},
                {"Pontos de Experiência:", "6", "3"}
        };

        for (String[] info : infoPositions) {
            String label = info[0];
            int x = Integer.parseInt(info[1]);
            int y = Integer.parseInt(info[2]);

            gbc.gridx = x;
            gbc.gridy = y;
            panel.add(new JLabel(label), gbc);

            gbc.gridx = x + 1;
            JTextField textField = new JTextField(15);
            textField.setPreferredSize(new Dimension(15, 20));
            panel.add(textField, gbc);

            // Adiciona ao mapa
            String key = label.replaceAll(" ", "").toLowerCase(); // Exemplo de chave única
            fieldsMap.put(key, textField);
        }
        return panel;
    }

    private JPanel createBonusesPanel() {
        JPanel panel = stylePanel("Bônus");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(createLabeledField("Inspiração:", 5), gbc);

        gbc.gridx = 1;
        panel.add(createLabeledField("Bônus de Proficiência:", 5), gbc);
        return panel;
    }

    private JPanel createAttributesPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), "Atributos",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));

        String[] attributes = {"Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma"};
        for (String attr : attributes) {
            JPanel attrPanel = new JPanel(new BorderLayout());
            attrPanel.setOpaque(false);
            attrPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

            JLabel label = new JLabel(attr, SwingConstants.CENTER);
            JTextField valueField = new JTextField(10);
            valueField.setPreferredSize(new Dimension(50, 50));
            JLabel modifierLabel = new JLabel("+0", SwingConstants.CENTER);

            attrPanel.add(label, BorderLayout.NORTH);
            attrPanel.add(valueField, BorderLayout.CENTER);
            attrPanel.add(modifierLabel, BorderLayout.SOUTH);

            panel.add(attrPanel);
        }
        return panel;
    }

    private JPanel createSavingThrowsPanel() {
        return createSkillOrSavePanel("Testes de Resistência", new String[] {
                "Força", "Destreza", "Constituição", "Inteligência", "Sabedoria", "Carisma"
        });
    }

    private JPanel createSkillsPanel() {
        return createSkillOrSavePanel("Perícias", new String[] {
                "Acrobacia (Des)", "Arcanismo (Int)", "Atletismo (For)", "Enganação (Car)",
                "História (Int)", "Intimidação (Car)", "Intuição (Sab)", "Investigação (Int)",
                "Medicina (Sab)", "Natureza (Int)", "Percepção (Sab)", "Persuasão (Car)",
                "Prestidigitação (Des)", "Religião (Int)", "Sobrevivência (Sab)"
        });
    }

    private JPanel createSkillOrSavePanel(String title, String[] items) {
        JPanel panel = new JPanel(new GridLayout(items.length, 1, 5, 5));
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), title,
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));

        for (String item : items) {
            JPanel row = new JPanel(new BorderLayout());
            row.setOpaque(false);

            JCheckBox checkBox = new JCheckBox();
            JLabel label = new JLabel(item);
            JTextField modifierField = new JTextField(3);

            row.add(checkBox, BorderLayout.WEST);
            row.add(label, BorderLayout.CENTER);
            row.add(modifierField, BorderLayout.EAST);

            panel.add(row);
        }

        return panel;
    }

    private JPanel createPerceptionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.gridx = 0;
        panel.add(new JLabel("Sabedoria Passiva: "), gbc);
        gbc.gridx ++;
        JTextField textField = new JTextField(15); // Define o tamanho pela quantidade de colunas
        panel.add(textField, gbc);

        return panel;
    }

    private JPanel createCombatPanel() {
        JPanel combatPanel = stylePanel("Combate");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.CENTER;

        // Linha 1: Classe de Armadura, Iniciativa, e Deslocamento
        gbc.gridy = 0;
        gbc.gridx = 0;
        combatPanel.add(createLabeledField("Classe ARMADURA:", 5), gbc);

        gbc.gridx = 1;
        combatPanel.add(createLabeledField("Iniciativa:", 5), gbc);

        gbc.gridx = 2;
        combatPanel.add(createLabeledField("Deslocamento:", 5), gbc);

        // Linha 2: PV Totais
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        combatPanel.add(createLabeledField("PV Totais:", 15), gbc);

        // Linha 3: Pontos de Vida Atuais
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        combatPanel.add(createLabeledField("Pontos de Vida Atuais:", 15), gbc);

        // Linha 4: Pontos de Vida Temporários
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        combatPanel.add(createLabeledField("Pontos de Vida Temporários:", 15), gbc);

        // Linha 5: Dados de Vida e Testes contra a Morte
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        combatPanel.add(createLabeledField("Dados de Vida:", 10), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        combatPanel.add(createDeathSavePanel(), gbc);

        return combatPanel;
    }

    // Função auxiliar para criar campos com labels
    private JPanel createLabeledField(String labelText, int textFieldColumns) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        JTextField textField = new JTextField(textFieldColumns);

        panel.add(label, BorderLayout.NORTH);
        panel.add(textField, BorderLayout.CENTER);

        return panel;
    }

    // Função auxiliar para criar o painel de Testes contra a Morte
    private JPanel createDeathSavePanel() {
        JPanel deathSavePanel = new JPanel(new GridBagLayout());
        deathSavePanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2);

        // Adicionando sucessos e fracassos
        gbc.gridx = 0;
        gbc.gridy = 0;
        deathSavePanel.add(new JLabel("Sucessos"), gbc);

        gbc.gridy = 1;
        deathSavePanel.add(new JLabel("Fracassos"), gbc);

        // Adicionando círculos de marcadores (representados por checkboxes)
        for (int i = 1; i <= 3; i++) {
            gbc.gridy = 0;
            gbc.gridx = i;
            deathSavePanel.add(new JCheckBox(), gbc); // Círculos para sucessos

            gbc.gridy = 1;
            deathSavePanel.add(new JCheckBox(), gbc); // Círculos para fracassos
        }

        return deathSavePanel;
    }

    private JPanel createBackgroundPanel() {
        // Painel principal para a seção de background
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BoxLayout(backgroundPanel, BoxLayout.Y_AXIS)); // Layout vertical
        backgroundPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), "Passado",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));
        //backgroundPanel.setBackground(new Color(244, 237, 215)); // Cor de pergaminho

        // Campos de texto e rótulos
        String[] labels = {"Traços de Personalidade", "Ideais", "Ligações", "Defeitos"};
        for (String label : labels) {
            // Subpainel para cada seção
            JPanel sectionPanel = new JPanel(new BorderLayout());
            sectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem
            //sectionPanel.setBackground(new Color(244, 237, 215)); // Cor de fundo igual

            // Label na parte inferior
            JLabel sectionLabel = new JLabel(label, JLabel.CENTER);
            sectionLabel.setFont(new Font("Serif", Font.BOLD, 12));
            sectionPanel.add(sectionLabel, BorderLayout.SOUTH);

            // Caixa de texto para escrever
            JTextArea textArea = new JTextArea(3, 20); // 3 linhas de altura
            textArea.setLineWrap(true); // Quebra de linha automática
            textArea.setWrapStyleWord(true); // Quebra de palavra completa
            textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o JTextArea
            //textArea.setBackground(new Color(235, 245, 255)); // Cor semelhante à imagem
            sectionPanel.add(textArea, BorderLayout.CENTER);

            // Adiciona a seção ao painel principal
            backgroundPanel.add(sectionPanel);
        }

        return backgroundPanel;
    }

    private JPanel createBigTextPanel() {
        // Painel principal para a seção de características e habilidades
        JPanel bigTextPanel = new JPanel();
        bigTextPanel.setLayout(new BoxLayout(bigTextPanel, BoxLayout.Y_AXIS)); // Layout vertical
        bigTextPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), "Anotações",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));

        // Subpainel para cada seção
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem

        // Label na parte inferior
        JLabel sectionLabel = new JLabel("Características e Habilidades", JLabel.CENTER);
        sectionLabel.setFont(new Font("Serif", Font.BOLD, 12));
        sectionPanel.add(sectionLabel, BorderLayout.SOUTH);

        // Caixa de texto para escrever
        JTextArea textArea = new JTextArea(10, 20); // 3 linhas de altura
        textArea.setLineWrap(true); // Quebra de linha automática
        textArea.setWrapStyleWord(true); // Quebra de palavra completa
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o JTextArea
        sectionPanel.add(textArea, BorderLayout.CENTER);

        // Adiciona a seção ao painel principal
        bigTextPanel.add(sectionPanel);

        return bigTextPanel;
    }

    private JPanel createAttacksAndSpellsPanel() {
        // Painel principal
        JPanel attacksPanel = new JPanel(new BorderLayout());
        attacksPanel.setBorder(BorderFactory.createTitledBorder("Ataques e Magias"));
        attacksPanel.setBackground(new Color(244, 237, 215)); // Fundo similar ao pergaminho

        // Painel para a tabela superior
        JPanel tablePanel = new JPanel(new GridLayout(4, 3, 5, 5)); // 4 linhas, 3 colunas, espaçamento de 5px
        String[] headers = {"Nome", "Bônus", "Dano / Tipo"};

        // Adiciona os cabeçalhos das colunas
        for (String header : headers) {
            JLabel headerLabel = new JLabel(header, JLabel.CENTER);
            headerLabel.setFont(new Font("Serif", Font.BOLD, 12));
            headerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para separar
            //headerLabel.setBackground(new Color(200, 200, 200)); // Cor cinza para cabeçalho
            headerLabel.setOpaque(true); // Necessário para mostrar a cor de fundo
            tablePanel.add(headerLabel);
        }

        // Adiciona campos de entrada para as linhas
        for (int i = 0; i < 3; i++) { // 3 linhas para Nome, Bônus e Dano/Tipo
            JTextField nameField = new JTextField();
            JTextField bonusField = new JTextField();
            JTextField damageField = new JTextField();
            tablePanel.add(nameField);
            tablePanel.add(bonusField);
            tablePanel.add(damageField);
        }

        // Painel para o espaço de anotações
        JPanel notesPanel = new JPanel(new BorderLayout());
        JTextArea notesArea = new JTextArea(5, 20); // Área de texto para anotações
        notesArea.setLineWrap(true); // Quebra de linha automática
        notesArea.setWrapStyleWord(true); // Quebra em palavras completas
        notesArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda preta
        //notesArea.setBackground(new Color(235, 245, 255)); // Azul claro
        JLabel notesLabel = new JLabel("Notas adicionais sobre ataques e magias", JLabel.CENTER);
        notesLabel.setFont(new Font("Serif", Font.ITALIC, 12));

        notesPanel.add(notesLabel, BorderLayout.NORTH);
        notesPanel.add(notesArea, BorderLayout.CENTER);

        // Adiciona a tabela e o espaço de anotações ao painel principal
        attacksPanel.add(tablePanel, BorderLayout.NORTH);
        attacksPanel.add(notesPanel, BorderLayout.CENTER);

        return attacksPanel;
    }

    private JPanel createMoneyAndEquipmentPanel() {
        // Painel principal
        JPanel moneyEquipmentPanel = new JPanel(new BorderLayout());
        moneyEquipmentPanel.setBorder(BorderFactory.createTitledBorder("Dinheiro e Equipamentos"));
        //moneyEquipmentPanel.setBackground(new Color(244, 237, 215)); // Fundo similar ao pergaminho

        // Painel lateral para a seção de Dinheiro
        JPanel moneyPanel = new JPanel(new GridBagLayout());
        //moneyPanel.setBackground(new Color(244, 237, 215));

        // Configuração do GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Margens entre os componentes
        gbc.anchor = GridBagConstraints.CENTER;

        // Adiciona os rótulos e campos para cada moeda
        String[] moneyLabels = {"PC", "PP", "PE", "PO", "PL"};
        for (int i = 0; i < moneyLabels.length; i++) {
            // Rótulo de cada moeda
            JLabel moneyLabel = new JLabel(moneyLabels[i] + ":", JLabel.CENTER);
            moneyLabel.setFont(new Font("Serif", Font.BOLD, 12));
            moneyLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para separar

            gbc.gridx = 0; // Coluna 0
            gbc.gridy = i; // Linha correspondente
            moneyPanel.add(moneyLabel, gbc);

            // Campo de texto para cada moeda
            JTextField moneyField = new JTextField(6); // Define a largura inicial
            moneyField.setHorizontalAlignment(JTextField.CENTER);
            moneyField.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda
            gbc.gridx = 1; // Coluna 1
            gbc.gridy = i; // Linha correspondente
            moneyPanel.add(moneyField, gbc);
        }

        // Painel para Equipamentos
        JPanel equipmentPanel = new JPanel(new BorderLayout());
        JTextArea equipmentArea = new JTextArea(12, 30); // Área para equipamentos
        equipmentArea.setLineWrap(true); // Quebra de linha automática
        equipmentArea.setWrapStyleWord(true); // Quebra em palavras completas
        equipmentArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda
        //equipmentArea.setBackground(new Color(235, 245, 255)); // Fundo azul claro
        JLabel equipmentLabel = new JLabel("Equipamentos", JLabel.CENTER);
        equipmentLabel.setFont(new Font("Serif", Font.BOLD, 12));

        equipmentPanel.add(equipmentLabel, BorderLayout.NORTH);
        equipmentPanel.add(new JScrollPane(equipmentArea), BorderLayout.CENTER); // Scroll para texto longo

        // Painel combinado (lado a lado)
        JPanel combinedPanel = new JPanel(new BorderLayout());
        combinedPanel.add(moneyPanel, BorderLayout.WEST); // Dinheiro à esquerda
        combinedPanel.add(equipmentPanel, BorderLayout.CENTER); // Equipamentos à direita

        // Adiciona o painel combinado ao painel principal
        moneyEquipmentPanel.add(combinedPanel, BorderLayout.CENTER);

        return moneyEquipmentPanel;
    }

    private JPanel createLanguagesAndProficiencyPanel() {
        // Painel principal para a seção de características e habilidades
        JPanel languagesAndProficiencyPanel = new JPanel();
        languagesAndProficiencyPanel.setLayout(new BoxLayout(languagesAndProficiencyPanel, BoxLayout.Y_AXIS)); // Layout vertical
        languagesAndProficiencyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), "",
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));

        // Subpainel para cada seção
        JPanel sectionPanel = new JPanel(new BorderLayout());
        sectionPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Margem

        // Label na parte inferior
        JLabel sectionLabel = new JLabel("Idiomas e Outras Proficiências", JLabel.CENTER);
        sectionLabel.setFont(new Font("Serif", Font.BOLD, 12));
        sectionPanel.add(sectionLabel, BorderLayout.SOUTH);

        // Caixa de texto para escrever
        JTextArea textArea = new JTextArea(10, 20); // 3 linhas de altura
        textArea.setLineWrap(true); // Quebra de linha automática
        textArea.setWrapStyleWord(true); // Quebra de palavra completa
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Borda para o JTextArea
        sectionPanel.add(textArea, BorderLayout.CENTER);

        // Adiciona a seção ao painel principal
        languagesAndProficiencyPanel.add(sectionPanel);

        return languagesAndProficiencyPanel;
    }

    private JPanel stylePanel(String panelTitle){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(82, 56, 41)), panelTitle,
                TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 16), new Color(82, 56, 41)));
        return panel;
    }

    private Map<String, JTextComponent> fieldsMap = new HashMap<>();

    private Document coletarDados() {
        Document ficha = new Document();
        //ficha.append("nome", nomeField.getText());
        //ficha.append("idade", Integer.parseInt(idadeField.getText())); // Exemplo para campos numéricos
        //ficha.append("habilidades", habilidadesArea.getText());
        // Adicione outros campos conforme necessário
        return ficha;
    }

    private void salvarFicha() {
        try {
            Document ficha = coletarDados();
            DAO dao = new DAO();
            dao.abrirBanco();
            dao.inserir("fichas", ficha); // "fichas" é o nome da coleção
            dao.fecharBanco();
            JOptionPane.showMessageDialog(this, "Ficha salva com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar ficha: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Ficha gui = new Ficha();
            gui.setVisible(true);
        });
    }
}
