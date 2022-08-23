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

![image](https://user-images.githubusercontent.com/111562058/186277523-684464d3-878a-4de3-8d0a-fddc48c3d90f.png)
