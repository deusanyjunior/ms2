/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JPanelNovoCadastro.java
 *
 * Created on 17/03/2010, 20:52:01
 */

package rca.dept.gui.swing;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jvnet.substance.SubstanceLookAndFeel;


/**
 *
 * @author rennan
 */
public class JPanelFormulario extends javax.swing.JPanel {

    Vector<String> list;
    DefaultComboBoxModel model;
    String funcionario = "Funcionário";
    String start = "000000", black = "FFFFFF", red = "FF0000";
    private String cpfMask = "###.###.###-##", cepMask = "#####-###",
            nascMask = "##/##/####", phoneMask = "(##)####-####", rgMask = "#########";



    /** Creates new form JPanelNovoCadastro */
    public JPanelFormulario() {
        initComponents();
        list = new Vector<String>();
        list.add("Aluno");
        list.add("Professor");
        list.add(funcionario);
        model = new DefaultComboBoxModel(list);
        jComboBoxFuncao.setModel(model);
        
    }

        /**
     * Ajeita um textField de acordo com a mascara.
     * <p>Uma mascara deve conter # (que representa numeros) intercalados por no maximo um outro char.</p>
     * <p>Ex.: "###.###.###-##" e "(##)####-####"
     * @param toAdjust JTextField a ser ajustado
     * @param mask Mascara da forma descrita acima
     */
    public void adjustUnderMask(JTextField toAdjust, String mask){
        String text = toAdjust.getText();
        StringBuilder builder = new StringBuilder();
        int k = 0;
        for (int i = 0; i < text.length() && k < mask.length(); i++) {
            char t = text.charAt(i);
            if(Character.isDigit(t)){
                char m = mask.charAt(k++);
                while(m != '#' && k < mask.length()){
                    builder.append(m);
                    m = mask.charAt(k++);
                }
                if(m == '#')
                    builder.append(t);
            }
        }
        text = builder.toString();
        if(!toAdjust.getText().equals(text))
            toAdjust.setText(text);
    }

    public void check(){
        boolean everythingOk = true;
        
        everythingOk &= checkOnlyLetters(jTextFieldNome, jLabelNome, 5);
        everythingOk &= checkSize(jTextFieldCPF, jLabelCPF, cpfMask.length(), false);
        everythingOk &= checkMinimumSize(jTextFieldRG, jLabelRG, 6);
        everythingOk &= checkMinimumSize(jTextFieldOrgao, jLabelOrgao, 2);
        everythingOk &= checkMinimumSize(jTextFieldEndereco, jLabelEndereco, 5);
        everythingOk &= checkMinimumSize(jTextFieldCidade, jLabelCidade, 5);
        everythingOk &= checkMinimumSize(jTextFieldBairro, jLabelBairro, 2);
        everythingOk &= checkSize(jTextFieldCEP, jLabelCEP, cepMask.length(), false);
        everythingOk &= checkSize(jTextFieldNascimento, jLabelNascimento, nascMask.length(), false);
        everythingOk &= checkSize(jTextFieldTelefone, jLabelTelefone, phoneMask.length(), true);
        everythingOk &= checkSize(jTextFieldCelular, jLabelCelular, phoneMask.length(), true);
        if(everythingOk){
            jLabelAviso.setText("");
            jButtonRegistrar.setEnabled(true);
        } else {
            String text = "<html><div color=\"#" + red +
                    "\">ATENÇÃO: Os campos em vermelho estão incompletos ou com erros</div></html>";
            jLabelAviso.setText(text);
            jButtonRegistrar.setEnabled(false);
        }
        
    }

    private boolean checkOnlyLetters(JTextField toCheck, JLabel toChange, int minimum){
        int cont = 0;
        for(char c : toCheck.getText().toCharArray()){
            if(Character.isLetter(c)){
                cont++;
            } else if(c != ' ') {
                cont -= toCheck.getText().length();
            }
        }
        boolean underRestraint = (cont >= minimum);
        applyCorrespondentColor(toChange, underRestraint);
        return underRestraint;
    }

