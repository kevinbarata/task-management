package com.project.resto.util;

/**
 * 基础配置
 *
 * @version 1.0
 * @author: likai
 * @date 创建时间：2017年8月25日
 * @parameter
 * @return
 */
public interface QoConstants {

    String INDONESIA_FINANCE_EXCHANGE_NAME = "uzone_indonesia_finance_fanout_exchange";
    String INDONESIA_TO_FINANCE_QUE_NAME = "uzone_indonesia_to_finance_queue";

    /**
     * 系统常量
     */
    interface System {
        String OK = "1";
        String FAIL = "0";
        public static final int REDIS_HOUR_TIME = 3600;//过期时间6h
    }

    /**
     * 字典
     */
    interface DICT {
        String PRODUCT_ID = "indonesia";

        /**
         * code缓存前辍
         */
        String PREFIX_REDIS_CODE = "UZONE-INDS-SESSIONID-";
        String PREFIX_REDIS_USERID_PRODUCT = "UZONE-INDS-USERID-PRODUCT-";

        String LOGIN_USER = "LOGIN-USER:";//登录用户缓存

        //财务状态
        String XENDIT_LOAN_STATUS_SENDING = "SENDING";

        String XENDIT_LOAN_STATUS_FAILED = "FAILED";

        String XENDIT_LOAN_STATUS_PENDING = "PENDING";

        String XENDIT_LOAN_STATUS_COMPLETED = "COMPLETED";

        String XENDIT_PAYER_EMAIL = "pinjaman@mobanker.com";


    }

    /**
     * 系统提示信息
     *
     * @author liuyafei
     */
    interface MESSAGE {
        String ERROR_HEAD = "09";
        // 固定保留 0-100
        String SUCCESS = "00000000";
        String SUCCESS_MSG = "调用成功";

        String SYS_EXCEPTION = ERROR_HEAD + "000001";
        String SYS_EXCEPTION_MSG = "系统异常";

        public static final String DB_TIMEOUT = ERROR_HEAD + "000003";
        public static final String DB_TIMEOUT_MSG = "数据库连接超时";

        public static final String CACHE_TIMEOUT = ERROR_HEAD + "000004";
        public static final String CACHE_TIMEOUT_MSG = "缓存连接超时";

        public static final String MQ_TIMEOUT = ERROR_HEAD + "000005";
        public static final String MQ_TIMEOUT_MSG = "消息队列连接超时";

        public static final String DB_CONNECT_SPEND = ERROR_HEAD + "000006";
        public static final String DB_CONNECT_SPEND_MSG = "数据库连接数用尽";

        public static final String SERVICE_VALID = ERROR_HEAD + "000007";
        public static final String SERVICE_VALID_MSG = "找不到服务";

        public static final String PARAM_VALID = ERROR_HEAD + "000008";
        public static final String PARAM_VALID_MSG = "参数异常";

        public static final String PARAM_REQUIRED = ERROR_HEAD + "000009";
        public static final String PARAM_REQUIRED_MSG = "缺少必填参数";

        public static final String PARAM_OUT_RANGE = ERROR_HEAD + "000010";
        public static final String PARAM_OUT_RANGE_MSG = "参数值不在取值范围";

        public static final String SIGN_VERIFY_FAILED = ERROR_HEAD + "000011";
        public static final String SIGN_VERIFY_FAILED_MSG = "签名验证失败";

        public static final String REQUEST_TIMEOUT = ERROR_HEAD + "000012";
        public static final String REQUEST_TIMEOUT_MSG = "请求超时";

        public static final String UNAUTH_REQUEST = ERROR_HEAD + "000013";
        public static final String UNAUTH_REQUEST_MSG = "无权调用";

        public static final String INTER_VERSION_NONSUPPORT = ERROR_HEAD + "000014";
        public static final String INTER_VERSION_NONSUPPORT_MSG = "接口版本不支持";
        
        public static final String HTTP_ERROR = ERROR_HEAD + "000015";


        //财务
        public static final String XENDIT_CREATE_INVOICE_NULL_ERROR = ERROR_HEAD + "000015";
        public static final String XENDIT_CREATE_INVOICE_NULL_MSG = "获取还款返回状态报错";

        public static final String XENDIT_CREATE_INVOICE_OUT_OF_SERVICE_ERROR = ERROR_HEAD + "000016";
        public static final String XENDIT_CREATE_INVOICE_OUT_OF_SERVICE_MSG = "不在还款服务时间";
        


        public static final String XENDIT_GET_BALANCE_ERROR = ERROR_HEAD + "000015";
        public static final String XENDIT_GET_BALANCE_MSG = "获取还款返回状态报错";

        public static final String XENDIT_CREATE_INVOICE_not_click_repeatedly_error = ERROR_HEAD + "000018";
        public static final String XENDIT_CREATE_INVOICE_not_click_repeatedly_MSG = "请勿重复点击";
        
        public static final String VERIFYIDNFO_ERROR = ERROR_HEAD + "000019";
        public static final String VERIFYIDNFO_MSG = "验证失败";
        
        public static final String refuseLoan_ERROR = ERROR_HEAD + "000017";
        public static final String refuseLoan_MSG = "放款拒绝修改失败";
        
        public static final String LOAN_ERROR = ERROR_HEAD + "000020";
        public static final String LOAN_MSG = "放款失败";
        
        public static final String RepayRecordSuccessDetail_ERROR = ERROR_HEAD + "000021";
        public static final String RepayRecordSuccessDetail_MSG = "查询明细失败";


    }

    /**
     * 后台api版本号
     */
    public static final String ADMIN_API_VERSION_2_0 = "2.0/";

    Integer CAN_BORROW_AFTER_DAYS = 5;// 借款被拒几天后能再次借款(审核被拒绝,放款被拒时使用)

    //分单MQ
    String INDONESIA_ASSIGN_EXCHANGE_NAME = "uzone_indonesia_assign_fanout_exchange";
    String INDONESIA_TO_ASSIGN_QUE_NAME = "uzone_indonesia_to_assign_queue";
    //准入MQ
    String INDONESIA_ORDER_CHECK_EXCHANGE_NAME = "indonesia_order_check_fanout_exchange";
    String INDONESIA_TO_ORDER_CHECK_QUE_NAME = "indonesia_to_order_check_queue";

    /**创建订单发送到准入(决策)MQ exchange*/
    String INDONESIA_ORDER_CREATE_RESULT = "indonesia_order_create_result";

    String ORDER_CHECK_USER_ROLE = "ORDER:CHECK:USER:ROLE:ASSIGN:";

}
