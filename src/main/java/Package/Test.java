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

public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
//		// đọc vào file
//		ParseResult<CompilationUnit> parseResult = null;
//		FileInputStream in = new FileInputStream("src/main/java/Package/Student.java");
//		try {
//			JavaParser parse = new JavaParser();
//			// parse the file
//			parseResult = parse.parse(in);
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
		String folderPath = "src/main/java/Package/Student.java"; // Thay đổi đường dẫn của bạn

        // Lấy danh sách các tệp Java trong thư mục (bao gồm cả thư mục con)
        List<File> javaFiles = getJavaFiles(new File(folderPath));

        // Thực hiện hành động cho mỗi tệp Java
        for (File javaFile : javaFiles) {
            processJavaFile(javaFile);
        }
	}
	private static List<File> getJavaFiles(File folder) {
        List<File> javaFiles = new ArrayList<File>();
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Nếu là thư mục, đệ quy để lấy tất cả các tệp Java trong thư mục con
                    javaFiles.addAll(getJavaFiles(file));
                } else if (file.isFile() && file.getName().endsWith(".java")) {
                    // Nếu là tệp Java, thêm vào danh sách
                    javaFiles.add(file);
                }
            }
        }

        return javaFiles;
    }
//  private void getFile(String folderName) throws Exception {
//	  File folder = new File(folderName);
//	  File [] files = folder.listFiles();
//	  if (files !=null) {
//		  for (File file : files) {
//              if (file.isDirectory()) {
//            	  }
//              System.out.println(file.getAbsolutePath());
//              }
//                 
//	}
//  }
	private static void processJavaFile(File javaFile) {
        try {
            // Đọc tệp Java và thực hiện các hành động từ câu hỏi 1
            FileInputStream in = new FileInputStream(javaFile);
            JavaParser parse = new JavaParser();
            ParseResult<CompilationUnit> parseResult = parse.parse(in);
            Optional<CompilationUnit> optional = parseResult.getResult();
            if (optional.isPresent()) {
                CompilationUnit cu = optional.get();
                getFields(cu);
                System.out.println("======================");
                getMethods(cu);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