    private boolean checkSize(JTextField toCheck, JLabel toChange, int size, boolean acceptBlank){
        boolean underRestraint = (toCheck.getText().length() == size);
        underRestraint |= (acceptBlank && toCheck.getText().length() == 0);
        applyCorrespondentColor(toChange, underRestraint);
        return underRestraint;
    }

    private boolean checkMinimumSize(JTextField toCheck, JLabel toChange, int size){
        boolean underRestraint = (toCheck.getText().length() >= size);
        applyCorrespondentColor(toChange, underRestraint);
        return underRestraint;
    }

    private void applyCorrespondentColor(JLabel toChange, boolean underRestraint) {
        if(underRestraint) {
            String text = toChange.getText();
            if(text.length() > 38){
                toChange.setText(text.substring(27, text.length()-12));
            }
        } else {
            String html = "<html><div color=\"#%s\">%s</div><html>";
            String text = toChange.getText();
            if(text.length() < 41){
                toChange.setText(String.format(html, red, text));
                
            }
        }
    }

    private void focusableFields(boolean value){
        jTextFieldCPF.setFocusable(value);
        jTextFieldRG.setFocusable(value);
        jTextFieldOrgao.setFocusable(value);
        jTextFieldEndereco.setFocusable(value);
        jTextFieldCidade.setFocusable(value);
        jComboBox1.setFocusable(value);
        jTextFieldBairro.setFocusable(value);
        jTextFieldCEP.setFocusable(value);
        jTextFieldNascimento.setFocusable(value);
        jTextFieldTelefone.setFocusable(value);
        jTextFieldCelular.setFocusable(value);
        jComboBoxFuncao.setFocusable(value);
    }
    
