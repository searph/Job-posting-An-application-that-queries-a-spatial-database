import java.awt.*;
import java.awt.*;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.SwingUtilities;
import oracle.sql.STRUCT;
import javax.swing.border.*;
import java.util.*;
import java.lang.*;
import oracle.sdoapi.OraSpatialManager;
import oracle.sdoapi.geom.*;
import oracle.sdoapi.adapter.*;
import oracle.sdoapi.*;


public class hw2 {

	private JFrame frmA;
	private JTextField txtActiveFeature;
	private JTextField txtQuerry;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;
	loadpicture l;
	int x;
	int y;
	int clickas=0;
	int clickbu=0;
	int clickstu=0;
	int choose=-1;
	int sub=0;
	Vector<Integer> v1 = new Vector<Integer>();
	Vector<Integer> v2 = new Vector<Integer>();
	Connection con=null;
	JTextArea textArea = new JTextArea();
    int f=1;
    static String im=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		im=args[0];
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw2 window = new hw2();
					window.frmA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hw2() {
		connectdatabase();
		initialize();		
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmA = new JFrame();
		frmA.setTitle("Junchi Li/ USCID:3056872405");
		frmA.setBounds(100, 100, 1025, 686);
		frmA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmA.getContentPane().setLayout(null);
		
		final JCheckBox chckbxNewCheckBox = new JCheckBox("AS");
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(chckbxNewCheckBox.isSelected()==true)
				clickas=1;
				else clickas=0;
			}
		});
		chckbxNewCheckBox.setBounds(853, 62, 97, 23);
		frmA.getContentPane().add(chckbxNewCheckBox);
		
		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Building");
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(chckbxNewCheckBox_1.isSelected()==true)
					clickbu=1;
					else clickbu=0;
			}
		});
		chckbxNewCheckBox_1.setBounds(853, 88, 97, 23);
		frmA.getContentPane().add(chckbxNewCheckBox_1);
		
		final JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Student");
		chckbxNewCheckBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(chckbxNewCheckBox_2.isSelected()==true)
					clickstu=1;
					else clickstu=0;
			}
		});
		chckbxNewCheckBox_2.setBounds(853, 114, 97, 23);
		frmA.getContentPane().add(chckbxNewCheckBox_2);
		txtActiveFeature = new JTextField();
		txtActiveFeature.setBounds(853, 21, 146, 34);
		txtActiveFeature.setBackground(Color.LIGHT_GRAY);
		txtActiveFeature.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtActiveFeature.setText("Active Feature Type");
		frmA.getContentPane().add(txtActiveFeature);
		txtActiveFeature.setColumns(10);
		
		txtQuerry = new JTextField();
		txtQuerry.setBounds(853, 164, 146, 34);
		txtQuerry.setBackground(Color.LIGHT_GRAY);
		txtQuerry.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtQuerry.setText("Querry");
		frmA.getContentPane().add(txtQuerry);
		txtQuerry.setColumns(10);
		
		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Whole Region");
		rdbtnNewRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e){
				x=-1;y=-1;
				l.repaint();
				if(rdbtnNewRadioButton.isSelected())choose=0;
				else choose=-1;
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(853, 232, 109, 23);
		frmA.getContentPane().add(rdbtnNewRadioButton);
		
		final JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Point Querry");
		rdbtnNewRadioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x=-1;y=-1;
				l.repaint();
				if(rdbtnNewRadioButton_1.isSelected())choose=1;
				else choose=-1;
				
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(853, 279, 109, 23);
		frmA.getContentPane().add(rdbtnNewRadioButton_1);
		
		final JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Range Querry");
		rdbtnNewRadioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				v1.removeAllElements();
				v2.removeAllElements();
				x=-1;y=-1;
				l.repaint();
				if(rdbtnNewRadioButton_2.isSelected())choose=2;
				else choose=-1;
				
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(853, 323, 109, 23);
		frmA.getContentPane().add(rdbtnNewRadioButton_2);
		
		final JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Surrounding Student");
		rdbtnNewRadioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x=-1;y=-1;
				l.repaint();
				if(rdbtnNewRadioButton_3.isSelected())choose=3;
				else choose=-1;
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_3);
		rdbtnNewRadioButton_3.setBounds(853, 369, 138, 23);
		frmA.getContentPane().add(rdbtnNewRadioButton_3);
		
		final JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Emergency Querry");
		rdbtnNewRadioButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				x=-1;y=-1;
				l.repaint();
				if(rdbtnNewRadioButton_4.isSelected())choose=4;
				else choose=-1;
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_4);
		rdbtnNewRadioButton_4.setBounds(853, 417, 138, 23);
		frmA.getContentPane().add(rdbtnNewRadioButton_4);
		
		JButton btnNewButton = new JButton("Submit Querry");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				sub=1;
				l.repaint();
				
			}
		});
		btnNewButton.setBounds(853, 493, 124, 23);
		frmA.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 591, 810, 46);
		frmA.getContentPane().add(scrollPane);
		
		
		scrollPane.setViewportView(textArea);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(853, 546, 28, 14);
		frmA.getContentPane().add(lblX);
		
		textField = new JTextField();
		textField.setBounds(891, 543, 86, 20);
		frmA.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(853, 571, 28, 14);
		frmA.getContentPane().add(lblY);
		
		textField_1 = new JTextField();
		textField_1.setBounds(891, 568, 86, 20);
		frmA.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		l=new loadpicture();
		frmA.getContentPane().add(l);
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




