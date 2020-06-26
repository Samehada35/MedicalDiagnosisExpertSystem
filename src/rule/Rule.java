package rule;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

import javafx.scene.text.Text;

public class Rule {
  BooleanRuleBase rb;
  String name;
  Clause antecedents[];
  Clause consequent; 
  Boolean truth;
  boolean fired = false;


  public Rule(BooleanRuleBase rb, String name, Clause lhs, Clause rhs) {
    this.rb = rb;
    this.name = name;
    antecedents = new Clause[1];
    antecedents[0] = lhs;
    lhs.addRuleRef(this);
    consequent = rhs;
    rhs.addRuleRef(this);
    rhs.setConsequent();
    rb.ruleList.addElement(this);
    truth = null;
  }


  public Rule(BooleanRuleBase rb, String name, Clause[] lhsClauses, Clause rhs) {
    this.rb = rb;
    this.name = name;
    antecedents = new Clause[lhsClauses.length];
    for (int i = 0; i < lhsClauses.length; i++) {
      antecedents[i] = lhsClauses[i];
      antecedents[i].addRuleRef(this);
    }
    consequent = rhs;
    rhs.addRuleRef(this);
    rhs.setConsequent();
    rb.ruleList.addElement(this);
    truth = null;
  }


  long numAntecedents() {
    return antecedents.length;
  }


  public static void checkRules(Vector clauseRefs) {
    Enumeration enu = clauseRefs.elements();

    while (enu.hasMoreElements()) {
      Clause temp = (Clause) enu.nextElement();
      Enumeration enum2 = temp.ruleRefs.elements();

      while (enum2.hasMoreElements()) {
        ((Rule) enum2.nextElement()).check(); 
      }
    }
  }


  Boolean check() {
    rb.trace("\nTesting rule " + name);
    for (int i = 0; i < antecedents.length; i++) {
      if (antecedents[i].truth == null) {
        return truth = null;
      }
      if (antecedents[i].truth.booleanValue() == true) {
        continue;
      } else {
        return truth = new Boolean(false);
      }
    } 
    return truth = new Boolean(true);
  }


  void fire() {
    rb.trace("\nFiring rule " + name);
    truth = new Boolean(true);
    fired = true;

      consequent.lhs.setValue(consequent.rhs);
      checkRules(consequent.lhs.clauseRefs);

  }


  Boolean backChain() {
    rb.trace("\nEvaluating rule " + name);
    for (int i = 0; i < antecedents.length; i++) { 
      if (antecedents[i].truth == null) {
        rb.backwardChain(antecedents[i].lhs.name);
      }
      if (antecedents[i].truth == null) {
        antecedents[i].lhs.askUser();
        truth = antecedents[i].check();
      }
      if (antecedents[i].truth.booleanValue() == true) {
        continue;
      } else {
        return truth = new Boolean(false);
      }
    }
    return truth = new Boolean(true);
  }


  void display(Text text) {
	  text.setText(text.getText()+name + ": IF ");
    for (int i = 0; i < antecedents.length; i++) {
      Clause nextClause = antecedents[i];

      text.setText(text.getText()+nextClause.toString());
      if ((i + 1) < antecedents.length) {
    	  text.setText(text.getText()+"\n     AND ");
      }
    }
    text.setText(text.getText()+"\n     THEN ");
    text.setText(text.getText()+consequent.toString() + "\n\n");
  }

  void reset() {
    fired = false;
  }
}
