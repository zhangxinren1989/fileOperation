
package gui.fileoperation.practice;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import gui.util.DirectoryUtil;
import gui.util.FileUtil;

public class PracticeFunction
{
	private PracticeFunction() {};
	
	/**
	 * statisticFileInfo:统计一个文件夹下文件的类型，数量，大小，平均大小，最大，最小大小
	 * 文件的总数量，总大小，总平均大小，最大，最小，文件夹数量. <br/>
	 * @author zhangxinren
	 * @param dir
	 * @return
	 * @since 2019年6月8日 下午4:36:25
	 */
	public static Map<String, Object[][]> statisticFileInfo(File dir)
	{
		// 获取文件夹下的所有文件（夹）
		List<File> allFiles = DirectoryUtil.getAllFiles(dir);
		// 获取所有文件
		List<File> files = FileUtil.getFiles(allFiles);
		// 获取所有文件夹
		List<File> dirs = DirectoryUtil.getDirectories(allFiles);
		
		Map<String, List<Long>> statisticInfo = new LinkedHashMap<>();
		
		Map<String, Object[][]> result = new HashMap<>();
		String fileName;
		String extendName;
		long size;
		String noneExtendName = "无扩展名";
		// 0：总大小；1：最大大小；2：最小大小；3：总数量
		List<Long> fileInfoList;
		
		List<Long> totalInfo = new ArrayList<>();
		// 文件夹数量
		totalInfo.add((long)dirs.size());
		// 不同类型文件的数量
		totalInfo.add(0l);
		// 文件的总大小
		totalInfo.add(0l);
		// 文件的最大大小
		totalInfo.add(0l);
		// 文件的最小大小
		totalInfo.add(0l);
		
		statisticInfo.put(noneExtendName, null);
		
		if(files.size() > 0)
		{
			for(int i = 0; i < files.size(); i++)
			{
				fileName = files.get(i).getName();
				
				extendName = FileUtil.getExtendName(fileName);
				
				if("".equals(extendName))
				{
					extendName = noneExtendName;
				}
				
				fileInfoList = statisticInfo.get(extendName);
				size = files.get(i).length();
				
				// 没有该类型的文件，初始化一下
				if(null == fileInfoList)
				{
					fileInfoList = new ArrayList<>();
					fileInfoList.add((long)size);
					fileInfoList.add((long)size);
					fileInfoList.add((long)size);
					fileInfoList.add(1l);
				}
				else// 有该类型的文件，处理一下
				{
					fileInfoList.set(0, fileInfoList.get(0) + size);
					
					if(fileInfoList.get(1) < size)
					{
						fileInfoList.set(1, (long)size);
					}
					
					if(fileInfoList.get(2) > size)
					{
						fileInfoList.set(2, (long)size);
					}
					
					fileInfoList.set(3, fileInfoList.get(3) + 1);
				}
				
				// 未有最大文件大小或最大文件大小小于当前文件
				if(totalInfo.get(3) == 0 || totalInfo.get(3) < size)
				{
					totalInfo.set(3, size);
				}

				// 未有最小文件大小或最小文件大小大于当前文件
				if(totalInfo.get(4) == 0 || totalInfo.get(4) > size)
				{
					totalInfo.set(4, size);
				}
					
				statisticInfo.put(extendName, fileInfoList);
				
				totalInfo.set(1, totalInfo.get(1) + 1);//总文件数
				totalInfo.set(2, totalInfo.get(2) + size);//总文件大小
			}
		}
		
		int rows;
		if(statisticInfo.get(noneExtendName) == null)
		{
			rows = statisticInfo.size() - 1;
		}
		else
		{
			rows = statisticInfo.size();
		}
		
		// "类型", "数量", "总大小(kb)", "最大(kb)", "最小(kb)", "平均(kb)"
		Object[][] totalInfoArr = new Object[1][6];
		// 文件夹数量
		totalInfoArr[0][0] = totalInfo.get(0);
		// 文件总数量
		totalInfoArr[0][1] = totalInfo.get(1);
		// 文件总大小
		totalInfoArr[0][2] = totalInfo.get(2);
		// 文件平均大小
		if(totalInfo.get(1) > 0)
		{
			totalInfoArr[0][3] = Math.round(totalInfo.get(2) * 1.0 / totalInfo.get(1) * 100) / 1.0 / 100;
		}
		else
		{
			totalInfoArr[0][3] = 0;
		}
		// 文件最大大小
		totalInfoArr[0][4] = totalInfo.get(3);
		// 文件最小大小
		totalInfoArr[0][5] = totalInfo.get(4);
		result.put("totalInfo", totalInfoArr);
		
		Object[][] resultArr = new Object[rows][];
		int index = 0;
		for(Entry<String, List<Long>> entry: statisticInfo.entrySet())
		{
			if(null != entry.getValue())
			{
				List<Long> value = entry.getValue();
				Object[] item = new Object[8];
				
				extendName = entry.getKey();
				
				if(extendName.indexOf(".") > -1)
				{
					extendName = extendName.substring(1);
				}
				
				item[0] = index + 1;
				item[1] = extendName;
				item[2] = value.get(3)*1.0;
				item[3] = value.get(0)*1.0;
				item[4] = value.get(1)*1.0;
				item[5] = value.get(2)*1.0;
				item[6] = Math.round(value.get(0) * 1.0 / value.get(3) * 100) / 1.0 / 100;
				item[7] = "";
				resultArr[index++] = item;
			}
		}
		
		result.put("rows", resultArr);
		
		return result;
	}
}

