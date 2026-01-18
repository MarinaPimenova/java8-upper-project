

# DEV Notes
## Parent POM
The warning is straightforward: when you are using maven-compiler-plugin without explicitly declaring its version. Since Maven 3.9+, this is strongly discouraged and results in the warning you see.

Even if the version is inherited implicitly, Maven wants it declared explicitly either in:

the module POM, or

the parent POM via <pluginManagement>

the best practice is to define the plugin version once in the parent under pluginManagement.

Parent POM (jmp-parent) — recommended change:
```xml
<build>
    <pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.12.1</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </pluginManagement>
</build>

```
Then your child POM stays clean and needs no version:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
</plugin>
```