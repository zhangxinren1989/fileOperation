package gui.util;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.FrameStatus;
import gui.constant.FileConstant;

/**
 * 只操作一级目录，这里面的操作只是文件，不涉及GUI组件的操作
 * @author xrzhang
 *
 */
public class FileUtil {
	private FileUtil(){}
	
	/**
	 * 判断文件是否有扩展名
	 * @param fileName
	 * @return
	 */
	public static boolean hasExtendName(String fileName)
	{
		return fileName.lastIndexOf(".") > -1 && isExtendName(fileName.substring(fileName.lastIndexOf(".")), true);
	}
	
	/**
	 * 判断一串数字是否是文件后缀:英文，长度在1-10位
	 * @param fileSuffixName 文件后缀
	 * @param containDot 是否包含.
	 * @return
	 */
	public static boolean isExtendName(String fileSuffixName, boolean containDot)
	{
		boolean result = false;
		
		if(null != fileSuffixName)
		{
			if(containDot && fileSuffixName.length() >= 2 && fileSuffixName.length() <= 11)
			{
				fileSuffixName = fileSuffixName.substring(1);
			}
			else 
			{
				return false;
			}
			
			if(fileSuffixName.length() >= 1 && fileSuffixName.length() <= 10)
			{
				String regex = "\\[a-zA-Z]{1,10}";
				Pattern pattern = Pattern.compile(regex);
				if(!pattern.matcher(fileSuffixName).matches())
				{
					result = true;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 拼接字符串数组并在指定位置插入字符串：插入位置的索引从1开始，不能插到第一个元素之前或最后一个元素之后
	 * @param fileNameSplit 文件名分隔后的数组
	 * @param placeIndex 要替换的位置
	 * @param insertStr 替换为的字符串
	 * @param joinStr 连接字符串
	 * @return
	 */
	public static String concatInsertArray(String[] fileNameSplit, int placeIndex, String insertStr, Matcher matcher)
	{
		String result = "";
		if(fileNameSplit.length - 1 < placeIndex || placeIndex <= 0)
		{
			for(int i = 0; i < fileNameSplit.length - 1; i++)
			{
				result += fileNameSplit[i] + matcher.group();
			}
			matcher.find();
		}
		else
		{
			String[] preArr = Arrays.copyOfRange(fileNameSplit, 0, placeIndex);
			String[] postArr = Arrays.copyOfRange(fileNameSplit, placeIndex, fileNameSplit.length);
			
			String preStr = "";
			String postStr = "";
			for(int i = 0; i < preArr.length - 1; i++)
			{
				preStr += preArr[i] + matcher.group();
				matcher.find();
			}
			
			if(preArr.length > 0) {
				preStr += preArr[preArr.length - 1];
			}
			
			matcher.find();
			for(int i = 0; i < postArr.length - 1; i++)
			{
				postStr += postArr[i] + matcher.group();
				matcher.find();
			}
			
			if(postArr.length > 0) {
				postStr += postArr[postArr.length - 1];
			}
			
			result = preStr + insertStr + postStr;
		}
		
		return result;
	}
	
	/**
	 * 获取文件的扩展名
	 * @param fileName 文件名
	 * @return
	 */
	public static String getExtendName(String fileName)
	{
		String extendName = "";
		
		if(fileName.lastIndexOf(".") > -1)
		{
			extendName = fileName.substring(fileName.lastIndexOf("."));
			if(!isExtendName(extendName, true))
			{
				extendName = "";
			}
		}
		
		return extendName.toLowerCase();
	}
	
	/**
	 * 获取文件扩展名
	 * @param directory 要获取其中文件扩展名的文件夹名
	 * @return
	 */
	public static Set<String> getExtendNames(File directory)
	{
		Set<String> extendNames = new HashSet<>();
		if(directory.isDirectory())
		{
			List<File> files = null;
			
			if(FrameStatus.multiLevel)
			{
				files = MultiLevelFileDirUtil.breadthFirstFileDirectory(directory, 1);
			}
			else
			{
				files = listFiles(directory);
			}
			
			String extendName = null;
			for(File f: files)
			{
				if(f.isDirectory())
				{
					continue;
				}
				else
				{
					extendName = getExtendName(f.getName());
					if(!"".equals(extendName))
					{
						extendNames.add(extendName.toLowerCase());
					}
				}
			}
		}
		
		return extendNames;
	}
	
	/**
	 * 当前一级目录下是否含有文件
	 * @param directory 要处理其中内容的文件夹名
	 * @return
	 */
	public static boolean isContainFile(File directory)
	{
		boolean result = false;
		if(directory.isDirectory())
		{
			for(File f: directory.listFiles())
			{
				if(f.isFile())
				{
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 从文件夹一级目录中找出文件
	 * @param directory 要处理其中内容的文件夹名
	 * @return
	 */
	public static List<File> listFiles(File directory)
	{
		List<File> result = new ArrayList<>();
		if(directory.isDirectory())
		{
			for(File f: directory.listFiles())
			{
				if(f.isFile())
				{
					result.add(f);
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 文件夹是否包含没有扩展名的文件
	 * @param directory 要处理其中内容的文件夹名
	 * @return
	 */
	public static boolean isContainNonExtendNameFile(File directory)
	{
		boolean result = false;
		if(directory.isDirectory())
		{
			String extendName = "";
			File[] files = null;
			if(FrameStatus.multiLevel)
			{
				files = MultiLevelFileDirUtil.getAllSubFile(directory).toArray(new File[0]);
			}
			else
			{
				files = directory.listFiles(new FileFilter() {
					@Override
					public boolean accept(File file) {
						return file.isFile();
					}
				});
			}
			
			for(File f: files)
			{
				if(f.isFile())
				{
					extendName = getExtendName(f.getName());
					
					if("".equals(extendName))
					{
						result = true;
						break;
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 判断是否是合法的文件名
	 * @param fileNamePart 部分或全部文件名
	 * @return
	 */
	public static boolean checkLegalFileName(String fileNamePart)
	{
		if(null == fileNamePart || fileNamePart.matches(FileConstant.NON_FILE_NAME_CHAR_REGEX))
		{
			return false;
		}
		
		return true;
	}
	
	/**
	 * 从一组File中取出文件
	 * @param files
	 * @return
	 */
	public static List<File> getFiles(List<File> files)
	{
		List<File> result = new ArrayList<>();
		if(null != files && files.size() > 0)
		{
			for(File f: files)
			{
				if(f.isFile())
				{
					result.add(f);
				}
			}
		}
		
		return result;
	}
	
}
