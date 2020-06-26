package medical;

import java.util.Enumeration;
import java.util.Set;

import javax.swing.JTextArea;

import rule.RuleVariable;

public class Test {

	public static void main(String args[]){
		MedicalExpertSystem m = new MedicalExpertSystem();
		
		Enumeration<String> keys = m.getRuleBase().getVariables().keys();
		
        /*while(keys.hasMoreElements()){
        	String key = keys.nextElement();
            System.out.println(key+","+((RuleVariable)m.getRuleBase().getVariables().get(key)));
        }*/
        
        
        m.getRuleBase().setVariableValue("Tuberculose", "true");
        m.getRuleBase().setVariableValue("Frottis de TP", "Positif");
        m.getRuleBase().setVariableValue("Infection concomitante par le VIH", "true");
        

        m.getRuleBase().forwardChain();
        
        System.out.println(m.getRuleBase().getVariable("Catégorie").getValue());
	}
}
