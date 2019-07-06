package gui.fileoperation.modify;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.util.DirectoryUtil;
import gui.util.FileUtil;
import gui.util.MultiLevelFileDirUtil;

/**
 * 多级文件（夹）的修改
 * @author zhangxinren
 *
 */
public class MultiLevelModify {
	private MultiLevelModify(){}
	
	/**
	 * 处理多级文件（夹）按前缀数字后缀改名，广度优先
	 * @param directory 待处理的文件夹
	 * @param pre 前缀
	 * @param suf 后缀
	 * @param start 开始数字
	 * @param step 数字增量
	 * @param fileType 要处理类型：文件（或/和）文件夹
	 * @param seperateFileDirectory 是否文件和文件夹一起命名
	 * @param seperateEveryLevel 是否多级文件夹一起命名
	 * @param extendNames 文件扩展名
	 * @param nonExtendNames 非文件扩展名
	 */
	public static void preNumPostModify(File directory, String pre, String suf, int start, int step, int fileType,
			boolean seperateFileDirectory, boolean seperateEveryLevel, List<String> extendNames, List<String> nonExtendNames) {
		switch(fileType)
		{
			case 1:
				if(seperateEveryLevel)
				{
					preNumPostModifySeperateLevelMultiFile(directory, pre, suf, start, step, extendNames, nonExtendNames);
				}
				else 
				{
					preNumPostModifyMultiFile(directory, pre, suf, start, step, extendNames, nonExtendNames);
				}
				break;
			case 2:
				if(seperateEveryLevel)
				{
					preNumPostModifySeperateLevelMultiDirectory(directory, pre, suf, start, step);
				}
				else 
				{
					preNumPostModifyMultiDirectory(directory, pre, suf, start, step);
				}
				break;
			case 3:
				if(seperateEveryLevel)
				{
					preNumPostModifySeperateLevelMultiFileDirectory(directory, pre, suf, start, step, extendNames, nonExtendNames, seperateFileDirectory);
				}
				else 
				{
					preNumPostModifyMultiFileDirectory(directory, pre, suf, start, step, extendNames, nonExtendNames, seperateFileDirectory);
				}
				break;
			default:
		}
	}
	
