package org.greencode.common.utils;



/**
 * 项目前端接口常量类
 */
public class ClientConstants {

    /**
     * 成功
     */
    public static final int SUCCESS_CODE = 0;
    public static final String SUCCESS_MSG = "SUCCESS";

    public static final int CANCEL_TIME_CODE = 1;
    public static final String CANCEL_TIME_MSG = "24h以内";
    /**
     * 失败
     */
    public static final int COMMON_ERROR_CODE = 500;
    public static final String COMMON_ERROR_MSG = "未知错误，请联系技术人员";
    /**
     * 参数不完整
     */
    public static final int PARAM_ERROR_CODE = 10001;
    public static final String PARAM_ERROR_MSG = "信息不完整";
    /**
     * 角色有误
     */
    public static final int NOT_FIND_ERROR_CODE = 10002;
    public static final String NOT_FIND_ERROR_MSG = "没有找到该条记录！";
    /**
     * 信息已存在
     */
    public static final int EXIST_ERROR_CODE = 10003;
    public static final String EXIST_ERROR_MSG = "您选择的班次人数已满，请重新申请！";
    /**
     * 账号格式有误
     */
    public static final int USN_ERROR_CODE = 10004;
    public static final String USN_ERROR_MSG = "账号长度在6-15位之间，且不能有空格！";
    /**
     * 密码格式有误
     */
    public static final int PWD_ERROR_CODE = 10005;
    public static final String PWD_ERROR_MSG = "密码格式有误!6-16位数字和字母的组合";
    /**
     * 手机号格式有误
     */
    public static final int PHONE_ERROR_CODE = 10006;
    public static final String PHONE_ERROR_MSG = "手机号格式有误！";

    /**
     *年龄格式有误
     */
    public static final int AGE_ERROR_CODE = 10007;
    public static final String AGE_ERROR_MSG = "年龄格式有误，1岁--120岁！";

    /**
     *
     */
    public static final int NAME_ERROR_CODE = 10008;
    public static final String NAME_ERROR_MSG = "名字格式有误，不能为空！";
//    /**
//     * 密码格式有误
//     */
//    public static final int PWD_ERROR_CODE = 10004;
//    public static final String PWD_ERROR_MSG = "密码格式有误！至少包含一个大写字母、一个小写字母和数字";
//    /**
//     * 两次密码不一致
//     */
//    public static final int PWD_MATCH_CODE = 10005;
//    public static final String PWD_MATCH_MSG = "两次密码不一致！";

//    /**
//     * 验证码发送失败
//     */
//    public static final int SEND_ERROR_CODE = 10007;
//    public static final String SEND_ERROR_MSG = "验证码发送失败！";
//    /**
//     * 验证码失效
//     */
//    public static final int CODE_INVALID_CODE = 10008;
//    public static final String CODE_INVALID_MSG = "验证码已失效！";
//    /**
//     * 验证码有误
//     */
//    public static final int CODE_ERROR_CODE = 10009;
//    public static final String CODE_ERROR_MSG = "验证码输入有误！";
//    /**
//     * 手机号已被注册
//     */
//    public static final int PHONE_EXIST_CODE = 10010;
//    public static final String PHONE_EXIST_MSG = "手机号已被注册！";
//    /**
//     * 手机号不存在
//     */
//    public static final int PHONE_INEXIST_CODE = 10011;
//    public static final String PHONE_INEXIST_MSG = "手机号不存在！";
//    /**
//     * 用户名密码错误
//     */
//    public static final int USER_ERROR_CODE = 10012;
//    public static final String USER_ERROR_MSG = "用户名或密码错误！";
//    /**
//     * 追问上限错误
//     */
//    public static final int QUESTION_LIMIT_CODE = 10013;
//    public static final String QUESTION_LIMIT_MSG = "追问已达上限！";
//    /**
//     * 问题尚未答复错误
//     */
//    public static final int NOT_REPLY_CODE = 10014;
//    public static final String NOT_REPLY_MSG = "问题尚未答复，无法追问！";
//
//    public static final int PAY_ERROR_CODE = 10015;
//    public static final String PAY_ERROR_MSG = "支付调用失败！";
//
//    public static final int REFUSE_ERROR_CODE = 10016;
//    public static final String REFUSE_ERROR_MSG = "退款失败！";
//
//    public static final int TASK_LIMIT_CODE = 10017;
//    public static final String TASK_LIMIT_MSG = "任务已达上限！";
//
//    public static final int PARAM_LIMIT_CODE = 10018;
//    public static final String PARAM_LIMIT_MSG = "请传入页码";
//
//    public static final int BIND_RELATION_CODE = 10019;
//    public static final String BIND_RELATION_MSG = "学生与教师暂无绑定关系!";
//
//
//    public static final String NULL = "null";
//    /**
//     * map集合 初始化大小
//     */
//    public static final int MAP_INIT_NUM = 16;
//    /**
//     * 微信手机号（不含区号）判断标识
//     */
//    public static final String WX_PHONE_LOGO = "purePhoneNumber";
//    /**
//     * 微信运动步数集合
//     */
//    public static final String WX_RUN_DATA = "stepInfoList";
//
//    public static final int STATUS_ZERO = 0;
//    public static final int STATUS_ONE = 1;
//    public static final int STATUS_TWO = 2;
//    public static final int STATUS_THREE = 3;
//    public static final int STATUS_FOUR = 4;
//    public static final int STATUS_FIVE = 5;
//    public static final int STATUS_SIX = 6;
//    public static final int STATUS_SEVEN = 7;
//    public static final int STATUS_ENIGHT = 8;
//
    /**
     * 正则：手机号校验
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
    /**
     * 正则年龄校验，1岁--120岁
     */
    public static final String REGEX_AGE = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";

    /**
     * 正则密码校验，6-16位数字和字母的组合
     */
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
//    /**
//     * 正则：密码校验，至少包含一个大写字母、一个小写字母和数字
//     */
//    public static final String REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$";
//
//    /**
//     * 验证码有效期（redis.key 有效期）
//     */
//    public static final int KEY_TIMEOUT = 10;
//
//    /**
//     * 初始头像
//     */
//    public static final String TEA_HEAD = "/img/teacher.png";
//    public static final String STU_HEAD = "/img/student.png";
//    public static final String TOU_HEAD = "/img/tourist.png";

}
