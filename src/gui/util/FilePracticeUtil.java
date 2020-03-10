package gui.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.IdentifierHelper;

public class FilePracticeUtil {
	private FilePracticeUtil() {
	}

	/**
	 * 删除一个文件夹下的所有内容，再删除自身
	 * @param folderPath
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			File selfFolder = new File(folderPath);
			selfFolder.delete(); // 删除空文件夹
			System.gc();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除路径下的所有内容
	 * @param path
	 * @return
	 */
	public static boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		File[] tempList = file.listFiles();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			temp = tempList[i];
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delFolder(temp.getAbsolutePath());// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * countLines:统计文件行数 <br/>
	 * @author zhangxinren
	 * @param f
	 * @return
	 * @since 2020年1月1日 下午1:56:26
	 */
	public static long countFileLines(File f) {
		int res = 0;
		LineNumberReader lineNumberReader = null;
		
		if(f != null && f.exists()) {
			try {
	    		if(f.exists()){
	    			long fileLength = f.length();
	    			lineNumberReader = new LineNumberReader(new FileReader(f));
	    			lineNumberReader.skip(fileLength);
	    			res = lineNumberReader.getLineNumber();
	    		}else {
	    			System.out.println("File does not exists!");
	    		}
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}finally {
	    		if(lineNumberReader != null) {
	    			try
					{
						lineNumberReader.close();
					}
					catch(IOException e)
					{
						e.printStackTrace();
						
					}
	    		}
			}
		}
		
		return res;
	}
	
	/**
	 * 
	 * countFilesLines:统计某种扩展名文件的数量及行数. <br/>
	 * @author zhangxinren
	 * @param dir
	 * @return
	 * @since 2020年1月1日 下午2:10:21
	 */
	public static Map<String, long[]> countFilesLines(File dir) {
		if(null == dir || !dir.exists() || !dir.isDirectory()) {
			return null;
		}
		
		Map<String, List<File>> typeFiles = new HashMap<String, List<File>>();
		Map<String, long[]> res = new HashMap<>();
		long[] counts;
		
		try
		{
			Files.walkFileTree(Paths.get(dir.toURI()), new SimpleFileVisitor<Path>() {
				List<File> files;
				
				@Override
				public FileVisitResult visitFileFailed(Path path, IOException e) throws IOException
				{
					System.out.println("访问文件：" + path.getFileName() + "失败！失败原因：" + e.getMessage());
					return FileVisitResult.CONTINUE;
				}
				
				@Override
				public FileVisitResult visitFile(Path path, BasicFileAttributes attributes) throws IOException
				{
					String extendName = FileUtil.getExtendName(path.getFileName().toString());
					files = typeFiles.getOrDefault(extendName, new ArrayList<File>());
					files.add(path.toFile());
					typeFiles.put(extendName, files);
					return FileVisitResult.CONTINUE;
				}
			});
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		for(String key: typeFiles.keySet()) {
			counts = new long[2];
			counts[0] = typeFiles.get(key).size();
			for(File f: typeFiles.get(key)) {
				counts[1] += countFileLines(f);
			}
			res.put(key, counts);
		}
		
		return res;
	}
	
	public static Map<String, long[]> countFilesLines(String dir) {
		return countFilesLines(new File(dir));
	}
	
	/*public static void main(String[] args)
	{
		countFilesLines("G:\\dev\\blog\\jpress\\jpress");
	}*/
}
