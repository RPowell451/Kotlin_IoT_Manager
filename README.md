# Kotlin_IoT_Manager
IoT manager written in Kotlin.<br>
CSCI 4320 Group Project

Credits:<br>
Iot manager app and webcam IoT created by Robett Powell.<br>
Pet Feeder IoT created by Dondré Banks Overton.<br>
Health Monitoriing IoT created by Allen Kim.<br>
Thermostat IoT created by Monte Cameron.<br>

Webcam IoT Details:  
In order to use the remote webcam IoT, the user must first create an account. The account creation process is supported by Google's firebase authentication and firebase realtime database. Once the user logs in , the user can begin to add remote webcams. In order to add a remote cam the user must provide a name for the camera, the camera's location, and the IP address and the port with which to connect to the webcam. Once this information is submitted, it is stored with the user's information in the firebase realtime database. In order to interact with the remote webcam, one would touch the "cameras" option in the menu and then access the drop down menu associated with the desired camera. From the drop down menu, the user would then select "view camera" to view the remote camera. A python app which utilizes flask and OpenCV runs on the remote devices and listens out for connections from clients. Upon receiving a connection the remote app begins streaming video to the android app. In order to simulate a remote sensor the remote python app utilizes a pretrained learning model called "MobileNettSSD_deploy". This enables the remote camera to "sense" when objects are Infront of it. In addition to viewing cameras, other functional features include modifying a given camera’s connection settings and removing a camera. In order to change the port in which the webcam IoT listens out for incoming connections, edit the python server’s source code. By default the python server app listens out for connections on port 5000. A demonstration video of the IoT can be viewed below.<br>

https://github.com/RPowell451/Kotlin_IoT_Manager/assets/77960656/bd461c1e-2744-42db-a80a-5dc9c6c395b3

