import java.lang.*;
import java.lang.reflect.*;

public class Inspector {
    public void inspect(Object obj, boolean recursive){
        Class c = obj.getClass();
        //System.out.println(c.getName());
        inspectClass(c, recursive, 0);
    }

    private void inspectClass(Class c, boolean recursive, int depth){
        inspectSuperClass(c, recursive, depth);
        inspectInterface(c, recursive, depth);
        inspectConstructor(c, depth);
        //inspectMethod(c, depth);
        //inspectField(c, recursive, depth);
    }

    private void inspectSuperClass(Class c, boolean recursive, int depth){
        //System.out.println("inspecting super class of " + c);
        // handling formatting
        String tab = "";
        for(int i=0; i<depth; i++){
            tab += "\t";
        }

        //print class we are currently looking at
        System.out.println(tab + "CLASS: " + c.getName());

        Class superClass = c.getSuperclass();

        // handle case when class and superclass both equal object
        if(c.equals(Object.class) && superClass == null){
            return;
        }
        //print next superclass
        System.out.println(tab + "\tSUPERCLASS: " + superClass.getName());
        inspectClass(superClass, recursive, depth+2);

    }

    private void inspectInterface(Class c, boolean recursive, int depth){
        //System.out.println("inspecting interface for " + c);
        depth += 1;
        Class[] interfaces = c.getInterfaces();

        // handling formatting
        String tab = "";
        for(int i=0; i<depth; i++){
            tab += "\t";
        }

        if(interfaces.length==0){
            System.out.println(tab + "*** NO SUPERINTERFACE ***");
        }else{
            // class does have interfaces
            for(int i = 0; i < interfaces.length; i++){
                System.out.println(tab + "INTERFACE: " + interfaces[i]);
                inspectInterface(interfaces[i], recursive, depth);
                inspectMethod(interfaces[i], depth);

            }
        }
    }

    private void inspectConstructor(Class c, int depth){
        depth += 1;
        // handling formatting
        String tab = "";
        for(int i=0; i<depth; i++){
            tab += "\t";
        }
        String tab2 = tab + "\t";
        // list of each constructors for a class
        Constructor[] constructors = c.getConstructors();
        for (int i = 0; i < constructors.length; i++){
            System.out.println(tab + "CONSTRUCTOR:");
            System.out.println(tab2 + "Name: " + constructors[i].getName());

            //parameters
            Class[] parameterTypes = constructors[i].getParameterTypes();
            System.out.print(tab2 + "Parameter Types: ");
            if (parameterTypes.length == 0){
                System.out.println("None");
            }else {
                for (Class parameter : parameterTypes) {
                    System.out.print(parameter.getName() + " ");
                }
                System.out.println("");
            }

            System.out.println(tab2 + "Modifier: " + constructors[i].getModifiers());
        }
    }

    private void inspectMethod(Class c, int depth){
        
    }

    private void inspectField(Class c, boolean recursive, int depth){

    }

}
