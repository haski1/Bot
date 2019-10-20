javac -classpath src;api\telegrambots-4.4.0.1-jar-with-dependencies.jar -d bin -encoding UTF-8 -proc:none src/Main.java
java -classpath bin;api\telegrambots-4.4.0.1-jar-with-dependencies.jar Main
pause