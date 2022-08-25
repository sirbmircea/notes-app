```diff
- branch-ul [optimizations-post-submission](https://github.com/sirbmircea/notes-app/tree/optimizations-post-submission) contine cod mai curat si decuplat
```
# notes-app

A note app that stores notes by Command Line or by Angular based Web Application

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

![image](https://user-images.githubusercontent.com/111562058/186296114-8b8dc792-568c-42f9-b4a9-b37e591409c8.png)

Mai editez din cand in cand in branch-ul optimizations-post-submission. Masterul e ca am trimis pe mail, dar daca vreti, puteti sa va uitati direct in optimizations-post-submission, pentru ca o sa fie cod mai clean si mai clar. 
