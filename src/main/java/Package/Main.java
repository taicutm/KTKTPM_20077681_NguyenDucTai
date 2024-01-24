package Package;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.naming.ldap.PagedResultsControl;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class Main {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

//		// đọc vào file
//		ParseResult<CompilationUnit> parseResult = null;
//		FileInputStream in = new FileInputStream("src/main/java/Package/Student.java");
//		try {
//			JavaParser parse = new JavaParser();
//			// parse the file
//			parseResult = parse.parse(in);
//			
//			Optional<CompilationUnit> optional = parseResult.getResult();
//			if (optional.isPresent()) {
//				CompilationUnit cu = optional.get();
//				getFields(cu);
//				System.out.println("======================");
//				getMethods(cu);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			in.close();
//		}
		List<String> models = folder("src/main/java/Package/");
		for (String model: models) {
			FileInputStream in = new FileInputStream(model);
			ParseResult<CompilationUnit> parseResult = null;
			
			try {
				JavaParser parser = new JavaParser();
				
				parseResult = parser.parse(in);
				Optional<CompilationUnit> optional = parseResult.getResult();
				if (optional.isPresent()) {
					CompilationUnit cu = optional.get();
					getFields(cu);
					System.out.println("===============");
					getMethods(cu);
					System.out.println("===============");
					
				} else {

				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			finally {
				in.close();

			}
			}
	}
	public static List<String> folder(String folderName) throws Exception{
		File folder = new File(folderName);
		File [] files = folder.listFiles();
		List<String> list = new ArrayList() ;
		for (File file: files) {
//			if(file.isDirectory())
//				folder(file);
			list.add(file.getAbsolutePath());
		}
		return list;
	}

	private static void getMethods(CompilationUnit cu) {
		// TODO Auto-generated method stub
		List<MethodDeclaration> methods = cu.findAll(MethodDeclaration.class);
		for (MethodDeclaration method : methods) {
			System.out.println("Method Name Printed: "+method.getName());
			System.out.println("Type Method Name Printed: "+method.getType());
			System.out.println("Modifiers:: "+method.getModifiers());
			System.out.println("Parameters:: "+method.getParameters());
			System.out.println("======");

		}
		
	}

	private static void getFields(CompilationUnit cu) {
		// TODO Auto-generated method stub
		List<FieldDeclaration> fields = cu.findAll(FieldDeclaration.class);
		for (FieldDeclaration field : fields) {
			System.out.println(field);
		}
	}

}
