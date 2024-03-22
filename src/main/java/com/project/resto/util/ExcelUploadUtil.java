package com.project.resto.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dell on 2018/6/20.
 */
public class ExcelUploadUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUploadUtil.class);

    private static final String SUFFIX_2003 = ".xls";
    private static final String SUFFIX_2007 = ".xlsx";


    /**
     * 解析excel文档
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook resolveExcelFile(MultipartFile file) throws IOException {
        String fileName=file.getOriginalFilename();
        Workbook workbook=null;
        if(fileName.endsWith(SUFFIX_2003)){
            workbook = new HSSFWorkbook(file.getInputStream());
        }else if(fileName.endsWith(SUFFIX_2007)){
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        return  workbook;
    }


    /**
     * 解析excel文档
     *
     * 需要保证 实体类的属性条目 >=  上传文件的列条目
     *
     * 目前支持 String  Date  Long  类型转换
     * Date 需要更具上传的类型  增加匹配模版 待测
     * @param sheet  t
     * @return
     * @throws IOException
     */
    public static <T>  List<T> resolveSheet(Sheet sheet,Class<T> t) throws  UploadExcelException{
        List<T> list = new ArrayList<>();
        try {
            int lastRowNum = sheet.getLastRowNum();
            int columns = sheet.getRow(0).getPhysicalNumberOfCells();
            Field[] fields = t.getDeclaredFields();
            if (fields.length < columns) {
                throw new Exception("excel import model invalid  or  data object invalid");
            }
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                T demoT = t.newInstance();
                for (int j = 0; j < columns; j++) {

                    Cell cell = row.getCell(j);
                    Field filed = fields[j];
                    Method method=getSetMethod(t,filed.getName(),filed.getType());
                    if(method==null){
                        continue;
                    }
                    if (filed.getType() == String.class) {
                        try {
                            logger.info(filed.getName()+"----"+j+"-----"+cell.getStringCellValue());
                            if(!StringUtils.isBlank(cell.getStringCellValue())) {
                                method.invoke(demoT, cell.getStringCellValue());
                            }
                        }catch(Exception e1){
                            if(e1 instanceof  IllegalStateException){
                                if(e1.getMessage().contains("Cannot get a text value from a numeric cell") || e1.getMessage().contains("Cannot get a STRING value from a NUMERIC cell")) {
                                    logger.info(filed.getName()+"----"+j+"-----"+cell.getNumericCellValue());
                                    Double value=cell.getNumericCellValue();
                                    if(value!=null) {
                                        method.invoke(demoT, String.valueOf(value.longValue()));
                                    }
                                }else{
                                    e1.printStackTrace();
                                }
                            }else if(e1 instanceof NullPointerException){
                                continue;
                            }else{
                                e1.printStackTrace();
                            }
                       }
                    } else if (filed.getType() == Date.class) {
                        method.invoke(demoT,cell.getDateCellValue());
                    } else if (filed.getType() == Long.class) {
                        method.invoke(demoT, Long.valueOf(cell.getNumericCellValue()+""));
                    }
                }
                list.add(demoT);
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new UploadExcelException("excel upload reslove error",e.getMessage());
        }
        return  list;
    }


    private  static <T> Method getSetMethod(Class<T> t,String filedName,Class<?> paramType){
        String methodName="set"+filedName.substring(0,1).toUpperCase()+filedName.substring(1);
        try {
            Method method= t.getDeclaredMethod(methodName,paramType);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
