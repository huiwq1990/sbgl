import java.util.Date;

import com.sbgl.util.DateUtil;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateUtil.getCurrentWeekMonday();
		
		Date selStartDate = DateUtil.addDay(semesterStartDate, (selsemesterweek-1)*7);
		Date selEndDate = DateUtil.addDay(selStartDate, 7);
	}

}
