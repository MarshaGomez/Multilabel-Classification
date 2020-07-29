package ModelClassifier;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * This is a classifier for wikihow dataset  
 * @author Daria Maggi and Marsha Gomez 
 * Data Mining and Machine Learning University of Pisa
 * Github link: https://github.com/dariamaggi/parser
 */
public class ModelGenerator {

    public Instances loadDataset(String path) {
        System.out.println("\n --- MODEL GENERATOR -----");
        Instances dataset = null;
        try {
            dataset = DataSource.read(path);
            if (dataset.classIndex() == -1) {
                dataset.setClassIndex(dataset.numAttributes() - 1);
            }
        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dataset;
    }

    public Classifier buildSMO(Instances traindataset){
        SMO m = new SMO();
        try {
            m.buildClassifier(traindataset);

        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
    public Classifier buildRandomForest(Instances traindataset){
        RandomForest m = new RandomForest();
        try {
            m.buildClassifier(traindataset);

        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    public Classifier buildNaiveBayes(Instances traindataset){
        NaiveBayes m = new NaiveBayes();
        try {
            m.buildClassifier(traindataset);

        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
    
        public Classifier buildJ48(Instances traindataset){
        J48 m = new J48();
        try {
            m.buildClassifier(traindataset);

        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }
        
    public Classifier buildClassifier(Instances traindataset, String type) {
        Classifier m;
        // Depend on the Classifier type, build the Classifier
        switch(type) {
            case "SMO":
                m = new SMO();
                m = buildSMO(traindataset);
                break;
            case "J48":
                m = new J48();
                m = buildJ48(traindataset);
                break;
            case "RANDOMFOREST":
                m = new RandomForest();
                m = buildRandomForest(traindataset);
                break;
            case "NAIVEBAYES":
                m = new NaiveBayes();
                m = buildNaiveBayes(traindataset);
                break;
            default:
                m = new SMO();
                m = buildSMO(traindataset);
        }
        return m;
    }

    public Evaluation evaluateModel(Classifier model, Instances traindataset, Instances testdataset) {
        Evaluation eval = null;
        try {
            // Evaluate classifier with test dataset
            eval = new Evaluation(traindataset);
            eval.evaluateModel(model, testdataset);
            eval.crossValidateModel(model, traindataset, 10, new Random(1));
            System.out.println("--- EVALUATE MODEL-----");
        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eval;
    }

    public void saveModel(Classifier model, String modelpath) {
        try {
            SerializationHelper.write(modelpath, model);
        } catch (Exception ex) {
            Logger.getLogger(ModelGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
