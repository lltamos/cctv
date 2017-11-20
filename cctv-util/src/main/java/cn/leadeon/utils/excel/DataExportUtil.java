package cn.leadeon.utils.excel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * 数据导出工具类
 * 
 */
public class DataExportUtil {
	private final static Logger log = Logger.getLogger(DataExportUtil.class);
	/**
	 * 导出单个的excel文件
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param destPath
	 *            导出后生成文件路径
	 * @param startRow
	 *            从第几行开始写入数据
	 * @param sourceRow
	 *            复制第几行的样式
	 * @param list
	 *            要导出的数据
	 * @return
	 */
	public static void expSingleFile(String templatePath, String destPath,
			int startRow, int sourceRowIndex, List<List<String>> list) {
		
		//D:\pearComs\pear-bill-server\target\classes\cn\leadeon\bill\quartz\template
		// 输出内容至目标文件
		FileOutputStream fileOut = null;
		try {
			// 根据模板文件路径，构造模板文件
			File srcFile = new File(templatePath);
			srcFile.length();
			// 根据生成文件路径构造目标文件
			File destFile = new File(destPath);
			// copy模板文件至目标路径
			FileUtils.copyFile(srcFile, destFile);
			// 读取copy过来的文件
			InputStream in = new FileInputStream(destFile);
			XSSFWorkbook workbook = new XSSFWorkbook(in);
			XSSFSheet sheet = workbook.getSheetAt(0);
			// 获取表头行
			XSSFRow headRow = sheet.getRow(startRow);
			// 根据表头行获取每行总共有多少列
			int cells = headRow.getLastCellNum();
			int k = startRow;
			for (Iterator<List<String>> it = list.iterator(); it.hasNext();) {
				// 每一行数据
				List<String> data = it.next();
				if (k <= sourceRowIndex) {
					// 循环设置每一行的值
					for (int i = 0; i < cells; i++) {
						// 判断每一列是否有值
						if (i < data.size()) {
							if (null != data.get(i) && !"".equals(data.get(i))) {
								// if (isNumeric(data.get(i))) {
								// sheet.getRow(k)
								// .getCell(i)
								// .setCellValue(
								// Double.valueOf(data.get(i)));
								// } else {
								sheet.getRow(k)
										.getCell(i)
										.setCellValue(
												new XSSFRichTextString(data
														.get(i)));
								// }
							}
						}
					}
				} else {
					// 模板自带行
					XSSFRow sourceRow = sheet.getRow(sourceRowIndex);
					// 创建目标行
					XSSFRow targetRow = sheet.createRow(k);
					// 设置目标行的高度，和模板行一样
					targetRow.setHeight(sourceRow.getHeight());
					// 循环设置目标列的样式和值
					for (int i = 0; i < cells; i++) {
						if (data.get(i).equals("合计")
								|| data.get(i).equals("应收合计")
								|| data.get(i).equals("应付合计")) {
							CellRangeAddress cellRangeAddress = new CellRangeAddress(
									k, k, 0, 2);
							sheet.addMergedRegion(cellRangeAddress);
						}
						if (data.get(i).equals("应收合计")
								|| data.get(i).equals("应付合计")) {
							CellRangeAddress cellRangeAddress = new CellRangeAddress(
									k, k, 3, cells - 1);
							sheet.addMergedRegion(cellRangeAddress);
						}
						XSSFCell sourceCell = sourceRow.getCell(i);
						XSSFCell targetCell = targetRow.createCell(i);
						targetCell.setCellStyle(sourceCell.getCellStyle());
						targetCell.setCellType(sourceCell.getCellType());
						if (null != data.get(i) && !"".equals(data.get(i))) {
							// if (isNumeric(data.get(i))) {
							// targetCell.setCellValue(Double.valueOf(data
							// .get(i)));
							// } else {
							targetCell.setCellValue(new XSSFRichTextString(data
									.get(i)));
							// }
						}
					}
				}
				k++;
			}
			fileOut = new FileOutputStream(destFile);
			workbook.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("导出单个的excel文件异常--->>>" + e.getMessage(),e);
		} finally {
			if (null != fileOut) {
				try {
					fileOut.flush();
					fileOut.close();
				} catch (Exception e1) {
					log.error(
							"导出单个的excel文件关闭FileOutputStream异常--->>>"
									+ e1.getMessage(), e1);
				}
			}
		}
	}

