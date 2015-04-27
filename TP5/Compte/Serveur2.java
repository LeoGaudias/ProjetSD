import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import java.io.*;

// Lancement du service de noms : orbd -ORBInitialPort 3000
// Lancement du serveur : java Serveur localhost 3000
public class Serveur2
{
  public static void main(String args[])
  {
    if (args.length != 2)
    {
      System.out.println("Usage : java Serveur <machineServeurDeNoms> <port>");
      return;
    }
    try
    {
      String [] argv = {"-ORBInitialHost", args[0], "-ORBInitialPort", args[1]};
      ORB orb = ORB.init( argv, null );

      CompteImpl cpt1 = new CompteImpl();
      CompteImpl cpt2 = new CompteImpl();
      CompteImpl cpt3 = new CompteImpl();
      CompteImpl cpt4 = new CompteImpl();

      POA poa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      poa.the_POAManager().activate();

      org.omg.CORBA.Object poaobj1 = poa.servant_to_reference(cpt1);
      org.omg.CORBA.Object poaobj2 = poa.servant_to_reference(cpt2);
      org.omg.CORBA.Object poaobj3 = poa.servant_to_reference(cpt3);
      org.omg.CORBA.Object poaobj4 = poa.servant_to_reference(cpt4);

      //NameServer
      org.omg.CORBA.Object obj = null;
	    obj = orb.resolve_initial_references("NameService");
	    if(obj == null)
      {
		      System.out.println("Ref nil sur NameService");
          System.exit(1);
	    }

      org.omg.CosNaming.NamingContext nc = NamingContextHelper.narrow(obj);

      // Agence bancaire
      NameComponent[] agenceName = new NameComponent[1];
      agenceName[0] = new NameComponent("agence", "");
      NamingContext ncA = nc.new_context();
      nc.rebind_context(agenceName, ncA);

      // Client 1
      NameComponent[] client1Name = new NameComponent[1];
      client1Name[0] = new NameComponent("client1", "");
      NamingContext ncC1 = nc.new_context();
      ncA.rebind_context(client1Name, ncC1);

      // Client 2
      NameComponent[] client2Name = new NameComponent[1];
      client2Name[0] = new NameComponent("client2", "");
      NamingContext ncC2 = nc.new_context();
      ncA.rebind_context(client2Name, ncC2);

      //compte1 client 1
      NameComponent[] aNameC1cpt1 = new NameComponent[1];
      aNameC1cpt1[0] = new NameComponent("compte1", "");
      ncC1.rebind(aNameC1cpt1, poaobj1);

      //compte1 client 1
      NameComponent[] aNameC1cpt2 = new NameComponent[1];
      aNameC1cpt2[0] = new NameComponent("compte2", "");
      ncC1.rebind(aNameC1cpt2, poaobj2);

      //compte1 client 1
      NameComponent[] aNameC2cpt1 = new NameComponent[1];
      aNameC2cpt1[0] = new NameComponent("compte1", "");
      ncC2.rebind(aNameC2cpt1, poaobj3);

      //compte1 client 1
      NameComponent[] aNameC2cpt2 = new NameComponent[1];
      aNameC2cpt2[0] = new NameComponent("compte2", "");
      ncC2.rebind(aNameC2cpt2, poaobj4);

      System.out.println("Le serveur est pret ");
      orb.run();
    }
    catch ( org.omg.CORBA.SystemException ex )
    {
      ex.printStackTrace();
    }

    catch ( org.omg.CORBA.UserException ex )
    {
      ex.printStackTrace();
    }
  }
}
