package reflection;
import java.lang.*;
import java.lang.reflect.*;
import static java.lang.System.out;
import static java.lang.System.err;
//Add class folder to build path
public class Two {
	Class clz;
	Constructor constructor;
	Object obj;
	Method meth;
	public void accessClass() {
		out.println("------------------------------------------------------\n\n");
		try {
			clz =  Class.forName("mypackage.Movie");		//Use fully qualified name to load class
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(clz!=null) {

			out.println("Class Name: " + clz.getName());
			out.println("Is Interface: " + clz.isInterface());
			//For fields
			if(clz.getMethods().length>0){
				inspectMethods();
			}
			//For fields
			if(clz.getFields().length>0){

				inspectFields();
			}
			//For parent class and interfaces
			inspectInheritance();
		}
	}
	private void inspectInheritance() {
		Class superClz = clz.getSuperclass();
		Class[] interfaces = clz.getInterfaces();
		out.println("Parent Class: " + superClz.getName());
		for(Class cl: interfaces) {
			out.println("Parent Interface: " +cl.getName());

		}
	}
	private void inspectFields() {
		out.println("Fields: " );
		Field[] fields = clz.getFields();
		for(Field f : fields) {
			out.println(" Modifier: "+ Modifier.toString(f.getModifiers()) + " \n   Type: "+ f.getType() + " \n   Name:" + f.getName() );
		}
	}
	private void inspectMethods() {

		out.println("Methods: " );
		Method[] methods = clz.getDeclaredMethods();
		for(Method m : methods) {
			out.println("   Modifier: "+ Modifier.toString(m.getModifiers())  + " \n   Return Type: "+ m.getReturnType() +   " \n   Name:"  + m.getName() );
			Parameter[] param = m.getParameters();	
			out.println("Parameters: " );

			for(Parameter p:param){
				out.println("   Type: " + p.getType());
				out.println("   Name: " + p.getName());
			}
			out.println();
		}
	}
	public void createInstance() {
		// Class::newInstance bypasses the exception handling - which you really don't want.
		Class[] cArg = new Class[2]; 		//Our constructor has 2 arguments
		cArg[0] = String.class; 			//First argument is of *object* type Long
		cArg[1] = int.class; 				//Second argument is of *object* type String
		try {			
			constructor = clz.getDeclaredConstructor(cArg);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}

		try {
			obj = constructor.newInstance("lord of the rings",0);

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			out.println("handle 3 called");
			e.printStackTrace();
		}


	}
	public void invokeMethod()
	{
		try {
			meth = clz.getMethod("priceCode");

			meth.setAccessible(true);
			Object o = meth.invoke(obj);
			out.format("%s() returned %d", meth.getName(), (int) o);

			// Handle any exceptions thrown by method to be invoked.
		} catch (InvocationTargetException x) {
			x.printStackTrace();	
		} catch (IllegalAccessException x) {
			x.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
