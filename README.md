<h1 align=center>PGM STAT TRACKER</h1>
<p align=center>This simple plugin allows you to track your PGM stats and send them to a MySQL database!</p>

<h2>Overview</h2>
This plug-in was made after I had played PGM with some friends who wanted to compare stats like most kills and KD ratio. There wasn't a good way to track these stats and in order to fulfill that want, I created this!

<h2>Dependencies</h2>
<li><a href=https://github.com/PGMDev/PGM>PGM</a></li>
<li><a href=https://www.mysql.com>MySQL</a></li>
<p></p>
This plugin is meant to work along with PGM, sending the stats to a MySQL database.

<h2>Usage</h2>
This will generate a "config.yml" file to insert your MySQL information in order to connect to your database.
<p></p>
The Default settings are:

```
database:
    ip: 'localhost'
    port: '3306'
    dbName: 'pgmst'
    user: 'root'
    password: ''
```
