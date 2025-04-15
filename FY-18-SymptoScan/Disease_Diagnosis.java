package week1;
import java.util.Scanner;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Details
{   static String name;
   static  int age;
    static String g;
   static  int wt;
    static String bgo;
	public static void details()
   {
      Scanner sc=new Scanner(System.in);
      System.out.println("Enter The Patient's Name:");
      name=sc.nextLine();
      System.out.println("Enter The Patient's Age:");
      age=sc.nextInt();
      System.out.println("Enter The Patient's Gender:");
       g=sc.next();
      System.out.println("Enter The Patient's Weight:");
      wt=sc.nextInt();
      System.out.println("Enter The Patient's Blood Group:");
      bgo=sc.next();
   }
	}
class MedicalCardDisplay {
	static String lnk;
   public static void displayMedicalCard(String diagnosis) {
       SwingUtilities.invokeLater(() -> {
       	
           JFrame frame = new JFrame("Medical Card");
           frame.setSize(800, 600); // Set the size of the frame
           frame.getContentPane().setBackground(Color.WHITE); // Set background color
           frame.setLayout(new BorderLayout()); // Set layout manager
           String nm=Details.name;
           int ag=Details.age;
           String gen=Details.g;
           String bgr=Details.bgo;
           int wg=Details.wt;
           JLabel introLabel=new JLabel("Hi "+nm+"!");
           JLabel reportLabel =new JLabel("Here's your preliminary medical diagnosis report");
           JLabel diagnosisLabel = new JLabel("Based on your responses, your probable diagnosis is:");
           JLabel diagnosisValueLabel = new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>" + diagnosis + "</div></html>");
           JLabel recommendationLabel = new JLabel("We recommend consulting with a professional to confirm this preliminary assessment and seek appropriate guidance.");
           JLabel infoLabel = new JLabel("You can also refer to the following resources for additional information.");
           JLabel thankYouLabel = new JLabel("Thank you for using our diagnosis system. Stay healthy, stay fit.");
           Font font = new Font("Arial", Font.BOLD, 18); // Set font
           Color textColor = Color.BLACK; // Set text color
           introLabel.setFont(font);
           reportLabel.setFont(font);      
           introLabel.setHorizontalAlignment(JLabel.CENTER);
           reportLabel.setHorizontalAlignment(JLabel.CENTER);    
           diagnosisLabel.setHorizontalAlignment(JLabel.CENTER);
           diagnosisValueLabel.setHorizontalAlignment(JLabel.CENTER);
           recommendationLabel.setHorizontalAlignment(JLabel.CENTER);
           thankYouLabel.setHorizontalAlignment(JLabel.CENTER);
           JPanel infoPanel = new JPanel();
           infoPanel.setBackground(new Color(173, 216, 230));
           infoPanel.setLayout(new GridLayout(4, 1,0,10));
           infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding
           
           JLabel nameLabel = new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>"+"Name: " + Details.name+"</div></html>");
           JLabel ageLabel = new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>"+"Age: " + Details.age+"</div></html>");
           JLabel genderLabel = new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>"+"Gender: " + Details.g+"</div></html>");
           JLabel weightLabel = new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>"+"Weight: " + Details.wt+"</div></html>");
           JLabel bloodLabel= new JLabel("<html><div style='border: 1px solid black; padding: 5px;'>"+"Blood Group: "+ Details.bgo+"</div></html>");
           Font labelFont = new Font("Arial", Font.BOLD, 20);
           nameLabel.setFont(labelFont);
           ageLabel.setFont(labelFont);
           genderLabel.setFont(labelFont);
           weightLabel.setFont(labelFont);
           bloodLabel.setFont(labelFont);
           nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
           ageLabel.setHorizontalAlignment(SwingConstants.CENTER);
           genderLabel.setHorizontalAlignment(SwingConstants.CENTER);
           weightLabel.setHorizontalAlignment(SwingConstants.CENTER);
           bloodLabel.setHorizontalAlignment(SwingConstants.CENTER);
  
           infoPanel.add(Box.createVerticalStrut(10));
           infoPanel.add(nameLabel);
           infoPanel.add(Box.createVerticalStrut(5));
           infoPanel.add(ageLabel);
           infoPanel.add(Box.createVerticalStrut(5));
           infoPanel.add(genderLabel);
           infoPanel.add(Box.createVerticalStrut(5));
           infoPanel.add(weightLabel);
           infoPanel.add(Box.createVerticalStrut(5));
           infoPanel.add(bloodLabel);
           infoPanel.add(Box.createVerticalStrut(5));

           JPanel mainPanel = new JPanel(new BorderLayout());
           mainPanel.setBackground(Color.WHITE);
           mainPanel.add(infoPanel, BorderLayout.CENTER);
           mainPanel.add(diagnosisLabel, BorderLayout.NORTH);
           mainPanel.add(diagnosisValueLabel, BorderLayout.SOUTH);
           frame.add(mainPanel);
    
           infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS)); // Set X_AXIS orientation
           infoPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between labels
           infoPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between labels
           infoPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add spacing between labels
           infoLabel.setHorizontalAlignment(JLabel.CENTER);
           diagnosisLabel.setFont(font);
           diagnosisValueLabel.setFont(font);
           recommendationLabel.setFont(font);
           infoLabel.setFont(font);
           thankYouLabel.setFont(font);
           diagnosisLabel.setForeground(textColor);
           diagnosisValueLabel.setForeground(textColor);
           recommendationLabel.setForeground(textColor);
           infoLabel.setForeground(textColor);
           thankYouLabel.setForeground(textColor);
           
           JLabel label = new JLabel("Click here to refer to resources");
           if(diagnosis=="Typhoid(Probability:80-90%)" || diagnosis=="Typhoid(Probability:50-60%)"||diagnosis=="Typhoid(Probability:20-30%)")
           {lnk="https://www.mayoclinic.org/diseases-conditions/typhoid-fever/symptoms-causes/syc-20378661";}
           else if(diagnosis=="Pneumonia(Probability:80-90%)" || diagnosis=="Pneumonia(Probability:50-60%)"||diagnosis=="Pneumonia(Probability:20-30%)")
           {
         	  lnk="https://www.lung.org/lung-health-diseases/lung-disease-lookup/pneumonia/symptoms-and-diagnosis";
           }
           else if(diagnosis=="Hepatitis A(Probability:90-100%)")
           {
         	   lnk="https://www.who.int/news-room/fact-sheets/detail/hepatitis-a";
           }
           else if(diagnosis=="Food Poisoning or Appendicitis(Probability:90-100%)")
           {
         	   lnk="https://drive.google.com/file/d/17KgWsIiYy7ud7J_C_E4lPMVGgW9oGQM7/view";
           }
           else if(diagnosis=="Common Cold(Probability:90-100%)")
           {
         	   lnk="https://drive.google.com/file/d/17KgWsIiYy7ud7J_C_E4lPMVGgW9oGQM7/view";
           }
           else if(diagnosis=="Hormonal Fluctuations(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/women/ss/slideshow-hormone-imbalance";
           }
           else if(diagnosis=="Anxiety(Probability:90-100%)")
           {
         	   lnk="https://www.who.int/news-room/fact-sheets/detail/anxiety-disorders";
           }
           else if(diagnosis=="Stress Eating(Probability:90-100%)")
           {
         	   lnk="https://www.hopkinsmedicine.org/health/wellness-and-prevention/tips-to-manage-stress-eating";
           }
           else if(diagnosis=="Eating Disorder(Probability:80-90%)"||diagnosis=="Eating Disorder(Probability:30-40%)")
           {
         	   lnk="https://www.nimh.nih.gov/health/topics/eating-disorders#:~:text=A%20relentless%20pursuit%20of%20thinness,seriousness%20of%20low%20body%20weight";
           }
           else if(diagnosis=="PCOS(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/women/what-is-pcos";
           }
           else if(diagnosis=="Menopause(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/menopause/understanding-menopause-symptoms";
           }
           else if(diagnosis=="Panic Attacks(Probability:80-90%)")
           {
         	   lnk="https://www.webmd.com/anxiety-panic/panic-attack-symptoms";
           }
           else if(diagnosis=="Depression(Probability:80-90%)"||diagnosis=="Depression(Probability:30-40% or Short term Depression)")
           {
         	   lnk="https://www.nhs.uk/mental-health/conditions/depression-in-adults/symptoms/";
           }
           else if(diagnosis=="Dysthymia(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/depression/chronic-depression-dysthymia";
           }
           else if(diagnosis=="Acne(Probability:90-100%)")
           {
         	   lnk="https://my.clevelandclinic.org/health/diseases/12233-acne";
           }
           else if(diagnosis=="Hyperpigmentation(Probability:90-100%)")
           {
         	   lnk="https://www.healthline.com/health/hyperpigmentation#symptoms-and-risks";
           }
           else if(diagnosis=="Dehydrated skin(Probability:90-100%)")
           {
         	   lnk="https://www.medicalnewstoday.com/articles/dehydrated-skin#summary";
           }
           else if(diagnosis=="Nummular dermatitis or Ringworm(Probability:90-100%)")
           {
         	   lnk="https://nationaleczema.org/eczema/types-of-eczema/nummular-eczema/";
           }
           else if(diagnosis=="Impetigo(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/skin-problems-and-treatments/understanding-impetigo-basics";
           }
           else if(diagnosis=="Contact Dermatitis(Probability:90-100%)")
           {
         	   lnk="https://www.mayoclinic.org/diseases-conditions/contact-dermatitis/symptoms-causes/syc-20352742";
           }
           else if(diagnosis=="Eczema(Probability:90-100%)")
           {
         	   lnk="https://www.webmd.com/skin-problems-and-treatments/eczema/atopic-dermatitis-eczema";
           }
           else
           {   lnk="https://drive.google.com/file/d/17KgWsIiYy7ud7J_C_E4lPMVGgW9oGQM7/view";
           }

           Color darkBlue = new Color(0, 0, 128);
           label.setForeground(darkBlue);
           label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
           label.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseClicked(MouseEvent e) {
                   try {
                       Desktop.getDesktop().browse(new java.net.URI(lnk));
                   } catch (Exception ex) {
                       ex.printStackTrace();
                   }
               }
           });
           label.setFont(new Font("Arial", Font.BOLD, 20)); // Set font to Arial, bold, size 20
           label.setHorizontalAlignment(JLabel.CENTER);
           JPanel panel = new JPanel();
           panel.setBackground(Color.LIGHT_GRAY);
           panel.setLayout(new GridLayout(0, 1));
           panel.add(introLabel);
           panel.add(reportLabel);
           panel.add(infoPanel); // Add the panel containing name, age, gender labels
           panel.add(diagnosisLabel);
           panel.add(diagnosisValueLabel);
           panel.add(recommendationLabel);
           panel.add(infoLabel);
           panel.add(label);
           panel.add(thankYouLabel);
           
           JPanel headerPanel = new JPanel(new BorderLayout());
           headerPanel.setBackground(new Color(0, 0, 128)); // Dark blue color
           headerPanel.setPreferredSize(new Dimension(frame.getWidth(), 50)); // Set height
           JLabel headerLabel = new JLabel("Welcome to SymptoScan");
           headerLabel.setForeground(Color.WHITE); // White text color
           headerLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font
           headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
           headerPanel.add(headerLabel, BorderLayout.CENTER);
           
           JScrollPane scrollPane = new JScrollPane(panel);
           frame.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to center of frame
           frame.add(headerPanel, BorderLayout.NORTH);
           frame.setLocationRelativeTo(null); // Center the frame on the screen
           frame.setVisible(true);
       });
   }
 
}

