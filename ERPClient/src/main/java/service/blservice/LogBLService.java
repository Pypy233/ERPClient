package service.blservice;

import objects.ResultMessage;
import vo.LogVO;
import vo.UserVO;

import java.util.ArrayList;

public interface LogBLService {
    /**
     * 增加日志
     * @param vo
     * @param operation
     * @param msg
     * @return
     */
    public ResultMessage addLog(UserVO vo, String operation, ResultMessage msg);

    /**
     * 删除日志
     * @param vo
     * @return
     */
    public ResultMessage delete(LogVO vo);

    /**
     * 更新日志
     * @param vo
     * @return
     */
    public ResultMessage update(LogVO vo);

    /**
     * 得到当前用户的日志
     * @param vo
     * @return
     */
    public ArrayList<LogVO> getLog(UserVO vo);

    /**
     * 按照用户名查找日志
     * @param name
     * @return
     */
    public ArrayList<LogVO> findByName(String name);

    /**
     * 按照日期查找日志
     * @param date
     * @return
     */
    public ArrayList<LogVO> findByDate(String date);

    /**
     * 按操作查找日志
     * @param operation
     * @return
     */
    public ArrayList<LogVO> findByOperation(String operation);

}
