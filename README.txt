# Job-posting-An-application-that-queries-a-spatial-database
Database parameter: 
url ="jdbc:oracle:thin:@localhost:1521:orcl"
username = "Lee";
password = "901128";
 
how to compile and run:
1. Put classes111.jar,sdoapi.zip,map.jgp,students.xy, buildings.xy, announcementSystems.xy into the same folder.
2. you should run the createtable.sql to cteate the tables and index we need;
3. first,you should compile the populate.java as following:
   open the Cmd(note: better to run it as the administrator);
   compile populate.java with classes111.jar;sdoapi.zip
   then run it: java -cp classes111.jar;sdoapi.zip;. populate students.xy buildings.xy announcementSystems.xy
4. now, you have inserted the data to the tables
5. then, you should compile the hw2.java as following:
   javac -cp classes111.jar;sdoapi.zip;. hw2.java
   then run it:java -cp classes111.jar;sdoapi.zip;. hw2 map.jpg
6. finally, you use the droptable to delete all the tables.

Resolution:
1.For the createtable.sql and droptable.sql, they are the simple operations to create and drop tables we need. What should be point out is that we should create indexes for those attributes whose type is SDO_GEOMETRY in order to the following querry.
2.For GUI, I use the 'WindowBuilder' in the eclipse to finish it and add some mouse movement in the following work.(I does not separate the GUI code and querry code clearly.However I have made some commentary in hw2 to distinguish them)
3.For whole region querry, for student, I use vector to store all the data received from the querry(select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s).
  then use fillRect() to draw the picture.For announcement Systems, I still use vector to store center point and radius received from the querry(select a.as_location.sdo_point.x,a.as_location.sdo_point.y,as_radius from announcementsystem a),
  then use fillRect() and drawOval() to draw picture. For buildings, I use a structure to handle Geometry Objects cause buildings are polygon which are hard to divide into some simple objects.
  I use two arrays to store all points' x coordinate and y coordinate. Then use drawPolygon() to draw picture.(the first point and the last point in array must be the same so that we can use drawPolygon()).
4.For point querry, by recording the point where the mouse release, I can get the center point of the cricle, then draw the cricle in the picture.For student, I use function SDO_INSIDE to get the point meeting the requirement.And then use SDO_INSIDE and SDO_NN
  to get the nearest student. For announcement Systems, I use the fuction SDO_ANYINTERACT to get the as meeting the requirements and use SDO_ANYINTERACT and SDO_NN to get the nearest one.
  For building, I use the similar way to get the buildings meeting the requirement(using the structure to handle also).
5.For range querry, I use vector to store all the points clicked in the priture to draw the polygon and use the record to do the querry like the point querry.
6.For surrounding querry, after getting the point clicked on the picture, I can use SDO_NN to get the nearest announcement Systems.After that, I use SDO_INSIDE to get the students in the announcement Systems. This is straight.
7.For emergency querry, firstly, I use the same way in surrounding querry to get the students in the broken announcement Systems. After that, I use the querry(select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius,a.as_ID, SDO_NN_DISTANCE(1) dist from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL), 'sdo_num_res=2',1)='TRUE' ORDER BY dist)
  to get the two nearest one and order them by the decreasing diatance. I use only two variables to store the x coordinate and y coordinate of the points of announcement Systems individually. So I can cover the first point's value by the second point to get the second nearest announcement Systems.
  Also, in the querry I receive the ID of each announcement Systems in order to make my decision of letting the students in the same announcement Systems have the same colors easier.

  
