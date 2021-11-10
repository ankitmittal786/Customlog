"# Customlog" 

Steps to run the project:

1. download or clone the project "CustomLog"
2. Open project "CustomLog" in cmd->type "mvn clean install" or "mvn clean package" -> press enter
3. Type cd Target
4. Enter java -jar "customlog-0.0.1-SNAPSHOT.jar" --server.port=9090 --logfile.path=path_of_file   & press enter
      Eg java -jar "customlog-0.0.1-SNAPSHOT.jar" --server.port=9090 --logfile.path="C:\Users\AnkitMittal\Downloads\customlog\customlog\logfile.txt"
6. open Browser type "http://localhost:9090/savelogs"
7. TO check present logs in database type "http://localhost:9090/getlogs".

JDK 11,Maven,Tomcat must be installed.  

Note : JSON Data must be properly formatted & valid use JSOM formatter site to validate
