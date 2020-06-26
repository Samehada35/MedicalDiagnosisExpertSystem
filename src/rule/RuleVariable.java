package rule;

 
import java.util.*;
import java.awt.*;
import javax.swing.*;


public class RuleVariable extends Variable {
  protected BooleanRuleBase rb;
  protected Vector clauseRefs; 
  protected String promptText;
  protected String ruleName;


  public RuleVariable(BooleanRuleBase rb, String name) {
    super(name);
    this.rb = rb;
    rb.addVariable(this);
    clauseRefs = new Vector();
  }


  public void setValue(String value) {
    this.value = value;
    updateClauses();
  }


  public String askUser() {
    String answer = null;

    // position dialog over parent dialog
    JFrame frame = new JFrame();
    RuleVarDialog dlg = new RuleVarDialog(frame, "Ask User for Value", true);

    dlg.setLocation(200, 200);
    dlg.setData(this);
    dlg.show();
    answer = dlg.getData();
    setValue(answer);
    return value;
  }


  public void addClauseRef(Clause ref) {
    clauseRefs.addElement(ref);
  }

  public void updateClauses() {
    Enumeration enu = clauseRefs.elements();

    while (enu.hasMoreElements()) {
      ((Clause) enu.nextElement()).check();
    }
  }


  public void setRuleName(String ruleName) {
    this.ruleName = ruleName;
  }


  public void setPromptText(String prompText) {
    this.promptText = prompText;
  }


  public String getPromptText() {
    return promptText;
  }


  public void computeStatistics(String inValue) {}
  ;


  public int normalize(String inValue, float[] outArray, int inx) {
    return inx;
  }
}

