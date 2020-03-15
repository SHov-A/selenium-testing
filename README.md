### Steps for Selenium Grid
The root piece of selenium grid is selenium server.That is executable jar file which is in classpath of project.
You should run that jar file for hub and also for nodes. 
<ul>
<li><b>for hub</b>: java -jar selenium-server-standalone-3.141.59.jar -role hub</li>
<li><b>for node</b>: java -Dwebdriver.chrome.driver="your path to chrome driver" -jar selenium-server-standalone-3.141.59.jar -role node -hub http://ip.of.hub:4444/grid/register/</li>
</ul>
After these steps node should register to <code>http://ip.of.hub:4444/grid/register</code><br>
For more info see selenium grid official <a href="https://www.selenium.dev/documentation/en/grid/">docs.</a>