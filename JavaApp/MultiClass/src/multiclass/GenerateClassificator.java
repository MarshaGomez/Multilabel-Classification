
package MultiClass;

import ModelClassifier.ModelGenerator;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.core.Debug;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.stemmers.LovinsStemmer;
import weka.core.stemmers.Stemmer;
import weka.core.stopwords.WordsFromFile;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * This is a classifier for wikihow dataset  
 * @author Daria Maggi and Marsha Gomez 
 * Data Mining and Machine Learning University of Pisa
 * Github link: https://github.com/dariamaggi/parser
 */

public class GenerateClassificator extends javax.swing.JFrame {
    
    // Setup global configurations

    int minTermFreq = 2;
    boolean useStemmer = false;
    boolean useIdf = true;
    int maxGrams = 2;
    int minGrams = 2;
    String stopWords = "D:\\GitHub\\parser\\JavaApp\\MultiClass\\src\\data\\stopwords.txt";
    private ImageIcon image1;


    Evaluation evalsummary = null;
    /**
     * Creates new form GenerateClassificator
     */
    public GenerateClassificator() {
        initComponents();
        jPanel1.setBackground(Color.WHITE);
        
        image1 = new ImageIcon(getClass().getResource("..\\img\\header_logo.png"));
        
        Image image = image1.getImage(); // transform it 
        Image newimg = image.getScaledInstance(150, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        image1 = new ImageIcon(newimg);  // transform it back
        jLabel5.setIcon(image1);
        
        jButton1.setBackground(Color.WHITE);
        jButton2.setBackground(Color.WHITE);
        jButton3.setBackground(Color.WHITE);
        Back.setBackground(Color.WHITE);
        jComboBoxCategory1.setBackground(Color.WHITE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCategory1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Back = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Generate Classificator");

        jLabel2.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel2.setText("Classifier");

        jComboBoxCategory1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jComboBoxCategory1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SMO", "RANDOMFOREST", "J48", "NAIVEBAYES" }));
        jComboBoxCategory1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategory1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel3.setText("Open ARFF");

        jTextField1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N

        jButton2.setBackground(new java.awt.Color(120, 153, 71));
        jButton2.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jButton2.setText("Choose");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jLabel4.setText("Save MODEL");

        jTextField2.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N

        jButton3.setBackground(new java.awt.Color(120, 153, 71));
        jButton3.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jButton3.setText("Choose");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(120, 153, 71));
        jButton1.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        jButton1.setText("Generate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        Back.setBackground(new java.awt.Color(120, 153, 71));
        Back.setFont(new java.awt.Font("Microsoft JhengHei", 0, 14)); // NOI18N
        Back.setText("< Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Microsoft YaHei", 0, 30)); // NOI18N
        jLabel5.setText("Automatic Classifier");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Back))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addGap(38, 38, 38)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jComboBoxCategory1, 0, 495, Short.MAX_VALUE)
                                                .addComponent(jTextField1)))
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategory1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jLabel4))
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Back)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCategory1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategory1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCategory1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String message = "";
        
        if (!"".equals(jTextField1.getText()) && !"".equals(jTextField2.getText())) {
            
            try {
                Date date = new Date();
                message = message + "\n -- PROCESS INIT \n"+ dateFormat.format(date);
                jTextArea1.setText(message);
                String DATASETPATH = jTextField1.getText();
                String MODELPATH = jTextField2.getText();
                

                ModelGenerator mg = new ModelGenerator();
                
                message = message + "\n Model Generetor done";
                jTextArea1.setText(message);
                Instances dataset = mg.loadDataset(DATASETPATH);

                // -- Filter filter = new Normalize();
                StringToWordVector filter = new StringToWordVector();
                filter.setWordsToKeep(1000000);

                // divide dataset to train dataset 80% and test dataset 20%
                int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
                int testSize = dataset.numInstances() - trainSize;

                dataset.randomize(new Debug.Random(1));// if you comment this line the accuracy of the model will be droped from 96.6% to 80%
                
                message = message + "\n Setting Filter";
                jTextArea1.setText(message);

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
                    stopwords.setStopwords(new File(stopWords));
                    filter.setStopwordsHandler(stopwords);
                }

                //Normalize dataset
                filter.setInputFormat(dataset);
                Instances datasetnor = Filter.useFilter(dataset, filter);
                
                message = message + "\n Training Data Set done" + "\n Test Data Set done";
                jTextArea1.setText(message);
                Instances traindataset = new Instances(datasetnor, 0, trainSize);
                Instances testdataset = new Instances(datasetnor, trainSize, testSize);
                
                String option = jComboBoxCategory1.getSelectedItem().toString();

                switch(option) {
                    case "SMO":
                        // build classifier with train dataset             
                        SMO smo = (SMO) mg.buildClassifier(traindataset, option);

                        // Evaluate classifier with test dataset
                        evalsummary = mg.evaluateModel(smo, traindataset, testdataset);

                        //Save model 
                        mg.saveModel(smo, MODELPATH+"\\"+option+".bin");
                        break;
                    case "J48":
                        // build classifier with train dataset             
                        J48 j48 = (J48) mg.buildClassifier(traindataset, option);

                        // Evaluate classifier with test dataset
                        evalsummary = mg.evaluateModel(j48, traindataset, testdataset);

                        //Save model 
                        mg.saveModel(j48, MODELPATH+"\\"+option+".bin");
                        break;
                    case "RANDOMFOREST":
                        // build classifier with train dataset             
                        RandomForest randomForest = (RandomForest) mg.buildClassifier(traindataset, option);

                        // Evaluate classifier with test dataset
                        evalsummary = mg.evaluateModel(randomForest, traindataset, testdataset);

                        //Save model 
                        mg.saveModel(randomForest, MODELPATH+"\\"+option+".bin");
                        break;
                    case "NAIVEBAYES":
                        // build classifier with train dataset             
                        NaiveBayes naiveBayes = (NaiveBayes) mg.buildClassifier(traindataset, option);

                        // Evaluate classifier with test dataset
                        evalsummary = mg.evaluateModel(naiveBayes, traindataset, testdataset);

                        //Save model 
                        mg.saveModel(naiveBayes, MODELPATH+"\\"+option+".bin");
                        break;
                    default:
                        jTextArea1.setText("\n ERROR SELECTING THE CLASSIFICATOR");
                        break;
                }
                
                message = message + "\n Evaluation: " + evalsummary.toSummaryString("", true);
                jTextArea1.setText(message);
                
                message = message + "\n Matrix Evaluation Summary: \n"+ evalsummary.toMatrixString();
                
                date = new Date();
                message = message + "\n Model.bin save on Path: "+MODELPATH+"\n";
                jTextArea1.setText(message);
                message = message + "\n -- PROCESS END "+ dateFormat.format(date);
                jTextArea1.setText(message);
            } catch (Exception ex) {
                System.err.println("Error Modeling: " + ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("arff", "arff", "csv");
        fc.setFileFilter(filter);
        
        int selection = fc.showOpenDialog(this);

        if (selection == JFileChooser.APPROVE_OPTION) {
            File filePath = fc.getSelectedFile();
            
            jTextField1.setText(filePath.getAbsolutePath());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new java.io.File("."));
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int selection = fc.showOpenDialog(this);
        
        if (selection == JFileChooser.APPROVE_OPTION) {
            jTextField2.setText(fc.getCurrentDirectory().toString());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        new Main().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GenerateClassificator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateClassificator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateClassificator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateClassificator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateClassificator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxCategory1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
