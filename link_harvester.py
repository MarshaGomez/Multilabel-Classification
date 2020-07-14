category = ["Arts-and-Entertainment", "Cars-%26-Other-Vehicles", "Travel", "Work-World", "Relationships", "Philosophy-and-Religion", "Family-Life", "Finance-and-Business", "Computers-and-Electronics", "Food-and-Entertaining", "Home-and-Garden",  "Youth", "Education-and-Communications", "Health", "Hobbies-and-Crafts","Personal-Care-and-Style", "Sports-and-Fitness", "Holidays-and-Traditions", "Pets-and-Animals"]

import requests
from bs4 import BeautifulSoup
import re
if __name__ == '__main__':
    for cat in category:
        print(cat)
        html_text = requests.get("https://www.wikihow.com/Category:" + cat).text
        soup = BeautifulSoup(html_text, 'html.parser')
        links = [div.find_all('a') for div in soup.find_all("div", {"id": "subcats"})][0]#find subcategories
        links=[it.get("href") for it in links]
        links[:] = [x for x in links if x]
        #now links contains the /category:subcat name
        [print(i) for i in links]
        with open('./texts/'+cat + '.txt', 'a') as f:
            cat=str("/Category:"+cat)
            f.write("%s\n" % cat)
            [f.write("%s\n" % item) for item in links]
            f.close()