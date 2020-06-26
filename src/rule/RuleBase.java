package rule;

import java.util.*;
import javax.swing.*;

import javafx.scene.text.Text;


public interface RuleBase {

  public void setDisplay(JTextArea txtArea);

  public void trace(String text);

  public void displayVariables(JTextArea textArea);

  public void displayRules(Text text);

  public void reset();

  public void backwardChain(String goalVarName);

  public void forwardChain();

  public Vector getGoalVariables();
}
