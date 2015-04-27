import org.omg.CORBA.*;
import java.util.*;

public class AnnuaireImpl extends AnnuairePOA
{
  Hashtable numeros = new Hashtable();

  public AnnuaireImpl() {
    super();
    numeros.put("tintin", "06 76 70 80 09");
    numeros.put("milou", "06 50 40 36 76");
    numeros.put("tournesol", "06 07 33 72 06");
  }

  public void chercheNom(String nom,  StringHolder numero)
  {
    numero.value = (String) numeros.get(nom);
  	if (numero.value == null)
    {
      numero.value = "Nom inconnu : " + nom;
    }
  }

  public void listerNoms(StringHolder liste)
  {
    String res = " " ;
  	Iterator it = numeros.keySet().iterator();
  	while (it.hasNext()) {
  	    String nom = (String) it.next();
  	    res += nom + " : " + numeros.get(nom) + " \n " ;
  	}
    liste.value = res;
  }
}
