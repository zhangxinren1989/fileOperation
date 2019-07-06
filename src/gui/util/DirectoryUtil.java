package gui.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryUtil {
	private DirectoryUtil(){}
	
	/**
	 * 文件夹下是否包含目录
	 * @param directory
	 * @return
	 */
	public static boolean isContainDirectory(File directory)
	{
		boolean result = false;
		if(directory.isDirectory())
		{
			for(File f: directory.listFiles())
			{
				if(f.isDirectory())
				{
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 从文件夹一级目录中找出文件夹
	 * @param directory
	 * @return
	 */
	public static List<File> listDirectories(File directory)
	{
		List<File> result = new ArrayList<>();
		if(directory.isDirectory())
		{
			for(File f: directory.listFiles())
			{
				if(f.isDirectory())
				{
					result.add(f);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 从一组File中取出文件夹
	 * @param files
	 * @return
	 */
	public static List<File> getDirectories(List<File> files)
	{
		List<File> result = new ArrayList<>();
		if(null != files && files.size() > 0)
		{
			for(File f: files)
			{
				if(f.isDirectory())
				{
					result.add(f);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * getAllFiles:找出一个文件夹中的所有文件和文件夹（包括子目录）. <br/>
	 * @author zhangxinren
	 * @param dir
	 * @return
	 * @since 2019年6月8日 上午11:13:33
	 */
	public static List<File> getAllFiles(File dir)
	{
		List<File> allFiles = new ArrayList<>();
		getAllFiles(dir, allFiles);
		
		return allFiles;
	}
	
	/**
	 * getAllFiles:找出一个文件夹中的所有文件和文件夹（包括子目录）. <br/>
	 * @author zhangxinren
	 * @param dir
	 * @param allFiles
	 * @since 2019年6月8日 上午11:21:43
	 */
	private static void getAllFiles(File dir, List<File> allFiles)
	{
		
		if(dir.isDirectory())
		{
			for(File f: dir.listFiles())
			{
				allFiles.add(f);
				
				if(f.isDirectory())
				{
					getAllFiles(f, allFiles);
				}
			}
		}
	}
}
