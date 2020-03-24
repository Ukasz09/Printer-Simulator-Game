# 📠 Printer Simulator/Game [![License](https://img.shields.io/badge/licence-MIT-blue)](https://choosealicense.com/licenses/mit/) [![Contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)](https://github.com/Ukasz09/Printer-Simulator-Game) [![Status](https://img.shields.io/badge/status-finished-brightgreen)](https://github.com/Ukasz09/Printer-Simulator-Game)

<br/>
💻 All resolutions support <br/>
🔉 Sounds and music <br/>

## Gameplay
<p align="center"><img width=95% src="https://raw.githubusercontent.com/Ukasz09/PrinterSimulator/master/readmeImages/gameplay1.gif"></p>
<p align="center"><img width=95% src="https://raw.githubusercontent.com/Ukasz09/PrinterSimulator/master/readmeImages/gameplay2.gif"></p>
<br/>

## Screenshots 

![alt text](https://raw.githubusercontent.com/Ukasz09/PrinterSimulator/master/readmeImages/1.png)
![alt text](https://raw.githubusercontent.com/Ukasz09/PrinterSimulator/master/readmeImages/2.png)
![alt text](https://raw.githubusercontent.com/Ukasz09/PrinterSimulator/master/readmeImages/3.png)

## How to use it
If there is a problem with running, try to open it by console with command:
- Windows
```cmd
java -jar PrinterSimulator2.jar
```

- Linux 
```bash
java -jar PrinterSimulator2.jar
```
<br/>
If you will see errors about not having error like this: <br/>
```bash
java.lang.NoClassDefFoundError: javafx/application/Application
```
<br/>
it means that you don't have javafx libraries and you need to follow this steps: <br/>

- 1) Download javaFx libraries for linux [javafx-oracle.com](https://www.oracle.com/java/technologies/java-archive-javafx-downloads.html#javafx_sdk-1.3.1-oth-JPR)
- 2) Unpack files into your java library destination, for example: `/usr/lib/jvm/java-14-oracle`
- 3) Run script made by myself for you, with java library destination folder

<br/>
```bash
./runGame.sh /usr/lib/jvm/java-14-oracle
```
## Game control
By `mouse` - intuitively, by clicking on individual elements

## Software design stuff
**Used Designs Patterns:**
<br/><br/>
✅ Builder <br/>
✅ Singleton <br/>
✅ Strategy <br/>
✅ Decorator <br/>
✅ Observer <br/>

**Code overview:**
<br/><br/>
✔️ 90 classes (including enums and interfaces) <br/>
✔️ over 4200 lines of code  <br/>

## Acknowledgements
Thanks to one of my teacher, who inspired me to this app 
___
## 📫 Contact 
Created by <br/>
<a href="https://github.com/Ukasz09" target="_blank"><img src="https://avatars0.githubusercontent.com/u/44710226?s=460&v=4"  width="100px;"></a>
<br/> gajerski.lukasz@gmail.com - feel free to contact me! ✊
