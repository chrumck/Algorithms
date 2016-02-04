To make a maven repo from a file:
* Create a directory in the workspace (repo or something)
* execute this: 

define an unmanaged artifact
```shell
mvn deploy:deploy-file -Durl=file:///path/to/yourproject/repo/ -Dfile=mylib-1.0.jar -DgroupId=com.example -DartifactId=mylib -Dpackaging=jar -Dversion=1.0
```

add this to your POM (tell maven there is a new magical repo where you just created the unmanaged artifact)
```
<repositories>
    <!--other repositories if any-->
    <repository>
        <id>project.local</id>
        <name>project</name>
        <url>file:${project.basedir}/repo</url>
    </repository>
</repositories>
```

Now tell maven to depend on a jar (as imported in step 1)
```
<dependency>
    <groupId>com.example</groupId>
    <artifactId>mylib</artifactId>
    <version>1.0</version>
</dependency>
```

to build the executable jar:

```shell
mvn install
```

This will produce a directory called target 

now to execute the jar:

```shell
java -jar target/Percolation-1.0-SNAPSHOT.jar src/test/input10.txt
```

