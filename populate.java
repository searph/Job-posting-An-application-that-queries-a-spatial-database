import java.awt.*;
import java.io.*;
import java.lang.*;

import javax.swing.*;

import java.sql.*;
import java.util.*;

public class populate {
	Connection con = null;
	Statement state = null;
	ResultSet rs = null;
	String student=null;
	String building=null;
	String as=null;
  public populate(String args[]){
    	
	   for (int i=0;i<args.length;i++)
        {
            
            if (args[i].equals("students.xy"))
            { student=args[i];
            }

            else  if (args[i].equals("buildings.xy"))
            {    building=args[i];
            }
            else  if (args[i].equals("announcementSystems.xy"))
            {    as=args[i];

            }
        }
    	connectdatabase();
    	deleteall();
    	insertstudent();
    	insertas();
    	insertbuilding();
    }
	


public static void main(String[] args)
{
	populate pop = new populate(args);
}

public void connectdatabase() 
{
	try
	{
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("Oracle's jdbc-odbc driver has been loaded.");
        String url ="jdbc:oracle:thin:@localhost:1521:orcl";
        String username = "Lee";
        String password = "901128";
        con = DriverManager.getConnection(url, username, password);
        System.out.println("The database has been connected."); 
       
   	}
	catch (Exception e)
	{
	System.out.println( "Error while connecting to DB: "+ e.toString() );
	e.printStackTrace();
	System.exit(-1);
	}		
}

public void  deleteall()  
{
	try
	{
		System.out.println("Firstly, we delete all the existing data.");
	    String temp="delete from student";
        state= con.createStatement();
	    state.executeUpdate(temp);
 	    System.out.println("Data in table student has been delete");
 	    temp= "delete from building";
 	    state= con.createStatement();
 	    state.executeUpdate(temp);
 	    System.out.println("Data in table building has been delete");
 	    temp= "delete from announcementsystem";
 	    state= con.createStatement();
 	    state.executeUpdate(temp);
 	    System.out.println("Data in table announcement has been delete");
	}
	catch (Exception e)
	{
	System.out.println( "Error while connecting to DB: "+ e.toString() );
	e.printStackTrace();
	System.exit(-1);
	}		
}

    
public void insertstudent()
{
    BufferedReader reader = null; 
    PreparedStatement tempst = null;
    try {  
        //System.out.println("read the file by line");  
        reader = new BufferedReader(new FileReader(student));  
        String tempString = null;  
        //int line = 1;  
        while ((tempString = reader.readLine()) != null) {  
           //System.out.println("line " + line + ": " + tempString); 
        	String[] element = tempString.split(", ");
        	tempst = con.prepareStatement("Insert into student values(?,SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL))");
        	tempst.setString(1,element[0]);
        	tempst.setInt(2,Integer.parseInt(element[1]));
        	tempst.setInt(3,Integer.parseInt(element[2]));
        	tempst.executeUpdate();
            //line++;  
        } 
        System.out.println("load student successfully");
        reader.close(); 
        tempst.close();
    } catch (IOException | SQLException e) {  
        e.printStackTrace();
       }
}

public void insertas()
{
    BufferedReader reader = null; 
    PreparedStatement tempst = null;
    try {  
        //System.out.println("read the file by line");  
        reader = new BufferedReader(new FileReader(as));  
        String tempString = null;  
        //int line = 1;  
        while ((tempString = reader.readLine()) != null) {  
           //System.out.println("line " + line + ": " + tempString); 
        	String[] element = tempString.split(", ");
        	tempst = con.prepareStatement("Insert into announcementsystem values(?,SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL),?,SDO_GEOMETRY(2003,NULL,NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))");
        	tempst.setString(1,element[0]);
        	tempst.setInt(2,Integer.parseInt(element[1]));
        	tempst.setInt(3,Integer.parseInt(element[2]));
        	tempst.setInt(4,Integer.parseInt(element[3]));
        	tempst.setInt(5,(Integer.parseInt(element[1])-Integer.parseInt(element[3])));
        	tempst.setInt(6,Integer.parseInt(element[2]));
        	tempst.setInt(7,(Integer.parseInt(element[1])+Integer.parseInt(element[3])));
        	tempst.setInt(8,Integer.parseInt(element[2]));
        	tempst.setInt(9,Integer.parseInt(element[1]));
        	tempst.setInt(10,(Integer.parseInt(element[2])-Integer.parseInt(element[3])));
        	tempst.executeUpdate();
           //line++;  
        }  
        System.out.println("load AS successfully");
        reader.close(); 
        tempst.close();
    } catch (IOException | SQLException e) {  
        e.printStackTrace();
       }
}

public void insertbuilding()
{
    BufferedReader reader = null; 
    PreparedStatement tempst = null;
    try {  
        //System.out.println("read the file by line");  
        reader = new BufferedReader(new FileReader(building));  
        String tempString = null;  
        //int line = 1;  
        while ((tempString = reader.readLine()) != null) {  
           //System.out.println("0000"); 
        	String sql = "insert into building values(?,?,SDO_GEOMETRY(2003,NULL,NULL,MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,1),MDSYS.SDO_ORDINATE_ARRAY(";
        	String[] element = tempString.split(", ");
        	int a = element.length-3;
        	int b=1;
        	for(b=1;b<=a;b++){
        		sql=sql+"?";
        		if (b!=a){
        			sql=sql+",";
        		}
        		else sql=sql+",?,?";
        	}
        	sql=sql+")))";
        	//System.out.println(sql);
        	tempst = con.prepareStatement(sql);
        	tempst.setString(1,element[0]);
        	tempst.setString(2,element[1]);
        	for (b=1;b<=a;b++){
        	    tempst.setInt(b+2,Integer.parseInt(element[b+2]));
        	}
        	tempst.setInt(a+3,Integer.parseInt(element[3]));
        	tempst.setInt(a+4,Integer.parseInt(element[4]));
        	tempst.executeUpdate();
           //line++;  
        }  
        System.out.println("load building successfully");
        reader.close(); 
        tempst.close();
    } catch (IOException | SQLException e) {  
        e.printStackTrace();
       }
}
}