    private JLabel getFiller(int w, int h){
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(w, h));
        return label;
    }


    public void setPrivilegy(JPanelLogin.Privilegy privilegy){
        if(privilegy == JPanelLogin.Privilegy.FUNCIONARIO) {
            list.remove(funcionario);
        }
        else if(privilegy == JPanelLogin.Privilegy.FUNCIONARIO) {
            if(!list.contains(funcionario))
                list.add(funcionario);
        }
    }

    @Override
    public void setVisible(boolean b) {
        check();
        focusableFields(false);
        super.setVisible(b);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelNome = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabelEndereco = new javax.swing.JLabel();
        jTextFieldEndereco = new javax.swing.JTextField();
        jLabelCidade = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabelBairro = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jTextFieldCEP = new javax.swing.JTextField();
        jLabelCEP = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        jLabelOrgao = new javax.swing.JLabel();
        jTextFieldCPF = new javax.swing.JTextField();
        jLabelRG = new javax.swing.JLabel();
        jTextFieldRG = new javax.swing.JTextField();
        jTextFieldOrgao = new javax.swing.JTextField();
        jLabelNascimento = new javax.swing.JLabel();
        jLabelCelular = new javax.swing.JLabel();
        jTextFieldNascimento = new javax.swing.JTextField();
        jLabelTelefone = new javax.swing.JLabel();
        jTextFieldTelefone = new javax.swing.JTextField();
        jTextFieldCelular = new javax.swing.JTextField();
        jLabelFuncao = new javax.swing.JLabel();
        jComboBoxFuncao = new javax.swing.JComboBox();
        jButtonRegistrar = new javax.swing.JButton();
        jLabelAviso = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBoxThemes = new javax.swing.JComboBox();
        jLabelFuncao1 = new javax.swing.JLabel();

        jLabelNome.setText("Nome");

        jTextFieldNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jLabelEndereco.setText("Endereço");

        jTextFieldEndereco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jLabelCidade.setText("Cidade");

        jTextFieldCidade.setText("João Pessoa");
        jTextFieldCidade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jLabelBairro.setHorizontalAlignment(JLabel.RIGHT);
        jLabelBairro.setText("Bairro");

        jTextFieldBairro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jTextFieldCEP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCEPKeyReleased(evt);
            }
        });

        jLabelCEP.setHorizontalAlignment(JLabel.RIGHT);
        jLabelCEP.setText("CEP");

        jLabelCPF.setText("CPF");

        jLabelOrgao.setHorizontalAlignment(JLabel.RIGHT);
        jLabelOrgao.setText("Orgão");

        jTextFieldCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCPFKeyReleased(evt);
            }
        });

        jLabelRG.setHorizontalAlignment(JLabel.RIGHT);
        jLabelRG.setText("RG");

        jTextFieldRG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldRG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldRGKeyReleased(evt);
            }
        });

        jTextFieldOrgao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jLabelNascimento.setText("Dt. Nasc.");

        jLabelCelular.setHorizontalAlignment(JLabel.RIGHT);
        jLabelCelular.setText("Celular");

        jTextFieldNascimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldNascimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNascimentoKeyReleased(evt);
            }
        });

        jLabelTelefone.setHorizontalAlignment(JLabel.RIGHT);
        jLabelTelefone.setText("Telefone");

        jTextFieldTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTelefoneKeyReleased(evt);
            }
        });

        jTextFieldCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });
        jTextFieldCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCelularKeyReleased(evt);
            }
        });

        jLabelFuncao.setText("Função");

        jComboBoxFuncao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aluno" }));
        jComboBoxFuncao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                someFieldFocusLost(evt);
            }
        });

        jButtonRegistrar.setText("Registrar");
        jButtonRegistrar.setEnabled(false);
        jButtonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarActionPerformed(evt);
            }
        });

        jLabelAviso.setHorizontalAlignment(JLabel.RIGHT);
        jLabelAviso.setText("<html><div color=\"#FF0000\">Os campos em vermelho estão incompletos ou inválidos</div><html>");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RR", "RO", "RJ", "RN", "RS", "SC", "SP", "SE", "TO" }));
        jComboBox1.setSelectedItem("PB");

        jComboBoxThemes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Aluno" }));
        String[] options = SubstanceLookAndFeel.getAllSkins().keySet().toArray(new String[0]);
        jComboBoxThemes.setModel(new DefaultComboBoxModel(options));
        jComboBoxThemes.setSelectedIndex(1);
        jComboBoxThemes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxThemesActionPerformed(evt);
            }
        });

        jLabelFuncao1.setText("Tema");

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jLabelFuncao1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelNome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelEndereco, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelNascimento, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelCPF, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .add(jLabelCidade, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .add(jLabelFuncao, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .add(5, 5, 5)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(jComboBoxFuncao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelAviso, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE))
                    .add(jTextFieldNome, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jTextFieldCPF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 121, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(jLabelRG, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(5, 5, 5)
                        .add(jTextFieldRG, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelOrgao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(1, 1, 1)
                        .add(jTextFieldOrgao, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .add(jTextFieldEndereco, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .add(layout.createSequentialGroup()
                        .add(jTextFieldCidade, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                        .add(10, 10, 10)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 64, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(15, 15, 15)
                        .add(jLabelBairro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(5, 5, 5)
                        .add(jTextFieldBairro, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                        .add(15, 15, 15)
                        .add(jLabelCEP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(5, 5, 5)
                        .add(jTextFieldCEP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jComboBoxThemes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 310, Short.MAX_VALUE)
                        .add(jButtonRegistrar))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jTextFieldNascimento, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .add(15, 15, 15)
                        .add(jLabelTelefone)
                        .add(5, 5, 5)
                        .add(jTextFieldTelefone, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .add(15, 15, 15)
                        .add(jLabelCelular)
                        .add(5, 5, 5)
                        .add(jTextFieldCelular, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(31, 31, 31)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldNome, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelNome))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelCPF)
                    .add(jTextFieldCPF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldOrgao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelRG)
                    .add(jTextFieldRG, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelOrgao))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jTextFieldEndereco, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelEndereco))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE, false)
                    .add(jLabelCidade)
                    .add(jTextFieldCidade, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldBairro, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelBairro)
                    .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldCEP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelCEP))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelNascimento)
                    .add(jTextFieldNascimento, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldCelular, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelCelular)
                    .add(jTextFieldTelefone, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelTelefone))
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.CENTER)
                    .add(jComboBoxFuncao, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelFuncao)
                    .add(jLabelAviso, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonRegistrar)
                    .add(jComboBoxThemes, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelFuncao1)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarActionPerformed
        String[] options = new String[]{
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico",
            "Violao Classico","Violao Classico","Violao Classico","Violao Classico","Violao Classico"
        };
        Object str = JOptionPane.showInputDialog(jButtonRegistrar, "Selecione o instrumento do aluno:", "Instrumento",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }//GEN-LAST:event_jButtonRegistrarActionPerformed

    private void someFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_someFieldFocusLost
        check();
    }//GEN-LAST:event_someFieldFocusLost

    private void jTextFieldCPFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCPFKeyReleased
        adjustUnderMask(jTextFieldCPF, cpfMask);
    }//GEN-LAST:event_jTextFieldCPFKeyReleased

    private void jTextFieldCEPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCEPKeyReleased
        adjustUnderMask(jTextFieldCEP, cepMask);
    }//GEN-LAST:event_jTextFieldCEPKeyReleased

    private void jTextFieldNascimentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNascimentoKeyReleased
        adjustUnderMask(jTextFieldNascimento, nascMask);
    }//GEN-LAST:event_jTextFieldNascimentoKeyReleased

    private void jTextFieldTelefoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefoneKeyReleased
        adjustUnderMask(jTextFieldTelefone, phoneMask);
    }//GEN-LAST:event_jTextFieldTelefoneKeyReleased

    private void jTextFieldCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCelularKeyReleased
        adjustUnderMask(jTextFieldCelular, phoneMask);
    }//GEN-LAST:event_jTextFieldCelularKeyReleased

    private void jTextFieldNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNomeFocusGained
        focusableFields(true);
    }//GEN-LAST:event_jTextFieldNomeFocusGained

    private void jComboBoxThemesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxThemesActionPerformed
        System.out.println("here");
        String str = (String)jComboBoxThemes.getSelectedItem();
        SubstanceLookAndFeel.setSkin(SubstanceLookAndFeel.getAllSkins().get(str).getClassName());
    }//GEN-LAST:event_jComboBoxThemesActionPerformed

    private void jTextFieldRGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRGKeyReleased
        adjustUnderMask(jTextFieldRG, rgMask);
    }//GEN-LAST:event_jTextFieldRGKeyReleased



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRegistrar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBoxFuncao;
    private javax.swing.JComboBox jComboBoxThemes;
    private javax.swing.JLabel jLabelAviso;
    private javax.swing.JLabel jLabelBairro;
    private javax.swing.JLabel jLabelCEP;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelCelular;
    private javax.swing.JLabel jLabelCidade;
    private javax.swing.JLabel jLabelEndereco;
    private javax.swing.JLabel jLabelFuncao;
    private javax.swing.JLabel jLabelFuncao1;
    private javax.swing.JLabel jLabelNascimento;
    private javax.swing.JLabel jLabelNome;
    private javax.swing.JLabel jLabelOrgao;
    private javax.swing.JLabel jLabelRG;
    private javax.swing.JLabel jLabelTelefone;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCEP;
    private javax.swing.JTextField jTextFieldCPF;
    private javax.swing.JTextField jTextFieldCelular;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldEndereco;
    private javax.swing.JTextField jTextFieldNascimento;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldOrgao;
    private javax.swing.JTextField jTextFieldRG;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables

}
