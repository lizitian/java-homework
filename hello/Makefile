all:
	javac -O -g:none hello.java
	jar -cvmf Manifest hello.jar hello.class
clean:
	rm -f hello.class hello.jar
run:
	java -jar hello.jar
runclass:
	java hello
