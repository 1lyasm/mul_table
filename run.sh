javac -d build -cp external_libraries/*.jar src/*.java 
cd build
jar -f mul_table.jar -e Main -m ../Manifest.txt --create *.class
java -jar mul_table.jar
cd ..
rm build/*.class
# rm build/*.jar
