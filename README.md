# Smash-VODs-Finder

UPDATE JANUARY 2023:
The program now pulls from VGBootCamp's videos on Youtube (https://www.youtube.com/@vgbootcamp) using the Youtube API. This gives a more comprehensive list of tournament sets, with about 20,000. However, the GUI does not work with the updated list of videos yet, as the SQL database is still under construction. Stay tuned for updates!


A program that extracts video titles and links of Super Smash Bros. Ultimate tournament matches from text files with java swing as a GUI.


The reason for this project's existence is that it is prohibitively difficult to find Super Smash Bros. Ultimate matches of specific characters and players, even with websites and tools that claim to cast a wide net for all tournament sets. Early tournament and less-viewed sets are not easy to find on Youtube or elsewhere. That is why I have decided to create a program that will find those lesser-known matches.

Currently the program extracts videos from a database of all S tier tournaments matches uploaded to Youtube since 2019 and as of January 23rd, 2022. For a list of S tier tournaments, consult this website: https://en.wikipedia.org/wiki/List_of_major_Super_Smash_Bros._Ultimate_tournaments

Instructions for use:
1. Compile and run VideoCompiler.java within SmashVODsFinder/src/VideoFinder/.  This will open the main window where you can query for tournament matches containing a character. Capitalization and spaces matter when searching.
2. An output window will open with the matching tournament sets. To open the video of a match in your browser, simply click on the cell containing the URL of the desired match.
3. The program supports closing the output window and entering another query.


Planned Upcoming Features:
1. Ability to search and filter by tournament, player, and multiple characters.
2. Cleaner and better looking UI.
3. More information regarding each video such as tournament stage and date.
4. Integration of JDBC and an SQL database.


Code Credit:

download.java is a modification of GeeksForGeeks' "download a webpage using java". (https://www.geeksforgeeks.org/download-web-page-using-java/)

Thank you to Dr. El-Sayed for her JDBC template from CS377 at Emory University!
