<h1 align=center>PGM STAT TRACKER</h1>
<p align=center>This simple plugin allows you to track your PGM stats and send them to a MySQL database!</p>

##Overview
This plugin was made after me and my friends played some matches of PGM and wanted to compete for who have the most kills and better KD (like the old Overcast Network website) but there wasn't a way to track our stats, so to learn java and fill our necessities I made this!
##Dependencies
<li><a href=https://github.com/PGMDev/PGM>PGM</a></li>
<li><a href=https://www.mysql.com>MySQL</a></li>

This plugin is meant to work along with PGM, sending the stats to a MySQL database.

##Usage
The plugin will generate a **"config.yml"** file, where you can put your MySQL info for connecting to your database.

The Default settings are:
```
Ip: 'localhost'
Port: '3306'
DBName: 'pgmst'
User: 'root'
Password: ''
```