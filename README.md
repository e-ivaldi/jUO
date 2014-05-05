jUO
===

Juo is (for the moment) a very basic proof of concept for a ultima online java server that wants to recreate a uo t2a experience, there are actually some shards that try to create the same atmosphere but they (all ?) use a relative new version of the client, I have the feeling that one of the thing that created the aforementioned atmosphere was the lightness and the responsiveness of the client itself, so.. this is it.

(the server works only for the client ver. 1.26.4 (patched to use a decrypted connection))

Note: in order to start the server the following files *must* be copied into /src/main/resources before building the project:

1) map0.mul

HOW TO BUILD THE PROJECT:
=========================

You can build the project easily in your ide if java8 and maven are installed and configured properly.
(I personally use Eclipse)

If you want to build the project from the command line:

enter the "juo" folder,
run "mvn clean package" (mvn3 and java8 need to be installed and configured properly)
get your built jar from the "target" folder
run it using "java -jar juo-{VERSION}.jar"

![alt tag](http://i.imgur.com/xHj4XF5.png)
