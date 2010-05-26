package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Botao com imagem de fundo
 * @author Cassio Lemos
 *
 */
public class MergedButton extends JPanel {

    private Image imgBkg;
    private JButton btn;

    public MergedButton(JButton btn, Icon ico, String label, ActionListener al, String srcImg) {
        super();

        this.setMaximumSize(btn.getMaximumSize());
        this.setMinimumSize(btn.getMinimumSize());
        this.setPreferredSize(btn.getPreferredSize());

        this.imgBkg = new javax.swing.ImageIcon(srcImg).getImage();

        btn.applyComponentOrientation(getComponentOrientation());
        btn.setIcon(ico);
        btn.setFocusPainted(false);
        btn.setFocusable(false);
        btn.setRequestFocusEnabled(false);
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setContentAreaFilled(false);//obrigatorio - fundo transparente
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);
        //btn.setMargin(new Insets(1, 1, 20, 50));
        btn.setForeground(Color.WHITE);
        //btn.setBackground(new Color(7, 41, 89));
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setText(label);
        btn.addActionListener(al);

        this.btn = btn;

        this.setLayout(new BorderLayout());
        this.add(btn, BorderLayout.CENTER);
    }

    public JButton getButton() {
        return this.btn;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imgBkg != null) {
            g.drawImage(imgBkg, 0, 0, this);
        }
    }
}


