package financemanager.gui.panels;
/**
 * @author Thomas Donegan
 * @number R00044989
 * @e-mail thomas.donegan@mycit.ie
 * @version 0.0.1
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import financemanager.*;
import spreadsheet.*;

public class Preferences_JPanel {
    // objects
    Panel_Elements gui = new Panel_Elements(); // to use gui elements
    Strings string = new Strings(); // to use reused information strings
    Spreadsheet cell = new Spreadsheet();

    // variables
    int oddColor;
    int evenColor;
    int highlightedColor;
    String[] colors = string.colors();

    public Preferences_JPanel() {
        gui.preferencesPanel = new JPanel();
        gui.panel = new JPanel();
        init();
    }
    public void init() {
        showOptions();
    }
    public JPanel preferencesPanel() {
        return gui.preferencesPanel;
    }
    public void showOptions() {
        gui.lblTableLines = new JLabel("Table Lines:");
        gui.lblOdds = new JLabel("Odd Lines: ");
        gui.cmbOdds = new JComboBox(colors);
        gui.cmbOdds.setBackground(Color.WHITE);
        gui.cmbOdds.setSelectedIndex(0);
        gui.lblEvens = new JLabel("Even Lines: ");
        gui.cmbEvens = new JComboBox(colors);
        gui.cmbEvens.setBackground(Color.WHITE);
        gui.cmbEvens.setSelectedIndex(1);
        gui.lblHighlighted = new JLabel("Highlighted Lines: ");
        gui.cmbHighlighted = new JComboBox(colors);
        gui.cmbHighlighted.setBackground(Color.WHITE);
        gui.cmbHighlighted.setSelectedIndex(5);
        gui.btnApply = new JButton("Apply");
        gui.btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                apply();
            }
        });
        gui.panel.add(gui.lblTableLines);
        gui.panel.add(gui.lblOdds);
        gui.panel.add(gui.cmbOdds);
        gui.panel.add(gui.lblEvens);
        gui.panel.add(gui.cmbEvens);
        gui.panel.add(gui.lblHighlighted);
        gui.panel.add(gui.cmbHighlighted);
        gui.panel.add(gui.btnApply);
        gui.preferencesPanel.add(gui.panel);
    }
    public void apply() {
        oddColor = gui.cmbOdds.getSelectedIndex();
        evenColor = gui.cmbEvens.getSelectedIndex();
        highlightedColor = gui.cmbHighlighted.getSelectedIndex();
        System.out.println(oddColor + "--" + evenColor + "--" + highlightedColor);
        if((oddColor == evenColor) || (oddColor == highlightedColor) || (evenColor == highlightedColor)) {
            JOptionPane.showMessageDialog(null, "Must use different colors!");
        }
        else {

        }
    }
}