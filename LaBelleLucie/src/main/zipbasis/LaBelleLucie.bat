rem @echo off

setlocal

set APP_JAR=LaBelleLucie-0.1.0-SNAPSHOT.jar

java -cp lib/CLP*.jar,%APP_JAR% --module-path lib --add-modules javafx.controls,javafx.web net.sf.cotelab.lbl.LaBelleLucie

endlocal