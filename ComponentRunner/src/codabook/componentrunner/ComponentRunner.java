package codabook.componentrunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import codabook.componentmodel.ComponentRegistry;
import codabook.componentmodel.ComponentInterface;

/*************************************************************************************************
*
* COMPANION CODE FOR THE BOOK �Component Oriented Development & Assembly � CODA Using Java�
* 
* @author � Piram Manickam, Sangeetha S, Subrahmanya S V
* @see -  http://www.codabook.com
* 
* <br><br><b>CODE CONTRIBUTORS</b> � <p>- Vishal Verma, Shikhar Johari, Soumya Bardhan, Rohit Jain,
* Karthika Nair, Vibhuti Pithwa, Vaasavi Lakshmi</p>

**************************************************************************************************/
public class ComponentRunner {

	public static void main(String[] args) {

		if (args.length != 1) {
			System.out
					.println("ERROR - Usage: ComponentRunner <fullyQualifiedClassNameOfMainClass>");
			return;
		}

		
		Class<?> mainClazz = null;
		System.out.println(args[0]);
		try {
			mainClazz = Class.forName(args[0]);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR - Unable to load main class " + args[0]);
			return;
		}

		if (mainClazz == null) {
			System.out.println("ERROR - Unable to load main class " + args[0]);
			return;
		}

		Method mainMethod = null;

		try {
			mainMethod = mainClazz.getMethod("main", String[].class);
		} catch (Exception e) {
			System.out.println("ERROR - Unable to load main method in the class " + args[0]);
			return;
		}

		if (mainMethod == null) {
			System.out.println("ERROR - Unable to load main method in the class " + args[0]);
			return;
		}

		File currentFolder = new File(".");
		File[] allFiles = currentFolder.listFiles();

		
		if (allFiles == null)
			return;

		for (File aFile : allFiles) {
			if (!aFile.getName().toLowerCase().endsWith(".jar"))
				continue;

			JarFile jarFile = null;

			try {
				jarFile = new JarFile(aFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (jarFile == null)
				continue;

			//System.out.println("Examining Jar File : " + jarFile.getName());

			Enumeration<JarEntry> jarEntries = jarFile.entries();

			while (jarEntries.hasMoreElements()) {

				JarEntry jarEntry = jarEntries.nextElement();

				if (jarEntry.isDirectory())
					continue;

				if (!jarEntry.getName().endsWith(".class"))
					continue;

				String jarEntryClassName = jarEntry.getName().replace('/', '.');

				String className = jarEntryClassName.substring(0,
						jarEntryClassName.indexOf(".class"));

				//System.out.println("    Loading class : " + className);

				Class<?> clazz = null;

				try {
					clazz = Class.forName(className);
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (clazz == null)
					continue;

				if (clazz.isInterface())
					continue;

				try {

					if (clazz.asSubclass(ComponentInterface.class) == null)
						continue;

					Class<?>[] interfaces = clazz.getInterfaces();

					Object compObject = clazz.newInstance();

					for (Class<?> ifceClazz : interfaces) {
						if (ifceClazz == ComponentInterface.class)
							continue;
						//System.out.println("Registering provided interface : "
							//	+ ifceClazz.getName());
						ComponentRegistry.registerComponent(ifceClazz, compObject);
					}

				} catch (Exception e) {
					//e.printStackTrace();
				}

			}

		}

		//System.out.println("Executing main method: " + mainMethod.toString());

		try {
			String[] params = null;
			mainMethod.invoke(null, (Object) params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
