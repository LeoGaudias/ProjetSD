import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import java.io.*;

// Lancement du service de noms : tnameserv -ORBInitialPort 3000 &
// Lancement du serveur :         java Serveur localhost 3000 &

public class Serveur
{
  public static void main(String[] args)
  {
    if (args.length != 2)
    {
      System.out.println("Usage : java Serveur <machineServeurDeNoms> <port>");
      return;
    }
    try
    {

      //init ORB
      String [] argv = {"-ORBInitialHost", args[0],"-ORBInitialPort", args[1]};
	    ORB orb = ORB.init( argv, null );

      CompteImpl cpt1 = new CompteImpl();
      CompteImpl cpt2 = new CompteImpl();

      //initialiser le POA
      POA poa = POAHelper.narrow( orb.resolve_initial_references( "RootPOA" ));
      poa.the_POAManager().activate();

      org.omg.CORBA.Object poaobj1 = poa.servant_to_reference(cpt1);
      org.omg.CORBA.Object poaobj2 = poa.servant_to_reference(cpt2);

      // String ior = orb.object_to_string( poaobj );

      // try
      // {
      //   FileOutputStream file = new FileOutputStream(iorfile.value);
  	  //   PrintWriter out = new PrintWriter(file);
  	  //   out.println(ior);
      //   out.flush();
  	  //   file.close();
      // }
      // catch(IOException ex)
      // {
      //   System.out.println (ex.toString());
      // }

      // System.out.println( ior );

      org.omg.CORBA.Object obj = null;
	    obj = orb.resolve_initial_references("NameService");
	    if(obj == null)
      {
		      System.out.println("Ref nil sur NameService");
          System.exit(1);
	    }

      org.omg.CosNaming.NamingContext nc = NamingContextHelper.narrow(obj);

      NameComponent[] aName1 = new NameComponent[1];
      //NameComponent(String _id, String _kind)
	    aName1[0] = new NameComponent("compte1","");
	    // aName1[0].id = "compte1";
	    // aName1[0].kind = "somme";
      nc.rebind(aName1, poaobj1);

      NameComponent[] aName2 = new NameComponent[1];
      aName2[0] = new NameComponent("compte2","");
	    // aName2[0].id = "compte2";
	    // aName2[0].kind = "somme";
      nc.rebind(aName2, poaobj2);

	    System.out.println("Le serveur est pret ");

      orb.run();
    }
    catch(org.omg.CORBA.SystemException ex)
    {
      ex.printStackTrace();
    }
    catch(org.omg.CORBA.UserException ex)
    {
      ex.printStackTrace();
    }
  }
}
