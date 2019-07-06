package gui.fileoperation.interest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import gui.constant.InterestConstant;
import gui.util.FileUtil;

public class InterestFunction {
	private InterestFunction(){}
	
	public static int wordDirectoryInterest(File wordFile)
	{
		List<String> words = new ArrayList<>();
		String wordLine = null;
		int directoryNameNum = 0;
		BufferedReader rb = null;
		
		try
		{
			rb = new BufferedReader(new InputStreamReader(new FileInputStream(wordFile)));
			int index = 0;
			while((wordLine = rb.readLine()) != null)
			{
				if(FileUtil.checkLegalFileName(wordLine))
				{
					words.add(String.format("%04d", index++) + " " + wordLine);
				}
				else
				{
					return InterestConstant.FILE_NAME_NOT_SUPPORT;
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return InterestConstant.READ_FILE_EXCEPTION;
		}
		finally
		{
			if(null != rb)
			{
				try {
					rb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(words.isEmpty())
		{
			return InterestConstant.FILE_CONTENT_EMPTY;
		}
		
		File interestDirectory = new File(wordFile.getParent(), InterestConstant.INTEREST_DIRECTORY_STRING);
		if(interestDirectory.exists())
		{
			for(int i = 2; i < 1000 && interestDirectory.exists(); i++)
			{
				interestDirectory = new File(wordFile.getParent(), InterestConstant.INTEREST_DIRECTORY_STRING + i);
				directoryNameNum = i;
			}
		}
			
		if(interestDirectory.exists())
		{
			return InterestConstant.CREATE_DIRECTORY_FAILED;
		}
		interestDirectory.mkdir();
		
		File tempDirectory = null;
		for(int i = 0, size = words.size(); i < size; i++)
		{
			tempDirectory = new File(interestDirectory.getPath(), words.get(i));
			tempDirectory.mkdir();
		}
		
		return directoryNameNum;
	}
}
