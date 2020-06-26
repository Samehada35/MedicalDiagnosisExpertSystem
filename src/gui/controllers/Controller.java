package gui.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Cell;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import medical.MedicalExpertSystem;
import rule.BooleanRuleBase;

public class Controller implements Initializable{

	private Stage stage;
	private MedicalExpertSystem m = new MedicalExpertSystem();
	private HashMap<String,String> diseases = new HashMap<>();
	private HashMap<String,String> symptoms = new HashMap<>();
	private Control[] controls;
	
	public Controller() {
		populateLists();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void populateLists() {
		diseases.put("Tuberculose","La tuberculose est une maladie infectieuse causée par la bactérie Mycobacterium tuberculosis, contagieuse, avec des signes cliniques variables. Elle arrive en tête des causes de mortalité d'origine infectieuse à l'échelle mondiale, devant le sida.");
		diseases.put("Diabète","Le diabète est une maladie chronique qui ne se guérit pas, mais que l’on peut traiter et contrôler. Il est causé par un manque ou un défaut d'utilisation d’une hormone appelée insuline.");
		diseases.put("Malaria","Le paludisme ou la malaria, appelé également \"fièvre des marais\", est une maladie infectieuse due à un parasite du genre Plasmodium, propagée par la piqûre de certaines espèces de moustiques anophèles.");
		diseases.put("Fièvre Typhoïde","La fièvre typhoïde (du grec tuphos, torpeur) ou typhus abdominal est une maladie infectieuse décrite en 1818 par Pierre Bretonneau, causée par une bactérie de la famille Entérobactérie, du genre des salmonelles, et dont les espèces responsables sont Salmonella enterica.");
		diseases.put("Virus de la dengue", "La dengue est une infection virale transmise par la piqûre d’un moustique femelle du genre Aedes. On distingue quatre sérotypes du virus de la dengue (DEN 1, DEN 2, DEN 3 et DEN 4). Ce virus est l'un des plus répandus à travers le monde et on dénote environ 100 millions de cas chaque année de sa maladie sous sa forme bénigne.");
		diseases.put("Brucellose", "La brucellose est une maladie infectieuse due à une bactérie du genre Brucella, commune à certains animaux et à l'homme : on parle d'anthropozoonose.");
		
		symptoms.put("Anorexie", "L'anorexie correspond à une perte d'appétit, empêchant le patient de se nourrir. Si celle-ci devient chronique, elle peut devenir mortelle, par manque de nutriments dans le corps. Elle peut être un symptôme dans une maladie plus grave, comme le cancer, ou d'origine mentale.");
		symptoms.put("Asthénie", "Asthénie, de -sthénie (force, vigueur) et du privatif a- : affaiblissement de l'organisme, fatigue physique. Par extension, elle peut concerner l'état psychique, la libido ou l'intellect.");
		symptoms.put("Adénopathie Cervicale", "Les adénopathies cervicales correspondent à une augmentation de volume des ganglions situés au niveau du cou. Ces \"sentinelles du système immunitaire\" sont essentielles au bon fonctionnement du corps. L'adénopathie cervicale peut avoir plusieurs origines, le traitement choisi dépendra donc de la cause. ");
		symptoms.put("Frottis", "Un frottis sanguin est du sang étalé sur une lame de microscope, dans le but d'observer ses cellules et aussi les dénombrer. Le frottis doit subir une coloration pour révéler certaines cellules qui sans cela seraient transparentes, donc non visibles.");
		symptoms.put("VIH", "Le virus de l'immunodéficience humaine est un rétrovirus infectant l'Homme et responsable du syndrome d'immunodéficience acquise, qui est un état affaibli du système immunitaire le rendant vulnérable à de multiples infections opportunistes.");
		symptoms.put("Parasitémie", "La parasitémie est la quantité d'un parasite présent dans le sang humain ou animal Elle est utilisée pour le diagnostic d'une maladie parasitaire, ou l'efficacité du traitement contre cette maladie. La non efficacité du traitement peut faire évoquer une antibiorésistance");
		symptoms.put("Leucémie", "La leucémie (du grec leukos, blanc, et haima, sang) est un cancer des cellules de la moelle osseuse (les cellules de la moelle produisent les cellules sanguines, d'où le terme parfois utilisé de cancer du sang), faisant partie des hémopathies malignes.");
		symptoms.put("Fièvre Ondulante", "Infection provoquée par des bactéries du genre Brucella impliquant surtout le système réticulo-endothélial. Maladie caractérisée par fièvre, affaiblissement, malaise et amaigrissement.");
		symptoms.put("Oedème", "Un œdème correspond au gonflement d'un organe ou d'un tissu dû à une accumulation ou un excès intratissulaire de liquides dans le milieu interstitiel chez les animaux ou dans les cellules chez les végétaux.");
		symptoms.put("Splénomégalie", "La splénomégalie est une augmentation anormale du volume de la rate. La rate, située sous le diaphragme, dans la partie supérieure gauche de l'abdomen, est un organe faisant partie du système immunitaire.");
		symptoms.put("Ecchymose", "Lorsqu'un corps frappe des parties molles de l'organisme, il peut produire une contusion : lésion sans rupture de la peau ni fissure des tissus qui se caractérise par une décoloration initiale puis un renflement. On parle d'ecchymose \\e.ki.moz\\ seulement lorsqu'il y a une extravasation sanguine dermique, autrement dit du sang qui quitte les vaisseaux.");
		symptoms.put("Léthargie", "La léthargie est une forme de vie ralentie qui permet à certains animaux de surmonter des conditions ambiantes défavorables. Les animaux en léthargie ont des fonctions vitales extrêmement réduites.");
		symptoms.put("Jaunisse", "La jaunisse, ou ictère en langage médical, est une coloration jaune de la peau et des muqueuses (revêtements qui tapissent les cavités du corps). Son intensité peut être plus ou moins importante et elle commence souvent par une coloration jaune du blanc de l’œil.");
		symptoms.put("Hépatomégalie", "L'hépatomégalie (repérable à la palpation chez l'homme) est une hypertrophie du foie c'est-à-dire une augmentation du volume du foie, palpable sous le rebord costal droit. Il peut s'agir d'une augmentation de volume de l'organe en entier, d'un lobe en particulier ou d'un secteur plus circonscrit.");
		symptoms.put("Adénopathie", "L’adénopathie est le terme médical qui est couramment utilisé pour désigner le gonflement des ganglions lymphatiques. Avoir les ganglions gonflés est le signe d’une agression de l’organisme. Les adénopathies peuvent ainsi être perçues comme un signal d’alerte. Elles peuvent être liées à des infections bénignes et passagères, mais peuvent aussi avoir des causes plus graves.");		
	}
	
	
	@FXML
	private void close(ActionEvent event) {
		Alert a =  new Alert(AlertType.CONFIRMATION,"Voulez-vous vraiment quitter ?",ButtonType.YES,ButtonType.NO);
		
		Optional<ButtonType> result = a.showAndWait();
		
		if(result.get() == ButtonType.YES) {
			Platform.exit();
			System.exit(0);
		}
		
	}

	
	@FXML
	private void showRuleBase(ActionEvent event) {
		setTab(1);
		
		Text rulesList = (Text) stage.getScene().lookup("#rules-list");
		
		m.getRuleBase().displayRules(rulesList);
	}
	
	@FXML
	private void describeData(ActionEvent event) {
		ListView ld = (ListView) stage.getScene().lookup("#diseases-list-view");
		ListView ls = (ListView) stage.getScene().lookup("#symptoms-list-view");

		ObservableList<String> observableDiseasesList = FXCollections.observableArrayList(new ArrayList<>(diseases.keySet()));
		ld.setItems(observableDiseasesList);
		
		
		ObservableList<String> observableSymptomsList = FXCollections.observableArrayList(new ArrayList<>(symptoms.keySet()));
		ls.setItems(observableSymptomsList);
		
		setTab(2);
		
	}
	
	@FXML
	private void describeDisease(MouseEvent event) {
		ListView l = (ListView) stage.getScene().lookup("#diseases-list-view");
		
		int selectedIndex = l.getSelectionModel().getSelectedIndex();
		
		Text descriptionText = (Text) stage.getScene().lookup("#description-text");
	
		descriptionText.setText(diseases.get(l.getItems().get(selectedIndex)));
	}
	
	@FXML
	private void describeSymptom(MouseEvent event) {
		ListView l = (ListView) stage.getScene().lookup("#symptoms-list-view");
		
		int selectedIndex = l.getSelectionModel().getSelectedIndex();
		
		Text descriptionText = (Text) stage.getScene().lookup("#description-text");

		descriptionText.setText(symptoms.get(l.getItems().get(selectedIndex)));
	}
	
	@FXML
	private void showAbout(ActionEvent event) {
		Alert a = new Alert(AlertType.INFORMATION,"Application réalisée par Arabi Sami\nEncadrée par M. Kechid",ButtonType.CLOSE);

		a.setTitle("A propos");
		a.setHeaderText("Projet Technologie des Agents");
		
		a.showAndWait();
	}
	
	@FXML
	private void diagnose(ActionEvent event) {
		m.getRuleBase().reset();
		
		ComboBox<String> cb = (ComboBox<String>) stage.getScene().lookup("#disease-detection-choice");
		RadioButton rb1 = (RadioButton) stage.getScene().lookup("#forward-chaining-choice");
		RadioButton rb2 = (RadioButton) stage.getScene().lookup("#backward-chaining-choice");
		
		cb.getItems().setAll("Tuberculose", "Diabète", "Malaria", "Fièvre Typhoïde", "Virus de la dengue", "Brucellose");
		cb.getSelectionModel().select(0);	
		cb.setDisable(true);
		
		rb1.setSelected(true);
		
		rb1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cb.setDisable(true);
				
			}
		});
		
		rb2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				cb.setDisable(false);
				
			}
		});
		
		Button diagnose = (Button) stage.getScene().lookup("#diagnose-button");
		
		Object[] obj = stage.getScene().lookup("#symptom-wrapper").lookupAll(".control").toArray();
		
		TextField g = (TextField) obj[0];
		
		g.setTextFormatter(new TextFormatter<String>(t->{
			return t.getText().matches("[0-9]*") ? t : null;
		}));
		
		ComboBox<String>[] c = new ComboBox[7];

		for(int i=1;i<8;i++) {
			c[i-1] = (ComboBox) obj[i];
		}
		
		c[0].getItems().addAll("Faible","Moyenne","Forte");
		c[1].getItems().addAll("Aucune","Faible","Profuse");
		c[2].getItems().addAll("Positif","Négatif");
		c[3].getItems().addAll("Seropositif","Seronegatif");
		c[4].getItems().addAll("Absente","Présente");
		c[5].getItems().addAll("Basse","Normale");
		c[6].getItems().addAll("Basse","Normale","Haute");
		
		for(int i=0;i<c.length;i++) {
			c[i].getSelectionModel().select(0);
		}

		CheckBox[] ch = new CheckBox[24];
		
		for(int i=8;i<obj.length;i++) {
			ch[i-8] = (CheckBox) obj[i];
		}

		
		diagnose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Text diagnoseResult = (Text) stage.getScene().lookup("#diagnose-result");
				
				BooleanRuleBase rb = m.getRuleBase();
				
				rb.setVariableValue("Glucose Plasmatique à jeun", g.getText());
				rb.setVariableValue("Fièvre", c[0].getValue());
				rb.setVariableValue("Transpiration", c[1].getValue());
				rb.setVariableValue("Frottis", c[2].getValue());
				rb.setVariableValue("VIH", c[3].getValue());
				rb.setVariableValue("Parasitémie", c[4].getValue());
				rb.setVariableValue("Hémoglobine", c[5].getValue());
				rb.setVariableValue("Tension Artérielle", c[6].getValue());
				rb.setVariableValue("Toux", String.valueOf(ch[0].isSelected()));
				rb.setVariableValue("Amaigrissement", String.valueOf(ch[1].isSelected()));
				rb.setVariableValue("Anorexie", String.valueOf(ch[2].isSelected()));
				rb.setVariableValue("Asthénie", String.valueOf(ch[3].isSelected()));
				rb.setVariableValue("Adénopathies cervicales", String.valueOf(ch[4].isSelected()));
				rb.setVariableValue("Rechutes", String.valueOf(ch[5].isSelected()));
				rb.setVariableValue("Traitement après interruption", String.valueOf(ch[6].isSelected()));
				rb.setVariableValue("Traitement après échec thérapeutique", String.valueOf(ch[7].isSelected()));
				rb.setVariableValue("Coma", String.valueOf(ch[8].isSelected()));
				rb.setVariableValue("Leucémie", String.valueOf(ch[9].isSelected()));
				rb.setVariableValue("Hallucinations", String.valueOf(ch[10].isSelected()));
				rb.setVariableValue("Hémorragie externe", String.valueOf(ch[11].isSelected()));
				rb.setVariableValue("Hémorragie interne", String.valueOf(ch[12].isSelected()));
				rb.setVariableValue("Battement de coeur anormal", String.valueOf(ch[13].isSelected()));
				rb.setVariableValue("Sensation Froid", String.valueOf(ch[14].isSelected()));
				rb.setVariableValue("Fièvre Ondulante", String.valueOf(ch[15].isSelected()));
				rb.setVariableValue("Oedème", String.valueOf(ch[16].isSelected()));
				rb.setVariableValue("Splénomégalie", String.valueOf(ch[17].isSelected()));
				rb.setVariableValue("Ecchymose", String.valueOf(ch[18].isSelected()));
				rb.setVariableValue("Léthargie", String.valueOf(ch[19].isSelected()));
				rb.setVariableValue("Jaunisse", String.valueOf(ch[20].isSelected()));
				rb.setVariableValue("Hépatomégalie", String.valueOf(ch[21].isSelected()));
				rb.setVariableValue("Gonflement Ganglions", String.valueOf(ch[22].isSelected()));
				rb.setVariableValue("Adénopathie", String.valueOf(ch[23].isSelected()));
				
				
				if(rb1.isSelected()) {
					m.getRuleBase().forwardChain();

					diagnoseResult.setText("");
					
					for(String disease : diseases.keySet()) {
						
						String result = m.getRuleBase().getVariable(disease).getValue();
						
						if(m.getRuleBase().getVariable(disease).getValue() != null) {
							switch(disease) {
							case "Tuberculose":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous avez une tuberculose type : "+result+"\n");
								break;
							case "Diabète":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous êtes : "+result+"\n");
								break;
							case "Malaria":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous avez une malaria "+result+"\n");
								break;
							case "Fièvre Typhoïde":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous avez une fièvre typhoïde "+result+"\n");
								break;
							case "Virus de la dengue":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous avez la virus de la dengue type "+result+"\n");
								break;
							case "Brucellose":
								diagnoseResult.setText(diagnoseResult.getText()+"Vous avez la brucellose\n");
								break;
							}
							
						}else {
							diagnoseResult.setText(diagnoseResult.getText()+"Vous n'avez pas : "+disease+"\n");
						}
					}
					
				}else {
					m.getRuleBase().backwardChain(cb.getValue());
					
					String result = m.getRuleBase().getVariable(cb.getValue()).getValue();
					
					if(result != null) {
						switch(cb.getValue()) {
							case "Tuberculose":
								diagnoseResult.setText("Vous avez une tuberculose type : "+result);
								break;
							case "Diabète":
								diagnoseResult.setText("Vous êtes : "+result);
								break;
							case "Malaria":
								diagnoseResult.setText("Vous avez une malaria "+result);
								break;
							case "Fièvre Typhoïde":
								diagnoseResult.setText("Vous avez une fièvre typhoïde "+result);
								break;
							case "Virus de la dengue":
								diagnoseResult.setText("Vous avez la virus de la dengue type "+result);
								break;
							case "Brucellose":
								diagnoseResult.setText("Vous avez la brucellose");
								break;
						}
					}else {
						diagnoseResult.setText("Vous n'avez pas : "+cb.getValue());
					}
				}
				
				setTab(4);
				
			}
			
		});
		
		setTab(3);
	}
	

	public void setStage(Stage primaryStage) {
		this.stage = primaryStage;
	}
	
	private void setTab(int index) {
		TabPane tabPane = (TabPane) stage.getScene().lookup("#main-tab-pane");
		
		tabPane.getSelectionModel().select(index);
		
	}
	
	private String getValue(int index) {
		if(index==0) {
			return ((TextField) controls[index]).getText();
		}else if(index > 0 && index < 8 ) {
			return ((ComboBox<String>) controls[index]).getValue();
		}else {
			return String.valueOf(((CheckBox) controls[index]).isSelected());
		}
	}
}