/*******       load the picture                    *****/
	public class loadpicture extends JPanel
	{
        private ImageIcon imageIcon;
        public loadpicture()
        {
           imageIcon = new ImageIcon(im); //写入文件路径
           this.setVisible(true);  //设置为显示
           this.setBounds(0,0,820,580);
           frmA.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
   			@Override
   			public void mouseMoved(MouseEvent e) {
   				int a= e.getX();
   				int b= e.getY();
   				textField.setText(String.valueOf(a));
   				textField_1.setText(String.valueOf(b));
   			}
   		});
           frmA.getContentPane().addMouseListener(new MouseAdapter() {
   			@Override
   			public void mouseReleased(MouseEvent e) {
   				x=e.getX();
   				y=e.getY();
   				if(e.getModifiers()==InputEvent.BUTTON3_MASK)//bug
   				{
   					x=v1.get(0);y=v2.get(0);
   				}
   				repaint();
   			}
   		});
   		}
        
	    public void paint(Graphics g)
	    {
	    	g.drawImage(imageIcon.getImage(), 0, 0, this);	
	    	/*    whole region   */	
	    	if(choose==0)
	    	{   
	    		if(sub==1)
	    		{   textArea.append("*************whole region****************"+"\n");     
	    			try {
						Statement state= con.createStatement();
						if(clickstu==1){
							ResultSet rs= state.executeQuery("select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s");
							//System.out.println(rs);
							Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							while(rs.next())
							{
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));	
							}
							for(int i=0;i<xray.size();i++)
							{
								g.setColor(Color.green);
								g.fillRect(xray.get(i)-5, yray.get(i)-5, 10, 10);
							}
							textArea.append("Querry"+f+": "+"select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s"+"\n");
							f++;
						}
						if(clickas==1){
							ResultSet rs= state.executeQuery("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,as_radius from announcementsystem a");
							Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							Vector<Integer> r = new Vector<Integer>();
							while(rs.next())
							{
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));r.add(rs.getInt(3));
							}
							for(int i=0;i<xray.size();i++)
							{
								g.setColor(Color.red);
								g.fillRect(xray.get(i)-7, yray.get(i)-7, 15, 15);
								g.drawOval(xray.get(i)-r.get(i), yray.get(i)-r.get(i), 2*r.get(i), 2*r.get(i));
							}
							textArea.append("Querry"+f+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,as_radius from announcementsystem a"+"\n");
							f++;
						}
						if(clickbu==1){//System.out.print("sssssss");
							ResultSet rs= state.executeQuery("select b.building_location from building b");
							STRUCT polygon;		//Structure to handle Geometry Objects
							Geometry geom;     	//Structure to handle Geometry Objects
							try
							{
					            //ResultSetMetaData meta = rs.getMetaData();
                                GeometryAdapter sdoAdapter = OraSpatialManager.getGeometryAdapter("SDO", "9",STRUCT.class, null, null, con);

					 	        while(rs.next())
					 	        {
					 	        	polygon = (STRUCT)rs.getObject(1);
									geom = sdoAdapter.importGeometry( polygon );
					      			if ( (geom instanceof oracle.sdoapi.geom.Polygon) )
					      			{
										oracle.sdoapi.geom.Polygon polygon_1= (oracle.sdoapi.geom.Polygon) geom;
										CurveString c=polygon_1.getExteriorRing();
										CoordPoint[] p=c.getPointArray();
										int i=p.length;
										//System.out.println(i);
										int[] xray=new int[i];
										int[] yray=new int[i];
										for(int j=0; j<i;j++)
										{
											xray[j]=(int) p[j].getX();
											yray[j]=(int) p[j].getY();
											//System.out.println(xray[j]);
										}
										g.setColor(Color.yellow);
										g.drawPolygon(xray,yray,i);
									    }
					 	        }
					        }
							catch( Exception e )
						    { System.out.println(" Error : " + e.toString() ); }
							textArea.append("Querry"+f+": "+"select b.building_location from building b"+"\n");
							f++;
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	}
	    			}
