import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class Serveur
{

  public static void main(String[] args)
  {
    try
    {
      //init ORB
      ORB orb = ORB.init( args, null );

      IcarreImpl myobj = new IcarreImpl();
      //String ior = orb.object_to_string( myobj );

      //initialiser le POA
      POA poa = POAHelper.narrow( orb.resolve_initial_references( "RootPOA" ));
      poa.the_POAManager().activate();
      org.omg.CORBA.Object poaobj = poa.servant_to_reference( myobj );
      String ior = orb.object_to_string( poaobj );

      System.out.println( ior );

      //String ior = orb.object_to_string( myobj );
      //System.out.println( ior );

      orb.run();
    }
    catch( org.omg.CORBA.SystemException ex ) { ex.printStackTrace();}
    catch( org.omg.CORBA.UserException ex ) { ex.printStackTrace();}
  }
}