	/**
	 * 导出单个的excel文件
	 * 
	 * @param destPath
	 *            导出后生成文件路径
	 * @param list
	 *            要导出的数据
	 * @return
	 */
	public static void expSingleFile(String destPath, List<List<String>> list) {
		// 输出内容至目标文件
		FileOutputStream fileOut = null;
		try {
			File file = new File(destPath);
			// 创建新的excel文件
			XSSFWorkbook workbook = new XSSFWorkbook();
			String sheetName = file.getName().substring(0,
					file.getName().lastIndexOf("."));
			XSSFSheet sheet = workbook.createSheet(sheetName);
			int k = 0;
			for (Iterator<List<String>> it = list.iterator(); it.hasNext();) {
				// 每一行数据
				List<String> data = it.next();
				// 创建目标行
				XSSFRow targetRow = sheet.createRow(k);
				// 循环设置目标列的样式和值
				for (int i = 0; i < data.size(); i++) {
					XSSFCell targetCell = targetRow.createCell(i);
					if (null != data.get(i) && !"".equals(data.get(i))) {
						targetCell.setCellValue(data.get(i));
					}
				}
				k++;
			}
			File destFile = new File(destPath);
			if (!destFile.exists()) {
				File destDir = new File(destFile.getParent());
				if (!destDir.exists()) {
					destDir.mkdirs();
				}
				destFile.createNewFile();
			}
			fileOut = new FileOutputStream(destPath);
			workbook.write(fileOut);
		} catch (Exception e) {
			log.error("导出单个的excel文件异常--->>>" + e.getMessage(), e);
		} finally {
			if (fileOut != null) {
				try {
					fileOut.flush();
					fileOut.close();
				} catch (IOException e1) {
					log.error(
							"导出单个的excel文件关闭FileOutputStream异常--->>>"
									+ e1.getMessage(), e1);
				}
			}
			log.info("结束导出工具类:文件名称" + destPath);
		}
	}

	/**
	 * 生成为CVS文件
	 * 
	 * @param exportData
	 *            源数据List
	 * @param map
	 *            csv文件的列表头map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static void createCSVFile(List<?> exportData, LinkedHashMap map,
			BufferedWriter csvFileOutputStream, int pageNo) {
		try {
			// 写入文件头部
			if (pageNo == 1) {
				for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
						.hasNext();) {
					java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
							.next();
					csvFileOutputStream
							.write((String) propertyEntry.getValue() != null ? new String(
									((String) propertyEntry.getValue())
											.getBytes("GBK"), "GBK") : "");
					if (propertyIterator.hasNext()) {
						csvFileOutputStream.write(",");
					}
				}
				csvFileOutputStream.write("\r\n");
			}
			int count = 0;
			for (Iterator iterator = exportData.iterator(); iterator.hasNext();) {
				Object row = (Object) iterator.next();
				for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator
						.hasNext();) {
					java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
							.next();
					csvFileOutputStream
							.write((String) BeanUtils.getProperty(
									row,
									((String) propertyEntry.getKey()) != null ? (String) propertyEntry
											.getKey() : ""));
					if (propertyIterator.hasNext()) {
						csvFileOutputStream.write(",");
					}
				}
				if (iterator.hasNext()) {
					csvFileOutputStream.write("\r\n");
				}
				count++;
				if (count % 2000 == 0) {
					csvFileOutputStream.flush();
				}
			}
		} catch (Exception e) {
			log.error("导出csv文件异常--->>>" + e.getMessage(), e);
			e.printStackTrace();
		}
	}

	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^\\d+$|^\\d+\\.\\d+$|-\\d+$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

}
