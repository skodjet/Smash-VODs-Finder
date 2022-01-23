# Smash-VODs-Finder

A program that extracts video titles and links of Super Smash Bros. Ultimate tournament matches from text files with java swing as a GUI.


The reason for this project's existence is that it is prohibitively difficult to find Super Smash Bros. Ultimate matches of specific characters and players, even with websites and tools that claim to cast a wide net for all tournament sets. Early tournament and less-viewed sets are not easy to find on Youtube or elsewhere. That is why I have decided to create a program that will find those lesser-known matches.

Currently the program extracts videos from a database of all S tier tournaments matches uploaded to Youtube since 2019 and as of January 23rd, 2022. For a list of S tier tournaments, consult this website: https://en.wikipedia.org/wiki/List_of_major_Super_Smash_Bros._Ultimate_tournaments

Instructions for use:
1. Compile and run VideoCompiler.java. This will open the main window where you can query for tournament matches containing a character. Capitalization and spaces matter when searching.
2. An output window will open with the matching tournament sets. To open the video of a match in your browser, simply click on the cell containing the URL of the desired match.
3. The program supports closing the output window and entering another query.


Planned Upcoming Features:
1. Ability to search and filter by tournament, player, and multiple characters.
2. Cleaner and better looking UI.
3. More information regarding each video such as tournament stage and date.
