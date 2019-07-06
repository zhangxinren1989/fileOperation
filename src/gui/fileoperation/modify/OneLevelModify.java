package gui.fileoperation.modify;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gui.util.FileUtil;

/**
 * 单级文件（夹）的修改
 * @author zhangxinren
 *
 */
public class OneLevelModify {
	
	/**
	 * 前缀数字后缀修改文件名
	 * @param directory 要修改其中文件名的文件夹
	 * @param pre 修改后的前缀
	 * @param suf 修改后的后缀
	 * @param start 开始数字
	 * @param step 数字的增量
	 * @param extendNames 文件的扩展名（扩展名不处理，原样输出）
	 * @param nonExtendNames 非文件扩展名（.xxx结尾的内容，可以被处理）
	 */
	public static void preNumPostModify(File directory, String pre, String suf, int start, int step,
			List<String> extendNames, List<String> nonExtendNames)
	{
		String parentPath = directory.getAbsolutePath();
		int num = start;
		for(File f: directory.listFiles())
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
					f.renameTo(new File(parentPath, pre + num + suf + extendName));
					num += step;
				}
			}
		}
	}
	
	/**
	 * 可以使用正则表达式的文件名替换
	 * @param directory 要替换其中文件名内容的文件夹
	 * @param replace 要替换的内容
	 * @param replaceAs 被替换成的内容
	 * @param placeIndex 替换第次出现的要替换内容（大于0的整数，否则替换所有出现要替换内容的地方）
	 * @param extendNames 文件的扩展名（扩展名不处理，原样输出）
	 * @param nonExtendNames 不是扩展名的以.xxx结尾的内容（可以被处理）
	 */
	public static void replaceModify(File directory, String replace, String replaceAs, int placeIndex,
			List<String> extendNames, List<String> nonExtendNames)
	{
		String parentPath = directory.getAbsolutePath();
		String fileName = null;
		String[] fileSplit = null;
		String extendName = null;
		
		for(File f: directory.listFiles())
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
			
			f.renameTo(new File(parentPath, fileName + extendName));
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
		String parentPath = directory.getAbsolutePath();
		if(!"".equals(replaceAs))
		{
			replaceAs = "." + replaceAs;
		}
		for(File f: directory.listFiles())
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
					f.renameTo(new File(parentPath, fileName));
				}
			}
			
		}
	}
	
	/**
	 * 前缀数字后缀修改文件夹名
	 * @param directory 要修改其中文件名的文件夹
	 * @param pre 修改后的前缀
	 * @param suf 修改后的后缀
	 * @param start 开始数字
	 * @param step 数字增量
	 */
	public static void preNumPostDirectoryModify(File directory, String pre, String suf, int start, int step)
	{
		String parentPath = directory.getAbsolutePath();
		int num = start;
		for(File f: directory.listFiles())
		{
			if(f.isFile())
			{
				continue;
			}
			else
			{
				f.renameTo(new File(parentPath, pre + num + suf));
				num += step;
			}
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
		String parentPath = directory.getAbsolutePath();
		String fileName = null;
		String[] fileSplit = null;
		
		for(File f: directory.listFiles())
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
			
			f.renameTo(new File(parentPath, fileName));
		}
	}
}
