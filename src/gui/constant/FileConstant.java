package gui.constant;

public interface FileConstant {
	/** 文件名中不允许出现的字符 */
	String NON_FILE_NAME_CHAR_REGEX = ".*[*|\\\\:\"<>?/].*";
	/** 系统保留的部分名称 */
	String RESERVE_FILE_NAME_REGEX = "(nul)|(aux)|(con)|(com1)|(lpt1)";
	/** 文件修改总页数 */
	int MODIFY_TOTAL_PAGE = 3;
	/** 文件创建总页数 */
	int CREATE_TOTAL_PAGE = 1;
	/** 文件删除总页数 */
	int DELETE_TOTAL_PAGE = 1;
	/** 实用功能总页数 */
	int PRACTICE_TOTAL_PAGE = 1;
	/** 趣味功能总页数 */
	int INTEREST_TOTAL_PAGE = 1;
	/** 修改模式 */
	int MODIFY_MODE = 3;
	/** 创建模式 */
	int CREATE_MODE = 1;
	/** 删除模式 */
	int DELETE_MODE = 2;
	/** 文件名最长长度 */
	int MAX_NAME_LENGTH = 255;
}
