import java.io.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Client
{
  public static void main( String args[] )  {

    try
    {
      if (args.length != 2)
      {
    		System.out.println("Usage : java Client <machineServeurDeNoms> <port>");
    		return;
	    }

      // String [] argv = {"-ORBInitialHost",args[0],"-ORBInitialPort",args[1]};
	    // ORB orb = ORB.init( argv, null );

      // String ior = "";
      //
      // try
      // {
      //   FileReader file = new FileReader(iorfile.value);
      //   BufferedReader in = new BufferedReader(file);
      //   ior = in.readLine();
      //   file.close();
      // }
      // catch(IOException ex)
      // {
      //   System.out.println (ex.toString());
      // }
      //
      // System.out.println( "1) IOR lue : " + ior );

      ORB orb = ORB.init(new String[0], null );

      // Compte1
      String reference = "corbaname::" + args[0] + ":" + args[1] + "#compte1";
      org.omg.CORBA.Object obj = orb.string_to_object(reference);

      if (obj == null)
      {
        System.out.println("Acces impossible a l'objet corba");
        System.exit(1);
      }

      Comptes.Compte cpt1 = Comptes.CompteHelper.narrow(obj);

      if (cpt1 == null)
      {
        System.err.println("Erreur sur narrow()");
        System.exit(0);
      }

      // Compte2
      reference = "corbaname::" + args[0] + ":" + args[1] + "#compte2";
      org.omg.CORBA.Object obj2 = orb.string_to_object(reference);

      if (obj2 == null)
      {
        System.out.println("Acces impossible a l'objet corba");
        System.exit(1);
      }

      Comptes.Compte cpt2 = Comptes.CompteHelper.narrow(obj2);

      if (cpt2 == null)
      {
        System.err.println("Erreur sur narrow()");
        System.exit(0);
      }

      cpt1.deposeBillets(200.50);
      cpt2.deposeBillets(500.25);

      System.out.println(cpt1.afficheMontant());
      System.out.println(cpt2.afficheMontant());

      cpt1.retireBillets(40);
      cpt2.retireBillets(70);
      System.out.println(cpt1.afficheMontant());
      System.out.println(cpt2.afficheMontant());

      cpt1.virementCompteaCompte(50, cpt2);

      System.out.println(cpt1.afficheMontant());
      System.out.println(cpt2.afficheMontant());

     }
     catch( org.omg.CORBA.SystemException ex )
     {
       ex.printStackTrace();
     }
    //  catch( org.omg.CORBA.UserException ex )
    //  {
    //    ex.printStackTrace();
    //  }
  }
}
