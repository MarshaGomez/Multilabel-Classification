import os
from mpl_toolkits.mplot3d import Axes3D
from sklearn.preprocessing import StandardScaler
import matplotlib.pyplot as plt # plotting
import numpy as np # linear algebra
import os # accessing directory structure
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)
import csv

def getData(path):
    files = []
    # r=root, d=directories, f = files
    for r, d, f in os.walk(path):
        for file in f:
            if '.csv' in file:
                files.append(os.path.join(r, file))

    for f in files:
        cleanCSV(f)
        print(f)

def cleanCSV(path):
    print(path)

def createCleanCSV(path):
    listCSV = os.listdir(path)
    csvfile = "./data/data/AllCategories.csv"
    csv_out = csv.writer(open(csvfile, 'w'), delimiter=',')
    count = 0
    for CSV in listCSV:
        print(CSV)
        csv_in = open(path+CSV)
        for line in csv_in:
            csv_out.writerow()
            count = count + 1
            print(CSV)
            print(line)
            if count >= 1:
                csv_in.close()
                break
            #csv_out.writerow(line[0], line[1], line[2], line[3])


path = './data/data/'
createCleanCSV(path)