javac -d build -cp build src/*.java 
cd build
jar -f mul_table.jar -e Main --create *.class com
java -jar mul_table.jar
cd ..
rm build/*.class
rm build/*.jar