class TreeNode {
	String diagno;
   String name;
   TreeNode yesNode;
   TreeNode noNode;
   public TreeNode(String name) {
       this.name = name;
       this.yesNode = null;
       this.noNode = null;
   }
   public void setYesNode(TreeNode node) {
       this.yesNode = node;
       }
   public void setNoNode(TreeNode node) {
       this.noNode = node;
   }
   public String getName() {
       return name;
   }
   public TreeNode getYesNode() {
   	
       return yesNode;
   }
   public TreeNode getNoNode() {
       return noNode;
   }
}
public class Disease_Diagnosis {
	private static TreeNode root;
	   public static void main(String[] args) {
	       buildTree();
	       diagnose();
	   }	       
	  private static void buildTree() {
		  root=new TreeNode("Does the patient have fever?");
	       TreeNode highfeverNode = new TreeNode("Does the patient have high grade fever?");
	       TreeNode nofeverNode=new TreeNode("Does the patient feel low or lethargic?");
	       TreeNode recent_travelNode = new TreeNode("Did the patient travel recently?");
	       TreeNode lowgradefeverNode = new TreeNode("Does the patient have a low grade fever?");
	       TreeNode food_yesNode = new TreeNode("Has the patient consumed food from outside?");
	       TreeNode weaknessNode = new TreeNode("Is the patient having symptoms like weakness,fatigue?");
	       TreeNode coughNode = new TreeNode("Is the patient having symptoms like cough,chest pain or shortness of breath?");
	       TreeNode diarrhoeaNode = new TreeNode("Does the patient have diarrhoea or constipation?");
	       TreeNode vomiting_100Node = new TreeNode("Is the patient experiencing vomiting?");
	       TreeNode vomiting_40Node = new TreeNode("Is the patient experiencing vomiting?");
	       TreeNode lowfever_recentTravelNode = new TreeNode("Did the patient travel recently?");
	       TreeNode TyphoidNode = new TreeNode("Typhoid(Probability:80-90%)");
	       TreeNode Typhoid_40Node = new TreeNode("Typhoid(Probability:50-60%)");
	       TreeNode NoTyphoidNode = new TreeNode("Typhoid(Probability:20-30%)");
	       TreeNode cough_fatigue_lossofappetiteNode = new TreeNode("Is the patient experiencing fatigue,loss of appetite or nausea and vomiting");
	       TreeNode chronic_diseaseNode = new TreeNode("Does the patient have any medical history of chronic illnesses like asthama,diabetes etc.");
	       TreeNode common_feverNode = new TreeNode("Common Fever(Probability:90-100%)");
	       TreeNode smoking_100Node = new TreeNode("Does the patient smoke? or is exposed to pollutants due to job or any other reason?");
	       TreeNode smoking_Node = new TreeNode("Does the patient smoke? or is exposed to pollutants due to job or any other reason?");
	       TreeNode PneumoniaNode = new TreeNode("Pneumonia(Probability:80-90%)");
	       TreeNode PneumoniaNode_80 = new TreeNode("Pneumonia(Probability:50-60%)");
	       TreeNode PneumoniaNode_low = new TreeNode("Pneumonia(Probability:20-30%)");
	       TreeNode lowfever_foodNode=new TreeNode("Has the patient consumed food from outside?");
	       TreeNode lowfever_weaknessNode=new TreeNode("Does the patient feel weakness or experience loss in appetite?");
	       TreeNode lowfever_stomachpainNode=new TreeNode("Does the patient feel stomach pain or has experienced vomiting?");
	       TreeNode lowfever_runnynoseNode=new TreeNode("Does the patient have a runny nose or a sore throat?");
	       TreeNode lowfever_yelloweyesNode=new TreeNode("Does the patient have yellow eyes or skin/dark urine/experience change in colour of stool?");
	       TreeNode lowfever_commonfever_utiNode=new TreeNode("If Male: Common Fever;If Female:UTI (Probability:90-100%)");
	       TreeNode lowfever_hepatitisaNode=new TreeNode("Hepatitis A(Probability:90-100%)");
	       TreeNode lowfever_foodpoisoning=new TreeNode("Food Poisoning or Appendicitis(Probability:90-100%)");
	       TreeNode lowfever_commoncoldNode=new TreeNode("Common Cold(Probability:90-100%)");
	       TreeNode lowfever_hormonesNode=new TreeNode("Hormonal Fluctuations(Probability:90-100%)");
	        TreeNode noFeverLowLethargicNode = new TreeNode("Does the patient feel low or lethargic?");
	       TreeNode sadHopelessNode = new TreeNode("Does the patient feel sad, hopeless or empty");
	       TreeNode sleepPatternNode = new TreeNode("Is there a change in the patient's sleeping pattern?");
	       TreeNode worrinessNode = new TreeNode("Does the patient experience feelings of worriness and irritability?");
	       TreeNode worrinessIrritabilityNode = new TreeNode("Does the patient experience feelings of worriness and irritability?");
	       TreeNode appetiteNode = new TreeNode("Is there a change in the patient's appetite?");
	       TreeNode worriness_yesNode = new TreeNode("Anxiety(Probability:90-100%)");
	       TreeNode periods_yesNode = new TreeNode("Does the patient face irregular periods?");
	       TreeNode weightNode = new TreeNode("Is there a sudden change in patient's weight?");
	       TreeNode overconcerned_weightNode = new TreeNode("Is the patient overconcerned about their weight?");
	       TreeNode oilyFaceNode_weightyes = new TreeNode("Does the patient has oily skin, hairloss or facial hair growth?");
	       TreeNode oilyFaceNode_weightno=new TreeNode("Does the patient has oily skin, hairloss or facial hair growth?");
	       TreeNode food_intakeNode=new TreeNode("Does the patient frequently keep a track of their food intake?");
	       TreeNode binge_eatNode=new TreeNode("Does the patient binge eat? or restricts themself from eating?");
	       TreeNode sedentary_lifestyleNode=new TreeNode("Does the patient leads a sedentary lifestyle?");
	       TreeNode seasonal_changesNode=new TreeNode("The change in appetite might be occuring due to seasonal changes");
	       TreeNode health_checkupNode=new TreeNode("Health checkup is required");
	       TreeNode avoid_socialoutingsNode=new TreeNode("Does the patient avoid social situations involving food?");
	       TreeNode health_consciousNode=new TreeNode("You are Health conscious");
	       TreeNode self_inducedNode=new TreeNode("Does the patient experience purging behaviour?(self induced vomiting or extreme exercise.)");
	       TreeNode stress_eatingNode=new TreeNode("Stress Eating(Probability:90-100%)");
	       TreeNode eating_disorder_high=new TreeNode("Eating Disorder(Probability:80-90%)");
	       TreeNode eating_disorder_low=new TreeNode("Eating Disorder(Probability:30-40%)");
	       TreeNode stressNode = new TreeNode("Stress or bad lifestyle");
	       TreeNode pcosNode = new TreeNode("PCOS(Probability:90-100%)");
	       TreeNode menopauseNode = new TreeNode("Menopause(Probability:90-100%)");
	       TreeNode lossOfInterestNode = new TreeNode("Has the patient experienced loss of interest or focus lately?");
	       TreeNode eatingHabitsNode = new TreeNode("Are there any changes in patient's eating habits?");
	       TreeNode changeInLifeNode_100 = new TreeNode("Are there any major changes in patient's life?");
	       TreeNode changeInLifeNode_80 = new TreeNode("Are there any major changes in patient's life?");
	       TreeNode sweatingNode = new TreeNode("Does the patient experience sweating, rapid heartbeat or shortness of breath");
	       TreeNode lonelinessNode = new TreeNode("Does the patient overthink or experience feelings of loneliness?");
	       TreeNode muscleTensionNode = new TreeNode("Does the patient experience muscle cramps(clenching of fist, jaw)/trembling?");
	       TreeNode panicAttacksNode = new TreeNode("Panic Attacks(Probability:80-90%)");
	       TreeNode thoughtsOfDying_100Node = new TreeNode("Is the patient experiencing a thoughts of dying or losing control?");
	       TreeNode thoughtsOfDying_80Node = new TreeNode("Is the patient experiencing a thoughts of dying or losing control?");
	       TreeNode depression_100Node = new TreeNode("Depression(Probability:80-90%)");  
	        TreeNode depression_lowNode = new TreeNode("Depression(Probability:30-40% or Short term Depression)");
	        TreeNode dysthymiaNode = new TreeNode("Dysthymia(Probability:90-100%)");
	       TreeNode anxietyNode = new TreeNode("Anxiety(Probability:90-100%)");
	       TreeNode skin_relatedNode = new TreeNode("Is the patient facing any skin related issues?");
	       TreeNode dry_skinNode = new TreeNode("Does the patient have dry,cracked or itchy skin?");
	       TreeNode rashesNode = new TreeNode("Does the patient have rashes?");
	       TreeNode acne_dryskinNode = new TreeNode("Does the patient have acne?");
	       TreeNode acneNode = new TreeNode("Does the patient have acne?");
	       TreeNode acnecardNode = new TreeNode("Acne(Probability:90-100%)");
	       TreeNode seasonal_dryskinNode = new TreeNode("The patient is having dry and itchy skin due to seasonal changes");
	       TreeNode coin_rashesNode = new TreeNode("Does the patient have coin shaped rashes?");
	       TreeNode darkening_skinNode = new TreeNode("Is the patient experiencing darkening of skin?");
	       TreeNode hyperpigmentationNode=new TreeNode("Hyperpigmentation(Probability:90-100%)");
	       TreeNode dehydrated_skinNode=new TreeNode("Dehydrated skin(Probability:90-100%)");
	       TreeNode ringwormNode=new TreeNode("Nummular dermatitis or Ringworm(Probability:90-100%)");
	       TreeNode blisterNode=new TreeNode("Does the patient have fluid filled blisters?");
	       TreeNode mouth_chinNode=new TreeNode("Is it around mouth chin or nose?");
	       TreeNode hairloss_rashNode=new TreeNode("Does the patient have hair loss at the rash site?");
	       TreeNode skin_irritatingNode=new TreeNode("Has the patient come in contact with any substance which might cause skin allergies or any person who has skin related issues?");
	       TreeNode impetigoNode=new TreeNode("Impetigo(Probability:90-100%)");
	       TreeNode contact_dermatitisNode=new TreeNode("Contact Dermatitis(Probability:90-100%)");
	       TreeNode eczemaNode=new TreeNode("Eczema(Probability:90-100%)");
	       root.setYesNode(highfeverNode);
	       root.setNoNode(noFeverLowLethargicNode);
	       highfeverNode.setYesNode(recent_travelNode);
	       highfeverNode.setNoNode(lowgradefeverNode);
	       lowgradefeverNode.setYesNode(lowfever_recentTravelNode);
	       lowgradefeverNode.setNoNode(lowfever_recentTravelNode);
	       recent_travelNode.setYesNode(food_yesNode);
	       recent_travelNode.setNoNode(food_yesNode);
	       food_yesNode.setYesNode(weaknessNode);
	       food_yesNode.setNoNode(coughNode);
	       weaknessNode.setYesNode(diarrhoeaNode);
	       weaknessNode.setNoNode(diarrhoeaNode);
	      diarrhoeaNode.setYesNode(vomiting_100Node);
	      diarrhoeaNode.setNoNode(vomiting_40Node);
	      vomiting_100Node.setYesNode(TyphoidNode);
	      vomiting_100Node.setNoNode(TyphoidNode);
	      vomiting_40Node.setYesNode(Typhoid_40Node);
	      vomiting_40Node.setNoNode(NoTyphoidNode);
	      coughNode.setYesNode(cough_fatigue_lossofappetiteNode);
	      coughNode.setNoNode(common_feverNode);
	      cough_fatigue_lossofappetiteNode.setYesNode(chronic_diseaseNode);
	      cough_fatigue_lossofappetiteNode.setNoNode(common_feverNode);
	      chronic_diseaseNode.setYesNode(smoking_100Node);
	      chronic_diseaseNode.setNoNode(smoking_Node);
	      smoking_100Node.setYesNode(PneumoniaNode);
	      smoking_100Node.setNoNode(PneumoniaNode);
	      smoking_Node.setNoNode(PneumoniaNode_80);
	      smoking_Node.setNoNode(PneumoniaNode_low);
	       lowfever_recentTravelNode.setYesNode(lowfever_foodNode);
	       lowfever_recentTravelNode.setNoNode(lowfever_foodNode);
	       lowfever_foodNode.setYesNode(lowfever_weaknessNode);
	       lowfever_foodNode.setNoNode(lowfever_weaknessNode);
	       lowfever_weaknessNode.setYesNode(lowfever_stomachpainNode);
	       lowfever_weaknessNode.setNoNode(lowfever_runnynoseNode);
	       lowfever_stomachpainNode.setYesNode(lowfever_yelloweyesNode);
	       lowfever_stomachpainNode.setNoNode(lowfever_commonfever_utiNode);
	       lowfever_yelloweyesNode.setYesNode(lowfever_hepatitisaNode);
	       lowfever_yelloweyesNode.setNoNode(lowfever_foodpoisoning);
	       lowfever_runnynoseNode.setYesNode(lowfever_commoncoldNode);
	       lowfever_runnynoseNode.setNoNode(lowfever_hormonesNode);
	      nofeverNode.setYesNode(noFeverLowLethargicNode);
	      nofeverNode.setNoNode(noFeverLowLethargicNode);
	      noFeverLowLethargicNode.setYesNode(sadHopelessNode);
	      sadHopelessNode.setYesNode(sleepPatternNode);
	      sadHopelessNode.setNoNode(worrinessNode);
	      worrinessNode.setYesNode(anxietyNode);
	      worrinessNode.setNoNode(appetiteNode);
	      sleepPatternNode.setYesNode(lossOfInterestNode);
	      sleepPatternNode.setNoNode(lossOfInterestNode);
	      lossOfInterestNode.setYesNode(eatingHabitsNode);	    
	      lossOfInterestNode.setNoNode(worrinessIrritabilityNode);
	       worrinessIrritabilityNode.setYesNode(sweatingNode);
	       worrinessIrritabilityNode.setNoNode(lonelinessNode);
	       lonelinessNode.setYesNode(dysthymiaNode);
	       lonelinessNode.setNoNode(dysthymiaNode);
	      sweatingNode.setYesNode(muscleTensionNode);
	      sweatingNode.setNoNode(anxietyNode);
	      muscleTensionNode.setYesNode(panicAttacksNode);
	      muscleTensionNode.setNoNode(panicAttacksNode);
	      panicAttacksNode.setYesNode(anxietyNode);
	      panicAttacksNode.setNoNode(anxietyNode);
	      eatingHabitsNode.setYesNode(changeInLifeNode_100);
	      eatingHabitsNode.setNoNode(changeInLifeNode_80);
	      changeInLifeNode_100.setYesNode(thoughtsOfDying_100Node);
	      changeInLifeNode_100.setNoNode(thoughtsOfDying_80Node);
	      changeInLifeNode_80.setYesNode(depression_100Node);
	      changeInLifeNode_80.setNoNode(depression_100Node);
	      thoughtsOfDying_100Node.setYesNode(depression_100Node);
	      thoughtsOfDying_100Node.setNoNode(depression_100Node);
	      thoughtsOfDying_80Node.setYesNode(depression_100Node);
	      thoughtsOfDying_80Node.setNoNode(depression_lowNode);
	      appetiteNode.setYesNode(periods_yesNode);
	      appetiteNode.setNoNode(periods_yesNode);
	      periods_yesNode.setYesNode(weightNode);
	      periods_yesNode.setNoNode(overconcerned_weightNode);
	      weightNode.setYesNode(oilyFaceNode_weightyes);
	      weightNode.setNoNode(oilyFaceNode_weightno);
	      oilyFaceNode_weightyes.setYesNode(pcosNode);
	      oilyFaceNode_weightyes.setNoNode(menopauseNode);
	      oilyFaceNode_weightno.setYesNode(stressNode);
	      oilyFaceNode_weightno.setNoNode(stressNode);
	      overconcerned_weightNode.setYesNode(food_intakeNode);
	      overconcerned_weightNode.setNoNode(sedentary_lifestyleNode);
	      food_intakeNode.setYesNode(binge_eatNode);
	      food_intakeNode.setNoNode(seasonal_changesNode);
	      sedentary_lifestyleNode.setYesNode(health_checkupNode);
	      sedentary_lifestyleNode.setNoNode(health_checkupNode);
	      binge_eatNode.setYesNode(avoid_socialoutingsNode);
	      binge_eatNode.setNoNode(health_consciousNode);
	      avoid_socialoutingsNode.setYesNode(self_inducedNode);
	      avoid_socialoutingsNode.setNoNode(stress_eatingNode);
	      self_inducedNode.setYesNode(eating_disorder_high);
	      self_inducedNode.setNoNode(eating_disorder_low);
	      noFeverLowLethargicNode.setNoNode(skin_relatedNode);
	      skin_relatedNode.setYesNode(dry_skinNode);
	      skin_relatedNode.setNoNode(health_checkupNode);
	      dry_skinNode.setYesNode(rashesNode);
	      dry_skinNode.setNoNode(acneNode);
	      rashesNode.setYesNode(coin_rashesNode);
	      rashesNode.setNoNode(acne_dryskinNode);
	      acne_dryskinNode.setYesNode(acnecardNode);
	      acne_dryskinNode.setNoNode(dehydrated_skinNode);
	      acneNode.setYesNode(acnecardNode);
	      acneNode.setNoNode(darkening_skinNode);
	      darkening_skinNode.setYesNode(hyperpigmentationNode);
	      darkening_skinNode.setNoNode(dehydrated_skinNode);
	      coin_rashesNode.setYesNode(ringwormNode);
	      coin_rashesNode.setNoNode(blisterNode);
	      blisterNode.setYesNode(mouth_chinNode);
	      blisterNode.setNoNode(hairloss_rashNode);
	      hairloss_rashNode.setYesNode(eczemaNode);
	      hairloss_rashNode.setNoNode(seasonal_dryskinNode);
	      mouth_chinNode.setYesNode(impetigoNode);
	      mouth_chinNode.setNoNode(skin_irritatingNode);
	      skin_irritatingNode.setYesNode(contact_dermatitisNode);
	      skin_irritatingNode.setNoNode(seasonal_dryskinNode);
	  
		   }
		 
		   // Perform diagnosis based on user input
		   private static void diagnose() {
		       Scanner scanner = new Scanner(System.in);
		       TreeNode currentNode = root;
		       Details.details();
		       while (true) {
		           System.out.println(currentNode.getName() + " (yes/no)");
		           String answer = scanner.nextLine().toLowerCase();
		           if (answer.equals("yes")) {
		               currentNode = currentNode.getYesNode();
		           } else if (answer.equals("no")) {
		               currentNode = currentNode.getNoNode();
		           } else {
		               System.out.println("Invalid input. Please enter 'yes' or 'no'.");
		               continue;
		           }
		           if (currentNode == null) {
		               System.out.println("Diagnosis: Unknown");
		               break;
		           }
		           if (currentNode.getYesNode() == null && currentNode.getNoNode() == null) {
		               System.out.println("Diagnosis: " + currentNode.getName());
		               MedicalCardDisplay.displayMedicalCard(currentNode.getName());
		               break;
		           }
		       }
		       scanner.close();
		   }
		}
	