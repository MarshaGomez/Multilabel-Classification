from pprint import pprint

import pandas as pd
import csv
import re
import requests
from bs4 import BeautifulSoup



def parsePage(url):

    html_text = requests.get(url).text
    soup=BeautifulSoup(html_text, 'html.parser')
    #getSummary
    return [(soup.find("meta", property="og:title").get("content")), soup.find("div", {"id": "mf-section-0"}).getText()]

if __name__ == '__main__':
    category="Sports-and-Fitness"
    result=parsePage("https://www.wikihow.com/Teach-Kids-To-Run-Faster")

    f = open('raw_data.csv', 'w')
    with f:
        fnames = ['title', 'summary', 'category']
        writer = csv.DictWriter(f, fieldnames=fnames)

        writer.writeheader()
        writer.writerow({'title': result[0], 'summary': result[1], 'category':category})

