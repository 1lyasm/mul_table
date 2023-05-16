javac -d build -cp external_libraries src/*.java
cd build
jar -f mul_table.jar -e Main --create *.class
java -jar mul_table.jar
cd ..
rm build/*
