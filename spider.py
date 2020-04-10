from pprint import pprint

import pandas as pd
import csv
import re
import requests
from bs4 import BeautifulSoup



def getText(soup):
    tex=soup.find_all("div", {"class": "step"})
    tex= [it.getText() for it in tex]
    tex= [it.lower() for it in tex]

    tex= [re.sub("\\w{0, 2}\w", " ", it) for it in tex]

    # tex= [re.sub("\\b\\w{0,2}\\b|[^a-zA-Z ]", " ", it) for it in tex]
    #tex=[re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(it)) for it in tex]



 #   description = re.sub("([^a-zA-Z\\s+\\w]|\\s+)", " ", str(description))

    return tex
def parsePage(url):

    html_text = requests.get(url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    title=soup.find("meta", property="og:title").get("content")
    summary=soup.find("div", {"id": "mf-section-0"}).getText().lower()
    text=getText(soup)
    return [title,summary, text]

if __name__ == '__main__':
    category="Sports-and-Fitness"
    result=parsePage("https://www.wikihow.com/Teach-Kids-To-Run-Faster")
    print(result[2])

