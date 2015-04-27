import java.io.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;

public class Client2
{

  public static Comptes.Compte refCompte(ORB orb, String args[], String ref)
  {
    String reference = "corbaname::" + args[0] + ":" + args[1] + ref;
    org.omg.CORBA.Object obj = orb.string_to_object(reference);

    if (obj == null)
    {
      System.out.println("Acces impossible a l'objet corba");
      System.exit(1);
    }

    Comptes.Compte res = Comptes.CompteHelper.narrow(obj);

    if (res == null)
    {
      System.err.println("Erreur sur narrow()");
      System.exit(0);
    }

    return res;
  }

  public static void main( String args[] )  {

    try
    {
      if (args.length != 2)
      {
    		System.out.println("Usage : java Client <machineServeurDeNoms> <port>");
    		return;
	    }

      ORB orb = ORB.init(new String[0], null );


      Comptes.Compte c1_cpt1 = refCompte(orb,args,"#agence/client1/compte1");
      Comptes.Compte c1_cpt2 = refCompte(orb,args,"#agence/client1/compte2");
      Comptes.Compte c2_cpt1 = refCompte(orb,args,"#agence/client2/compte1");
      Comptes.Compte c2_cpt2 = refCompte(orb,args,"#agence/client2/compte2");

      c1_cpt1.deposeBillets(100.50);
      c1_cpt2.deposeBillets(300);
      c2_cpt1.deposeBillets(150);
      c2_cpt2.deposeBillets(500);

      System.out.println(c1_cpt1.afficheMontant());
      System.out.println(c1_cpt2.afficheMontant());
      System.out.println(c2_cpt1.afficheMontant());
      System.out.println(c2_cpt2.afficheMontant());
      System.out.println();

      c1_cpt1.retireBillets(10);
      c1_cpt2.retireBillets(150);
      c2_cpt1.retireBillets(70);
      c2_cpt2.retireBillets(30.50);

      System.out.println(c1_cpt1.afficheMontant());
      System.out.println(c1_cpt2.afficheMontant());
      System.out.println(c2_cpt1.afficheMontant());
      System.out.println(c2_cpt2.afficheMontant());
      System.out.println();

      c1_cpt1.virementCompteaCompte(300, c2_cpt2);

      System.out.println(c1_cpt1.afficheMontant());
      System.out.println(c1_cpt2.afficheMontant());
      System.out.println(c2_cpt1.afficheMontant());
      System.out.println(c2_cpt2.afficheMontant());

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
