import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import java.io.*;

public class Serveur
{

  public static void main(String[] args)
  {
    try
    {
      //init ORB
      ORB orb = ORB.init( args, null );

      //IcarreImpl myobj = new IcarreImpl();
      OpMatriceImpl myobj = new OpMatriceImpl();
      //String ior = orb.object_to_string( myobj );

      //initialiser le POA
      POA poa = POAHelper.narrow( orb.resolve_initial_references( "RootPOA" ));
      poa.the_POAManager().activate();
      org.omg.CORBA.Object poaobj = poa.servant_to_reference( myobj );
      String ior = orb.object_to_string( poaobj );
      try
      {
        FileOutputStream file = new FileOutputStream(iorfile.value);
  	    PrintWriter out = new PrintWriter(file);
  	    out.println(ior);
        out.flush();
  	    file.close();
      }
      catch(IOException ex)
      {
        System.out.println (ex.toString());
      }


      System.out.println( ior );

      //String ior = orb.object_to_string( myobj );
      //System.out.println( ior );

      orb.run();
    }
    catch( org.omg.CORBA.SystemException ex ) { ex.printStackTrace();}
    catch( org.omg.CORBA.UserException ex ) { ex.printStackTrace();}
  }
}
