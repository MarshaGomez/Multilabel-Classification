package ModelClassifier;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
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
        dataRaw.setClassIndex(dataRaw.numAttributes()- 1);
    }

    
    // -- public Instances createInstance(String TITLE, String SUMMARY, String TEXT, double result) {
    // --     dataRaw.clear();
    // --     String[] instanceValue1 = new String[]{TITLE, SUMMARY, TEXT};
    // --     dataRaw.add(new DenseInstance(1.0, instanceValue1));
    // --     return dataRaw;
    // -- }


    public String classifiy(Instances insts, String path) {
        String result = "Not classified!!";
        Classifier cls = null;
        try {
            cls = (SMO) SerializationHelper.read(path);
            result = classVal.get((int) cls.classifyInstance(insts.firstInstance()));
        } catch (Exception ex) {
            Logger.getLogger(ModelClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    public Instances getInstance() {
        return dataRaw;
    }
    

}
