package com.nashorn;

import com.nashorn.util.Constant;
import com.nashorn.util.EngineLoadUtils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.TypeVariable;
import java.util.Scanner;

import static com.nashorn.util.Constant.JAVASCRIPT_FUNCTION_PATH;

/**
 * @author i324779
 *         No need to restart the applicion or server,
 *         get java full name, using reflection to access fields and methods.
 *         <p>
 *         here use below function in the model.js:
 *         function getJavaFullName() {
 *         return "com.nashorn.entity.Person";
 *         }
 *         If you want to read diffrent content of file,
 *         just change the file path is fine.
 */
public class ReflectionInvokeDemo {
    public static void main(String[] args)
            throws FileNotFoundException, ScriptException, NoSuchMethodException, ClassNotFoundException {

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Please input one number:");
            while (input.hasNextInt()) {
                int number = input.nextInt();

                String name = getJavaFullName();
                System.out.printf("The Java full name is %s%n", name);

                Class<?> clazz = Class.forName(name);
                String classDescription = getClassDescription(clazz);
                System.out.println(classDescription);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * load function in model.js file to get Java full name.
     *
     * @return Java full name.
     * @throws FileNotFoundException
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    private static String getJavaFullName()
            throws FileNotFoundException, ScriptException, NoSuchMethodException {
        String javaFullName = "";
        ScriptEngine engine = EngineLoadUtils.loadNashornEngine();

        engine.eval(new FileReader(JAVASCRIPT_FUNCTION_PATH));
        if (engine instanceof Invocable) {
            Invocable in = (Invocable) engine;
            // execute javascript function in the model.js file.
            javaFullName = (String) in.invokeFunction("getJavaFullName");
        }

        return javaFullName;
    } // end method getJavaFullName

    /**
     * get the description fo a class,
     * including extends super class, and implements interface, generic info.
     * @param clazz class
     * @return the description of a class
     */
    private static String getClassDescription(Class clazz) {
        StringBuilder classDesc = new StringBuilder();

        // Prepare the modifiers and construct keyword (class, enum, interface etc.)
        int modifierBits = 0;
        String keyword = "";

        // Add keyword @interface, interface or class
        if (clazz.isPrimitive()) {
            // We do not want to add anything
        } else if (clazz.isInterface()) {
            modifierBits = clazz.getModifiers() & Modifier.interfaceModifiers();

            // AN annotation is an interface
            if (clazz.isAnnotation()) {
                keyword = "@interface";
            } else {
                keyword = "interface";
            }
        } else if (clazz.isEnum()) {
            modifierBits = clazz.getModifiers() & Modifier.classModifiers();
            keyword = "enum";
        } else {
            modifierBits = clazz.getModifiers() & Modifier.classModifiers();
            keyword = "class";
        }

        // Convert modifiers to their string represenation
        String modifiers = Modifier.toString(modifierBits);

        // Append modifiers
        classDesc.append(modifiers);

        // Append the construct keyword
        classDesc.append(" " + keyword);

        // Append simple name
        String simpleName = clazz.getSimpleName();
        classDesc.append(" " + simpleName);

        // Append generic parameters
        String genericParms = getGenericTypeParams(clazz);
        classDesc.append(genericParms);

        // Append super class
        Class superClass = clazz.getSuperclass();
        if (superClass != null) {
            String superClassSimpleName = superClass.getSimpleName();
            classDesc.append(" extends " + superClassSimpleName);
        }

        // Append Interfaces
        String interfaces = ReflectionInvokeDemo.getClassInterfaces(clazz);
        if (interfaces != null) {
            classDesc.append(" implements " + interfaces);
        }

        return classDesc.toString();
    } // end method getClassDescription

    /**
     * get interfaces in a class
     * @param c class
     * @return interface names
     */
    private static String getClassInterfaces(Class c) {
        // Get a comma-separated list of interfaces implemented by the class
        Class[] interfaces = c.getInterfaces();
        String interfacesList = null;

        if (interfaces.length > 0) {
            String[] interfaceNames = new String[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                interfaceNames[i] = interfaces[i].getSimpleName();
            }
            interfacesList = String.join(", ", interfaceNames);
        }

        return interfacesList;
    } // end method getClassInterfaces

    /**
     * get the generic type parameters in a class
     * @param c class
     * @return generic type parameters
     */
    private static String getGenericTypeParams(Class c) {
        StringBuilder sb = new StringBuilder();
        TypeVariable<?>[] typeParms = c.getTypeParameters();

        if (typeParms.length > 0) {
            String[] paramNames = new String[typeParms.length];
            for (int i = 0; i < typeParms.length; i++) {
                paramNames[i] = typeParms[i].getTypeName();
            }

            sb.append('<');
            String parmsList = String.join(",", paramNames);
            sb.append(parmsList);
            sb.append('>');
        }

        return sb.toString();
    } // end method getGenericTypeParams
}
