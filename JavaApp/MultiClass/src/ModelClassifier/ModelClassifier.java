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
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

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

    public Instances createInstance(String TITLE, String SUMMARY, String TEXT){
        FastVector      atts;
        FastVector      attVals;
        Instances       data;
        double[]        vals;
        int             i;

        // 1. set up attributes
        atts = new FastVector();
        // - String
        atts.addElement(new Attribute("TITLE", (FastVector) null));
        atts.addElement(new Attribute("SUMMARY", (FastVector) null));
        atts.addElement(new Attribute("TEXT", (FastVector) null));
        // - Nominal
        attVals = new FastVector();
        attVals.addElement("Arts-and-Entertainment");
        attVals.addElement("Cars-and-Other-Vehicles");
        attVals.addElement("Computers-and-Electronics");        
        attVals.addElement("Education-and-Communications");
        attVals.addElement("Family-Life");
        attVals.addElement("Finance-and-Business");
        attVals.addElement("Food-and-Entertaining");
        attVals.addElement("Health");
        attVals.addElement("Hobbies-and-Crafts");
        attVals.addElement("Holidays-and-Traditions");
        attVals.addElement("Home-and-Garden");
        attVals.addElement("Personal-Care-and-Style");
        attVals.addElement("Pets-and-Animals");
        attVals.addElement("Philosophy-and-Religion");        
        attVals.addElement("Relationships");
        attVals.addElement("Sports-and-Fitness");
        attVals.addElement("Travel");
        attVals.addElement("Work-World");
        attVals.addElement("Youth");
        atts.addElement(new Attribute("CATEGORY", attVals));
        
        // 2. create Instances object
        data = new Instances("OneCategorie-weka.filters.unsupervised.attribute.NominalToString-Cfirst-3", atts, 0);

        // 3. fill with data
        // first instance
        vals = new double[data.numAttributes()];
        // - String
        vals[0] = data.attribute(0).addStringValue(TITLE);
        vals[1] = data.attribute(1).addStringValue(SUMMARY);
        vals[2] = data.attribute(2).addStringValue(TEXT);

        
        //vals[3] = attVals.indexOf("Youth");
        
        // add
        data.add(new DenseInstance(1.0, vals));

        // 4. output data
        return data;
    }
    
    /*public Instances createInstance(Instances data) {
        dataRaw.clear();
        dataRaw.add(data.instance(0));
        
        return dataRaw;
     }*/


    public String classifiy(Instances insts, String path) {
        String result = "Not classified!";
        Classifier cls = null;
        try {
            cls = (SMO) SerializationHelper.read(path);
            result = classVal.get((int) cls.classifyInstance(insts.firstInstance()));

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
