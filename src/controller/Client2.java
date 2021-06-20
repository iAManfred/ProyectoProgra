package controller;

// Client2 class that
// sends data and receives also

import Model.Orders1;
import java.io.*;
import java.net.*;

class Client2 {


    
    
    
    
    
    
    
	public void Client2(Orders1 order)
		throws Exception
	{
            
            try (Socket socket = new Socket("localhost",222)) {
                System.out.println("Client: Sending Order");
                OutputStream out= socket.getOutputStream();
                ObjectOutputStream object= new ObjectOutputStream(out);
                object.writeObject(order);
                out.flush();
                BufferedReader br= new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String str1 = br.readLine();
                
                socket.close();
            }
           
        
          }
            
	

        
        

}
