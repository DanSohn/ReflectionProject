package reflection;
import java.util.HashSet;
import java.util.Set;
import java.lang.reflect.*;

public class One {
	enum E { A, B }

	public static void main(String[] args){
		One one = new One();
		one.getClassNames();
		Two two = new Two();
		two.accessClass();
		two.createInstance();
		two.invokeMethod();
		}
	
	
	public void getClassNames(){
		//If an instance of an object is available:
		Class c = "foo".getClass();
		System.out.println(c.getName());
	
		Class cd = E.A.getClass();
		System.out.println(cd.getName());

		Set<String> s = new HashSet<String>();
		Class<? extends Set> e = s.getClass();
		System.out.println(c.getName());

		/* If the type is available but there is no instance then it is possible to obtain a Class by appending ".class" to the name of the type. */
		
		//String str;
		//Class clz = str.getClass();   // compile-time error

		Class<String> clzz = String.class;  // correct
		System.out.println(clzz.getName());
		
		//Another option for Primitive data types:
		
		Class<Void> voidType = Void.TYPE;
		System.out.println(voidType.getName());
		
		Class doubType = Double.TYPE;
		System.out.println(doubType.getName());
		

	}
}
