package com.spring.serialization;
import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;

public class Serialization<T>
{
	public void serialize(ArrayList<T> arr)
	{
		try{
	         FileOutputStream fos= new FileOutputStream("DataBase");
	         ObjectOutputStream oos= new ObjectOutputStream(fos);
	         oos.writeObject(arr);
	         oos.close();
	         fos.close();
	       }catch(IOException ioe){
	            ioe.printStackTrace();
	        }
	}
	public void deserialize(ArrayList<T> arr)
	{
		 try
	        {
	            FileInputStream fis = new FileInputStream("DataBase");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            arr = (ArrayList<T>) ois.readObject();
	            ois.close();
	            fis.close();
	         }
		 catch(IOException ioe)
		 {
	             ioe.printStackTrace();
	             return;
	     }
		 catch(ClassNotFoundException c)
		 {
	             System.out.println("Class not found");
	             c.printStackTrace();
	             return;
	     }
	}
}
