package ModelClassifier;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.RandomForest;
import weka.core.AbstractInstance;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Instance;
import weka.core.SerializationHelper;

/**
 * This is a classifier for wikihow dataset  
 * @author Daria Maggi and Marsha Gomez 
 * Data Mining and Machine Learning University of Pisa
 * Github link: https://github.com/dariamaggi/parser
 */
public class ModelClassifier {

    private Attribute TITLE;
    private Attribute SUMMARY;
    private Attribute TEXT;


    private ArrayList<Attribute> attributes;
    private ArrayList<String> classVal;
    private Instances dataRaw;


    public ModelClassifier() {
        System.out.println("\n --- MODEL CLASSIFIER ------ ");
        TITLE = new Attribute("TITLE");
        SUMMARY = new Attribute("SUMMARY");
        TEXT = new Attribute("TEXT");

        attributes = new ArrayList<Attribute>();
        classVal = new ArrayList<String>();

        classVal.add("Arts-and-Entertainment");
        classVal.add("Cars-and-Other-Vehicles");
        classVal.add("Computers-and-Electronics");        
        classVal.add("Education-and-Communications");
        classVal.add("Family-Life");
        classVal.add("Finance-and-Business");
        classVal.add("Food-and-Entertaining");
        classVal.add("Health");
        classVal.add("Hobbies-and-Crafts");
        classVal.add("Holidays-and-Traditions");
        classVal.add("Home-and-Garden");
        classVal.add("Personal-Care-and-Style");
        classVal.add("Pets-and-Animals");
        classVal.add("Philosophy-and-Religion");        
        classVal.add("Relationships");
        classVal.add("Sports-and-Fitness");
        classVal.add("Travel");
        classVal.add("Work-World");
        classVal.add("Youth");

        attributes.add(TITLE);
        attributes.add(SUMMARY);
        attributes.add(TEXT);


        attributes.add(new Attribute("class", classVal));
        dataRaw = new Instances("TestInstances", attributes, 0);
        
        // Select the Classificator Attribute
        dataRaw.setClassIndex(dataRaw.numAttributes()- 1);
    }

    
    public Instances createInstance(Instances data) {
        dataRaw.clear();
        dataRaw.add(data.instance(0));
        
        
        
//        Instance data;
//        data = new DenseInstance(3);
//        
//        data.setValue(attributes.get(1), TITLE);        
//        data.setValue(attributes.get(2), SUMMARY);
//        data.setValue(attributes.get(3), TEXT);
//         
//        dataRaw.add(data);
//
//
//        dataRaw.clear();
//        
//        String[] values = new String[]{TITLE, SUMMARY, TEXT};
//        dataRaw.add(new AbstractInstance(values));


//            Instance instance = new DenseInstance(1);
//            instance.setValue(1, SUMMARY);            
//
//            dataRaw.add(instance);
        
        return dataRaw;
     }


    public String classifiy(Instances insts, String path) {
        String result = "Not classified!";
        Classifier cls = null;
        try {
            cls = (RandomForest) SerializationHelper.read(path);
            // System.out.println("Class predicted: " + cls);
//            dataRaw.instance(0).setClassValue(cls.classifyInstance(dataRaw.instance(0)));
//            double predicted;
//            System.out.println("Class predicted: " + dataRaw.instance(0).toString());
//            predicted = dataRaw.instance(0).classValue();
            result = classVal.get((int) cls.classifyInstance(insts.firstInstance()));

           // result = classVal.get((int) cls.classifyInstance(insts.firstInstance()));
            // double pred = cls.classifyInstance(dataRaw.instance(0));
            System.out.println("Class predicted: " + result);

        } catch (Exception ex) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    public Instances getInstance() {
        return dataRaw;
    }
    

}
