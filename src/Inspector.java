import java.lang.*;
import java.lang.reflect.*;

public class Inspector {
    public void inspect(Object obj, boolean recursive){
        Class c = obj.getClass();
        //System.out.println(c.getName());
        inspectClass(c, obj, recursive, 0);
    }

    private void inspectClass(Class c, Object obj, boolean recursive, int depth){
        inspectSuperClass(c, obj, recursive, depth);
        inspectInterface(c, obj, recursive, depth);
    }

    private void inspectSuperClass(Class c, Object obj, boolean recursive, int depth){
        Class superClass = c.getSuperclass();

        // handling formatting
        String tab = "";
        for(int i=0; i<depth; i++){
            tab += "\t";
        }

        //print class we are currently looking at
        System.out.println(tab + "CLASS: " + c.getName());

        // Check if I reached the object class
        if (!c.equals(Object.class)){
            System.out.println(tab + "\tSUPERCLASS: " + superClass.getName());
            inspectClass(superClass, obj, recursive, depth+2);
        }
    }

    private void inspectInterface(Class c, Object obj, boolean recursive, int depth){
        Class[] interfaces = c.getInterfaces();

        // handling formatting
        String tab = "";
        for(int i=0; i<depth; i++){
            tab += "\t";
        }

        if(interfaces.length==0){
            System.out.println(tab + "NO INTERFACES FOR THIS CLASS");
        }else{
            // class does have interfaces
            for(int i = 0; i < interfaces.length; i++){
                inspectClass(interfaces[i], obj,recursive, depth + 2);
            }
        }
    }

    private void inspectConstructor(Class c, Object obj, int depth){

    }

    private void inspectMethod(Class c, Object obj, int depth){

    }

    private void inspectField(Class c, Object obj, boolean recursive, int depth){

    }

}
