[![Build Status](https://travis-ci.org/brysalazar12/BatchRun.svg)](https://travis-ci.org/brysalazar12/BatchRun)

# BatchRun
Execute multiple program define in config

**Usage**  
**run:** java -jar BatchRun.jar  
it will create config file program.dat to your current directory.

**run:** java -jar BatchRun.jar /path/to/myconfig.txt  
it will read the config file /path/to/myconfig.txt and execute the program or command in this config

Sample Config
```
# '#' is comment. List the program here you want to batch run separated by new line.  
C:\Program Files (x86)\Google\Chrome\Application\chrome.exe http:www.google.com  
C:\Program Files (x86)\Mozilla Firefox\firefox.exe http://www.facebook.com/ http://www.yahoo.com  
C:\Program Files\NetBeans 8.0.2\bin\netbeans64.exe --console suppress  
```

**Note**
If you want to comment the command or program use **#**
