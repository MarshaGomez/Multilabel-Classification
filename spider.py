from pprint import pprint

import pandas as pd
import csv
import re
import requests
from bs4 import BeautifulSoup

from link_harvester import category

def getText(soup):
    tex=soup.find_all("div", {"class": "step"})
    tex=[it.getText() for it in tex]
    tex=[it.lower() for it in tex]
    tex=[re.sub("\\n\\b\\w{0,2}\\b|[^a-zA-Z ]", "", it) for it in tex]
    return tex
    # tex= [re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", " ", it) for it in tex]
    #tex=[re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(it)) for it in tex]



 #   description = re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(description))

def parsePage(url):

    html_text = requests.get(url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    title=soup.find("meta", property="og:title").get("content")
    summary=soup.find("div", {"id": "mf-section-0"}).getText().lower()
    text=getText(soup)
    return [title,summary, text]
def parseCat(url):
    html_text = requests.get(url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    li = soup.find_all("div", {"class": "responsive_thumb"})
    links = [div.find('a')['href'] for div in li]
    [print(it) for it in links]


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