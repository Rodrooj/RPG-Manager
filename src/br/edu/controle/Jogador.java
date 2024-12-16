package br.edu.controle;

import java.util.HashMap;
import java.util.Map;

public class Jogador {
    // Informações básicas
    private String nomePersonagem;
    private String Classe;
    private int nivel;
    private String raca;
    private String antecedente;
    private String alinhamento;
    private String nomeJogador;
    private int xp;

    // Atributos
    private int forca;
    private int destreza;
    private int constituicao;
    private int inteligencia;
    private int sabedoria;
    private int carisma;

    // Modificadores de atributo
    private int modForca;
    private int modDestreza;
    private int modConstituicao;
    private int modInteligencia;
    private int modSabedoria;
    private int modCarisma;

    private String idiomasEOutros;

    // Informações extras
    private int inspiracao;
    private int proficiencia;
    private int sabedoriaPassiva;

    // Testes de resistência
    private Map<String, Boolean> resistencias;
    private Map<String, Integer> bonus;

    public void ControleResistencias() {
        // Inicializando Mapas
        resistencias = new HashMap<>();
        bonus = new HashMap<>();

        // Preencher as perícias com valores padrão (inicialmente sem proficiência)
        String[] nomesPericias = {
                "Força", "Destreza", "Constituição", "Inteligencia", "Sabedoria", "Carisma"
        };
        for (String resistencia : nomesPericias) {
            resistencias.put(resistencia, false); // Inicialmente sem proficiência
            bonus.put(resistencia, 0);         // Inicialmente com valor 0
        }
    }

    // Pericias
    private Map<String, Boolean> proficiencias;
    private Map<String, Integer> pericias;

    public void ControlePericias() {
        // Inicializando Mapas
        proficiencias = new HashMap<>();
        pericias = new HashMap<>();

        // Preencher as perícias com valores padrão (inicialmente sem proficiência)
        String[] nomesPericias = {
                "Acrobacia", "Arcanismo", "Atletismo", "Enganação", "História",
                "Intuição", "Intimidação", "Investigação", "Medicina", "Natureza",
                "Percepção", "Performance", "Persuasão", "Prestidigitação",
                "Religião", "Sobrevivência"
        };
        for (String pericia : nomesPericias) {
            proficiencias.put(pericia, false); // Inicialmente sem proficiência
            pericias.put(pericia, 0);         // Inicialmente com valor 0
        }
    }

    // Atributos de combate
    private int classeArmadura;
    private int iniciativa;
    private int deslocamento;
    private int PVTotais;
    private int PVAtuais;
    private int PVTemporarios;
    private String dadoDeVida;
    private String[][] armas = {
            {"Arma 1", "Bonus 1", "Dano 1"},
            {"Arma 2", "Bonus 2", "Dano 2"},
            {"Arma 3", "Bonus 3", "Dano 3"}
    };

    // Atributos de dinheiro e equipamento
    private int pc;
    private int pp;
    private int pe;
    private int po;
    private int pl;
    private String equipamento;

    // Atributos de personalidade
    private String[] personalidades = {
            "Personalidade 1", "Personalidade 2"
    };
    private String ideal;
    private String ligacao;
    private String defeito;

    // Características e habilidades/Anotações
    private String texto;

    // Getters e Setters
    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getClasse() {
        return Classe;
    }

    public void setClasse(String classe) {
        Classe = classe;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        this.antecedente = antecedente;
    }

    public String getAlinhamento() {
        return alinhamento;
    }

