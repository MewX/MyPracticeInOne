This tool depends on a mysql connector, to run it:

```
java -classpath ".:mysql-connector-java-5.1.45-bin.jar" CrawlDouban | tee -a out.outjava -classpath ".:mysql-connector-java-5.1.45-bin.jar" CrawlDouban | tee -a out.out
```

Also, to pack all data that we've fetched, use this command:

```
7za a -r -scsUTF-8 dbdata.1223.bak.7z dbdata/
```

----

By the way, the program uses a tor ip switcher: https://github.com/alex-miller-0/Tor_Crawler
