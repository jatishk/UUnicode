###############################################################
#							      #
#			FIRST TIME USERS		      #
#							      #
#							      #
###############################################################



Below are the steps to install "Java SE Development Kit" on your operating systems.

1. To install Java: download the latest release from Oracle website. 
	
	Copy the URL and paste in the browser
	
	"http://www.oracle.com/technetwork/java/javase/downloads/index.html" 

	or

	"http://java.sun.com/javase/downloads/index.jsp"
	
	or

	"http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html" to download the Java Development Kit (JDK).


2. After pasting the given URL in browser. Click on the 'Download - Java Platform (JDK) 8u5'  and you will be taken to another screen.
	
     You must accept the Oracle Binary Code License Agreement for Java SE to download this software.
 	Click on Radio button "Accept License Agreement"

3. A message "Thank you for accepting the Oracle Binary Code License Agreement for Java SE; you may now download this software" will be shown to you.

4. Search your Operating System(Linux x86, Mac OS X x64, Windows x86, Windows x64) in "Product / File Description" column. In the Download column click on the link to start download your latest JDK version.

5. A System prompt asking to save the installer will come. Click on "Save File". The installer will be saved at default download location of browser or you can choose the location.

6. After the download is complete run the installation file.

7. Go with the default Installation location. Generally                          (Important)**

	For windows(x86): C:\Program Files (x86)\Java\jdkX.X.X\
	For Windows     : C:\Program Files\Java\jdkX.X.X\
	For Linux:      : /usr/java/<Java installation directory>

 and continue with the installation.

8. After completing the installation of JDK you have to set the classpath variables.


To set the classpath follow the instructions given below:



********************************************

		LINUX
		
********************************************
Set JAVA_HOME:

    Korn and bash shells:

    export JAVA_HOME=<jdk-install-dir>

    export PATH=$JAVA_HOME/bin:$PATH

    Bourne shell:

    JAVA_HOME=<jdk-install-dir>

    export JAVA_HOME

    PATH=$JAVA_HOME/bin:$PATH

    export PATH

    C shell:

    setenv JAVA_HOME <jdk-install-dir>

    setenv PATH $JAVA_HOME/bin:$PATH

    export PATH=$JAVA_HOME/bin:$PATH

********************************************

		WINDOWS
		
********************************************	


    1. Right-click the My Computer icon on your desktop and select Properties.
    2. Click the Advanced tab.
    3. Click the Environment Variables button.
    3. Under System Variables, click New.
    4. Enter the variable name as JAVA_HOME.
    5 .Enter the variable value as the "installation path" for the Java Development Kit.

        Note for Windows users on 64-bit systems
        
	"Program Files' OR 'Program Files(x86)"
	
example, "C:\Program Files (x86)\Java\jdkX.X.X"                          

	and press enter. where X.X.X is the complete folder name.
	
    6. Under System Variables, find PATH, and click on it. In the Edit windows,Append "%JAVA_HOME%\bin"; to the beginning of the PATH variable.
    7. Click OK.
    8. Click Apply Changes.

NOTE: "<inst-java-dir>" double quotes are mandatory.


****************************************************************************

		WINDOWS Through Command Prompt
		
****************************************************************************

Setting JAVA_HOME using command prompt

    1. Open command prompt
    2. Type the following in the command prompt
    
	set JAVA_HOME = "C:\Program Files\Java\jdkX.X.X"
	
	and press enter. where X.X.X is the complete folder name.

    3. set PATH = %PATH%;"%JAVA_HOME%\bin"

NOTE: "<inst-java-dir>" double quotes(") are mandatory.

9. Verfication of java installation:

	9.1 Open Terminal/Command Prompt
	9.2 type: java -version

Congratulations!! The installation of Java is complete and you are ready to start development using it.



 

 
