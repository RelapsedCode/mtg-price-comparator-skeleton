## The problem:
There is a great platform which gives you access to number of sellers around the world, The problem is that you cannot filter markets of a specific sellers. mtg-price-comparator gives you the opportunity to search a list of cards only in the specified sellers markets.

chromedriver and chromium are not bundled, but you can use your own or selenium server. <br>
<br>
Configuration: <br>
Add credential to: src/main/java/configs/data.properties

### How to use:
Option 1: <br>
Search for a specific card in the main search tab, without filtering sellers. Adds the cheapest card. Proceeds with the next one. When all cards are added it goes to the shopping card and outputs the total amount to be paid.

Option 2: <br>
Navigates to the first seller from the seller list. Serach for all the cards provided in the csv. The procedure is repeated for each seller. At the end goes to the shopping card and print out the different amount from the different sellers. 

Option 3: <br>
Saves the gathered on formation from Option 2 to a csv file.

Note: Right-click on the resource folder -> mark directory as Resource. Otherwise, the logger will not get the properties file thus will not lof info messages in the console.
