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

![image](https://user-images.githubusercontent.com/111562058/186026901-c6a42e5d-6e32-4ac9-ba28-5734883b280c.png)


INTREBARE: NoteControllerConsole e prea incarcat ca sa fie considerat controller. Problema e ca pentru a sti pe care dintre functionalitatile pe care le expune controllerul sa o apelez (add, list, find), ar trebui sa prelucrez mai intai datele, ceea ce ar insemna ca nu mai este NoteControllerConsole primul layer cel care preia console requestul(args-urile). Deci iar am o clasa incarcata care preia requestul..

TODO: adauga aplicatia angular intr-un model numit forntend, si muta aplicatia java intr-un modul numit backend

TODO: poate add id
