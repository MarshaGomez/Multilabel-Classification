from pprint import pprint

import pandas as pd
import csv
import re
import requests
import nltk
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from nltk.stem.snowball import SnowballStemmer
from langdetect import detect
from bs4 import BeautifulSoup
from link_harvester import category


# Clean Description attribute. Apply Stopwords and Stem
def cleanDescription(description):
    description = description.getText().lower() 
    description = re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", " ", description)
    description = re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(description))

    if description != ' ' and description != '':
        lang = detect(description)

        if lang == 'en':
            stop_words = set(stopwords.words('english'))
            word_tokens = word_tokenize(description)

            filtered_sentence = []
            [filtered_sentence.append(w) for w in word_tokens if w not in stop_words ]
            

            stemmer = SnowballStemmer('english')
            stemSentence = ''
            for word in filtered_sentence:
                stem = stemmer.stem(word)
                stemSentence += stem
                stemSentence += ' '
            description = re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", " ", str(stemSentence))
            description = re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(description))
            return description
        else:
            return ''
    else:
        return ''

def getText(soup):
    tex=soup.find_all("div", {"class": "step"})
    [cleanDescription(it) for it in tex]
    
    return " ".join(tex)
    # tex= [re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", " ", it) for it in tex]
    #tex=[re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(it)) for it in tex]



 # ([^a-zA-Z\\s+\\w]|\\s+)  description = re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(description))

def parsePage(url):

    html_text = requests.get("https://www.wikihow.com"+url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    title=soup.find_all("a", {"href": "https://www.wikihow.com"+url})[1].getText()
    print(title)
    summary = soup.find("div", {"id": "mf-section-0"}).getText().lower()
    summary=re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", "", summary)
    summary=re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(summary))

    print(summary)
   # summary=soup.find("div", {"id": "mf-section-0"}).getText().lower().replace("[","").replace("]","").replace("x\nresearch source","")\
       # .replace("(","").replace(")","").replace(".","").replace(",","")
    text=getText(soup)
    print(text)
    #rows.append( [title,summary, text])

def manageUrls(links):
    print("TODO")
    rows=[]
    [parsePage(url) for url in links]
   # [print(url) for url in rows]
def parseCat(url):
    html_text = requests.get(url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    li = soup.find_all("div", {"class": "responsive_thumb"})
    links = [div.find('a')['href'][23:] for div in li]
    manageUrls(links)


if __name__ == '__main__':
    print("for cat in category:")
    print("open cat.txt")
    print("for each subcat, get page and save to cvs")
    cat=category[0]
    with open('./texts/' + cat + '.txt', 'r') as fp:
        line = fp.readline()
        cnt = 1
        while line:
            result=parseCat("https://www.wikihow.com"+line)
            line = fp.readline()
            cnt += 1
        fp.close()