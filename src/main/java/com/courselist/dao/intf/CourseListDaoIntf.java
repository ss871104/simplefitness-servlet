package com.courselist.dao.intf;

import com.common.dao.CommonDao;
import com.courselist.vo.CourseList;


public interface CourseListDaoIntf extends CommonDao<CourseList, Integer> {

	/* *
	 *  Func: 取得課程報名上限人數
	 *  CreateBy: Iris
	 *  CreateDate: 2022/09/18
	 * */
	CourseList getCourseListByCourseId(Integer courseId);

}
