
import ModelClassifier.ModelClassifier;
import ModelClassifier.ModelGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Attribute;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.core.stemmers.LovinsStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.stopwords.WordsFromFile;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.unsupervised.attribute.Normalize;

/**
 * This is a classifier for wikihow dataset  
 * @author Daria Maggi and Marsha Gomez 
 * Data Mining and Machine Learning University of Pisa
 * Github link: https://github.com/dariamaggi/parser
 */
public class Training {

    public static final String DATASETPATH = "D:\\GitHub\\parser\\data\\cleaned\\AllCategories.arff";
    public static final String DATASETPATHONE = "D:\\GitHub\\parser\\data\\cleaned\\OneCategorie.arff";
    public static final String MODELPATH = "D:\\GitHub\\parser\\JavaApp\\MultiClass\\src\\data\\RANDOMFOREST.bin";

    public static void main(String[] args) throws Exception {
        int minTermFreq = 2;
        boolean useStemmer = false;
        boolean useIdf = true;
        int maxGrams = 2;
        int minGrams = 2;
        Evaluation evalsummary = null;
        
        ModelGenerator mg = new ModelGenerator();

        Instances dataset = mg.loadDataset(DATASETPATH);
        Instances data = mg.loadDataset(DATASETPATHONE);



        // -- Filter filter = new Normalize();
        StringToWordVector filter = new StringToWordVector();
        filter.setWordsToKeep(1000000);

        // divide dataset to train dataset 80% and test dataset 20%
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));// if you comment this line the accuracy of the model will be droped from 96.6% to 80%
        
        System.out.print("\n ---- SET CONFIGURATION ----");

        if(useIdf){
            filter.setIDFTransform(true);
        }
        filter.setTFTransform(true);
        filter.setLowerCaseTokens(true);
        filter.setOutputWordCounts(true);
        filter.setMinTermFreq(minTermFreq);
        filter.setNormalizeDocLength(new SelectedTag(StringToWordVector.FILTER_NORMALIZE_ALL,StringToWordVector.TAGS_FILTER));
        NGramTokenizer t = new NGramTokenizer();
        t.setNGramMaxSize(maxGrams);
        t.setNGramMinSize(minGrams);    
        filter.setTokenizer(t); 
        
        // Use Stemmer
        if (useStemmer){
            Stemmer s = new /*Iterated*/LovinsStemmer();
            filter.setStemmer(s);
            
            // Stop Words
            WordsFromFile stopwords = new WordsFromFile();
            stopwords.setStopwords(new File("D:\\GitHub\\parser\\JavaApp\\MultiClass\\src\\data\\stopwords.txt"));
            filter.setStopwordsHandler(stopwords);
        }
        
        //Normalize dataset
        filter.setInputFormat(dataset);
        filter.setInputFormat(data);
        
        Instances datasetnor = Filter.useFilter(dataset, filter);
        Instances dataFilter = Filter.useFilter(data, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);
        
        
//        System.out.print("\n ---- SMO MODEL ----");
//        // build classifier with train dataset             
//        SMO smo = (SMO) mg.buildClassifier(traindataset, "SMO");
//
//        // Evaluate classifier with test dataset
//        evalsummary = mg.evaluateModel(smo, traindataset, testdataset);
//        System.out.println("Evaluation: " + evalsummary.toSummaryString("", true));
//
//        //Save model 
//        mg.saveModel(smo, MODELPATH);
//        System.out.print("\n ---- SAVE MODEL ----");
//       
        
        //classifiy a single instance 
//        


      
        
        ModelClassifier cls = new ModelClassifier();
        
        
//        String classname = cls.classifiy(Filter.useFilter(cls.createInstance(
//                "support art", 
//                "art allow peopl public express fun creativ meaning way art music theatr provid countless creator ",
//                "musician entertain livelihood without usa gov elect offici consid call legisl phone speak"), filter), MODELPATH);
//        System.out.println("\n The class name for the instance TEST is: " +classname);
        
 
        
        
        


        String classname = cls.classifiy(cls.createInstance(dataFilter), MODELPATH);
        System.out.println("\n The class name for the instance TEST is: " +classname);
        
        
        
        
        System.out.print("\n ---- FINISH ----");

    }

}
