package medical;

import rule.BooleanRuleBase;
import rule.Clause;
import rule.Condition;
import rule.Rule;
import rule.RuleVariable;

public class MedicalExpertSystem {

	private BooleanRuleBase rb;
	
	private RuleVariable touxVar, fievreVar, amaigrissementVar, anorexieVar, asthenieVar, transpirationVar, adenopathiesCervicalesVar, 
						 tuberculoseVar, rechutesVar, traitementApresInterruptionVar, traitementApresEchecTherapeutiqueVar,
						 categorieVar, frottisVar,VIHVar, 
						 glucosePlasmatiqueVar, diabeteVar,
						 parasitemieVar, hemoglobineVar, comaVar, malariaVar,
						 leucemieVar, hallucinationVar, typhoideVar,
						 hemorragieExterneVar, hemorragieInterneVar, battementCoeurAnormalVar, tensionVar, sensationFroidVar, 
						 dengueTypeVar, fievreOndulanteVar, oedemeVar, splenomegalieVar, ecchymoseVar, lethargieVar,
						 jaunisseVar, hepatomegalieVar, gonflementGanglionsVar, adenopathieVar, brucelloseVar;
	
	private Rule R1, R2, R3, R4, R5, R6, R7, R8, R9, R10, R11, R12, R13, R14, R15, R16, R17, R18, R19;

	public MedicalExpertSystem() {
		rb = new BooleanRuleBase("Base de règles médicale");
		
		initializeVariables();
		initializeRules();
	}
	
	
	public void initializeVariables() {
		/** Tuberculose **/
		touxVar = new RuleVariable(rb,"Toux");
		fievreVar  = new RuleVariable(rb,"Fièvre");
		amaigrissementVar  = new RuleVariable(rb,"Amaigrissement");
		anorexieVar  = new RuleVariable(rb,"Anorexie");
		asthenieVar  = new RuleVariable(rb,"Asthénie");
		transpirationVar  = new RuleVariable(rb,"Transpiration");
		adenopathiesCervicalesVar  = new RuleVariable(rb,"Adénopathies cervicales");
		tuberculoseVar  = new RuleVariable(rb,"Tuberculose");
		frottisVar =  new RuleVariable(rb,"Frottis");
		categorieVar  = new RuleVariable(rb,"Catégorie");
		rechutesVar  = new RuleVariable(rb,"Rechutes");
		traitementApresInterruptionVar  = new RuleVariable(rb,"Traitement après interruption");
		traitementApresEchecTherapeutiqueVar  = new RuleVariable(rb,"Traitement après échec thérapeutique");
		VIHVar = new RuleVariable(rb,"VIH");
		
		touxVar.setLabels("true false");
		fievreVar.setLabels("Faible Moyenne Forte");
		amaigrissementVar.setLabels("true false");
		anorexieVar.setLabels("true false");
		asthenieVar.setLabels("true false");
		transpirationVar.setLabels("Aucune Faible Profuse");
		adenopathiesCervicalesVar.setLabels("true false");
		tuberculoseVar.setLabels("true false");
		frottisVar.setLabels("Positif Negatif");
		categorieVar.setLabels("I II II");
		rechutesVar.setLabels("true false");
		traitementApresInterruptionVar.setLabels("true false");
		traitementApresEchecTherapeutiqueVar.setLabels("true false");
		VIHVar.setLabels("Seropositif Seronegatif");
		
		/** Diabete **/
		
		glucosePlasmatiqueVar = new RuleVariable(rb,"Glucose Plasmatique à jeun");
		diabeteVar = new RuleVariable(rb,"Diabète");
		
		diabeteVar.setLabels("Non-Diabetique Prediabetique Diabetique");
		
		/** Malaria **/
		
		parasitemieVar = new RuleVariable(rb,"Parasitémie");
		hemoglobineVar = new RuleVariable(rb,"Hémoglobine");
		comaVar = new RuleVariable(rb,"Coma");
		malariaVar = new RuleVariable(rb,"Malaria");
		
		parasitemieVar.setLabels("Absente Presente");
		hemoglobineVar.setLabels("Basse Normale");
		comaVar.setLabels("true false");
		malariaVar.setLabels("Peu-Compliquee Severe");
		
		/** Fièvre Typhoide **/
		
		leucemieVar = new RuleVariable(rb,"Leucémie");
		hallucinationVar = new RuleVariable(rb,"Hallucinations");
		typhoideVar = new RuleVariable(rb,"Fièvre Typhoïde");
		
		leucemieVar.setLabels("true false");
		hallucinationVar.setLabels("true false");
		typhoideVar.setLabels("Normale Severe");
		
		/** Virus de la dengue **/
		
		hemorragieExterneVar = new RuleVariable(rb,"Hémorragie externe");
		hemorragieInterneVar = new RuleVariable(rb,"Hémorragie interne");
		battementCoeurAnormalVar = new RuleVariable(rb,"Battement de coeur anormal");
		tensionVar = new RuleVariable(rb,"Tension Artérielle");
		sensationFroidVar = new RuleVariable(rb,"Sensation Froid");
		dengueTypeVar = new RuleVariable(rb,"Virus de la dengue");
		
		hemorragieExterneVar.setLabels("true false");
		hemorragieInterneVar.setLabels("true false");
		battementCoeurAnormalVar.setLabels("true false");
		tensionVar.setLabels("Basse Normale Haute");
		sensationFroidVar.setLabels("true false");
		dengueTypeVar.setLabels("1/2 3/4");
		
		/** Brucellose **/
		
		fievreOndulanteVar = new RuleVariable(rb,"Fièvre Ondulante");
		oedemeVar = new RuleVariable(rb,"Oedème");
		splenomegalieVar = new RuleVariable(rb,"Splénomégalie");
		ecchymoseVar = new RuleVariable(rb,"Ecchymose");
		lethargieVar = new RuleVariable(rb,"Léthargie");
		jaunisseVar = new RuleVariable(rb,"Jaunisse");
		hepatomegalieVar = new RuleVariable(rb,"Hépatomégalie");
		gonflementGanglionsVar = new RuleVariable(rb,"Gonflement Ganglions");
		adenopathieVar = new RuleVariable(rb,"Adénopathie");
		brucelloseVar = new RuleVariable(rb,"Brucellose");
		
		fievreOndulanteVar.setLabels("true false");
		oedemeVar.setLabels("true false");
		splenomegalieVar.setLabels("true false");
		ecchymoseVar.setLabels("true false");
		lethargieVar.setLabels("true false");
		jaunisseVar.setLabels("true false");
		hepatomegalieVar.setLabels("true false");
		gonflementGanglionsVar.setLabels("true false");
		adenopathieVar.setLabels("true false");
		brucelloseVar.setLabels("true false");
		
	}
	
