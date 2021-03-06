package server.api.corba.generated;


/**
* server.api.corba.generated/_DatabaseInterfaceStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server/api/corba/Database.idl
* Sunday, December 3, 2017 9:57:04 PM EET
*/

public class _DatabaseInterfaceStub extends org.omg.CORBA.portable.ObjectImpl implements server.api.corba.generated.DatabaseInterface
{

  public String[] listSchemaNames ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listSchemaNames", true);
                $in = _invoke ($out);
                String $result[] = server.api.corba.generated.StringArrayHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listSchemaNames (        );
            } finally {
                _releaseReply ($in);
            }
  } // listSchemaNames

  public void createSchema (String name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("createSchema", true);
                $out.write_string (name);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                createSchema (name        );
            } finally {
                _releaseReply ($in);
            }
  } // createSchema

  public server.api.corba.generated.TransferSchema loadSchema (String name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("loadSchema", true);
                $out.write_string (name);
                $in = _invoke ($out);
                server.api.corba.generated.TransferSchema $result = server.api.corba.generated.TransferSchemaHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return loadSchema (name        );
            } finally {
                _releaseReply ($in);
            }
  } // loadSchema

  public void deleteSchema (String name)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteSchema", true);
                $out.write_string (name);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                deleteSchema (name        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteSchema

  public server.api.corba.generated.TransferTable readTable (String schemaName, String tableName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("readTable", true);
                $out.write_string (schemaName);
                $out.write_string (tableName);
                $in = _invoke ($out);
                server.api.corba.generated.TransferTable $result = server.api.corba.generated.TransferTableHelper.read ($in);
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return readTable (schemaName, tableName        );
            } finally {
                _releaseReply ($in);
            }
  } // readTable

  public void writeTable (String schemaName, server.api.corba.generated.TransferTable table)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("writeTable", true);
                $out.write_string (schemaName);
                server.api.corba.generated.TransferTableHelper.write ($out, table);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                writeTable (schemaName, table        );
            } finally {
                _releaseReply ($in);
            }
  } // writeTable

  public void deleteTable (String schemaName, String tableName)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("deleteTable", true);
                $out.write_string (schemaName);
                $out.write_string (tableName);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                deleteTable (schemaName, tableName        );
            } finally {
                _releaseReply ($in);
            }
  } // deleteTable

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:server.api.corba.generated/DatabaseInterface:1.0"};

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
} // class _DatabaseInterfaceStub
