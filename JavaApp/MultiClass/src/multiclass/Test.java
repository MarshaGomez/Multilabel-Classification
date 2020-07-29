/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiclass;


import weka.classifiers.Classifier;
import weka.clusterers.Clusterer;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;


/**
 * @author Gowtham Girithar Srirangasamy
 *
 */
public class Test {
	/** file names are defined */
	public static final String TRAINING_DATA_SET_FILENAME = "Train.arff";
	public static final String TESTING_DATA_SET_FILENAME = "Test.arff";
	public static final String PREDICTION_DATA_SET_FILENAME = "Test_1.arff";

	/**
	 * This method is to load the data set.
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Instances getDataSet(String fileName) throws Exception {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		StringToWordVector filter = new StringToWordVector();
		int classIdx = 1;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		/** load the traing data */
		 loader.setSource(Test.class.getResourceAsStream(fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		//loader.setFile(new File(fileName));
		Instances dataSet = loader.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		filter.setInputFormat(dataSet);
		dataSet = Filter.useFilter(dataSet, filter);
		return dataSet;
	}

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	public static void process() throws Exception {
            System.out.print(TESTING_DATA_SET_FILENAME);            
            System.out.print(TRAINING_DATA_SET_FILENAME);
            System.out.print(PREDICTION_DATA_SET_FILENAME);

		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		
                Instances predictingDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME);

                /** Classifier here is Linear Regression */
		Classifier classifier = new NaiveBayes();
                
		/** */
		classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
                
                
		Evaluation eval = new Evaluation(trainingDataSet);
		eval.evaluateModel(classifier, testingDataSet);
                
                
		/** Print the algorithm summary */
		System.out.println("** Naive Bayes Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		//System.out.println(classifier);
		
                for (int i = 0; i < predictingDataSet.numInstances(); i++) {
			System.out.println(predictingDataSet.instance(i));
			double index = classifier.classifyInstance(predictingDataSet.instance(i));
			String className = trainingDataSet.attribute(0).value((int) index);
			//System.out.println(className);
		}

	}
        
        /* public static void main(String[] args) {
            System.out.println("Init MAIN");
            try {
                System.out.println("Process Init");
                process();
            } catch (Exception e) {
                System.err.println("Error"+e);
            }
        } */
}