    public void setAlinhamento(String alinhamento) {
        this.alinhamento = alinhamento;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getConstituicao() {
        return constituicao;
    }

    public void setConstituicao(int constituicao) {
        this.constituicao = constituicao;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getSabedoria() {
        return sabedoria;
    }

    public void setSabedoria(int sabedoria) {
        this.sabedoria = sabedoria;
    }

    public int getCarisma() {
        return carisma;
    }

    public void setCarisma(int carisma) {
        this.carisma = carisma;
    }

    public int getModForca() {
        return modForca;
    }

    public void setModForca(int modForca) {
        this.modForca = modForca;
    }

    public int getModDestreza() {
        return modDestreza;
    }

    public void setModDestreza(int modDestreza) {
        this.modDestreza = modDestreza;
    }

    public int getModConstituicao() {
        return modConstituicao;
    }

    public void setModConstituicao(int modConstituicao) {
        this.modConstituicao = modConstituicao;
    }

    public int getModInteligencia() {
        return modInteligencia;
    }

    public void setModInteligencia(int modInteligencia) {
        this.modInteligencia = modInteligencia;
    }

    public int getModSabedoria() {
        return modSabedoria;
    }

    public void setModSabedoria(int modSabedoria) {
        this.modSabedoria = modSabedoria;
    }

    public int getModCarisma() {
        return modCarisma;
    }

    public void setModCarisma(int modCarisma) {
        this.modCarisma = modCarisma;
    }

    public String getIdiomasEOutros() {
        return idiomasEOutros;
    }

    public void setIdiomasEOutros(String idiomasEOutros) {
        this.idiomasEOutros = idiomasEOutros;
    }

    public int getInspiracao() {
        return inspiracao;
    }

    public void setInspiracao(int inspiracao) {
        this.inspiracao = inspiracao;
    }

    public int getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(int proficiencia) {
        this.proficiencia = proficiencia;
    }

    public int getSabedoriaPassiva() {
        return sabedoriaPassiva;
    }

    public void setSabedoriaPassiva(int sabedoriaPassiva) {
        this.sabedoriaPassiva = sabedoriaPassiva;
    }

    public Map<String, Boolean> getResistencias() {
        return resistencias;
    }

    public void setResistencias(Map<String, Boolean> resistencias) {
        this.resistencias = resistencias;
    }

    public Map<String, Integer> getBonus() {
        return bonus;
    }

    public void setBonus(Map<String, Integer> bonus) {
        this.bonus = bonus;
    }

    public Map<String, Boolean> getProficiencias() {
        return proficiencias;
    }

    public void setProficiencias(Map<String, Boolean> proficiencias) {
        this.proficiencias = proficiencias;
    }

    public Map<String, Integer> getPericias() {
        return pericias;
    }

    public void setPericias(Map<String, Integer> pericias) {
        this.pericias = pericias;
    }

    public int getClasseArmadura() {
        return classeArmadura;
    }

    public void setClasseArmadura(int classeArmadura) {
        this.classeArmadura = classeArmadura;
    }

    public int getIniciativa() {
        return iniciativa;
    }

    public void setIniciativa(int iniciativa) {
        this.iniciativa = iniciativa;
    }

    public int getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(int deslocamento) {
        this.deslocamento = deslocamento;
    }

    public int getPVTotais() {
        return PVTotais;
    }

    public void setPVTotais(int PVTotais) {
        this.PVTotais = PVTotais;
    }

    public int getPVAtuais() {
        return PVAtuais;
    }

    public void setPVAtuais(int PVAtuais) {
        this.PVAtuais = PVAtuais;
    }

    public int getPVTemporarios() {
        return PVTemporarios;
    }

    public void setPVTemporarios(int PVTemporarios) {
        this.PVTemporarios = PVTemporarios;
    }

    public String getDadoDeVida() {
        return dadoDeVida;
    }

    public void setDadoDeVida(String dadoDeVida) {
        this.dadoDeVida = dadoDeVida;
    }

    public String[][] getArmas() {
        return armas;
    }

    public void setArmas(String[][] armas) {
        this.armas = armas;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public int getPe() {
        return pe;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    public int getPo() {
        return po;
    }

    public void setPo(int po) {
        this.po = po;
    }

    public int getPl() {
        return pl;
    }

    public void setPl(int pl) {
        this.pl = pl;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String[] getPersonalidades() {
        return personalidades;
    }

    public void setPersonalidades(String[] personalidades) {
        this.personalidades = personalidades;
    }

    public String getIdeal() {
        return ideal;
    }

    public void setIdeal(String ideal) {
        this.ideal = ideal;
    }

    public String getLigacao() {
        return ligacao;
    }

    public void setLigacao(String ligacao) {
        this.ligacao = ligacao;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    // Final Getters e Setters

    private void calcularModificadores() {
        this.modForca = (forca - 10) / 2;
        this.modDestreza = (destreza - 10) / 2;
        this.modConstituicao = (constituicao - 10) / 2;
        this.modInteligencia = (inteligencia - 10) / 2;
        this.modSabedoria = (sabedoria - 10) / 2;
        this.modCarisma = (carisma - 10) / 2;
    }
}
