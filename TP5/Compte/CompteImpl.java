import Comptes.ComptePOA;
//import org.omg.CORBA.*;
import java.io.*;

public class CompteImpl extends ComptePOA
{
  private double att_somme;
  public CompteImpl(double s)
  {
    att_somme = s;
  }

  public CompteImpl()
  {
    att_somme = 0;
  }

  // depose un certain montant sur un compte
  public void deposeBillets(double montant)
  {
    att_somme += montant;
    System.out.println("Vous avez : "+ att_somme +"€ sur votre compte");
  }

  // retrait d'argent du compte
  // découvert non autorisé
  public boolean retireBillets(double montant)
  {
    if(att_somme >= montant)
    {
      att_somme -= montant;
      System.out.println("-"+ montant +"€ sur votre compte");
      System.out.println("Il vous reste : "+ att_somme +"€ sur votre compte");
      return true;
    }
    else
    {
      System.out.println("Vous ne pouvez pas être à découvert");
      return false;
    }
  }

  // affiche le montant deposé sur le compte
  public double afficheMontant()
  {
    System.out.println("Vous avez " + att_somme + " €");
    return att_somme;
  }

  // vire une somme du compte courant vers le compte specifie en paramètre
  // si le montant du compte est insuffisant l'operation n'est pas realisé
  // et false est retourné
  public boolean virementCompteaCompte(double somme, Comptes.Compte cpt)
  {
    // TODO
    if(retireBillets(somme))
    {
      cpt.deposeBillets(somme);
      return true;
    }
    return false;
  }

  //sert à rien mais sinon l'implémentation déconne
  public double somme()
  {
    return 0;
  }
}
