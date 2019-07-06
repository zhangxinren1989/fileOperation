package gui.util;

import java.io.File;

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
}
