category = ["Arts-and-Entertainment", "Family-Life", "Hobbies-and-Crafts", "Pets-and-Animals", "Travel", "Cars-%26-Other-Vehicles", "Finance-and-Business", "Holidays-and-Traditions", "Philosophy-and-Religion",
                "Work-World", "Computers-and-Electronics", "Food-and-Entertaining", "Home-and-Garden", "Relationships", "Youth", "Education-and-Communications", "Health", "Personal-Care-and-Style",
                "Sports-and-Fitness"]

import requests
from bs4 import BeautifulSoup
import re
def art():
    it="Arts-and-Entertainment"
    html_text = requests.get("https://www.wikihow.com/Category:Arts-and-Entertainment").text
    soup = BeautifulSoup(html_text, 'html.parser')
    li=soup.find_all("div", {"class": "responsive_thumb"})
    links=[div.find('a')['href'] for div in li]
    with open('arts-and-entertrainment.txt', 'w') as f:
        for item in links:
            f.write("%s\n" % item[23:])
def getCat(it):
    html_text = requests.get("https://www.wikihow.com/Category:"+it).text
    soup = BeautifulSoup(html_text, 'html.parser')
    li = soup.find_all("div", {"class": "cat_link"})
    print(li)

    cats = ["Collecting-Books", "Comics", "EBook-Readers",
            "Goodreads", "Google-Books", "Libraries", "Purchasing-Books",
            "Recipe-Books-and-Cookbooks", "Reference-Texts", "Selling-Books"]
    for item in cats:
        html_text = requests.get("https://www.wikihow.com/Category:" + item).text
        soup = BeautifulSoup(html_text, 'html.parser')
        li = soup.find_all("div", {"class": "responsive_thumb"})
        links = [div.find('a')['href'] for div in li]
        with open('arts-and-entertrainment.txt', 'a') as f:
            [f.write("%s\n" % item[23:]) for item in links]
#trova sub cat
    it = "Family-Life"
    html_text = requests.get("https://www.wikihow.com/Category:" + it).text
    soup = BeautifulSoup(html_text, 'html.parser')
    links = [div.find_all('a') for div in soup.find_all("div", {"id": "subcats"})][0]
    [print(it.get("href")) for it in links]
    for item in links:
        html_text = requests.get("https://www.wikihow.com" + item).text
        soup = BeautifulSoup(html_text, 'html.parser')
        li = soup.find_all("div", {"class": "responsive_thumb"})
        temp = [div.find('a')['href'] for div in li]
        temp[:] = [x for x in temp if x]
        links.extend(temp)

        with open(cat + '.txt', 'a') as f:
            [f.write("%s\n" % item) for item in links]
        # set file name
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