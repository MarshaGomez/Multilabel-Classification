
import pandas as pd
import csv
import re
import requests
from bs4 import BeautifulSoup



if __name__ == '__main__':
    vgm_url = 'https://www.wikihow.com/Be-Talented-in-Multiple-Areas'
    html_text = requests.get(vgm_url).text
    soup = BeautifulSoup(html_text, 'html.parser')
    tec=soup.find_all("a", href=re.compile("Category:"))
    print((tec[0]["title"])[9:])
