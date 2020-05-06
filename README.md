# parser

## Intro

Parser made as prelimary work for the final project for Data Mining class at the University of Pisa, carried out by Marsha Gomez and I.

The aim is to build a multi-label classifier of Wikihow pages. A Wikihow page pertains to one and only one of 19 macro-categories. Right now resources are labeled by hand by the creator of the resource, but our aim is to build an automatic tool for the task, based on the article's text and summary. 

## Project files
**link_harvester.py**
The 19 macrocategories of WikiHow are hard-coded. Under discussion whether to automatize this.
```
For each category, the internal path of the first sublayer of categories is saved.
Eact NAME_CAT.txt will contain strings like /Category:SUB_CAT name.
TODO : check no repetition
```

**spider.py*** - 
```
for each string in NAME_CAT.txt
  the link "http://www.wikihow.com/string" is visited
  title is taken
  summary is taken and polished
  text is taken and poolished
  TODO: everything is saved in a csv file
```
## External libraries used so far

 * request is used to handle the connection phase and download the required pages.
 * BeautifulSoup is used to pull data from the HTML file
 * npl  is employed  to clean, stem and tokenize the contents taken from the html code.

