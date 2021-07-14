package com.netnoss.www.util;

public class StatusCode {
	/** UserName ist keine Existenz*/
	public final static int FAIL_EMPTY=10;
	/** PWD ist falsch*/
	public final static int FAIL_PWD_ERROR=101;
	/** CheckCode ist falsch*/
	public final static int FAIL_CODE_ERROR=102;
	public final static int FAIL_CARD_EXIST=105;
	/**find result leer* */
	public final static int FAIL=30;
	/** result ist Leer*/
	public final static int FAIL_RESULT_EMPYT=103;
	/** SUCCESS*/
	public final static int SUCCESS=20;
	/** width of the created image */
	public final static int IMG_WIDTH= 75;
	/**height of the created image */
	public final static int IMG_HEIGHT = 32;
	/**checkCode length*/
	public final static int IMG_NUM=4;
	/**Line Count of the created image*/
	public final static int IMG_LINECOUNT=4;
	/**IMAGE CODES ARRAY*/
	public final static char[] IMG_CODE_SEQUENCE= { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
	            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };	
	/**Generate validation codes success*/
	public final static int IMG_CODE_SUCCESS=30;
	/**pageNum total**/
	public final static int PAGE_TOTAL=10;
	public final static int PAGE_TOTAL_5=5;
}
