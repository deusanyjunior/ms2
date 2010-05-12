/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rca.dept.swing.utils;

import javax.swing.UIManager;

/**
 *
 * @author rennan
 */
public class SwingPTBR {

    public static void translate(){
        translateOptionPane();
    }

    private static void translateOptionPane(){
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

    }

}