	private static void preNumPostModifyMultiFileDirectory(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames, boolean seperateFileDirectory)
	{
		List<File> handleFiles = MultiLevelFileDirUtil.breadthFirstFileDirectory(directory, 3);
		
		if(handleFiles.size() > 0)
		{
			Collections.reverse(handleFiles);
			
			for(int i = handleFiles.size() - 1; i >= 0; i--)
			{
				File temp = handleFiles.get(i);
				if(temp.isFile())
				{
					String extendName = FileUtil.getExtendName(temp.getName());
					if(!((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
							|| ("".equals(extendName) && (extendNames.contains("全部") ||extendNames.contains("无扩展名")))))
					{
						handleFiles.remove(i);
					}
				}
			}
			
			int fileStart = 0;
			int dirStart = 0;
			if(!seperateFileDirectory)
			{
				fileStart = start + (handleFiles.size() - 1) * step;
				dirStart = start + (handleFiles.size() - 1) * step;
			}
			else
			{
				fileStart = start + (FileUtil.getFiles(handleFiles).size() - 1) * step;
				dirStart = start + (DirectoryUtil.getDirectories(handleFiles).size() - 1) * step;
			}
			
			for(File f: handleFiles)
			{
				if(!seperateFileDirectory)
				{
					start = fileStart > dirStart ? dirStart : fileStart;
					fileStart = start;
					dirStart = start;
				}
				if(f.isDirectory())
				{
					if(dirStart == 0)
					{
						f.renameTo(new File(f.getParent(), pre + suf));
					}
					else
					{
						f.renameTo(new File(f.getParent(), pre + dirStart + suf));
					}
					dirStart -= step;
				}
				else
				{
					String extendName = FileUtil.getExtendName(f.getName());
					if(start == 0)
					{
						f.renameTo(new File(f.getParent(), pre + suf + extendName));
					}
					else
					{
						f.renameTo(new File(f.getParent(), pre + fileStart + suf + extendName));
					}
					fileStart -= step;
				}
			}
		}
	}
	
	private static void preNumPostModifyMultiFile(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames)
	{
		List<File> handleFiles = MultiLevelFileDirUtil.breadthFirstFileDirectory(directory, 1);
		if(handleFiles.size() > 0)
		{
			Collections.reverse(handleFiles);
			start += step * (handleFiles.size() - 1);
			for(File f: handleFiles)
			{
				String extendName = FileUtil.getExtendName(f.getName());
				if((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
						|| ("".equals(extendName) && (extendNames.contains("全部") ||extendNames.contains("无扩展名"))))
				{
					if(start == 0)
					{
						f.renameTo(new File(f.getParent(), pre + suf + extendName));
					}
					else
					{
						f.renameTo(new File(f.getParent(), pre + start + suf + extendName));
					}
				}
				start -= step;
			}
		}
	}
	
	private static void preNumPostModifyMultiDirectory(File directory, String pre, String suf, int start, int step)
	{
		List<File> handleFiles = MultiLevelFileDirUtil.breadthFirstFileDirectory(directory, 2);
		if(handleFiles.size() > 0)
		{
			Collections.reverse(handleFiles);
			start += step * (handleFiles.size() - 1);
			for(File f: handleFiles)
			{
				if(start == 0)
				{
					f.renameTo(new File(f.getParent(), pre + suf));
				}
				else
				{
					f.renameTo(new File(f.getParent(), pre + start + suf));
				}
				start -= step;
			}
		}
	}
	
	private static void preNumPostModifyFileDirectory(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames, boolean seperateFileDirectory)
	{
		if(null == directory)
		{
			return;
		}
		
		int fileStart = start;
		int dirStart = start;
		
		List<File> handleFiles = Arrays.asList(directory.listFiles());
		for(int i = handleFiles.size() - 1; i >= 0; i--)
		{
			File temp = handleFiles.get(i);
			if(temp.isFile())
			{
				String extendName = FileUtil.getExtendName(temp.getName());
				if(!((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
						|| ("".equals(extendName) && (extendNames.contains("全部") ||extendNames.contains("无扩展名")))))
				{
					handleFiles.remove(i);
				}
			}
		}
		
		for(File f: handleFiles)
		{
			if(!seperateFileDirectory)
			{
				start = fileStart > dirStart ? fileStart : dirStart;
				fileStart = start;
				dirStart = start;
			}
			if(f.isDirectory())
			{
				if(dirStart == 0)
				{
					f.renameTo(new File(f.getParent(), pre + suf));
				}
				else
				{
					f.renameTo(new File(f.getParent(), pre + dirStart + suf));
				}
				dirStart += step;
			}
			else
			{
				String extendName = FileUtil.getExtendName(f.getName());
				if(fileStart == 0)
				{
					f.renameTo(new File(f.getParent(), pre + suf + extendName));
				}
				else
				{
					f.renameTo(new File(f.getParent(), pre + fileStart + suf + extendName));
				}
				fileStart += step;
			}
		}
	}
	
	private static void preNumPostModifyFile(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames)
	{
		if(null == directory)
		{
			return;
		}
		
		List<File> handleFiles = FileUtil.getFiles(Arrays.asList(directory.listFiles()));
		for(int i = handleFiles.size() - 1; i >= 0; i--)
		{
			File temp = handleFiles.get(i);
			if(temp.isFile())
			{
				String extendName = FileUtil.getExtendName(temp.getName());
				if(!((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
						|| ("".equals(extendName) && (extendNames.contains("全部") ||extendNames.contains("无扩展名")))))
				{
					handleFiles.remove(i);
				}
			}
		}
		
		for(File f: handleFiles)
		{
			String extendName = FileUtil.getExtendName(f.getName());
			if(start == 0)
			{
				f.renameTo(new File(f.getParent(), pre + suf + extendName));
			}
			else
			{
				f.renameTo(new File(f.getParent(), pre + start + suf + extendName));
			}
			start += step;
		}
	}
	
	private static void preNumPostModifyDirectory(File directory, String pre, String suf, int start, int step)
	{
		if(null == directory)
		{
			return;
		}
		
		for(File f: DirectoryUtil.getDirectories(Arrays.asList(directory.listFiles())))
		{
			if(start == 0)
			{
				f.renameTo(new File(f.getParent(), pre + suf));
			}
			else
			{
				f.renameTo(new File(f.getParent(), pre + start + suf));
			}
			start += step;
		}
	}
	
	private static void preNumPostModifySeperateLevelMultiFileDirectory(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames, boolean seperateFileDirectory)
	{
		if(null == directory)
		{
			return;
		}
		
		preNumPostModifyFileDirectory(directory, pre, suf, start, step, extendNames, nonExtendNames, seperateFileDirectory);
		
		for(File f: directory.listFiles())
		{
			if(f.isDirectory())
			{
				preNumPostModifySeperateLevelMultiFileDirectory(f, pre, suf, start, step, extendNames, nonExtendNames, seperateFileDirectory);
			}
		}
	}
	
	private static void preNumPostModifySeperateLevelMultiFile(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames)
	{
		if(null == directory)
		{
			return;
		}
		
		preNumPostModifyFile(directory, pre, suf, start, step, extendNames, nonExtendNames);
		
		for(File f: directory.listFiles())
		{
			if(f.isDirectory())
			{
				preNumPostModifySeperateLevelMultiFile(f, pre, suf, start, step, extendNames, nonExtendNames);
			}
		}
	}
	
	private static void preNumPostModifySeperateLevelMultiDirectory(File directory, String pre, String suf, int start, int step)
	{
		if(null == directory)
		{
			return;
		}
		
		preNumPostModifyDirectory(directory, pre, suf, start, step);
		
		for(File f: directory.listFiles())
		{
			if(f.isDirectory())
			{
				preNumPostModifySeperateLevelMultiDirectory(f, pre, suf, start, step);
			}
		}
	}
	
	public static void replaceDirectoryFileModify(File directory, String replace, String replaceAs, int replaceIndex, int fileType, List<String> extendNames, List<String> nonExtendNames)
	{
		switch(fileType)
		{
			case 1://替换文件名
				replaceFileModify(directory, replace, replaceAs, replaceIndex, extendNames, nonExtendNames);
				break;
			case 2://替换文件夹名
				replaceDirectoryModify(directory, replace, replaceAs, replaceIndex);
				break;
			case 3://同时替换文件和文件夹名
				replaceFileModify(directory, replace, replaceAs, replaceIndex, extendNames, nonExtendNames);
				replaceDirectoryModify(directory, replace, replaceAs, replaceIndex);
				break;
			default:
		}
	}
	
	/**
	 * 
	 * replaceFileModify:替换文件夹下的多级文件的文件名. <br/>
	 * @author xrzhang
	 * @param directory
	 * @param replace
	 * @param replaceAs
	 * @param placeIndex
	 * @param extendNames
	 * @param nonExtendNames
	 * @since 2019年5月1日 下午12:35:19
	 */
	public static void replaceFileModify(File directory, String replace, String replaceAs, int placeIndex,
			List<String> extendNames, List<String> nonExtendNames)
	{
		String fileName = null;
		String[] fileSplit = null;
		String extendName = null;
		List<File> allFiles = MultiLevelFileDirUtil.getAllSubFile(directory);
		
		for(File f: allFiles)
		{
			if(f.isDirectory())
			{
				continue;
			}
			
			fileName = f.getName();
			extendName = FileUtil.getExtendName(fileName);
			fileName = fileName.substring(0, fileName.length() - extendName.length());
			
			if(placeIndex <= 0)
			{
				if((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
						|| ("".equals(extendName) && (extendNames.contains("全部") || extendNames.contains("无扩展名"))))
				{
					fileName = fileName.replaceAll(replace, replaceAs);
				}
			}
			else
			{
				fileSplit = fileName.split(replace, -1);
				if(fileSplit.length <= placeIndex)
				{
					continue;
				}
				else
				{
					if((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
							|| ("".equals(extendName) && (extendNames.contains("全部") || extendNames.contains("无扩展名"))))
					{
						Matcher matcher = Pattern.compile(replace).matcher(fileName); 
						if(matcher.find()) {
							fileName = FileUtil.concatInsertArray(fileSplit, placeIndex, replaceAs, matcher);
						}
						else
						{
							return;
						}
					}
				}
			}
			
			f.renameTo(new File(f.getParent(), fileName + extendName));
		}
	}
	
	/**
	 * 替换修改文件夹名（可以使用正则表达式）
	 * @param directory 要修改其中文件名扩展名的文件夹
	 * @param replace 要替换的字符串
	 * @param replaceAs 被替换成的字符串
	 * @param placeIndex 替换第次出现的要替换内容（大于0的整数，否则替换所有出现要替换内容的地方）
	 */
	public static void replaceDirectoryModify(File directory, String replace, String replaceAs, int placeIndex)
	{
		String fileName = null;
		String[] fileSplit = null;
		List<File> allDirs = MultiLevelFileDirUtil.getAllSubDir(directory);
		// 原来的顺序是上级目录在下级目录前，会先修改上级目录再修改下级目录，可能会有问题，改一下顺序，使之先修改下级目录再修改上级目录
		Collections.reverse(allDirs);
		
		for(File f: allDirs)
		{
			if(f.isFile())
			{
				continue;
			}
			
			fileName = f.getName();
			
			if(placeIndex <= 0)
			{
				fileName = fileName.replaceAll(replace, replaceAs);
			}
			else
			{
				// -1的作用是字符串尾部的被替换位置会保存下来，否则数组长度会减少
				fileSplit = fileName.split(replace, -1);
				if(fileSplit.length <= placeIndex)
				{
					continue;
				}
				else
				{
					Matcher matcher = Pattern.compile(replace).matcher(fileName); 
					if(matcher.find()) {
						fileName = FileUtil.concatInsertArray(fileSplit, placeIndex, replaceAs, matcher);
					}
					else
					{
						return;
					}
				}
			}
			
			f.renameTo(new File(f.getParent(), fileName));
		}
	}
	
	/**
	 * 修改文件的扩展名
	 * @param directory 要修改其中文件名扩展名的文件夹
	 * @param replaceAs 替换成的扩展名
	 * @param extendNames 要修改的扩展名
	 * @param nonExtendNames 非文件扩展名
	 */
	public static void extendNameModify(File directory, String replaceAs, List<String> extendNames,
			List<String> nonExtendNames)
	{
		List<File> files = MultiLevelFileDirUtil.getAllSubFile(directory);
		if(!"".equals(replaceAs))
		{
			replaceAs = "." + replaceAs;
		}
		for(File f: files)
		{
			if(f.isDirectory())
			{
				continue;
			}
			else
			{
				String extendName = FileUtil.getExtendName(f.getName());
				if((!"".equals(extendName) && ((extendNames.contains("全部") || extendNames.contains(extendName)) && !nonExtendNames.contains(extendName)))
						|| ("".equals(extendName) && (extendNames.contains("全部") ||extendNames.contains("无扩展名"))))
				{
					String fileName = f.getName();
					fileName = fileName.substring(0, fileName.length() - extendName.length()) + replaceAs;
					f.renameTo(new File(f.getParent(), fileName));
				}
			}
			
		}
	}
}
