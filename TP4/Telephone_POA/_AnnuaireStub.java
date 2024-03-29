
/**
* _AnnuaireStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from Annuaire.idl
* mercredi 8 avril 2015 16 h 14 CEST
*/

public class _AnnuaireStub extends org.omg.CORBA.portable.ObjectImpl implements Annuaire
{

  public void chercheNom (String nom, org.omg.CORBA.StringHolder numero)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("chercheNom", true);
                $out.write_string (nom);
                $in = _invoke ($out);
                numero.value = $in.read_string ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                chercheNom (nom, numero        );
            } finally {
                _releaseReply ($in);
            }
  } // chercheNom

  public void listerNoms (org.omg.CORBA.StringHolder liste)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listerNoms", true);
                $in = _invoke ($out);
                liste.value = $in.read_string ();
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                listerNoms (liste        );
            } finally {
                _releaseReply ($in);
            }
  } // listerNoms

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Annuaire:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _AnnuaireStub
