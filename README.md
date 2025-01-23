# Juno ₊˚⊹♡

![alt text](https://www.vpesports.com/wp-content/uploads/2024/08/Overwatch-2-Unveils-Deeper-Narrative-Dive-Into-Junos-Past.png)

This project is based on the Duke project template, which is named after the Java mascot Duke. However, as part of my individual project for CS2103, I have renamed it to Juno, inspired by the Overwatch character. Below are the instructions to set up and run the project.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Juno.java` file, right-click it, and choose `Run Juno.main()` (if the code editor is showing compile errors, try restarting the IDE). 

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