/*     point querry   */	    	
	    	if(choose==1)
	    	{  if(y!=-1)
	    	{
	    		g.drawRect(x-2,y-2,5,5);
	    		g.setColor(Color.red);
	    		g.fillRect(x-2,y-2,5,5);
	    		g.drawOval(x-50,y-50,100,100);
	    		if(sub==1)
	    		{   textArea.append("*************point querry****************"+"\n"); 
	    			try{
	    				PreparedStatement tempsql;
						if(clickstu==1)
						{  
							tempsql = con.prepareStatement("select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1,x);
							tempsql.setInt(2,y+50);
							tempsql.setInt(3,x);
							tempsql.setInt(4,y-50);
							tempsql.setInt(5,x+50);
							tempsql.setInt(6,y);
							ResultSet rs= tempsql.executeQuery();
							Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							while(rs.next())
							{   //System.out.println(rs.getInt(1));
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));
							}
							for(int i=0;i<xray.size();i++)
							{
								g.setColor(Color.green);
								g.fillRect(xray.get(i)-5, yray.get(i)-5, 10, 10);
							}
							tempsql = con.prepareStatement("select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_NN(s.student_location, SDO_GEOMETRY(2001, NULL, sdo_point_type(?,?,NULL), NULL,NULL),'sdo_num_res=1')='TRUE' and SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1,x);
							tempsql.setInt(2,y);
							tempsql.setInt(3,x-50);
							tempsql.setInt(4,y);
							tempsql.setInt(5,x+50);
							tempsql.setInt(6,y);
							tempsql.setInt(7,x);
							tempsql.setInt(8,y+50);
							rs= tempsql.executeQuery();
							while(rs.next())
							{
								int xtemp=rs.getInt(1); int ytemp=rs.getInt(2);//System.out.println(xtemp);
								g.setColor(Color.yellow);
								g.fillRect(xtemp-5, ytemp-5, 10, 10);
							}
							
							textArea.append("Querry"+f+".1"+": "+"select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
							textArea.append("Querry"+f+".2"+": "+"select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where"+" SDO_NN(s.student_location, SDO_GEOMETRY(2001, NULL, sdo_point_type("+"x"+","+y+","+"NULL), NULL,NULL),'sdo_num_res=1')='TRUE'"+"and"+" SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
							f++;
							}
						if(clickas==1)
						{
							tempsql = con.prepareStatement("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where  SDO_ANYINTERACT(a.circle, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1,x);
							tempsql.setInt(2,y+50);
							tempsql.setInt(3,x);
							tempsql.setInt(4,y-50);
							tempsql.setInt(5,x+50);
							tempsql.setInt(6,y);
							ResultSet rs= tempsql.executeQuery();
							Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							Vector<Integer> rray = new Vector<Integer>();
							while(rs.next())
							{
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));rray.add(rs.getInt(3));
							}
							for(int i=0;i<xray.size();i++)
							{
								if(xray.size()==1){
									g.setColor(Color.yellow);
									g.fillRect(xray.get(i)-7, yray.get(i)-7, 15, 15);
									g.drawOval(xray.get(i)-rray.get(i), yray.get(i)-rray.get(i), 2*rray.get(i),2*rray.get(i));
								}
								else
								g.setColor(Color.green);
								g.fillRect(xray.get(i)-7, yray.get(i)-7, 15, 15);
								g.drawOval(xray.get(i)-rray.get(i), yray.get(i)-rray.get(i), 2*rray.get(i),2*rray.get(i));
							}
							tempsql = con.prepareStatement("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001, NULL, sdo_point_type(?,?,NULL), NULL,NULL),'sdo_num_res=1')='TRUE' and SDO_ANYINTERACT(a.circle, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1,x);
							tempsql.setInt(2,y);
							tempsql.setInt(3,x);
							tempsql.setInt(4,y+50);
							tempsql.setInt(5,x);
							tempsql.setInt(6,y-50);
							tempsql.setInt(7,x+50);
							tempsql.setInt(8,y);
							rs= tempsql.executeQuery();
							while(rs.next())
							{
								int xtemp=rs.getInt(1); int ytemp=rs.getInt(2);int rtemp=rs.getInt(3);//System.out.println(xtemp);
								g.setColor(Color.yellow);
								g.fillRect(xtemp-7, ytemp-7, 15, 15);
								g.drawOval(xtemp-rtemp, ytemp-rtemp, 2*rtemp,2*rtemp);
							}
							textArea.append("Querry"+f+".1"+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_ANYINTERACT(a.circle, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
							textArea.append("Querry"+f+".2"+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where"+" SDO_NN(a.circle, SDO_GEOMETRY(2001, NULL, sdo_point_type("+"x"+","+y+","+"NULL), NULL,NULL),'sdo_num_res=1')='TRUE'"+"and"+" SDO_ANYINTERACT(a.circle, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
							f++;
						}
	    			
						if(clickbu==1)
						{
							tempsql = con.prepareStatement("select b.building_location from building b where  SDO_ANYINTERACT(b.building_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1,x);
							tempsql.setInt(2,y+50);
							tempsql.setInt(3,x);
							tempsql.setInt(4,y-50);
							tempsql.setInt(5,x+50);
							tempsql.setInt(6,y);
							ResultSet rs= tempsql.executeQuery();
							STRUCT polygon;		//Structure to handle Geometry Objects
							Geometry geom;     	//Structure to handle Geometry Objects
							try
							{
					            //ResultSetMetaData meta = rs.getMetaData();
                                GeometryAdapter sdoAdapter = OraSpatialManager.getGeometryAdapter("SDO", "9",STRUCT.class, null, null, con);

					 	        while(rs.next())
					 	        {
					 	        	polygon = (STRUCT)rs.getObject(1);
									geom = sdoAdapter.importGeometry( polygon );
					      			if ( (geom instanceof oracle.sdoapi.geom.Polygon) )
					      			{
										oracle.sdoapi.geom.Polygon polygon_1= (oracle.sdoapi.geom.Polygon) geom;
										CurveString c=polygon_1.getExteriorRing();
										CoordPoint[] p=c.getPointArray();
										int i=p.length;
										//System.out.println(i);
										int[] xray=new int[i];
										int[] yray=new int[i];
										for(int j=0; j<i;j++)
										{
											xray[j]=(int) p[j].getX();
											yray[j]=(int) p[j].getY();
											//System.out.println(xray[j]);
										}
										g.setColor(Color.green);
										g.drawPolygon(xray,yray,i);
									    }

							        
					       	    }
					        }catch( Exception e )
						    { System.out.println(" Error : " + e.toString() ); }
						     tempsql = con.prepareStatement("select b.building_location from building b where SDO_NN(b.building_location, SDO_GEOMETRY(2001, NULL, sdo_point_type(?,?,NULL), NULL,NULL),'sdo_num_res=1')='TRUE'and SDO_ANYINTERACT(b.building_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
						        tempsql.setInt(1,x);
								tempsql.setInt(2,y);
								tempsql.setInt(3,x);
								tempsql.setInt(4,y-50);
								tempsql.setInt(5,x+50);
								tempsql.setInt(6,y);
								tempsql.setInt(7,x);
								tempsql.setInt(8,y+50);
								rs= tempsql.executeQuery();
								try
								{
						            //ResultSetMetaData meta = rs.getMetaData();
	                                GeometryAdapter sdoAdapter = OraSpatialManager.getGeometryAdapter("SDO", "9",STRUCT.class, null, null, con);

						 	        while(rs.next())
						 	        {
						 	        	polygon = (STRUCT)rs.getObject(1);
										geom = sdoAdapter.importGeometry( polygon );
						      			if ( (geom instanceof oracle.sdoapi.geom.Polygon) )
						      			{
											oracle.sdoapi.geom.Polygon polygon_1= (oracle.sdoapi.geom.Polygon) geom;
											CurveString c=polygon_1.getExteriorRing();
											CoordPoint[] p=c.getPointArray();
											int i=p.length;
											//System.out.println(i);
											int[] xray=new int[i];
											int[] yray=new int[i];
											for(int j=0; j<i;j++)
											{
												xray[j]=(int) p[j].getX();
												yray[j]=(int) p[j].getY();
												//System.out.println(xray[j]);
											}
											g.setColor(Color.yellow);
											g.drawPolygon(xray,yray,i);
										    }

								        
						       	    }
						        }
								catch( Exception e )
							    { System.out.println(" Error : " + e.toString() ); }
								textArea.append("Querry"+f+".1"+": "+"select b.building_location from building b where SDO_ANYINTERACT(b.building_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
								textArea.append("Querry"+f+".2"+": "+"select b.building_location building b where"+" SDO_NN(b.building_location, SDO_GEOMETRY(2001, NULL, sdo_point_type("+"x"+","+y+","+"NULL), NULL,NULL),'sdo_num_res=1')='TRUE'"+"and"+" SDO_ANYINTERACT(b.building_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+x+","+(y+50)+","+x+","+(y-50)+","+(x+50)+","+y+")))='TRUE'"+"\n");
								f++;	
						}
	    			}catch( Exception e )
				    { System.out.println(" Error : " + e.toString() ); }
	    		}
	    	}
	    	}
	    	
	    	/*     range querry   */		    	
	    	if(choose==2)
	    	{   if(y!=-1)
	    	{
	    		v1.add(x);
	    		v2.add(y);
	    		int i=v1.size();
	    		if(i==1){
	    			g.setColor(Color.red);
	    			g.drawLine(v1.get(i-1),v2.get(i-1),v1.get(i-1),v2.get(i-1));
	    			
	    		}
	    		else{
	    			for(int j=0;j<i-1;j++){
		    			g.setColor(Color.red);
		    			g.drawLine(v1.get(j),v2.get(j),v1.get(j+1),v2.get(j+1));
		    		    
		    		}
	    		}//System.out.println(v1.get(0));System.out.println(v2.get(0));System.out.println(v1.get(i-1));System.out.println(v2.get(i-1));
	    		if(sub==1)
	    		{textArea.append("*************range querry****************"+"\n"); 
	    			try{
	    				PreparedStatement tempsql;
	    				String sql;
	    				if(clickstu==1){
	    					sql="select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003,NULL,NULL,MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,1),MDSYS.SDO_ORDINATE_ARRAY(";
	    					
	    					for(int j=0;j<i;j++){
	    						sql=sql+"?,?";
	    						if(j!=i-1)sql=sql+",";
	    					}
	    					sql=sql+")))='TRUE'";
	    					tempsql=con.prepareStatement(sql);
	    					for(int j=1;j<=i;j++){
	    						tempsql.setInt(2*j-1, v1.get(j-1));tempsql.setInt(2*j, v2.get(j-1));
	    					}
	    					ResultSet rs= tempsql.executeQuery();
							Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							while(rs.next())
							{   //System.out.println(rs.getInt(1));
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));
							}
							for(int j=0;j<xray.size();j++)
							{
								g.setColor(Color.green);
								g.fillRect(xray.get(j)-5, yray.get(j)-5, 10, 10);
							}
							textArea.append("Querry"+f+": "+sql+"\n");
							f++;	
	    				}
	    				if(clickas==1){
	    					sql="select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_ANYINTERACT(a.circle, SDO_GEOMETRY(2003,NULL,NULL,MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,1),MDSYS.SDO_ORDINATE_ARRAY(";
	    					for(int j=0;j<i;j++){
	    						sql=sql+"?,?";
	    						if(j!=i-1)sql=sql+",";
	    					}
	    					sql=sql+")))='TRUE'";
	    					tempsql=con.prepareStatement(sql);
	    					for(int j=1;j<=i;j++){
	    						tempsql.setInt(2*j-1, v1.get(j-1));tempsql.setInt(2*j, v2.get(j-1));
	    					}
	    					ResultSet rs= tempsql.executeQuery();
	    					Vector<Integer> xray = new Vector<Integer>();
							Vector<Integer> yray = new Vector<Integer>();
							Vector<Integer> rray = new Vector<Integer>();
							while(rs.next())
							{
								xray.add(rs.getInt(1));yray.add(rs.getInt(2));rray.add(rs.getInt(3));
							}
							for(int j=0;j<xray.size();j++)
							{
								
								g.setColor(Color.red);
								g.fillRect(xray.get(j)-7, yray.get(j)-7, 15, 15);
								g.drawOval(xray.get(j)-rray.get(j), yray.get(j)-rray.get(j), 2*rray.get(j),2*rray.get(j));
							}
							textArea.append("Querry"+f+": "+sql+"\n");
							f++;	
	    				}
	    				if(clickbu==1)
	    				{
	    					sql="select b.building_location from building b where SDO_ANYINTERACT(b.building_location, SDO_GEOMETRY(2003,NULL,NULL,MDSYS.SDO_ELEM_INFO_ARRAY(1,1003,1),MDSYS.SDO_ORDINATE_ARRAY(";
	    					for(int j=0;j<i;j++){
	    						sql=sql+"?,?";
	    						if(j!=i-1)sql=sql+",";
	    					}
	    					sql=sql+")))='TRUE'";
	    					tempsql=con.prepareStatement(sql);
	    					for(int j=1;j<=i;j++){
	    						tempsql.setInt(2*j-1, v1.get(j-1));tempsql.setInt(2*j, v2.get(j-1));
	    					}
	    					ResultSet rs= tempsql.executeQuery();
	    					STRUCT polygon;		//Structure to handle Geometry Objects
							Geometry geom;     	//Structure to handle Geometry Objects
							try
							{
					            //ResultSetMetaData meta = rs.getMetaData();
                                GeometryAdapter sdoAdapter = OraSpatialManager.getGeometryAdapter("SDO", "9",STRUCT.class, null, null, con);

					 	        while(rs.next())
					 	        {
					 	        	polygon = (STRUCT)rs.getObject(1);
									geom = sdoAdapter.importGeometry( polygon );
					      			if ( (geom instanceof oracle.sdoapi.geom.Polygon) )
					      			{
										oracle.sdoapi.geom.Polygon polygon_1= (oracle.sdoapi.geom.Polygon) geom;
										CurveString c=polygon_1.getExteriorRing();
										CoordPoint[] p=c.getPointArray();
										int j=p.length;
										//System.out.println(i);
										int[] xray=new int[j];
										int[] yray=new int[j];
										for(int q=0; q<j;q++)
										{
											xray[q]=(int) p[q].getX();
											yray[q]=(int) p[q].getY();
											//System.out.println(xray[j]);
										}
										g.setColor(Color.yellow);
										g.drawPolygon(xray,yray,j);
									    }
					      			}
					        }catch( Exception e )
						    { System.out.println(" Error : " + e.toString() ); }
							textArea.append("Querry"+f+": "+sql+"\n");
							f++;		    				
	    				}
	    				
	    			}
	    			catch( Exception e )
				    { System.out.println(" Error : " + e.toString() ); }
	    			}
	    	}
	    	}
	    	
	    	/*     surrounding querry   */	
	    	if(choose==3)
	    	{   
	    		if(y!=-1)
	    		{
	    			g.drawRect(x-2,y-2,5,5);
		    		g.setColor(Color.red);
		    		g.fillRect(x-2,y-2,5,5);
		    		PreparedStatement tempsql = null;
		    		int xtemp=0; int ytemp=0; int rtemp=0;
		    		try {
						tempsql = con.prepareStatement("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL), 'sdo_num_res=1')='TRUE'");
						tempsql.setInt(1, x);tempsql.setInt(2, y);
						ResultSet rs= tempsql.executeQuery();
						while(rs.next())
						{
						xtemp=rs.getInt(1); ytemp=rs.getInt(2);rtemp=rs.getInt(3);
						g.setColor(Color.red);
						g.fillRect(xtemp-7, ytemp-7, 15, 15);
						g.drawOval(xtemp-rtemp, ytemp-rtemp, rtemp*2, 2*rtemp);
						}
						
						
						if(sub==1)
						{textArea.append("*************whole region****************"+"\n"); 
							textArea.append("Querry"+f+".1"+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_NN(a.cricle, SDO_GEOMETRY(2001, NULL, NULL,SDO_POINT_TYPE("+x+","+y+","+"NULL), NULL , NULL), 'sdo_num_res=1')='TRUE'"+"\n");
							tempsql = con.prepareStatement("select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1, xtemp);
							tempsql.setInt(2, ytemp-rtemp);
							tempsql.setInt(3, xtemp);
							tempsql.setInt(4, ytemp+rtemp);
							tempsql.setInt(5, xtemp-rtemp);
							tempsql.setInt(6, ytemp);
							rs=tempsql.executeQuery();
							while(rs.next())
							{
								int a =rs.getInt(1); int b=rs.getInt(2);
								g.setColor(Color.green);
								g.fillRect(a-5, b-5, 10,10);
							}
							textArea.append("Querry"+f+".2"+": "+"select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+xtemp+","+(ytemp-rtemp)+","+xtemp+","+(ytemp+rtemp)+","+(xtemp-rtemp)+","+ytemp+")))='TRUE'"+"\n");
							f++;	
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		
	    		}
	    		
	    	}
	    	/*    emerengcy querry   */	
	    	if(choose==4)
	    	{
	    		if(y!=-1)
	    		{
	    			g.drawRect(x-2,y-2,5,5);
		    		g.setColor(Color.red);
		    		g.fillRect(x-2,y-2,5,5);
		    		PreparedStatement tempsql = null;
		    		int xtemp=0; int ytemp=0; int rtemp=0;
		    		Vector<Integer> x4 = new Vector<Integer>();
		    		Vector<Integer> y4= new Vector<Integer>();
		    		
		    		try {
						tempsql = con.prepareStatement("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL), 'sdo_num_res=1')='TRUE'");
						tempsql.setInt(1, x);tempsql.setInt(2, y);
						ResultSet rs= tempsql.executeQuery();
						while(rs.next())
						{
						xtemp=rs.getInt(1); ytemp=rs.getInt(2);rtemp=rs.getInt(3);
						g.setColor(Color.red);
						g.fillRect(xtemp-7, ytemp-7, 15, 15);
						g.drawOval(xtemp-rtemp, ytemp-rtemp, rtemp*2, 2*rtemp);
						}
						
						if(sub==1)
						{textArea.append("*************emergency region****************"+"\n"); 
						textArea.append("Querry"+f+".1"+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius from announcementsystem a where SDO_NN(a.cricle, SDO_GEOMETRY(2001, NULL, NULL,SDO_POINT_TYPE("+x+","+y+","+"NULL), NULL , NULL), 'sdo_num_res=1')='TRUE'"+"\n");
							tempsql = con.prepareStatement("select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY(?,?,?,?,?,?)))='TRUE'");
							tempsql.setInt(1, xtemp);
							tempsql.setInt(2, ytemp-rtemp);
							tempsql.setInt(3, xtemp);
							tempsql.setInt(4, ytemp+rtemp);
							tempsql.setInt(5, xtemp-rtemp);
							tempsql.setInt(6, ytemp);
							rs=tempsql.executeQuery();
							while(rs.next())
							{
								x4.add(rs.getInt(1)); y4.add(rs.getInt(2));
							}
							textArea.append("Querry"+f+".2"+": "+"select s.student_location.sdo_point.x,s.student_location.sdo_point.y from student s where SDO_INSIDE(s.student_location, SDO_GEOMETRY(2003, NULL, NULL,SDO_ELEM_INFO_ARRAY(1,1003,4),SDO_ORDINATE_ARRAY("+xtemp+","+(ytemp-rtemp)+","+xtemp+","+(ytemp+rtemp)+","+(xtemp-rtemp)+","+ytemp+")))='TRUE'"+"\n");
							int i=x4.size();
							for(int j=0;j<i;j++)
							{
								tempsql= con.prepareStatement("select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius,a.as_ID, SDO_NN_DISTANCE(1) dist from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE(?,?,NULL), NULL , NULL), 'sdo_num_res=2',1)='TRUE' ORDER BY dist");
								tempsql.setInt(1, x4.get(j));
								tempsql.setInt(2, y4.get(j));
								rs=tempsql.executeQuery();
								textArea.append("Querry"+f+".3"+"."+(j+1)+": "+"select a.as_location.sdo_point.x,a.as_location.sdo_point.y,a.as_radius,a.as_ID, SDO_NN_DISTANCE(1) dist from announcementsystem a where SDO_NN(a.circle, SDO_GEOMETRY(2001,NULL,SDO_POINT_TYPE("+x4.get(j)+","+y4.get(j)+","+"NULL), NULL , NULL), 'sdo_num_res=2',1)='TRUE' ORDER BY dist"+"\n");
								int x_4 = 0; int y_4=0; int r_4=0; String id=null;
								Color[] c= {Color.gray,Color.orange,Color.yellow,Color.green,Color.blue,Color.black,Color.pink}; 
								while(rs.next())
								{
									x_4=rs.getInt(1);y_4=rs.getInt(2);r_4=rs.getInt(3);id=rs.getString(4);
								}
								int h=-1;
								if(id.indexOf("1")!=-1)
									h=0;
								if(id.indexOf("2")!=-1)
									h=1;
								if(id.indexOf("3")!=-1)
									h=2;
								if(id.indexOf("4")!=-1)
									h=3;
								if(id.indexOf("5")!=-1)
									h=4;
								if(id.indexOf("6")!=-1)
									h=5;
								if(id.indexOf("7")!=-1)
									h=6;
										
									g.setColor(c[h]);
									g.drawOval(x_4-r_4, y_4-r_4, 2*r_4, 2*r_4);
									g.fillRect(x4.get(j)-5, y4.get(j)-5, 10, 10);
									
								
						
							}
							f++;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		
	    		}
	    		
	    	}
	    	sub=0;
	    }
	    
	}
	    
	    }




