# notes-app

A note app that stores notes by Command Line or by Http Requests

## Commands

#### Add note
```shell
java -jar <jar-name>.jar add <note_title> <note_content>
```
#### List note by title
```shell
java -jar <jar-name>.jar list <note_title>
```
#### List all notes
```shell
java -jar <jar-name>.jar list
```
#### Start Spring boot
```shell
java -jar <jar-name>.jar run-spring-boot
```
## Create jar file
Open Windows PowerShell in the root folder "notes-app"
```shell
mvn package
```

## Architecture

![image](https://user-images.githubusercontent.com/111562058/186289348-edd68e8b-a278-44e8-87a6-a2a1b6134173.png)