	public void initializeRules() {
		/** Tuberculose **/
		Clause[] c1 = new Clause[8];
		
		c1[0] = new Clause(touxVar,new Condition("="),"true");
		c1[1] = new Clause(fievreVar,new Condition("="),"Faible");
		c1[2] = new Clause(amaigrissementVar,new Condition("="),"true");
		c1[3] = new Clause(anorexieVar,new Condition("="),"true");
		c1[4] = new Clause(asthenieVar,new Condition("="),"true");
		c1[5] = new Clause(transpirationVar,new Condition("="),"Profuse");
		c1[6] = new Clause(adenopathiesCervicalesVar,new Condition("="),"true");
		c1[7] = new Clause(frottisVar,new Condition("="),"Positif");
		
		Clause[] c2 = new Clause[10];
		
		c2[0] = new Clause(touxVar,new Condition("="),"true");
		c2[1] = new Clause(fievreVar,new Condition("="),"Faible");
		c2[2] = new Clause(amaigrissementVar,new Condition("="),"true");
		c2[3] = new Clause(anorexieVar,new Condition("="),"true");
		c2[4] = new Clause(asthenieVar,new Condition("="),"true");
		c2[5] = new Clause(transpirationVar,new Condition("="),"Profuse");
		c2[6] = new Clause(adenopathiesCervicalesVar,new Condition("="),"true");
		c2[7] = new Clause(rechutesVar,new Condition("="),"true");
		c2[8] = new Clause(traitementApresInterruptionVar,new Condition("="),"true");
		c2[9] = new Clause(traitementApresEchecTherapeutiqueVar,new Condition("="),"true");	
		
		Clause[] c3 = new Clause[8];	

		c3[0] = new Clause(touxVar,new Condition("="),"true");
		c3[1] = new Clause(fievreVar,new Condition("="),"Faible");
		c3[2] = new Clause(amaigrissementVar,new Condition("="),"true");
		c3[3] = new Clause(anorexieVar,new Condition("="),"true");
		c3[4] = new Clause(asthenieVar,new Condition("="),"true");
		c3[5] = new Clause(transpirationVar,new Condition("="),"Profuse");
		c3[6] = new Clause(adenopathiesCervicalesVar,new Condition("="),"true");
		c3[7] = new Clause(VIHVar,new Condition("="),"Seropositif");
		

		
		R1 = new Rule(rb,"R1",c1,new Clause(tuberculoseVar,new Condition("="),"I"));
		R2 = new Rule(rb,"R2",c2,new Clause(tuberculoseVar,new Condition("="),"II"));
		R3 = new Rule(rb,"R3",c3,new Clause(tuberculoseVar,new Condition("="),"III"));
		
		/** Diabete **/
	
		Clause c4 = new Clause(glucosePlasmatiqueVar,new Condition("<"),"99");
		
		Clause[] c5 = new Clause[2];
				
		c5[0] = new Clause(glucosePlasmatiqueVar, new Condition(">"),"98");
		c5[1] = new Clause(glucosePlasmatiqueVar, new Condition("<"),"126");
		
		Clause c6 = new Clause(glucosePlasmatiqueVar,new Condition(">"),"125");
		
		R4 = new Rule(rb,"R4",c4, new Clause(diabeteVar,new Condition("="),"Non-Diabetique"));
		R5 = new Rule(rb,"R5",c5, new Clause(diabeteVar,new Condition("="),"Prediabetique"));
		R6 = new Rule(rb,"R6",c6, new Clause(diabeteVar,new Condition("="),"Diabetique"));
		
		/** Malaria **/
		
		Clause c7 = new Clause(parasitemieVar,new Condition("="),"true");
		
		Clause[] c8 = new Clause[4];
		
		c8[0] = new Clause(parasitemieVar,new Condition("="),"true");
		c8[1] = new Clause(hemoglobineVar,new Condition("="),"Basse");
		c8[2] = new Clause(glucosePlasmatiqueVar,new Condition("<"),"50");
		c8[3] = new Clause(comaVar,new Condition("="),"true");
		

		R7 = new Rule(rb,"R7",c7, new Clause(malariaVar,new Condition("="),"Peu-Compliquee"));
		R8 = new Rule(rb,"R8",c8, new Clause(malariaVar,new Condition("="),"Severe"));
		
		/** Fièvre Typhoide **/
		

		Clause c9 = new Clause(leucemieVar,new Condition("="),"true");
		
		Clause[] c10 = new Clause[2];
		
		c10[0] = new Clause(leucemieVar,new Condition("="),"true");
		c10[1] = new Clause(hallucinationVar,new Condition("="),"true");
		

		R9 = new Rule(rb,"R9",c9, new Clause(typhoideVar,new Condition("="),"Normale"));
		R10 = new Rule(rb,"R10",c10, new Clause(typhoideVar,new Condition("="),"Severe"));
		
		
		/** Virus de la dengue **/
		
		Clause[] c11 = new Clause[4];
		
		c11[0] = new Clause(fievreVar,new Condition("="),"Forte");
		c11[1] = new Clause(hemorragieExterneVar,new Condition("="),"true");
		c11[2] = new Clause(hemorragieInterneVar,new Condition("="),"true");
		c11[3] = new Clause(battementCoeurAnormalVar,new Condition("="),"true");
		

		Clause[] c12 = new Clause[5];
		
		c12[0] = new Clause(fievreVar,new Condition("="),"Forte");
		c12[1] = new Clause(hemorragieExterneVar,new Condition("="),"true");
		c12[2] = new Clause(hemorragieInterneVar,new Condition("="),"true");
		c12[3] = new Clause(tensionVar,new Condition("="),"Basse");
		c12[4] = new Clause(transpirationVar,new Condition("="),"Profuse");
		
		Clause[] c13 = new Clause[5];
		
		c13[0] = new Clause(fievreVar,new Condition("="),"Forte");
		c13[1] = new Clause(hemorragieExterneVar,new Condition("="),"true");
		c13[2] = new Clause(hemorragieInterneVar,new Condition("="),"true");
		c13[3] = new Clause(tensionVar,new Condition("="),"Basse");
		c13[4] = new Clause(sensationFroidVar,new Condition("="),"true");
		

		R11 = new Rule(rb,"R11",c11, new Clause(dengueTypeVar,new Condition("="),"1/2"));
		R12 = new Rule(rb,"R12",c12, new Clause(dengueTypeVar,new Condition("="),"3/4"));
		R13 = new Rule(rb,"R13",c13, new Clause(dengueTypeVar,new Condition("="),"3/4"));
	
		/** Brucellose **/
		
		Clause[] c14 = new Clause[3];
		
		c14[0] = new Clause(fievreVar,new Condition("="),"Forte");
		c14[1] = new Clause(transpirationVar,new Condition("="),"Profuse");
		c14[2] = new Clause(asthenieVar,new Condition("="),"true");
		
		R14 = new Rule(rb,"R14",c14, new Clause(fievreOndulanteVar,new Condition("="),"true"));
		
		
		Clause c15 = new Clause(oedemeVar,new Condition("="),"true");
		
		R15 = new Rule(rb,"R15",c15, new Clause(splenomegalieVar,new Condition("="),"true"));
		
		Clause[] c16 = new Clause[3];
		
		c16[0] = new Clause(ecchymoseVar,new Condition("="),"true");
		c16[1] = new Clause(lethargieVar,new Condition("="),"true");
		c16[2] = new Clause(jaunisseVar,new Condition("="),"true");
		
		R16 = new Rule(rb,"R16",c16, new Clause(hepatomegalieVar,new Condition("="),"true"));
		
		Clause c17 = new Clause(gonflementGanglionsVar, new Condition("="),"true");

		R17 = new Rule(rb,"R17",c17, new Clause(adenopathieVar,new Condition("="),"true"));
		
		Clause[] c18 = new Clause[4];
		
		c18[0] = new Clause(fievreOndulanteVar,new Condition("="),"true");
		c18[1] = new Clause(splenomegalieVar,new Condition("="),"true");
		c18[2] = new Clause(hepatomegalieVar,new Condition("="),"true");
		c18[3] = new Clause(adenopathieVar,new Condition("="),"true");
		
		R18 = new Rule(rb,"R18",c18, new Clause(brucelloseVar,new Condition("="),"true"));
	}


	public BooleanRuleBase getRuleBase() {
		return rb;
	}


	public void setRuleBase(BooleanRuleBase rb) {
		this.rb = rb;
	}

	
}
