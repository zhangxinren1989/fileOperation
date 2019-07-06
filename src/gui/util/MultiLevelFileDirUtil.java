package gui.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 多级目录操作：操作多级目录下的文件和文件夹
 * @author zhangxinren
 *
 */
public class MultiLevelFileDirUtil {
	private MultiLevelFileDirUtil() {}
	
	/**
	 * 广度优先获取所有文件（或/和）文件夹
	 * @param directory 根目录
	 * @param mode 1：仅获取文件，2：仅获取目录，3：同时获取文件和目录
	 * @return
	 */
	public static List<File> breadthFirstFileDirectory(File directory, int mode)
	{
		List<File> result = new ArrayList<>();
		LinkedBlockingQueue<File> dirs = new LinkedBlockingQueue<>();
		
		if(null == directory || directory.isFile()) {
			return result;
		}
		
		dirs.offer(directory);
		
		while(!dirs.isEmpty())
		{
			File tempFile = dirs.poll();
			
			if(null != tempFile)
			{
				for(File f: tempFile.listFiles())
				{
					if((mode == 1 && f.isFile()) || (mode == 2 && f.isDirectory()) || mode == 3)
					{
						result.add(f);
					}
					
					if(f.isDirectory())
					{
						dirs.offer(f);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * getAllSubDir:获取一个目录的所有子目录. <br/>
	 * 
	 * @author xrzhang
	 * @param basePath
	 * @return
	 * @since 2019年4月19日 上午10:18:49
	 */
	public static List<File> getAllSubDir(File baseDir)
	{
		List<File> result = new ArrayList<File>();
		if(baseDir.isDirectory())
		{
			getAllSubDirOrFile(baseDir, result, true);
		}

		return result;
	}

	/**
	 * 
	 * getAllSubFile:获取一个目录的所有子文件. <br/>
	 * 
	 * @author xrzhang
	 * @param baseDir
	 * @return
	 * @since 2019年4月19日 上午10:37:49
	 */
	public static List<File> getAllSubFile(File baseDir)
	{
		List<File> result = new ArrayList<File>();
		if(baseDir.isDirectory())
		{
			getAllSubDirOrFile(baseDir, result, false);
		}

		return result;
	}

	/**
	 * 
	 * getAllSubDir:获取一个文件夹的所有子文件夹 <br/>
	 * 
	 * @author xrzhang
	 * @param baseDir
	 * @param subDirs
	 * @param isDir true：获取所有子目录,false：获取所有子文件
	 * @since 2019年4月19日 上午10:24:17
	 */
	private static void getAllSubDirOrFile(File baseDir, List<File> subEles, boolean isDir)
	{
		if(baseDir.isDirectory())
		{
			for(File f : baseDir.listFiles())
			{
				if(f.isDirectory())
				{
					if(isDir)
					{
						subEles.add(f);
					}
					getAllSubDirOrFile(f, subEles, isDir);
				}
				else if(f.isFile() && !isDir)
				{
					subEles.add(f);
				}
			}
		}
	}
}
