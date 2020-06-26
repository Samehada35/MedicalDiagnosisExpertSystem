package rule;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class RuleVarDialog extends JDialog {
  JPanel panel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JButton OKButton = new JButton();
  JLabel PromptLabel = new JLabel();
  JLabel jLabel2 = new JLabel();
  JComboBox ValueComboBox = new JComboBox();
  RuleVariable ruleVar;


  public RuleVarDialog(Frame frame, String title, boolean modal) {
    super(frame, title, modal);
    try {
      jbInit();
      pack();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }


  public RuleVarDialog() {
    this(null, "", false);
  }


  void jbInit() throws Exception {
    panel1.setLayout(borderLayout1);
    OKButton.setText("OK");
    OKButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        OKButton_actionPerformed(e);
      }
    });
    PromptLabel.setText("What ?");
    PromptLabel.setBounds(new Rectangle(32, 29, 341, 17));
    jPanel2.setLayout(null);
    jLabel2.setText("Value = ");
    jLabel2.setBounds(new Rectangle(36, 79, 67, 17));
    ValueComboBox.setBounds(new Rectangle(104, 79, 226, 24));
    ValueComboBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ValueComboBox_actionPerformed(e);
      }
    });
    panel1.setMinimumSize(new Dimension(400, 200));
    panel1.setPreferredSize(new Dimension(400, 200));
    getContentPane().add(panel1);
    panel1.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(OKButton, null);
    panel1.add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(PromptLabel, null);
    jPanel2.add(jLabel2, null);
    jPanel2.add(ValueComboBox, null);
  }


  void OKButton_actionPerformed(ActionEvent e) {
    dispose();
  }


  void ValueComboBox_actionPerformed(ActionEvent e) {}


  public void setData(RuleVariable ruleVar) {
    this.ruleVar = ruleVar;
    PromptLabel.setText(ruleVar.getPromptText());
    Vector labels = ruleVar.getLabels();

    if (labels != null) {
      for (int i = 0; i < labels.size(); i++) {
        ValueComboBox.addItem((String) labels.elementAt(i));
      }
      ValueComboBox.setEditable(false);
    } else {
      ValueComboBox.setEditable(true);
    }
  }


  public String getData() {
    return (String) ValueComboBox.getSelectedItem();
  }
}
