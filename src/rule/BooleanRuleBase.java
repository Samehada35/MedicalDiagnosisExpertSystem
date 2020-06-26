package rule;

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

import javafx.scene.text.Text;


public class BooleanRuleBase implements RuleBase {
  String name;
  Hashtable variableList = new Hashtable();
  Clause clauseVarList[];
  Vector ruleList = new Vector(); 
  Vector conclusionVarList;
  Rule rulePtr;
  Clause clausePtr;
  Stack goalClauseStack = new Stack(); 
  Hashtable effectors;
  Hashtable sensors;
  JTextArea textArea1;

  
  public void setDisplay(JTextArea txtArea) {
    textArea1 = txtArea;
  }


  public BooleanRuleBase(String name) {
    this.name = name;
  }

  public void trace(String text) {
    if (textArea1 != null) {
      textArea1.append(text);
    }
  }

  public void displayVariables(JTextArea textArea) {
    Enumeration enu = variableList.elements();

    while (enu.hasMoreElements()) {
      RuleVariable temp = (RuleVariable) enu.nextElement();

      textArea.append("\n" + temp.name + " value = " + temp.value);
    }
  }

  public void displayRules(Text text) {
	  text.setText("");
    Enumeration enu = ruleList.elements();

    while (enu.hasMoreElements()) {
      Rule temp = (Rule) enu.nextElement();

      temp.display(text);
    }

  }


  public void displayConflictSet(Vector ruleSet) {
    trace("\n" + " -- Rules in conflict set:\n");
    Enumeration enu = ruleSet.elements();

    while (enu.hasMoreElements()) {
      Rule temp = (Rule) enu.nextElement();

      trace(temp.name + "(" + temp.numAntecedents() + "), ");
    }
  }


  public void reset() {
    trace("\n --- Setting all " + name + " variables to null");
    Enumeration enu = variableList.elements();

    while (enu.hasMoreElements()) {
      RuleVariable temp = (RuleVariable) enu.nextElement();

      temp.setValue(null);
    }

    Enumeration enum2 = ruleList.elements();

    while (enum2.hasMoreElements()) {
      Rule temp = (Rule) enum2.nextElement();

      temp.reset();  // set fired flag to false
    }
  }


  public void backwardChain(String goalVarName) {
    RuleVariable goalVar = (RuleVariable) variableList.get(goalVarName);
    Enumeration goalClauses = goalVar.clauseRefs.elements();

    while (goalClauses.hasMoreElements()) {
      Clause goalClause = (Clause) goalClauses.nextElement();

      if (goalClause.consequent == false) {
        continue;
      }
      goalClauseStack.push(goalClause);
      Rule goalRule = goalClause.getRule();
      Boolean ruleTruth = goalRule.backChain();  // find rule truth value

      if (ruleTruth == null) {
        trace("\nRule " + goalRule.name + " is null, can't determine truth value.");
      } else if (ruleTruth.booleanValue() == true) {

        // rule is OK, assign consequent value to variable
        goalVar.setValue(goalClause.rhs);
        goalVar.setRuleName(goalRule.name);
        goalClauseStack.pop();                   // clear item from subgoal stack
        trace("\nRule " + goalRule.name + " is true, setting " + goalVar.name + ": = " + goalVar.value);
        if (goalClauseStack.empty() == true) {
          trace("\n +++ Found Solution for goal: " + goalVar.name);
          break;                                 // for now, only find first solution, then stop
        }
      } else {
        goalClauseStack.pop();                   // clear item from subgoal stack
        trace("\nRule " + goalRule.name + " is false, can't set " + goalVar.name);
      }
    }                                            // endwhile
    if (goalVar.value == null) {
      trace("\n +++ Could Not Find Solution for goal: " + goalVar.name);
    }
  }

  public Vector match(boolean test) {
    Vector matchList = new Vector();
    Enumeration enu = ruleList.elements();

    while (enu.hasMoreElements()) {
      Rule testRule = (Rule) enu.nextElement();

      if (test) {
        testRule.check();  // test the rule antecedents
      }
      if (testRule.truth == null) {
        continue;
      }

      if ((testRule.truth.booleanValue() == true) && (testRule.fired == false)) {
        matchList.addElement(testRule);
      }
    }
    displayConflictSet(matchList);
    return matchList;
  }

  public Rule selectRule(Vector ruleSet) {
    Enumeration enu = ruleSet.elements();
    long numClauses;
    Rule nextRule;
    Rule bestRule = (Rule) enu.nextElement();
    long max = bestRule.numAntecedents();

    while (enu.hasMoreElements()) {
      nextRule = (Rule) enu.nextElement();
      if ((numClauses = nextRule.numAntecedents()) > max) {
        max = numClauses;
        bestRule = nextRule;
      }
    }
    return bestRule;
  }


  public void forwardChain() {
    Vector conflictRuleSet = new Vector();

    conflictRuleSet = match(true);
    while (conflictRuleSet.size() > 0) {
      Rule selected = selectRule(conflictRuleSet); 
      

      selected.fire();
      conflictRuleSet = match(false);
    }
  }


  public void addEffector(Object obj, String effectorName) {
    if (effectors == null) {
      effectors = new Hashtable();
    }
    effectors.put(effectorName, obj);
  }


  public Object getEffectorObject(String effectorName) {
    return effectors.get(effectorName);
  }


  public void addSensor(Object obj, String sensorName) {
    if (sensors == null) {
      sensors = new Hashtable();
    }
    sensors.put(sensorName, obj);
  }


  public Object getSensorObject(String sensorName) {
    return sensors.get(sensorName);
  }


  public void addVariable(RuleVariable variable) {
    variableList.put(variable.getName(), variable);
  }


  public Hashtable getVariables() {
    return (Hashtable) variableList.clone();
  }


  public Vector getGoalVariables() {
    Vector goalVars = new Vector();
    Enumeration enu = variableList.elements();

    while (enu.hasMoreElements()) {
      RuleVariable ruleVar = (RuleVariable) enu.nextElement();
      Vector goalClauses = ruleVar.clauseRefs;

      if ((goalClauses != null) && (goalClauses.size() != 0)) {
        goalVars.addElement(ruleVar);
      }
    }
    return goalVars;
  }


  public RuleVariable getVariable(String name) {
    if (variableList.containsKey(name)) {
      return (RuleVariable) variableList.get(name);
    }
    return null;
  }


  public void setVariableValue(String name, String value) {
    RuleVariable variable = getVariable(name);

    if (variable != null) {
      variable.setValue(value);
    } else {
      System.out.println("BooleanRuleBase: Can't set value, variable " + name + " is not defined!");
    }
  }
}
